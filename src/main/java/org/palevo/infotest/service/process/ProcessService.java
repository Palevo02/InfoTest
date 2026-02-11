package org.palevo.infotest.service.process;

import org.palevo.infotest.DTO.ProcessCreateDTO;
import org.palevo.infotest.model.RunningTranslateProcess;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProcessService {
    Long run( String userIp, ProcessCreateDTO process);
    ResponseEntity<String> findResultById(String id);
    ResponseEntity<List<RunningTranslateProcess>> findAllRunningProcess();
    ResponseEntity<String> deleteById(String id);
}
