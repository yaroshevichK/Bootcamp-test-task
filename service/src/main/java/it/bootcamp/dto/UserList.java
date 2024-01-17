package it.bootcamp.dto;

import it.bootcamp.model.Role;

public record UserList(String fullName,
                       String email,
                       Role role) {

}
