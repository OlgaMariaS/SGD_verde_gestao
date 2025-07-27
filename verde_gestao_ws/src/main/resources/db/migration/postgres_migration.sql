-- TABELAS BÁSICAS

CREATE TABLE "usuario" (
    "usuarioid" BIGINT PRIMARY KEY,
    "administrador" BOOLEAN NOT NULL,
    "nome" VARCHAR(255) NOT NULL,
    "senha" VARCHAR(255) NOT NULL,
    "tipousuarioid" BIGINT NOT NULL,
    "secaoid" BIGINT NOT NULL
);

CREATE TABLE "secao" (
    "secaoid" BIGINT PRIMARY KEY,
    "descricao" VARCHAR(255) NOT NULL
);

CREATE TABLE "tipo_usuario" (
    "tipousuarioid" BIGINT PRIMARY KEY,
    "descricao" VARCHAR(255) NOT NULL
);

CREATE TABLE "tipo_solicitacao" (
    "tiposolicitacaoid" BIGINT PRIMARY KEY,
    "descricao" VARCHAR(255) NOT NULL
);

CREATE TABLE "tipo_documento" (
    "tipodocumentoid" BIGINT PRIMARY KEY,
    "descricao" VARCHAR(255) NOT NULL,
    "prazo_em_dias" BIGINT NOT NULL,
    "inativo" BOOLEAN NOT NULL
);

-- TABELAS RELACIONADAS A USUÁRIO

CREATE TABLE "endereco_usuario" (
    "enderecousuarioid" BIGINT PRIMARY KEY,
    "usuarioid" BIGINT NOT NULL,
    "numero" VARCHAR(255) NOT NULL,
    "logradouro" VARCHAR(255) NOT NULL,
    "bairro" VARCHAR(255) NOT NULL,
    "cidade" VARCHAR(255) NOT NULL,
    "estado" VARCHAR(255) NOT NULL,
    "cep" VARCHAR(255) NOT NULL
);

CREATE TABLE "info_usuario" (
    "infousuarioid" BIGINT PRIMARY KEY,
    "usuarioid" BIGINT NOT NULL,
    "nome_completo" VARCHAR(255) NOT NULL,
    "data_nascimento" DATE NOT NULL,
    "celular" VARCHAR(255) NOT NULL
);

CREATE TABLE "log" (
    "logid" BIGINT PRIMARY KEY,
    "usuarioid" BIGINT NOT NULL,
    "info" VARCHAR(255) NOT NULL
);

-- SOLICITAÇÕES, DOCUMENTOS E RELAÇÕES

CREATE TABLE "solicitacao" (
    "solicitacaoid" BIGINT PRIMARY KEY,
    "tiposolicitacaoid" BIGINT NOT NULL,
    "criador_usuarioid" BIGINT NOT NULL,
    "responsavel_usuarioid" BIGINT NOT NULL,
    "data_criacao" DATE NOT NULL,
    "status" VARCHAR(255) NOT NULL,
    "descricao" VARCHAR(255) NOT NULL
);

CREATE TABLE "comentario" (
    "comentarioid" BIGINT PRIMARY KEY,
    "solicitacaoid" BIGINT NOT NULL,
    "autor_usuarioid" BIGINT NOT NULL,
    "texto" VARCHAR(255) NOT NULL
);

CREATE TABLE "aviso" (
    "avisoid" BIGINT PRIMARY KEY,
    "autor_usuarioid" BIGINT NOT NULL,
    "titulo" VARCHAR(255) NOT NULL,
    "texto" VARCHAR(255) NOT NULL,
    "data_inicio" DATE NOT NULL,
    "data_fim" DATE NOT NULL
);

CREATE TABLE "documento" (
    "documentoid" BIGINT PRIMARY KEY,
    "tipodocumentoid" BIGINT NOT NULL,
    "arquivo" bytea NOT NULL
);

CREATE TABLE "documento_solicitacao" (
    "solicitacaoid" BIGINT NOT NULL,
    "documentoid" BIGINT NOT NULL,
    PRIMARY KEY ("solicitacaoid", "documentoid")
);

-- FOREIGN KEYS

ALTER TABLE "usuario"
    ADD FOREIGN KEY ("tipousuarioid") REFERENCES "tipo_usuario" ("tipousuarioid"),
    ADD FOREIGN KEY ("secaoid") REFERENCES "secao" ("secaoid");

ALTER TABLE "solicitacao"
    ADD FOREIGN KEY ("tiposolicitacaoid") REFERENCES "tipo_solicitacao" ("tiposolicitacaoid"),
    ADD CONSTRAINT "solicitacao_criador_usuarioid_foreign"
        FOREIGN KEY ("criador_usuarioid") REFERENCES "usuario" ("usuarioid") ON DELETE CASCADE,
    ADD CONSTRAINT "solicitacao_responsavel_usuarioid_foreign"
        FOREIGN KEY ("responsavel_usuarioid") REFERENCES "usuario" ("usuarioid") ON DELETE CASCADE;

ALTER TABLE "comentario"
    ADD FOREIGN KEY ("solicitacaoid") REFERENCES "solicitacao" ("solicitacaoid"),
    ADD CONSTRAINT "comentario_autor_usuarioid_foreign"
        FOREIGN KEY ("autor_usuarioid") REFERENCES "usuario" ("usuarioid") ON DELETE CASCADE;

