package it.bootcamp.util;

public class DataService {
    public static final String MODEL_MAPPER = "spring";
    public static final String USER_LIST_FULL_NAME = "fullName";
    public static final String PATTERN_FULL_NAME = "%s %s %s";
    public static final String USER_EMAIL = "email";

    //validation
    public static final String MESSAGE_NOT_BLANK = "{not-blank}";
    public static final String MESSAGE_NAME_LENGTH = "{name.length}";
    public static final String PATTERN_LATIN = "[a-zA-Z]*";
    public static final String MESSAGE_LATIN = "{latin-symbols}";
    public static final int MIN_FIRST_NAME = 3;
    public static final int MAX_FIRST_NAME = 20;
    public static final String MESSAGE_SURNAME_LENGTH = "{surname.length}";
    public static final int MIN_SURNAME = 3;
    public static final int MAX_SURNAME = 40;
    public static final String MESSAGE_MIDDLE_NAME_LENGTH = "{middle-name.length}";
    public static final int MIN_MIDDLE_NAME = 3;
    public static final int MAX_MIDDLE_NAME = 40;
    public static final String MESSAGE_EMAIL = "{email}";

    public static final String MESSAGE_ROLE = "{roles}";
}
