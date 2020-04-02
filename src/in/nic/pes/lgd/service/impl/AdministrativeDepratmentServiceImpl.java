package in.nic.pes.lgd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.batik.svggen.font.table.ScriptList;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LocalBodyEntityDetails;
import com.cmc.lgd.localbody.entities.LocalBodyTable;
import com.org.ep.exception.BaseAppException;

import in.nic.pes.lgd.bean.ChildExtendDept;
import in.nic.pes.lgd.bean.Country;
import in.nic.pes.lgd.bean.DepartmentLBWardMapping;
import in.nic.pes.lgd.bean.DepartmentMapping;
import in.nic.pes.lgd.bean.DepartmentMappingFrom;
import in.nic.pes.lgd.bean.DepartmentMappingPK;
import in.nic.pes.lgd.bean.DeptAdminUnit;
import in.nic.pes.lgd.bean.DeptAdminUnitEntity;
import in.nic.pes.lgd.bean.EntityTransactionsNew;
import in.nic.pes.lgd.bean.ExistDeptHierarchy;
import in.nic.pes.lgd.bean.ExistingDeptMapping;
import in.nic.pes.lgd.bean.FetchOrgLocatedLevel;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LgdOrganisationListByOrgType;
import in.nic.pes.lgd.bean.LgdOrganizationAtLevels;
import in.nic.pes.lgd.bean.LgdSubordinateOrgUnits;
import in.nic.pes.lgd.bean.LgdUpperHierarchyLevel;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationUnit;
import in.nic.pes.lgd.constant.DepartmentConstent;
import in.nic.pes.lgd.dao.AdministrativeDepratmentDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.draft.form.DepartmentOrgListDto;
import in.nic.pes.lgd.forms.AdminOrgDeptForm;
import in.nic.pes.lgd.service.AdministrativeDepratmentService;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.LocalGovtSetupService;
import pes.attachment.util.AttachedFilesForm;
import java.util.Arrays;

@Service
public class AdministrativeDepratmentServiceImpl implements AdministrativeDepratmentService {
	@Autowired
	public AdministrativeDepratmentDAO administrativeDepratmentDao;

	@Autowired
	public LocalGovtBodyService localGovtBodyService;

	@Autowired
	public LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	public LocalGovtSetupService localGovtSetupService;

	@Autowired
	CommonService commonService;

