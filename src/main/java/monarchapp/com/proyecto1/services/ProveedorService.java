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

    public ProveedorEntity buscarPorCodigo(String codigo){
        ArrayList<ProveedorEntity> proveedores = (ArrayList<ProveedorEntity>) proveedorRepository.findAll();
        for (int i = 0; i < proveedores.size(); i++){
            if (proveedores.get(i).getCodigo().equals(codigo)){
                return proveedores.get(i);
            }
        }
        ProveedorEntity o = null;
        return o;
    }

    public ProveedorEntity guardarProveedor(ProveedorEntity proveedor){
        return proveedorRepository.save(proveedor);
    }

    public void borrarTodo(){
        proveedorRepository.deleteAll();
    }
}
