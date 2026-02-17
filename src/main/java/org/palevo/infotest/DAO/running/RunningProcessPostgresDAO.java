package org.palevo.infotest.DAO.running;

import org.palevo.infotest.model.RunningTranslateProcess;
import org.springframework.data.repository.CrudRepository;

public interface RunningProcessPostgresDAO extends RunningProcessDAO,CrudRepository<RunningTranslateProcess,String> {
}
