package br.com.sw2you.realmeet.controllers;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import br.com.sw2you.realmeet.api.facade.RoomsApi;
import br.com.sw2you.realmeet.api.model.CreateRoomDTO;
import br.com.sw2you.realmeet.api.model.RoomDTO;
import br.com.sw2you.realmeet.services.RoomService;
import br.com.sw2you.realmeet.utils.ResponseEntityUtils;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController implements RoomsApi {
    private final Executor controllerExecutor;
    private final RoomService roomService;

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Override
    public CompletableFuture<ResponseEntity<RoomDTO>> getRoom(final Long roomId) {
        logger.info("Obtendo dados do Room a partir do: {}", roomId);

        return supplyAsync(() -> roomService.findById(roomId), controllerExecutor).thenApply(ResponseEntityUtils::ok);
    }

    @Override
    public CompletableFuture<ResponseEntity<RoomDTO>> createRoom(final CreateRoomDTO createRoomDTO) {
        logger.info("Criando os dados do Room: {}", createRoomDTO.toString());

        return supplyAsync(() -> roomService.createRoom(createRoomDTO), controllerExecutor)
            .thenApply(ResponseEntityUtils::created);
    }
}
