package it.bootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String fistName;

    private String surname;

    private String middleName;

    private String email;

    private String role;
}
