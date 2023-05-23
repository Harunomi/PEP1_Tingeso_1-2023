package monarchapp.com.proyecto1.controllers;


import monarchapp.com.proyecto1.entities.ProveedorEntity;
import monarchapp.com.proyecto1.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;
    @PostMapping("/crearProveedor")
    public String crearProveedor(@ModelAttribute("proveedor") ProveedorEntity proveedor, Model model, BindingResult resultado) {
        if (resultado.hasErrors()){
            return "crearProveedor";
        }
        proveedorService.guardarProveedor(proveedor);
        model.addAttribute("proveedor", proveedor);
        return "index";
    }
    @GetMapping("/listaProveedores")
    public String listaProveedores(ProveedorEntity proveedor, Model model){
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        model.addAttribute("proveedor",proveedores);
        return "listaProveedores";
    }

    @GetMapping("/borrarProveedor")
    public String vaciarProveedores(){
        proveedorService.borrarTodo();
        return "redirect:/";
    }





}
