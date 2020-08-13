package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

public class DaoGeneric<C> {
	
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public void iniciaTransicao() {
		entityManager = JPAUtil.getEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
	}
	public void fechaTransicao() {
		entityTransaction.commit();
		entityManager.close();
	}
	
	public void salvar(C entidade) {
		iniciaTransicao();

		entityManager.persist(entidade);

		fechaTransicao();
	}

	public C merge(C entidade) {
		iniciaTransicao();

		C retorno = entityManager.merge(entidade);

		fechaTransicao();

		return retorno;
	}

	public void deletarPorId(C entidade) {
		iniciaTransicao();

		Object id = JPAUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id)
				.executeUpdate();

		fechaTransicao();
	}

	public List<C> getListEntity(Class<C> entidade) {
		iniciaTransicao();

		List<C> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();

		fechaTransicao();

		return retorno;
	}

}
