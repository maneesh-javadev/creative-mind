package in.nic.pes.lgd.draft.constant;

public enum DraftConstant {
	
	CURRENT_DATE_PATTERN("dd/MM/yyyy"),
	
	ENTITY_TYPE_STATE("S"),
	
	ENTITY_TYPE_DISTRICT("D"),
	
	ENTITY_TYPE_SUBDISTRICT("T"),
	
	ENTITY_TYPE_VILLAGE("V"),
	
	CREATE_SUBDISTRICT_OPERATION_CODE("16"),
	
	ATTACHMENT_MASTER_GO (1),
	
	ATTACHMENT_WEBSERVICES_DOC (4),
	
	ATTACHMENT_MASTER_MAP (2),
	
	DRAFT_OPERATION_CREATE_SUBDISTRICT(10),
	
	FORM_ACTION_PUBLISH("Publish"),
	
	FORM_ACTION_DRAFT("Draft"),
	
	DISTRICT_NAME_ENGLISH("DistNameEng"),
	
	OPERATION_TYPE_CREATE("C"),
	
	OPERATION_TYPE_MANAGE("M"),
	
	OPERATION_TYPE_INVALID("I"),
	
	CHANGE_SUBDISTRICT_OPERATION_CODE("7"),
	
	DRAFT_OPERATION_CHANGE_SUBDISTRICT(11),
	

	;
	
	/**
	 * The {@code value} used for assign enum constructor parameter. 
	 */
	private String value;

	/**
	 * Default Constructor
	 */
	private DraftConstant() {
	}

	private DraftConstant(String value) {
		this.value = value;
	}

	private DraftConstant(Integer value) {
		this.value = value.toString();
	}

	private DraftConstant(Float value) {
		this.value = value.toString();
	}
	
	private DraftConstant(Long value) {
		this.value = value.toString();
	}
	
	@Override
	public String toString() {
		return value != null ? value : name();
	}

}
