package monarchapp.com.proyecto1.controllers;

import monarchapp.com.proyecto1.entities.PlanillaEntity;
import monarchapp.com.proyecto1.services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class PlanillaController {
    @Autowired
    PlanillaService planillaService;
    private String defecto = "redirect:/";

    @GetMapping("/listaPlanilla")
    public String listaPlanilla(PlanillaEntity planilla, Model model, RedirectAttributes ms){
        ArrayList<PlanillaEntity> listaPlanilla = planillaService.obtenerPlanilla();
        if (listaPlanilla.isEmpty()){
            ms.addFlashAttribute("mensaje5","La base de datos de planilla se encuentra vacia.");
            return defecto;
        }else{
            model.addAttribute("planilla",listaPlanilla);
            return "listaPlanilla";
        }
    }

    @GetMapping("/vaciarPlanilla")
    public String vaciarPlanilla(RedirectAttributes ms){
        planillaService.borrarTodo();
        ms.addFlashAttribute("mensaje6","La base de datos de planilla fue vaciada");
        return defecto;
    }

    @GetMapping("/calcularPlanilla")
    public String calcularPlanilla(RedirectAttributes ms){
        planillaService.calcularQuincenas();
        ms.addFlashAttribute("mensaje7","Los calculos fueron realizados");
        return defecto;
    }
}
