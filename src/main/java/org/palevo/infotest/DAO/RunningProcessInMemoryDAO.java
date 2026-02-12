package org.palevo.infotest.DAO;

import org.palevo.infotest.model.Process;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.palevo.infotest.model.RunningTranslateProcess;

public class RunningProcessInMemoryDAO implements RunningProcessDAO{
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final ConcurrentHashMap<Long,RunningTranslateProcess> storage = new ConcurrentHashMap();

    public RunningTranslateProcess save(RunningTranslateProcess process) {
        storage.put(idGenerator.getAndIncrement(), process);
        return process;
    }
    public Iterable<RunningTranslateProcess> findAll() {
        Iterable<RunningTranslateProcess> list = storage.values();
        return list;
    }
    public void delete(RunningTranslateProcess process) {
        storage.remove(process);
    }
}
