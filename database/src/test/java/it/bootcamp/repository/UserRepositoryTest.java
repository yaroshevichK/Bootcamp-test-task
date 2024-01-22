package it.bootcamp.repository;

import it.bootcamp.TestApplication;
import it.bootcamp.Util;
import it.bootcamp.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = TestApplication.class)
@Testcontainers
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Container
    private static final MySQLContainer<?> container =
            new MySQLContainer<>("mysql:8.1.0");


    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    User newUser = Util.createUser();
    List<User> listUsers = Util.createListUsers();

    @Test
    void testCreateUser() {
        User insertedUser = userRepository.save(newUser);

        User actualUser = userRepository.findById(insertedUser.getId())
                .orElse(null);
        assertAll(
                () -> assertNotNull(actualUser),
                () -> assertThat(actualUser).isEqualTo(newUser)
        );
    }

    @Test
    void testGetAllUsersSortedByEmail() {
        userRepository.saveAll(listUsers);
        Sort sortByEmail = Sort.by(Sort.Direction.ASC, "email");
        List<User> sortedUsers = listUsers
                .stream()
                .sorted(Comparator.comparing(User::getEmail))
                .toList();

        List<User> users = userRepository.findAll(sortByEmail);
        assertAll(
                () -> assertThat(users).hasSize(4),
                () -> assertEquals(users.get(0).getEmail(), sortedUsers.get(0).getEmail()),
                () -> assertEquals(users.get(1).getEmail(), sortedUsers.get(1).getEmail()),
                () -> assertEquals(users.get(2).getEmail(), sortedUsers.get(2).getEmail()),
                () -> assertEquals(users.get(3).getEmail(), sortedUsers.get(3).getEmail())
        );

    }

    @Test
    void testGetAllUsersByPage() {
        userRepository.saveAll(listUsers);
        Pageable pageable = PageRequest.of(
                0,
                10,
                Sort.Direction.ASC,
                "email"
        );
        List<User> sortedUsers = listUsers
                .stream()
                .sorted(Comparator.comparing(User::getEmail))
                .toList();

        Page<User> userPage = userRepository.findAll(pageable);
        assertAll(
                () -> assertEquals(userPage.getTotalPages(), 1),
                () -> assertThat(userPage.getContent()).hasSize(4),
                () -> assertEquals(userPage.getContent().get(0).getEmail(), sortedUsers.get(0).getEmail()),
                () -> assertEquals(userPage.getContent().get(1).getEmail(), sortedUsers.get(1).getEmail()),
                () -> assertEquals(userPage.getContent().get(2).getEmail(), sortedUsers.get(2).getEmail()),
                () -> assertEquals(userPage.getContent().get(3).getEmail(), sortedUsers.get(3).getEmail())
        );
    }

    @Test
    void testFindUserByEmail() {
        userRepository.save(newUser);
        assertNotNull(userRepository.findByEmail(newUser.getEmail()));
    }

    @AfterEach
    void cleanData() {
        userRepository.deleteAll();
    }

}