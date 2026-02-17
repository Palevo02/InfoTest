package org.palevo.infotest.DAO.running;

import org.palevo.infotest.model.RunningTranslateProcess;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningProcessDAO  {
    RunningTranslateProcess save(RunningTranslateProcess process);
    Iterable<RunningTranslateProcess> findAll();
    void delete(RunningTranslateProcess process);
}
