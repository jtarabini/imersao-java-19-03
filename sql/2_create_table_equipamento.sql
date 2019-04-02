-- Table: public.equipamento

-- DROP TABLE public.equipamento;

CREATE TABLE public.equipamento
(
    id integer NOT NULL DEFAULT nextval('equipamento_id_seq'::regclass),
    descricao character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT equipamento_pkey PRIMARY KEY (id),
    CONSTRAINT uk_descricao UNIQUE (descricao)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.equipamento
    OWNER to postgres;