package ca.ucalgary.vetapp.controller;

public class NotFoundException extends RuntimeException {
    NotFoundException(String entity, Long id) {
        super(String.format("[FAIL] Could not find %s with id = %l.", entity, id));
    }
}
