package net.bootlab.webchat.exceptions;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException() {
        super();
    }

    public ChatNotFoundException(String s) {
        super(s);
    }

    public ChatNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
