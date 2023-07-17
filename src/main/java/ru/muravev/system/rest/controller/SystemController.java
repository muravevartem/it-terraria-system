package ru.muravev.system.rest.controller;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;
import ru.muravev.system.domain.command.SystemCreateCommand;
import ru.muravev.system.domain.command.SystemNameModifyCommand;
import ru.muravev.system.domain.query.ListSystemQuery;
import ru.muravev.system.projection.view.SystemView;
import ru.muravev.system.rest.model.FieldModificationDto;
import ru.muravev.system.rest.model.SystemCreationDto;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/systems")
@RequiredArgsConstructor
public class SystemController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    public void create(@RequestBody SystemCreationDto systemCreationDto) {
        int id = new Random().nextInt(100);
        SystemCreateCommand command = SystemCreateCommand.builder()
                .name(systemCreationDto.name())
                .id("sys" + id)
                .description(systemCreationDto.description())
                .build();
        commandGateway
                .send(command)
                .join();
    }

    @PutMapping("/{id}/name")
    public void modifyName(@PathVariable String id, @RequestBody FieldModificationDto<String> name) {
        SystemNameModifyCommand command = SystemNameModifyCommand.builder()
                .id(id)
                .name(name.value())
                .build();
        commandGateway
                .send(command)
                .join();
    }

    @GetMapping
    public List<SystemView> getAll() {
        return queryGateway
                .query(new ListSystemQuery(), ResponseTypes.multipleInstancesOf(SystemView.class))
                .join();
    }
}
