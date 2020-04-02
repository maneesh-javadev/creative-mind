package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.LgdDesignation;
import org.springframework.validation.Errors;

/*
 * @Author Pooja
 * on 12-05-2015
 */
public class DepartmentDesignationValidator {

	public void validate(Object model, Errors errors) {
		
		LgdDesignation designationForm = (LgdDesignation) model;
		String flowName = designationForm.getFlowName();
		
		if("designationCenter".equals(flowName) || "designationState".equals(flowName)){
			Integer orgType = designationForm.getOrgType();
			if(orgType == null || orgType.equals(0)){
				errors.rejectValue("orgType","Organization Type is Invalid.");
				return;
			}
			Integer olc = designationForm.getOlc();
			if(olc == null || olc.equals(0)){
				errors.rejectValue("olc","Organization is Invalid.");
				return;
			}
			
		}
		else{
			errors.rejectValue("designationName","Designation Flow does'nt Mapped with any Valid Page.");
			return;
		}
		
	}

}
