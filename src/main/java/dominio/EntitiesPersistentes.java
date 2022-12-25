package dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class EntitiesPersistentes {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Integer id;
}
