package Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "Tb_Test")

public class EntityTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

}
