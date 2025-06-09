package com.company.pizzaria.util;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    public static void load() {
        Dotenv.configure().ignoreIfMissing().load();
    }
}
