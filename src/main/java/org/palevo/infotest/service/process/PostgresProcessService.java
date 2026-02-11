package org.palevo.infotest.service.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.palevo.infotest.DAO.ProcessDAO;
import org.palevo.infotest.DTO.ProcessCreateDTO;
import org.palevo.infotest.model.Process;
import org.palevo.infotest.service.text.ModifyService;
import org.palevo.infotest.service.text.TranslateService;
import org.palevo.infotest.utils.ProcessType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Slf4j
@Qualifier("postgres")
public class PostgresProcessService implements ProcessService {
    ModifyService modifyService;
    TranslateService translateService;
    ProcessDAO processDAO;

    public PostgresProcessService(ModifyService modifyService, TranslateService translateService, ProcessDAO processDAO) {
        this.modifyService = modifyService;
        this.translateService = translateService;
        this.processDAO = processDAO;
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
