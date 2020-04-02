package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.ConvertRLBtoTLBForm;
import in.nic.pes.lgd.forms.ConvertRLBtoULBForm;
import in.nic.pes.lgd.forms.ConvertTLBtoRLBForm;
import in.nic.pes.lgd.service.ComboFillingService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("convertLocalBodyValidator")
public class ConvertLocalBodyValidator implements Validator {

	private static final Logger LOG = Logger.getLogger(ConvertLocalBodyValidator.class);
	@Autowired
	private ComboFillingService comboFillingService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ConvertRLBtoULBForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors, String govtSetUp, int stateCode) throws Exception {
		int districtP1 = 0;
		int districtP2 = 0;
		int urbanType = 0;
		try {
			CustomRegexValidator regexValidator = new CustomRegexValidator();
			ConvertRLBtoULBForm convertForm = (ConvertRLBtoULBForm) target;

			if (govtSetUp != null && !govtSetUp.isEmpty()) {
				if (convertForm.getDistrictLocalBodies() != null && convertForm.getDistrictLocalBodies().contains(",")) {
					String[] districtP = convertForm.getDistrictLocalBodies().split(",");
					districtP1 = Integer.parseInt(districtP[0].toString());
					districtP2 = Integer.parseInt(districtP[1].toString());
					if (govtSetUp.equalsIgnoreCase("DIV")) {
						if (districtP1 < 1) {
							errors.rejectValue("districtLocalBodies", "Error.DISTRICTLOCALBODY");
						} else if (!comboFillingService.getComboValuesforApp('L', "S#1", stateCode, districtP1)) {
							errors.rejectValue("districtLocalBodies", "Error.DISTRICTLOCALBODY");
						}
					}
					if (govtSetUp.equalsIgnoreCase("DV")) {
						if (districtP2 < 1) {
							errors.rejectValue("districtLocalBodies", "Error.DISTRICTLOCALBODY");
						} else if (!comboFillingService.getComboValuesforApp('L', "S#1", stateCode, districtP2)) {
							errors.rejectValue("districtLocalBodies", "Error.DISTRICTLOCALBODY");
						}
					}

				}
				if (convertForm.getIntermediateLocalBodies() != null && convertForm.getIntermediateLocalBodies().contains(",")) {
					String[] interP = convertForm.getIntermediateLocalBodies().split(",");
					int interP1 = Integer.parseInt(interP[0].toString());
					int interP2 = Integer.parseInt(interP[1].toString());
					if (govtSetUp.equalsIgnoreCase("DIV")) {
						if (interP1 < 1) {
							errors.rejectValue("intermediateLocalBodies", "Error.INTERMEDIATELOCALBODY");
						} else if (!comboFillingService.getComboValuesforApp('L', "L", districtP1, interP1)) {
							errors.rejectValue("intermediateLocalBodies", "Error.INTERMEDIATELOCALBODY");
						}
					}
					if (govtSetUp.equalsIgnoreCase("IV")) {
						if (interP2 < 1) {
							errors.rejectValue("intermediateLocalBodies", "Error.INTERMEDIATELOCALBODY");
						} else if (!comboFillingService.getComboValuesforApp('L', "S#2", stateCode, interP2)) {
							errors.rejectValue("intermediateLocalBodies", "Error.INTERMEDIATELOCALBODY");
						}
					}

				}

				if (convertForm.getVillagelocalbodyNameDest() == null && convertForm.getLbFull()=='N') {
					errors.rejectValue("villagelocalbodyNameDest", "Error.TARGETVILLAGELB");
				}
				String declareLB = convertForm.getDeclarenewULB();
				String mergeLB = convertForm.getMergeRLBtoULB();
				if (declareLB == null && mergeLB == null) {
					errors.rejectValue("mergeRLBtoULB", "error.select.RLBtoULBOption");
				}

				if (mergeLB != null) {
					if (convertForm.getUrbanLgTypeName() == null || convertForm.getUrbanLgTypeName().isEmpty()) {
						errors.rejectValue("urbanLgTypeName", "Error.URBANLBTYPE");
						convertForm.setMergeUlbClick(true);

					} else if (convertForm.getUrbanLgTypeName() != null && !convertForm.getUrbanLgTypeName().isEmpty()) {
						urbanType = Integer.parseInt(convertForm.getUrbanLgTypeName());
						if (urbanType < 1) {
							errors.rejectValue("urbanLgTypeName", "Error.URBANLBTYPE");
							convertForm.setMergeUlbClick(true);
						} else if (!comboFillingService.getComboValuesforApp('Z', "S#U", stateCode, urbanType)) {
							errors.rejectValue("urbanLgTypeName", "Error.INCORECT.URBANLBTYPE");
							convertForm.setMergeUlbClick(true);
						}

					}

					if (convertForm.getUrbanlocalBodyNameEnglish() == null || convertForm.getUrbanlocalBodyNameEnglish().isEmpty()) {
						errors.rejectValue("urbanlocalBodyNameEnglish", "Error.URBANLB");
						convertForm.setMergeUlbClick(true);
					} else if (convertForm.getUrbanlocalBodyNameEnglish() != null && !convertForm.getUrbanlocalBodyNameEnglish().isEmpty()) {
						int urbanName = Integer.parseInt(convertForm.getUrbanlocalBodyNameEnglish());
						if (urbanName < 1) {
							errors.rejectValue("urbanlocalBodyNameEnglish", "Error.URBANLB");
							convertForm.setMergeUlbClick(true);
						} else if (!comboFillingService.getComboValuesforApp('L', "S#" + urbanType, stateCode, urbanName)) {
							errors.rejectValue("urbanlocalBodyNameEnglish", "Error.URBANLB");
							convertForm.setMergeUlbClick(true);
						}

					}
				}

				if (declareLB != null) {
					if (convertForm.getLocalBodyNameInEn() == null || convertForm.getLocalBodyNameInEn().isEmpty()) {
						errors.rejectValue("localBodyNameInEn", "Error.URBANLBNEW");
						convertForm.setDeclareUlbClick(true);
					} else if (convertForm.getLocalBodyNameInEn() != null && !convertForm.getLocalBodyNameInEn().isEmpty()) {
						if (!regexValidator.validateSpecialCharacters(convertForm.getLocalBodyNameInEn())) { //Modified by Pooja on 08-10-2015
							errors.rejectValue("localBodyNameInEn", "error.valid.COMMONNAMEREQUIRED");
							convertForm.setDeclareUlbClick(true);
						}
					}

					if (convertForm.getLocalBodyNameInLcl() != null && !convertForm.getLocalBodyNameInLcl().isEmpty()) {
						if (!regexValidator.validateSpecialCharacters(convertForm.getLocalBodyNameInLcl())) {
							errors.rejectValue("localBodyNameInLcl", "error.valid.COMMONNAMEREQUIRED");
							convertForm.setDeclareUlbClick(true);
						}
					}
					if (convertForm.getLocalBodyliasInEn() != null && !convertForm.getLocalBodyliasInEn().isEmpty()) {
						if (!regexValidator.checkforAlphabetsandSpace(convertForm.getLocalBodyliasInEn())) {
							errors.rejectValue("localBodyliasInEn", "error.valid.COMMONNAMEREQUIRED");
							convertForm.setDeclareUlbClick(true);
						}
					}

					if (convertForm.getLocalBodyliasInLcl() != null && !convertForm.getLocalBodyliasInLcl().isEmpty()) {
						if (!regexValidator.validateSpecialCharacters(convertForm.getLocalBodyliasInLcl())) {
							errors.rejectValue("localBodyliasInLcl", "error.valid.COMMONNAMEREQUIRED");
							convertForm.setDeclareUlbClick(true);
						}
					}

					if (convertForm.getUrbanLgTypeNameNew() == null || convertForm.getUrbanLgTypeNameNew().isEmpty()) {
						errors.rejectValue("urbanLgTypeNameNew", "Error.URBANLBTYPENEW");
						convertForm.setDeclareUlbClick(true);
					} else if (convertForm.getUrbanLgTypeNameNew() != null && !convertForm.getUrbanLgTypeNameNew().isEmpty()) {
						int urbanLBtype = Integer.parseInt(convertForm.getUrbanLgTypeNameNew());
						if (urbanLBtype < 1) {
							errors.rejectValue("urbanLgTypeNameNew", "Error.URBANLBTYPENEW");
							convertForm.setDeclareUlbClick(true);
						} else if (!comboFillingService.getComboValuesforApp('Z', "S#U", stateCode, urbanLBtype)) {
							errors.rejectValue("urbanLgTypeNameNew", "Error.URBANLBTYPENEW");
							convertForm.setDeclareUlbClick(true);
						}

					}
				}

			}
		} catch (Exception e) {
			LOG.error("Exception-->" + e);
			throw e;
		}
	}

	public void validateRLBtoTLB(Object target, Errors errors, String govtSetUp, int stateCode) throws Exception {
		try {
			CustomRegexValidator regexValidator = new CustomRegexValidator();
			ConvertRLBtoTLBForm convertForm = (ConvertRLBtoTLBForm) target;
			String declareLB = convertForm.getDeclarenewTLB();
			String mergeLB = convertForm.getMergeRLBtoTLB();

			if (convertForm.getRurallbTypeName() == null) {
				errors.rejectValue("rurallbTypeName", "error.blank.ruralLBTypeName");
			}
			if (convertForm.getLgd_LBDistListAtVillageCA() == null) {
				errors.rejectValue("lgd_LBDistListAtVillageCA", "Error.DISTRICTLOCALBODY");
			}
			if (convertForm.getLgd_LBInterListAtVillageCA() == null) {
				errors.rejectValue("lgd_LBInterListAtVillageCA", "Error.INTERMEDIATELOCALBODY");
			}
			if (convertForm.getLgd_LBVillageDestAtVillageCA() == null) {
				errors.rejectValue("lgd_LBVillageDestAtVillageCA", "Error.NoVPSelected");
			}

			if (declareLB == null && mergeLB == null) {
				errors.rejectValue("mergeRLBtoTLB", "error.select.RLBtoTLBOption");
			}

			if (declareLB != null) {
				if (convertForm.getLocalBodyNameInEn() == null || convertForm.getLocalBodyNameInEn().isEmpty()) {
					errors.rejectValue("localBodyNameInEn", "error.blank.localBodyNameInEn");
				} else if (convertForm.getLocalBodyNameInEn() != null && !convertForm.getLocalBodyNameInEn().isEmpty()) {
					if (!regexValidator.validateSpecialCharacters(convertForm.getLocalBodyNameInEn())) {//modified by pooja on 08-10-2015
						errors.rejectValue("localBodyNameInEn", "error.valid.COMMONNAMEREQUIRED");
					}
				}
				if (convertForm.getTraditionalLbTypeName() == null) {
					errors.rejectValue("traditionalLgTypeNameNew", "error.blank.ruralLBTypeName");
				}
				if (convertForm.getLgd_LBDistrictAtVillageforNew() == null) {
					errors.rejectValue("lgd_LBDistrictAtVillageforNew", "error.SELECTHDCOUNCIL");
				}
				if (convertForm.getLgd_LBIntermediateAtVillageforNew() == null) {
					errors.rejectValue("lgd_LBIntermediateAtVillageforNew", "error.SELECTBACOUNCIL");
				}
			}

			if (mergeLB != null) {
				if (convertForm.getTraditionalLbTypeName() == null) {
					errors.rejectValue("traditionalLbTypeName", "error.blank.ruralLBTypeName");
				}
				if (convertForm.getLgd_LBDistrictAtVillageforExist() == null) {
					errors.rejectValue("lgd_LBDistrictAtVillageforExist", "error.SELECTHDCOUNCIL");
				}
				if (convertForm.getLgd_LBIntermediateAtVillageforExist() == null) {
					errors.rejectValue("lgd_LBIntermediateAtVillageforExist", "error.SELECTBACOUNCIL");
				}
				if (convertForm.getLgd_LBVillagePanchaytforExist() == null) {
					errors.rejectValue("lgd_LBVillagePanchaytforExist", "error.SELECTVCOUNCIL");
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void validateTLBtoRLB(Object target, Errors errors, String govtSetUp, int stateCode) throws Exception {
		try {
			CustomRegexValidator regexValidator = new CustomRegexValidator();
			ConvertTLBtoRLBForm convertForm = (ConvertTLBtoRLBForm) target;
			String declareLB = convertForm.getDeclarenewRLB();
			String mergeLB = convertForm.getMergeTLBtoRLB();

			if (convertForm.getRurallbTypeName() == null) {
				errors.rejectValue("rurallbTypeName", "error.blank.ruralLBTypeName");
			}
			if (convertForm.getLgd_LBDistListAtVillageCA() == null) {
				errors.rejectValue("lgd_LBDistListAtVillageCA", "Error.DISTRICTLOCALBODY");
			}
			if (convertForm.getLgd_LBInterListAtVillageCA() == null) {
				errors.rejectValue("lgd_LBInterListAtVillageCA", "Error.INTERMEDIATELOCALBODY");
			}
			if (convertForm.getLgd_LBVillageDestAtVillageCA() == null) {
				errors.rejectValue("lgd_LBVillageDestAtVillageCA", "Error.NoVPSelected");
			}

			if (declareLB == null && mergeLB == null) {
				errors.rejectValue("mergeTLBtoRLB", "error.select.TLBtoRLBOption");
			}

			if (declareLB != null) {
				if (convertForm.getLocalBodyNameInEn() == null || convertForm.getLocalBodyNameInEn().isEmpty()) {
					errors.rejectValue("localBodyNameInEn", "error.blank.localBodyNameInEn");
				} else if (convertForm.getLocalBodyNameInEn() != null && !convertForm.getLocalBodyNameInEn().isEmpty()) {
					if (!regexValidator.checkforAlphabetsandSpace(convertForm.getLocalBodyNameInEn())) {
						errors.rejectValue("localBodyNameInEn", "error.valid.COMMONNAMEREQUIRED");
					}
				}
				if (convertForm.getTraditionalLgTypeNameNew() == null) {
					errors.rejectValue("traditionalLgTypeNameNew", "error.blank.ruralLBTypeName");
				}
				if (convertForm.getLgd_LBDistrictAtVillageforNew() == null) {
					errors.rejectValue("lgd_LBDistrictAtVillageforNew", "Error.DISTRICTLOCALBODY");
				}
				if (convertForm.getLgd_LBIntermediateAtVillageforNew() == null) {
					errors.rejectValue("lgd_LBIntermediateAtVillageforNew", "Error.INTERMEDIATELOCALBODY");
				}
			}

			if (mergeLB != null) {
				if (convertForm.getTraditionalLbTypeName() == null) {
					errors.rejectValue("traditionalLbTypeName", "error.blank.ruralLBTypeName");
				}
				if (convertForm.getLgd_LBDistrictAtVillageforExist() == null) {
					errors.rejectValue("lgd_LBDistrictAtVillageforExist", "Error.DISTRICTLOCALBODY");
				}
				if (convertForm.getLgd_LBIntermediateAtVillageforExist() == null) {
					errors.rejectValue("lgd_LBIntermediateAtVillageforExist", "Error.INTERMEDIATELOCALBODY");
				}
				if (convertForm.getLgd_LBVillagePanchaytforExist() == null) {
					errors.rejectValue("lgd_LBVillagePanchaytforExist", "Error.NoVPSelected");
				}
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void validate(Object arg0, Errors arg1) {

	}

}
