package ru.muravev.system.domain.command;

import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record SystemNameModifyCommand(
        @TargetAggregateIdentifier
        String id,
        String name
) {
}
