package com.example.remilty.exceptions.exception;

public class SwiftCodeNotFoundException extends RuntimeException {
    public SwiftCodeNotFoundException() {
        super("Swift code not found");
    }
}
