package ru.muravev.system.projection.view;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.util.Objects;

@Entity
@Table(name = "system_view")
@Getter
@Setter
public class SystemView implements Persistable<String> {
    @Id
    private String id;
    private String name;
    private String description;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemView that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
