package com.cmc.lgd.localbody.rules;

import java.util.EnumSet;

/**
 * The {@code LocalBodyConstant} Enum used to set constant for Local Body.
 * 
 * @author Vinay Yadav
 * @since 7/26/2014
 * 
 */
public enum LocalBodyConstant {

	/**
	 * Panchayat Level Local Body having value 'P'.
	 */
	LB_PANCHAYAT("P"),

	/**
	 * Traditional Level Local Body having value 'T'.
	 */
	LB_TRADITIONAL("T"),

	/**
	 * Urban Level Local Body having value 'U'.
	 */
	LB_URBAN("U"),

	/**
	 * Rural Local Body having value 'R'.
	 */
	LB_RURAL("R"),
	
	/**
	 * District Panchayat Level Local Body having value 'D'.
	 */
	DISTRICT_PANCHAYAT_LEVEL("D"),
	
	/**
	 * Intermediate Panchayat Level Local Body having value 'P'.
	 */
	INTERMEDIATE_PANCHAYAT_LEVEL("I"),
	
	/**
	 * Village Panchayat Level Local Body having value 'P'.
	 */
	VILLAGE_PANCHAYAT_LEVEL("V"),
	
	/**
	 * District Constant having value 'D'.
	 */
	DISTRICT_CONSTANT("D"),
	
	/**
	 * Sub District Constant having value 'T'.
	 */
	SUB_DISTRICT_CONSTANT("T"),
	
	/**
	 * Sub District Start Constant having value 'S'.
	 */
	SUB_DISTRICT_START_CONSTANT("S"),
	
	/**
	 * Village Constant having value 'V'.
	 */
	VILLAGE_CONSTANT("V"),
	
	/**
	 * Local body Constant having value 'G'.
	 */
	LOCAL_BODY_CONSTANT("G"),

	/**
	 * Default Constant.
	 */
	DEFAULT,

	/**
	 * Way to Define Integer Constant.
	 */
	DEFINE_INTEGER(1),

	/**
	 * Way to define Float Constant.
	 */
	DEFINE_FLOAT(1.24f),
	
	OPERATION_CODE,
	
	FLAG_CODE,
	
	ATTACHMENT_MASTER_GO (1),
	
	ATTACHMENT_WEBSERVICES_DOC (4),
	
	ATTACHMENT_MASTER_MAP (2),
	
	LB_PUBLISH_FAILD,
	
	PROCESS_MODIFY_NAME("LB_Modify_Name"),
	
	PROCESS_MODIFY_PARENT("LB_Modify_Parent"),
	
	PROCESS_MODIFY_TYPE("LB_Modify_Type"),
	
	PROCESS_MODIFY_GOV_ORDER("LB_Modify_GO"),
	
	PROCESS_CHANGE_COVERAGE("LB_Change_Coverage"),
	
	PROCESS_MAP_COVERAGE("LB_Map_Coverage"),
	
	CURRENT_DATE_PATTERN("dd/MM/yyyy"),
	
	LOCAL_BODY_DRAFT_TABLE("draft_localbody_temp"),
	
	CHANGE_COVERAGE_DRAFT_TABLE("draft_change_coverage_temp"),
	
	SUCCESS_MESSAGE("SUCCESS"),
	
	FAILED_MESSAGE("FAILED"),
	
	LB_CREATION_PROCESS(1),
	
	LB_CHANGE_COVERAGE_PROCESS(9),
	
	
	ATTACHMENT_MASTER_GO_DOWNLOAD_DIRECTORY(5),
	
	/**
	 *  Before trigger fire create localbody set ispesa value 'Z' 
	 *  default value pass to function create_localbody_fn 
	 */
	LB_ISPESA_DEFAULT_VALUE("N"),
	
	
	
	ATTACHMENT_MASTER_GO_NOTIFICATION(7),	
	
	ATTACHMENT_MASTER_GO_ANNOUNCEMENT(10),; 
	
	
	/**
	 * The {@code value} used for assign enum constructor parameter. 
	 */
	private String value;

	/**
	 * Default Constructor
	 */
	private LocalBodyConstant() {
	}

	private LocalBodyConstant(String value) {
		this.value = value;
	}

	private LocalBodyConstant(Integer value) {
		this.value = value.toString();
	}

	private LocalBodyConstant(Float value) {
		this.value = value.toString();
	}
	
	private LocalBodyConstant(Long value) {
		this.value = value.toString();
	}

