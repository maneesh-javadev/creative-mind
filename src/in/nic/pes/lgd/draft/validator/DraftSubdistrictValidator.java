package in.nic.pes.lgd.draft.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.draft.form.DraftSubdistrictForm;
import in.nic.pes.lgd.draft.service.DraftSubdistrictService;

public class DraftSubdistrictValidator implements Validator {


	@Autowired 
	DraftSubdistrictService draftSubdistrictService;
	
	
	private CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
	
	
	@Override
	public boolean supports(Class<?> arg0) {
	return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		DraftSubdistrictForm draftSubdistrictForm=(DraftSubdistrictForm)target;
		validateGeneralDetails(draftSubdistrictForm,errors);
		
	}
	
	
	private void validateGeneralDetails(DraftSubdistrictForm draftSubdistrictForm, Errors errors) {
		Integer districtCode=draftSubdistrictForm.getDistrictCode()!=null?draftSubdistrictForm.getDistrictCode():draftSubdistrictForm.getSelectDistrictCode();
		if(districtCode==null){
			errors.rejectValue("districtCode", "error.PSDT");
		}else{
			validateDraftSubdistrictNameEnglish(draftSubdistrictForm,errors);
			validateDraftSubdistrictCoverage(draftSubdistrictForm,errors);
		}
		
		validateStateSpecificCode(draftSubdistrictForm,errors);
		coordinatesFields(draftSubdistrictForm,errors);
		
	}
	
	
	
	
	private void validateDraftSubdistrictNameEnglish(DraftSubdistrictForm draftSubdistrictForm, Errors errors){
		String SubdistNameEng=draftSubdistrictForm.getSubdistrictNameEnglish();
		if (StringUtils.isBlank(SubdistNameEng)) {	
				errors.rejectValue("subdistrictNameEnglish", "Error.SUBDISTRICTENGLISH");
		}else if (!customValidator.checkforAlphabetNumbericWithSpaceDotandSlash(SubdistNameEng)){
			errors.rejectValue("subdistrictNameEnglish", "Error.data.SubDistrictNameEng");
		}else{
			subDistrictNameUnique(draftSubdistrictForm,errors);
		}
	}
	
	private void subDistrictNameUnique(DraftSubdistrictForm draftSubdistrictForm, Errors errors){
		String subdistirctNameEngList=draftSubdistrictForm.getListofSubdistrictNameEng()==null?"":draftSubdistrictForm.getListofSubdistrictNameEng();
		if(subdistirctNameEngList.contains(draftSubdistrictForm.getSubdistrictNameEnglish())){
			errors.rejectValue("subdistrictNameEnglish", "Error.subdistrict.name.unique.previous");
		}else {
			Character isUnique=null;
			try{
				isUnique=draftSubdistrictService.subdistrictNameIsUnique(draftSubdistrictForm.getSubdistrictNameEnglish(), draftSubdistrictForm.getDistrictCode()!=null?draftSubdistrictForm.getDistrictCode():draftSubdistrictForm.getSelectDistrictCode());
				
			}catch(Exception e){
				e.printStackTrace();
			}
			if(isUnique!=null && isUnique=='D'){
				errors.rejectValue("subdistrictNameEnglish", "Error.subdistrict.name.unique.draf");
			}else if(isUnique!=null && isUnique=='P'){
				errors.rejectValue("subdistrictNameEnglish", "Error.subdistrict.name.unique.publish");
			}
		}
    }
	
	
	
	
	private void validateDraftSubdistrictCoverage(DraftSubdistrictForm draftSubdistrictForm, Errors errors){
		boolean isPartFlag=false;
		String contDraftSubDistrictList=draftSubdistrictForm.getContibutingSubdistrict();
		if(contDraftSubDistrictList==null || (contDraftSubDistrictList!=null && StringUtils.isBlank(contDraftSubDistrictList))){
			errors.rejectValue("contibutingSubdistrict", "Please select contribute Sub-District");
		}else if(contDraftSubDistrictList!=null && contDraftSubDistrictList.contains(",")){
			Scanner  scanner = new Scanner(contDraftSubDistrictList);
			List<Integer> subdistrictPartList=new ArrayList<Integer>();
			scanner.useDelimiter(",");
			
			Integer coverageFullCount=0;
			while (scanner.hasNext()) {
				String covValue=scanner.next();
				if(covValue.contains("PART")){
					subdistrictPartList.add(Integer.parseInt(covValue.split("@")[0]));
					isPartFlag=true;
				}else if(covValue.contains("FULL")){
					coverageFullCount++;
				}
				
				
			}
			
			if(!(isPartFlag || coverageFullCount>=2)){
				errors.rejectValue("contibutingSubdistrict", "Error.subdistrict.coverage.validatation");
			}
			
			/*if(isPartFlag){
				validateDraftvillageCoverage(draftSubdistrictForm,subdistrictPartList,errors);
			}*/
	
		}else {
			List<Integer> subdistrictPartList=new ArrayList<Integer>();
			if(contDraftSubDistrictList.contains("PART")){
				isPartFlag=true;
				subdistrictPartList.add(Integer.parseInt(contDraftSubDistrictList.split("@")[0]));
				
			}else{
				errors.rejectValue("contibutingSubdistrict", "Error.subdistrict.coverage.validatation");
			}
			
			/*if(isPartFlag){
				validateDraftvillageCoverage(draftSubdistrictForm,subdistrictPartList,errors);
			}*/
		}
		
		
	}
	
