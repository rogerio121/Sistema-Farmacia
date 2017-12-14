package br.com.farmacia.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static final String usuario = "root";
	private static final String senha = "1234";
	private static final String url = "jdbc:mysql://localhost:3306/farmacia";
	
	public static Connection conectar() throws SQLException {
		
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(url,usuario,senha);
		return conexao;
	}
	
	public static void main(String[] args) {
		try {
			Connection conexao = Conexao.conectar();
			System.out.println("Conectado");
		} catch (SQLException e) {
			System.out.println("Erro na conexão: "+e);
		}
	}
}
