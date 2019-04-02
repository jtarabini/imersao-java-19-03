package br.com.targettrust.locadora.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import br.com.targettrust.locadora.entidades.Carro;
import br.com.targettrust.locadora.exception.PlacaJaCadastradaException;
import br.com.targettrust.locadora.exception.VeiculoNaoEncontradoException;
import br.com.targettrust.locadora.util.DbUtil;

public class CarroRepositoryImpl implements CarroRepository {

	@Override
	public void insertCarro(Carro carro) {
		try {
			String insert = "INSERT INTO veiculo(" + "	placa, marca, modelo, cor, portas)"
					+ "	VALUES (? , ?, ?, ?, ?)";
			Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, carro.getPlaca());
			statement.setString(2, carro.getMarca());
			statement.setString(3, carro.getModelo());
			statement.setString(4, carro.getCor());
			statement.setInt(5, carro.getPortas());
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (PSQLException e) {
			throw new PlacaJaCadastradaException();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Carro de placa " + carro.getPlaca() + "inserido com sucesso");
	}

	@Override
	public void updateCarro(Carro carro) {
		// TODO Auto-generated method stub
		String sql = "UPDATE veiculo SET " + "  placa = ?, marca = ?, modelo = ?," + "  cor = ?, portas = ?, ano = ? "
				+ " WHERE id = ? ";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, carro.getPlaca());
			ps.setString(2, carro.getMarca());
			ps.setString(3, carro.getModelo());
			ps.setString(4, carro.getCor());
			ps.setInt(5, carro.getPortas());
			ps.setInt(6, carro.getAno());
			ps.setInt(7, carro.getId());
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
	public List<Carro> listCarros() {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from veiculo where tipo = ?";
			Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "CARRO");
			ResultSet resultSet = statement.executeQuery(sql);
			List<Carro> carros = new ArrayList<>();
			while (resultSet.next()) {
				Carro carro = new Carro();
				// popular
				carro.setCor(resultSet.getString("cor"));
				carro.setMarca(resultSet.getString("marca"));
				carro.setModelo(resultSet.getString("modelo"));
				carro.setPlaca(resultSet.getString("placa"));
				carro.setPortas(resultSet.getInt("portas"));
				carros.add(carro);
			}
			return carros;
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
	public Carro findByPlaca(String placa) {
		String sql = "select * from veiculo where placa = ?";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, placa);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
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
		} catch (PSQLException e) {
			throw new PlacaJaCadastradaException();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

}