package org.palevo.infotest.DAO;

import org.palevo.infotest.model.RunningTranslateProcess;
import org.springframework.data.repository.CrudRepository;

public interface RunningProcessPostgresDAO extends RunningProcessDAO,CrudRepository<RunningTranslateProcess,String> {
}