	@Override
	public String toString() {
		return value != null ? value : name();
	}
	
	
	public Integer get (String panchayatType, String processAction) {
		Integer operationCode = null;
		if(LocalBodyConstant.PROCESS_MODIFY_NAME.toString().equals(processAction)){
			if(LB_PANCHAYAT.toString().equals(panchayatType)){
				operationCode = 45; // Change Name of Local Government Body (PRI)
			}else if(LB_TRADITIONAL.toString().equals(panchayatType)){
				operationCode = 47; // Change Name of Local Government Body (Traditional)
			}else if(LB_URBAN.toString().equals(panchayatType)){
				operationCode = 46; // Change Name of Local Government Body (Urban)
			}
		} else if(PROCESS_MODIFY_PARENT.toString().equals(processAction)){
			if(LB_PANCHAYAT.toString().equals(panchayatType)){
				operationCode = 48; // Change Upper hierarchy of Local Body (PRI)
			}else if(LB_TRADITIONAL.toString().equals(panchayatType)){
				operationCode = 49; // Change Upper hierarchy of Local Body (Traditional)
			}
		} else if (PROCESS_MODIFY_TYPE.toString().equals(processAction)){
			if(LB_URBAN.toString().equals(panchayatType)){
				operationCode = 22; // Change Type of Local Government (Urban)
			}
		} if(LocalBodyConstant.PROCESS_MODIFY_GOV_ORDER.toString().equals(processAction)){
			if(LB_PANCHAYAT.toString().equals(panchayatType)){
				operationCode = 45; // Change Name of Local Government Body (PRI)
			}else if(LB_TRADITIONAL.toString().equals(panchayatType)){
				operationCode = 47; // Change Name of Local Government Body (Traditional)
			}else if(LB_URBAN.toString().equals(panchayatType)){
				operationCode = 46; // Change Name of Local Government Body (Urban)
			}
			
		} if(LocalBodyConstant.PROCESS_CHANGE_COVERAGE.toString().equals(processAction)){
			if(LB_PANCHAYAT.toString().equals(panchayatType)){
				operationCode = 50; // Change Name of Local Government Body (PRI)
			}else if(LB_TRADITIONAL.toString().equals(panchayatType)){
				operationCode = 52; // Change Name of Local Government Body (Traditional)
			}else if(LB_URBAN.toString().equals(panchayatType)){
				operationCode = 51; // Change Name of Local Government Body (Urban)
			}
			
		} else{ // Block for Create New Local Body
			if(LB_PANCHAYAT.toString().equals(panchayatType)){
				operationCode = 20; // New PRI Created with Contribution of Existing Local Body and Mapped LandRegions
			}else if(LB_TRADITIONAL.toString().equals(panchayatType)){
				operationCode = 27; // New Traditional Created with Contribution of Existing Local Body and Mapped LandRegions
			}else if(LB_URBAN.toString().equals(panchayatType)){
				operationCode = 21; // Create Urban Local Body
			}
		}
		return operationCode;
	}
	
	public Integer getFlagCode (String panchayatType, String processAction) {
		Integer flagCode = null;
		if(LocalBodyConstant.PROCESS_MODIFY_NAME.toString().equals(processAction)){
			if(LB_PANCHAYAT.toString().equals(panchayatType)){
				//flagCode = 45; // Change Name of Local Government Body (PRI)
			}else if(LB_TRADITIONAL.toString().equals(panchayatType)){
				//flagCode = 47; // Change Name of Local Government Body (Traditional)
			}else if(LB_URBAN.toString().equals(panchayatType)){
				//flagCode = 46; // Change Name of Local Government Body (Urban)
			}
		} else{ // Block for Create New Local Body
			if(LB_PANCHAYAT.toString().equals(panchayatType)){
				flagCode = 13; // New PRI Created with Contribution of Existing Local Body and Mapped LandRegions
			}else if(LB_TRADITIONAL.toString().equals(panchayatType)){
				flagCode = 14; // New Traditional Created with Contribution of Existing Local Body and Mapped LandRegions
			}else if(LB_URBAN.toString().equals(panchayatType)){
				flagCode = 15; // New ULB Created with Contribution of Existing Local Body and Mapped LandRegions
			}
		}
		return flagCode;
	}
	
	public static boolean isValidCreationType(String text) {
	    if (text != null) {
	    	EnumSet<LocalBodyConstant> constants = EnumSet.of(LocalBodyConstant.LB_PANCHAYAT, LocalBodyConstant.LB_TRADITIONAL, LocalBodyConstant.LB_URBAN);
	    	for (LocalBodyConstant b : constants) {
	    		if (b.toString().equalsIgnoreCase(text)) {
	    			return true;
	    		}
	    	}
	    }
	    return false;
	  }
}
