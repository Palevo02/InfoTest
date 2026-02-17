package org.palevo.infotest.DAO.process;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.palevo.infotest.model.Process;

public class ProcessInMemoryDAO implements ProcessDAO {

    private final AtomicLong idGenerator = new AtomicLong(1);
    private final ConcurrentHashMap<Long, Process> storage = new ConcurrentHashMap();

    public Process save(Process process) {
        storage.put(idGenerator.getAndIncrement(), process);
        return process;
    }

    public boolean existsById(String id) {
        if (storage.containsKey(Long.valueOf(id))) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteById(String id) {
            storage.remove(Long.valueOf(id));
    }

    public Optional<Process> findById(String id) {
        return Optional.of((Process) storage.get(Long.valueOf(id)));
    }
}
