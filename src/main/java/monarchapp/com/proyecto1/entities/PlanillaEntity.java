package monarchapp.com.proyecto1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Table(name = "planilla")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanillaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String quincena;
    private String codigo;
    private String nombre;
    private Integer totalDias; // total de dias que envio leche
    private float totalKLS; // total de KLS de leche
    private float promedioDiarioKLS;
    private float variacionLeche;
    private float porcentajeGrasa;
    private float variacionGrasa;
    private float porcentajeSolidos;
    private float variacionSolidos;
    private float pagoLeche;
    private float pagoGrasa;
    private float pagoSolidos;
    private double bonificacionFrecuencia;
    private double dctoVariacionLeche;
    private double dctoVariacionGrasa;
    private double dctoVariacionST;
    private double pagoTotal;
    private double pagoAcopioLeche;
    private double montoRetencion;
    private double montoFinal;
}
