package org.palevo.infotest.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.palevo.infotest.utils.ProcessType;
@Data
@Getter
@Setter
@AllArgsConstructor
public class ProcessCreateDTO {

    @NotNull
    ProcessType type;
    @Size(min = 1, max = 100, message = "Input data length must be between 1 and 1000 characters")
    String value;
    @Pattern(regexp = "[A-Z]{3}", message = "Source language must be a 3-letter uppercase code")
    String fromLanguage;
    @Pattern(regexp = "[A-Z]{3}", message = "Source language must be a 3-letter uppercase code")
    String toLanguage;
}
