package br.com.sw2you.realmeet.integration;

import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_ID;
import static br.com.sw2you.realmeet.utils.TestDataCreator.roomBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.sw2you.realmeet.api.facade.RoomApi;
import br.com.sw2you.realmeet.api.model.RoomDTO;
import br.com.sw2you.realmeet.core.BaseIntegrationTest;
import br.com.sw2you.realmeet.domains.entities.Room;
import br.com.sw2you.realmeet.domains.repositories.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

@ExtendWith(SpringExtension.class)
class RoomApiIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private RoomApi roomApi;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    protected void setupEach() throws Exception {
        setLocalhostBasePath(roomApi.getApiClient(), "/v1");
    }

    @Test
    void testGetRoomSuccess() {
        final Room roomBuild = roomBuilder().build();
        roomRepository.saveAndFlush(roomBuild);

        assertNotNull(roomBuild.getId());
        assertTrue(roomBuild.getActive());

        final RoomDTO roomDTO = roomApi.getRoom(roomBuild.getId());

        assertEquals(roomBuild.getId(), roomDTO.getId());
        assertEquals(roomBuild.getName(), roomDTO.getName());
        assertEquals(roomBuild.getSeats(), roomDTO.getSeats());
    }

    @Test
    void testGetRoomInactive() {
        final Room roomBuild = roomBuilder().active(false).build();
        roomRepository.saveAndFlush(roomBuild);

        assertFalse(roomBuild.getActive());
        assertThrows(HttpClientErrorException.NotFound.class, () -> roomApi.getRoom(roomBuild.getId()));
    }

    @Test
    void testGetRoomDoesNotExist() {
        assertThrows(HttpClientErrorException.NotFound.class, () -> roomApi.getRoom(DEFAULT_ROOM_ID));
    }
}
