package monarchapp.com.proyecto1.services;

import monarchapp.com.proyecto1.entities.ProveedorEntity;
import monarchapp.com.proyecto1.repositories.ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void obtenerProveedoresTest(){
        ProveedorEntity p1 = new ProveedorEntity();
        proveedorRepository.save(p1);
        ArrayList<ProveedorEntity> lista = proveedorService.obtenerProveedores();
        assertNotNull(lista);
        proveedorRepository.delete(p1);
    }

    @Test
    public void guardarProveedorTest(){
        ProveedorEntity p1 = new ProveedorEntity();
        p1.setCodigo("test");
        proveedorService.guardarProveedor(p1);
        int resultado = 0;
        ArrayList<ProveedorEntity> lista = proveedorService.obtenerProveedores();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals("test")){
                resultado = 1;
            }
        }
        assertEquals(1,resultado);
        proveedorRepository.delete(p1);

    }



}