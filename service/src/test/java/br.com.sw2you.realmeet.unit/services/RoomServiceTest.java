package br.com.sw2you.realmeet.unit.services;

import static br.com.sw2you.realmeet.unit.utils.TestDataCreator.roomBuilder;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import br.com.sw2you.realmeet.api.model.RoomDTO;
import br.com.sw2you.realmeet.domains.repositories.RoomRepository;
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
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    @Spy
    private RoomMapper mapper = Mappers.getMapper(RoomMapper.class);

    @Test
    void shouldReturnId() {
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(roomBuilder().build()));

        final RoomDTO response = roomService.findById(123L);

        assertNotNull(response);
    }
}