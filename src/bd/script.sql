CREATE TABLE usuario(
	user_id int PRIMARY KEY AUTO_INCREMENT,
    nome varchar(200),
    senha varchar(300),
    admin boolean
);

CREATE TABLE CNH(
	cnh_id int PRIMARY KEY auto_increment,
    perfil_id int,
    pontuacao int,
    tipo_carteira varchar(500),
    cpf varchar(300),
    data_1_habilitacao date,
    emissao date,
    validade date,
    identidade varchar(200),
    nacionalidade varchar(200),
    FOREIGN KEY (perfil_id) REFERENCES perfil(perfil_id)
);

CREATE TABLE perfil(
	perfil_id int PRIMARY KEY AUTO_INCREMENT,
    user_id int,
    FOREIGN KEY (user_id) REFERENCES usuario(user_id)
);

CREATE TABLE multa(
	multa_id int PRIMARY KEY AUTO_INCREMENT,
    codigo varchar(250),
    descricao varchar(600),
    tipo_multa int,
    pontos int,
    valor double,
    base_legal varchar(500)
);

CREATE TABLE CRLV(
	crlv_id int PRIMARY KEY AUTO_INCREMENT,
    perfil_id int,
    tipo varchar(200),
    cor_predominante varchar(200),
    categoria varchar(200),
    modelo varchar(200),
    data_documento date,
    ano_fabricacao date,
    ano_modificacao date,
    FOREIGN KEY (perfil_id) REFERENCES perfil(perfil_id)
);


