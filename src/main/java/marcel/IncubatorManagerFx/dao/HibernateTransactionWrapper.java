package marcel.IncubatorManagerFx.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class HibernateTransactionWrapper {
	public <T> boolean run(TransactionalCode transactionalCode) {

		boolean status = false;
		Session session = null;
		Transaction transaction = null;
		T auxVar;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			transactionalCode.execute(session,transaction);

			transaction.commit();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				transaction.rollback();
			} catch (Exception silentIgnore) {

			}
		} finally {
			session.close();
		}
		return status;
		
		
	}
	
	
}
