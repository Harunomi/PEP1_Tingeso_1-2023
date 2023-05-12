package monarchapp.com.proyecto1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "acopio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcopioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String fecha;
    private String turno;
    private String proveedor;
    private float kls;

    // funcion para calcular el total de dias que tiene un mes
    public int maxDia(int number){
        try{
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
    public int getDia(AcopioEntity acopio){
        String[] partes = acopio.getFecha().split(";");
        return Integer.parseInt(partes[2]);
    }
    public int getMes(AcopioEntity acopio){
        String[] partes = acopio.getFecha().split(";");
        return Integer.parseInt(partes[1]);
    }

    public ArrayList<AcopioEntity> borrarPorCodigo(String codigo,ArrayList<AcopioEntity> acopioEntrada,int ultimoIndice){
        ArrayList<AcopioEntity> salida = new ArrayList<AcopioEntity>();
        for (int i = 0; i < acopioEntrada.size(); i++) {
            if (!(acopioEntrada.get(i).getProveedor().equals(codigo))){
                salida.add(acopioEntrada.get(i));
            }
        }
        return salida;
    }

    public int periodoQuincena(AcopioEntity acopio){
        int dia = this.getDia(acopio);
        if (dia <= 15){
            return 1;
        }else{
            return 2;
        }
    }

    public int totalDiasQuincena(ArrayList<AcopioEntity>acopio){
        int diaInicial = this.getDia(acopio.get(0));
        int diaActual;
        int totalDiasQuincena = 0;
        if (diaInicial <=15){
            diaActual = diaInicial;
            while (diaActual <=15){
                totalDiasQuincena++;
                diaActual = this.getDia(acopio.get(totalDiasQuincena));
            }
        }else{
            diaActual = diaInicial;
            while (diaActual != 1){
                totalDiasQuincena++;
                diaActual = this.getDia(acopio.get(totalDiasQuincena));
            }
        }
        return totalDiasQuincena;

    }

    public ArrayList<AcopioEntity> acopioPorQuincena(ArrayList<AcopioEntity> entrada){
        int totalDiasQuincena = this.totalDiasQuincena(entrada);
        ArrayList<AcopioEntity> salida = new ArrayList<>();
        for (int i = 0; i < totalDiasQuincena; i++) {
            salida.add(entrada.get(i));
        }
        return salida;
    }
    public ArrayList<AcopioEntity> borrarAcopioPorQuincena(ArrayList<AcopioEntity> entrada,int indice){
        ArrayList<AcopioEntity> salida = new ArrayList<>();
        for (int i = indice; i <entrada.size() ; i++) {
            salida.add(entrada.get(i));
        }
        return salida;
    }

}
