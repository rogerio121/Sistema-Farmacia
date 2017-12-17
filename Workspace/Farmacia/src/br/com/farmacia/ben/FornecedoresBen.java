package br.com.farmacia.ben;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.farmacia.DAO.fornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBen {


	private Fornecedores fornecedor ;
	private ArrayList<Fornecedores> itens;
	private ArrayList<Fornecedores> itensFiltrados;



	public Fornecedores getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedores fornecedor) {
		this.fornecedor = fornecedor;
	}

	public ArrayList<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}

	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}


	@PostConstruct //Quando a pagina for construida ele executará o método abaixo, por conta do (@PostConstruct)
	public void preparaPesquisa() {

		try {
			fornecedoresDAO fdao = new fornecedoresDAO();
			itens = fdao.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMenssage()");
			e.printStackTrace();
		}
	}
	public void instanciarFornecedo(){
		fornecedor = new Fornecedores();
	}

	public void novo(){
		try {
			fornecedoresDAO fdao = new fornecedoresDAO();
			fdao.salvar(fornecedor);

			itens= fdao.listar();
			JSFUtil.adicionarMensagemSucesso("Salvo com Sucesso!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			fornecedoresDAO fdao = new fornecedoresDAO();
			fdao.excluir(fornecedor);

			itens = fdao.listar();
			JSFUtil.adicionarMensagemSucesso("Excluído com Sucesso!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedor com produto associado");
			e.printStackTrace();
		}
	}



	public void editar() {
		try {
			fornecedoresDAO fdao = new fornecedoresDAO();
			fdao.editar(fornecedor);

			itens = fdao.listar();
			JSFUtil.adicionarMensagemSucesso("Editado com Sucesso!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Nex.getMessage()");
			e.printStackTrace();
		}
	}

}
