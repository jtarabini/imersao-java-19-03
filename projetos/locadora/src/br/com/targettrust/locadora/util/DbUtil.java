package br.com.targettrust.locadora.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	public static Connection getConnection() {
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
