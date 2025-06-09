-- Garante que o banco existe
CREATE DATABASE IF NOT EXISTS gestao_pizzaria;
USE gestao_pizzaria;

-- Criação das tabelas
CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS ingrediente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL UNIQUE,
    quantidade_atual DECIMAL(10,2) NOT NULL,
    estoque_minimo DECIMAL(10,2) NOT NULL,
    unidade_medida VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS pizzas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL UNIQUE,
    descricao TEXT,
    imagem VARCHAR(512),
    tipo VARCHAR(100) NOT NULL,
    preco_base DECIMAL(10,2) NOT NULL
);

-- Insere dados iniciais
-- Usuário admin
INSERT IGNORE INTO usuario (username, senha, role) VALUES 
('admin', '$2a$10$l.ZNd16Y0dNHEc0gwM1PvORJNdz11tAcGWdWPIn7PD804SLCzSmwS', 'ADMIN');

-- Ingredientes
INSERT IGNORE INTO ingrediente (nome, quantidade_atual, estoque_minimo, unidade_medida) VALUES
('Calabresa', 2.0, 3.0, 'Kg'),
('Molho de Tomate', 10.0, 3.0, 'Kg'),
('Presunto', 15.0, 5.0, 'Kg'),
('Bacon', 15.0, 5.0, 'Kg'),
('Cebola', 10.0, 3.0, 'Kg'),
('Tomate', 10.0, 3.0, 'Kg'),
('Frango', 15.0, 5.0, 'Kg'),
('Catupiry', 10.0, 3.0, 'Kg'),
('Milho', 10.0, 3.0, 'Kg'),
('Ervilha', 10.0, 3.0, 'Kg');

-- Pizzas
INSERT IGNORE INTO pizzas (nome, descricao, imagem, tipo, preco_base) VALUES
('Margherita Clássica', 'Pizza tradicional com molho de tomate, mussarela e manjericão fresco.', 'https://images.unsplash.com/photo-1534308983496-4fabb1a015ee?q=80&w=1476&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'tradicionais', 45.90),
('Quatro Queijos', 'Uma deliciosa mistura de mussarela, gorgonzola, parmesão e provolone.', 'https://images.unsplash.com/photo-1706954225234-fe9808ce3d72?q=80&w=1527&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'tradicionais', 49.90),
('Pepperoni Picante', 'Tradicional pizza com muito pepperoni e um toque picante.', 'https://images.unsplash.com/photo-1600028068383-ea11a7a101f3?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'tradicionais', 52.90),
('Vegetariana Especial', 'Coberta com legumes frescos, queijo e ervas aromáticas.', 'https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?ixlib=rb-4.0.3&auto=format&fit=crop&w=1470&q=80', 'especiais', 55.90),
('Frango com Catupiry', 'Deliciosa pizza de frango desfiado com catupiry cremoso.', 'https://images.unsplash.com/photo-1618213837799-25d5552820d3?q=80&w=1442&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'tradicionais', 48.90),
('Doce de Chocolate', 'Cobertura cremosa de chocolate meio amargo e raspas de chocolate branco.', 'https://images.unsplash.com/photo-1594007654729-407eedc4be65?q=80&w=1456&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'doces', 39.90),
('Calabresa com Cebola', 'Saborosa pizza de calabresa com cebolas caramelizadas.', 'https://images.unsplash.com/photo-1632935254449-e777adc9addf?q=80&w=1480&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'tradicionais', 47.90),
('Marguerita Gourmet', 'Mussarela de búfala, tomate cereja e manjericão fresco.', 'https://images.unsplash.com/photo-1513104890138-7c749659a591?ixlib=rb-4.0.3&auto=format&fit=crop&w=1470&q=80', 'especiais', 59.90),
('Portuguesa', 'Tradicional com presunto, ovos, cebola, azeitonas e ervilhas.', 'https://images.unsplash.com/photo-1595854341625-f33ee10dbf94?ixlib=rb-4.0.3&auto=format&fit=crop&w=1470&q=80', 'tradicionais', 54.90),
('Vegana', 'Coberta com queijo vegano e legumes grelhados.', 'https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?ixlib=rb-4.0.3&auto=format&fit=crop&w=781&q=80', 'especiais', 57.90),
('Cogumelos Trufados', 'Uma mistura gourmet com cogumelos e azeite de trufas.', 'https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?ixlib=rb-4.0.3&auto=format&fit=crop&w=688&q=80', 'especiais', 64.90),
('Doce de Banana com Canela', 'Banana caramelizada com canela e queijo cremoso.', 'https://images.unsplash.com/photo-1574126154517-d1e0d89ef734?ixlib=rb-4.0.3&auto=format&fit=crop&w=1074&q=80', 'doces', 42.90),
('Pizza Medonha', 'A pizza mais assustadora que você já viu', 'https://imgs.search.brave.com/9BKfixr3BWFG-xvFUFjDjrgZwlg41LLuLZEQ_IDcf_8/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvNjEz/NjkzMTQ2L3Bob3Rv/L2hhbGxvd2Vlbi1j/cmVhdGl2ZS1zY2Fy/eS1mb29kLW1vbnN0/ZXItem9tYmllLWZh/Y2Utd2l0aC1leWVz/LXBpenphLmpwZz9z/PTYxMng2MTImdz0w/Jms9MjAmYz1ZaWp2/SDJyWmo4cHo5c0h2/TGVRb1JiXy1CRVBy/dE8zZ3BVU25CSnpW/QW9FPQ', 'doces', 49.90);