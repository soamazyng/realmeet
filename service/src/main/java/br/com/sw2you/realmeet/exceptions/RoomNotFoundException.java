package br.com.sw2you.realmeet.exceptions;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(final String message) {
        super(message);
    }
}
