package com.company.pizzaria;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Usa o perfil "test" (H2)
class SistemaDeGestaoParaPizzariaApplicationTests {

    @Test
    void contextLoads() {
        // Teste vazio para verificar se o contexto do Spring carrega
    }
}