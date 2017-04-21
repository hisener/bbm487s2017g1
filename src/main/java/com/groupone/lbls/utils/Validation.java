package com.groupone.lbls.utils;

import java.util.regex.Pattern;

public class Validation {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);

    public static boolean isEmailValid(String email) {
        return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
    }
}