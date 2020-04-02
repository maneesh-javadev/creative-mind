package in.nic.pes.lgd.dao;
import in.nic.pes.lgd.bean.ConfigureMap;
import in.nic.pes.lgd.bean.MapAttachment;

import java.util.List;

import org.hibernate.Session;

public interface MapDao {
	public boolean configmap(ConfigureMap map)throws Exception;
	public List<MapAttachment> getUploadFileConfigurationDetails(Session session) throws Exception;
	
	/**
	 * The {@code mapConfigurations} in DAO Layer, Map Configuration related to GIS Server and 
	 * Other server configured in database
	 * @param level
	 * @param localGovBodyType
	 * @param vpFlag
	 * @param vpState
	 * @return
	 * @throws Exception 
	 */
	public List<Object[]> mapConfigurations(Integer level, String localGovBodyType, String vpFlag, Integer vpState) throws Exception;
}

	

