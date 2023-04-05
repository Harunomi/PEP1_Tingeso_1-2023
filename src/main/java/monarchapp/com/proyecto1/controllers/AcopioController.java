package monarchapp.com.proyecto1.controllers;

import monarchapp.com.proyecto1.entities.AcopioEntity;
import monarchapp.com.proyecto1.services.AcopioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class AcopioController {
    @Autowired
    private AcopioService acopioService;

    @GetMapping("/subirArchivoA")
    public String subirArchivo(){
        return "subirArchivoA";
    }

    @PostMapping("/cargarA")
    public String cargarA(@RequestParam("acopio")MultipartFile file, RedirectAttributes ms){
        acopioService.save(file);
        ms.addFlashAttribute("mensaje","Archivo cargado exitosamente!");
        return "redirect:/subirArchivoA";
    }

    @GetMapping("/vaciarAcopio")
    public String vaciarAcopio(RedirectAttributes ms){
        acopioService.borrarTodo();
        ms.addFlashAttribute("mensaje1","Base de datos vaciada.");
        return "redirect:/";
    }

    @GetMapping("/listaAcopio")
    public String listaAcopio(AcopioEntity acopio, Model model, RedirectAttributes ms){
        ArrayList<AcopioEntity> listaAcopio = acopioService.obtenerAcopio();
        if (listaAcopio.isEmpty()){
            ms.addFlashAttribute("mensaje2","La base de datos para acopio se encuentra vacia, suba un archivo primero");
            return "redirect:/";
        }else{
            model.addAttribute("acopio",listaAcopio);
            return "listaAcopio";
        }
    }
}
