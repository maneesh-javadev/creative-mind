package in.nic.pes.lgd.service;

import java.util.List;

import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.forms.InvalidateLocalBodyForm;

public interface ReactivateLocalBodyService {
	
	public List<Operations> getInvalidateType();
	
	public List<Object> getInvalidatedLocalBody(int operationCode,int slc);
	
	public List<Object> validateReactivation(final InvalidateLocalBodyForm invalidateLocalBody) throws Exception;
	
	public List<Object> getInvalidatedVillages(int operationCode,int slc, int dlc);

}
