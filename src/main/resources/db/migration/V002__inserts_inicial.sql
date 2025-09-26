insert into TB_TERMO_ACEITE (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, DESCRICAO)
VALUES (CURRENT_TIMESTAMP, null,
        'Eu entendo e concordo que as informações fornecidas podem ser transferidas para terceiros, incluindo prestadores de serviços, afiliados e parceiros comerciais da empresa Gastro Reserva, conforme necessário para cumprir as finalidades descritas neste termo. ');

insert into TB_USUARIO (DTYPE, DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, TIPO_USUARIO, CPF, NOME, SENHA, EMAIL, ENDERECO,
                        TERMO_ACEITE_COD)
VALUES (null, CURRENT_TIMESTAMP, null, 'FUNCIONARIO', '93029719022', 'nomeTeste', 'teste', 'teste@gmail.com',
        'qualquer endereço', 1);

insert into TB_RESTAURANTE (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, NOME_RESTAURANTE, ENDERECO)
values (CURRENT_TIMESTAMP, NULL, 'RESTAURANTE TESTE', 'endereço do restaurante');

insert into TB_MESA (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, QTD_CADEIRA, COD_RESTAURANTE, NUMERO)
VALUES ( CURRENT_TIMESTAMP, null, 4, 1, 1);

insert into TB_MESA (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, QTD_CADEIRA, COD_RESTAURANTE, NUMERO)
VALUES ( CURRENT_TIMESTAMP, null, 6, 1, 2);

insert into TB_MESA (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, QTD_CADEIRA, COD_RESTAURANTE, NUMERO)
VALUES ( CURRENT_TIMESTAMP, null, 8, 1, 3);

insert into TB_RESERVA (DATA_DE_INCLUSAO, DATA_DE_ALTERACAO, USUARIO_COD, COMENTARIO, RESTAURANTE_COD, DATA_AGENDAMENTO,
                        MESA_COD, CHECKED_IN, CHECKED_OUT)
VALUES (CURRENT_TIMESTAMP, null, 1, '01', 1, CURRENT_TIMESTAMP, 2, false, false)