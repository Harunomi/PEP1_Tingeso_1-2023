package monarchapp.com.proyecto1.services;

import monarchapp.com.proyecto1.entities.AcopioEntity;
import monarchapp.com.proyecto1.entities.GrasaSolidoEntity;
import monarchapp.com.proyecto1.entities.PlanillaEntity;
import monarchapp.com.proyecto1.repositories.PlanillaRepository;
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
    void totalDiasQuincenaTest(){
        ArrayList<AcopioEntity> acopioList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AcopioEntity acopio = new AcopioEntity();
            acopio.setFecha("2023/03/02");
            acopioList.add(acopio);
        }
        assertEquals(3,planillaService.totalDiasQuincena(acopioList));
    }

    @Test
    void setQuincenaStringTest(){
        String fecha = "2023/01/16";
        assertEquals("2023/01/2",planillaService.setQuincenaString(fecha));

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
    void pagoPorCategoriaTest(){
        assertEquals(7000,planillaService.pagoPorCategoria(10,"A"));
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
    void pagoGrasaTest(){
        assertEquals(1200,planillaService.pagoGrasa(50,10));
    }

    @Test
    void pagoSTTest(){
        assertEquals(900,planillaService.pagoST(20,10));
    }

    @Test
    void descuentoVariacionKLSTest(){
        assertEquals(70,planillaService.descuentoVariacionKLS(-20,1000));
    }

    @Test
    void descuentoVariacionGrasaTest(){
        assertEquals(120,planillaService.descuentoVariacionGrasa(-20,1000));
    }

    @Test
    void descuentoVariacionST(){
        assertEquals(270,planillaService.descuentoVariacionST(-13,1000));
    }

    @Test
    void calcularMontoRetencionTest(){
        assertEquals(130000,planillaService.calcularMontoRetencion(1000000,"Si"));
    }

}
