package nl.lakedigital.hulpmiddelen.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import nl.lakedigital.hulpmiddelen.domein.PersistenceObject;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractRepository<T> {
	private final Logger logger = Logger.getLogger(this.getClass());
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	protected String persistenceContext = "dias";

	private final Class<T> persistentClass;

	public abstract void setPersistenceContext(String persistenceContext);

	public void zetPersistenceContext(String persistenceContext) {
		if (persistenceContext != null) {
			this.persistenceContext = persistenceContext;
		} else {
			this.persistenceContext = "dias";
		}
	}

	public AbstractRepository(Class<T> entityClass) {
		this.persistentClass = entityClass;
	}

	@Transactional
	public void verwijder(T o) {
		getTx().begin();
		getEm().remove(o);
		getTx().commit();
	}

	@Transactional
	public List<T> alles() {
		Query query = getEm().createQuery("select e from " + this.persistentClass.getSimpleName() + " e");
		@SuppressWarnings("unchecked")
		List<T> ret = query.getResultList();

		return ret;
	}

	@Transactional
	public void opslaan(T o) {
		logger.debug("opslaan " + o);

		getTx().begin();
		if (((PersistenceObject) o).getId() == null) {
			getEm().persist(o);
		} else {
			getEm().merge(o);
		}
		getTx().commit();
	}

	@Transactional
	public T lees(Long id) {
		return getEm().find(this.persistentClass, id);
	}

	public EntityManagerFactory getEmf() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(persistenceContext);
		}
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public EntityManager getEm() {
		if (em == null) {
			em = getEmf().createEntityManager();
		}
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public EntityTransaction getTx() {
		if (tx == null) {
			tx = getEm().getTransaction();
		}
		return tx;
	}

	public void setTx(EntityTransaction tx) {
		this.tx = tx;
	}
}