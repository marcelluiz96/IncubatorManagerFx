package marcel.IncubatorManagerFx.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import marcel.IncubatorManagerFx.entity.User;

public class UserHibernateDAO extends GenericHibernateDAO {
	
	public User findUser(String login, String password) {
		try {
			
		
		getCurrentSession().beginTransaction();
		
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		
		
		Root<User> root = query.from(User.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.equal(root.get("login"), login));
		predicates.add(cb.equal(root.get("password"), password));
	
		query.select(root).where(predicates.toArray(new Predicate[]{}));
		
		Query<User> q = getCurrentSession().createQuery(query);
		
		List<User> retorno = q.list();
		
		getCurrentSession().close();
		
		if (retorno != null && !(retorno.isEmpty()))
			return retorno.get(0);
		else return null;
		} catch (Exception e) {
			e.printStackTrace();
			getCurrentSession().close();
			return null;
		}
	}

	public void registerUser(User user) throws Exception {
		boolean success = persist(user);
		
		if (!success) throw new Exception("Failed to register the user!");
		
	}

}
