package it.bootcamp.mapper;

import it.bootcamp.TestApplication;
import it.bootcamp.Util;
import it.bootcamp.dto.UserList;
import it.bootcamp.dto.UserRequest;
import it.bootcamp.dto.UserResponse;
import it.bootcamp.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TestApplication.class})
class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testUserRequestToUserMapper() {
        UserRequest userRequest = Util.createUserRequest();
        User user = Util.createUser();

        User result = userMapper.userRequestToUserMapper(userRequest);
        assertThat(user).isEqualTo(result);
    }

    @Test
    public void testUserToUserListMapper() {
        UserList userList = Util.createUserList();
        User user = Util.createUser();

        UserList result = userMapper.userToUserListMapper(user);
        assertThat(userList).isEqualTo(result);
    }

    @Test
    public void testUserToUserResponseMapper() {
        UserResponse userResponse = Util.createUserResponse();
        User user = Util.createUser();

        UserResponse result = userMapper.userToUserResponseMapper(user);
        assertThat(userResponse).isEqualTo(result);
    }
}