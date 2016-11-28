package marcel.IncubatorManagerFx.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class TransactionalCode {
	public abstract void execute(Session session,Transaction transaction);
}
