package com.app.flagtick.exception;

/**
 * @author Flagtick Inc
 * You should define and throw a specific exception rather than using a generic RuntimeException.
 * This improves code clarity and makes it easier to handle specific error conditions.
 */
public class PDFGenerationException extends Exception {

    public PDFGenerationException(String message) {
        super(message);
    }

    public PDFGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

}
