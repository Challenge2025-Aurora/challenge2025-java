-- Senha: 'senha123' (hash BCrypt: $2a$10$tJ9fF2vY9G3fWj4qXn4x/O0gQv7r.3iG.yM2o.vH1/P1O5nN)
INSERT INTO PERFIL (nome) VALUES ('ROLE_ADMIN');
INSERT INTO PERFIL (nome) VALUES ('ROLE_OPERADOR');

INSERT INTO USUARIO (nome, email, senha)
VALUES ('Samuel Admin', 'admin@aurora.com', '$2a$10$tJ9fF2vY9G3fWj4qXn4x/O0gQv7r.3iG.yM2o.vH1/P1O5nN');

INSERT INTO USUARIO_PERFIS (usuario_id, perfil_id)
VALUES (1, (SELECT id FROM PERFIL WHERE nome = 'ROLE_ADMIN'));