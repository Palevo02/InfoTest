package org.palevo.infotest.DAO;

import org.palevo.infotest.model.RunningTranslateProcess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningProcessDAO extends CrudRepository<RunningTranslateProcess,String> {
}
