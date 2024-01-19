package it.bootcamp;

import it.bootcamp.dto.UserResponse;
import it.bootcamp.model.Role;

public class Util {
    public static String createUserRequestJson() {
        return "{" +
                "\"firstName\":\"Jeremie\"," +
                "\"surname\":\"Christiansen\"," +
                "\"middleName\":\"Parker\"," +
                "\"email\":\"third@yahoo.com\"," +
                "\"role\":\"ADMINISTRATOR\"" +
                "}";
    }

    public static String createUserRequestJsonNotCorrectData() {
        return "{" +
                "\"firstName\":\"Jeremie24fg\"," +
                "\"surname\":\"C\"," +
                "\"middleName\":\"Parker763\"," +
                "\"email\":\"thirdyahoo.com\"," +
                "\"role\":\"ADMIN\"" +
                "}";
    }

    public static String createUserRequestJsonNotCorrectEmail() {
        return "{" +
                "\"firstName\":\"Jeremie\"," +
                "\"surname\":\"Christiansen\"," +
                "\"middleName\":\"Parker\"," +
                "\"email\":\"thirdyahoo.com\"," +
                "\"role\":\"ADMINISTRATOR\"" +
                "}";
    }

    public static String createUserRequestJsonNotUniqueEmail() {
        return "{" +
                "\"firstName\":\"Jeremie\"," +
                "\"surname\":\"Christiansen\"," +
                "\"middleName\":\"Parker\"," +
                "\"email\":\"Maude_Harris@yahoo.com\"," +
                "\"role\":\"ADMINISTRATOR\"" +
                "}";
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
}
