package org.palevo.infotest.DAO;


import org.palevo.infotest.DTO.ProcessCreateDTO;
import org.palevo.infotest.model.Process;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessDAO {
    <S extends Process> S save(S entity);
     boolean existsById(String id);
    void deleteById(String id);

    Optional<Process> findById(String id);

}
