package monarchapp.com.proyecto1.services;

import monarchapp.com.proyecto1.entities.GrasaSolidoEntity;
import monarchapp.com.proyecto1.repositories.GrasaSolidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class GrasaSolidoServiceTest {
    @Autowired
    GrasaSolidoService grasaSolidoService;

    @Autowired
    GrasaSolidoRepository grasaSolidoRepository;

    @Test
    public void borrarTodoTest(){
        GrasaSolidoEntity g1 = new GrasaSolidoEntity();
        grasaSolidoRepository.save(g1);
        grasaSolidoService.borrarTodo();
        ArrayList<GrasaSolidoEntity> lista = (ArrayList<GrasaSolidoEntity>) grasaSolidoRepository.findAll();
        assertEquals(0,lista.size());
    }

    @Test
    public void obtenerGrasaSolidoTest(){
        GrasaSolidoEntity g1 = new GrasaSolidoEntity();
        grasaSolidoRepository.save(g1);
        ArrayList<GrasaSolidoEntity> lista = grasaSolidoService.obtenergrasaSolidos();
        assertNotNull(lista);
        grasaSolidoRepository.delete(g1);
    }


}
