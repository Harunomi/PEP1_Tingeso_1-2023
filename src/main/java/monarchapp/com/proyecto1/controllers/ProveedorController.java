package monarchapp.com.proyecto1.controllers;


import monarchapp.com.proyecto1.entities.ProveedorEntity;
import monarchapp.com.proyecto1.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;



    @PostMapping("/crearProveedor")
    public String crearProveedor(ProveedorEntity proveedor,Model model) {
        proveedorService.guardarProveedor(proveedor);
        model.addAttribute("proveedor", proveedor);
        return "index";
    }

}
