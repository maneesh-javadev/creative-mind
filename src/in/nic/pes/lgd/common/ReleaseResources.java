package in.nic.pes.lgd.common;

import org.hibernate.Session;

/*
 * @Author - Sarvapriya Malhotra
 */
public class ReleaseResources 
{
	public static void doReleaseResources(Session session)
	{
		if (session!=null)
		{
			session.close();
			session = null;
		}
		Runtime run = Runtime.getRuntime();
		run.gc();
	}
}
