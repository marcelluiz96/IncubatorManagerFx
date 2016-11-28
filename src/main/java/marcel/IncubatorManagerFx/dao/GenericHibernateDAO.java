package marcel.IncubatorManagerFx.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

/**
 * 
 * @author marce
 * Classe com algumas funções do Hibernate para os DAOs seguintes
 * MEU AMIGO ESSE CÓDIGO TÁ FICANDO LINDO DRUDRUDRSURSDHU
 * 
 * Update do dia 17: Descobri que existem classes assim prontas na Internet
 * Quero morrer
 *
 */
public class GenericHibernateDAO extends HibernateTransactionWrapper{

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	public GenericHibernateDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	//PERSISTE QUALQUER OBJETO ENVIADO. TODO TRATAR EXCEÇÕES
	public boolean persist(final Object object) {
		HibernateTransactionWrapper wrapper = new HibernateTransactionWrapper();
		try {
			wrapper.run(new TransactionalCode() {

				@Override
				public void execute(Session session, Transaction transaction) {
					session.persist(object);

				}
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public <T> T getById(Class<T> classe, long id) {
		Transaction t = getCurrentSession().beginTransaction();
		Criteria c = getCurrentSession().createCriteria(classe);

		c.add(Restrictions.idEq(id));

		List<T> retorno = c.list();
		t.commit();
		getCurrentSession().close();
		if (retorno == null || retorno.isEmpty()) {
			return null;
		} else {
			return classe.cast(retorno.get(0));	
		}

	}

	public <T> void update(final T object){
		Transaction t =  getCurrentSession().beginTransaction();
		getCurrentSession().update(object);
		t.commit();
		getCurrentSession().close();

	}
	
	public <T> void delete(final T object){
		Transaction t =  getCurrentSession().beginTransaction();
		getCurrentSession().delete(object);
		t.commit();
		getCurrentSession().close();

	}

	public <T> List<T> listAll(Class<T> classe) {
		return listAll(classe, false);
	}

	public <T> List<T> listAll(Class<T> classe, boolean cache) {
		getCurrentSession().beginTransaction();
		CriteriaQuery<T> query = getCurrentSession().getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		Query<T> q = getCurrentSession().createQuery(query);
		q.setCacheable(cache);
		List<T> retorno = q.list();

		getCurrentSession().close();
		return retorno;
	}


	public Session getNewSession() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}

	//Retorna a session atual, ou uma nova session caso a atual seja nula. Geralmente vai ser. O método é apenas um failsafe
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession() != null ? sessionFactory.getCurrentSession() : getNewSession();
	}



}
