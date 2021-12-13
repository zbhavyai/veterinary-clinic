package ca.ucalgary.vetapp.controller;

public class UserExistsException extends RuntimeException {
    UserExistsException(String email) {
        super(String.format("[FAIL] Email is \"%s\" already taken. Could not register.", email));
    }
}
