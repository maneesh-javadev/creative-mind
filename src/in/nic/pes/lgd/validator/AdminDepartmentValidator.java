package in.nic.pes.lgd.validator;


import in.nic.pes.lgd.bean.OrganizationUnit;
import in.nic.pes.lgd.constant.DepartmentConstent;
import in.nic.pes.lgd.dao.AdministrativeDepratmentDAO;
import in.nic.pes.lgd.forms.AdminOrgDeptForm;
import in.nic.pes.lgd.forms.OrgLocatedAtLevelsForm;
import in.nic.pes.lgd.service.OrganizationService;
import in.nic.pes.lgd.utils.ApplicationConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class AdminDepartmentValidator implements Validator {

	private boolean isCentreuser;
	private boolean organizationflow;
	private HttpSession session;
	@Autowired
	private AdministrativeDepratmentDAO administrativeDepratmentDAO;
	@Autowired
	private OrganizationService organizationService;

	public void validate(Object target, Errors errors, boolean isCenterUser, boolean organizationflow, HttpSession session) {
		this.isCentreuser = isCenterUser;
		this.organizationflow = organizationflow;
		this.session = session;
		validate(target, errors);
	}
	public void validateExtendDept(Object target, Errors errors, boolean isCenterUser, boolean organizationflow, HttpSession session) {
		this.isCentreuser = isCenterUser;
		this.organizationflow = organizationflow;
		this.session = session;
		validateExtendDept(target, errors);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	
	private boolean checkForNumeric(String strName){ 
		String regexPattern = "[0-9]+$";
		Pattern pattern 		= Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher 		= pattern.matcher(strName);
		boolean hasSpecialChars = matcher.find();
		return hasSpecialChars;
	}
	
	/*@SuppressWarnings("unchecked")
	private boolean checkInSession(String depatmentName){
		LinkedList<AdminOrgDeptForm> departmentForms = (LinkedList<AdminOrgDeptForm>) session.getAttribute("storedDeptForms");
		if(departmentForms != null){
			for(AdminOrgDeptForm form : departmentForms){
				if(depatmentName.equalsIgnoreCase(form.getDepartmentNameEnglish())){
					   return true;
				}
			}
		}
		return false;
	}*/
	
	/**
	 * {@code validateSpecialCharacters} method validation for special characters,
	 * If any special charater exist in string {@code method} return false, true
	 * returns for valid string.
	 * 
	 * @return boolean {true, false}
	 * @author vinay Yadav
	 */
	private boolean validateSpecialCharacters(String inputValue){
		String regexPattern = "[\\#\\%\\~\\!\\@\\$\\^\\*\\_\\+\\`\\=\\{\\}\\\\\\[\\\\\\]\\|\\:\\;\\'\\\\\"\\<\\>\\?\\\\]";
		Pattern pattern 		= Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher 		= pattern.matcher(inputValue);
		boolean hasSpecialChars = matcher.find();
		return hasSpecialChars;
	}
	
		
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

		AdminOrgDeptForm orgDeptForm = (AdminOrgDeptForm) target;
		String nameEnglish = orgDeptForm.getDepartmentNameEnglish();
		if (nameEnglish == null || "".equals(nameEnglish.trim())) {
			errors.rejectValue("departmentNameEnglish", "error.DeptNameBlank");
			return;
		}

		boolean ckeckfornumber = checkForNumeric(nameEnglish);
		if (ckeckfornumber) {
			errors.rejectValue("departmentNameEnglish","error.NumberNotAllowed","Numbers Not Allowed");
			return;
		}

		boolean checkforspecialchar = validateSpecialCharacters(nameEnglish);
		if (checkforspecialchar) {
			errors.rejectValue("departmentNameEnglish","error.SpecialCharNotAllowed ","Special Characters Not Allowed");
			return;
		}
		boolean nameExist = administrativeDepratmentDAO.checkExistingDepartmentNameForExtendedDept(orgDeptForm.getStateCode(),nameEnglish ,orgDeptForm.getOrgLocatedLevelCode(), session);
		
		if (nameExist) {
			errors.rejectValue("departmentNameEnglish",	"error.DeptNameDuplicate","Department Name Already Exist");
			return;
		}
		if (!isCentreuser) {
			if (organizationflow) {

				if (orgDeptForm.getFormAccessLevel() == null
						|| "".equals(orgDeptForm.getFormAccessLevel())) {

					Integer deptlist = orgDeptForm.getDeptOrgCode();
					if (deptlist == null) {
						errors.rejectValue("deptOrgCode", "error.select.DEPTFRMLIST","Department is required");
						return;
					}

					Integer orglist = orgDeptForm.getOrgType();
					if (orglist == null) {
						errors.rejectValue("orgType", "error.select.ORGFRMLIST","Organization is required");
						return;

					}
				}

			}
		} else {

			if (orgDeptForm.getFormAccessLevel() == null
					|| "".equals(orgDeptForm.getFormAccessLevel())) {
				
                  if(!orgDeptForm.getIsMinistry()){
				     Integer deptlist = orgDeptForm.getMinistryId();
				      if (deptlist == null) {
					    errors.rejectValue("ministryId","error.select.MINISTRYFRMLIST","Ministry is required");
					return;
				    }
                  }

				if (organizationflow) {

					Integer deptlistorg = orgDeptForm.getDeptOrgCode();
					if (deptlistorg == null) {
						errors.rejectValue("deptOrgCode", "error.select.DEPTFRMLIST","Department is required");
					return;
					}
					Integer orglist = orgDeptForm.getOrgType();
					if (orglist == null) {
						errors.rejectValue("orgType", "error.select.ORGTYPEFRMLIST","Organization is required");
						return;

					}

				}
			}
		}

		if ("S".equalsIgnoreCase(orgDeptForm.getChooseLevelAllOrSpecific())) {

			if (ApplicationConstant.STATE_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (isCentreuser) {
					String statelist = orgDeptForm.getStateIds();
					if (statelist == null) {
						//errors.rejectValue("stateIds", "State list must be filled");
						errors.rejectValue("setErrorMsg", "error.SelectStateList", "State list must be filled");
						return;
					}
				}
			}

			if (ApplicationConstant.BLOCK_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (!isCentreuser) {

					String ChkDistrictlist = orgDeptForm.getDistrictChkfull();
					String SubDistrictcheck = orgDeptForm.getDistrictChkpart();
					if (ChkDistrictlist == null && SubDistrictcheck==null) {
						//errors.rejectValue("districtChkfull", "At Least one District must be Selected");
						errors.rejectValue("setErrorMsg", "error.DistMustBeSelected", "At Least one Block must be Selected");
						return;
					}

					String Districtcheck = orgDeptForm.getDistrictChkfull();
					if ("DFull".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}
					
					if ("DPart".equalsIgnoreCase(SubDistrictcheck)) {
						String Blocklist = orgDeptForm.getBlockIds();
						if (Blocklist == null) {
							//errors.rejectValue("blockIds", "Block list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectBlockList", "Block list must be filled");
							return;
						}
					}
				}

			}

			if (ApplicationConstant.ADMINISTRATIVE_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (!isCentreuser) {
					String Administrativelist = orgDeptForm
							.getAdminUnitEntityIds();
					if (Administrativelist == null) {
						//errors.rejectValue("adminUnitEntityIds", "Administrative list must be filled");
						errors.rejectValue("setErrorMsg", "error.SelectAdministrativeList", "Administrative list must be filled");
						return;
					}
				}
			}

			if (ApplicationConstant.DISTRICT_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (isCentreuser) {

					String chkstatelist = orgDeptForm.getChkFullCoveragestate();
					String Districtcheck = orgDeptForm.getChkPartialCoverage();
					if (chkstatelist == null && Districtcheck==null) {
						//errors.rejectValue("ChkFullCoveragestate", "At Least one State must be Selected");
						errors.rejectValue("setErrorMsg", "error.StateMustBeSelected", "At Least one District must be Selected");
						return;
					}

					String Statecheckfull = orgDeptForm
							.getChkFullCoveragestate();
					if ("Full".equalsIgnoreCase(Statecheckfull)) {
						String statelist = orgDeptForm.getStateIds();
						if (statelist == null) {
							//errors.rejectValue("stateIds", "State list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectStateList","State list must be filled");
							return;
						}
					}
					
					if ("Part".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}
				} else {
					String Districtlist = orgDeptForm.getDistrictIds();
					if (Districtlist == null) {
						//errors.rejectValue("districtIds", "District list must be filled");
						errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
						return;
					}
				}
			}
			if (ApplicationConstant.SUBDISTRICT_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (isCentreuser) {

					String chkstatelist = orgDeptForm.getChkFullCoveragestate();
					String Districtcheck = orgDeptForm.getDistrictChkfull();
					String SubDistrictcheck = orgDeptForm.getDistrictChkpart();
					if (chkstatelist == null && Districtcheck==null && SubDistrictcheck==null) {
						//errors.rejectValue("ChkFullCoveragestate", "At Least one State must be Selected");
						errors.rejectValue("setErrorMsg", "error.StateMustBeSelected", "At Least one SubDistrict must be Selected");
						return;
					}

					String Statecheckfull = orgDeptForm
							.getChkFullCoveragestate();
					if ("Full".equalsIgnoreCase(Statecheckfull)) {
						String statelist = orgDeptForm.getStateIds();
						if (statelist == null) {
							//errors.rejectValue("stateIds", "State list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectStateList","State list must be filled");
							return;
						}
					}
					
					if ("DFull".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}
					
					if ("DPart".equalsIgnoreCase(SubDistrictcheck)) {
						String districtlist = orgDeptForm.getSubdistrictIds();
						if (districtlist == null) {
							//errors.rejectValue("subdistrictIds", "SubDistrict list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectSubDistrictList", "SubDistrict list must be filled");
							return;
						}
					}
				} else {

					String ChkDistrictlist = orgDeptForm.getDistrictChkfull();
					String SubDistrictcheck = orgDeptForm.getDistrictChkpart();
					if (ChkDistrictlist == null && SubDistrictcheck==null) {
						//errors.rejectValue("districtChkfull", "At Least one District must be Selected");
						errors.rejectValue("setErrorMsg", "error.DistMustBeSelected", "At Least one SubDistrict must be Selected");
						return;
					}
					String Districtcheck = orgDeptForm.getDistrictChkfull();
					if ("DFull".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}
					
					if ("DPart".equalsIgnoreCase(SubDistrictcheck)) {
						String districtlist = orgDeptForm.getSubdistrictIds();
						if (districtlist == null) {
							//errors.rejectValue("subdistrictIds", "SubDistrict list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectSubDistrictList", "SubDistrict list must be filled");
							return;
						}
					}
				}
			}

			if (ApplicationConstant.VILLAGE_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (isCentreuser) {

					String chkstatelist = orgDeptForm.getChkFullCoveragestate();
					String Districtcheck = orgDeptForm.getDistrictChkfull();
					String SubDistrictcheckfull = orgDeptForm.getSubDistrictChkfull();
					String SubDistrictcheckpart = orgDeptForm.getSubDistrictChkpart();
					if (chkstatelist == null && Districtcheck==null && SubDistrictcheckfull==null && SubDistrictcheckpart==null) {
						//errors.rejectValue("ChkFullCoveragestate", "At Least one State must be Selected");
						errors.rejectValue("setErrorMsg", "error.StateMustBeSelected", "At Least one Village must be Selected");
						return;
					}

					String Statecheckfull = orgDeptForm
							.getChkFullCoveragestate();
					if ("Full".equalsIgnoreCase(Statecheckfull)) {
						String statelist = orgDeptForm.getStateIds();
						if (statelist == null) {
							//errors.rejectValue("stateIds", "State list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectStateList","State list must be filled");
							return;
						}
					}
					
					if ("DFull".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}
					
					if ("SDFull".equalsIgnoreCase(SubDistrictcheckfull)) {
						String districtlist = orgDeptForm.getSubdistrictIds();
						if (districtlist == null) {
							//errors.rejectValue("subdistrictIds", "SubDistrict list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectSubDistrictList", "SubDistrict list must be filled");
							return;
						}
					}
					
					if ("SDPart".equalsIgnoreCase(SubDistrictcheckpart)) {
						String villagelist = orgDeptForm.getVillageIds();
						if (villagelist == null) {
							//errors.rejectValue("villageIds", "Village list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectVillage", "Village list must be filled");
							return;
						}
					}
				} else {

					String ChkDistrictlist = orgDeptForm.getDistrictChkfull();
					String SubDistrictcheckfull = orgDeptForm.getSubDistrictChkfull();
					String SubDistrictcheckpart = orgDeptForm.getSubDistrictChkpart();
					if (ChkDistrictlist == null && SubDistrictcheckfull==null && SubDistrictcheckpart==null) {
						//errors.rejectValue("districtChkfull", "At Least one District must be Selected");
						errors.rejectValue("setErrorMsg", "error.DistMustBeSelected", "At Least one Village must be Selected");
						return;
					}

					String Districtcheck = orgDeptForm.getDistrictChkfull();
					if ("DFull".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}

				
					if ("SDFull".equalsIgnoreCase(SubDistrictcheckfull)) {
						String districtlist = orgDeptForm.getSubdistrictIds();
						if (districtlist == null) {
							//errors.rejectValue("subdistrictIds", "SubDistrict list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectSubDistrictList", "SubDistrict list must be filled");
							return;
						}
						
						if ("SDPart".equalsIgnoreCase(SubDistrictcheckpart)) {
							String villagelist = orgDeptForm.getVillageIds();
							if (villagelist == null) {
								//errors.rejectValue("villageIds", "Village list must be filled");
								errors.rejectValue("setErrorMsg", "error.SelectVillage", "Village list must be filled");
								return;
							}
						}
					}
				}

			}
			if (DepartmentConstent.DISTRICT_PANCHAYAT_LEVEL.toString()
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if(!(orgDeptForm.getDpIds()!=null && !("").equals(orgDeptForm.getDpIds().toCharArray()))){
					errors.rejectValue("setErrorMsg", "error.SelectlbCoverage", "Covergae list must be fill");
				}
				
			}else if (DepartmentConstent.INTERMEDIATE_PANCHAYAT_LEVEL.toString()
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if(!((orgDeptForm.getDpIds()!=null && !("").equals(orgDeptForm.getDpIds().toCharArray())) || (orgDeptForm.getIpIds()!=null && !("").equals(orgDeptForm.getIpIds().toCharArray())))){
					errors.rejectValue("setErrorMsg", "error.SelectlbCoverage", "Covergae list must be fill");
				}
				
			}else if (DepartmentConstent.INTERMEDIATE_PANCHAYAT_LEVEL.toString()
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if(!((orgDeptForm.getDpIds()!=null && !("").equals(orgDeptForm.getDpIds().toCharArray())) || (orgDeptForm.getIpIds()!=null && !("").equals(orgDeptForm.getIpIds().toCharArray())) 
						|| (orgDeptForm.getVpIds()!=null && !("").equals(orgDeptForm.getVpIds().toCharArray())))){
					errors.rejectValue("setErrorMsg", "error.SelectlbCoverage", "Covergae list must be fill");
				}
				
			}
			
			
		}
	}
	
	private void validateExtendDept(Object target, Errors errors) {
		// TODO Auto-generated method stub

		AdminOrgDeptForm orgDeptForm = (AdminOrgDeptForm) target;
		String nameEnglish = orgDeptForm.getDepartmentNameEnglish();
		if (nameEnglish == null || "".equals(nameEnglish.trim())) {
			errors.rejectValue("departmentNameEnglish", "error.DeptNameBlank");
			return;
		}

		boolean ckeckfornumber = checkForNumeric(nameEnglish);
		if (ckeckfornumber) {
			errors.rejectValue("departmentNameEnglish","error.NumberNotAllowed","Numbers Not Allowed");
			return;
		}

		boolean checkforspecialchar = validateSpecialCharacters(nameEnglish);
		if (checkforspecialchar) {
			errors.rejectValue("departmentNameEnglish","error.SpecialCharNotAllowed ","Special Characters Not Allowed");
			return;
		}
		boolean nameExist = administrativeDepratmentDAO.checkExistingDepartmentNameForExtendedDept(orgDeptForm.getStateCode(),nameEnglish ,orgDeptForm.getOrgLocatedLevelCode(), session);
		
		if (nameExist) {
			errors.rejectValue("departmentNameEnglish",	"error.DeptNameDuplicate","Department Name Already Exist");
			return;
		}
		if (!isCentreuser) {
			if (organizationflow) {

				if (orgDeptForm.getFormAccessLevel() == null
						|| "".equals(orgDeptForm.getFormAccessLevel())) {

					Integer deptlist = orgDeptForm.getDeptOrgCode();
					if (deptlist == null) {
						errors.rejectValue("deptOrgCode", "error.select.DEPTFRMLIST","Department is required");
						return;
					}

					Integer orglist = orgDeptForm.getOrgType();
					if (orglist == null) {
						errors.rejectValue("orgType", "error.select.ORGFRMLIST","Organization is required");
						return;

					}
				}

			}
		} else {

			if (orgDeptForm.getFormAccessLevel() == null
					|| "".equals(orgDeptForm.getFormAccessLevel())) {

				Integer deptlist = orgDeptForm.getMinistryId();
				if (deptlist == null) {
					errors.rejectValue("ministryId","error.select.MINISTRYFRMLIST","Ministry is required");
					return;
				}

				if (organizationflow) {

					Integer deptlistorg = orgDeptForm.getDeptOrgCode();
					if (deptlistorg == null) {
						errors.rejectValue("deptOrgCode", "error.select.DEPTFRMLIST","Department is required");
					return;
					}
					Integer orglist = orgDeptForm.getOrgType();
					if (orglist == null) {
						errors.rejectValue("orgType", "error.select.ORGTYPEFRMLIST","Organization is required");
						return;

					}

				}
			}
		}
		if ("S".equalsIgnoreCase(orgDeptForm.getChooseLevelAllOrSpecific())) {

			if (ApplicationConstant.STATE_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (isCentreuser) {
					String statelist = orgDeptForm.getStateIds();
					if (statelist == null) {
						//errors.rejectValue("stateIds", "State list must be filled");
						errors.rejectValue("setErrorMsg", "error.SelectStateList", "State list must be filled");
						return;
					}
				}
			}

			if (ApplicationConstant.BLOCK_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (!isCentreuser) {

					String ChkDistrictlist = orgDeptForm.getDistrictChkfull();
					String SubDistrictcheck = orgDeptForm.getDistrictChkpart();
					if (ChkDistrictlist == null && SubDistrictcheck==null) {
						//errors.rejectValue("districtChkfull", "At Least one District must be Selected");
						errors.rejectValue("setErrorMsg", "error.DistMustBeSelected", "At Least one Block must be Selected");
						return;
					}

					String Districtcheck = orgDeptForm.getDistrictChkfull();
					if ("DFull".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}
					
					if ("DPart".equalsIgnoreCase(SubDistrictcheck)) {
						String Blocklist = orgDeptForm.getBlockIds();
						if (Blocklist == null) {
							//errors.rejectValue("blockIds", "Block list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectBlockList", "Block list must be filled");
							return;
						}
					}
				}

			}

			if (ApplicationConstant.ADMINISTRATIVE_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (!isCentreuser) {
					String Administrativelist = orgDeptForm
							.getAdminUnitEntityIds();
					if (Administrativelist == null) {
						//errors.rejectValue("adminUnitEntityIds", "Administrative list must be filled");
						errors.rejectValue("setErrorMsg", "error.SelectAdministrativeList", "Administrative list must be filled");
						return;
					}
				}
			}

			if (ApplicationConstant.DISTRICT_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (isCentreuser) {

					String chkstatelist = orgDeptForm.getChkFullCoveragestate();
					String Districtcheck = orgDeptForm.getChkPartialCoverage();
					if (chkstatelist == null && Districtcheck==null) {
						//errors.rejectValue("ChkFullCoveragestate", "At Least one State must be Selected");
						errors.rejectValue("setErrorMsg", "error.StateMustBeSelected", "At Least one District must be Selected");
						return;
					}

					String Statecheckfull = orgDeptForm
							.getChkFullCoveragestate();
					if ("Full".equalsIgnoreCase(Statecheckfull)) {
						String statelist = orgDeptForm.getStateIds();
						if (statelist == null) {
							//errors.rejectValue("stateIds", "State list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectStateList","State list must be filled");
							return;
						}
					}
					
					if ("Part".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}
				} else {
					String Districtlist = orgDeptForm.getDistrictIds();
					if (Districtlist == null) {
						//errors.rejectValue("districtIds", "District list must be filled");
						errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
						return;
					}
				}
			}
			if (ApplicationConstant.SUBDISTRICT_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (isCentreuser) {

					String chkstatelist = orgDeptForm.getChkFullCoveragestate();
					String Districtcheck = orgDeptForm.getDistrictChkfull();
					String SubDistrictcheck = orgDeptForm.getDistrictChkpart();
					if (chkstatelist == null && Districtcheck==null && SubDistrictcheck==null) {
						//errors.rejectValue("ChkFullCoveragestate", "At Least one State must be Selected");
						errors.rejectValue("setErrorMsg", "error.StateMustBeSelected", "At Least one SubDistrict must be Selected");
						return;
					}

					String Statecheckfull = orgDeptForm
							.getChkFullCoveragestate();
					if ("Full".equalsIgnoreCase(Statecheckfull)) {
						String statelist = orgDeptForm.getStateIds();
						if (statelist == null) {
							//errors.rejectValue("stateIds", "State list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectStateList","State list must be filled");
							return;
						}
					}
					
					if ("DFull".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}
					
					if ("DPart".equalsIgnoreCase(SubDistrictcheck)) {
						String districtlist = orgDeptForm.getSubdistrictIds();
						if (districtlist == null) {
							//errors.rejectValue("subdistrictIds", "SubDistrict list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectSubDistrictList", "SubDistrict list must be filled");
							return;
						}
					}
				} else {

					String ChkDistrictlist = orgDeptForm.getDistrictChkfull();
					String SubDistrictcheck = orgDeptForm.getDistrictChkpart();
					if (ChkDistrictlist == null && SubDistrictcheck==null) {
						//errors.rejectValue("districtChkfull", "At Least one District must be Selected");
						errors.rejectValue("setErrorMsg", "error.DistMustBeSelected", "At Least one SubDistrict must be Selected");
						return;
					}
					String Districtcheck = orgDeptForm.getDistrictChkfull();
					if ("DFull".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}
					
					if ("DPart".equalsIgnoreCase(SubDistrictcheck)) {
						String districtlist = orgDeptForm.getSubdistrictIds();
						if (districtlist == null) {
							//errors.rejectValue("subdistrictIds", "SubDistrict list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectSubDistrictList", "SubDistrict list must be filled");
							return;
						}
					}
				}
			}

			if (ApplicationConstant.VILLAGE_LEVEL_CODE
					.equalsIgnoreCase(orgDeptForm.getFormAccessLevel())) {
				if (isCentreuser) {

					String chkstatelist = orgDeptForm.getChkFullCoveragestate();
					String Districtcheck = orgDeptForm.getDistrictChkfull();
					String SubDistrictcheckfull = orgDeptForm.getSubDistrictChkfull();
					String SubDistrictcheckpart = orgDeptForm.getSubDistrictChkpart();
					if (chkstatelist == null && Districtcheck==null && SubDistrictcheckfull==null && SubDistrictcheckpart==null) {
						//errors.rejectValue("ChkFullCoveragestate", "At Least one State must be Selected");
						errors.rejectValue("setErrorMsg", "error.StateMustBeSelected", "At Least one Village must be Selected");
						return;
					}

					String Statecheckfull = orgDeptForm
							.getChkFullCoveragestate();
					if ("Full".equalsIgnoreCase(Statecheckfull)) {
						String statelist = orgDeptForm.getStateIds();
						if (statelist == null) {
							//errors.rejectValue("stateIds", "State list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectStateList","State list must be filled");
							return;
						}
					}
					
					if ("DFull".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}
					
					if ("SDFull".equalsIgnoreCase(SubDistrictcheckfull)) {
						String districtlist = orgDeptForm.getSubdistrictIds();
						if (districtlist == null) {
							//errors.rejectValue("subdistrictIds", "SubDistrict list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectSubDistrictList", "SubDistrict list must be filled");
							return;
						}
					}
					
					if ("SDPart".equalsIgnoreCase(SubDistrictcheckpart)) {
						String villagelist = orgDeptForm.getVillageIds();
						if (villagelist == null) {
							//errors.rejectValue("villageIds", "Village list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectVillage", "Village list must be filled");
							return;
						}
					}
				} else {

					String ChkDistrictlist = orgDeptForm.getDistrictChkfull();
					String SubDistrictcheckfull = orgDeptForm.getSubDistrictChkfull();
					String SubDistrictcheckpart = orgDeptForm.getSubDistrictChkpart();
					if (ChkDistrictlist == null && SubDistrictcheckfull==null && SubDistrictcheckpart==null) {
						//errors.rejectValue("districtChkfull", "At Least one District must be Selected");
						errors.rejectValue("setErrorMsg", "error.DistMustBeSelected", "At Least one Village must be Selected");
						return;
					}

					String Districtcheck = orgDeptForm.getDistrictChkfull();
					if ("DFull".equalsIgnoreCase(Districtcheck)) {
						String Districtlist = orgDeptForm.getDistrictIds();
						if (Districtlist == null) {
							//errors.rejectValue("districtIds", "District list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectDiatrictList", "District list must be filled");
							return;
						}
					}

				
					if ("SDFull".equalsIgnoreCase(SubDistrictcheckfull)) {
						String districtlist = orgDeptForm.getSubdistrictIds();
						if (districtlist == null) {
							//errors.rejectValue("subdistrictIds", "SubDistrict list must be filled");
							errors.rejectValue("setErrorMsg", "error.SelectSubDistrictList", "SubDistrict list must be filled");
							return;
						}
						
						if ("SDPart".equalsIgnoreCase(SubDistrictcheckpart)) {
							String villagelist = orgDeptForm.getVillageIds();
							if (villagelist == null) {
								//errors.rejectValue("villageIds", "Village list must be filled");
								errors.rejectValue("setErrorMsg", "error.SelectVillage", "Village list must be filled");
								return;
							}
						}
					}
				}

			}
		}
	}
	
	
	/* for Organisation Unit Save multiple sub offices start*/
	/**
	 * {@code unSupportedCharactersEnglish} method validation for special characters,
	 * If any special character exist in string {@code inputValue} return false, true
	 * returns for valid string.
	 * 
	 * @return boolean {true, false}
	 */
	private boolean checkforAlphabetWithSpaceDotandBrackets(String strName) { // check
		// for
		// Alphabets &
		// Space
		/*pattern modified by pooja on 03-07-2015 for taking forwardslash */
		String pattern = "^[a-zA-Z\\/\\-\\.\\(\\)\\s]+$";
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;

	}
	
	@SuppressWarnings({ "unchecked" })
	public Object[] orgUnitCommonFields(AdminOrgDeptForm adminOrgDeptForm, Errors errors, Integer stateCode, List<OrganizationUnit> organisationUnitList) {

		Map<Object, String> errorMsg = new LinkedHashMap<>();
		;
		Object[] errAndAdminFormArray = new Object[2];
		Boolean checkDuplicate = false;
		try {
			if (stateCode == 0) {
				if (adminOrgDeptForm.getMinistryId() == null) {
					errors.rejectValue("ministryId", "error.select.MINISTRYFRMLIST", "Ministry is required");
				}
			}

			if (adminOrgDeptForm.getDeptOrgCode() == null) {
				errors.rejectValue("deptOrgCode", "error.select.DEPTFRMLIST", "It is mandatory to Select a Organization/Department");
			} else {
				String updateOffyc = adminOrgDeptForm.getUpdatedSubOfficeList();
				List<OrganizationUnit> savedOffycList = administrativeDepratmentDAO.getOrganizationUnitList(adminOrgDeptForm.getDeptOrgCode(), stateCode);
				int flag = 0;
				String errMsgDisplay = null;
				List<String> detpnameList = new ArrayList<String>();
				if (null != adminOrgDeptForm.getDepartmentNameEnglish()) {
					detpnameList = Arrays.asList(adminOrgDeptForm.getDepartmentNameEnglish().trim().toUpperCase().replaceAll("\\s", "").split(","));
				}

				for (int i = 0; i < organisationUnitList.size(); i++) {
					flag = 0;
					errMsgDisplay = null;
					String nameEnglish = organisationUnitList.get(i).getOrgUnitName();
					if (StringUtils.isBlank(nameEnglish)) {
						flag = 1;
						errMsgDisplay = "error.DeptNameBlank";
					} else if (checkForNumeric(nameEnglish)) {
						flag = 1;
						errMsgDisplay = "Numbers are not allowed";
					} else if (!checkforAlphabetWithSpaceDotandBrackets(nameEnglish)) {
						flag = 1;
						errMsgDisplay = "Special characters are not allowed ";
					}

					else {
						/*
						 * modified by pooja on 03-07-2015 for checking existing
						 * department within state
						 */
						checkDuplicate = organizationService.checkExistingOrgUnitName(stateCode, nameEnglish);
						if (savedOffycList.size() > 0 && savedOffycList != null) {
							for (OrganizationUnit org : savedOffycList) {
								if (checkDuplicate) {
									if (null != organisationUnitList.get(i).getOrgUnitCode()) {
										if (!organisationUnitList.get(i).getOrgUnitCode().equals(org.getOrgUnitCode())) {
											flag = 1;
											errMsgDisplay = "A department already exists with the same name";
											break;
										}
									} else {
										flag = 1;
										errMsgDisplay = "A department already exists with the same name";
										break;
									}
								}
							}
						} else {
							if (checkDuplicate) {
								flag = 1;
								errMsgDisplay = "A department already exists with the same name";
							}
						}
						if (null != detpnameList) {
							if (Collections.frequency(detpnameList, nameEnglish.toUpperCase().trim().replaceAll("\\s", "")) > 1) {
								flag = 1;
								errMsgDisplay = "Duplicate Name";

							}
						}

					}

					if (flag == 1) {
						if (null != organisationUnitList.get(i).getOrgUnitCode()) {
							errorMsg.put(organisationUnitList.get(i).getOrgUnitCode(), errMsgDisplay);
						} else {
							errorMsg.put(organisationUnitList.get(i).getOrgUnitName(), errMsgDisplay);
						}
					}
				}
				if (!StringUtils.isBlank(updateOffyc)) {

					for (OrganizationUnit org : organisationUnitList) {
						for (OrganizationUnit savedOrgUnitList : savedOffycList) {
							if (savedOrgUnitList.getOrgUnitCode().equals(org.getOrgUnitCode())) {
								savedOffycList.remove(savedOrgUnitList);
								break;

							}
						}
					}
				}
				organisationUnitList.addAll(savedOffycList);

				List<AdminOrgDeptForm> adminformList = new ArrayList<AdminOrgDeptForm>();
				Transformer transformer = new Transformer() {
					public AdminOrgDeptForm transform(Object input) {
						AdminOrgDeptForm adminform = new AdminOrgDeptForm();
						OrganizationUnit org = (OrganizationUnit) input;
						adminform.setDepartmentNameEnglish(org.getOrgUnitName());
						adminform.setOlc(org.getOrgUnitCode()); // set orgUnit
																// Pk value i.e.
																// orgUnitCode
						return adminform;
					}
				};
				adminformList = (List<AdminOrgDeptForm>) CollectionUtils.collect(organisationUnitList, transformer);
				Collections.reverse(adminformList);
				errAndAdminFormArray[0] = errorMsg;
				errAndAdminFormArray[1] = adminformList;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return errAndAdminFormArray;
	}

	/* for Organisation Unit Save multiple sub offices ends here*/
	
	/**
	 * This Validator Check Coverage Change or Not and Generate Exception if no Change in Coverage
	 * @author Maneesh Kumar 21-July-2015
	 * @param target
	 * @param errors
	 * @throws Exception
	 */
	public void validateExtnedOrganizationUnits(Object target, Errors errors) throws Exception {
	   AdminOrgDeptForm orgDeptForm = (AdminOrgDeptForm) target;
	   String accessLevel=orgDeptForm.getFormAccessLevel();
	   boolean error_flag=false;
	   if(!"F".equalsIgnoreCase(orgDeptForm.getChooseLevelAllOrSpecific())){
		   if ("A".equalsIgnoreCase(accessLevel) && orgDeptForm.getAdminUnitEntityIds()==null){
			   error_flag=true;
		   }/*else  if (("L".equalsIgnoreCase(accessLevel) || "P".equalsIgnoreCase(accessLevel) || "G".equalsIgnoreCase(accessLevel)) && orgDeptForm.getAdminUnitEntityIds()==null){
			    error_flag=true;
		   }*/
		   else if ("D".equalsIgnoreCase(accessLevel) && orgDeptForm.getDistrictIds()==null){
			    error_flag=true;
		   }else if ("T".equalsIgnoreCase(accessLevel) && orgDeptForm.getDistrictIds()==null && orgDeptForm.getSubdistrictIds()==null){
			    error_flag=true;
			   
		   }else if ("B".equalsIgnoreCase(accessLevel)  && orgDeptForm.getDistrictIds()==null && orgDeptForm.getBlockIds()==null ){
			    error_flag=true;
		   }
		   else if ("V".equalsIgnoreCase(accessLevel) && orgDeptForm.getDistrictIds()==null && orgDeptForm.getSubdistrictIds()==null && orgDeptForm.getVillageIds()==null){
			    error_flag=true;
			   
		   }else if ("X".equalsIgnoreCase(accessLevel) && orgDeptForm.getDpIds()==null){
			   error_flag=true;
		   }
		   else if ("Y".equalsIgnoreCase(accessLevel) &&  orgDeptForm.getDpIds()==null && orgDeptForm.getIpIds()==null){
			   error_flag=true;
		   }
		   else if ("Z".equalsIgnoreCase(accessLevel) &&  orgDeptForm.getDpIds()==null && orgDeptForm.getIpIds()==null  && orgDeptForm.getVpIds()==null){
			   error_flag=true;
		   }
		   else if ("U".equalsIgnoreCase(accessLevel) && orgDeptForm.getUrbanIds()==null){
			   error_flag=true;
		   }
		   
		   if(error_flag){
			   errors.rejectValue("setErrorMsg", "error.NoChangeCoverArea", "You are Saving form without making any changes,Please make necessary changes and then Save.");
		   }
	   }  
	}
	
	/**
	 * To check Validation of Modify Organization Level 
	 * @author Pooja
	 * @since 18-09-2015
	 * @param target
	 * @param errors
	 * @throws Exception
	 */
	public void validateChangeLvlSpecificNameOrg(Object target,Errors errors) throws Exception {
		
		OrgLocatedAtLevelsForm orgLocatedAtLevelsForm = (OrgLocatedAtLevelsForm)target;
		
		if(orgLocatedAtLevelsForm.getOrgType() == null || "".equals(orgLocatedAtLevelsForm.getOrgType())) {
			errors.rejectValue("orgType","error.orgTypeError","Please Select an Organization Type.");
		}else if(orgLocatedAtLevelsForm.getOlc() == null || "".equals(orgLocatedAtLevelsForm.getOlc())) {
			errors.rejectValue("olc","error.olcError","Please Select an Organization.");
		}else if(orgLocatedAtLevelsForm.getOrgLocatedLevelCode() == null || "".equals(orgLocatedAtLevelsForm.getOrgLocatedLevelCode())) {
			errors.rejectValue("orgLocatedLevelCode","error.orgLocatedLevelError","Please Select an Organization Level.");
		}else if(orgLocatedAtLevelsForm.getOrgLevelSpecificName() == null || "".equals(orgLocatedAtLevelsForm.getOrgLevelSpecificName())) {
			errors.rejectValue("orgLevelSpecificName","error.orgLevelSpecificName","Please enter new Level Specific name of organization.");
		}else if(orgLocatedAtLevelsForm.getOrgLevelSpecificName() != null) {
			if(!checkforAlphabetWithSpaceDotandBrackets(orgLocatedAtLevelsForm.getOrgLevelSpecificName())){
				errors.rejectValue("orgLevelSpecificName","error.orgLevelSpecificName","Please enter valid new Level Specific name of organization.");
			}
		}
		if(orgLocatedAtLevelsForm.getOrderNo() != null) {
			if(unSupportedCharactersOrderNo(orgLocatedAtLevelsForm.getOrderNo())){
				errors.rejectValue("orderNo", "Error.invalidegovordrno","Please Enter Valid Govt. Order No.");
			}
		}
		if(orgLocatedAtLevelsForm.getOrderDate() != null) {
			if(orgLocatedAtLevelsForm.getOrderDate().after(new Date())){
				errors.rejectValue("orderDate", "error.valid.morecurrentDate","Order Date should not be future Date.");
			}
		}
		if(orgLocatedAtLevelsForm.getOrdereffectiveDate() != null) {
			if(orgLocatedAtLevelsForm.getOrdereffectiveDate().after(new Date())){
				errors.rejectValue("ordereffectiveDate", "error.valid.effectiveDate","Effective Date should not be future Date.");
			}
		}
		if(orgLocatedAtLevelsForm.getOrderDate() != null && orgLocatedAtLevelsForm.getOrdereffectiveDate() != null){
			if(orgLocatedAtLevelsForm.getOrderDate().after(orgLocatedAtLevelsForm.getOrdereffectiveDate())){
				errors.rejectValue("orderDate", "error.ORDERDATEInCorrect","Order Date should be less than equal to Effective Date.");
				errors.rejectValue("ordereffectiveDate", "error.EFFECTIVEDATEInCorrect","Effective Date should be greater than or equal to order date.");
			}
		}
		if(orgLocatedAtLevelsForm.getGazPubDate() != null) {
			if(orgLocatedAtLevelsForm.getOrdereffectiveDate().after(orgLocatedAtLevelsForm.getGazPubDate())) {
				errors.rejectValue("gazPubDate", "error.gazPubDateInCorrect","Gazette Publication Date should be greater than or equal to Effective date.");
			}
		}
		
	}
	
	/**
	 * {@code unSupportedCharactersOrderNo} method validation for special characters,
	 * If any special character exist in string {@code inputValue} return false, true
	 * returns for valid string.
	 * 
	 * @return boolean {true, false}
	 */
	private boolean unSupportedCharactersOrderNo(String inputValue){
		String regexPattern = "[#%&\\~\\!\\@\\$\\^\\*\\_\\+\\`\\=\\{\\}\\\\\\[\\\\\\]\\|\\\\:\\;\\'\\\"\\<\\>\\?\\\\]";
		return executeRegEx(regexPattern, inputValue);
	}
	private boolean executeRegEx(String regexPattern, String input){ 
		Pattern pattern 		= Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher 		= pattern.matcher(input);
		return matcher.find();
	}
}