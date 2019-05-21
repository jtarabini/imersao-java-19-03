package br.com.targettrust.traccadastros.entidades;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Entity
@Table(name="tb_acessorio")
public class Acessorio extends Entidade {
	
	@Column(name="acs_descricao")
	private String descricao;

	@ManyToMany(mappedBy = "acessorios")
	private Set<Veiculo> veiculos;

	@ManyToMany(mappedBy = "acessorios")
	private Set<Modelo> modelos;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(Set<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Set<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(Set<Modelo> modelos) {
		this.modelos = modelos;
	}
	
	@Query("   select marca "
			+ "  from Marca marca "
			+ " where (:id is null or marca.id = :id )"
			+ "   and (:nome is null or marca.nome = :nome )")
	List<Marca> search(
			@Param("id") Long id, 
			@Param("nome") String nome);
}


}