	@Override
	public List<DeptAdminUnit> getLandRegionUnitLevel(boolean isCenterUser) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.getLandRegionUnitLevel(isCenterUser);
	}

	@Override
	public List<DeptAdminUnit> getAdministrativeUnitLevel(Integer parentAdminCode, Integer stateCode) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.getAdministrativeUnitLevel(parentAdminCode, stateCode);
	}

	public List<LBTypeDetails> getLocalBodyTypesDetails(Integer stateCode) {
		return administrativeDepratmentDao.getLocalBodyTypesDetails(stateCode);
	}
	@Override
	public List<Object[]> getUnitLevelNames(Set<Integer> unitCodes) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.getUnitLevelNames(unitCodes);
	}

	@Override
	public List<DeptAdminUnitEntity> getAdminEntities(Integer unitLevelCode) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.getAdminEntities(unitLevelCode);
	}

	@Override
	public boolean saveAdminDepartmentProcess(LinkedList<AdminOrgDeptForm> storedFormList, Integer stateCode, boolean isCenterUser,Long userId,Boolean isOrganization) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.saveAdminDepartmentProcess(storedFormList, stateCode, isCenterUser,userId,isOrganization);
	}

	@Override
	public List<Organization> getDepartmentList(Integer stateCode, Integer orgTypeCode) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.getDepartmentList(stateCode, orgTypeCode);
	}

	@Override
	public List<DeptAdminUnitEntity> getDeptAdminUnitList(int stateCode) throws BaseAppException {
		return administrativeDepratmentDao.getAdminUnitListbyStateCode(stateCode);
	}

	@Override
	public boolean checkExistingDepartmentName(Integer stateCode, String departmentName, HttpSession httpSession) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.checkExistingDepartmentName(stateCode, departmentName, httpSession);
	}

	/**
	 * To Get Admin Unit Details For Extended Functionality with their operation
	 * state
	 * 
	 * @author Ripunj on 06-10-2014
	 * 
	 * @param orgCode
	 * @param unitLevelCode
	 */
	@Override
	public List<DeptAdminUnitEntity> getAdminEntitiesExtended(Integer orgCode, Integer unitLevelCode) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.getAdminEntitiesExtended(orgCode, unitLevelCode);
	}

	@Override
	public boolean saveExtendedAdminDepartmentProcess(LinkedList<AdminOrgDeptForm> storedFormList, Integer stateCode, Integer orgLocatedLevelCode, boolean isCenterUser) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.saveExtendedAdminDepartmentProcess(storedFormList, stateCode, orgLocatedLevelCode, isCenterUser);
	}

	public boolean checkExistingDepartmentNameForExtendedDept(Integer stateCode, String departmentName, Integer orgLocatedLevelCode, HttpSession httpSession) {
		return administrativeDepratmentDao.checkExistingDepartmentNameForExtendedDept(stateCode, departmentName, orgLocatedLevelCode, httpSession);
	}

	public List<DeptAdminUnit> getAdminUnitLevelList(int stateCode) {
		return administrativeDepratmentDao.getAdminUnitLevelList(stateCode);
	}

	public List<DeptAdminUnitEntity> getAdminUnitListbyStateCodeandLevel(int stateCode, int level) {
		return administrativeDepratmentDao.getAdminUnitListbyStateCodeandLevel(stateCode, level);
	}

	@Override
	public void setMappingBasedOnRole(ModelMap model, HttpServletRequest request,Integer stateCode) {
		String message = "";
		try {
			if (model.get("message") != null) {
				message = model.get("message").toString();
			}
			boolean isStateAdmin = true;
			boolean isDeptAdmin = false;
			TreeMap<String, String> mappingTypeList = new TreeMap<String, String>();
			mappingTypeList.put("OMO", "label.betweenDept");
			mappingTypeList.put("OMG", "label.deptMultipleLB");
			mappingTypeList.put("GMO", "label.lbMultipleDept");

			if (isStateAdmin || isDeptAdmin) {
				List<GetLocalGovtSetup> getLocalGovtSetupList = localGovtSetupService.getLocalbodyDetailDAOOrderInLocalBodyType(stateCode);
				model.put("localGovtSetup", getLocalGovtSetupList);

				List<LgdOrganisationListByOrgType> organizationList = administrativeDepratmentDao.getOrganizationListByOrgTypeFn(2, 1, stateCode);
				model.put("organizationList", organizationList);

				model.put("mappingTypeList", mappingTypeList);
				model.put("isStateAdmin", isStateAdmin);
			} else {
				message = "msg.noPrivilege";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in setMappingBasedOnRole method of DepartmentMappingService->" + e);
		}
		model.put("message", message);
	}

	@Override
	public List<LgdOrganizationAtLevels> getOrganizationAtLevels(int orgCode) {
		return administrativeDepratmentDao.getOrganizationAtLevels(orgCode);
	}

	@Override
	public List<LgdSubordinateOrgUnits> getUnmappedSubordinateOrgUnits(int orgCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.getUnmappedSubordinateOrgUnits(orgCode, stateCode, mappingType, fromLevelCategory, toLevelTypeCode);
	}

	@Override
	public Boolean checkIfDeptMappingExists(Integer stateID, String mappingType, Integer toLevelTypeCode, String fromLevelCategory, Integer fromLevelTypeCode, Integer fromLevelCode) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.checkIfDeptMappingExists(stateID, mappingType, toLevelTypeCode, fromLevelCategory, fromLevelTypeCode, fromLevelCode);
	}

	/**
	 * @author Ripunj on 02-02-2015
	 * @param localBodyTypeCode
	 * @param stateCode
	 * @param mappingType
	 * @param fromLevelCategory
	 * @param toLevelTypeCode
	 * @return
	 */
	@Override
	public List<LocalbodyListbyState> getUnmappedULBList(Integer localBodyTypeCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode) {
		return administrativeDepratmentDao.getUnmappedULBList(localBodyTypeCode, stateCode, mappingType, fromLevelCategory, toLevelTypeCode);
	}

	/**
	 * @author Ripunj on 02-02-2015
	 * @param localBodyCode
	 * @param stateCode
	 * @param mappingType
	 * @param fromLevelCategory
	 * @param toLevelTypeCode
	 * @return
	 */

	@Override
	public List<LocalbodyListbyState> getUnmappedParentwiseLBList(int localBodyCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.getUnmappedParentwiseLBList(localBodyCode, stateCode, mappingType, fromLevelCategory, toLevelTypeCode);
	}

	/**
	 * @author Ripunj on 02-02-2015
	 * @param orgCode
	 * @return
	 */
	public List<LgdSubordinateOrgUnits> getSubordinateOrgUnits(int orgCode) {
		return administrativeDepratmentDao.getSubordinateOrgUnits(orgCode);
	}

	/**
	 * 
	 */
	@Override
	public boolean saveDepartmentMapping(ModelMap model, HttpServletRequest request) {
		boolean saveFlag = false;
		try {
			String mappingType = null;
			String toCategory = null;
			String fromCategory = null;
			Long toLevelCode = null;
			String[] fromLevelCode = null;
			Integer toSelectedIdx = 0;
			Integer fromSelectedIdx = 0;
			Integer stateID = 0;
			Integer toLevelTypeCode = 0;
			Integer fromLevelTypeCode = 0;
			Long userID = 0l;
			String frmLevelCode=null;
			String wardcode=null;
			Set<DepartmentMappingFrom> departmentMappingFromList = new HashSet<DepartmentMappingFrom>();
           
			if (request.getParameter("stateId") != null) {
				stateID = Integer.parseInt(request.getParameter("stateId").toString());
			}
			if (request.getParameter("userId") != null) {
				userID = Long.parseLong(request.getParameter("userId").toString());
			}
			if (request.getParameter("mappingType") != null) {
				mappingType = request.getParameter("mappingType");
			}
			if (request.getParameter("to_category") != null) {
				toCategory = request.getParameter("to_category");
			}
			if (request.getParameter("from_category") != null) {
				fromCategory = request.getParameter("from_category");
			}
			if (request.getParameter("to_selected_idx") != null) {
				toSelectedIdx = Integer.parseInt(request.getParameter("to_selected_idx"));
			}
			if (request.getParameter("from_selected_idx") != null) {
				fromSelectedIdx = Integer.parseInt(request.getParameter("from_selected_idx"));
			}

			if (toSelectedIdx > 0 && fromSelectedIdx > 0) {
				
				if (mappingType != null ) {
					if (mappingType.equals("OMO")) {
						if (request.getParameter("toDepartmentOrgUnits") != null) {
							toLevelCode = Long.parseLong(request.getParameter("toDepartmentOrgUnits"));
						}

						if (request.getParameterValues("fromDepartmentOrgUnit") != null) {
							fromLevelCode = request.getParameterValues("fromDepartmentOrgUnit" );
						}
						toLevelTypeCode = request.getParameter("toDepartmentLevel") != null ? Integer.parseInt(request.getParameter("toDepartmentLevel")) : 0;
						fromLevelTypeCode = request.getParameter("fromDepartmentLevel") != null ? Integer.parseInt(request.getParameter("fromDepartmentLevel")) : 0;
					} else if (mappingType.equals("GMO")) {
						
						if (request.getParameter("to_level_" + toSelectedIdx) != null) {
							toLevelCode = Long.parseLong(request.getParameter("to_level_" + toSelectedIdx));
						}
						
						if (request.getParameterValues("fromDepartmentOrgUnit") != null) {
							fromLevelCode = request.getParameterValues("fromDepartmentOrgUnit" );
						}
						
						toLevelTypeCode = request.getParameter("toLocalBodySetup") != null ? Integer.parseInt(request.getParameter("toLocalBodySetup")) : 0;
						fromLevelTypeCode = request.getParameter("fromDepartmentLevel") != null ? Integer.parseInt(request.getParameter("fromDepartmentLevel")) : 0;
					} else if (mappingType.equals("OMG")) {
						
						
						if (request.getParameter("toDepartmentOrgUnits") != null) {
							toLevelCode = Long.parseLong(request.getParameter("toDepartmentOrgUnits"));
						}
						
						if (request.getParameterValues("from_level_" + fromSelectedIdx) != null) {
							fromLevelCode = request.getParameterValues("from_level_" + fromSelectedIdx);
						}
						
						toLevelTypeCode = request.getParameter("toDepartmentLevel") != null ? Integer.parseInt(request.getParameter("toDepartmentLevel")) : 0;
						fromLevelTypeCode = request.getParameter("fromLocalBodySetup") != null ? Integer.parseInt(request.getParameter("fromLocalBodySetup")) : 0;
					}

					DepartmentMapping deptMapping = administrativeDepratmentDao.getDeptMappingByMappingType(stateID, mappingType, toCategory, toLevelCode);
					
					if (deptMapping == null) {
						deptMapping = new DepartmentMapping();
						deptMapping.setStateId(stateID);
						deptMapping.setMappingType(mappingType);
						deptMapping.setToLevelCategory(toCategory);
						deptMapping.setToLevelTypeCode(toLevelTypeCode);
						deptMapping.setToLevelCode(toLevelCode);
						deptMapping.setMappingDonebyUserid(userID);
					}
					deptMapping.setMappingDonebyUserid(userID);
					for (String code : fromLevelCode)
					 { 
				         DepartmentMappingFrom deptMappingFrom = new DepartmentMappingFrom();
						  deptMappingFrom.setFromLevelCategory(fromCategory);
						  deptMappingFrom.setFromLevelTypeCode(fromLevelTypeCode);
						  deptMappingFrom.setFromLevelCode(Long.parseLong(code));
						  deptMappingFrom.setDepartmentMapping(deptMapping);
						  departmentMappingFromList.add(deptMappingFrom); 
					}
				    
					 StringBuilder sb = new StringBuilder(); 
					 for (String str:fromLevelCode) 
					 {
					 sb.append(str).append(","); 
					 frmLevelCode=sb.substring(0, sb.length() - 1);
					 }
					 deptMapping.setDepartmentMappingFrom(departmentMappingFromList);
					   //administrativeDepratmentDao.merge(deptMapping,fromCategory,fromLevelTypeCode,frmLevelCode);
					  // saveFlag = true;
					   
		             List<DepartmentLBWardMapping> wardMapping = new ArrayList<DepartmentLBWardMapping>(); 
		              String[] wardList = request.getParameterValues("wardList"); 
		               if(wardList != null)
		              { 
		            	for (String wardCode : wardList) 
		               { 
		              DepartmentLBWardMapping deptLbWardMapping = new DepartmentLBWardMapping();
					  deptLbWardMapping.setDepartmentMapping(deptMapping);
					  deptLbWardMapping.setDepartmentMappingPK(new DepartmentMappingPK(deptMapping.getMappingId(),Integer.parseInt(wardCode)));
					  wardMapping.add(deptLbWardMapping);
					  } 
		            	}
		               
		               if(wardList!=null)
		               {
		                StringBuilder wc = new StringBuilder(); 
		                for (String str:wardList) 
						 {
		                 wc.append(str).append(","); 
						 wardcode=wc.substring(0, wc.length() - 1);
						 }
				       }
		               
		               else
		               {
		            	   wardcode="N";
		               }
		            
					  deptMapping.setLbWardMappingList(wardMapping);
					 // administrativeDepratmentDao.merge(deptMapping);
					  administrativeDepratmentDao.merge(deptMapping,fromCategory,fromLevelTypeCode,frmLevelCode,wardcode);
					  
					  
					  saveFlag = true;
					 
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in saveDepartmentMapping method of AdministrativeDepratmentServiceImpl->" + e);
		}
		return saveFlag;
	}

	@Override
	public List<Organization> getOrganizationbyCriteria(Integer slc, Integer orgTypeCode) throws Exception {
		return administrativeDepratmentDao.getOrganizationbyCriteria(slc, orgTypeCode);
	}

	@Override
	public List<DeptAdminUnit> getExistLandRegionUnitLevelbyOrganization(boolean isCenterUser, Integer olc, Integer slc,String entityType) throws Exception {
		return administrativeDepratmentDao.getExistLandRegionUnitLevelbyOrganization(isCenterUser, olc,slc,entityType);
	}
	@Override
	public List<DeptAdminUnit> getExistingDepartmentSetUp(boolean isCenterUser, Integer olc, Integer slc,String entityType) throws Exception {
		return administrativeDepratmentDao.getExistingDepartmentSetUp(isCenterUser, olc,slc,entityType);
	}


	@Override
	public List<DeptAdminUnit> getLandRegionUnitLevelbyOrganization(boolean isCenterUser, Integer olc, Integer landRegionSlc, Integer adminUnitCode) throws Exception {
		return administrativeDepratmentDao.getLandRegionUnitLevelbyOrganization(isCenterUser, olc, landRegionSlc, adminUnitCode);
	}

	@Override
	public List<DeptAdminUnit> getChildofExistingParentLevelbyOrganization(Integer parentAdminLvlCode, Integer olc, Integer landRegionSlc, Integer slc, String adminUnitCodes, String localBodyLevel,Integer childAdminCode) throws Exception {
		List<Integer> adminUnitCode = null;
		List<Integer> localBodyCode = null;
		if (adminUnitCodes.contains("@")) {
			adminUnitCode = new ArrayList<Integer>();
			localBodyCode = new ArrayList<Integer>();
			Scanner scanner = new Scanner(adminUnitCodes);
			scanner.useDelimiter("@");
			while (scanner.hasNext()) {
				//check for sub District and block
				
				int unitCode=Integer.parseInt(scanner.next());
				if(unitCode>0){
				     if(unitCode==3){
					 adminUnitCode.add(5);
				     }
				     if(unitCode==5){
					adminUnitCode.add(3);
				    }
				adminUnitCode.add(unitCode);
				}else{
					localBodyCode.add(-unitCode);
				}
			}
			
			scanner.close();
		}
		return administrativeDepratmentDao.getChildofExistingParentLevelbyOrganization(parentAdminLvlCode, olc, landRegionSlc, slc, adminUnitCode,localBodyCode, localBodyLevel,childAdminCode);
	}

	@Override
	public List<DeptAdminUnit> createtHierarchybyadminUnitCode(Integer olc, String adminUnitCodes,Integer statecode) throws Exception {
		List<Integer> adminUnitCode = new ArrayList<Integer>();
		List<Integer> localBodyCode = new ArrayList<Integer>();
		Scanner scanner = new Scanner(adminUnitCodes);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			int value=Integer.parseInt(scanner.next());
			if(value>0)
			adminUnitCode.add(value);
			else
			localBodyCode.add(-value);
		}
		scanner.close();
		return administrativeDepratmentDao.createtHierarchybyadminUnitCode(olc, adminUnitCode,localBodyCode,statecode);
	}

	@Override
	public List<OrgLocatedAtLevels> getExistDeptbyOrganization(Integer olc, Integer adminUnitCode) throws Exception {
		return administrativeDepratmentDao.getExistDeptbyOrganization(olc, adminUnitCode);
	}

	@Override
	public boolean saveRestructureDepartmentLevel(LinkedList<AdminOrgDeptForm> storedFormList, Integer stateCode, boolean isCenterUser, Integer olc,Long userId) throws Exception {
		return administrativeDepratmentDao.saveRestructureDepartmentLevel(storedFormList, stateCode, isCenterUser, olc, userId);
	}

	@Override
	public List<OrgLocatedAtLevels> getParentDeptListbyOrganization(Integer olc, Integer parentLevelCode) throws Exception {
		return administrativeDepratmentDao.getParentDeptListbyOrganization(olc, parentLevelCode);
	}

	@Override
	public List<DeptAdminUnit> getStateWiseAdminUnit(boolean isCenterUser, Integer slc) throws Exception {
		return administrativeDepratmentDao.getStateWiseAdminUnit(isCenterUser, slc);
	}

	/*@Override
	public boolean checkParentOrgLocatedLevelCode(Integer parentOrgLocatedLevelCode, Integer level) {
		return administrativeDepratmentDao.checkParentOrgLocatedLevelCode(parentOrgLocatedLevelCode, level);
	}*/

	/*@Override
	public Integer getEqualUnitLevelCode(Integer level) {
		return administrativeDepratmentDao.getEqualUnitLevelCode(level);
	}*/

	@Override
	public List<DeptAdminUnit> getOfficeNamebyLevel(Integer olc, Integer level) {
		return administrativeDepratmentDao.getOfficeNamebyLevel(olc, level);
	}
	
/* for Organization Unit Start */
	
	public String getStateName(int stateCode) throws Exception{
		return administrativeDepratmentDao.getStateName(stateCode);
	}
	@Override
	public Boolean saveOrganisationUnit(List<OrganizationUnit>organisationUnitList) throws Exception{		
			return administrativeDepratmentDao.saveOrganizationUnit((List<OrganizationUnit>)organisationUnitList);		
	}	
	public List<OrganizationUnit> getOrganizationUnitList(Integer orgDeptCode,Integer stateCode) throws Exception{
		return administrativeDepratmentDao.getOrganizationUnitList(orgDeptCode,stateCode);
	}
	public Object[] getOrgUnitEntityData(Integer orgDeptCode,Integer stateCode) throws Exception{
		return administrativeDepratmentDao.getOrgUnitEntityData(orgDeptCode,stateCode);
	}
	/* for Organization Unit Ends here */

	/**
	 *  Get details of Office by OrgLocatedLevelCode and StateCode
	 *  @author Maneesh Kumar 21-July-2015
	 */
	@Override
	public Object[] getExtendOrganizationBasicDetail(Integer OrgLocatedLevelCode,Integer stateCode) throws Exception {
		return administrativeDepratmentDao.getExtendOrganizationBasicDetail(OrgLocatedLevelCode,stateCode);
	}

	/**
	 *  Save New Coverage information of Office
	 *  @author Maneesh Kumar 21-July-2015
	 */
	@Override
	public boolean saveOrganizationUnits(AdminOrgDeptForm adminOrgDeptForm) throws Exception {
	return administrativeDepratmentDao.saveOrganizationUnits(adminOrgDeptForm);
	}

	@Override
	public boolean validateEntityListforFullCoverage(String entityCodes, Integer entityType, Integer parentEntityCode) throws Exception {
		List<Integer> entityList=entityCodes!=null?commonService.createListFormString(entityCodes):null;
		return administrativeDepratmentDao.validateEntityListforFullCoverage(entityList, entityType, parentEntityCode);
	}
	
	/**
	 * Code used to get Organization Located Level List except top level for
	 * Rename Specific Name of organization Levels
	 * @author Pooja @since 17-09-2015
	 * @param olc
	 */
	@Override
	public List<OrgLocatedAtLevels> getOrgLocatedLevelListExceptTop(Integer olc) throws Exception {
		return administrativeDepratmentDao.getOrgLocatedLevelListExceptTop(olc);
	}
	/**
	 * code for saving attachment details in Update Organization Levels
	 * @author Pooja @since 21-09-2015
	 * @param attachedList
	 * @param orgLocatedLevelCode
	 */
	@Override
	public Boolean saveDataInAttachment(List<AttachedFilesForm> attachedList,Integer orgLocatedLevelCode) throws Exception{
		return administrativeDepratmentDao.saveDataInAttachment(attachedList, orgLocatedLevelCode);
	}

	@Override
	public List<DeptAdminUnit> getUnitLevelbyOrganization(Integer olc) throws Exception {
		return administrativeDepratmentDao.getUnitLevelbyOrganization(olc);
	}

	@Override
	public List<Organization> getCenterOrganizationbyCriteria(Integer orgTypeCode,Integer parentOrgCode) throws Exception {
		return administrativeDepratmentDao.getCenterOrganizationbyCriteria(orgTypeCode,parentOrgCode);
	}
    
	/*Added By Pranav
	On 24 Oct 2016*/
	@Override
	public List<LgdOrganizationAtLevels> getOrganizationAtLevelsForDeptLBMapping(int orgCode) {
		return administrativeDepratmentDao.getOrganizationAtLevelsForDeptLBMapping(orgCode);
	}
   
	/*Added By Pranav
	On 26 Oct 2016
	to get hierarchy of org at particular located level
	For Dept-LocalBody-Mapping form*/
	@Override
	public List<LgdUpperHierarchyLevel> getUpperHierarchyLevel(int org_located_level_code) {
		return administrativeDepratmentDao.getUpperHierarchyLevel(org_located_level_code);
	}

	@Override
	public List<LocalBodyTable> getLocalBodyEntities(Integer unitLevelCode, Integer stateCode) {
		// TODO Auto-generated method stub
		return administrativeDepratmentDao.getLocalBodyEntities(unitLevelCode,stateCode);
	}

	/*@Override
	public List<DeptAdminUnit> getChildofExistingParentLevelbyOrganization(Integer parentAdminLvlCode, Integer slc, String adminLvlCodeList, boolean ExistLvlFlag,Integer ExistLvlCode) throws Exception {
		
		List<Integer> adminUnitCode = null;
		if (adminLvlCodeList.contains("@")) {
			adminUnitCode = new ArrayList<Integer>();
			Scanner scanner = new Scanner(adminLvlCodeList);
			scanner.useDelimiter("@");
			while (scanner.hasNext()) {
				adminUnitCode.add(Integer.parseInt(scanner.next()));
			}
		}
		return administrativeDepratmentDao.getChildofExistingParentLevelbyOrganization(parentAdminLvlCode, slc, adminUnitCode, ExistLvlFlag,ExistLvlCode);
	}*/
	//By Abhishek
	
		public List<Organization> getAllOrganizationDetailsBySC(Integer slc){
			return administrativeDepratmentDao.getAllOrganizationDetailsBySC(slc);
		}
		public String[] checkOrgUnit(Integer orgVal){
			return administrativeDepratmentDao.checkOrgUnit(orgVal);
		}
		public boolean invokeScriptCall(String level,String queryType,Integer selectedOrgVal){
			return administrativeDepratmentDao.invokeScriptCall(level,queryType,selectedOrgVal);
		}
		public List<DeptAdminUnit> getOfficeNamebyLevelWithLocalbody(Integer olc, Integer level) {
			return administrativeDepratmentDao.getOfficeNamebyLevelWithLocalbody(olc, level);
		}
		
		@Override
		public List<ChildExtendDept> getChildofParentAdminCode(Integer slc, Integer parentAdminLevelCode,String existAdminCodes) throws Exception {
			List<Integer> existAdminCodeList = new ArrayList<Integer>();
			existAdminCodeList.add(-99);
			if (existAdminCodes!=null && existAdminCodes.contains(",")) {
				Scanner scanner = new Scanner(existAdminCodes);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					existAdminCodeList.add(Integer.parseInt(scanner.next()));
				}
			}else if(existAdminCodes!=null && existAdminCodes.length()>0){
				existAdminCodeList.add(Integer.parseInt(existAdminCodes));
			}
			
			return administrativeDepratmentDao.getChildofParentAdminCode(slc, parentAdminLevelCode, existAdminCodeList);
		}
		
		@Override
		public List<ExistDeptHierarchy> getExistDeptHierarchy(Integer olc,Integer slc,Boolean isHierarchy) throws Exception {
			return administrativeDepratmentDao.getExistDeptHierarchy(olc,slc,isHierarchy);
		}

		@Override
		public String[] getLevelWiseTreeDetails(Integer stateCode, String parentHierarchy) throws Exception {
			return administrativeDepratmentDao.getLevelWiseTreeDetails(stateCode, parentHierarchy);
		}
		
		@Override
		public List<LocalBodyEntityDetails> getDistrictPanchayatList(Integer lbTypeCode,Integer stateCode, String existlbcodes)throws HibernateException {
			List<Integer> existlbList = new ArrayList<Integer>();
			existlbList.add(-99);
			if (existlbcodes!=null && existlbcodes.contains(",")) {
				Scanner scanner = new Scanner(existlbcodes);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					existlbList.add(Integer.parseInt(scanner.next()));
				}
			}else if(existlbcodes!=null && existlbcodes.length()>0){
				existlbList.add(Integer.parseInt(existlbcodes));
			}
			return administrativeDepratmentDao.getDistrictPanchayatList(lbTypeCode,stateCode, existlbList,Boolean.FALSE);
		}

		@Override
		public List<LocalBodyEntityDetails> getParentwiseLocalBody(Integer localBodyCode, String existlbcodes) throws HibernateException {
			List<Integer> existlbList = new ArrayList<Integer>();
			existlbList.add(-99);
			if (existlbcodes!=null && existlbcodes.contains(",")) {
				Scanner scanner = new Scanner(existlbcodes);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					existlbList.add(Integer.parseInt(scanner.next()));
				}
			}else if(existlbcodes!=null && existlbcodes.length()>0){
				existlbList.add(Integer.parseInt(existlbcodes));
			}
			return administrativeDepratmentDao.getParentwiseLocalBody(localBodyCode,existlbList,Boolean.FALSE);
		}

		@Override
		public List<OrgLocatedAtLevels> getOfficeDetailbyParent(Integer olc,Integer parentOrgLocaltedCode,Integer locatedAtLevel) throws Exception {
			return administrativeDepratmentDao.getOfficeDetailbyParent(olc,parentOrgLocaltedCode,locatedAtLevel);
		}

		@Override
		public List<LocalBodyEntityDetails> getlbListbyCreteria(String existDPlbCodes, String existIPlbCodes, String existVPlbCodes,String accessLevel) throws HibernateException {
			
			List<Integer> existDPlbList = new ArrayList<Integer>();
			existDPlbList.add(-99);
			if (existDPlbCodes!=null && existDPlbCodes.contains(",")) {
				Scanner scanner = new Scanner(existDPlbCodes);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					existDPlbList.add(Integer.parseInt(scanner.next()));
				}
			}else if(existDPlbCodes!=null && existDPlbCodes.length()>0){
				existDPlbList.add(Integer.parseInt(existDPlbCodes));
			}
			
			List<Integer> existIPlbList = new ArrayList<Integer>();
			existIPlbList.add(-99);
			if (existIPlbCodes!=null && existIPlbCodes.contains(",")) {
				Scanner scanner = new Scanner(existIPlbCodes);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					existIPlbList.add(Integer.parseInt(scanner.next()));
				}
			}else if(existIPlbCodes!=null && existIPlbCodes.length()>0){
				existIPlbList.add(Integer.parseInt(existIPlbCodes));
			}
			
			List<Integer> existVPlbList=null;
			if((DepartmentConstent.VILLAGE_PANCHAYAT_LEVEL.toString()).equals(accessLevel)){
				existVPlbList = new ArrayList<Integer>();
				if(existVPlbCodes!=null && !("").equals(existVPlbCodes)){
				if (existVPlbCodes.contains(",")) {
					Scanner scanner = new Scanner(existVPlbCodes);
					scanner.useDelimiter(",");
					while (scanner.hasNext()) {
						existVPlbList.add(Integer.parseInt(scanner.next()));
					}
				}else if( existVPlbCodes.length()>0){
					existVPlbList.add(Integer.parseInt(existVPlbCodes));
				}
			   }else{
				   existVPlbList.add(-99);
			   }
			}
			return administrativeDepratmentDao.getlbListbyCreteria(existDPlbList, existIPlbList, existVPlbList);
		}

		@Override
		public List<OrgLocatedAtLevels> getOrgLocatedbyOrgCodeNew(int orgCode) throws Exception {
			return administrativeDepratmentDao.getOrgLocatedbyOrgCodeNew(orgCode);
		}

		@Override
		public List<FetchOrgLocatedLevel> fetchOfficeDetailbyParent(Integer olc, Integer parentOrgLocaltedCode,	Integer locatedAtLevel) throws Exception {
			return administrativeDepratmentDao.fetchOfficeDetailbyParent(olc, parentOrgLocaltedCode, locatedAtLevel);
		}
		
		@Override
		public  List<DepartmentOrgListDto> getDeptOrgList(Integer adminLevel ,Integer parentEntity ) throws Exception {
			return administrativeDepratmentDao.getDeptOrgList( adminLevel , parentEntity );
		
		}
		
		
		@Override
		public List<EntityTransactionsNew> getTranctionDtlsChangeEntity(Integer stateCode) throws Exception{
			return administrativeDepratmentDao.getTranctionDtlsChangeEntity(stateCode);
		}
		
		@Override
		public List<Object> getScriptThroughTxId(Integer transactionId) throws Exception{
			return administrativeDepratmentDao.getScriptThroughTxId(transactionId);
		}

		@Override
		public List<Country> getCountryList() throws Exception {
			return administrativeDepratmentDao.getCountryList();
		}

		@Override
		public List<ExistingDeptMapping> getExistingDeptMapping(Integer orgUnitCode, Integer orgLocatedCode,
				Integer slc, String mappingType) throws Exception {
			return administrativeDepratmentDao.getExistingDeptMapping(orgUnitCode, orgLocatedCode, slc, mappingType);
		}

		@Override
		public boolean deleteExistingDeptMapping(Integer mappingFromId, Integer mappingId,Integer userid,Integer stateID) {
			// TODO Auto-generated method stub
			return administrativeDepratmentDao.deleteExistingDeptMapping(mappingFromId, mappingId,userid,stateID);
		}

		

	/*
	 * @Override public boolean deleteExistingDeptMapping(Integer mappingFromId) {
	 * return administrativeDepratmentDao.deleteExistingDeptMapping(mappingFromId);
	 * }
	 */

		
	
}
