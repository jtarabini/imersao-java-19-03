<<<<<<< HEAD
package br.com.targettrust.locadora.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.targettrust.locadora.entidades.Carro;

public class CarroRepositoryImpl implements CarroRepository {

	@Override
	public void insertCarro(Carro carro)  {
		try {
			String insert = "INSERT INTO carro(" 
					+ "	placa, marca, modelo, cor, portas)"
					+ "	VALUES (? , ?, ?, ?, ?)";
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, carro.getPlaca());
			statement.setString(2, carro.getMarca());
			statement.setString(3, carro.getModelo());
			statement.setString(4, carro.getCor());
			statement.setInt(5, carro.getPortas());
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Carro de placa " + carro.getPlaca() + "inserido com sucesso");
	}

	@Override
	public void updateCarro(Carro carro) {
		// TODO Auto-generated method stub
		String sql = "UPDATE carro SET "
				+ "  placa = ?, marca = ?, modelo = ?,"
				+ "  cor = ?, portas = ?, ano = ? "
				+ " WHERE id = ? ";
		try {
			Connection connection = this.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, carro.getPlaca());
			ps.setString(2, carro.getMarca());
			ps.setString(3, carro.getModelo());
			ps.setString(4, carro.getCor());
			ps.setInt(5, carro.getPortas());
			ps.setInt(6,  carro.getAno());
			ps.setInt(7, carro.getId());
			ps.executeUpdate();
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Carro> listCarros() {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from carro";
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
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
		// TODO Auto-generated method stub

	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/locadora", "postgres",
					"postgres");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
=======
package br.com.targettrust.locadora.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.targettrust.locadora.entidades.Carro;

public class CarroRepositoryImpl implements CarroRepository {

	@Override
	public void insertCarro(Carro carro)  {
		try {
			String insert = "INSERT INTO carro(" 
					+ "	placa, marca, modelo, cor, portas)"
					+ "	VALUES (? , ?, ?, ?, ?)";
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, carro.getPlaca());
			statement.setString(2, carro.getMarca());
			statement.setString(3, carro.getModelo());
			statement.setString(4, carro.getCor());
			statement.setInt(5, carro.getPortas());
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Carro de placa " + carro.getPlaca() + "inserido com sucesso");
	}

	@Override
	public void updateCarro(Carro carro) {
		// TODO Auto-generated method stub
		String sql = "UPDATE carro SET "
				+ "  placa = ?, marca = ?, modelo = ?,"
				+ "  cor = ?, portas = ?, ano = ? "
				+ " WHERE id = ? ";
		try {
			Connection connection = this.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, carro.getPlaca());
			ps.setString(2, carro.getMarca());
			ps.setString(3, carro.getModelo());
			ps.setString(4, carro.getCor());
			ps.setInt(5, carro.getPortas());
			ps.setInt(6,  carro.getAno());
			ps.setInt(7, carro.getId());
			ps.executeUpdate();
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Carro> listCarros() {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from carro";
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
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
		// TODO Auto-generated method stub

	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/locadora", "postgres",
					"postgres");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
>>>>>>> 7a9648241bf71e41e55b651d8ac0159c83dd7437
