package it.bootcamp.service;

import it.bootcamp.Util;
import it.bootcamp.dto.UserList;
import it.bootcamp.dto.UserRequest;
import it.bootcamp.dto.UserResponse;
import it.bootcamp.exceptions.NoContentException;
import it.bootcamp.exceptions.NotCorrectPageException;
import it.bootcamp.mapper.UserMapper;
import it.bootcamp.model.User;
import it.bootcamp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() {
        UserRequest userRequest = Util.createUserRequest();
        UserResponse userResponse = Util.createUserResponse();
        User user = new User();

        when(userMapper.userRequestToUserMapper(userRequest)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserResponseMapper(user)).thenReturn(userResponse);

        assertEquals(userService.createUser(userRequest), userResponse);

        verify(userMapper).userRequestToUserMapper(userRequest);
        verify(userRepository).save(user);
        verify(userMapper).userToUserResponseMapper(user);
    }

    @Test
    void testGetAllUserSortedByEmail() {
        User user1 = Util.createUser1();
        User user2 = Util.createUser2();
        List<User> users = Stream.of(user1, user2)
                .sorted(Comparator.comparing(User::getEmail))
                .toList();

        UserList userList1 = Util.createUserList1();
        UserList userList2 = Util.createUserList2();
        List<UserList> usersList = Stream.of(userList1, userList2)
                .sorted(Comparator.comparing(UserList::email))
                .toList();

        Sort sortByEmail = Sort.by(Sort.Direction.ASC, "email");


        when(userRepository.findAll(sortByEmail)).thenReturn(users);

        when(userMapper.userToUserListMapper(user1)).thenReturn(userList1);
        when(userMapper.userToUserListMapper(user2)).thenReturn(userList2);

        List<UserList> allUsers = userService.getAllUsers();

        assertAll(
                () -> assertEquals(allUsers.size(), 2),
                () -> assertEquals(usersList.get(0), allUsers.get(0)),
                () -> assertEquals(usersList.get(1), allUsers.get(1))
        );


        verify(userMapper).userToUserListMapper(user1);
        verify(userMapper).userToUserListMapper(user2);
        verify(userRepository).findAll(sortByEmail);
    }

    @Test
    void testGetEmptyList() {
        Sort sortByEmail = Sort.by(Sort.Direction.ASC, "email");
        when(userRepository.findAll(sortByEmail)).thenReturn(new ArrayList<>());

        assertThrows(NoContentException.class, () -> userService.getAllUsers());
        verify(userRepository).findAll(sortByEmail);
    }


    @Test
    void testGetAllUserSortedByEmailByPage() {
        User user1 = Util.createUser1();
        User user2 = Util.createUser2();
        List<User> users = Stream.of(user1, user2)
                .sorted(Comparator.comparing(User::getEmail))
                .toList();

        UserList userList1 = Util.createUserList1();
        UserList userList2 = Util.createUserList2();
        List<UserList> usersList = Stream.of(userList1, userList2)
                .sorted(Comparator.comparing(UserList::email))
                .toList();


        Pageable pageable = PageRequest.of(
                0,
                10,
                Sort.Direction.ASC,
                "email"
        );
        Page<User> pageMock = new PageImpl<>(users);


        when(userRepository.findAll(pageable)).thenReturn(pageMock);
        when(userMapper.userToUserListMapper(user1)).thenReturn(userList1);
        when(userMapper.userToUserListMapper(user2)).thenReturn(userList2);

        Page<UserList> usersByPage = userService.getUsersByPage(1);

        assertAll(
                () -> assertEquals(usersByPage.getTotalPages(), 1),
                () -> assertEquals(usersByPage.getContent().size(), 2),
                () -> assertEquals(usersList.get(0), usersByPage.getContent().get(0)),
                () -> assertEquals(usersList.get(1), usersByPage.getContent().get(1))
        );


        verify(userMapper).userToUserListMapper(user1);
        verify(userMapper).userToUserListMapper(user2);
        verify(userRepository).findAll(pageable);
    }

    @Test
    void testGetEmptyListUsersByPage() {
        Pageable pageable = PageRequest.of(
                0,
                10,
                Sort.Direction.ASC,
                "email"
        );
        Page<User> pageMock = new PageImpl<>(new ArrayList<>());

        when(userRepository.findAll(pageable)).thenReturn(pageMock);
        assertThrows(NoContentException.class,
                () -> userService.getUsersByPage(1));

        verify(userRepository).findAll(pageable);
    }

    @Test
    void testGetUsersByPageNotCorrectPage() {
        Pageable pageable = PageRequest.of(
                1,
                10,
                Sort.Direction.ASC,
                "email"
        );
        List<User> users = Arrays.asList(new User(),new User());
        Page<User> pageMock = new PageImpl<>(users);

        when(userRepository.findAll(pageable)).thenReturn(pageMock);
        assertThrows(NotCorrectPageException.class,
                () -> userService.getUsersByPage(2));

        verify(userRepository).findAll(pageable);
    }

    @Test
    void testGetAllUserByZeroPage() {
        assertThrows(NotCorrectPageException.class,
                () -> userService.getUsersByPage(0));
    }

    @Test
    void testGetAllUserByErrorPage() {
        assertThrows(NotCorrectPageException.class,
                () -> userService.getUsersByPage(-1));
    }

}