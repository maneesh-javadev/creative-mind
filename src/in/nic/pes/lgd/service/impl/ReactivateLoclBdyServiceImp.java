package in.nic.pes.lgd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.dao.ReactivateLocalBodyDAO;
import in.nic.pes.lgd.forms.InvalidateLocalBodyForm;
import in.nic.pes.lgd.service.ReactivateLocalBodyService;

public class ReactivateLoclBdyServiceImp implements ReactivateLocalBodyService{
	
	@Autowired
	ReactivateLocalBodyDAO reactivateLocalBodyDAO;

	@Override
	public List<Operations> getInvalidateType() {
		return reactivateLocalBodyDAO.getInvalidateType();
	}

	@Override
	public List<Object> getInvalidatedLocalBody(int operationCode,int slc) {
		return reactivateLocalBodyDAO.getInvalidatedLocalBody(operationCode,slc);
	}

	@Override
	public List<Object> validateReactivation(InvalidateLocalBodyForm invalidateLocalBody) throws Exception {
		return reactivateLocalBodyDAO.validateReactivation(invalidateLocalBody);
	}

	@Override
	public List<Object> getInvalidatedVillages(int operationCode, int slc, int dlc) {
		return reactivateLocalBodyDAO.getInvalidatedVillages(operationCode,slc,dlc);
	}

}
