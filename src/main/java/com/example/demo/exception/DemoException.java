package com.example.demo.exception;

public class DemoException extends RuntimeException {

    public DemoException() {
    }

    public DemoException(String string) {
        super(string);
    }

    public DemoException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public DemoException(Throwable thrwbl) {
        super(thrwbl);
    }
}

