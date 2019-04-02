-- Table: public.rl_carro_equipamento

-- DROP TABLE public.rl_carro_equipamento;

CREATE TABLE public.rl_carro_equipamento
(
    id_veiculo bigint NOT NULL,
    id_equipamento bigint NOT NULL,
    CONSTRAINT rl_carro_equipamento_pkey PRIMARY KEY (id_veiculo, id_equipamento),
    CONSTRAINT rl_carro_equipamento_id_equipamento_fkey FOREIGN KEY (id_equipamento)
        REFERENCES public.equipamento (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rl_carro_equipamento_id_veiculo_fkey FOREIGN KEY (id_veiculo)
        REFERENCES public.veiculo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.rl_carro_equipamento
    OWNER to postgres;