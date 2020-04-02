package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.SearchForm;
import in.nic.pes.lgd.service.ComboFillingService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component(value = "viewValidatorLB")
public class ViewValidatorLB implements Validator {
	private static final Logger log = Logger.getLogger(ViewValidatorLB.class);

	@Autowired
	private ComboFillingService comboFillingService;

	@Override
	public boolean supports(Class<?> clazz) {

		return SearchForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub

	}

	public void validateView(Object target, Errors errors) throws Exception {

		LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;

		// Validation For Panchayat & Traditional Combo Fields
		if (lbForm.getLgd_lbCategory().equalsIgnoreCase("P")
				|| lbForm.getLgd_lbCategory().equalsIgnoreCase("T")) {
			boolean checkType = validateLBTypeList(lbForm);
			if (!checkType){
				throw new Exception();
			}	
			if (lbForm.getLocalBodyNameEnglishList() != null
					&& !lbForm.getLocalBodyNameEnglishList().trim().isEmpty()
					&& Integer.parseInt(lbForm.getLocalBodyNameEnglishList()) > 0) {
				boolean returnValue = validateViewLBDistP(lbForm);
				if (!returnValue){
					throw new Exception();
				}	
			}

			if (lbForm.getLocalBodyNameEnglishListSource() != null
					&& !lbForm.getLocalBodyNameEnglishListSource().trim().isEmpty()
					&& Integer.parseInt(lbForm
							.getLocalBodyNameEnglishListSource()) > 0){
				validateLBInterPList(lbForm);
			}	
		}

		if (lbForm.getLgd_lbCategory().equalsIgnoreCase("U")) {
			// Validation For Urban Combo Fields
			boolean checkType = validateLBTypeList(lbForm);
			if (!checkType){
				throw new Exception();
			}	
		}	
	}

	private boolean validateLBTypeList(LocalGovtBodyForm lbForm)
			throws Exception {
		try {
			List<LocalbodyforStateWise> localBodyTypelist = lbForm
					.getLocalBodyTypelist();
			String lbTypeName[] = lbForm.getLgd_LBTypeName().split(":");
			for (LocalbodyforStateWise lbStateW : localBodyTypelist) {
				if (lbTypeName.length > 1
						&& lbStateW.getLocalBodyTypeCode() == Integer
								.parseInt(lbTypeName[0])
						&& lbStateW.getLevel().contentEquals(lbTypeName[1])
						&& lbStateW.getCategory().contentEquals(lbTypeName[2])){
					return true;
				}	
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

/*	public boolean validateLBTypeUrban(LocalGovtBodyForm lbForm)
			throws Exception {
		try {
			List<LocalbodyforStateWise> localBodyTypelist = lbForm
					.getLocalBodyTypelist();

			for (LocalbodyforStateWise lbStateW : localBodyTypelist) {
				if (lbStateW.getLocalBodyTypeCode() == Integer.parseInt(lbForm
						.getLgd_LBTypeName()))
					return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}*/

	private boolean validateViewLBDistP(LocalGovtBodyForm lbForm) {
		try {
			List<LocalbodyListbyState> districtPanchayatList = lbForm
					.getDistrictPanchayatList();

			for (LocalbodyListbyState lbStateW : districtPanchayatList) {
				if (lbForm.getLocalBodyNameEnglishList() != null
						&& !lbForm.getLocalBodyNameEnglishList().isEmpty()
						&& lbStateW.getLocalBodyCode() == Integer
								.parseInt(lbForm.getLocalBodyNameEnglishList())) {
					return true;
				}
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return false;
	}

	private void validateLBInterPList(LocalGovtBodyForm lbForm)
			throws Exception {
		try {

			  comboFillingService
					.getComboValuesforApp('L', "L", Integer.parseInt(lbForm
							.getLocalBodyNameEnglishList()), Integer
							.parseInt(lbForm
									.getLocalBodyNameEnglishListSource()));

		} catch (Exception e) {
			throw e;
		}
		 
	}

}
