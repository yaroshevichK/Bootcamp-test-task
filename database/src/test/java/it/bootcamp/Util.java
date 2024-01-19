package it.bootcamp;

import it.bootcamp.model.Role;
import it.bootcamp.model.User;

import java.util.ArrayList;
import java.util.List;

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

    public static List<User> createListUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User(null,
                "Jeremie",
                "Christiansen",
                "Parker",
                "third@yahoo.com",
                Role.ADMINISTRATOR
        ));

        users.add(new User(null,
                "Jeremie",
                "Christiansen",
                "Parker",
                "second@yahoo.com",
                Role.SALE_USER
        ));

        users.add(new User(null,
                "Jeremie",
                "Christiansen",
                "Parker",
                "ylast@yahoo.com",
                Role.CUSTOMER_USER
        ));

        users.add(new User(null,
                "Jeremie",
                "Christiansen",
                "Parker",
                "aaa@yahoo.com",
                Role.SECURE_API_USER
        ));

        return users;
    }
}
