//package com.company.pizzaria.controller;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/adicionais")
//public class AdicionalController {
//
//    private final AdicionalService adicionalService;
//
//    public AdicionalController(AdicionalService adicionalService) {
//        this.adicionalService = adicionalService;
//    }
//
//    @GetMapping
//    public List<Adicional> listarTodos() {
//        return adicionalService.listarTodos();
//    }
//
//    @GetMapping("/disponiveis")
//    public List<Adicional> listarDisponiveis() {
//        return adicionalService.listarDisponiveis();
//    }
//
//    @PostMapping
//    public ResponseEntity<Adicional> criar(@Valid @RequestBody AdicionalDTO adicionalDTO) {
//        Adicional adicional = new Adicional();
//        // Mapear DTO para entidade
//        return ResponseEntity.ok(adicionalService.criar(adicional));
//    }
//
//}