package ru.muravev.system.configuration.axon.upcaster;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.SimpleSerializedType;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.springframework.stereotype.Component;
import ru.muravev.system.domain.event.SystemNameModifiedEvent;

@Component
public class SystemNameModifiedEvent1_to_2Upcaster extends SingleEventUpcaster {
    private static final SimpleSerializedType TARGET_TYPE = new SimpleSerializedType(SystemNameModifiedEvent.class.getTypeName(), "1.0");


    @Override
    protected boolean canUpcast(IntermediateEventRepresentation intermediateRepresentation) {
        return intermediateRepresentation.getType().equals(TARGET_TYPE);
    }

    @Override
    protected IntermediateEventRepresentation doUpcast(IntermediateEventRepresentation intermediateRepresentation) {
        return intermediateRepresentation.upcastPayload(
                new SimpleSerializedType(TARGET_TYPE.getName(), "2.0"),
                JsonNode.class,
                event -> {
                    ((ObjectNode)event).put("description", "upcast");
                    return event;
                }
        );
    }
}
