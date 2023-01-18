package de.thomas.creatures.implementation.util;

public class AssertionException extends Exception {
    private static final long serialVersionUID = -4980406126997282476L;
    private final String message;

    public AssertionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
