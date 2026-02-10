package org.palevo.infotest.service.process;

import org.palevo.infotest.DTO.ProcessCreateDTO;

public interface ProcessService {
    Long run( String userIp, ProcessCreateDTO process);
    String choice( ProcessCreateDTO process);
}
