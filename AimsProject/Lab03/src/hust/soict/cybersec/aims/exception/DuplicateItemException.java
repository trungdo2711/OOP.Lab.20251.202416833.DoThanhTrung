package hust.soict.cybersec.aims.exception;

public class DuplicateItemException extends Exception {

    // Default constructor
    public DuplicateItemException() {
    }

    // Constructor with message
    public DuplicateItemException(String message) {
        super(message);
    }
}