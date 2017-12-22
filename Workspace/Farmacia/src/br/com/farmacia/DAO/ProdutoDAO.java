package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.conexao.Conexao;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;

public class ProdutoDAO {

	public void salvar(Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos ");
		sql.append("(descricao, preco, quantidade, fornecedores_codigo) ");
		sql.append("VALUES (?, ?, ?, ?)");

		Connection conexao = Conexao.conectar();
		PreparedStatement pstmt;
		pstmt = conexao.prepareStatement(sql.toString());
		pstmt.setString(1, p.getDescricao());
		pstmt.setDouble(2, p.getPreco());
		pstmt.setInt(3, p.getQuantidade());
		pstmt.setLong(4, p.getFornecedores().getCodigo());
		pstmt.executeUpdate();

		pstmt.close();
		conexao.close();
	}

	public ArrayList<Produtos> listar() throws SQLException{

		StringBuilder sql = new StringBuilder();
		ResultSet rs;

		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("FROM produtos p ");
		sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo");

		Connection conexao = Conexao.conectar();
		PreparedStatement pstmt;
		pstmt = conexao.prepareStatement(sql.toString());
		rs=pstmt.executeQuery();

		ArrayList<Produtos> listaProdutos= new ArrayList<Produtos>();

		while(rs.next()) {
			Fornecedores fornecedor = new Fornecedores();
			fornecedor.setCodigo(rs.getLong("f.codigo"));
			fornecedor.setDescricao(rs.getString("f.descricao"));

			Produtos produto = new Produtos();
			produto.setDescricao(rs.getString("p.descricao"));
			produto.setCodigo(rs.getLong("p.codigo"));
			produto.setPreco(rs.getDouble("p.codigo"));
			produto.setQuantidade(rs.getInt("p.quantidade"));
			produto.setFornecedores(fornecedor);

			listaProdutos.add(produto);
		}

		pstmt.close();
		conexao.close();

		return listaProdutos;
	}
	
	public void excluir(Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ?");

		Connection conexao = Conexao.conectar();
		PreparedStatement pstmt;
		pstmt = conexao.prepareStatement(sql.toString());
		pstmt.setLong(1, p. getCodigo());
		pstmt.executeUpdate();

		pstmt.close();
		conexao.close();
	}


	public void editar(Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo = ? ");
		sql.append("WHERE codigo = ?");

		Connection conexao = Conexao.conectar();

		PreparedStatement pstmt = conexao.prepareStatement(sql.toString());

		pstmt.setString(1, p.getDescricao());
		pstmt.setDouble(2, p.getPreco());
		pstmt.setLong(3, p.getQuantidade());
		pstmt.setLong(4, p.getFornecedores().getCodigo());
		pstmt.setLong(5, p.getCodigo());
		pstmt.executeUpdate();

		pstmt.close();
		conexao.close();
	}


	public static void main(String[] args) {

		Produtos p1 = new Produtos();

		p1.setDescricao("Produto1");
		p1.setPreco(3.5);
		p1.setQuantidade(10);

		Fornecedores f = new Fornecedores();
		f.setCodigo(1122);
		p1.setFornecedores(f);

		ProdutoDAO pdb = new ProdutoDAO();


		try {

			p1.setCodigo(2);
			p1.setDescricao("Alterado");
			p1.setFornecedores(f);
			pdb.editar(p1);
			System.out.println("foi ");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erro ao buscar: "+e);
		}
	}

}
