package br.com.sw2you.realmeet.services;

import static java.util.Objects.requireNonNull;

import br.com.sw2you.realmeet.api.model.RoomDTO;
import br.com.sw2you.realmeet.domains.entities.Room;
import br.com.sw2you.realmeet.domains.repositories.RoomRepository;
import br.com.sw2you.realmeet.exceptions.RoomNotFoundException;
import br.com.sw2you.realmeet.helpers.mappers.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

    public RoomDTO findById(Long id) {
        requireNonNull(id);

        final Room room = roomRepository
            .findByIdAndActive(id, true)
            .orElseThrow(() -> new RoomNotFoundException(String.format("Room %s not found", id)));

        RoomDTO roomDTO = roomMapper.fromEntityToDto(room);

        logger.info("Sucesso em obter os dados do Room Id: {} - retornando o objeto: {}", id, roomDTO);

        return roomDTO;
    }
}
