package in.nic.pes.lgd.section.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.pes.lgd.section.form.SectionForm;
import in.nic.pes.lgd.section.service.SectionService;



/**
 * 
 * @author Maneesh Kumar @since 10Apr2016
 *
 */
public class SectionValidator implements Validator{

	@Autowired
	private SectionService sectionService;
	
	
	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		SectionForm sectionForm=(SectionForm)target;
		
		validateGeneralDetails(sectionForm,errors);
		
		if((SectionConstant.IS_CENTER.toString().equals(sectionForm.getIsCenterorstate()))){
			validateOrganisationDetails(sectionForm,errors);
		}else{
			String entityType=sectionForm.getParentType();
			if(validateEntityType(entityType)){
				errors.rejectValue("parentType", "error.section.select.entity.type");
			}
			else if((SectionConstant.PARENT_TYPE_LOCALBODY.toString()).equals(entityType)){
				validateLocalbodyDetails(sectionForm,errors);
			}else if((SectionConstant.PARENT_TYPE_ORGANIZATION.toString()).equals(entityType)){
				validateOrganisationDetails(sectionForm,errors);
			}
		}
		
	}
	
	public void validateView(Object target, Errors errors) {
		SectionForm sectionForm=(SectionForm)target;
		if((SectionConstant.IS_CENTER.toString().equals(sectionForm.getIsCenterorstate()))){
			validateCenterOrganisationDetails(sectionForm,errors);
		}else{
			String entityType=sectionForm.getParentType();
			if(validateEntityType(entityType)){
				errors.rejectValue("parentType", "error.section.select.entity.type");
			}else if((SectionConstant.PARENT_TYPE_LOCALBODY.toString()).equals(entityType)){
				validateLocalbodyDetailsForView(sectionForm,errors);
			}
		}
	}
	
	
	public void validateUpdate(Object target, Errors errors) {
		SectionForm sectionForm=(SectionForm)target;
		validateSectionNameEnglish(sectionForm,errors,true);
		
	}
	
	public void validateLocalbodyDetailsForView(SectionForm sectionForm,Errors errors){
		if(StringUtils.isBlank(sectionForm.getLbTypeHierarchy())){
			errors.rejectValue("lbTypeHierarchy", "Error.heirarchyselect", "Please Select atleast Hierarchy.");
		}
		if(StringUtils.isBlank(sectionForm.getLocalBodyType())){
			errors.rejectValue("localBodyType", "error.select.LBTYPE");
		}else{
			
			String lbTypeArray[] = sectionForm.getLocalBodyType().split("_");
			String parent  = lbTypeArray[2];
			String category  = lbTypeArray[4];
			
			if(validateLbHierarchy(sectionForm.getLocalBodyLevelCodes(),parent,category,true)){
				errors.rejectValue("localBodyLevelCodes", "error.select.lb");
			}
		}
		
		
		
	}
	
	public void validateGeneralDetails(SectionForm sectionForm,Errors errors){
		
		validateSectionNameEnglish(sectionForm,errors,false);
		
	}
	
	
	public void validateSectionNameEnglish(SectionForm sectionForm,Errors errors,boolean isupdate){
		String SectionNameEng=isupdate?sectionForm.getSectionNameEnglishChange():sectionForm.getSectionNameEnglish();
		String errorField=isupdate?"sectionNameEnglishChange":"sectionNameEnglish";
		if (StringUtils.isBlank(SectionNameEng)) {
			errors.rejectValue(errorField, "error.blank.sectionNameInEn");
		}else if(validateFirstLetterInitial(SectionNameEng.charAt(0))){
			errors.rejectValue(errorField, "error.first.letter.must.be.alphabet");
		}
		else if (unSupportedCharactersEnglish(SectionNameEng)) {
			errors.rejectValue(errorField, "Error.invalidchar");
		}
		else if (validateSpace(SectionNameEng)) {
			errors.rejectValue(errorField, "Error.invalidspace");
		}
		else if (SectionNameEng.length() > 200) {
			errors.rejectValue(errorField, "Error.Sizevalid", new String[] {"200"}, null);
		}
		
		else if(isupdate){
			if(SectionNameEng.toLowerCase().trim().equals(sectionForm.getSectionNameEnglish().toLowerCase().trim())){
				errors.rejectValue(errorField, "error.must.change.section.name.of.previous");
			}else{
				try{
					if(!validateSectionNameUnique(sectionForm)){
						errors.rejectValue(errorField, "Error.section.name.unique");
					}
					}catch(Exception e){
						errors.rejectValue(errorField, e.toString());
					}
			}
			
		}
	}
	
	public boolean validateSectionNameUnique(SectionForm sectionForm)throws Exception {
		
		return sectionService.sectionNameUniquewithParent(sectionForm.getSectionNameEnglishChange(), Integer.parseInt(sectionForm.getParentCode()),sectionForm.getParentType());
		
		
	}
	
	public boolean validateSpace(String SectionNameEng){
		Integer key;
		Integer spaceCount=0;
		for(int i=0;i<SectionNameEng.length();i++){
		 key=(int)SectionNameEng.charAt(i);
		 if(key==32)/* Space Key */
			{
				if(spaceCount>0){
					
					return true;
				}
				spaceCount++;
			}else {
				spaceCount=0;
			}
		}
		return false;
	}
	
	public boolean validateFirstLetterInitial(Character firstLetter){
		Integer key=(int)firstLetter;
		return(!(key>64 && key<91));
	}
	
	private boolean unSupportedCharactersEnglish(String inputValue){
	String regexPattern = "[#%&\\%&\\~\\!\\@\\$\\^\\*\\_\\+\\`\\=\\{\\}\\\\\\[\\\\\\]\\|\\\\:\\;\\'\\\"\\<\\>\\?\\\\]";
		return executeRegEx(regexPattern, inputValue);
	}
	
	private boolean executeRegEx(String regexPattern, String input){ 
		Pattern pattern 		= Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher 		= pattern.matcher(input);
		return matcher.find();
	}
	
	public boolean validateEntityType(String entityType){
		return(!( (SectionConstant.PARENT_TYPE_LOCALBODY.toString()).equals(entityType)         || 
				  (SectionConstant.PARENT_TYPE_ORGANIZATION.toString()).equals(entityType)      ));
	}
	
	public void validateLocalbodyDetails(SectionForm sectionForm,Errors errors){
		if(StringUtils.isBlank(sectionForm.getLbTypeHierarchy())){
			errors.rejectValue("lbTypeHierarchy", "Error.heirarchyselect", "Please Select atleast Hierarchy.");
		}
		if(StringUtils.isBlank(sectionForm.getLocalBodyType())){
			errors.rejectValue("localBodyType", "error.select.LBTYPE");
		}else{
			
			String lbTypeArray[] = sectionForm.getLocalBodyType().split("_");
			String parent  = lbTypeArray[2];
			String category  = lbTypeArray[4];
			
			if(validateLbHierarchy(sectionForm.getLocalBodyLevelCodes(),parent,category,false)){
				errors.rejectValue("localBodyLevelCodes", "error.select.lb");
			}else{
				String parentChildFlg=sectionForm.getLbSpcificFullRadio();
				if(StringUtils.isBlank(parentChildFlg)){
					errors.rejectValue("lbSpcificFullRadio", "error.choose.fullorpart");
				}else if(validateLbSpecificorFull(parentChildFlg)){
					errors.rejectValue("lbSpcificFullRadio", "error.not.valid.fullorpart");
				}else if ( ((SectionConstant.ENTITY_SPECIFIC.toString()).equals(parentChildFlg)) && (StringUtils.isBlank(sectionForm.getSelectedLbList()))  ) {
					errors.rejectValue("selectedLbList", "error.select.specificList");
					
				}
			}
		}
		
		
		
	}
	
	
	public void validateOrganisationDetails(SectionForm sectionForm,Errors errors){
		if((SectionConstant.IS_CENTER.toString()).equals(sectionForm.getIsCenterorstate()) && sectionForm.getOrgTypeCode()==null){
			errors.rejectValue("orgTypeCode", "Label.selOrgType");
		}
		
		String orgLevelCodes=sectionForm.getOrgLevelCodes();
		if(orgLevelCodes==null || (orgLevelCodes!=null && orgLevelCodes.length()<=0)){
			errors.rejectValue("orgLevelCodes", "error.select.specificList");
		}else{
			boolean flag=false;
			String orgLevelCodeArray[] = orgLevelCodes.split(",");
			Integer orgElementLen=(SectionConstant.IS_CENTER.toString()).equals(sectionForm.getIsCenterorstate())?Integer.parseInt(SectionConstant.CENTER_ORGANIZATION_ELEMENT_LENGTH.toString()):Integer.parseInt(SectionConstant.STATE_ORGANIZATION_ELEMENT_LENGTH.toString());
			if(orgLevelCodeArray.length<orgElementLen){
				errors.rejectValue("orgLevelCodes", "error.select.specificList");
				flag= true;
				
			}
			
			
			for(int i=0;i<orgLevelCodeArray.length;i++){
				if(StringUtils.isBlank(orgLevelCodeArray[i]))	{
					errors.rejectValue("orgLevelCodes", "error.select.specificList");
					flag= true;
					break;
				}
			}
			
			if(!flag){
				String parentChildFlg=sectionForm.getParentChildFlg();
				if(StringUtils.isBlank(parentChildFlg)){
					errors.rejectValue("orgSpcificFullRadio", "error.choose.fullorpart");
				}else if(validateOrgSpecificorFull(parentChildFlg)){
					errors.rejectValue("orgSpcificFullRadio", "error.not.valid.fullorpart");
				}else if ( ((SectionConstant.ENTITY_SPECIFIC.toString()).equals(parentChildFlg)) && (StringUtils.isBlank(sectionForm.getSelectedOrgList()))  ) {
					errors.rejectValue("selectedOrgList", "error.select.specificList");
				}
			}
		}
	}
	
	public boolean validateLbSpecificorFull(String parentChildFlg){
		return(!( (SectionConstant.ENTITY_FULL.toString()).equals(parentChildFlg)                    || 
				  (SectionConstant.ENTITY_FULL_WITH_TOP_LOCALBODY.toString()).equals(parentChildFlg) || 
				  (SectionConstant.ENTITY_SPECIFIC.toString()).equals(parentChildFlg)                ));
	}
	
	public boolean validateOrgSpecificorFull(String parentChildFlg){
		return(!( (SectionConstant.ENTITY_FULL.toString()).equals(parentChildFlg)                    || 
				  (SectionConstant.ENTITY_SPECIFIC.toString()).equals(parentChildFlg)                ));
	}
	
	
	public boolean validateLbHierarchy(String localBodyLevelCodes,String parent,String category,boolean isManage){
		boolean flag=false;
		if((!(SectionConstant.NULL_STRING.toString().equals(parent)) && !(SectionConstant.LOCAL_BODY_CATEGORY_URBN.toString().equals(category))) || isManage){
			if(StringUtils.isBlank(localBodyLevelCodes)){
				flag= true;
			}else{
				String lbCodeArray[]=localBodyLevelCodes.split(",");
				for(int i=0;i<lbCodeArray.length;i++){
					if(StringUtils.isBlank(lbCodeArray[i]))	{
						flag= true;
						break;
					}
				}
				
			}
		}
		
		return flag;
	}
	
	public void validateCenterOrganisationDetails(SectionForm sectionForm,Errors errors){
		String orgLevelCodes=sectionForm.getOrgLevelCodes();
		if(orgLevelCodes==null || (orgLevelCodes!=null && orgLevelCodes.length()<=0)){
			errors.rejectValue("orgLevelCodes", "error.select.specificList");
		}else{
			boolean flag=false;
			String orgLevelCodeArray[] = orgLevelCodes.split(",");
			for(int i=0;i<orgLevelCodeArray.length;i++){
				if(StringUtils.isBlank(orgLevelCodeArray[i]))	{
					errors.rejectValue("orgLevelCodes", "error.select.specificList");
					flag= true;
					break;
				}
			}
		}
	}

}
