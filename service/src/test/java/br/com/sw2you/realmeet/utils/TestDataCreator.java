package br.com.sw2you.realmeet.utils;

import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_NAME;
import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_SEATS;

import br.com.sw2you.realmeet.domains.entities.Room;

public final class TestDataCreator {

    private TestDataCreator() {}

    public static Room.RoomBuilder roomBuilder() {
        return Room.builder().name(DEFAULT_ROOM_NAME).seats(DEFAULT_ROOM_SEATS);
    }
}
