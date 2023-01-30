package br.com.sw2you.realmeet.unit.services;

import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_ID;
import static br.com.sw2you.realmeet.utils.TestDataCreator.roomBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.sw2you.realmeet.api.model.RoomDTO;
import br.com.sw2you.realmeet.domains.repositories.RoomRepository;
import br.com.sw2you.realmeet.exceptions.RoomNotFoundException;
import br.com.sw2you.realmeet.helpers.mappers.RoomMapper;
import br.com.sw2you.realmeet.services.RoomService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RoomServiceTest {
    @InjectMocks
    private RoomService victim;

    @Mock
    private RoomRepository roomRepository;

    @Spy
    private RoomMapper mapper = Mappers.getMapper(RoomMapper.class);

    @Test
    void shouldReturnId() {
        when(roomRepository.findByIdAndActive(DEFAULT_ROOM_ID, true))
            .thenReturn(Optional.of(roomBuilder().id(DEFAULT_ROOM_ID).build()));

        final RoomDTO response = victim.findById(DEFAULT_ROOM_ID);

        assertNotNull(response);
        assertEquals(DEFAULT_ROOM_ID, response.getId());
        assertEquals(roomBuilder().build().getName(), response.getName());
    }

    @Test
    void testRoomNotFoundException() {
        when(roomRepository.findByIdAndActive(DEFAULT_ROOM_ID, true)).thenReturn(Optional.empty());

        assertThrows(RoomNotFoundException.class, () -> victim.findById(DEFAULT_ROOM_ID));
    }
}
