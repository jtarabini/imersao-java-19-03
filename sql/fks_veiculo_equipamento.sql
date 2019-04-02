ALTER TABLE rl_carro_equipamento
ADD FOREIGN KEY (id_veiculo) REFERENCES veiculo(id);

ALTER TABLE rl_carro_equipamento
ADD FOREIGN KEY (id_equipamento) REFERENCES equipamento(id);