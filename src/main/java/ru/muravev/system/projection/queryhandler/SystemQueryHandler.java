package ru.muravev.system.projection.queryhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import ru.muravev.system.domain.query.ListSystemQuery;
import ru.muravev.system.projection.repository.SystemViewRepository;
import ru.muravev.system.projection.view.SystemView;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SystemQueryHandler {
    private final SystemViewRepository viewRepository;

    @QueryHandler
    public List<SystemView> handle(ListSystemQuery query) {
        return viewRepository.findAll();
    }
}
