package br.com.sw2you.realmeet.unit.mappers;

import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_ID;
import static br.com.sw2you.realmeet.utils.TestDataCreator.roomBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.sw2you.realmeet.api.model.RoomDTO;
import br.com.sw2you.realmeet.helpers.mappers.RoomMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RoomMapperUnitTest {
    private final RoomMapper victim = Mappers.getMapper(RoomMapper.class);

    @Test
    void testFromEntityToDTO() {
        var room = roomBuilder().id(DEFAULT_ROOM_ID).build();

        final RoomDTO roomDTO = victim.fromEntityToDto(room);

        assertEquals(room.getId(), roomDTO.getId());
        assertEquals(room.getName(), roomDTO.getName());
        assertEquals(room.getSeats(), roomDTO.getSeats());
    }
}
