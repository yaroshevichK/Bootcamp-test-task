package it.bootcamp.util;

public class ConstantsRest {
    public static final String USERS_POINT = "/users";
    public static final String USERS_BY_PAGE_POINT = "/byPage";
    public static final String PARAM_PAGE_NUMBER = "pageNumber";
    public static final String DEFAULT_PAGE = "1";

    //logging
    public static final String START_ADD_ENTITY = "Starting create entity:";
    public static final String COMPLETE_ADD_ENTITY = "Entity is created";
    public static final String REST_CONTROLLER_RUN = "REST controller {} method - {} is running";
    public static final String REST_CONTROLLER_FINISH = "REST controller {} method - {}  is finishing";
    public static final String SERVICE_METHOD_RUN = "Service method - {} class: {}  is running";
    public static final String SERVICE_METHOD_FINISH = "Service method - {} class: {}  is finishing";
    public static final String REST_CONTROLLER_EXCEPTION =
            "Throw exception: method - {} class: {} \n Exception message: {}";
    public static final String EXCEPTION_HANDLER =
            "ExceptionHandler has been invoked: method - {} class - {}";
}
