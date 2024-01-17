package it.bootcamp.mapper;

import it.bootcamp.dto.UserList;
import it.bootcamp.dto.UserRequest;
import it.bootcamp.dto.UserResponse;
import it.bootcamp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static it.bootcamp.util.ConstantsService.MODEL_MAPPER;
import static it.bootcamp.util.ConstantsService.PATTERN_FULL_NAME;
import static it.bootcamp.util.ConstantsService.USER_LIST_FULL_NAME;

@Mapper(componentModel = MODEL_MAPPER)
public interface UserMapper {
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
