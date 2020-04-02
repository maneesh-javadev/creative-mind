package com.cmc.lgd.localbody.services.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cmc.lgd.localbody.dao.LocalBodyDao;
import com.cmc.lgd.localbody.entities.CompletedCoverageDetails;
import com.cmc.lgd.localbody.entities.CoverageDetailsLocalBody;
import com.cmc.lgd.localbody.entities.CriteriaDraftedEntities;
import com.cmc.lgd.localbody.entities.DraftChangeCoverageTemp;
import com.cmc.lgd.localbody.entities.DraftChangeGovtOrderLBTemp;
import com.cmc.lgd.localbody.entities.DraftChangeParentLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftChangeTypeLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftRenameLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftUsedChangeCoverageLbLrTemp;
import com.cmc.lgd.localbody.entities.DraftUsedLbLrTemp;
import com.cmc.lgd.localbody.entities.GovernmentOrderDetails;
import com.cmc.lgd.localbody.entities.LBAttributes;
import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LBTypeDetailsWithCategory;
import com.cmc.lgd.localbody.entities.LBTypeHierarchy;
import com.cmc.lgd.localbody.entities.LocalBodyEntityDetails;
import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.entities.LocalBodyTable;
import com.cmc.lgd.localbody.entities.ManageLBDetails;
import com.cmc.lgd.localbody.entities.UnmappedLandregions;
import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.cmc.lgd.localbody.rules.LocalBodyUtil;
import com.cmc.lgd.localbody.services.LocalBodyService;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.draft.form.LocalBodyDetailDto;
import in.nic.pes.lgd.forms.Response;

@Service
public class LocalBodyServiceImpl implements LocalBodyService {
	
	@Autowired 
	private LocalBodyDao localBodyDao;
	
	@Autowired
	private LocalBodyUtil localBodyUtil;
	
