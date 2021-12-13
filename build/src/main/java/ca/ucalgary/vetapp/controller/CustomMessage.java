package ca.ucalgary.vetapp.controller;

import org.springframework.http.HttpStatus;

/**
 * Wraps a custom message as object to be presented as json
 */
public class CustomMessage {
    private HttpStatus status;
    private String message;

    /**
     * Constructor
     *
     * @param code    the bad http status code
     * @param message the message to support
     */
    CustomMessage(HttpStatus code, String message) {
        this.status = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
