package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Pessoa;

public class PessoaDao {

	EntityManager manager;
	EntityTransaction transaction;

	public PessoaDao() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernate4");
		manager = factory.createEntityManager();
	}

	public void create(Pessoa p) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(p);
		transaction.commit();
	}

	public void excluir(Pessoa pessoa) {
		transaction = manager.getTransaction();
		transaction.begin();

		Pessoa p = manager.find(Pessoa.class, pessoa.getId());
		manager.remove(p);

		transaction.commit();
		manager.flush();
	}

	public Pessoa findByCode(Long cod) {
		return manager.find(Pessoa.class, cod);
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> findAll() {
		return manager.createQuery("From Pessoa").getResultList();
	}

	public static void main(String[] args) {
		PessoaDao pd = new PessoaDao();
		try {
			Pessoa p = pd.findByCode(1L);
			p.setNome("Matheus Bandeira");

			pd.create(p);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
