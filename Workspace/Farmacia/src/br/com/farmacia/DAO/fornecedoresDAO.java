package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.farmacia.conexao.Conexao;
import br.com.farmacia.domain.Fornecedores;

public class fornecedoresDAO {

	public void salvar(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores");
		sql.append("(descricao)");
		sql.append("VALUES (?)");

		Connection conexao = Conexao.conectar();
		PreparedStatement pstmt;
		pstmt = conexao.prepareStatement(sql.toString());
		pstmt.setString(1, f.getDescricao());
		pstmt.executeUpdate();

		pstmt.close();
		conexao.close();
	}

	public void excluir(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ?");

		Connection conexao = Conexao.conectar();
		PreparedStatement pstmt;
		pstmt = conexao.prepareStatement(sql.toString());
		pstmt.setLong(1, f. getCodigo());
		pstmt.executeUpdate();

		pstmt.close();
		conexao.close();
	}

	public void editar(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ?");

		Connection conexao = Conexao.conectar();

		PreparedStatement pstmt = conexao.prepareStatement(sql.toString());

		pstmt.setString(1, f.getDescricao());
		pstmt.setLong(2, f.getCodigo());
		pstmt.executeUpdate();

		pstmt.close();
		conexao.close();
	}

	public Fornecedores buscarPorCodigo(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		ResultSet rs;
		Fornecedores fornecedor = new Fornecedores();
		sql.append("SELECT * FROM fornecedores ");
		sql.append("WHERE codigo = ?");

		Connection conexao = Conexao.conectar();
		PreparedStatement pstmt;
		pstmt = conexao.prepareStatement(sql.toString());
		pstmt.setLong(1, f.getCodigo());
		rs=pstmt.executeQuery();

		if(rs.next()) {
			fornecedor.setDescricao(rs.getString("descricao"));
		}

		pstmt.close();
		conexao.close();

		return fornecedor;
	}

	public ArrayList<Fornecedores> buscarPorDescricao(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		ResultSet rs;
		sql.append("SELECT * FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");

		Connection conexao = Conexao.conectar();
		PreparedStatement pstmt;
		pstmt = conexao.prepareStatement(sql.toString());
		pstmt.setString(1, "%"+f. getDescricao()+"%");
		rs=pstmt.executeQuery();

		ArrayList<Fornecedores> listaFornecedores= new ArrayList<Fornecedores>();

		while(rs.next()) {
			Fornecedores fornecedor = new Fornecedores();

			fornecedor.setDescricao(rs.getString("descricao"));
			fornecedor.setCodigo(rs.getLong("codigo"));

			listaFornecedores.add(fornecedor);
		}

		pstmt.close();
		conexao.close();

		return listaFornecedores;
	}

	public ArrayList<Fornecedores> listar() throws SQLException{

		StringBuilder sql = new StringBuilder();
		ResultSet rs;

		sql.append("SELECT * FROM fornecedores ");
		sql.append("ORDER BY descricao ASC");

		Connection conexao = Conexao.conectar();
		PreparedStatement pstmt;
		pstmt = conexao.prepareStatement(sql.toString());
		rs=pstmt.executeQuery();

		ArrayList<Fornecedores> listaFornecedores= new ArrayList<Fornecedores>();

		while(rs.next()) {
			Fornecedores fornecedor = new Fornecedores();

			fornecedor.setDescricao(rs.getString("descricao"));
			fornecedor.setCodigo(rs.getLong("codigo"));

			listaFornecedores.add(fornecedor);
		}

		pstmt.close();
		conexao.close();

		return listaFornecedores;
	}

}
