package org.palevo.infotest.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.processing.Pattern;
import org.palevo.infotest.utils.ProcessType;
@Data
@Getter
@Setter
@AllArgsConstructor
public class ProcessCreateDTO {

    ProcessType type;
    String value;
    String fromLanguage;
    String toLanguage;
}
