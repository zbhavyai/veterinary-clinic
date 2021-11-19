package ca.ucalgary.vetapp.controller;

public class UnsupportedRequestException extends RuntimeException {
    UnsupportedRequestException(String message) {
        super(String.format("[FAIL] %s. Please check your request", message));
    }
}
