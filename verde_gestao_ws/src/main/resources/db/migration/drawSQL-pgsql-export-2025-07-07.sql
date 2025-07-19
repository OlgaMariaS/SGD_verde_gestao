CREATE TABLE "usuario" (
    "usuarioid" BIGINT NOT NULL,
    "administrador" BOOLEAN NOT NULL,
    "nome" VARCHAR(255) NOT NULL,
    "senha" VARCHAR(255) NOT NULL,
    "tipousuarioid" BIGINT NOT NULL,
    "secaoid" BIGINT NOT NULL,
    PRIMARY KEY ("usuarioid")
);

CREATE TABLE "secao" (
    "secaoid" BIGINT NOT NULL,
    "descricao" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("secaoid")
);

CREATE TABLE "tipo_usuario" (
    "tipousuarioid" BIGINT NOT NULL,
    "descricao" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("tipousuarioid")
);

CREATE TABLE "tipo_solicitacao" (
    "tiposolicitacaoid" BIGINT NOT NULL,
    "descricao" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("tiposolicitacaoid")
);

CREATE TABLE "tipo_documento" (
    "tipodocumentoid" BIGINT NOT NULL,
    "descricao" VARCHAR(255) NOT NULL,
    "prazo_em_dias" BIGINT NOT NULL,
    "inativo" BOOLEAN NOT NULL,
    PRIMARY KEY ("tipodocumentoid")
);

CREATE TABLE "solicitacao" (
    "solicitacaoid" BIGINT NOT NULL,
    "tiposolicitacaoid" BIGINT NOT NULL,
    "criador_usuarioid" BIGINT NOT NULL,
    "responsavel_usuarioid" BIGINT NOT NULL,
    "data_criacao" DATE NOT NULL,
    "status" VARCHAR(255) NOT NULL,
    "descricao" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("solicitacaoid")
);

CREATE TABLE "documento" (
    "documentoid" BIGINT NOT NULL,
    "tipodocumentoid" BIGINT NOT NULL,
    "arquivo" bytea NOT NULL,
    PRIMARY KEY ("documentoid")
);

CREATE TABLE "documento_solicitacao" (
    "solicitacaoid" BIGINT NOT NULL,
    "documentoid" BIGINT NOT NULL,
    PRIMARY KEY ("solicitacaoid", "documentoid")
);

CREATE TABLE "endereco_usuario" (
    "enderecousuarioid" BIGINT NOT NULL,
    "usuarioid" BIGINT NOT NULL,
    "numero" VARCHAR(255) NOT NULL,
    "logradouro" VARCHAR(255) NOT NULL,
    "bairro" VARCHAR(255) NOT NULL,
    "cidade" VARCHAR(255) NOT NULL,
    "estado" VARCHAR(255) NOT NULL,
    "cep" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("enderecousuarioid")
);

CREATE TABLE "comentario" (
    "comentarioid" BIGINT NOT NULL,
    "solicitacaoid" BIGINT NOT NULL,
    "autor_usuarioid" BIGINT NOT NULL,
    "texto" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("comentarioid")
);

CREATE TABLE "aviso" (
    "avisoid" BIGINT NOT NULL,
    "autor_usuarioid" BIGINT NOT NULL,
    "titulo" VARCHAR(255) NOT NULL,
    "texto" VARCHAR(255) NOT NULL,
    "data_inicio" DATE NOT NULL,
    "data_fim" DATE NOT NULL,
    PRIMARY KEY ("avisoid")
);

CREATE TABLE "info_usuario" (
    "infousuarioid" BIGINT NOT NULL,
    "usuarioid" BIGINT NOT NULL,
    "nome_completo" VARCHAR(255) NOT NULL,
    "data_nascimento" DATE NOT NULL,
    "celular" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("infousuarioid")
);

CREATE TABLE "log" (
    "logid" BIGINT NOT NULL,
    "usuarioid" BIGINT NOT NULL,
    "info" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("logid")
);

-- Adicionar constraints de chave estrangeira

ALTER TABLE "usuario"
    ADD CONSTRAINT "usuario_tipousuarioid_foreign" FOREIGN KEY ("tipousuarioid") REFERENCES "tipo_usuario" ("tipousuarioid");

ALTER TABLE "usuario"
    ADD CONSTRAINT "usuario_secaoid_foreign" FOREIGN KEY ("secaoid") REFERENCES "secao" ("secaoid");

ALTER TABLE "solicitacao"
    ADD CONSTRAINT "solicitacao_tiposolicitacaoid_foreign" FOREIGN KEY ("tiposolicitacaoid") REFERENCES "tipo_solicitacao" ("tiposolicitacaoid");

ALTER TABLE "solicitacao"
    ADD CONSTRAINT "solicitacao_criador_usuarioid_foreign" FOREIGN KEY ("criador_usuarioid") REFERENCES "usuario" ("usuarioid");

