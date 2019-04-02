-- Table: public.veiculo

-- DROP TABLE public.veiculo;

CREATE TABLE public.veiculo
(
    id integer NOT NULL DEFAULT nextval('carro_id_seq'::regclass),
    placa character varying(8) COLLATE pg_catalog."default" NOT NULL,
    marca character varying(20) COLLATE pg_catalog."default" NOT NULL,
    modelo character varying(20) COLLATE pg_catalog."default" NOT NULL,
    cor character varying(60) COLLATE pg_catalog."default" NOT NULL,
    portas bigint,
    ano bigint,
    cilindradas bigint,
    tipo character varying(5) COLLATE pg_catalog."default" NOT NULL DEFAULT 'CARRO'::character varying,
    CONSTRAINT carro_pkey PRIMARY KEY (id),
    CONSTRAINT uk_placa UNIQUE (placa)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.veiculo
    OWNER to postgres;