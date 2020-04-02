package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.common.CustomRegexValidator;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("orgGovtOrderValidator")
public class GovtOrderValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return GovernmentOrder.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		GovernmentOrder govtForm = (GovernmentOrder) object;
		String govtOrderConfig = govtForm.getGovtOrderConfig();
		Date today = new Date();
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();

		if (govtForm.getOrderNo().isEmpty()) {
			try {
				errors.rejectValue("orderNo", "Msg.ORDERNO");
			} catch (Exception e) {
				errors.rejectValue("orderNo", "Msg.ORDERNO");
			}

		} else if (!customValidator.checkforOrderNum(govtForm.getOrderNo())) {
			errors.rejectValue("orderNo", "error.valid.ORDERNO");
		}

		if (govtForm.getOrderDate() == null) {
			try {
				errors.rejectValue("orderDate", "Msg.ORDERDATE");
			} catch (Exception e) {
				errors.rejectValue("orderDate", "Msg.ORDERDATE");
			}
		} else if (govtForm.getOrderDate() != null) {
			if (!customValidator.checkforDate(govtForm.getOrderDate())) {
				errors.rejectValue("orderDate", "error.valid.ORDERDATE");
			} else if (govtForm.getOrderDate().after(today) && !govtForm.getOrderDate().equals(today)) {
				errors.rejectValue("orderDate", "error.INCORECT.ORDERDATE");
			}
		}

		if (govtForm.getEffectiveDate() == null) {
			try {
				errors.rejectValue("effectiveDate", "Msg.EFFECTIVEDATE");
			} catch (Exception e) {
				errors.rejectValue("effectiveDate", "Msg.EFFECTIVEDATE");
			}

		} else if (govtForm.getEffectiveDate() != null) {
			if (!customValidator.checkforDate(govtForm.getEffectiveDate())) {
				errors.rejectValue("effectiveDate", "error.valid.EFFECTIVEDATE");
			}
		}

		if (govtOrderConfig.equals("govtOrderUpload")) {
			if (govtForm.getAttachFile1() == null) {
					errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
			}	else if (govtForm.getAttachFile1().get(0).getFileItem().getName().isEmpty()) {
					errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
			}	
		} else if (govtOrderConfig.equals("govtOrderGenerate")) {
			if (govtForm.getTemplateList() != null) {
				int templateCode = Integer.parseInt(govtForm.getTemplateList());
				if (templateCode < 1) {
					errors.rejectValue("templateList", "Msg.GovtOrderGenerate");
				}
			}
		}

	}
}