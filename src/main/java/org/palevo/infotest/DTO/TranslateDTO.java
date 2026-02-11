package org.palevo.infotest.DTO;

import lombok.*;
import org.palevo.infotest.utils.ProcessType;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class TranslateDTO {
    String value;
    String fromLanguage;
    String toLanguage;
}
