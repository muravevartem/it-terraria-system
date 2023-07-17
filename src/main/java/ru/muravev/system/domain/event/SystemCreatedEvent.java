package ru.muravev.system.domain.event;

import lombok.Data;
import org.axonframework.serialization.Revision;


public record SystemCreatedEvent(
        String id,
        String name,
        String description
) {
}
