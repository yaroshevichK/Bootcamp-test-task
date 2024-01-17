package it.bootcamp.service;

import it.bootcamp.dto.UserList;
import it.bootcamp.dto.UserRequest;
import it.bootcamp.dto.UserResponse;
import it.bootcamp.mapper.UserMapper;
import it.bootcamp.model.User;
import it.bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static it.bootcamp.util.ConstantsService.PAGE_SIZE;
import static it.bootcamp.util.ConstantsService.USER_EMAIL;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserResponse createUser(UserRequest userRequest) {
        //========================
        //добавить проверку на существующий email

        User user = userMapper.userRequestToUserMapper(userRequest);
        user = userRepository.save(user);

        return userMapper.userToUserResponseMapper(user);
    }

    public List<UserList> getAllUsers() {
        Sort sortByEmail = Sort.by(Sort.Direction.ASC, USER_EMAIL);
        List<User> users = userRepository.findAll(sortByEmail);

        return users.stream()
                .map(userMapper::userToUserListMapper)
                .collect(Collectors.toList());
    }

    public Page<UserList> getUsersByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(
                pageNumber - 1,
                PAGE_SIZE,
                Sort.Direction.ASC,
                USER_EMAIL
        );
        Page<User> pageUsers = userRepository.findAll(pageable);

        List<UserList> users = pageUsers.getContent().stream()
                .map(userMapper::userToUserListMapper)
                .toList();


        return new PageImpl<>(
                users,
                pageable,
                pageUsers.getTotalElements());
    }
}
