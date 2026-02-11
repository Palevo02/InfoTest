package org.palevo.infotest.DAO;


import org.palevo.infotest.DTO.ProcessCreateDTO;
import org.palevo.infotest.model.Process;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessDAO extends CrudRepository<Process, String> {
    
}
