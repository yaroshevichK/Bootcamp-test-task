package it.bootcamp.dto;

import it.bootcamp.model.Role;
import it.bootcamp.validation.RoleValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static it.bootcamp.util.DataService.MAX_FIRST_NAME;
import static it.bootcamp.util.DataService.MAX_MIDDLE_NAME;
import static it.bootcamp.util.DataService.MAX_SURNAME;
import static it.bootcamp.util.DataService.MESSAGE_EMAIL;
import static it.bootcamp.util.DataService.MESSAGE_LATIN;
import static it.bootcamp.util.DataService.MESSAGE_MIDDLE_NAME_LENGTH;
import static it.bootcamp.util.DataService.MESSAGE_NAME_LENGTH;
import static it.bootcamp.util.DataService.MESSAGE_NOT_BLANK;
import static it.bootcamp.util.DataService.MESSAGE_SURNAME_LENGTH;
import static it.bootcamp.util.DataService.MIN_FIRST_NAME;
import static it.bootcamp.util.DataService.MIN_MIDDLE_NAME;
import static it.bootcamp.util.DataService.MIN_SURNAME;
import static it.bootcamp.util.DataService.PATTERN_LATIN;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = MESSAGE_NOT_BLANK)
    @Size(min = MIN_FIRST_NAME, max = MAX_FIRST_NAME,
            message = MESSAGE_NAME_LENGTH)
    @Pattern(regexp = PATTERN_LATIN, message = MESSAGE_LATIN)
    private String firstName;

    @NotBlank(message = MESSAGE_NOT_BLANK)
    @Size(min = MIN_SURNAME, max = MAX_SURNAME,
            message = MESSAGE_SURNAME_LENGTH)
    @Pattern(regexp = PATTERN_LATIN, message = MESSAGE_LATIN)
    private String surname;

    @NotBlank(message = MESSAGE_NOT_BLANK)
    @Size(min = MIN_MIDDLE_NAME, max = MAX_MIDDLE_NAME,
            message = MESSAGE_MIDDLE_NAME_LENGTH)
    @Pattern(regexp = PATTERN_LATIN, message = MESSAGE_LATIN)
    private String middleName;

    @Email(message = MESSAGE_EMAIL)
    private String email;

    @NotNull
    @RoleValidator(role = Role.class)
    private String role;
}
