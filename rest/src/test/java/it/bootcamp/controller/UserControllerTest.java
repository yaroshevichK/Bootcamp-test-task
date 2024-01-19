package it.bootcamp.controller;


import it.bootcamp.TestApplication;
import it.bootcamp.Util;
import it.bootcamp.dto.UserResponse;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Sql(
        "classpath:schema.sql"
)
@SpringBootTest(classes = TestApplication.class)
@Testcontainers
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Container
    private static final MySQLContainer<?> container =
            new MySQLContainer<>("mysql:8.1.0");

    @DynamicPropertySource
    static void mySqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Test
    public void testAddUser() throws Exception {
        UserResponse userResponse = Util.createUserResponse();
        String requestContent = Util.createUserRequestJson();

        ResultActions resultActions = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent)
        );

        resultActions
                .andExpect(status().isCreated())
                .andExpect(
                        jsonPath("$.firstName", is(userResponse.firstName()))
                )
                .andExpect(
                        jsonPath("$.surname", is(userResponse.surname()))
                )
                .andExpect(
                        jsonPath("$.middleName", is(userResponse.middleName()))
                )
                .andExpect(
                        jsonPath("$.email", is(userResponse.email()))
                )
                .andExpect(
                        jsonPath("$.role", is(userResponse.role().name()))
                );
    }

    @Test
    public void testAddUserNotCorrectData() throws Exception {
        String requestContent = Util.createUserRequestJsonNotCorrectData();

        ResultActions resultActions = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent)
        );

        resultActions
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddUserNotCorrectEmail() throws Exception {
        String requestContent = Util.createUserRequestJsonNotCorrectEmail();

        ResultActions resultActions = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent)
        );

        resultActions
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddUserNotUniqueEmail() throws Exception {
        String requestContent = Util.createUserRequestJsonNotUniqueEmail();

        ResultActions resultActions = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent)
        );

        resultActions
                .andExpect(status().isConflict())
                .andExpect(
                        jsonPath("$.message", is("User email not unique: Maude_Harris@yahoo.com"))
                );
    }


    @Test
    public void testGetAllUsers() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(15)));
    }


    @Test
    public void testGetUsersByPage() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                get("/users/byPage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("pageNumber","2")
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(5)));
    }

    @Test
    public void testGetUsersByDefaultPage() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                get("/users/byPage")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(10)));
    }

    @Test
    public void testGetUsersByNotCorrectPage() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                get("/users/byPage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("pageNumber","0")
        );

        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.message", is("Page must be positive and not equals 0"))
                );
    }

    @Test
    public void testGetUsersByPageMore() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                get("/users/byPage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("pageNumber","3")
        );

        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.message", is("Page not correct. Count pages in database: 2"))
                );
    }
}