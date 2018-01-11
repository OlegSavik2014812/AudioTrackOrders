package service.validation.validationregex;

public final class ValidatorRegEx {
    public final static String USER_FIRST_NAME = "^(([A-Z]{1}[a-z]{1,}))$";
    public final static String USER_LAST_NAME = "^(([A-Z]{1}[a-z]{1,}))$";
    public final static String USER_LOGIN = "^([A-Za-z0-9_]{5,})$";
    public final static String USER_PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,})";

    private ValidatorRegEx() {
    }
}
