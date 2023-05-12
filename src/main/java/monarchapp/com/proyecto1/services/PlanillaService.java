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
public class    PlanillaService {
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
        ArrayList<PlanillaEntity> planilla = new ArrayList<PlanillaEntity>();
        while(!(acopio.isEmpty())){
            /*
            la logica del programa corresponde a: en primer lugar revisamos el indice del ultimo
            elemento correspondiente a la primera quincena a calcular
            luego creamos un ArrayList correspondiente al primer periodo de la quincena
            recorremos a los proveedores de manera de ir calculando los datos correspondiente a dicha quincena
            una vez creada cada plantilla de proveedores, se actualiza la lista de acopio eliminando la quincena
            ya calculada
             */
            int ultimoIndice = acopio.get(0).totalDiasQuincena(acopio);
            quincenaActual = acopio.get(0).acopioPorQuincena(acopio);
            for (int i = 0; i < proveedores.size(); i++) {
                PlanillaEntity planillaActual = new PlanillaEntity();
                planillaActual.setCodigo(proveedores.get(i).getCodigo());
                planillaActual.setNombre(proveedores.get(i).getNombre());
                planillaActual.setTotalDias(totalDiasLeche(proveedores.get(i).getCodigo(),quincenaActual));
                float KLSactual = totalKLS(proveedores.get(i).getCodigo(),quincenaActual,ultimoIndice);
                planillaActual.setPromedioDiarioKLS(KLSactual/planillaActual.getTotalDias());
                float grasaActual = obtenerGrasa(grasaSolido,proveedores.get(i).getCodigo());
                float solidoActual = obtenerST(grasaSolido,proveedores.get(i).getCodigo());
                planillaActual.setPorcentajeGrasa(grasaActual);
                planillaActual.setPorcentajeSolidos(solidoActual);
                planillaActual.setPagoGrasa(pagoGrasa(grasaActual,KLSactual));
                planillaActual.setPagoSolidos(pagoST(solidoActual,KLSactual));
                planillaActual.setPagoLeche(pagoPorCategoria(KLSactual,proveedores.get(i).getCategoria()));
                planillaActual.setBonificacionFrecuencia(bonificacionFrecuencia(acopio,proveedores.get(i).getCodigo(),planillaActual.getPagoLeche()));


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
        }else if (categoria.equals("D")){
            return kls * 250;
        }else{
            return 0;
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
    public float obtenerGrasa(ArrayList<GrasaSolidoEntity> grasaSolido,String codigo){
        float salida = 0;
        for (int i = 0; i < grasaSolido.size(); i++) {
            if (grasaSolido.get(i).getProveedor().equals(codigo)){
                salida = grasaSolido.get(i).getGrasa();
            }
        }
        return salida;
    }
    public float obtenerST(ArrayList<GrasaSolidoEntity> grasaSolido,String codigo){
        float salida = 0;
        for (int i = 0; i < grasaSolido.size(); i++) {
            if (grasaSolido.get(i).getProveedor().equals(codigo)){
                salida = grasaSolido.get(i).getSolido();
            }
        }
        return salida;
    }
    public float pagoGrasa(float grasa,float KLS){
        if (grasa >= 0 && grasa < 21){
            return 30 * KLS;
        } else if (grasa >= 21 && grasa < 46) {
            return 80 * KLS;
        } else if (grasa >=46 && grasa < 101){
            return 120 * KLS;
        }else{
            return 0;
        }
    }

    public float pagoST(float solidosT, float KLS){
        if(solidosT>=0 && solidosT <8){
            return KLS * -130;
        } else if (solidosT >= 8 && solidosT < 19) {
            return KLS * -90;
        } else if (solidosT >= 19 && solidosT < 36) {
            return KLS * 90;
        } else if (solidosT >= 36 && solidosT < 101) {
            return KLS * 150;
        }else{
            return 0;
        }
    }

    public double bonificacionFrecuencia(ArrayList<AcopioEntity>acopio,String codigo,float pagoKLS){
        int tarde = 0;
        int manyana = 0;
        for (int i = 0; i < acopio.size(); i++) {
            if (acopio.get(i).getProveedor().equals(codigo)){
                if (acopio.get(i).getTurno().equals("M")){
                    manyana++;
                }
                if (acopio.get(i).getTurno().equals("T")){
                    tarde++;
                }
            }
        }
        if ((tarde+manyana)>=10 && tarde>0 && manyana>0){ // tarde y manyana
            return (pagoKLS*0.20);
        }else if ((tarde+manyana)>=10 && tarde == 0){ // solo manyana
            return (pagoKLS*0.12);
        } else if ((tarde+manyana)>=10 && manyana == 0) { // solo tarde
            return (pagoKLS*0.08);
        }else{
            return 0;
        }
    }

    public PlanillaEntity descuentoVariaciones(PlanillaEntity planillaActual, ArrayList<PlanillaEntity> planillas){
        PlanillaEntity salida = new PlanillaEntity();
        for (int i = planillas.size(); i > 0 ; i = i - 1) {
            if (planillas.get(i).getCodigo().equals(planillaActual.getCodigo())){
                
            }
        }
    }
}
