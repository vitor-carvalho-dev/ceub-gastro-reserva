-- ========================================================
-- V2: SCRIPT DE CARGA DE DADOS PARA MICROSOFT SQL SERVER
-- ========================================================

-- Carga na tabela tb_termo_aceite
INSERT INTO TB_TERMO_ACEITE (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, DESCRICAO)
VALUES (GETDATE(), NULL,
        'Eu entendo e concordo que as informações fornecidas podem ser transferidas para terceiros, incluindo prestadores de serviços, afiliados e parceiros comerciais da empresa Gastro Reserva, conforme necessário para cumprir as finalidades descritas neste termo.');
GO

-- Carga na tabela tb_usuario
INSERT INTO TB_USUARIO (DTYPE, DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, TIPO_USUARIO, CPF, NOME, SENHA, EMAIL, ENDERECO,
                        TERMO_ACEITE_COD)
VALUES (NULL, GETDATE(), NULL, 'FUNCIONARIO', '93029719022', 'nomeTeste', 'teste', 'teste@gmail.com',
        'qualquer endereço', 1);
GO

-- Carga na tabela tb_restaurante
INSERT INTO TB_RESTAURANTE (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, NOME_RESTAURANTE, ENDERECO)
VALUES (GETDATE(), NULL, 'Sabor do Sertão', 'Avenida Brasil, 456, Rio de Janeiro - RJ');
GO

-- Carga na tabela tb_mesa
INSERT INTO TB_MESA (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, QTD_CADEIRA, COD_RESTAURANTE, NUMERO)
VALUES (GETDATE(), NULL, 4, 1, 1);

INSERT INTO TB_MESA (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, QTD_CADEIRA, COD_RESTAURANTE, NUMERO)
VALUES (GETDATE(), NULL, 6, 1, 2);

INSERT INTO TB_MESA (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, QTD_CADEIRA, COD_RESTAURANTE, NUMERO)
VALUES (GETDATE(), NULL, 8, 1, 3);
GO

-- Carga na tabela tb_reserva
INSERT INTO TB_RESERVA (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, USUARIO_COD, COMENTARIO, RESTAURANTE_COD, DATA_AGENDAMENTO,
                        MESA_COD, CHECKED_IN, CHECKED_OUT)
VALUES (GETDATE(), NULL, 1, '01', 1, GETDATE(), 2, 0, 0); -- Lembre-se: 0 para false
GO