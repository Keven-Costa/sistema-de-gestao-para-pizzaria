package com.company.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.pizzaria.model.entity.Endereco;
import com.company.pizzaria.model.entity.Cliente;
import com.company.pizzaria.repository.EnderecoRepository;
import com.company.pizzaria.repository.ClienteRepository;

@Controller
@RequestMapping("/checkout")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String exibirFormulario(@ModelAttribute("endereco") Endereco endereco) {
        return "checkout";
    }

    @PostMapping("/salvar")
    public String salvarEndereco(
            @ModelAttribute("endereco") Endereco endereco,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "checkout";
        }

        // SOLUÇÃO SIMPLES PARA APRENDIZADO:
        // 1. Criar um cliente temporário (ou pegar um existente)
        Cliente clienteTemp = new Cliente();
        clienteTemp.setNome("Cliente Temporário"); // Você pode pegar do formulário depois
        clienteRepository.save(clienteTemp); // Salva o cliente primeiro
        
        // 2. Associar ao endereço
        endereco.setCliente(clienteTemp);
        
        // 3. Agora salva o endereço
        enderecoRepository.save(endereco);

        redirectAttributes.addFlashAttribute("mensagem", "Endereço salvo com sucesso!");
        return "redirect:/rastreamento-pedido";
    }
}