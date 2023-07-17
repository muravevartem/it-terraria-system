package ru.muravev.system.domain.aggregate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import ru.muravev.system.domain.command.SystemCreateCommand;
import ru.muravev.system.domain.command.SystemNameModifyCommand;
import ru.muravev.system.domain.event.SystemCreatedEvent;
import ru.muravev.system.domain.event.SystemNameModifiedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Getter
@Slf4j
public class System {
    @AggregateIdentifier
    private String id;
    private String name;
    private String description;

    public System() {
    }

    @CommandHandler
    public System(SystemCreateCommand command) {
        apply(new SystemCreatedEvent(command.id(), command.name(), command.description()));
    }

    @CommandHandler
    public void handle(SystemNameModifyCommand command) {
        apply(new SystemNameModifiedEvent(command.id(), command.name(), null));
    }

    @EventSourcingHandler
    public void on(SystemCreatedEvent event) {
        this.id = event.id();
        this.name = event.name();
        this.description = event.name();
    }

    @EventSourcingHandler
    public void on(SystemNameModifiedEvent event) {
        this.name = event.name();
    }
}
