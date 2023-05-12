package monarchapp.com.proyecto1.services;

import monarchapp.com.proyecto1.entities.AcopioEntity;
import monarchapp.com.proyecto1.entities.GrasaSolidoEntity;
import monarchapp.com.proyecto1.entities.PlanillaEntity;
import monarchapp.com.proyecto1.entities.ProveedorEntity;
import monarchapp.com.proyecto1.repositories.PlanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class PlanillaService {
    @Autowired
    private PlanillaRepository planillaRepository;

    @Autowired
    private AcopioService acopioService;

    @Autowired
    private GrasaSolidoService grasaSolidoService;

    @Autowired
    private ProveedorService proveedorService;


    public ArrayList<PlanillaEntity> obtenerPlanilla(){
        return (ArrayList<PlanillaEntity>) planillaRepository.findAll();
    }

    public void borrarTodo(){
        planillaRepository.deleteAll();
    }

    public void calcularQuincenas(){
        ArrayList<AcopioEntity> acopio = acopioService.obtenerAcopio();
        ArrayList<GrasaSolidoEntity> grasaSolido = grasaSolidoService.obtenergrasaSolidos();
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        ArrayList<AcopioEntity> quincenaActual;
        while(!(acopio.isEmpty())){
            // principal es, en primer lugar conseguir el periodo de la primera quincena, es decir,
            // el indice inicial y final de la quincena.
            // luego ir proveedor por proveedor calculando los datos de la quincena
            // cada vez ir haciendo mas chica la lista de acopio a medida que se van usando los datos.
            int ultimoIndice = acopio.get(0).totalDiasQuincena(acopio);
            quincenaActual = acopio.get(0).acopioPorQuincena(acopio);
            for (int i = 0; i < proveedores.size(); i++) {
                PlanillaEntity planillaActual = new PlanillaEntity();
                planillaActual.setCodigo(proveedores.get(i).getCodigo());
                planillaActual.setNombre(proveedores.get(i).getNombre());
                planillaActual.setTotalDias(totalDiasLeche(proveedores.get(i).getCodigo(),quincenaActual));
                float KLSactual = totalKLS(proveedores.get(i).getCodigo(),quincenaActual,ultimoIndice);
                planillaActual.setPromedioDiarioKLS(KLSactual/planillaActual.getTotalDias());
                float pagoKLS = pagoPorCategoria(KLSactual,proveedores.get(i).getCategoria());

            }

        }



    }
    public float totalKLS(String codigo, ArrayList<AcopioEntity> acopio,int ultimoIndice){
        float totalKLS = 0;
        for (int i = 0; i < ultimoIndice; i++) {
            if (acopio.get(i).getProveedor().equals(codigo)){
                totalKLS = totalKLS + acopio.get(i).getKls();
            }
        }
        return totalKLS;
    }
    public float pagoPorCategoria(float kls,String categoria){
        if (categoria.equals("A")){
            return kls * 700;
        } else if (categoria.equals("B")) {
            return kls * 550;
        } else if (categoria.equals("C")) {
            return kls * 400;
        }else{
            return kls * 250;
        }
    }
    public int totalDiasLeche(String codigo,ArrayList<AcopioEntity> acopio){
        int total = 0;
        for (int i = 0; i < acopio.size(); i++) {
            if (acopio.get(i).getProveedor().equals(codigo)){
                total++;
            }
        }
        return total;
    }


}
