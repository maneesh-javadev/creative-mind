package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.StateDataForm;
import in.nic.pes.lgd.forms.StateForm;
import in.nic.pes.lgd.service.CommonService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("StateValidator")
public class StateValidator implements Validator {
	
	@Autowired
	private CommonService commonService;
	

	
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return StateForm.class.isAssignableFrom(clazz);
		
	}
	
	
	
	@SuppressWarnings("unused")
	public void validateCreateState(Object object, Errors errors)throws Exception {
		StateForm stateForm=(StateForm)object;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		String stateNameEnglish=(stateForm.getDistrictNameInEn()!=null)?stateForm.getDistrictNameInEn().trim():null;
		String shortName=(stateForm.getShortName()!=null)?stateForm.getShortName().trim():null;
		Character stateOrUt=stateForm.getStateOrUt();
		String census2001=(stateForm.getCensus2011Code()!=null)?stateForm.getCensus2011Code().trim():null;
		String headquterNameEnglish=(stateForm.getHeadquarterName()!=null)?stateForm.getHeadquarterName().trim():null;
		String headquterNameLocal=(stateForm.getHeadquarterNameLocal().trim()!=null)?stateForm.getHeadquarterNameLocal().trim():null;
		String contributeState=(stateForm.getSTATENAMEENGLISH()!=null)?stateForm.getSTATENAMEENGLISH().trim():null;
		String contributeDistrict=(stateForm.getDistrictNameEnglish()!=null)?stateForm.getDistrictNameEnglish().trim():null;
		if(!(stateOrUt=='S' ||  stateOrUt=='U')){
			errors.rejectValue("stateOrUt","error.ms", "Please Select State or UT");
		}
		if(!(stateNameEnglish!=null && !stateNameEnglish.isEmpty())){
			errors.rejectValue("districtNameInEn","error.ms","Name of new State (In English) required");
		}else if(!customValidator.checkforAlphabetsandSpace(stateNameEnglish)) {
			errors.rejectValue("districtNameInEn","error.ms", "State name field contains invalid characters.Please use A-Z or a-z or space.");
			}else if(stateNameEnglish.length()>50){
						errors.rejectValue("districtNameInEn","error.ms","State Name In Englsih can contains up to 50 Characters only");
						}else if(commonService.existEntityName(null, 'S', stateNameEnglish)){
							errors.rejectValue("districtNameInEn","error.alredyExist.stateNameEnglish");
						}
		
		if(shortName!=null && !shortName.isEmpty()) 
		{
			if(!customValidator.checkforAlphabetsandSpace(shortName)){
				errors.rejectValue("shortName", "error.ms", "Short Name field contains invalid characters.Please use A-Z or a-z");
			}else if(shortName.length()>2){
				errors.rejectValue("shortName", "error.ms", "Short Name can contains up to 2 Characters only");
				} 
		}
		
		if(census2001!=null && !census2001.isEmpty()){
			if(!customValidator.checkforOnlyNumeric(census2001)){
			errors.rejectValue("census2011Code","Census 2001 field contains invalid characters.Please use 0 to 9.");
			}
				if(census2001.length()>6){
					errors.rejectValue("census2011Code","Census 2001 Code can contains up to 6 Characters only");
				}
		}
		if(headquterNameEnglish!=null && !headquterNameEnglish.isEmpty()){
			if (!customValidator.checkforAlphabetsandSpace(headquterNameEnglish)) {
				errors.rejectValue("headquarterName", "error.ms", "Headquarter Name(In English) contains invalid characters.Please use A-Z or a-z or space");
			}else if(headquterNameEnglish.length()>50){
					errors.rejectValue("headquarterName","error.ms", "Headquarter Name(In English) can contains up to 50 Characters only");
				}
		}
		
		if(headquterNameLocal!=null && !headquterNameLocal.isEmpty()){
			if (!customValidator.validateSpecialCharacters(headquterNameLocal)) {
				errors.rejectValue("headquarterNameLocal", "error.ms", "Headquarter Name(In Local) contains invalid characters");
			}else if(headquterNameLocal.length()>50){
					errors.rejectValue("headquarterNameLocal","error.ms", "Headquarter length(In Local) can contains up to 50 Characters only");
				}
		}
		
		if(!(contributeState!=null && !contributeState.isEmpty())) 
		{
			errors.rejectValue("STATENAMEENGLISH","error.ms", "Kindly Select Contributing State List");
		}else if(!((contributeState.indexOf("PART")>-1) || (contributeState.indexOf("FULL")>-1 && contributeState.indexOf("FULL")!=contributeState.lastIndexOf("FULL")))){
			errors.rejectValue("STATENAMEENGLISH","error.ms", "You Can't Make the New  State by Shifting Only One Full State. Kindly Select One More Contributing State.!");
		}else
		if(!(contributeState.indexOf("FULL")>-1 && contributeState.indexOf("FULL")!=contributeState.lastIndexOf("FULL")))
		{
			if(!(contributeDistrict!=null && !contributeDistrict.isEmpty())) 
			{
				errors.rejectValue("DistrictNameEnglish","error.ms", "Kindly Select At Least One Contributing District List");
			}
		}
		
	}
	
	public StateForm validateChange(Object object, Errors errors) throws Exception {
		StateForm stateForm = (StateForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		
		List<StateDataForm> stateDataForm=stateForm.getListStateDetails();
		if(!stateDataForm.isEmpty())
		{
			StateDataForm element=stateDataForm.get(0);
			String stateNameEngch =element.getStateNameEnglishch();
			String stateNameEng=element.getStateNameEnglish();
			/*String strNameAlias = element.getAliasEnglish();*/
			String strNameLocal =element.getStateNameLocal();
			/*String strNameLocalAlias = element.getAliasLocal();*/
			String sortNameEng=element.getShortName();
			char stateOrUt=element.getStateOrUt();
			char stateOrUtch=element.getStateOrUtch();
			
			if(stateNameEngch=="" || stateNameEngch.isEmpty())
			{
					errors.rejectValue("stateNameEnglishch", "error.blank.stateNameInEnch");
				stateForm.setErrorflag(2);
				
			}
			else 	if (!customValidator.checkforAlphabetsandSpace(stateNameEngch)) {
				errors.rejectValue("stateNameEnglishch", 
						"Error.data.StateNameEng");
			}
			else if((stateNameEngch.trim().equalsIgnoreCase(stateNameEng)) && (stateOrUt==stateOrUtch))
			{
				errors.rejectValue("errorflag", 
						"error.change.commonAlert");
				stateForm.setErrorflag(1);
			}
			else
				if(commonService.existEntityName(null, 'S', stateNameEngch) && (!stateNameEngch.trim().equalsIgnoreCase(stateNameEng)))
				{
					errors.rejectValue("stateNameEnglishch", 
							"error.alredyExist.stateNameEnglish");
				}
			
			
			
			
			
			
				if(sortNameEng!=null)
				{
					if (!sortNameEng.isEmpty()
							&& !customValidator.checkforAlphabetsandSpace(sortNameEng)) {
						errors.rejectValue("shortName", 
								"Error.data.StateSortNameEng");
					}
					if(sortNameEng.length()>2){
						errors.rejectValue("shortName", "error.ms", "Short Name length Must be two charcters");
					}
				}
				
			/*	if (!strNameAlias.isEmpty()
						&& !customValidator.checkforAlphabetsandSpace(strNameAlias)) {
					errors.rejectValue("aliasEnglish", "error.ms",
							"State Name Alias contains invalid characters");
				}*/
				if (!strNameLocal.isEmpty()
						&& !customValidator.validateSpecialCharacters(strNameLocal)) {
					errors.rejectValue("stateNameLocal", 
							"Error.data.stateNameLoc");
				}
			/*	if (!strNameLocalAlias.isEmpty()
						&& !customValidator
								.validateSpecialCharacters(strNameLocalAlias)) {
					errors.rejectValue("aliasLocal", "error.ms",
							"State Name Alias Local contains invalid characters");
				}*/
				
				
				
			}
		
			
			
			
			// ValidationUtils.rejectIfEmptyOrWhitespace(errors,"districtNameEnglishch",
			// "DISTRICTNAME.REQUIRED", "District Name in English is required");
		
		return stateForm;
	}

	public void validateCorrection(Object object, Errors errors) {

		StateForm stateForm = (StateForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		if (!stateForm.getListStateDetails().isEmpty()) {
			StateDataForm stateDataForm = stateForm.getListStateDetails().get(0);
			String strName = stateDataForm.getHeadquarterName();
			String govtOrderConfig = stateForm.getGovtOrderConfig();
			//String strNameAlias = stateDataForm.getAliasEnglish();
			Date today = new Date();
			String strNameLocal = stateDataForm.getHeadquarterNameLocal();
			String sortNameEng=stateDataForm.getShortName();
			//String strNameLocalAlias = stateDataForm.getAliasLocal();

			/*if (strNameAlias != null) {
				if (!strNameAlias.isEmpty() && !customValidator.checkforAlphabetsandSpace(strNameAlias)) {
					errors.rejectValue("aliasEnglish", "error.ms", "State Name Alias contains invalid characters");
				}
			}*/

			if(sortNameEng!=null )
			{
				if (!customValidator.checkforAlphabetsandSpace(sortNameEng) && !sortNameEng.isEmpty()) {
					errors.rejectValue("shortName", "error.ms", "Short Name contains invalid characters");
				}
				
				if(sortNameEng.length()>2){
					errors.rejectValue("shortName", "error.ms", "Short Name length Must be two charcters");
				}
			}
			
			if (strNameLocal != null) {
				if (!strNameLocal.isEmpty() && !customValidator.validateSpecialCharacters(strNameLocal)) {
					errors.rejectValue("headquarterNameLocal", "error.ms", "Headquarter Name Local contains invalid characters");
				}
			}

			/*if (strNameLocalAlias != null) {
				if (!strNameLocalAlias.isEmpty() && !customValidator.validateSpecialCharacters(strNameLocalAlias)) {
					errors.rejectValue("aliasLocal", "error.ms", "State Name Alias Local contains invalid characters");
				}

			}*/
			if (strName != null && (!strName.equals(""))) {
				if (!customValidator.checkforAlphabetsandSpace(strName)) {
					errors.rejectValue("headquarterName", "error.ms", "Headquarter Name contains invalid characters");
				}
			}

			String strFileCount=stateForm.getGovtfilecount();
			Integer fileCount=0;
			if(strFileCount!=null)
			{
				fileCount=Integer.parseInt(strFileCount);
			}
			if (!(stateDataForm.getOrderNocr().equalsIgnoreCase("")) || stateDataForm.getOrderDatecr() != null || stateDataForm.getOrdereffectiveDatecr() != null || stateDataForm.getGazPubDatecr() != null || (stateForm.getAttachFile1()!=null)) {
				if (!(stateDataForm.getOrderNocr().equalsIgnoreCase("")) || stateDataForm.getOrderDatecr() != null || stateDataForm.getOrdereffectiveDatecr() != null || stateDataForm.getGazPubDatecr() != null || (!stateForm.getAttachFile1().get(0).isEmpty())) {
					if (stateForm.getOrderNo().isEmpty()) {
						try {
							/*Modified changed by kirandeep on 16/03/2015*/
							errors.rejectValue("orderNocr", "Msg.ORDERNO");
						} catch (Exception e) {
							errors.rejectValue("orderNocr", "Msg.ORDERNO");
						}

					} else if (!customValidator.checkforOrderNum(stateForm.getOrderNo())) {
						errors.rejectValue("orderNocr", "error.valid.ORDERNO");
					}

					if (stateForm.getOrderDate() == null) {
						try {
							errors.rejectValue("orderDatecr", "Msg.ORDERDATE");
						} catch (Exception e) {
							errors.rejectValue("orderDatecr", "Msg.ORDERDATE");
						}
					} else if (stateForm.getOrderDate() != null) {
						if (!customValidator.checkforDate(stateForm.getOrderDate())) // will
																						// only
																						// matches
																						// alphabets
						{
							errors.rejectValue("orderDatecr", "error.valid.ORDERDATE");
						} else if (stateForm.getOrderDate().after(today) && !stateForm.getOrderDate().equals(today)) {
							errors.rejectValue("orderDatecr", "error.INCORECT.ORDERDATE");
						}
					}

					if (stateForm.getOrdereffectiveDate() == null) {
						try {
							errors.rejectValue("ordereffectiveDatecr", "Msg.EFFECTIVEDATE");
						} catch (Exception e) {
							errors.rejectValue("ordereffectiveDatecr", "Msg.EFFECTIVEDATE");
						}

					} else if (stateForm.getOrdereffectiveDate() != null) {
						if (!customValidator.checkforDate(stateForm.getOrdereffectiveDate())) // will
																								// only
																								// matches
																								// alphabets
						{
							errors.rejectValue("ordereffectiveDatecr", "error.valid.EFFECTIVEDATE");
						}

						if (stateForm.getOrdereffectiveDate().after(new Date())) {
							errors.rejectValue("ordereffectiveDatecr", "error.valid.EFFECTIVEFUTUREDATE");
						}

						else if (stateForm.getOrdereffectiveDate().before(stateForm.getOrderDate()) && !stateForm.getOrdereffectiveDate().equals(stateForm.getOrderDate())) {
							errors.rejectValue("ordereffectiveDatecr", "error.INCORECT.EFFECTIVEDATE");
						}

					}
					
					if(fileCount<=0)
					{
					if (govtOrderConfig.equals("govtOrderUpload")) {

						if (stateForm.getAttachFile1().isEmpty()) {
							try {
								errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
							} catch (Exception e) {
								errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
							}

						}
						else if(stateForm.getAttachFile1().get(0).isEmpty())
						{
							errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
						}

					} else if (govtOrderConfig.equals("govtOrderGenerate")) {

						if (stateForm.getTemplateList() != null) {
							int templateCode = Integer.parseInt(stateForm.getTemplateList());
							if (templateCode < 1) {
								errors.rejectValue("templateList", "Msg.GovtOrderGenerate");
							}
						}
					}
					}
				
				}
	
			}

		}
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors,"districtNameEnglishch",
		// "DISTRICTNAME.REQUIRED", "District Name in English is required");
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		//Modify State
		StateForm stateForm=(StateForm)object;
		String govtOrderConfig = stateForm.getGovtOrderConfig();
		
		Date today = new Date();
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();

		 
			if(stateForm.getOrderNo().isEmpty())
			{			
				try {
					errors.rejectValue("orderNo2","Msg.ORDERNO");	
				} catch (Exception e) {
					errors.rejectValue("orderNo2","Msg.ORDERNO");	
				}			
								
			}
			else if(!customValidator.checkforOrderNum(stateForm.getOrderNo()))
			{
				errors.rejectValue("orderNo1","error.valid.ORDERNO");	
			}
	 		
			if(stateForm.getOrderDate()==null)
			{
				try {
					errors.rejectValue("orderDate2","Msg.ORDERDATE");		
				} catch (Exception e) {
					errors.rejectValue("orderDate2","Msg.ORDERDATE");		
				}			
			}	
			else if(stateForm.getOrderDate() != null)
			{
			  if (!customValidator.checkforDate(stateForm.getOrderDate())) // will only matches alphabets
				 {
					errors.rejectValue("orderDate1", "error.valid.ORDERDATE");
				 }
			  else if(stateForm.getOrderDate().after(today) && !stateForm.getOrderDate().equals(today))
				{
					errors.rejectValue("orderDate1", "error.INCORECT.ORDERDATE");
				}
			}
			
			if(stateForm.getOrdereffectiveDate()==null)
			{		
				try {
					errors.rejectValue("effectiveDate2","Msg.EFFECTIVEDATE");				
				} catch (Exception e) {
					errors.rejectValue("effectiveDate2","Msg.EFFECTIVEDATE");			
				}		
							
			}
			else if(stateForm.getOrdereffectiveDate() != null)
			{
				 if (!customValidator.checkforDate(stateForm.getOrdereffectiveDate())) // will only matches alphabets
				 {
					errors.rejectValue("effectiveDate1", "error.valid.EFFECTIVEDATE");
				 }
				 
				 if(stateForm.getOrdereffectiveDate().after(new Date())){
					 errors.rejectValue("effectiveDate1", "error.valid.EFFECTIVEFUTUREDATE"); 
				 }
				 
				 else if(stateForm.getOrdereffectiveDate().before(stateForm.getOrderDate()) && !stateForm.getOrdereffectiveDate().equals(stateForm.getOrderDate()))
				{
					errors.rejectValue("effectiveDate1", "error.INCORECT.EFFECTIVEDATE");
				}
				 
			}
		
			if(govtOrderConfig.equals("govtOrderUpload"))
			{
			
				/*if(stateForm.getGazPubDate() != null)
				{ 
					if (!customValidator.checkforDate(stateForm.getGazPubDate())) // will only matches alphabets
					 {
						errors.rejectValue("gazPubDate2", "error.valid.GAZPUBDATE");
					 }
					else if(stateForm.getGazPubDate().before(stateForm.getOrderDate()) && !stateForm.getGazPubDate().equals(stateForm.getOrderDate()))				 
					{
						errors.rejectValue("gazPubDate2", "error.INCORECT.GAZPUBDATE");
					}
				}*/
				
				if(stateForm.getAttachFile1().isEmpty())
				{		
					try {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
					} catch (Exception e) {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");		
					}
						
					
				}
			
				/*
				else if(stateForm.getAttachFile1().size() >0 && stateForm.getAttachFile1().get(0).getOriginalFilename() == null || stateForm.getAttachFile1().get(0).getOriginalFilename().equals(""))
				{
					errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
				}*/
			}
			else if(govtOrderConfig.equals("govtOrderGenerate"))
			{ 
			
				if(stateForm.getTemplateList() != null)
				{
					int templateCode = Integer.parseInt(stateForm.getTemplateList());
					if(templateCode <1)
					{
						errors.rejectValue("templateList","Msg.GovtOrderGenerate");	
					}
				}
			}
			
		}
	}


