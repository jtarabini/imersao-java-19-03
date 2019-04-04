package br.com.targettrust.locadora.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import br.com.targettrust.locadora.entidades.Moto;
import br.com.targettrust.locadora.exception.PlacaJaCadastradaException;
import br.com.targettrust.locadora.exception.VeiculoNaoEncontradoException;
import br.com.targettrust.locadora.util.DbUtil;

public class MotoRepositoryImpl implements MotoRepository {

	@Override
	public void insert(Moto moto) {
		try {
			String insert = "INSERT INTO veiculo(" + "	placa, marca, modelo, cor, cilindradas)"
					+ "	VALUES (? , ?, ?, ?, ?)";
			Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, moto.getPlaca());
			statement.setString(2, moto.getMarca());
			statement.setString(3, moto.getModelo());
			statement.setString(4, moto.getCor());
			statement.setInt(5, moto.getCilindradas());
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (PSQLException e) {
			throw new PlacaJaCadastradaException();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Moto de placa " + moto.getPlaca() + "inserido com sucesso");
	}

	@Override
	public void update(Moto moto) {
		// TODO Auto-generated method stub
		String sql = "UPDATE veiculo SET " + "  placa = ?, marca = ?, modelo = ?," + "  cor = ?, cilindradas = ?, ano = ? "
				+ " WHERE id = ? ";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, moto.getPlaca());
			ps.setString(2, moto.getMarca());
			ps.setString(3, moto.getModelo());
			ps.setString(4, moto.getCor());
			ps.setInt(5, moto.getCilindradas());
			ps.setInt(6, moto.getAno());
			ps.setInt(7, moto.getId());
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
	public List<Moto> list() {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from veiculo where tipo = ?";
			Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "MOTO");
			ResultSet resultSet = statement.executeQuery(sql);
			List<Moto> motos = new ArrayList<>();
			while (resultSet.next()) {
				Moto moto = new Moto();
				// popular
				moto.setCor(resultSet.getString("cor"));
				moto.setMarca(resultSet.getString("marca"));
				moto.setModelo(resultSet.getString("modelo"));
				moto.setPlaca(resultSet.getString("placa"));
				moto.setCilindradas(resultSet.getInt("cilindradas"));
				motos.add(moto);
			}
			return motos;
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
	public Moto findByPlaca(String placa) {
		String sql = "select * from veiculo where placa = ?";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, placa);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
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
		} catch (PSQLException e) {
			throw new PlacaJaCadastradaException();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

}