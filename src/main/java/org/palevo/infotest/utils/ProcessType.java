package org.palevo.infotest.utils;

import lombok.Getter;

@Getter
public enum ProcessType {
    TRANSLATE(1),
    MODIFY(2);

    private final int id;

    ProcessType(int id) {
        this.id = id;
    }

    public static ProcessType fromId(int id) {
        for (ProcessType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown ProcessType id: " + id);
    }
}