ALTER TABLE "aviso"
    ADD CONSTRAINT "aviso_autor_usuarioid_foreign"
        FOREIGN KEY ("autor_usuarioid") REFERENCES "usuario" ("usuarioid") ON DELETE CASCADE;

ALTER TABLE "documento"
    ADD FOREIGN KEY ("tipodocumentoid") REFERENCES "tipo_documento" ("tipodocumentoid");

ALTER TABLE "documento_solicitacao"
    ADD FOREIGN KEY ("solicitacaoid") REFERENCES "solicitacao" ("solicitacaoid"),
    ADD FOREIGN KEY ("documentoid") REFERENCES "documento" ("documentoid");

ALTER TABLE "endereco_usuario"
    ADD CONSTRAINT "endereco_usuario_usuarioid_foreign"
        FOREIGN KEY ("usuarioid") REFERENCES "usuario" ("usuarioid") ON DELETE CASCADE;

ALTER TABLE "info_usuario"
    ADD CONSTRAINT "info_usuario_usuarioid_foreign"
        FOREIGN KEY ("usuarioid") REFERENCES "usuario" ("usuarioid") ON DELETE CASCADE;

ALTER TABLE "log"
    ADD CONSTRAINT "log_usuarioid_foreign"
        FOREIGN KEY ("usuarioid") REFERENCES "usuario" ("usuarioid") ON DELETE CASCADE;

-- SEQUENCES E DEFAULTS

CREATE SEQUENCE usuario_usuarioid_seq START 1;
ALTER TABLE "usuario" ALTER COLUMN "usuarioid" SET DEFAULT nextval('usuario_usuarioid_seq');

CREATE SEQUENCE secao_secaoid_seq START 1;
ALTER TABLE "secao" ALTER COLUMN "secaoid" SET DEFAULT nextval('secao_secaoid_seq');

CREATE SEQUENCE tipo_usuario_tipousuarioid_seq START 1;
ALTER TABLE "tipo_usuario" ALTER COLUMN "tipousuarioid" SET DEFAULT nextval('tipo_usuario_tipousuarioid_seq');

CREATE SEQUENCE tipo_solicitacao_tiposolicitacaoid_seq START 1;
ALTER TABLE "tipo_solicitacao" ALTER COLUMN "tiposolicitacaoid" SET DEFAULT nextval('tipo_solicitacao_tiposolicitacaoid_seq');

CREATE SEQUENCE tipo_documento_tipodocumentoid_seq START 1;
ALTER TABLE "tipo_documento" ALTER COLUMN "tipodocumentoid" SET DEFAULT nextval('tipo_documento_tipodocumentoid_seq');

CREATE SEQUENCE solicitacao_solicitacaoid_seq START 1;
ALTER TABLE "solicitacao" ALTER COLUMN "solicitacaoid" SET DEFAULT nextval('solicitacao_solicitacaoid_seq');

CREATE SEQUENCE documento_documentoid_seq START 1;
ALTER TABLE "documento" ALTER COLUMN "documentoid" SET DEFAULT nextval('documento_documentoid_seq');

CREATE SEQUENCE endereco_usuario_enderecousuarioid_seq START 1;
ALTER TABLE "endereco_usuario" ALTER COLUMN "enderecousuarioid" SET DEFAULT nextval('endereco_usuario_enderecousuarioid_seq');

CREATE SEQUENCE comentario_comentarioid_seq START 1;
ALTER TABLE "comentario" ALTER COLUMN "comentarioid" SET DEFAULT nextval('comentario_comentarioid_seq');

CREATE SEQUENCE aviso_avisoid_seq START 1;
ALTER TABLE "aviso" ALTER COLUMN "avisoid" SET DEFAULT nextval('aviso_avisoid_seq');

CREATE SEQUENCE info_usuario_infousuarioid_seq START 1;
ALTER TABLE "info_usuario" ALTER COLUMN "infousuarioid" SET DEFAULT nextval('info_usuario_infousuarioid_seq');

CREATE SEQUENCE log_logid_seq START 1;
ALTER TABLE "log" ALTER COLUMN "logid" SET DEFAULT nextval('log_logid_seq');

-- INSERTS

INSERT INTO tipo_usuario (descricao)
VALUES ('TI'), ('Chefe de Seção'), ('Chefe de Assistente'), ('Chefe Diretor');

INSERT INTO secao (descricao)
VALUES ('Nenhuma'), ('Matilha Verde'), ('Escoteiros Amarelos');

INSERT INTO usuario (usuarioid, administrador, nome, senha, tipousuarioid, secaoid)
VALUES (DEFAULT, TRUE, 'admin', 'senha123', 1, 1);

INSERT INTO tipo_solicitacao (descricao)
VALUES ('Reembolso'), ('Requisição de Documentos'), ('Acesso ao Sistema'), ('Alteração de Dados Cadastrais'), ('Solicitação de Férias'), ('Relatório Gerencial'), ('Solicitação de Equipamento'), ('Cancelamento de Solicitação'), ('Revisão de Pagamento'), ('Outros');

INSERT INTO tipo_documento (descricao, prazo_em_dias, inativo)
VALUES ('Nota Fiscal', 60, false), ('Contrato', 30, false);