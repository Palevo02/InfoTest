package org.palevo.infotest.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RunningTranslateProcess {
    @Id
    private Long id;
    @Column(name = "process_id")
    private Long processId;
}
