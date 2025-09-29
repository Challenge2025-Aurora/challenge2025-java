INSERT INTO EVENTO (tipo, descricao, criado_em, moto_id)
VALUES
('ENTRADA', 'Primeiro registro no sistema.', NOW() - INTERVAL '4' HOUR, 1);

INSERT INTO EVENTO (tipo, descricao, criado_em, moto_id)
VALUES
('LOCALIZACAO', 'Conferência de posição no pátio.', NOW() - INTERVAL '2' HOUR, 2);

INSERT INTO EVENTO (tipo, descricao, criado_em, moto_id)
VALUES
('MANUTENCAO', 'Enviada para a área de reparos.', NOW(), 3);