package br.com.farmacia.ben;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.DAO.fornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;


@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutoBean {


	private Produtos produto ;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;
	private ArrayList<Fornecedores>comboFornecedores;



	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}


	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public void setFornecedor(Produtos produtos) {
		this.produto = produtos;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}

	public Produtos getProduto() {
		return produto;
	}
	
	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}

	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}


	@PostConstruct //Quando a pagina for construida ele executará o método abaixo, por conta do (@PostConstruct)
	public void preparaPesquisa() {

		try {
			ProdutoDAO pdb = new ProdutoDAO();
			itens = pdb.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMenssage()");
			e.printStackTrace();
		}
	}


	public void instanciarProduto(){

		try {
			produto = new Produtos();
			fornecedoresDAO dao = new fornecedoresDAO();

			comboFornecedores = dao.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void novo() {

		try {
			ProdutoDAO dao= new ProdutoDAO();
			dao.salvar(produto);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Salvo com Sucesso!");

		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
		}
	}

	public void excluir() {
		try {
			ProdutoDAO dao= new ProdutoDAO();
			dao.excluir(produto);
			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Excluído com Sucesso!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			ProdutoDAO dao= new ProdutoDAO();
			dao.excluir(produto);
			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Editado com Sucesso!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Nex.getMessage()");
			e.printStackTrace();
		}
	}

}
