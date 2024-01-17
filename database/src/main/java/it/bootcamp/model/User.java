package it.bootcamp.model;

import it.bootcamp.util.DataDatabase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static it.bootcamp.util.DataDatabase.DB_FIRST_NAME;
import static it.bootcamp.util.DataDatabase.DB_USERS;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DB_USERS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = DB_FIRST_NAME)
    private String firstName;

    @Column
    private String surname;

    @Column(name = DataDatabase.DB_MIDDLE_NAME)
    private String middleName;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
