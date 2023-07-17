package ru.muravev.system.domain.event;

import org.axonframework.serialization.Revision;

@Revision("2.0")
public record SystemNameModifiedEvent(
        String id,
        String name,
        String description
) {
}
