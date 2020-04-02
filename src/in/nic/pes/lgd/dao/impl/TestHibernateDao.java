package in.nic.pes.lgd.dao.impl;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernateDao {

	
	public static void main(String[] args) {
		   
		  Session sess = null;
		  try{
		  SessionFactory fact = new Configuration().configure().buildSessionFactory();
		  sess = fact.openSession();
		  String sql_query = "select * from ";
		  Query query = sess.createQuery(sql_query);
		
		  for(Iterator it = query.iterate(); it.hasNext();){
			  Object[] row = (Object[]) it.next();
			 /* System.out.print(row[0]);
			  System.out.print("\t\t"+row[1]);
			  System.out.print("\t"+row[2]);*/
			  //System.out.println();
		  }
		  sess.close();
		  }
		  catch(Exception e ){
			  //System.out.println(e.getMessage());
		  }
		  }
}
