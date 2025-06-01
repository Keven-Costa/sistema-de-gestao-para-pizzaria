package com.company.pizzaria.util;

public class GeraSenha {
    public static void main(String[] args) {
        System.out.println(
            new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("123456")
        );
    }
}
