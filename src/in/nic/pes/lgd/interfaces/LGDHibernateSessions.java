package in.nic.pes.lgd.interfaces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/*
 * @Author - Sarvapriya Malhotra
 */
public interface LGDHibernateSessions {

	Session getSession(SessionFactory sessionFactory);

}
