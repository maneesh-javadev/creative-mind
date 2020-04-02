package in.nic.pes.lgd.section.rule;

public enum SectionConstant {
	
	PARENT_TYPE_LOCALBODY("G"),
	
	PARENT_TYPE_ORGANIZATION("O"),
	
	ENTITY_SPECIFIC("C"),
	
	ENTITY_FULL("P"),
	
	ENTITY_FULL_WITH_TOP_LOCALBODY("S"),
	
	CURRENT_DATE_PATTERN("dd/MM/yyyy"),
	
	LOCAL_BODY_CATEGORY_URBN("U"),
	
	NULL_STRING("null"),
	
	SECTION_UPDATE_FLAG_CODE("88"),
	
	SECTION_OPERATION_CREATE("C"),
	
	SECTION_OPERATION_UPDATE("U"),
	
	SECTION_OPERATION_DELETE("D"), 
	
	SECTION_OPERATION_RESTORE("R"),
	
	IS_CENTER("C"),
	
	CENTER_ORGANIZATION_ELEMENT_LENGTH("4"),
	
	STATE_ORGANIZATION_ELEMENT_LENGTH("4")
	;
	
	
	
	/**
	 * The {@code value} used for assign enum constructor parameter. 
	 */
	private String value;

	/**
	 * Default Constructor
	 */
	private SectionConstant() {
	}

	private SectionConstant(String value) {
		this.value = value;
	}

	private SectionConstant(Integer value) {
		this.value = value.toString();
	}

	private SectionConstant(Float value) {
		this.value = value.toString();
	}
	
	private SectionConstant(Long value) {
		this.value = value.toString();
	}
	
	@Override
	public String toString() {
		return value != null ? value : name();
	}
	

}
