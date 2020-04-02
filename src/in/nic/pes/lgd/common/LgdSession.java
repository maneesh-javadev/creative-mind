package in.nic.pes.lgd.common;



import in.nic.pes.lgd.interfaces.LGDHibernateSessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 
 * @author Sarvapriya Malhotra
 *
 */
public class LgdSession implements LGDHibernateSessions{

	private Session session;
	
	@Override
	public Session getSession(SessionFactory sessionFactory)
	{		
		if (this.session==null)
			this.session = sessionFactory.openSession();
		else
			this.session.flush();
		
		return this.session;
	}
	
	
	@Override
	protected void finalize() throws Throwable 
	{
		if (this.session.isOpen())
		{
			this.session.close();
		}
		this.session=null;
	}
	
	
}
