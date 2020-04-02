package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.Headquarters;

import java.util.List;

import org.hibernate.Session;
public interface HeadquartersDAO {

	public boolean save(Headquarters headquarters, Session session)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public boolean update(Headquarters headquarters, Session session)throws Exception;
	public abstract int getMaxHeadquartersCode()throws Exception;
// TODO Remove unused code found by UCDetector
// 	public abstract int getMaxHeadquartersVersion(int headquartersCode)throws Exception;
	public List<Headquarters> getHeadquartersDetailsDAO(String HQL,Session session)throws Exception;
	public boolean saveOrUpdate(Headquarters headquarters, Session session)throws Exception;
}
