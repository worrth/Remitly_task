package com.example.remilty.exceptions.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException() {
        super("Either provided country code does not exist or there are no banks with that code");
    }
}
