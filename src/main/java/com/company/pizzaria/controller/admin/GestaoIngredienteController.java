package com.company.pizzaria.controller.admin;

import com.company.pizzaria.model.entity.Ingrediente;
import com.company.pizzaria.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/gestao-ingredientes")
public class GestaoIngredienteController {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @GetMapping
    public String listarIngredientes(Model model) {
        List<Ingrediente> ingredientes = ingredienteRepository.findAll();
        model.addAttribute("ingredientes", ingredientes);
        model.addAttribute("ingrediente", new Ingrediente()); 
        return "admin/gestao-ingredientes";
    }

    @PostMapping("/salvar")
    public String salvarIngrediente(@ModelAttribute Ingrediente ingrediente) {
        ingredienteRepository.save(ingrediente);
        return "redirect:/admin/gestao-ingredientes";
    }

    @PostMapping("/excluir")
    public String excluirIngrediente(@RequestParam("id") Long id) {
        ingredienteRepository.deleteById(id);
        return "redirect:/admin/gestao-ingredientes";
    }
}
