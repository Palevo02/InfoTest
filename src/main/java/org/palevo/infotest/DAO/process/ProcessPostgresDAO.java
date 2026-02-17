package org.palevo.infotest.DAO.process;

import org.palevo.infotest.model.Process;
import org.springframework.data.repository.CrudRepository;

public interface ProcessPostgresDAO extends ProcessDAO,CrudRepository<Process, String> {
}
