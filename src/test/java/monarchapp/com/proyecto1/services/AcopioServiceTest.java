package monarchapp.com.proyecto1.services;

import monarchapp.com.proyecto1.entities.AcopioEntity;
import monarchapp.com.proyecto1.repositories.AcopioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AcopioServiceTest {
    @Autowired
    AcopioService acopioService;

    @Autowired
    AcopioRepository acopioRepository;

    @Test
    public void obtenerAcopioTest(){
        AcopioEntity a1 = new AcopioEntity();
        acopioRepository.save(a1);
        ArrayList<AcopioEntity> lista = acopioService.obtenerAcopio();
        assertNotNull(lista);
        acopioRepository.delete(a1);
    }

    @Test
    public void borrarTodoTest(){
        AcopioEntity a1 = new AcopioEntity();
        acopioRepository.save(a1);
        AcopioEntity a2 = new AcopioEntity();
        acopioRepository.save(a2);
        acopioService.borrarTodo();
        ArrayList<AcopioEntity> lista = (ArrayList<AcopioEntity>) acopioRepository.findAll();
        assertEquals(0,lista.size());


    }
}
