package br.com.targettrust.locadora.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import br.com.targettrust.locadora.entidades.Carro;
import br.com.targettrust.locadora.entidades.Moto;
import br.com.targettrust.locadora.entidades.Veiculo;
import br.com.targettrust.locadora.exception.PlacaJaCadastradaException;
import br.com.targettrust.locadora.exception.VeiculoNaoEncontradoException;
import br.com.targettrust.locadora.util.DbUtil;

public class VeiculoRepositoryImpl implements VeiculoRepository {

	@Override
	public void insert(Veiculo veiculo) {
		try {
			String insert = "INSERT INTO veiculo( " 
					+ "	placa, marca, modelo, cor, ano, tipo, portas, cilindradas)"
					+ "	VALUES (? , ?, ?, ?, ?, ? , ?, ? )";
			Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, veiculo.getPlaca());
			statement.setString(2, veiculo.getMarca());
			statement.setString(3, veiculo.getModelo());
			statement.setString(4, veiculo.getCor());
			statement.setInt(5, veiculo.getAno());
			if(veiculo instanceof Carro) {
				statement.setString(6, "CARRO");
				Carro carro = (Carro) veiculo;
				statement.setInt(7, carro.getPortas());
				statement.setNull(8, java.sql.Types.INTEGER);
			}
			else if(veiculo instanceof Moto) {
				statement.setString(6, "MOTO");
				Moto moto = (Moto) veiculo;
				statement.setNull(7, java.sql.Types.INTEGER);
				statement.setInt(8, moto.getCilindradas());
			}
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (PSQLException e) {
			throw new PlacaJaCadastradaException();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Veiculo de placa " + veiculo.getPlaca() + "inserido com sucesso");
	}

	@Override
	public void update(Veiculo veiculo) {
		// TODO Auto-generated method stub
		String sql = "UPDATE veiculo SET " 
				+ "  placa = ?, marca = ?, modelo = ?," 
				+ "  cor = ?, ano = ?, portas = ?, cilindradas = ?"
				+ " WHERE id = ? ";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, veiculo.getPlaca());
			ps.setString(2, veiculo.getMarca());
			ps.setString(3, veiculo.getModelo());
			ps.setString(4, veiculo.getCor());
			ps.setInt(5, veiculo.getAno());
			if(veiculo instanceof Carro) {
				Carro carro = (Carro) veiculo;
				ps.setInt(6, carro.getPortas());
				ps.setNull(7, java.sql.Types.INTEGER);
			}
			else if(veiculo instanceof Moto) {
				Moto moto = (Moto) veiculo;
				ps.setNull(6, java.sql.Types.INTEGER);
				ps.setInt(7, moto.getCilindradas());
			}
			ps.setInt(8, veiculo.getId());
			int result = ps.executeUpdate();
			if (result < 1) {
				throw new VeiculoNaoEncontradoException();
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Veiculo> list() {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from veiculo";
			Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "CARRO");
			ResultSet resultSet = statement.executeQuery(sql);
			List<Veiculo> veiculos = new ArrayList<>();
			while (resultSet.next()) {
				if(resultSet.getString("tipo").equals("CARRO")) {
					Carro carro = new Carro();
					carro.setId(resultSet.getInt("id"));
					carro.setAno(resultSet.getInt("ano"));
					carro.setCor(resultSet.getString("cor"));
					carro.setMarca(resultSet.getString("marca"));
					carro.setModelo(resultSet.getString("modelo"));
					carro.setPlaca(resultSet.getString("placa"));
					carro.setPortas(resultSet.getInt("portas"));
					veiculos.add(carro);					
				}
				else if(resultSet.getString("tipo").equals("MOTO")) {
					Moto moto = new Moto();
					moto.setId(resultSet.getInt("id"));
					moto.setAno(resultSet.getInt("ano"));
					moto.setCor(resultSet.getString("cor"));
					moto.setMarca(resultSet.getString("marca"));
					moto.setModelo(resultSet.getString("modelo"));
					moto.setPlaca(resultSet.getString("placa"));
					moto.setCilindradas(resultSet.getInt("cilindradas"));
					veiculos.add(moto);					
				}
			}
			return veiculos;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(String placa) {
		String sql = "delete from veiculo where placa = ?";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, placa);
			int result = ps.executeUpdate();
			if (result < 1) {
				throw new VeiculoNaoEncontradoException();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from veiculo where id = ?";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			if (result < 1) {
				throw new VeiculoNaoEncontradoException();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public Veiculo findByPlaca(String placa) {
		String sql = "select * from veiculo where placa = ?";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, placa);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if(rs.getString("tipo").equals("CARRO")) {
					Carro carro = new Carro();
					carro.setId(rs.getInt("id"));
					carro.setAno(rs.getInt("ano"));
					carro.setCor(rs.getString("cor"));
					carro.setMarca(rs.getString("marca"));
					carro.setModelo(rs.getString("modelo"));
					carro.setPlaca(rs.getString("placa"));
					carro.setPortas(rs.getInt("portas"));
					return carro;
				}
				else {
					Moto moto = new Moto();
					moto.setId(rs.getInt("id"));
					moto.setAno(rs.getInt("ano"));
					moto.setCor(rs.getString("cor"));
					moto.setMarca(rs.getString("marca"));
					moto.setModelo(rs.getString("modelo"));
					moto.setPlaca(rs.getString("placa"));
					moto.setCilindradas(rs.getInt("cilindradas"));
					return moto;					
				}
			}
		} catch (PSQLException e) {
			throw new PlacaJaCadastradaException();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

}