DROP TABLE IF EXISTS planetas;

CREATE TABLE planetas (
    id bigint not null auto_increment primary key,
    nome varchar(255),
    clima varchar(128),
    terreno varchar(128)
);
