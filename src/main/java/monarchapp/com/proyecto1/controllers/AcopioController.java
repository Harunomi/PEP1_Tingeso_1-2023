package monarchapp.com.proyecto1.controllers;

import monarchapp.com.proyecto1.services.AcopioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AcopioController {
    @Autowired
    private AcopioService acopio;

    @GetMapping("/subirArchivo")
    public String subirArchivo(){
        return "/subirArchivo";
    }

    @PostMapping("/cargar")
    public String cargar(@RequestParam("acopio")MultipartFile file, RedirectAttributes ms){
        acopio.save(file);
        ms.addFlashAttribute("mensaje","Archivo cargado exitosamente!");
        return "redirect:/subirArchivo";
    }

    @GetMapping("/vaciarAcopio")
    public String vaciarAcopio(RedirectAttributes ms){
        acopio.borrarTodo();
        ms.addFlashAttribute("mensaje1","Base de datos vaciada.");
        return "redirect:/";
    }
}
