package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Pessoa;
import persistence.PessoaDao;

@ManagedBean
@RequestScoped
public class PesquisaPessoaBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Pessoa PessoaSelecionada;
	private List<Pessoa> pessoas;
	
	public PesquisaPessoaBean() {
		PessoaSelecionada = new Pessoa();
	}

	public Pessoa getPessoaSelecionada() {
		return PessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		PessoaSelecionada = pessoaSelecionada;
	}

	public List<Pessoa> getPessoas() {
		pessoas = new ArrayList<>();
		try{
			pessoas = new PessoaDao().findAll();
		}catch(Exception ex){
			
		}
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public void editar(){
		FacesContext fc = FacesContext.getCurrentInstance();
		PessoaDao pd = new PessoaDao();
		try{
			pd.create(PessoaSelecionada);
			fc.addMessage("formListar", new FacesMessage("Salvo com sucesso"));
		}catch(Exception ex){
			fc.addMessage("formListar", new FacesMessage(ex.getMessage()));
		}
	}
	
}
