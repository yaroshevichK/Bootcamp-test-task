package it.bootcamp.service;

import it.bootcamp.dto.UserList;
import it.bootcamp.dto.UserRequest;
import it.bootcamp.dto.UserResponse;
import it.bootcamp.exceptions.NoContentException;
import it.bootcamp.exceptions.NotCorrectPageException;
import it.bootcamp.exceptions.NotUniqueException;
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

import static it.bootcamp.util.ConstantsService.ERROR_EMAIL_NOT_UNIQUE;
import static it.bootcamp.util.ConstantsService.ERROR_NO_CONTENT;
import static it.bootcamp.util.ConstantsService.ERROR_PAGE_NOT_CORRECT;
import static it.bootcamp.util.ConstantsService.ERROR_PAGE_NOT_POSITIVE;
import static it.bootcamp.util.ConstantsService.PAGE_SIZE;
import static it.bootcamp.util.ConstantsService.USER_EMAIL;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserResponse createUser(UserRequest userRequest) {
        validateEmail(userRequest);

        User user = userMapper.userRequestToUserMapper(userRequest);
        user = userRepository.save(user);

        return userMapper.userToUserResponseMapper(user);
    }

    public List<UserList> getAllUsers() {
        Sort sortByEmail = Sort.by(Sort.Direction.ASC, USER_EMAIL);
        List<User> users = userRepository.findAll(sortByEmail);

        if (users.size() == 0) {
            throw new NoContentException(ERROR_NO_CONTENT);
        }

        return users.stream()
                .map(userMapper::userToUserListMapper)
                .collect(Collectors.toList());
    }

    public Page<UserList> getUsersByPage(int pageNumber) {
        if (pageNumber <= 0) {
            throw new NotCorrectPageException(ERROR_PAGE_NOT_POSITIVE);
        }

        Pageable pageable = PageRequest.of(
                pageNumber - 1,
                PAGE_SIZE,
                Sort.Direction.ASC,
                USER_EMAIL
        );
        Page<User> pageUsers = userRepository.findAll(pageable);
        validationPage(pageNumber, pageUsers);

        List<UserList> users = pageUsers.getContent().stream()
                .map(userMapper::userToUserListMapper)
                .toList();


        return new PageImpl<>(
                users,
                pageable,
                pageUsers.getTotalElements());
    }

    private void validateEmail(UserRequest userRequest) {
        User findUser = userRepository.findByEmail(userRequest.getEmail());
        if (findUser != null) {
            throw new NotUniqueException(
                    String.format(ERROR_EMAIL_NOT_UNIQUE,
                            userRequest.getEmail())
            );
        }
    }

    private void validationPage(int pageNumber, Page<User> pageUsers) {
        if (pageUsers.getTotalPages() == 0) {
            throw new NoContentException(ERROR_NO_CONTENT);
        }
        if (pageNumber > pageUsers.getTotalPages()) {
            throw new NotCorrectPageException(
                    String.format(ERROR_PAGE_NOT_CORRECT, pageUsers.getTotalPages()));
        }
        if (pageUsers.getContent().isEmpty()) {
            throw new NoContentException(ERROR_NO_CONTENT);
        }
    }
}
