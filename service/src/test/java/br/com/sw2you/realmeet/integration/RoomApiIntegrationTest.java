package br.com.sw2you.realmeet.integration;

import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_ID;
import static br.com.sw2you.realmeet.utils.TestDataCreator.roomBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.sw2you.realmeet.api.facade.RoomApi;
import br.com.sw2you.realmeet.api.model.RoomDTO;
import br.com.sw2you.realmeet.core.BaseIntegrationTest;
import br.com.sw2you.realmeet.domains.entities.Room;
import br.com.sw2you.realmeet.domains.repositories.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RoomApiIntegrationTest extends BaseIntegrationTest {
    @InjectMocks
    private RoomApi roomApi;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    protected void setupEach() throws Exception {
        setLocalhostBasePath(roomApi.getApiClient(), "/v1");
    }

    @Test
    void test() {
        final Room roomBuild = roomBuilder().id(DEFAULT_ROOM_ID).build();
        roomRepository.saveAndFlush(roomBuild);
        final RoomDTO room = roomApi.getRoom(DEFAULT_ROOM_ID);
        assertEquals(roomBuild.getName(), room.getName());
    }
}
