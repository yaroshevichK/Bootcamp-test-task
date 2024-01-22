package it.bootcamp.dto;

import it.bootcamp.model.Role;

public record UserResponse(String firstName,
                           String surname,
                           String middleName,
                           String email,
                           Role role) {
}

