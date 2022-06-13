-- ID INTEGER NOT NULL, NOME VARCHAR, PLANO_EXCLUSIVE BOOLEAN, NUMERO_CONTA VARCHAR, DATA_NASCIMENTO DATE, SALDO NUMERIC
INSERT INTO CLIENTE (ID, NOME, PLANO_EXCLUSIVE, NUMERO_CONTA, DATA_NASCIMENTO, SALDO) VALUES 
(1, 'TESTE 1', FALSE, 'CC000001', '1988-01-01', 1000), 
(2, 'TESTE 2', FALSE, 'CC000002', '1988-02-02', 1000),
(3, 'TESTE 3', FALSE, 'CC000003', '1988-03-03', 1000),
(4, 'TESTE 4', FALSE, 'CC000004', '1988-04-04', 1000),
(5, 'TESTE 5', TRUE, 'CC000005', '1988-05-05', 0),
(6, 'TESTE 6', TRUE, 'CC000006', '1988-06-06', 0);