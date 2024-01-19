package it.bootcamp;

import it.bootcamp.dto.UserList;
import it.bootcamp.dto.UserRequest;
import it.bootcamp.dto.UserResponse;
import it.bootcamp.model.Role;
import it.bootcamp.model.User;

public class Util {
    public static User createUser() {
        return new User(
                null,
                "Jeremie",
                "Christiansen",
                "Parker",
                "third@yahoo.com",
                Role.ADMINISTRATOR
        );
    }

    public static UserRequest createUserRequest() {
        return new UserRequest(
                "Jeremie",
                "Christiansen",
                "Parker",
                "third@yahoo.com",
                "ADMINISTRATOR"
        );
    }

    public static UserResponse createUserResponse() {
        return new UserResponse(
                "Jeremie",
                "Christiansen",
                "Parker",
                "third@yahoo.com",
                Role.ADMINISTRATOR
        );
    }

    public static UserList createUserList() {
        return new UserList("Christiansen Jeremie Parker",
                "third@yahoo.com",
                Role.ADMINISTRATOR
        );
    }

    public static User createUser1() {
        return new User(
                null,
                "Jeremie",
                "Christiansen",
                "Parker",
                "ylast@yahoo.com",
                Role.CUSTOMER_USER
        );
    }

    public static User createUser2() {
        return new User(
                null,
                "Jeremie",
                "Christiansen",
                "Parker",
                "aaa@yahoo.com",
                Role.SECURE_API_USER
        );
    }

    public static UserList createUserList1() {
        return new UserList(
                "Jeremie Christiansen Parker",
                "ylast@yahoo.com",
                Role.CUSTOMER_USER
        );
    }

    public static UserList createUserList2() {
        return new UserList(
                "Jeremie Christiansen Parker",
                "aaa@yahoo.com",
                Role.SECURE_API_USER
        );
    }
}
