package monarchapp.com.proyecto1.services;

import monarchapp.com.proyecto1.entities.AcopioEntity;
import monarchapp.com.proyecto1.entities.GrasaSolidoEntity;
import monarchapp.com.proyecto1.entities.PlanillaEntity;
import monarchapp.com.proyecto1.entities.ProveedorEntity;
import monarchapp.com.proyecto1.repositories.AcopioRepository;
import monarchapp.com.proyecto1.repositories.GrasaSolidoRepository;
import monarchapp.com.proyecto1.repositories.PlanillaRepository;
import monarchapp.com.proyecto1.repositories.ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlanillaServiceTest {
    @Autowired
    PlanillaService planillaService;

    @Autowired
    PlanillaRepository planillaRepository;

    @Autowired
    AcopioRepository acopioRepository;

    @Autowired
    GrasaSolidoRepository grasaSolidoRepository;

    @Autowired
    ProveedorRepository proveedorRepository;



    @Test
    void borrarTodoTest(){
        PlanillaEntity planilla = new PlanillaEntity();
        planillaRepository.save(planilla);
        planillaService.borrarTodo();
        ArrayList<PlanillaEntity> lista = (ArrayList<PlanillaEntity>) planillaRepository.findAll();
        assertEquals(0,lista.size());
        planillaRepository.delete(planilla);
    }

    @Test
    void obtenerPlanillaTest(){
        PlanillaEntity planilla = new PlanillaEntity();
        planillaRepository.save(planilla);
        ArrayList<PlanillaEntity> lista = planillaService.obtenerPlanilla();
        assertNotNull(lista);
        planillaRepository.delete(planilla);
    }

    @Test
    void getDiaTest(){
        AcopioEntity acopio = new AcopioEntity();
        acopio.setFecha("2023/03/02");
        assertEquals(2,planillaService.getDia(acopio));
    }

    @Test
    void totalDiasQuincenaTestA(){
        ArrayList<AcopioEntity> acopioList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AcopioEntity acopio = new AcopioEntity();
            acopio.setFecha("2023/03/02");
            acopioList.add(acopio);
        }
        assertEquals(3,planillaService.totalDiasQuincena(acopioList));
    }

    @Test
    void totalDiasQuincenaTestB(){
        ArrayList<AcopioEntity> acopioList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AcopioEntity acopio = new AcopioEntity();
            acopio.setFecha("2023/03/16");
            acopioList.add(acopio);
        }
        assertEquals(3,planillaService.totalDiasQuincena(acopioList));
    }

    @Test
    void totalDiasQuincenaTestC(){
        ArrayList<AcopioEntity> acopioList = new ArrayList<>();
        assertEquals(0,planillaService.totalDiasQuincena(acopioList));
    }

    @Test
    void acopioPorQuincenaTest(){
        ArrayList<AcopioEntity> list = new ArrayList<>();
        AcopioEntity acopio = new AcopioEntity();
        for (int i = 0; i < 3; i++) {
            list.add(acopio);
        }
        ArrayList<AcopioEntity> listNueva = planillaService.acopioPorQuincena(list,1);
        assertEquals(1,listNueva.size());

    }

    @Test
    void setQuincenaStringTestA(){
        String fecha = "2023/01/16";
        assertEquals("2023/01/2",planillaService.setQuincenaString(fecha));

    }

    @Test
    void setQuincenaStringTesB(){
        String fecha = "2023/01/01";
        assertEquals("2023/01/1",planillaService.setQuincenaString(fecha));

    }

    @Test
    void totalKLSTest(){
        ArrayList<AcopioEntity> acopioList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AcopioEntity acopio = new AcopioEntity();
            acopio.setKls(1);
            acopio.setProveedor("01");
            acopioList.add(acopio);
        }
        assertEquals(5,planillaService.totalKLS("01",acopioList,5));

    }

    @Test
    void pagoPorCategoriaTestA(){
        assertEquals(7000,planillaService.pagoPorCategoria(10,"A"));
    }

    @Test
    void pagoPorCategoriaTestB(){
        assertEquals(5500,planillaService.pagoPorCategoria(10,"B"));
    }

    @Test
    void pagoPorCategoriaTestC(){
        assertEquals(4000,planillaService.pagoPorCategoria(10,"C"));
    }

    @Test
    void pagoPorCategoriaTestD(){
        assertEquals(2500,planillaService.pagoPorCategoria(10,"D"));
    }
    @Test
    void pagoPorCategoriaTestX(){
        assertEquals(0,planillaService.pagoPorCategoria(10,"asdasd"));
    }

    @Test
    void totalDiasLecheTest(){
        ArrayList<AcopioEntity> acopioList = new ArrayList<>();
        //assertEquals(0,planillaService.totalDiasLeche("01",acopioList));
        for (int i = 0; i < 5; i++) {
            AcopioEntity nuevo = new AcopioEntity();
            nuevo.setProveedor("01");
            acopioList.add(nuevo);
        }
        assertEquals(5,planillaService.totalDiasLeche("01",acopioList));
    }

    @Test
    void obtenerGrasaTest(){
        ArrayList<GrasaSolidoEntity> grasaList = new ArrayList<>();
        GrasaSolidoEntity grasa = new GrasaSolidoEntity();
        grasa.setGrasa(35);
        grasa.setProveedor("001");
        grasaList.add(grasa);
        assertEquals(35,planillaService.obtenerGrasa(grasaList,"001"));
    }

    @Test
    void obtenerSTTest(){
        ArrayList<GrasaSolidoEntity> grasaList = new ArrayList<>();
        GrasaSolidoEntity grasa = new GrasaSolidoEntity();
        grasa.setSolido(444);
        grasa.setProveedor("001");
        grasaList.add(grasa);
        assertEquals(444,planillaService.obtenerST(grasaList,"001"));
    }

    @Test
    void pagoGrasaTestA(){
        assertEquals(300,planillaService.pagoGrasa(0,10));
    }

    @Test
    void pagoGrasaTestB(){
        assertEquals(800,planillaService.pagoGrasa(21,10));
    }

    @Test
    void pagoGrasaTestC(){
        assertEquals(1200,planillaService.pagoGrasa(46,10));
    }

    @Test
    void pagoGrasaTestD(){
        assertEquals(0,planillaService.pagoGrasa(123123,10));
    }

    @Test
    void pagoSTTestA(){
        assertEquals(-1300,planillaService.pagoST(0,10));
    }

    @Test
    void pagoSTTestB(){
        assertEquals(-900,planillaService.pagoST(8,10));
    }

    @Test
    void pagoSTTestC(){
        assertEquals(900,planillaService.pagoST(19,10));
    }

    @Test
    void pagoSTTestD(){
        assertEquals(1500,planillaService.pagoST(36,10));
    }

    @Test
    void pagoSTTestX(){
        assertEquals(0,planillaService.pagoST(12321,10));
    }

    @Test
    void descuentoVariacionKLSTestA(){
        assertEquals(0,planillaService.descuentoVariacionKLS(-1,1000));
    }

    @Test
    void descuentoVariacionKLSTestB(){
        assertEquals(70,planillaService.descuentoVariacionKLS(-9,1000));
    }

    @Test
    void descuentoVariacionKLSTestC(){
        assertEquals(150,planillaService.descuentoVariacionKLS(-26,1000));
    }
    @Test
    void descuentoVariacionKLSTestD(){
        assertEquals(300,planillaService.descuentoVariacionKLS(-50,1000));
    }

    @Test
    void descuentoVariacionKLSTestX(){
        assertEquals(0,planillaService.descuentoVariacionKLS(1,1000));
    }

    @Test
    void descuentoVariacionGrasaTestA(){
        assertEquals(0,planillaService.descuentoVariacionGrasa(-1,1000));
    }

    @Test
    void descuentoVariacionGrasaTestB(){
        assertEquals(120,planillaService.descuentoVariacionGrasa(-16,1000));
    }

    @Test
    void descuentoVariacionGrasaTestC(){
        assertEquals(200,planillaService.descuentoVariacionGrasa(-26,1000));
    }
    @Test
    void descuentoVariacionGrasaTestD(){
        assertEquals(300,planillaService.descuentoVariacionGrasa(-42,1000));
    }

    @Test
    void descuentoVariacionGrasaTestX(){
        assertEquals(0,planillaService.descuentoVariacionGrasa(1,1000));
    }

    @Test
    void descuentoVariacionSTA(){
        assertEquals(0,planillaService.descuentoVariacionST(-4,1000));
    }

    @Test
    void descuentoVariacionSTB(){
        assertEquals(180,planillaService.descuentoVariacionST(-7,1000));
    }

    @Test
    void descuentoVariacionSTC(){
        assertEquals(270,planillaService.descuentoVariacionST(-13,1000));
    }
    @Test
    void descuentoVariacionSTD(){
        assertEquals(450,planillaService.descuentoVariacionST(-38,1000));
    }

    @Test
    void descuentoVariacionSTX(){
        assertEquals(0,planillaService.descuentoVariacionST(11,1000));
    }

    @Test
    void calcularMontoRetencionTestA(){
        assertEquals(130000,planillaService.calcularMontoRetencion(1000000,"Si"));
    }

    @Test
    void calcularMontoRetencionTestB(){
        assertEquals(0,planillaService.calcularMontoRetencion(1000000,"No"));
    }

    @Test
    void borrarQuincenaTest(){
        ArrayList<AcopioEntity> acopio = new ArrayList<>();
        AcopioEntity a = new AcopioEntity();
        for (int i = 0; i < 3; i++) {
            acopio.add(a);
        }
        ArrayList<AcopioEntity> resultado = planillaService.borrarQuincena(acopio,0);
        assertEquals(2,resultado.size());
    }

    @Test
    void bonificacionFrecuenciaTestA(){
        ArrayList<AcopioEntity> lista = new ArrayList<>();
        AcopioEntity acopio = new AcopioEntity();
        acopio.setTurno("M");
        acopio.setProveedor("01001");
        AcopioEntity acopio2 = new AcopioEntity();
        acopio2.setTurno("T");
        acopio2.setProveedor("01001");
        for (int i = 0; i < 6; i++) {
            lista.add(acopio);
            lista.add(acopio2);
        }

        assertEquals(200,planillaService.bonificacionFrecuencia(lista,"01001",1000));
    }

    @Test
    void bonificacionFrecuenciaTestB(){
        ArrayList<AcopioEntity> lista = new ArrayList<>();
        AcopioEntity acopio = new AcopioEntity();
        acopio.setTurno("M");
        acopio.setProveedor("01001");
        for (int i = 0; i < 12; i++) {
            lista.add(acopio);
        }

        assertEquals(120,planillaService.bonificacionFrecuencia(lista,"01001",1000));
    }

    @Test
    void bonificacionFrecuenciaTestC(){
        ArrayList<AcopioEntity> lista = new ArrayList<>();
        AcopioEntity acopio = new AcopioEntity();
        acopio.setTurno("T");
        acopio.setProveedor("01001");
        for (int i = 0; i < 12; i++) {
            lista.add(acopio);
        }

        assertEquals(80,planillaService.bonificacionFrecuencia(lista,"01001",1000));
    }

    @Test
    void bonificacionFrecuenciaTestD(){
        ArrayList<AcopioEntity> lista = new ArrayList<>();
        AcopioEntity acopio = new AcopioEntity();
        acopio.setTurno("T");
        acopio.setProveedor("01001");
        for (int i = 0; i < 4; i++) {
            lista.add(acopio);
        }

        assertEquals(0,planillaService.bonificacionFrecuencia(lista,"01001",1000));
    }

    @Test
    void descuentoVariacionesTestA(){
        ArrayList<PlanillaEntity> planillas = new ArrayList<>();
        PlanillaEntity planillaAnterior = new PlanillaEntity();
        planillaAnterior.setTotalKLS(10000);
        planillaAnterior.setPorcentajeGrasa(40);
        planillaAnterior.setPorcentajeSolidos(34);
        planillaAnterior.setCodigo("01001");
        planillas.add(planillaAnterior);
        PlanillaEntity planillaActual = new PlanillaEntity();
        planillaActual.setCodigo("01001");
        planillaActual.setTotalKLS(3000);
        planillaActual.setPorcentajeGrasa(40);
        planillaActual.setPorcentajeSolidos(20);
        planillaActual = planillaService.descuentoVariaciones(planillaActual,planillas);
        assertEquals(-70,planillaActual.getVariacionLeche());
    }

    @Test
    void descuentoVariacionesTestB(){
        ArrayList<PlanillaEntity> planillas = new ArrayList<>();
        PlanillaEntity planillaAnterior = new PlanillaEntity();
        planillaAnterior.setTotalKLS(10000);
        planillaAnterior.setPorcentajeGrasa(40);
        planillaAnterior.setPorcentajeSolidos(34);
        planillaAnterior.setCodigo("01002");
        planillas.add(planillaAnterior);
        PlanillaEntity planillaActual = new PlanillaEntity();
        planillaActual.setCodigo("01001");
        planillaActual.setTotalKLS(3000);
        planillaActual.setPorcentajeGrasa(40);
        planillaActual.setPorcentajeSolidos(20);
        planillaActual = planillaService.descuentoVariaciones(planillaActual,planillas);
        assertEquals(0,planillaActual.getVariacionLeche());
    }

    @Test
    void calcularQuincenasTest(){
        ProveedorEntity p1 = new ProveedorEntity();
        p1.setCodigo("01001");
        p1.setCategoria("A");
        p1.setNombre("Proveedor1");
        p1.setRetencion("Si");
        proveedorRepository.save(p1);
        GrasaSolidoEntity g1 = new GrasaSolidoEntity();
        g1.setGrasa(60);
        g1.setSolido(44);
        g1.setProveedor("01001");
        grasaSolidoRepository.save(g1);
        AcopioEntity a1 = new AcopioEntity();
        a1.setProveedor("01001");
        a1.setFecha("2023/03/01");
        a1.setKls(140);
        a1.setTurno("M");
        for (int i = 0; i < 11; i++) {
            acopioRepository.save(a1);
        }
        planillaService.calcularQuincenas();
        ArrayList<PlanillaEntity> resultado = (ArrayList<PlanillaEntity>) planillaRepository.findAll();
        assertEquals(1,resultado.size());
        proveedorRepository.delete(p1);
        grasaSolidoRepository.delete(g1);
        acopioRepository.deleteAll();
        planillaRepository.deleteAll();

    }


}
