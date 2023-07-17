package ru.muravev.system.projection.eventhandler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import ru.muravev.system.domain.event.SystemCreatedEvent;
import ru.muravev.system.domain.event.SystemNameModifiedEvent;
import ru.muravev.system.projection.repository.SystemViewRepository;
import ru.muravev.system.projection.view.SystemView;

@Service
@RequiredArgsConstructor
@Slf4j
public class SystemViewEventHandler {
    private final SystemViewRepository viewRepository;

    @EventHandler
    public void systemCreatedHandler(SystemCreatedEvent event) {
        SystemView systemView = new SystemView();
        systemView.setNew(true);
        systemView.setId(event.id());
        systemView.setName(event.name());
        systemView.setDescription(event.description());
        viewRepository.save(systemView);
    }

    @EventHandler
    @Transactional
    public void systemCreatedHandler(SystemNameModifiedEvent event) {
        SystemView view = viewRepository.findById(event.id())
                .orElseThrow();
        view.setName(event.name());
    }
}
