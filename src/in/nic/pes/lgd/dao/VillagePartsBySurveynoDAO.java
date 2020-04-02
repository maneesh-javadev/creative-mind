package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.VillagePartsBySurveyno;

public interface VillagePartsBySurveynoDAO {
	public boolean save(VillagePartsBySurveyno villagePartsBySurveyno)throws Exception;
	public int getMaxRecords(String query)throws Exception;

}
