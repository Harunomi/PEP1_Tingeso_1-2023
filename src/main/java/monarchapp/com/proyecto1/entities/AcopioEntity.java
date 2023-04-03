package monarchapp.com.proyecto1.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "acopio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcopioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    Long id;
    private String fecha;
    private String turno;
    private String proveedor;
    private Integer KLS;

    // funcion para calcular el total de dias que tiene un mes
    public int maxDia(String mes){
        try{
            int number = Integer.parseInt(mes);
            if(number == 1 ||number == 3 || number == 5 || number == 7 || number == 8 || number == 10 || number == 12 ){
                return 31;
            } else if (number == 2) {
                return 28;
            }else{
                return 30;
            }

        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        return -1;



    }
}