	private void validateDraftvillageCoverage(DraftSubdistrictForm draftSubdistrictForm,List<Integer> coverageSubdistList, Errors errors){
		try{
			String villageCoverage=draftSubdistrictForm.getContibutingVillage();
			if(villageCoverage==null || (villageCoverage!=null && StringUtils.isBlank(villageCoverage))){
				errors.rejectValue("contibutingVillage", "Please select contribute village");
			}else{
				List<Integer> coverageVillageList=new ArrayList<Integer>();
				Scanner  scanner = new Scanner(villageCoverage);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
						coverageVillageList.add(Integer.parseInt(scanner.next()));
				}
					
				String leftSubdistrictCoverage=draftSubdistrictService.valdidateVillageCoverage(coverageSubdistList, coverageVillageList);
				if(leftSubdistrictCoverage!=null && !StringUtils.isBlank(leftSubdistrictCoverage)){
					String errorMessage="Contributing Subdistrict part("+leftSubdistrictCoverage+") not exist contributing village ";
					errors.rejectValue("contibutingVillage", errorMessage);
				}
						
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param draftSubdistrictForm
	 * @param errors
	 */
	private void coordinatesFields(DraftSubdistrictForm draftSubdistrictForm,  Errors errors){
		String latitude = draftSubdistrictForm.getLatitude() == null ? "" : draftSubdistrictForm.getLatitude();
		String longitude = draftSubdistrictForm.getLongitude() == null ? "" : draftSubdistrictForm.getLongitude();
		if(!"".equals(longitude) || !"".equals(latitude)){
			String[] longitudeArray = longitude.trim().split(",");
			String[] latitudeArray = latitude.trim().split(",");
			if(longitudeArray.length == latitudeArray.length){
				for(int i = 0; i < longitudeArray.length; i++){
					String lon = longitudeArray[i];
					String lat = latitudeArray[i];
					try{
						if(!betweenExclusive(Double.parseDouble(lon), 32d, 98d)){
							errors.rejectValue("longitude", "Error.longituderng", new String[] {"32, 98"}, null);
						}
						if(!betweenExclusive(Double.parseDouble(lat), 6d, 38d)){
							errors.rejectValue("latitude", "Error.latituderng", new String[] {"6, 38"}, null);
						}
					}catch(java.lang.NumberFormatException e){
						errors.rejectValue("longitude", "Error.longitudechk");
						errors.rejectValue("latitude", "Error.latitudechk");
					}
				}
			}else {
				errors.rejectValue("longitude", "Error.lengthmismatch");
			}
		}
	}
	
	private static boolean betweenExclusive(Double x, Double min, Double max) {
	    return x >= min && x <= max;    
	}
	
	private void validateStateSpecificCode(DraftSubdistrictForm draftSubdistrictForm,  Errors errors){
		String ssCode=draftSubdistrictForm.getSscode();
		if(ssCode!=null && (!ssCode.trim().equals(""))){
			if(!customValidator.checkforAlphaNumericand(ssCode)){
				errors.rejectValue("sscode", "Error.SscodeNumeric");
			}else if(ssCode.length()>7){
				errors.rejectValue("sscode", "Error.Sscode");
			}
				
			}
			
	}	
			
	
	

}

