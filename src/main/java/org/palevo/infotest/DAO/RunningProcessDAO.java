package org.palevo.infotest.DAO;

import org.palevo.infotest.model.Process;
import org.palevo.infotest.model.RunningTranslateProcess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunningProcessDAO  {
    RunningTranslateProcess save(RunningTranslateProcess process);
    Iterable<RunningTranslateProcess> findAll();
    void delete(RunningTranslateProcess process);
}
