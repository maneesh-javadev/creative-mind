package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.dao.OrganizationDAO;
import in.nic.pes.lgd.forms.MinistryForm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("ministryValidator")
public class MinistryValidator implements Validator {
	
	private static final Logger log = Logger.getLogger(MinistryValidator.class);

	@Autowired
	private OrganizationDAO organizationDAO;

	public boolean supports(Class<?> clazz) {
		return MinistryForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		MinistryForm ministryForm = (MinistryForm) object;

		String strName = ministryForm.getMinistryName();
		if (ministryForm.getMinistryName().isEmpty()) {
			errors.rejectValue("ministryName", "error.blank.ministryName");
		}

		else if (!customValidator.checkforAlphabetsandSpace(strName)) {
			errors.rejectValue("ministryName1", "error.valid.ministryName");
		} else
			try {
				if (checkExistMinistryForm(ministryForm.getMinistryName())) {
					errors.rejectValue("ministryName", "error.exist.ministryName");
				}
			} catch (Exception e) {
				errors.rejectValue("ministryName", "error.exist.ministryName");
			}

		String strShortName = ministryForm.getShortministryName();

		if (strShortName != null && !strShortName.isEmpty()) {
			if (!customValidator.checkforShortName(strShortName)) {
				errors.rejectValue("shortministryName", "error.common.short.name");
			}
		}

	}

	public void validateChange(Object object, Errors errors) {

		try {
			MinistryForm ministryForm = (MinistryForm) object;
			//String strName3 = ministryForm.getOrderNo();
			// if (ministryForm.isCorrection() == true) {
			CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
			try {
				if (ministryForm.getMinistryNamecr().isEmpty()) {
					try {
						errors.rejectValue("ministryNamecr", "MINISTRYNAME.REQUIRED");
					} catch (Exception e) {
						errors.rejectValue("ministryNamecr", "MINISTRYNAME.REQUIRED");
					}

				} else {
					//added by Maneesh Kumar on 29-05-2013
					String ministryNamech=ministryForm.getMinistryNamecr();
					String ministryName=ministryForm.getMinistryName();
					if(!ministryName.equalsIgnoreCase(ministryNamech))
					{
					// added by sushil on 21-05-2013
						if(checkExistMinistryForm(ministryForm.getMinistryNamecr())) {
							errors.rejectValue("ministryNamecr", "error.exist.ministryName");
						}
					}
				}
				if (!customValidator.checkforAlphabetsandSpace(ministryForm.getMinistryNamecr())) {
					errors.rejectValue("ministryNamecr", "error.ms", "Ministry Name contains invalid characters");
				}
				
				String strShortName = ministryForm.getShortministryName();

				if (strShortName != null && !strShortName.isEmpty()) {
					if (!customValidator.checkforShortName(ministryForm.getShortministryName())) {
						errors.rejectValue("shortministryName", "error.ms", "Ministry Name contains invalid characters");
					}
				}
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

			/*
			 * } else if (ministryForm.isCorrection() == false) {
			 * CustomRegexValidator customValidator = CustomRegexValidator
			 * .getInstance(); String strName = ministryForm.getMinistryName();
			 * if (!customValidator.checkforAlphabetsandSpace(strName)) {
			 * errors.rejectValue("ministryName", "error.ms",
			 * "Ministry Name contains invalid characters"); } if
			 * (ministryForm.getMinistryName().isEmpty()) { try {
			 * errors.rejectValue("ministryName", "MINISTRYNAME.REQUIRED"); }
			 * catch (Exception e) { errors.rejectValue("ministryName",
			 * "MINISTRYNAME.REQUIRED"); }
			 * 
			 * }
			 * 
			 * if (ministryForm.getOrderNo().isEmpty() ||
			 * ministryForm.getOrderNo() == null ||
			 * ministryForm.getOrderNo().toString().equals("0")) { try {
			 * errors.rejectValue("orderNo", "ORDERNO.REQUIRED"); } catch
			 * (Exception e) { errors.rejectValue("orderNo",
			 * "ORDERNO.REQUIRED"); }
			 * 
			 * } else if (!customValidator.checkforOrderNum(strName3)) {
			 * errors.rejectValue("orderNo", "error.ms",
			 * "Order Number contains invalid characters"); }
			 * 
			 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderNocr",
			 * "orderNo.required", "Order Number is required.");
			 * 
			 * if (ministryForm.getOrderDate() == null) { try {
			 * errors.rejectValue("orderDate", "orderDate.required"); } catch
			 * (Exception e) { errors.rejectValue("orderDate",
			 * "orderDate.required"); }
			 * 
			 * } else if (!customValidator.checkforDate(ministryForm
			 * .getOrderDate())) // will // only // matches // alphabets {
			 * errors.rejectValue("orderDate", "error.valid.ORDERDATE"); } if
			 * (ministryForm.getOrdereffectiveDate() == null) { try {
			 * errors.rejectValue("ordereffectiveDate",
			 * "ordereffectiveDate.required"); } catch (Exception e) {
			 * errors.rejectValue("ordereffectiveDate",
			 * "ordereffectiveDate.required"); }
			 * 
			 * } else if (!customValidator.checkforDate(ministryForm
			 * .getOrdereffectiveDate())) // will // only // matches //
			 * alphabets { errors.rejectValue("ordereffectiveDate",
			 * "error.valid.EFFECTIVEDATE"); }
			 * if(ministryForm.getAttachedFile().isEmpty()) { try {
			 * errors.rejectValue("attachedFile","Msg.UPLOADGOVTORDER"); } catch
			 * (Exception e) {
			 * errors.rejectValue("attachedFile","Msg.UPLOADGOVTORDER"); }
			 * 
			 * 
			 * } else if(ministryForm.getAttachedFile().size() >0 &&
			 * ministryForm.getAttachedFile().get(0).getOriginalFilename() ==
			 * null ||
			 * ministryForm.getAttachedFile().get(0).getOriginalFilename(
			 * ).equals("")) {
			 * errors.rejectValue("attachedFile","Msg.UPLOADGOVTORDER"); } }
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
	}

	private boolean checkExistMinistryForm(String ministryName) throws Exception {

		int count = organizationDAO.getRecordsforMinistry(ministryName);
		if (count >= 1) {

			return true;
		} else {
			return false;
		}
	}

	
	public void viewValidateMinistry(Object object, Errors errors) {
		MinistryForm ministryForm = (MinistryForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		if(ministryForm.getMinistryName() !=null && !ministryForm.getMinistryName().equals("")){
			if (!customValidator.checkforAlphabetsandSpace(ministryForm
					.getMinistryName())) {
				errors.rejectValue("ministryName", "error.ms",
						"This field contains invalid characters.Please use A-Z or a-z");
			}
		}
		if((ministryForm.getCode() != null && !ministryForm.getCode().equals(""))){
			if (!customValidator.checkforOnlyNumeric(ministryForm
					.getCode())) {
				errors.rejectValue("code", "error.ms",
						"This field contains invalid characters.Please use 0 to 9");

			}
		}
	}
}