	@Override
	public LBAttributes onLoadLocalBody(Integer stateCode, String PANCHAYAT_TYPE, String processName) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.onLoadLocalBody(stateCode, PANCHAYAT_TYPE, processName);
	}

	@Override
	public LBAttributes onLoadDraftedSearchLocalBody(Integer stateCode, String PANCHAYAT_TYPE) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.onLoadDraftedSearchLocalBody(stateCode, PANCHAYAT_TYPE);
	}
	
	@Override
	public List<LBTypeDetails> buildLocalBodyHierarchy(Integer setupCode, Integer setupVersion) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.buildLocalBodyHierarchy(setupCode, setupVersion);
	}

	@Override
	public List<LocalBodyEntityDetails> getDistrictPanchayatList(Integer lbTypeCode, Integer stateCode, Integer draftTempCode, Integer processId) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getDistrictPanchayatList(lbTypeCode, stateCode, draftTempCode, processId);
	}

	@Override
	public List<LocalBodyEntityDetails> getDistrictPanchayatListForDistrictUser(Integer lbTypeCode, Integer districtCode, Integer draftTempCode, Integer processId) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getDistrictPanchayatListForDistrictUser(lbTypeCode, districtCode, draftTempCode, processId);
	}
	
	@Override
	public List<LocalBodyEntityDetails> getParentwiseLocalBody(Integer localBodyCode, Integer draftTempCode, Integer processId) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getParentwiseLocalBody(localBodyCode, draftTempCode, processId);
	}

	@Override
	public List<UnmappedLandregions> getUnmappedLandRegions(String lbTypeLevel, Integer stateCode, Integer districtCode, Integer draftTempCode, Integer processId) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getUnmappedLandRegions(lbTypeLevel, stateCode, districtCode, draftTempCode, processId);
	}

	@Override
	public List<CoverageDetailsLocalBody> fetchCoverageDetailsLocalBody(String localBodyCodes, String lbTypeLevel) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.fetchCoverageDetailsLocalBody(localBodyCodes, lbTypeLevel);
	}

	@Override
	public List<CoverageDetailsLocalBody> fetchCoverageLBLandRegion(String localBodyCodes, String lbTypeLevel, List<Integer> landRegionCodes) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.fetchCoverageLBLandRegion(localBodyCodes, lbTypeLevel, landRegionCodes);
	}

	@Override
	public List<UnmappedLandregions> fetchCoverageDetailsLandRegion(String unmappedCoverageLevel, String localBodyCodes, String localBodyType) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.fetchCoverageDetailsLandRegion(unmappedCoverageLevel, localBodyCodes, localBodyType);
	}

	@Override
	public List<GovernmentOrderDetails> fetchExistingGovernmentOrder(String orderNo, String rangeFrom, String rangeTo, Integer stateCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.fetchExistingGovernmentOrder(orderNo, rangeFrom, rangeTo, stateCode);
	}

	@Override
	public String publishLocalBody(LocalBodyForm localBodyForm, HttpServletRequest request) throws  Exception {
		// TODO Auto-generated method stub
		if(localBodyForm.getIsResetedCoverage() == null || localBodyForm.getIsResetedCoverage()){
			localBodyForm.setContributingLandRegionCodes(getConcatedLandRegionCodes(localBodyForm));
		} else {
			localBodyForm.setContributingLBCodes(localBodyForm.getHidContributingLBCodes());
			localBodyForm.setContributingLandRegionCodes(localBodyForm.getHidContributingLandRegionCodes());
		}
		localBodyForm.setCoordinates(concatedCoordinates(localBodyForm.getLongitude(), localBodyForm.getLatitude()));
		localBodyForm.setParentLocalBodyCode(getParentLBCode(localBodyForm.getLocalBodyLevelCodes()));
		uploadFiles(localBodyForm);
		localBodyUtil.convertTemplatetoPDF(localBodyForm, request);
		return localBodyDao.publishLocalBody(localBodyForm);
	}

	@Override
	public Boolean saveLocalBodyAsDraft(LocalBodyForm localBodyForm) throws Exception {
		// TODO Auto-generated method stub
		uploadFiles(localBodyForm);
		DraftLocalbodyTemp draftLocalbodyTemp = convertLocalBodyFormToDraftBean(localBodyForm);
		draftLocalbodyTemp.setUsedLBLRList(getUsedLBLRCodes(localBodyForm, draftLocalbodyTemp));
		return localBodyDao.saveLocalBodyAsDraft(draftLocalbodyTemp);
	}
	
	@Override
	public LBAttributes getLocalBodyDetailsForView(Integer localBodyCode) {
		// TODO Auto-generated method stub
		return localBodyDao.getLocalBodyDetailsForView(localBodyCode);
	}
	
	@Override
	public LBAttributes onLoadSearchCriteria(Integer stateCode, String panchayatType) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.onLoadSearchCriteria(stateCode, panchayatType);
	}
	
	@Override
	public Boolean publishOrDeleteDraftToTransaction(Integer tempLocalBodyCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return localBodyDao.publishOrDeleteDraftToTransaction(tempLocalBodyCode, isDeleteStatus, request);
	}

	@Override
	public Map<String, Object> getDraftedTempLBDetails(Integer tempLBCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getDraftedTempLBDetails(tempLBCode);
	}
	
	@Override
	public AttachmentMaster getUploadFileConfigurationDetails(Long fileMasterId) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getUploadFileConfigurationDetails(fileMasterId);
	}
	
	@Override
	public String checkLocalBodyNameExist(String localBodyName, Integer localBodyType, Integer parentCode, Integer stateCode, Integer draftTempId) {
		// TODO Auto-generated method stub
		return localBodyDao.checkLocalBodyNameExist(localBodyName, localBodyType, parentCode, stateCode, draftTempId);
	}
	
	@Override
	public Boolean checkMapUpload(Integer tierSetupCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.checkMapUpload(tierSetupCode);
	}
	
	@Override
	public LBAttributes getGovernmentOrderTemplate(Integer templateCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getGovernmentOrderTemplate(templateCode);
	}
	
	@Override
	public Map<String, Object> getLBFormforModification(Integer lbTempCode) throws Exception {
		// TODO Auto-generated method stub
		return localBodyDao.getLBFormforModification(lbTempCode);
	}
	
	/**
	 * 
	 * @param selectedLocalBodyCode
	 * @return
	 */
	@Override
	public Integer getParentLBCode(String selectedLocalBodyCode) {
		Integer parentLBCode = 0;
		if(selectedLocalBodyCode != null && !"".equals(selectedLocalBodyCode)){
			try{
				String[] lbCodeArray = selectedLocalBodyCode.split(",");
				parentLBCode = Integer.parseInt( lbCodeArray[lbCodeArray.length-1] );
			} catch(NumberFormatException e){
				parentLBCode = 0;
			}
		}
		return parentLBCode;
	}
	
	/**
	 * 
	 * @param localBodyForm
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	private void uploadFiles(LocalBodyForm localBodyForm) throws NumberFormatException, Exception {
		List<CommonsMultipartFile> files = localBodyForm.getGazettePublicationUpload();
		if(files != null && !files.isEmpty()){
			List<Attachment> attachments = localBodyUtil.uploadFileToServer(files, Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO.toString()));
			if(!attachments.isEmpty()){
				Attachment attachment = attachments.get(0);
				localBodyForm.setOrderPath(attachment.getFileTimestamp());
				localBodyForm.setOrderFileSize(attachment.getFileSize());
				localBodyForm.setOrderFileContentType(attachment.getFileContentType());
			}
		}
		List<CommonsMultipartFile> mapuploadFiles = localBodyForm.getMapUpload();
		if(mapuploadFiles != null && !mapuploadFiles.isEmpty()){
			List<Attachment> mapAttachments = localBodyUtil.uploadFileToServer(mapuploadFiles, Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_MAP.toString()));
			if(!mapAttachments.isEmpty()){
				Attachment mapAttachment = mapAttachments.get(0);
				localBodyForm.setMapUploadPath(mapAttachment.getFileTimestamp());
				localBodyForm.setMapFileSize(mapAttachment.getFileSize());
				localBodyForm.setMapFileContentType(mapAttachment.getFileContentType());
			}
		}
	}
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private DraftLocalbodyTemp convertLocalBodyFormToDraftBean (LocalBodyForm localBodyForm) throws IllegalAccessException, InvocationTargetException {
		DraftLocalbodyTemp toBean = new DraftLocalbodyTemp();
		try {
			/*BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance(); 
			localBodyUtil.setConverterAttributes();
		    beanUtilsBean.copyProperties(toBean, localBodyForm);*/
			localBodyUtil.copyBeanAttributes(toBean, localBodyForm);
			if(localBodyForm.getIsResetedCoverage() == null || localBodyForm.getIsResetedCoverage()){
				toBean.setContributingLandRegionCodes(getConcatedLandRegionCodes(localBodyForm));
			} else {
				toBean.setContributingLBCodes(localBodyForm.getHidContributingLBCodes());
				toBean.setContributingLandRegionCodes(localBodyForm.getHidContributingLandRegionCodes());
			}
			toBean.setCoordinates(concatedCoordinates(localBodyForm.getLongitude(), localBodyForm.getLatitude()));
			toBean.setParentLocalBodyCode(getParentLBCode(localBodyForm.getLocalBodyLevelCodes()));
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		}
		return toBean;
	}
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private DraftChangeCoverageTemp convertLocalBodyFormToChangeCoverageBean (LocalBodyForm localBodyForm) throws IllegalAccessException, InvocationTargetException {
		DraftChangeCoverageTemp toBean = new DraftChangeCoverageTemp();
		try {
			localBodyUtil.copyBeanAttributes(toBean, localBodyForm);
			if(localBodyForm.getIsResetedCoverage() == null || localBodyForm.getIsResetedCoverage()){
				toBean.setContributingLandRegionCodes(getConcatedLandRegionCodes(localBodyForm));
			} else {
				toBean.setContributingLBCodes(localBodyForm.getHidContributingLBCodes());
				toBean.setContributingLandRegionCodes(localBodyForm.getHidContributingLandRegionCodes());
			}
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		}
		return toBean;
	}
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 */
	private String getConcatedLandRegionCodes(LocalBodyForm localBodyForm){
		String headquarterCode = localBodyForm.getHeadQuarterCode();
		StringBuilder finalLandRegions = new StringBuilder();
		
		String lbCodes = localBodyForm.getContributingLBCodes();
		if(StringUtils.contains(lbCodes, "_FULL")){
			String typeCode = localBodyForm.getLocalBodyTypePanchayat() != null ? localBodyForm.getLocalBodyTypePanchayat().split("\\_")[3] : null;
			typeCode = LocalBodyConstant.INTERMEDIATE_PANCHAYAT_LEVEL.toString().equals(typeCode) ? 
					   LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString() : typeCode;
			List<CoverageDetailsLocalBody> coverageOfFull = fetchCoverageDetailsLocalBody(getFullLBCodesA(lbCodes), typeCode);
			finalLandRegions.append(concatedLandRegionCodesForFULLLB(coverageOfFull, headquarterCode));
		}
				
		finalLandRegions.append(concatedLandRegionCodes(localBodyForm.getContributingLBDistrictCodes(), 
														LocalBodyConstant.DISTRICT_CONSTANT.toString(),
														headquarterCode));
		
		finalLandRegions.append(concatedLandRegionCodes(localBodyForm.getContributingLBSubDistrictCodes(), 
														LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString(),
														headquarterCode));
		
		finalLandRegions.append(concatedLandRegionCodes(localBodyForm.getContributingLBVillageCodes(), 
														LocalBodyConstant.VILLAGE_CONSTANT.toString(),
														headquarterCode));
		
		finalLandRegions.append(concatedLandRegionCodes(localBodyForm.getContributingUnmappedDistrictCodes(), 
														LocalBodyConstant.DISTRICT_CONSTANT.toString(),
														headquarterCode));
		
		finalLandRegions.append(concatedLandRegionCodes(localBodyForm.getContributingUnmappedSubDistrictCodes(), 
														LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString(),
														headquarterCode));
		
		finalLandRegions.append(concatedLandRegionCodes(localBodyForm.getContributingUnmappedVillageCodes(), 
														LocalBodyConstant.VILLAGE_CONSTANT.toString(),
														headquarterCode));
		
		String contributingLRCodes = finalLandRegions.toString();
		if(contributingLRCodes.endsWith(",")){
			contributingLRCodes = contributingLRCodes.substring(0, contributingLRCodes.length()-1);
		}
		return StringUtils.isEmpty(contributingLRCodes) ? null : contributingLRCodes;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private String getFullLBCodesA(String input){
		Collection<String> collLandregionCodes = new ArrayList<String>();
		for(String ss: input.split("\\,")){
			if(ss.toString().contains("_FULL")){
				collLandregionCodes.add(ss.replace("_FULL", ""));
			}
		}
		return StringUtils.join(collLandregionCodes, ",");
	}
	
	/**
	 * 
	 * @param fullLBCoverages
	 * @param headquarterCode
	 * @return
	 */
	private String concatedLandRegionCodesForFULLLB(List<CoverageDetailsLocalBody> fullLBCoverages, final String headquarterCode){
		String completeLRCodes = "";
		Transformer transformer = new Transformer() {
			@Override
			public String transform(Object input) {
				StringBuilder output = new StringBuilder();
				CoverageDetailsLocalBody coverageDetailsLB = (CoverageDetailsLocalBody) input;
				output.append(coverageDetailsLB.getLandRegionCode()).append("_");
				output.append(coverageDetailsLB.getCoverageType().equals("P") ? "PART" : "FULL").append("_");
				output.append(coverageDetailsLB.getLandRegionType()).append("_");
				
				if(coverageDetailsLB.getLandRegionCode().toString().equals(headquarterCode)){
					output.append( true );
				} else {
					output.append( false );
				}
				return output.toString();
			}
		};
		@SuppressWarnings("unchecked")
		Collection<String> collLandregionCodes = CollectionUtils.collect(fullLBCoverages, transformer);
		completeLRCodes = StringUtils.join(collLandregionCodes, ",");
		return !"".equals(completeLRCodes.trim()) ? completeLRCodes.concat(",") : "";
	}
	
	
	/**
	 * 
	 * @param input
	 * @param panchayatType
	 * @param headquarterCode
	 * @return
	 */
	private String concatedLandRegionCodes(String input, final String panchayatType, final String headquarterCode){
		String completeLRCodes = "";
		if(input != null){
			Transformer transformer = new Transformer() {
				@Override
				public String transform(Object input) {
					StringBuilder output = new StringBuilder((String) input);
					output.append( "_" ).append( panchayatType );
					if(output.toString().split("_")[0].equals(headquarterCode)){
						output.append( "_" ).append( true );
					} else {
						output.append( "_" ).append( false );
					}
					return output.toString();
				}
			};
			@SuppressWarnings("unchecked")
			Collection<String> collLandregionCodes = CollectionUtils.collect(Arrays.asList(input.split(",")), transformer);
			completeLRCodes = StringUtils.join(collLandregionCodes, ",");
		}
		return !"".equals(completeLRCodes.trim()) ? completeLRCodes.concat(",") : "";
	}
	
	/**
	 * 
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	private String concatedCoordinates(String longitude, String latitude){
		String coordinates = null; 
		longitude = longitude != null ? longitude : "";
		latitude = latitude != null ? latitude : "";
		if(!"".equals(longitude) && !"".equals(latitude)){
			String[] longitudeArray = longitude.trim().split(",");
			String[] latitudeArray = latitude.trim().split(",");
			if(longitudeArray.length == latitudeArray.length){
				List<String> arrCoordinates = new ArrayList<String>();
				for(int i = 0; i < longitudeArray.length; i++){
					arrCoordinates.add(longitudeArray[i].concat(":").concat(latitudeArray[i]));
				}
				coordinates = StringUtils.join(arrCoordinates, ",");
			}
		}
		return coordinates;
	}

	/**
	 * 
	 * @param form
	 * @param draftLocalbodyTemp
	 * @return
	 */
	private List<DraftUsedLbLrTemp> getUsedLBLRCodes(LocalBodyForm form, DraftLocalbodyTemp draftLocalbodyTemp){
		List<DraftUsedLbLrTemp> completeListUsedLBLR = new ArrayList<DraftUsedLbLrTemp>();
		//completeListUsedLBLR.addAll(buildUsedLocalBodyData(draftLocalbodyTemp, form.getContributingLBCodes(), LocalBodyConstant.LOCAL_BODY_CONSTANT.toString()));
		completeListUsedLBLR.addAll(buildUsedLocalBodyData(draftLocalbodyTemp, draftLocalbodyTemp.getContributingLBCodes(), LocalBodyConstant.LOCAL_BODY_CONSTANT.toString()));
				
		//Adding All Used Land Region Codes from Existing and Unmapped / Partially Mapped Local Bodies
		completeListUsedLBLR.addAll(buildUsedLandRegionData(draftLocalbodyTemp, draftLocalbodyTemp.getContributingLandRegionCodes()));
		
		
		return completeListUsedLBLR;
	}
	
	/**
	 * 
	 * @param form
	 * @param draftChangeCoverageTemp
	 * @return
	 */
	private List<DraftUsedChangeCoverageLbLrTemp> getUsedChangeCoverageLBLRCodes(LocalBodyForm form, DraftChangeCoverageTemp draftChangeCoverageTemp){
		List<DraftUsedChangeCoverageLbLrTemp> completeListUsedLBLR = new ArrayList<DraftUsedChangeCoverageLbLrTemp>();
		completeListUsedLBLR.addAll(buildUsedChangeCoverageData(draftChangeCoverageTemp, draftChangeCoverageTemp.getContributingLBCodes(), LocalBodyConstant.LOCAL_BODY_CONSTANT.toString()));
	
		//Adding All Used Land Region Codes from Existing and Unmapped / Partially Mapped Local Bodies
		completeListUsedLBLR.addAll(buildChangeCoverageUsedLRData(draftChangeCoverageTemp, draftChangeCoverageTemp.getContributingLandRegionCodes()));
		
		return completeListUsedLBLR;
	}
	
	/**
	 * 
	 * @param draftLocalbodyTemp
	 * @param input
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<DraftUsedLbLrTemp> buildUsedLandRegionData(final DraftLocalbodyTemp draftLocalbodyTemp, String input){
		Collection<DraftUsedLbLrTemp> draftedLBLRCodes = new ArrayList<DraftUsedLbLrTemp>();
		if(input != null && !"".equals(input)){
			Transformer transformer = new Transformer() {
				@Override
				public DraftUsedLbLrTemp transform(Object input) {
					System.out.println(input);
					String[] arr = ((String) input).split("\\_");
					DraftUsedLbLrTemp temp = new DraftUsedLbLrTemp();
					temp.setLbLrCode((arr[0] == null ? 0 : Integer.valueOf(arr[0])));
					temp.setLbLrType(arr[2]);
					temp.setDraftLocalbodyTemp(draftLocalbodyTemp);
					return temp;
				}
			};
			draftedLBLRCodes = CollectionUtils.collect(Arrays.asList(input.split(",")), transformer);
		}
		return draftedLBLRCodes;
	}
	
	/**
	 * 
	 * @param draftChangeCoverageTemp
	 * @param input
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<DraftUsedChangeCoverageLbLrTemp> buildChangeCoverageUsedLRData(final DraftChangeCoverageTemp draftChangeCoverageTemp, String input){
		Collection<DraftUsedChangeCoverageLbLrTemp> draftedLBLRCodes = new ArrayList<DraftUsedChangeCoverageLbLrTemp>();
		if(input != null && !"".equals(input)){
			Transformer transformer = new Transformer() {
				@Override
				public DraftUsedChangeCoverageLbLrTemp transform(Object input) {
					System.out.println(input);
					String[] arr = ((String) input).split("\\_");
					DraftUsedChangeCoverageLbLrTemp temp = new DraftUsedChangeCoverageLbLrTemp();
					temp.setLbLrCode((arr[0] == null ? 0 : Integer.valueOf(arr[0])));
					temp.setLbLrType(arr[2]);
					temp.setDraftChangeCoverageTemp(draftChangeCoverageTemp);
					return temp;
				}
			};
			draftedLBLRCodes = CollectionUtils.collect(Arrays.asList(input.split(",")), transformer);
		}
		return draftedLBLRCodes;
	}

	/**
	 * 
	 * @param draftLocalbodyTemp
	 * @param input
	 * @param lbLrType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<DraftUsedLbLrTemp> buildUsedLocalBodyData(final DraftLocalbodyTemp draftLocalbodyTemp, String input, final String lbLrType){
		Collection<DraftUsedLbLrTemp> draftedLBLRCodes = new ArrayList<DraftUsedLbLrTemp>();
		if(input != null && !"".equals(input)){
			input = input.replace("_PART", "").replace("_FULL", "");
			Transformer transformer = new Transformer() {
				@Override
				public DraftUsedLbLrTemp transform(Object input) {
					DraftUsedLbLrTemp temp = new DraftUsedLbLrTemp();
					temp.setLbLrCode((input == null ? 0 : Integer.valueOf((String)input)));
					temp.setLbLrType(lbLrType);
					temp.setDraftLocalbodyTemp(draftLocalbodyTemp);
					return temp;
				}
			};
			draftedLBLRCodes = CollectionUtils.collect(Arrays.asList(input.split(",")), transformer);
		}
		return draftedLBLRCodes;
	}
	
	/**
	 * 
	 * @param draftChangeCoverageTemp
	 * @param input
	 * @param lbLrType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<DraftUsedChangeCoverageLbLrTemp> buildUsedChangeCoverageData(final DraftChangeCoverageTemp draftChangeCoverageTemp, String input, final String lbLrType){
		Collection<DraftUsedChangeCoverageLbLrTemp> draftedLBLRCodes = new ArrayList<DraftUsedChangeCoverageLbLrTemp>();
		if(input != null && !"".equals(input)){
			input = input.replace("_PART", "").replace("_FULL", "");
			Transformer transformer = new Transformer() {
				@Override
				public DraftUsedChangeCoverageLbLrTemp transform(Object input) {
					DraftUsedChangeCoverageLbLrTemp temp = new DraftUsedChangeCoverageLbLrTemp();
					temp.setLbLrCode((input == null ? 0 : Integer.valueOf((String)input)));
					temp.setLbLrType(lbLrType);
					temp.setDraftChangeCoverageTemp(draftChangeCoverageTemp);
					return temp;
				}
			};
			draftedLBLRCodes = CollectionUtils.collect(Arrays.asList(input.split(",")), transformer);
		}
		return draftedLBLRCodes;
	}
	
	/******* Manage Local Body Function(s) started Here *******/

	@Override
	public List<ManageLBDetails> getLocalBodiesForManage(String lbCreationType, Integer lbTypeCode, Integer stateCode, String localBodyLevelCodes, Integer districtCode) {
		// TODO Auto-generated method stub
		return localBodyDao.getLocalBodiesForManage(lbCreationType, lbTypeCode, stateCode, getParentLBCode(localBodyLevelCodes), districtCode);
	}

	@Override
	public Map<String, Object> viewLocalBodyDetails(Integer localBodyCode) {
		// TODO Auto-generated method stub
		return localBodyDao.viewLocalBodyDetails(localBodyCode);
	}

	@Override
	public LocalBodyForm getLocalBodyFormObject(Integer localbodyCode) throws HibernateException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		LocalBodyForm toBean = new LocalBodyForm();
		LocalBodyTable dbObject = localBodyDao.getLocalBodyFormObject(localbodyCode);
		
		localBodyUtil.copyBeanAttributes(toBean, dbObject);
		toBean.setiParamEffectiveDate(dbObject.getEffectiveDate());
		toBean.setStateCode(dbObject.getSlc());
		toBean.setEffectiveDate(null);
		return toBean;
	}
	
	public Integer getLBCodeByParentCode(Integer parentCode) throws HibernateException{
		// TODO Auto-generated method stub
		return localBodyDao.getLBCodeByParentCode(parentCode);
	}
	
	@Override
	public DraftRenameLocalbodyTemp getDraftRenameLocalbodyTemp(Integer entityTempId) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getDraftRenameLocalbodyTemp(entityTempId);
	}
	
	@Override
	public Boolean saveRenameLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception{
		// TODO Auto-generated method stub
		uploadFiles(localBodyForm);
		return localBodyDao.saveRenameLocalBodyAsDraft(localBodyForm);
	}

	@Override
	public Boolean publishRenameLocalBody(LocalBodyForm localBodyForm) throws Exception {
		// TODO Auto-generated method stub
		uploadFiles(localBodyForm);
		return localBodyDao.publishRenameLocalBody(localBodyForm);
	}

	@Override
	public List<CriteriaDraftedEntities> fetchDraftedEntities(String PANCHAYAT_TYPE, Integer localBodyTypeCode, Integer stateCode, String localBodyName, Integer processCode, Integer districtCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.fetchDraftedEntities(PANCHAYAT_TYPE, localBodyTypeCode, stateCode, localBodyName, processCode, districtCode);
	}

	@Override
	public Map<String, Object> fetchDraftedRenamedLB(Integer tempEntityCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.fetchDraftedRenamedLB(tempEntityCode);
	}

	@Override
	public Boolean publishOrDeleteDraftRenameLBToTransaction(Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return localBodyDao.publishOrDeleteDraftRenameLBToTransaction(tempEntityCode, isDeleteStatus, request);
	}

	@Override
	public LBAttributes getLBDetailsForModifyParent(Integer lbTypeCode, Integer parentLBCode, Integer stateCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getLBDetailsForModifyParent(lbTypeCode, parentLBCode, stateCode);
	}

	@Override
	public Boolean saveChangeParentLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		uploadFiles(localBodyForm);
		return localBodyDao.saveChangeParentLocalBodyAsDraft(localBodyForm);
	}

	@Override
	public String publishChangeParentLocalBody(LocalBodyForm localBodyForm) throws Exception {
		// TODO Auto-generated method stub
		uploadFiles(localBodyForm);
		return localBodyDao.publishChangeParentLocalBody(localBodyForm);
	}

	@Override
	public Map<String, Object> fetchDraftedChangeParentLB(Integer tempEntityCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.fetchDraftedChangeParentLB(tempEntityCode);
	}
	
	@Override
	public DraftChangeParentLocalbodyTemp getDraftChangeParentLocalbodyTemp(Integer entityTempId) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getDraftChangeParentLocalbodyTemp(entityTempId);
	}

	@Override
	public String publishOrDeleteDraftChangeParentLBToTransaction(Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return localBodyDao.publishOrDeleteDraftChangeParentLBToTransaction(tempEntityCode, isDeleteStatus, request);
	}

	@Override
	public Map<String, Object> getLBDetailsForModifyType(Integer lbTypeCode, Integer stateCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getLBDetailsForModifyType(lbTypeCode, stateCode);
	}

	@Override
	public Boolean saveChangeTypeLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		uploadFiles(localBodyForm);
		return localBodyDao.saveChangeTypeLocalBodyAsDraft(localBodyForm);
	}

	@Override
	public Boolean publishChangeTypeLocalBody(LocalBodyForm localBodyForm) throws Exception {
		// TODO Auto-generated method stub
		uploadFiles(localBodyForm);
		return localBodyDao.publishChangeTypeLocalBody(localBodyForm);
	}

	@Override
	public Map<String, Object> fetchDraftedChangeTypeLB(Integer tempEntityCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.fetchDraftedChangeTypeLB(tempEntityCode);
	}

	@Override
	public DraftChangeTypeLocalbodyTemp getDraftChangeTypeLocalbodyTemp(Integer entityTempId) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getDraftChangeTypeLocalbodyTemp(entityTempId);
	}

	@Override
	public Boolean publishOrDeleteDraftChangeTypeLBToTransaction(Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return localBodyDao.publishOrDeleteDraftChangeTypeLBToTransaction(tempEntityCode, isDeleteStatus, request);
	}

	@Override
	public Map<String, Object> getLBDetailsForModifyGovOrder(Integer localBodyCode, Integer stateCode, String PANCHAYAT_TYPE_CONSTANT) throws Exception {
		// TODO Auto-generated method stub
		return localBodyDao.getLBDetailsForModifyGovOrder(localBodyCode, stateCode, PANCHAYAT_TYPE_CONSTANT);
	}

	@Override
	public Boolean saveChangeGOLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		uploadFiles(localBodyForm);
		localBodyForm.setCoordinates(concatedCoordinates(localBodyForm.getLongitude(), localBodyForm.getLatitude()));
		return localBodyDao.saveChangeGOLocalBodyAsDraft(localBodyForm);
	}

	@Override
	public Boolean publishChangeGOLocalBody(LocalBodyForm localBodyForm) throws HibernateException {
		// TODO Auto-generated method stub
		//uploadFiles(localBodyForm);
		return null;
	}

	@Override
	public DraftChangeGovtOrderLBTemp getDraftChangeGovtOrderLBTemp(Integer tempEntityCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.getDraftChangeGovtOrderLBTemp(tempEntityCode);
	}

	@Override
	public Boolean publishOrDeleteDraftChangeGOToTransaction(Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return localBodyDao.publishOrDeleteDraftChangeGOToTransaction(tempEntityCode, isDeleteStatus, request);
	}

	@Override
	public LocalBodyForm setGOandMapFileAttributes(LocalBodyForm localBodyForm) throws Exception {
		// TODO Auto-generated method stub
		return localBodyDao.setGOandMapFileAttributes(localBodyForm);
	}
	
	@Override
	public Map<String, Object> changeCoverageDefaultDetails(LocalBodyForm localBodyForm) throws HibernateException {		
		return localBodyDao.changeCoverageDefaultDetails(localBodyForm);
	}
	
	@Override
	public String publishChangeCoveredArea(LocalBodyForm localBodyForm, HttpServletRequest request) throws  Exception {
		// TODO Auto-generated method stub
		if(localBodyForm.getIsResetedCoverage() == null || localBodyForm.getIsResetedCoverage()){
			localBodyForm.setContributingLandRegionCodes(getConcatedLandRegionCodes(localBodyForm));
		} else {
			localBodyForm.setContributingLBCodes(localBodyForm.getHidContributingLBCodes());
			localBodyForm.setContributingLandRegionCodes(localBodyForm.getHidContributingLandRegionCodes());
		}
		localBodyForm.setCoordinates(concatedCoordinates(localBodyForm.getLongitude(), localBodyForm.getLatitude()));
		localBodyForm.setParentLocalBodyCode(getParentLBCode(localBodyForm.getLocalBodyLevelCodes()));
		uploadFiles(localBodyForm);
		localBodyUtil.convertTemplatetoPDF(localBodyForm, request);
		return localBodyDao.publishChangeCoveredArea(localBodyForm);
	}

	@Override
	public Boolean saveChangeCoveredAreaAsDraft(LocalBodyForm localBodyForm) throws Exception {
		uploadFiles(localBodyForm);
		DraftChangeCoverageTemp draftChangeCoverageTemp = convertLocalBodyFormToChangeCoverageBean(localBodyForm);
		draftChangeCoverageTemp.setUsedLBLRList(getUsedChangeCoverageLBLRCodes(localBodyForm, draftChangeCoverageTemp));
		return localBodyDao.saveChangeCoveredAreaAsDraft(draftChangeCoverageTemp);
	}
	
	@Override
	public Map<String, Object> getDraftedChangeCoverageDetails(Integer tempChangeCoverageCode) throws Exception {		
		return localBodyDao.getDraftedChangeCoverageDetails(tempChangeCoverageCode);
	}

	@Override
	public Map<String, Object> getDraftedChangeCoverageModification(Integer tempChangeCoverageCode) throws Exception {
		// TODO Auto-generated method stub
		return localBodyDao.getDraftedChangeCoverageModification(tempChangeCoverageCode);
	}

	@Override
	public Boolean publishOrDeleteDraftToTransactionChangeCoverage(Integer tempChangeCoverageCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return localBodyDao.publishOrDeleteDraftToTransactionChangeCoverage(tempChangeCoverageCode, isDeleteStatus, request);
	}
	
	@Override
	public List<Object[]> getLBListOfSelectedLR(Integer landRegionCode, Integer lbtypecode) throws Exception{
		// TODO Auto-generated method stub
		return localBodyDao.getLBListOfSelectedLR( landRegionCode,  lbtypecode);
	}
	
	@Override
	public Map<String, Object> mapCoveredAreaDefaultDetails(LocalBodyForm localBodyForm) throws HibernateException{
		// TODO Auto-generated method stub
		return localBodyDao.mapCoveredAreaDefaultDetails(localBodyForm);
	}
	
	@Override
	public String updateMappedCoveredArea(LocalBodyForm localBodyForm, HttpServletRequest request) throws  Exception {
		// TODO Auto-generated method stub
		if(localBodyForm.getIsResetedCoverage() == null || localBodyForm.getIsResetedCoverage()){
			localBodyForm.setContributingLandRegionCodes(getConcatedLandRegionCodes(localBodyForm));
		} 
		return localBodyDao.updateMappedCoveredArea(localBodyForm);
	}

	@Override
	public List<String> fetchMimeType() {
		// TODO Auto-generated method stub
		return localBodyDao.fetchMimeType();
	}

	@Override
	public String getMappedLBsForGIS(Integer localBodyCode,String panchayatName,boolean isShowOnlyBoundary,boolean updateApprovedGP) throws IOException {
		// TODO Auto-generated method stub
		return localBodyDao.getMappedLBsForGIS(localBodyCode,panchayatName,isShowOnlyBoundary,updateApprovedGP);
	}

	@Override
	public String saveGISStatus(String statusFromGIS) {
		// TODO Auto-generated method stub
		return localBodyDao.saveGISStatus(statusFromGIS);
	}

	@Override
	public List<String> fetchPreviousNames(Integer localbodyCode) throws HibernateException {
		// TODO Auto-generated method stub
		return localBodyDao.fetchPreviousNames(localbodyCode);
	}

	@Override
	public List<LBTypeHierarchy> getLBTypeHierarchyStateWiseDetials(Integer stateCode) throws HibernateException {
		return localBodyDao.getLBTypeHierarchyStateWiseDetials(stateCode);
	}

	@Override
	public List<LBTypeDetailsWithCategory> buildLocalBodyHierarchyWithCategory(Integer setupCode, Integer setupVersion) throws HibernateException {
		return localBodyDao.buildLocalBodyHierarchyWithCategory(setupCode, setupVersion);
	}

	@Override
	public Boolean validateDistrictContainSubdistrict(Integer districtCode) {
		return localBodyDao.validateDistrictContainSubdistrict(districtCode);
	}
	
	
	/*
	  *  For Preview GIS Coverage#started @author Maneesh Kumar 30-12-2016
	 */
	@Override
	public String getMappedLBsForGISPreview(Integer localBodyCode, String panchayatName, String isShowOnlyBoundary, boolean updateApprovedGP, String deleteCode, String insertCode) throws IOException {
		return localBodyDao.getMappedLBsForGISPreview(localBodyCode, panchayatName, isShowOnlyBoundary, updateApprovedGP, deleteCode, insertCode);
	}

	@Override
	public List<LBTypeDetails> buildLBTypeList(Integer stateCode) throws HibernateException {
		return localBodyDao.buildLBTypeList(stateCode);
	}

	@Override
	public List<LocalBodyEntityDetails> getParentwiseLocalBodyRPT(Integer localBodyCode, Integer draftTempCode,Integer processId) throws HibernateException {
		return localBodyDao.getParentwiseLocalBodyRPT(localBodyCode, draftTempCode, processId);
	}

	@Override
	public List<LocalBodyEntityDetails> getDistrictPanchayatListRPT(Integer lbTypeCode, Integer stateCode,Integer draftTempCode, Integer processId) throws HibernateException {
		return localBodyDao.getDistrictPanchayatListRPT(lbTypeCode, stateCode, draftTempCode, processId);
	}

	@Override
	public String checkLocalbodyChangeParentInSameDistrict(Integer localBodyCode, Integer parentLblc)throws HibernateException {
	return localBodyDao.checkLocalbodyChangeParentInSameDistrict(localBodyCode, parentLblc);
	}

	@Override
	public List<UnmappedLandregions> fetchCoverageDetailsVillage(String unmappedCoverageLevel, String seltlcCode,String existvlcCodes, String localBodyType) throws HibernateException {
		List<Integer> existVillage =new ArrayList<Integer>();
		existVillage.add(-1);
		
		
		if (existvlcCodes!=null) {
			Scanner scanner = new Scanner(existvlcCodes);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				existVillage.add(Integer.parseInt(scanner.next()));
			}
			scanner.close();
		}
		return localBodyDao.fetchCoverageDetailsVillage(unmappedCoverageLevel, seltlcCode, existVillage, localBodyType);
	}
	
	@Override
	public boolean lbPartCoverageExistState(Integer stateCode, Character landRegionType, Integer lrlc) {
		return localBodyDao.lbPartCoverageExistState(stateCode, landRegionType, lrlc);
	}

	@Override
	public List<CompletedCoverageDetails> fetchExistingCoverage(Integer localBodyCode) throws HibernateException {
		return localBodyDao.fetchExistingCoverage(localBodyCode);
	}

	@Override
	public List<LocalBodyDetailDto> getPreviousAttachedFilesbyLblc(Integer localBodyCode) throws HibernateException {
		return localBodyDao.getPreviousAttachedFilesbyLblc(localBodyCode);
	}
	
	
	@Override
	public Boolean saveLocalBodyForm(LocalBodyForm localBoadyCode) throws HibernateException {
		return localBodyDao.saveLocalBodyForm( localBoadyCode);
	}
	
	
	@Override
	public List<Object[]> getOrderCodeThroughLblc(Integer localBoadyCode) {
		return localBodyDao.getOrderCodeThroughLblc( localBoadyCode);
	}

	@Override
	public List<Object[]> getLocalbodiesCoveredlrlc(Integer landRegionCode, String landRegionType) throws Exception {
		return localBodyDao.getLocalbodiesCoveredlrlc(landRegionCode,landRegionType);
	}

	@Override
	public void saveLocalbodyCoverageType(Integer lbCode, String coverageList,Integer userId) throws Exception {
		localBodyDao.saveLocalbodyCoverageType(lbCode, coverageList,userId);
		
	}

	@Override
	public Response saveEffectiveDateEntityLB(List<GetEntityEffectiveDate> getEntityEffectiveDateList, Integer userId) {
		return localBodyDao.saveEffectiveDateEntityLB(getEntityEffectiveDateList, userId);
	}

	

	
}
