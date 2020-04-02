package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface MapService {
	
	public boolean mapParliament(LocalGovtBodyForm parliamentConstituency,HttpSession session)throws Exception;
	
	public MapAttachment getUploadFileConfigurationDetails() throws Exception;
	
	/**
	 * The {@code mapConfigurations} in Service Layer, Map Configuration related to GIS Server and 
	 * Other server configured in database
	 * @param level
	 * @param localGovBodyType
	 * @param vpFlag
	 * @param vpState
	 * @return
	 * @author vinay yadav
	 * @throws Exception 
	 */
	public List<Object[]> mapConfigurations(Integer level, String localGovBodyType, String vpFlag, Integer vpState) throws Exception;
	public boolean savenUpdateConstituencyCoverage(LocalGovtBodyForm localGovtBodyForm) throws Exception;
}
