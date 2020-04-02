package com.cmc.lgd.localbody.aspects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.rules.LocalBodyConstant;

@Aspect
public class LocalbodyAspect {

	@Autowired
	private Validator validator;
	
	/*@Before("execution(* com.cmc.tracker.controllers.AOPDroolsDemoController.compileAOP(..)) && args(request, response)")
	public void logBefore(JoinPoint joinPoint, HttpServletRequest request, HttpServletResponse response) {
	}*/
	
	@Pointcut("execution(* com.cmc.lgd.localbody.controllers.RestructuredLocalbodyController.buildLocalBody(..))")
	private void lbPointCut() {}// the pointcut signature
	
	@Before("lbPointCut() && args(request, model, localBodyForm, binding)")
	public void lbBeforeAdviceExecution(JoinPoint joinPoint, 
										HttpServletRequest request, 
										Model model,
										LocalBodyForm localBodyForm,
										BindingResult binding ) throws Exception {
		String creationType = localBodyForm.getLocalBodyCreationType();
		if(!LocalBodyConstant.isValidCreationType(creationType)){
			throw new Exception("Request has been intrupted, Requested action is not available.");
		}
		localBodyForm.setOperationCode(LocalBodyConstant.OPERATION_CODE.get(creationType, null));
		localBodyForm.setFlagCode(LocalBodyConstant.FLAG_CODE.getFlagCode(creationType, null));
		String lbTypePanchayat = localBodyForm.getLocalBodyTypePanchayat();
	    if( StringUtils.isNotBlank(lbTypePanchayat)){
	    	Integer localBodyTypeCode = Integer.parseInt(lbTypePanchayat.split("\\_")[0]);
	    	localBodyForm.setLocalBodyTypeCode(localBodyTypeCode);
	    }
	    
	    //applicant.setValid(true);
	    //applicant.setAge(29);
	    /*ApplicationForm applicationForm = new ApplicationForm(1, new Date());
		Set<ConstraintViolation<ApplicationForm>> violations = validator.validate(applicationForm);
		Assert.assertNotNull(violations);
		Assert.assertEquals(Integer.valueOf(1), Integer.valueOf(violations.size()));*/
	}
	
	
}
