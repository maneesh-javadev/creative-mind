package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.BlockVillage;
import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.BlockDataForm;
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.service.BlockService;
import in.nic.pes.lgd.service.CommonService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component("BlockValidator")
public class BlockValidator implements Validator {
	
	private static final Logger log = Logger.getLogger(BlockValidator.class);
	
	private @Autowired
	CommonService commonService;
	
	@Autowired
	BlockService blockService;
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return BlockForm.class.isAssignableFrom(clazz);

	}

	public BlockForm validateBlockChange(Object object, Errors errors) {
		// modify Block
		BlockForm blockForm = (BlockForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		try {
			List<BlockDataForm> blockDataFormList = blockForm
					.getListBlockDetails();
			if (!blockDataFormList.isEmpty()) {
				BlockDataForm element = blockDataFormList.get(0);
				String blockNameEngch = element.getBlockNameEnglishch();
				String blockNameEng = element.getBlockNameEnglish();
				String blockNameLocal = element.getBlockNameLocalch();
				String NameAlias = element.getAliasEnglishch();
				String NameLocalAlias = element.getAliasLocalch();
				Integer dlc=blockForm.getDlc();

			
				if(blockNameEngch=="" || blockNameEngch.isEmpty())
				{
					errors.rejectValue("blockNameEnglishch","Error.blank.BlockNameEng");
					blockForm.setErrorflag(2);
				}
				else 	if (!customValidator.checkforAlphabetWithSpaceDotandSlash(blockNameEngch)) {
					errors.rejectValue("blockNameEnglishch","Error.data.BlockNameEng");
				}
				else if(blockNameEngch.equalsIgnoreCase(blockNameEng))
				{
					errors.rejectValue("errorflag","error.change.commonAlert");
					blockForm.setErrorflag(1);
				}
				else if(dlc!=null)
				{
					if(commonService.existEntityName(dlc, 'B', blockNameEngch))
					{
						errors.rejectValue("blockNameEnglishch","error.alredyExist.blockNameEng");
					}
				}
				
				
				if (!blockNameLocal.isEmpty()) {
					if (!customValidator
							.validateSpecialCharacters(blockNameLocal)) {
						errors.rejectValue("blockNameLocalch","Error.data.BlockNameLc");
					}
				}

				if (!NameAlias.isEmpty()) {
					if (!customValidator.checkforAlphabetsandSpace(NameAlias)) {
						errors.rejectValue("aliasEnglishch","Error.data.BlockAliasNameEng");
					}
				}

				

				if (!NameLocalAlias.isEmpty()) {
					if (!customValidator
							.validateSpecialCharacters(NameLocalAlias)) {
						errors.rejectValue("aliasLocalch","Error.data.BlockAliasNameLocal");
					}
				}

			}
			

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return blockForm;
		
	}

	
	public void validateBlockCorrection(Object object, Errors errors) {
		// modify Block
		BlockForm blockForm = (BlockForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		Date today = new Date();
		String govtOrderConfig = blockForm.getGovtOrderConfig();
		try {
			if (!blockForm.getListBlockDetails().isEmpty()) {
				BlockDataForm blockDataForm = blockForm.getListBlockDetails()
						.get(0);

				String aliasNameInEn = blockDataForm.getAliasEnglishch();
				if (aliasNameInEn != null) {
					if (!(aliasNameInEn.isEmpty())) {
						if (!customValidator
								.checkforAlphabetsandSpace(aliasNameInEn)) {
							errors.rejectValue("aliasEnglishch", 									"Error.BlockAliasNameEngData");
						}
					}
				}

				String aliasNameInLc = blockDataForm.getAliasLocalch();
				if (aliasNameInLc != null) {
					if (!(aliasNameInLc.isEmpty())) {
						if (!customValidator
								.validateSpecialCharacters(aliasNameInLc)) {
							errors.rejectValue("aliasLocalch", "error.ms",
									"Error.BlocktAliasNameLocalData");
						}
					}
				}

				String headquterNameEng = blockDataForm.getHeadquarterName();
				if (!customValidator
						.checkforAlphabetsandSpace(headquterNameEng)
						&& !(headquterNameEng.isEmpty())) {
					errors.rejectValue("headquarterName", "error.ms",
							"This field contains invalid characters.Please use A-Z or a-z or space.");
				}

				String headquterNameLc = blockDataForm
						.getHeadquarterNameLocal();
				if (!customValidator.validateSpecialCharacters(headquterNameLc)
						&& !(headquterNameLc.isEmpty())) {
					errors.rejectValue("headquarterNameLocal", "error.ms",
							"This field contains invalid characters.");
				}

				String stateSpecificCode = blockDataForm.getSsCode();
				if (!stateSpecificCode.isEmpty()) {
					if (stateSpecificCode.length() > 5) {
						errors.rejectValue("ssCode", "Error.Sscode");
					}
					if (!customValidator.checkforAlphaNumericand(stateSpecificCode)) {
						errors.rejectValue("ssCode", "Please enter alphanumerics only");
					}
				}

				String letitude = blockForm.getLati();
				if (letitude != null) {
					if (!letitude.isEmpty()) {
						if (!customValidator.checkforLatiandLongi(letitude)) {
							errors.rejectValue("lati",
									"error.valid.COMMONLONGANDLATVALUE",
									"Latitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
						}
					}
				}

				String longitude = blockForm.getLongi();
				if (longitude != null) {
					if (!longitude.isEmpty()) {
						if (!customValidator.checkforLatiandLongi(longitude)) {
							errors.rejectValue("longi",
									"error.valid.COMMONLONGANDLATVALUE",
									"Longitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
						}
					}
				}

				String orderNo = blockDataForm.getOrderNocr();
				Date orderDate = blockDataForm.getOrderDatecr();
				Date effectiveDate = blockDataForm.getOrdereffectiveDatecr();
				Date guzPubDate = blockDataForm.getGazPubDatecr();
				List<CommonsMultipartFile> attchGovermentOrder = blockForm
						.getAttachFile1();

				String strFileCount=blockForm.getGovtfilecount();
				Integer fileCount=0;
				if(strFileCount!=null)
				{
					fileCount=Integer.parseInt(strFileCount);
				}
				if (!(orderNo.equalsIgnoreCase("")) || orderDate != null
						|| effectiveDate != null || guzPubDate != null
						|| (attchGovermentOrder!=null)) {
					if (!(orderNo.equalsIgnoreCase("")) || orderDate != null
							|| effectiveDate != null || guzPubDate != null
							|| (!attchGovermentOrder.get(0).isEmpty())) {

						
							if (orderNo == null || orderNo.isEmpty()) {
								try {
									errors.rejectValue("orderNocr",
											"ORDERNO.REQUIRED");
								} catch (Exception e) {
									errors.rejectValue("orderNocr",
											"ORDERNO.REQUIRED");
								}

							} else if (!customValidator
									.checkforOrderNum(orderNo)) {
								errors.rejectValue("orderNocr", "error.ms",
										"Order Number contains invalid characters");
							}

							if (orderDate == null) {
								try {
									errors.rejectValue("orderDatecr",
											"orderDate.required");
								} catch (Exception e) {
									errors.rejectValue("orderDatecr",
											"orderDate.required");
								}

							} else {
								if (!customValidator.checkforDate(orderDate)) {

									errors.rejectValue("orderDatecr",
											"error.valid.ORDERDATE");
								}

								if (orderDate.compareTo(today) > 0) {
									errors.rejectValue("orderDatecr",
											"error.valid.morecurrentDate");
								}
							}
							if (effectiveDate == null) {
								try {
									errors.rejectValue("ordereffectiveDatecr",
											"ordereffectiveDate.required");
								} catch (Exception e) {
									errors.rejectValue("ordereffectiveDatecr",
											"ordereffectiveDate.required");
								}

							} else if (effectiveDate.before(orderDate)
									&& !effectiveDate.equals(orderDate)) {
								errors.rejectValue("ordereffectiveDatecr",
										"error.INCORECT.EFFECTIVEDATE");
							} else if (!customValidator
									.checkforDate(effectiveDate)) {
								errors.rejectValue("ordereffectiveDatecr",
										"error.valid.EFFECTIVEDATE");
							}

							if(fileCount<=0)
							{
								if (govtOrderConfig.equals("govtOrderUpload")) {

									if (blockForm.getAttachFile1().isEmpty()) {
										try {
											errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
										} catch (Exception e) {
											errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
										}

									}
									else if(blockForm.getAttachFile1().get(0).isEmpty())
									{
										errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
									}

								} else if (govtOrderConfig.equals("govtOrderGenerate")) {

									if (blockForm.getTemplateList() != null) {
										int templateCode = Integer.parseInt(blockForm.getTemplateList());
										if (templateCode < 1) {
											errors.rejectValue("templateList", "Msg.GovtOrderGenerate");
										}
									}
								}
							}

					}

				}

			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
	}
	
	public void invalidateBlockValidator(BlockForm blockForm,
			BindingResult result) {

		if (blockForm != null) {
			if (blockForm.getDistrictCode() == null
					|| blockForm.getDistrictCode().isEmpty()
					|| blockForm.getDistrictCode().toString().equals("0")) {
				result.rejectValue("districtCode", "error.PSDT");
			}
			if (blockForm.getBlockList() == null
					|| blockForm.getBlockList().isEmpty()
					|| blockForm.getBlockList().toString().equals("0")) {
				result.rejectValue("blockList", "error.PSBLK");
			}
			if (!blockForm.isRdoBlockDelete()) {
				if (blockForm.getTargetDistrictCodeYes() == null
						|| blockForm.getTargetDistrictCodeYes().isEmpty()
						|| blockForm.getTargetDistrictCodeYes().toString()
								.equals("0")) {
					result.rejectValue("targetDistrictCodeYes", "error.PSTDT");
				}
			}
			/*
			 * if (!blockForm.isRdoBlockDelete()) { if (blockForm.getBlockYes()
			 * == null || blockForm.getBlockYes().isEmpty() ||
			 * blockForm.getBlockYes().toString().equals("0")) {
			 * result.rejectValue("blockYes", "error.PSTBLK"); } }
			 */
			/*if (blockForm.isRdoBlockDelete()) {
				if (blockForm.getTargetDistrictCodeNo() == null
						|| blockForm.getTargetDistrictCodeNo().isEmpty()
						|| blockForm.getTargetDistrictCodeNo().toString()
								.equals("0")) {
					result.rejectValue("targetDistrictCodeNo", "error.PSTDT");
				}
				
				 * if (blockForm.getBlockNo() == null ||
				 * blockForm.getBlockNo().isEmpty() ||
				 * blockForm.getBlockNo().toString().equals("0")) {
				 * result.rejectValue("blockNo", "error.PSTBLK"); }
				 
			}*/

		}
	}

	public void viewValidateBlock(Object object, Errors errors) {
		BlockForm blockForm = (BlockForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		if (blockForm.getBlockNameEnglish() != null
				&& !blockForm.getBlockNameEnglish().equals("")) {
			if (!customValidator.checkforAlphabetsandSpace(blockForm
					.getBlockNameEnglish())) {
				errors.rejectValue("blockNameEnglish", "error.ms",
						"This field contains invalid characters.Please use A-Z or a-z");
			}
		}
		if ((blockForm.getCode() != null && !blockForm.getCode().equals(""))) {
			if (!customValidator.checkforOnlyNumeric(blockForm.getCode())) {
				errors.rejectValue("code", "error.ms",
						"This field contains invalid characters.Please use 0 to 9");

			}
		}
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
	public void validateBlocktoVillageMapping(Object object, Errors errors,Integer stateCode) {
		// modify Block
		BlockVillage blockVillage = (BlockVillage) object;
		String addBlkVillMapping = blockVillage.getVillageMappedListNew();
		String delBlkVillMapping = blockVillage.getVillageMappedListDel();
		if(!((addBlkVillMapping!=null  )||( delBlkVillMapping!=null ))){
			
			errors.rejectValue("villageMappedListNew", "error.ms",
					"Please mapped or unmapped of village for further action. ");
		
		}else if(addBlkVillMapping!=null) {
			
			Scanner scanner = new Scanner(addBlkVillMapping);
			StringBuilder sb =new StringBuilder("-1"); 
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				int value=Integer.parseInt(scanner.next().split("-")[0]);
				sb.append(","+value);
			}
			scanner.close();
			
			String blockDetails[]=blockVillage.getBlockCode().split("-");
			try{
			boolean flag=blockService.validateBlockVillageMapping(Integer.parseInt(blockDetails[0]), Integer.parseInt(blockDetails[1]), sb.toString());
			if(!flag){
				errors.rejectValue("villageMappedListNew", "error.ms","villages already exist in block");
			}
			}catch(Exception e){
				errors.rejectValue("villageMappedListNew", "error.ms",String.valueOf(e));
				log.error(e);
						
			}
		}
		
		
		
	}
}
