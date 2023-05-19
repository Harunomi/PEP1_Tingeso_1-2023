package monarchapp.com.proyecto1.controllers;

import monarchapp.com.proyecto1.entities.GrasaSolidoEntity;
import monarchapp.com.proyecto1.services.GrasaSolidoService;
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
public class GrasaSolidoController {
    @Autowired
    private GrasaSolidoService grasaSolidoService;

    private String defecto = "redirect:/";

    @GetMapping("/subirArchivoG")
    public String subirArchivo(){
        return "subirArchivoG";
    }

    @PostMapping("/cargarG")
    public String cargarG(@RequestParam("grasaSolido") MultipartFile file, RedirectAttributes ms){
        grasaSolidoService.save(file);
        ms.addFlashAttribute("mensaje5","Archivo cargado exitosamente");
        return "redirect:/subirArchivoG";
    }

    @GetMapping("/vaciarGrasaSolido")
    public String vaciarGrasaSolido(RedirectAttributes ms){
        grasaSolidoService.borrarTodo();
        ms.addFlashAttribute("mensaje3","Base de datos de Grasas y Solidos vaciada.");
        return defecto;
    }
    @GetMapping("/listaGrasaSolido")
    public String listaGrasaSolido(GrasaSolidoEntity grasaSolido, Model model,RedirectAttributes ms){
        ArrayList<GrasaSolidoEntity> listaGrasaSolido = grasaSolidoService.obtenergrasaSolidos();
        if (listaGrasaSolido.isEmpty()){
            ms.addFlashAttribute("mensaje4","La base de datos para Grasas y Solidos se encuentra vacia, suba un archivo primero");
            return defecto;
        }else{
            model.addAttribute("grasaSolido",listaGrasaSolido);
            return "listaGrasaSolido";
        }
    }




}
