package marcel.IncubatorManagerFx.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import marcel.IncubatorManagerFx.entity.Log;
import marcel.IncubatorManagerFx.entity.LogType;

public class LogHibernateDAO extends GenericHibernateDAO {
	
	public LogHibernateDAO() {
		super();
	}
	
	public List<Log> alarmsToday() { //TODO move to proper LogDAO
		getCurrentSession().beginTransaction();

		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Log> query = cb.createQuery(Log.class);

		Root<Log> root = query.from(Log.class);

		List<Predicate> predicates = new ArrayList<Predicate>();


		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		System.out.println(cal.getTime());
		predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("date"), cal.getTime()));

		predicates.add(cb.equal(root.get("logType"), LogType.ALARM));


		query.select(root).where(predicates.toArray(new Predicate[]{}));

		Query<Log> q = getCurrentSession().createQuery(query);


		List<Log> retorno = q.list();

		getCurrentSession().close();

		return retorno;
	}

	public List<Log> logsToday() {
		getCurrentSession().beginTransaction();

		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Log> query = cb.createQuery(Log.class);

		Root<Log> root = query.from(Log.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		System.out.println(cal.getTime());
		predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("date"), cal.getTime()));

		query.select(root).where(predicates.toArray(new Predicate[]{}));

		Query<Log> q = getCurrentSession().createQuery(query);


		List<Log> logList = q.list();

		getCurrentSession().close();

		return logList;
	}
	
	public int getLatestNoiseFrom(String uniqueCode) {
		try {
			
		
		getCurrentSession().beginTransaction();
		String sql = "SELECT l.id,l.noiseindb,l.date,l.incubatorName,l.logtype FROM Log l "
					+ "WHERE l.incubatorName = :uniqueCode ORDER BY l.date desc";
		
		Query query = getCurrentSession().createNativeQuery(sql, Log.class);
		query.setParameter("uniqueCode", uniqueCode);
		query.setMaxResults(1);
		Log log = (Log) query.getSingleResult();
		 
		
		getCurrentSession().close();
		return log.getNoiseInDb();
		} catch (Exception e) {
			if (getCurrentSession().getTransaction() != null)
				getCurrentSession().close();
			
			return 0;
			
		}
	}
}
