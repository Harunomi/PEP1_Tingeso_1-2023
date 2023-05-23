package monarchapp.com.proyecto1.services;

import monarchapp.com.proyecto1.entities.ProveedorEntity;
import monarchapp.com.proyecto1.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public ArrayList<ProveedorEntity> obtenerProveedores(){
        return (ArrayList<ProveedorEntity>) proveedorRepository.findAll();
    }

    public void guardarProveedor(ProveedorEntity proveedor){
        proveedorRepository.save(proveedor);
    }

    public void borrarTodo(){
        proveedorRepository.deleteAll();
    }

}
