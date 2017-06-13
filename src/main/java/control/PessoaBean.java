package control;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Pessoa;
import persistence.PessoaDao;

@ManagedBean
@RequestScoped
public class PessoaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	private Pessoa pessoaEditar;
	private List<Pessoa> pessoas;
	
	public PessoaBean() {
		pessoa = new Pessoa();
		pessoaEditar = new Pessoa();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	public Pessoa getPessoaEditar() {
		return pessoaEditar;
	}

	public void setPessoaEditar(Pessoa pessoaEditar) {
		this.pessoaEditar = pessoaEditar;
	}

	public List<Pessoa> getPessoas() {
		try{
			pessoas = new PessoaDao().findAll();
		}catch(Exception ex){
			
		}
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public void gravar(){
		PessoaDao pd = new PessoaDao();
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			pd.create(pessoa);
			fc.addMessage("form1", new FacesMessage("Gravado com sucesso!"));
			pessoa = new Pessoa();
		}catch(Exception ex){
			fc.addMessage("form1", new FacesMessage(ex.getMessage()));
		}
	}
	
	public void excluir(){
		FacesContext fc = FacesContext.getCurrentInstance();
		PessoaDao pd = new PessoaDao();
		try{
			pd.excluir(pessoa);
			fc.addMessage("", new FacesMessage("Dados excluidos"));
		}catch(Exception ex){
			fc.addMessage("", new FacesMessage(ex.getMessage()));
		}
	}
}
