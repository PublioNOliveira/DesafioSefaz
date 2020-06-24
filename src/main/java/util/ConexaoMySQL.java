package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

	public static String status = "Não conectou...";

	public ConexaoMySQL() {

	}

	public static java.sql.Connection getConexaoMySQL() {

		Connection connection = null; 

		try {

			String driverName = "com.mysql.jdbc.Driver";

			Class.forName(driverName);

			String url = "jdbc:mysql://us-cdbr-east-05.cleardb.net:3306/heroku_67f75504b5b868c";

			String username = "b00a93fc40702f"; 
			
			String password = "82240015"; 
			
			connection = DriverManager.getConnection(url, username, password);

			return connection;

		} catch (ClassNotFoundException e) { // Driver não encontrado

			System.out.println("O driver expecificado nao foi encontrado.");

			return null;

		} catch (SQLException e) {

			System.out.println("Nao foi possivel conectar ao Banco de Dados.");

			return null;

		}

	}

	public static String statusConection() {

		return status;

	}

	

	public static boolean FecharConexao() {

		try {

			ConexaoMySQL.getConexaoMySQL().close();

			return true;

		} catch (SQLException e) {

			return false;

		}

	}

	
	public static java.sql.Connection ReiniciarConexao() {

		FecharConexao();

		return ConexaoMySQL.getConexaoMySQL();

	}

}
