package com.groupone.lbls.model;

/*
 * Contains type of users. There are 2 types of users
 * Librarian and Customers
 */
public enum UserRole {
    NULL, LIBRARIAN, CUSTOMER;

    public static UserRole fromInt(int i) {
        switch (i) {
            case 1:
                return LIBRARIAN;
            case 2:
                return CUSTOMER;
        }
        return NULL;
    }

    @Override
    public String toString() {
        switch (this) {
            case LIBRARIAN:
                return "Librarian";
            case CUSTOMER:
                return "Customer";
        }
        return "Null";
    }
}
