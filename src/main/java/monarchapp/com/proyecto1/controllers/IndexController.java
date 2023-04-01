package monarchapp.com.proyecto1.controllers;


import monarchapp.com.proyecto1.entities.ProveedorEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    @GetMapping("/")
    public String main(){
        return "index";
    }

    @GetMapping("/proveedor")
    public String proveedor(ProveedorEntity proveedor, Model model){
        model.addAttribute("proveedor",proveedor);
        return "crearProveedor";
    }

}
