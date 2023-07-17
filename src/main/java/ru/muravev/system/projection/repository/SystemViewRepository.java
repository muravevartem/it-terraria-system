package ru.muravev.system.projection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.muravev.system.projection.view.SystemView;

public interface SystemViewRepository extends JpaRepository<SystemView, String> {
}
