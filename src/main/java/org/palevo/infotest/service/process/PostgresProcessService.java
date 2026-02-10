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
        Process process = Process.builder()
                .value(processCreateDTO.getValue())
                .result(choice(processCreateDTO))
                .created(Date.valueOf(LocalDate.now()))
                .requestIp(userIp)
                .build();
        processDAO.save(process);
        return process.getProcessId();
    }

    @Override
    public String choice( ProcessCreateDTO process) {
        String value = "";
        if(process.getType() == ProcessType.MODIFY){
           value = modifyService.modify(process.getValue());
        } else if (process.getType() == ProcessType.TRANSLATE){
            value = "In progress...";
            Thread myThread =
                    new Thread(() -> translateService.translate(process.getValue()));
            myThread.start();
        }
        return value;
    }


}
