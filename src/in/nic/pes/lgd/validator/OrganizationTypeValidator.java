package in.nic.pes.lgd.validator;


import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.dao.OrganizationDAO;
import in.nic.pes.lgd.forms.OrganizationTypeForm;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component("organizationTypeValidator")
public class OrganizationTypeValidator implements Validator{
	
	@Autowired(required=false)
	private OrganizationDAO organizationDAO;


	private CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
	public boolean supports(Class<?> clazz) {
		return OrganizationTypeForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors) {
		
		
		OrganizationTypeForm orgTypeForm = (OrganizationTypeForm) object;
		
		if (orgTypeForm.getOrgType().isEmpty()) {
			try {
				errors.rejectValue("orgType", "Organization Type must be enter");
			} catch (Exception e) {
				errors.rejectValue("orgType", "Organization Type must be enter");
			}
		}
		else
		{
			if(!customValidator.checkforAlphaNumericandSpace(orgTypeForm.getOrgType()))
			{
				errors.rejectValue("orgType", "error.valid.orgTypeName");
			}
			
			
			
		}
	}
	
	
public void validateforModify(Object object, Errors errors) throws Exception {
		
		
		OrganizationTypeForm orgTypeForm = (OrganizationTypeForm) object;
		
		if (orgTypeForm.getOrgType().isEmpty()) {
			try {
				errors.rejectValue("orgType", "Organization Type must be enter");
			} catch (Exception e) {
				errors.rejectValue("orgType", "Organization Type must be enter");
			}
		}
		else
		{
			if(!customValidator.checkforAlphaNumericandSpace(orgTypeForm.getOrgType()))
			{
				errors.rejectValue("orgType", "error.valid.orgTypeName");
			}
			
			if(organizationDAO.checktOrganizationTypeNameExist(orgTypeForm.getOrgType()))
					{
				errors.rejectValue("orgType","error.ms", "Organization Type alrady exist");
					}
			
			
		}
	}
	
	public boolean isStringLetter(String str)
	{
		boolean test=true;
		for(int i=0;i<str.length();i++)
		{
			char c=str.charAt(i);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c==' ') 
			{
				test=true;
			}
			else
			{
				test=false;
			}
		}
		return test;
	}

	@Override
	public BeanDescriptor getConstraintsForClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validate(T arg0, Class<?>... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validateProperty(T arg0,
			String arg1, Class<?>... arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validateValue(Class<T> arg0,
			String arg1, Object arg2, Class<?>... arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
