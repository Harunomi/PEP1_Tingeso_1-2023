package monarchapp.com.proyecto1.services;

import monarchapp.com.proyecto1.entities.ProveedorEntity;
import monarchapp.com.proyecto1.repositories.ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProveedorServiceTest {
    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    ProveedorService proveedorService;

    @Test
    public void borrarTodoTest(){
        ArrayList<ProveedorEntity> proveedores = new ArrayList<>();
        ProveedorEntity proveedor1 = new ProveedorEntity();
        proveedor1.setNombre("test");
        proveedor1.setCategoria("A");
        proveedor1.setCodigo("0001");
        ProveedorEntity proveedor2 = new ProveedorEntity();
        proveedor2.setNombre("test2");
        proveedor2.setCategoria("C");
        proveedor2.setCodigo("00021");
        proveedorRepository.save(proveedor2);
        proveedorService.borrarTodo();
        proveedores = proveedorRepository.findAll();
        assertEquals(true,proveedores.isEmpty());
    }



}