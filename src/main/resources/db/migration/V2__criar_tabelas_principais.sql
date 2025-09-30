CREATE TABLE MOTO (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(255) NOT NULL UNIQUE,
    modelo VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    atualizado_em TIMESTAMP,
    data_cadastro TIMESTAMP NOT NULL,
    ultimo_setor VARCHAR(255),
    ultimo_slot VARCHAR(255)
);

CREATE TABLE EVENTO (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    criado_em TIMESTAMP NOT NULL,
    moto_id BIGINT NOT NULL,
    FOREIGN KEY (moto_id) REFERENCES MOTO(id)
);

CREATE TABLE DETECCAO (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(255),
    modelo_prob VARCHAR(255),
    confianca DOUBLE PRECISION NOT NULL,
    bbox_x INT,
    bbox_y INT,
    bbox_w INT,
    bbox_h INT,
    setor_estimado VARCHAR(255),
    slot_estimado VARCHAR(255),
    timestamp TIMESTAMP NOT NULL
);