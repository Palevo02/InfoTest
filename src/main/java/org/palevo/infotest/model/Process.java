package org.palevo.infotest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.palevo.infotest.utils.ProcessType;

import java.sql.Date;

@Entity
@Table(name="process")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "process_id")
    private Long processId;

    @Column(nullable = false, length = 100)
    private String value;

    @Column(length = 100)
    private String result;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false, length = 30, name = "request_ip")
    private String requestIp;


}
