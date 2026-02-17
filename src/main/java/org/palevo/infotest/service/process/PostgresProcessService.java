package org.palevo.infotest.service.process;

import lombok.extern.slf4j.Slf4j;
import org.palevo.infotest.DAO.process.ProcessDAO;
import org.palevo.infotest.DAO.running.RunningProcessDAO;
import org.palevo.infotest.DTO.ProcessCreateDTO;
import org.palevo.infotest.model.Process;
import org.palevo.infotest.model.RunningTranslateProcess;
import org.palevo.infotest.service.text.ModifyService;
import org.palevo.infotest.service.text.TranslateService;
import org.palevo.infotest.utils.ProcessType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Qualifier("postgres")
public class PostgresProcessService implements ProcessService {
    ModifyService modifyService;
    TranslateService translateService;
    ProcessDAO processDAO;
    RunningProcessDAO runningProcessDAO;

    public PostgresProcessService(ModifyService modifyService, TranslateService translateService, ProcessDAO processDAO, RunningProcessDAO runningProcessDAO) {
        this.modifyService = modifyService;
        this.translateService = translateService;
        this.processDAO = processDAO;
        this.runningProcessDAO = runningProcessDAO;
    }

    @Override
    public Long run(String userIp, ProcessCreateDTO processCreateDTO) {

        if (processCreateDTO.getType() == ProcessType.MODIFY) {
            return modify(processCreateDTO, userIp);
        } else if (processCreateDTO.getType() == ProcessType.TRANSLATE) {
            return translate(processCreateDTO, userIp);
        }
        throw new IllegalArgumentException("Unsupported type: " + processCreateDTO.getType());
    }

    @Override
    public ResponseEntity<String> findResultById(String id) {
//        Process process = processDAO.getProcessBy(id);
        Optional<Process> process = processDAO.findById(id);


        if (process.isPresent()) {
            return ResponseEntity.ok(process.get().getResult());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<RunningTranslateProcess>> findAllRunningProcess() {
//        List<RunningTranslateProcess> list = processDAO.findAll().forEach((process -> list.add(process));
        Iterable<RunningTranslateProcess> list = runningProcessDAO.findAll();
        List<RunningTranslateProcess> target = new ArrayList<>();
        list.forEach(target::add);
        return ResponseEntity.ok(target);
    }

    @Override
    public ResponseEntity<String> deleteById(String id) {
        if (processDAO.existsById(id)) {
            processDAO.deleteById(id);
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public Long modify(ProcessCreateDTO processCreateDTO, String userIp) {
        String value = modifyService.modify(processCreateDTO.getValue());
        Process process = createProcess(processCreateDTO, userIp, value);
        processDAO.save(process);
        return process.getProcessId();
    }

    public Long translate(ProcessCreateDTO processCreateDTO, String userIp) {
        String value = "In progress...";

        Process process = createProcess(processCreateDTO, userIp, value);
        processDAO.save(process);
        RunningTranslateProcess runningProcess = RunningTranslateProcess.builder().processId(process.getProcessId()).build();
        runningProcessDAO.save(runningProcess);
        Thread thread = new Thread() {
            public void run() {
                String value = translateService.fakeTranslate(processCreateDTO);
                Process updateProcess = Process.builder()
                        .processId(process.getProcessId())
                        .value(processCreateDTO.getValue())
                        .result(value)
                        .created(Date.valueOf(LocalDate.now()))
                        .requestIp(userIp)
                        .build();
                processDAO.save(updateProcess);
                runningProcessDAO.delete(runningProcess);
            }
        };
        thread.start();
        return process.getProcessId();
    }

    private Process createProcess(ProcessCreateDTO processCreateDTO, String userIp, String result) {
        Process newProcess = Process.builder()
                .value(processCreateDTO.getValue())
                .result(result)
                .created(Date.valueOf(LocalDate.now()))
                .requestIp(userIp)
                .build();
        return newProcess;
    }


}
