package in.nic.pes.lgd.dao;

import java.util.List;

import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.forms.InvalidateLocalBodyForm;
import in.nic.pes.lgd.response.InvalidatedVillageResponse;
import in.nic.pes.lgd.response.Response;

public interface ReactivateLocalBodyDAO {

	public List<Operations> getInvalidateType();
	
	public List<Object> getInvalidatedLocalBody(int operationCodes,int slc);
	
	public List<Object> validateReactivation(InvalidateLocalBodyForm invalidateLocalBody) throws Exception;
	
	public List<Object> getInvalidatedVillages(int operationCodes,int slc, int dlc);
	
	public Response performReactivationOfVillage(final InvalidatedVillageResponse invalidatedVillageResponse) throws Exception;
}
