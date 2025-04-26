//package com.company.pizzaria.controller;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/pizzas")
//public class PizzaController {
//
//    private final PizzaService pizzaService;
//
//    public PizzaController(PizzaService pizzaService) {
//        this.pizzaService = pizzaService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Pizza> criar(@Valid @RequestBody PizzaDTO pizzaDTO) {
//        Pizza pizza = new Pizza();
//        // Mapear DTO para entidade (usando serviços para buscar Tamanho e Sabores)
//        return ResponseEntity.ok(pizzaService.criar(pizza));
//    }
//
//    @GetMapping("/por-sabor/{saborId}")
//    public List<Pizza> listarPorSabor(@PathVariable Long saborId) {
//        return pizzaService.buscarPorSabor(saborId);
//    }
//
//}