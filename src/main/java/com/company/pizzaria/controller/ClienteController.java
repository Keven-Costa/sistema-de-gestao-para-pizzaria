package com.company.pizzaria.controller;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.company.pizzaria.model.entity.Cliente;
import com.company.pizzaria.repository.ClienteRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cadastro")
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping
    public String exibirForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastro";
    }
    
    @PostMapping("/salvar")
    public String salvarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
                              BindingResult result,
                              @RequestParam String confirmarSenha,
                              Model model) {
        
        // Validação de senha
        if (!cliente.getSenha().equals(confirmarSenha)) {
            result.rejectValue("senha", "error.cliente", "As senhas não coincidem");
        }
        
        // Validação de email único (simplificada)
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            result.rejectValue("email", "error.cliente", "Este e-mail já está cadastrado");
        }
        
        if (result.hasErrors()) {
            return "cadastro";
        }
        
        // Define a data de cadastro
        cliente.setDataCadastro(LocalDateTime.now());
        
        // Salva o cliente (sem criptografia para simplificar)
        clienteRepository.save(cliente);
        
        return "redirect:/cadastro/sucesso";
    }
    
    @GetMapping("/sucesso")
    public String sucessoCadastro() {
        return "cadastro-sucesso";
    }
}