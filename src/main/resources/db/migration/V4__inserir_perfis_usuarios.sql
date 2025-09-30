INSERT INTO PERFIL (nome) VALUES ('ROLE_ADMIN');
INSERT INTO PERFIL (nome) VALUES ('ROLE_OPERADOR');

-- senha criptografada: 'fiap123'
SET @SENHA_TESTE = '$2a$12$9iaEGSUjUZy2OeZnprn1Ve2CYQRUrU5CKBO3iNMRy2/sY2S7PtgpC';

-- perfis genéricos
INSERT INTO USUARIO (nome, email, senha)
VALUES ('Admin Teste', 'admin@fiap.com', @SENHA_TESTE);

INSERT INTO USUARIO (nome, email, senha)
VALUES ('Operador Teste', 'operador@fiap.com', @SENHA_TESTE);


-- perfis do grupo
-- senhas: 558876 (Samuel), 555174 (Felipe), 558976 (Maria)
INSERT INTO USUARIO (nome, email, senha)
VALUES ('Samuel', 'samuel@email.com', '$2a$12$.e70I0kVDXm5wSosQEM40.pUMUnOT2OCDPJmemsZgzAJzVVc1f03m');

INSERT INTO USUARIO (nome, email, senha)
VALUES ('Felipe', 'felipe@email.com', '$2a$12$KVCgspmKuaWIb65OJsaKyuY49bSHE7juqG8u.LRlKtaWeFnm8MJva');

INSERT INTO USUARIO (nome, email, senha)
VALUES ('Maria', 'maria@email.com', '$2a$12$nKSHAh7I4uw18WXQWB2REuCDqKuErNFxn0RnS4vglzz.QCIUuTZXK');


SET @ADMIN_ID = (SELECT id FROM PERFIL WHERE nome = 'ROLE_ADMIN');
SET @OPERADOR_ID = (SELECT id FROM PERFIL WHERE nome = 'ROLE_OPERADOR');


INSERT INTO USUARIO_PERFIS (usuario_id, perfil_id)
VALUES ((SELECT id FROM USUARIO WHERE email = 'samuel@email.com'), @ADMIN_ID);

INSERT INTO USUARIO_PERFIS (usuario_id, perfil_id)
VALUES ((SELECT id FROM USUARIO WHERE email = 'felipe@email.com'), @ADMIN_ID);

INSERT INTO USUARIO_PERFIS (usuario_id, perfil_id)
VALUES ((SELECT id FROM USUARIO WHERE email = 'maria@email.com'), @ADMIN_ID);



INSERT INTO USUARIO_PERFIS (usuario_id, perfil_id)
VALUES ((SELECT id FROM USUARIO WHERE email = 'admin@fiap.com'), @ADMIN_ID);

INSERT INTO USUARIO_PERFIS (usuario_id, perfil_id)
VALUES ((SELECT id FROM USUARIO WHERE email = 'operador@fiap.com'), @OPERADOR_ID);