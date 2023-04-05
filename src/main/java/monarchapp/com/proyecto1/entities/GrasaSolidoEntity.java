package monarchapp.com.proyecto1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "grasa_solido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrasaSolidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String proveedor;
    private float grasa;
    private float solido;
}
