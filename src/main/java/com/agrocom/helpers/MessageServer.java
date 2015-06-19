package com.agrocom.helpers;

public class MessageServer {
    public static synchronized String serveErrorMessage(String code) {
        switch (code) {
            case "invalidEmail": {
                return "Please enter a valid email";
            }
            case "firstNameOrLastNameTooShort": {
                return "Please add a minimum of four letters name";
            }
            case "invalidPassword": {
                return "Please add a minimum 6 letter password";
            }
            case "emailAlreadyUsed": {
                return "The entered email is already used";
            }
            case "nameAlreadyUsed": {
                return "The entered name is already used";
            }
            case "emptyFields": {
                return "Please fill all the fields";
            }
            case "errorCreatingUser": {
                return "An error occurred during account creation. Please try again";
            }
            case "invalidToken": {
                return "Invalid token";
            }
            default: {
                return null;
            }
        }

    }
}
