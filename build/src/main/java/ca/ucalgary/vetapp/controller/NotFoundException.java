package ca.ucalgary.vetapp.controller;

public class NotFoundException extends RuntimeException {
    NotFoundException(Long id) {
        super("[FAIL] Could not find entity with id = " + id);
    }
}