ALTER TABLE "solicitacao"
    ADD CONSTRAINT "solicitacao_responsavel_usuarioid_foreign" FOREIGN KEY ("responsavel_usuarioid") REFERENCES "usuario" ("usuarioid");

ALTER TABLE "documento"
    ADD CONSTRAINT "documento_tipodocumentoid_foreign" FOREIGN KEY ("tipodocumentoid") REFERENCES "tipo_documento" ("tipodocumentoid");

ALTER TABLE "documento_solicitacao"
    ADD CONSTRAINT "documento_solicitacao_solicitacaoid_foreign" FOREIGN KEY ("solicitacaoid") REFERENCES "solicitacao" ("solicitacaoid");

ALTER TABLE "comentario"
    ADD CONSTRAINT "comentario_solicitacaoid_foreign" FOREIGN KEY ("solicitacaoid") REFERENCES "solicitacao" ("solicitacaoid");

ALTER TABLE "comentario"
    ADD CONSTRAINT "comentario_autor_usuarioid_foreign" FOREIGN KEY ("autor_usuarioid") REFERENCES "usuario" ("usuarioid");

ALTER TABLE "aviso"
    ADD CONSTRAINT "aviso_autor_usuarioid_foreign" FOREIGN KEY ("autor_usuarioid") REFERENCES "usuario" ("usuarioid");

ALTER TABLE "endereco_usuario"
    ADD CONSTRAINT "endereco_usuario_usuarioid_foreign" FOREIGN KEY ("usuarioid") REFERENCES "usuario" ("usuarioid");

ALTER TABLE "info_usuario"
    ADD CONSTRAINT "info_usuario_usuarioid_foreign" FOREIGN KEY ("usuarioid") REFERENCES "usuario" ("usuarioid");

ALTER TABLE "log"
    ADD CONSTRAINT "log_usuarioid_foreign" FOREIGN KEY ("usuarioid") REFERENCES "usuario" ("usuarioid");

-- Adicionar sequences

CREATE SEQUENCE usuario_usuarioid_seq START 1;
CREATE SEQUENCE secao_secaoid_seq START 1;
CREATE SEQUENCE tipo_usuario_tipousuarioid_seq START 1;
CREATE SEQUENCE tipo_solicitacao_tiposolicitacaoid_seq START 1;
CREATE SEQUENCE tipo_documento_tipodocumentoid_seq START 1;
CREATE SEQUENCE solicitacao_solicitacaoid_seq START 1;
CREATE SEQUENCE documento_documentoid_seq START 1;
CREATE SEQUENCE documento_solicitacao_solicitacaoid_seq START 1;
CREATE SEQUENCE endereco_usuario_enderecousuarioid_seq START 1;
CREATE SEQUENCE comentario_comentarioid_seq START 1;
CREATE SEQUENCE aviso_avisoid_seq START 1;
CREATE SEQUENCE info_usuario_infousuarioid_seq START 1;
CREATE SEQUENCE log_logid_seq START 1;

ALTER TABLE "usuario" ALTER COLUMN "usuarioid" SET DEFAULT nextval('usuario_usuarioid_seq');
ALTER TABLE "secao" ALTER COLUMN "secaoid" SET DEFAULT nextval('secao_secaoid_seq');
ALTER TABLE "tipo_usuario" ALTER COLUMN "tipousuarioid" SET DEFAULT nextval('tipo_usuario_tipousuarioid_seq');
ALTER TABLE "tipo_solicitacao" ALTER COLUMN "tiposolicitacaoid" SET DEFAULT nextval('tipo_solicitacao_tiposolicitacaoid_seq');
ALTER TABLE "tipo_documento" ALTER COLUMN "tipodocumentoid" SET DEFAULT nextval('tipo_documento_tipodocumentoid_seq');
ALTER TABLE "solicitacao" ALTER COLUMN "solicitacaoid" SET DEFAULT nextval('solicitacao_solicitacaoid_seq');
ALTER TABLE "documento" ALTER COLUMN "documentoid" SET DEFAULT nextval('documento_documentoid_seq');
ALTER TABLE "documento_solicitacao" ALTER COLUMN "solicitacaoid" SET DEFAULT nextval('documento_solicitacao_solicitacaoid_seq');
ALTER TABLE "endereco_usuario" ALTER COLUMN "enderecousuarioid" SET DEFAULT nextval('endereco_usuario_enderecousuarioid_seq');
ALTER TABLE "comentario" ALTER COLUMN "comentarioid" SET DEFAULT nextval('comentario_comentarioid_seq');
ALTER TABLE "aviso" ALTER COLUMN "avisoid" SET DEFAULT nextval('aviso_avisoid_seq');
ALTER TABLE "info_usuario" ALTER COLUMN "infousuarioid" SET DEFAULT nextval('info_usuario_infousuarioid_seq');
ALTER TABLE "log" ALTER COLUMN "logid" SET DEFAULT nextval('log_logid_seq');
