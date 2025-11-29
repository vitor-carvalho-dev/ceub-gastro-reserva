-- =======================================================
-- V1: SCRIPT DE CRIAÇÃO DE TABELAS PARA MICROSOFT SQL SERVER
-- =======================================================

-- Criação da tabela tb_termo_aceite
CREATE TABLE tb_termo_aceite
(
    cod               BIGINT IDENTITY(1,1) NOT NULL,
    data_de_inclusao  DATETIME2,
    data_de_alteracao DATETIME2,
    descricao         NVARCHAR(MAX)        NOT NULL,
    CONSTRAINT pk_tb_termo_aceite PRIMARY KEY (cod)
);
GO

-- Criação da tabela tb_usuario
CREATE TABLE tb_usuario
(
    cod               BIGINT IDENTITY(1,1) NOT NULL,
    dtype             VARCHAR(31),
    data_de_inclusao  DATETIME2,
    data_de_alteracao DATETIME2,
    tipo_usuario      NVARCHAR(255)        NOT NULL,
    cpf               NVARCHAR(255)        NOT NULL,
    nome              NVARCHAR(255)        NOT NULL,
    senha             NVARCHAR(255)        NOT NULL,
    email             NVARCHAR(255)        NOT NULL,
    endereco          NVARCHAR(255)        NOT NULL,
    termo_aceite_cod  BIGINT,
    CONSTRAINT pk_tb_usuario PRIMARY KEY (cod)
);
GO

ALTER TABLE tb_usuario
    ADD CONSTRAINT FK_TB_USUARIO_ON_TERMOACEITE_COD FOREIGN KEY (termo_aceite_cod) REFERENCES tb_termo_aceite (cod);
GO

-- Criação da tabela tb_notificacao
CREATE TABLE tb_notificacao
(
    cod               BIGINT IDENTITY(1,1) NOT NULL,
    data_de_inclusao  DATETIME2,
    data_de_alteracao DATETIME2,
    mensagem          NVARCHAR(255)        NOT NULL,
    tipo_notificacao  NVARCHAR(255)        NOT NULL,
    cod_usuario       BIGINT,
    CONSTRAINT pk_tb_notificacao PRIMARY KEY (cod)
);
GO

ALTER TABLE tb_notificacao
    ADD CONSTRAINT FK_TB_NOTIFICACAO_ON_COD_USUARIO FOREIGN KEY (cod_usuario) REFERENCES tb_usuario (cod);
GO

-- Criação da tabela tb_restaurante
CREATE TABLE tb_restaurante
(
    cod               BIGINT IDENTITY(1,1) NOT NULL,
    data_de_inclusao  DATETIME2,
    data_de_alteracao DATETIME2,
    nome_restaurante  NVARCHAR(255),
    endereco          NVARCHAR(MAX),
    caminho_imagem    NVARCHAR(255),
    CONSTRAINT pk_tb_restaurante PRIMARY KEY (cod)
);
GO

-- Criação da tabela tb_mesa
CREATE TABLE tb_mesa
(
    cod               BIGINT IDENTITY(1,1) NOT NULL,
    data_de_inclusao  DATETIME2,
    data_de_alteracao DATETIME2,
    qtd_cadeira       INT                  NOT NULL,
    cod_restaurante   BIGINT               NOT NULL,
    numero            INT                  NOT NULL,
    CONSTRAINT pk_tb_mesa PRIMARY KEY (cod)
);
GO

ALTER TABLE tb_mesa
    ADD CONSTRAINT FK_TB_MESA_ON_COD_RESTAURANTE FOREIGN KEY (cod_restaurante) REFERENCES tb_restaurante (cod);
GO

-- Criação da tabela tb_reserva
CREATE TABLE tb_reserva
(
    cod               BIGINT IDENTITY(1,1) NOT NULL,
    data_de_inclusao  DATETIME2,
    data_de_alteracao DATETIME2,
    usuario_cod       BIGINT,
    comentario        NVARCHAR(255),
    restaurante_cod   BIGINT,
    data_agendamento  DATETIME2,
    mesa_cod          BIGINT,
    checked_in        BIT,
    checked_out       BIT,
    CONSTRAINT pk_tb_reserva PRIMARY KEY (cod)
);
GO

ALTER TABLE tb_reserva ADD CONSTRAINT uc_tb_reserva_mesa_cod UNIQUE (mesa_cod);
ALTER TABLE tb_reserva ADD CONSTRAINT FK_TB_RESERVA_ON_MESA_COD FOREIGN KEY (mesa_cod) REFERENCES tb_mesa (cod);
ALTER TABLE tb_reserva ADD CONSTRAINT FK_TB_RESERVA_ON_RESTAURANTE_COD FOREIGN KEY (restaurante_cod) REFERENCES tb_restaurante (cod);
ALTER TABLE tb_reserva ADD CONSTRAINT FK_TB_RESERVA_ON_USUARIO_COD FOREIGN KEY (usuario_cod) REFERENCES tb_usuario (cod);
GO

-- Criação da tabela tb_avaliacao
CREATE TABLE tb_avaliacao
(
    cod               BIGINT IDENTITY(1,1) NOT NULL,
    data_de_inclusao  DATETIME2,
    data_de_alteracao DATETIME2,
    nota              INT                  NOT NULL,
    cod_reserva       BIGINT,
    cod_notificacao   BIGINT,
    CONSTRAINT pk_tb_avaliacao PRIMARY KEY (cod)
);
GO

ALTER TABLE tb_avaliacao ADD CONSTRAINT uc_tb_avaliacao_cod_notificacao UNIQUE (cod_notificacao);
ALTER TABLE tb_avaliacao ADD CONSTRAINT uc_tb_avaliacao_cod_reserva UNIQUE (cod_reserva);
ALTER TABLE tb_avaliacao ADD CONSTRAINT FK_TB_AVALIACAO_ON_COD_NOTIFICACAO FOREIGN KEY (cod_notificacao) REFERENCES tb_notificacao (cod);
ALTER TABLE tb_avaliacao ADD CONSTRAINT FK_TB_AVALIACAO_ON_COD_RESERVA FOREIGN KEY (cod_reserva) REFERENCES tb_reserva (cod);
GO