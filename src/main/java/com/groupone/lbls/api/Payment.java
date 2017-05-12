package com.groupone.lbls.api;

public class Payment {
    private static String validCardNumber = "4444 4444 4444 4444";

    public static boolean process(String cardNumber) {
        return cardNumber.equals(validCardNumber);
    }
}
