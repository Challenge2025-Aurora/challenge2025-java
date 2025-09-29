INSERT INTO PERFIL (nome) VALUES ('ROLE_ADMIN');
INSERT INTO PERFIL (nome) VALUES ('ROLE_OPERADOR');

-- as senhas são nossos rms criptografados

-- senha: 558876
INSERT INTO USUARIO (nome, email, senha)
VALUES ('Samuel', 'samuel@email.com', '$2a$12$.e70I0kVDXm5wSosQEM40.pUMUnOT2OCDPJmemsZgzAJqVVc1f03m');

-- senha: 555174
INSERT INTO USUARIO (nome, email, senha)
VALUES ('Felipe', 'felipe@email.com', '$2a$12$KVCgspmKuaWIb65OJsaKyuY49bSHE7juqG8u.LRlKtaWeFnm8MJva');

-- senha: 558976
INSERT INTO USUARIO (nome, email, senha)
VALUES ('Maria', 'maria@email.com', '$2a$12$nKSHAh7I4uw18WXQWB2REuCDqKuErNFxn0RnS4vglzz.QCIUuTZXK');

-- pessoa aleatoria pra ter alguem com função de operador
INSERT INTO USUARIO (nome, email, senha)
VALUES ('Operador', 'operador@email.com', '$2a$12$hKxA.jzZXofa2s8ycHOlSOvzW2dIrvUWymAo8ISMpXAmMXhkhqarC');

SET @ADMIN_ID = (SELECT id FROM PERFIL WHERE nome = 'ROLE_ADMIN');
SET @OPERADOR_ID = (SELECT id FROM PERFIL WHERE nome = 'ROLE_OPERADOR');

INSERT INTO USUARIO_PERFIS (usuario_id, perfil_id)
VALUES ((SELECT id FROM USUARIO WHERE email = 'samuel@email.com'), @ADMIN_ID);

INSERT INTO USUARIO_PERFIS (usuario_id, perfil_id)
VALUES ((SELECT id FROM USUARIO WHERE email = 'felipe@email.com'), @ADMIN_ID);

INSERT INTO USUARIO_PERFIS (usuario_id, perfil_id)
VALUES ((SELECT id FROM USUARIO WHERE email = 'maria@email.com'), @ADMIN_ID);

INSERT INTO USUARIO_PERFIS (usuario_id, perfil_id)
VALUES ((SELECT id FROM USUARIO WHERE email = 'operador@email.com'), @OPERADOR_ID);