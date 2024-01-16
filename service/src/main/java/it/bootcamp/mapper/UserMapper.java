package it.bootcamp.mapper;

import it.bootcamp.dto.UserList;
import it.bootcamp.dto.UserRequest;
import it.bootcamp.dto.UserResponse;
import it.bootcamp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static it.bootcamp.Constants.MODEL_MAPPER;
import static it.bootcamp.Constants.PATTERN_FULL_NAME;
import static it.bootcamp.Constants.USER_ID;
import static it.bootcamp.Constants.USER_LIST_FULL_NAME;
import static it.bootcamp.Constants.USER_REQ_ROLE;
import static it.bootcamp.Constants.USER_ROLE;

@Mapper(componentModel = MODEL_MAPPER)
public interface UserMapper {
    @Mapping(target = USER_ROLE, source = USER_REQ_ROLE)
    @Mapping(target = USER_ID, ignore = true)
    User userRequestToUserMapper(UserRequest userRequest);

    UserResponse userToUserResponseMapper(User user);

    @Mapping(target = USER_LIST_FULL_NAME, source = ".")
    UserList userToUserListMapper(User user);

    default String getFullName(User user) {
        return String.format(PATTERN_FULL_NAME,
                user.getSurname(),
                user.getFirstName(),
                user.getMiddleName());
    }

}
