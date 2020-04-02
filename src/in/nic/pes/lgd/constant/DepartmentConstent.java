package in.nic.pes.lgd.constant;

public enum DepartmentConstent {

	/*
	 * organization_type table organization_type_code for Department is 2(two)
	 */
	ORGANIZTION_TYPE_CODE_MINIST(1),
	
	ORGANIZTION_TYPE_CODE_DEPT(2),
	
	ORGANIZTION_TYPE_CODE_ORG(3),


	/*
	 * administration_unit_level table All land_region slc define as 0(zero)
	 * 
	 */
	
	LANDREGION_MAX_CAPACITY(5),
	
	ENTITY_TYPE_LANDREGION("L"),
	
	ENTITY_TYPE_ADMINUNIT("A"),
	LOCAL_BODY_CODE("X"),
	
	ENTITY_TYPE_BOTH("T"),
	
	LAND_REGION_SLC(0),

	EXISTING_DEPT_SLC(-1),

	BLOCK_EQUL_CODE(3),

	BLOCK_CHILD_CODE(4),

	BLOCK_CODE(5),

	DISTRICT_CODE(2),

	STATE_CODE(1),

	SUBDISTRICT_CODE(3),

	VILLAGE_CODE(4),

	ZERO_AGAIN_NULL(0),
	
	STATE_LEVEL(1),
	
	CENTER_LEVEL(0),
	
	SHORT_ORDER_START_SEQ(0),
	
	/*added by sunita on 10-07-2015*/
	CREATE_ADMINISTRATIVE_UNIT(1),
	
	MANAGE_ADMINISTRATIVE_UNIT(2),
	
	ZERO_VALUE(0),
	
	EXTEND_ORGANIZATION_UNITS_FLAG("Y"),
	
	IS_CENTER("C"),
	
	DISTRICT_PANCHAYAT_CODE(-1),
	
	INTERMEDIATE_PANCHAYAT_CODE(-2),
	
	VILLAGE_PANCHAYAT_CODE(-3),
	
	DISTRICT_PANCHAYAT_LEVEL("X"),
	
	INTERMEDIATE_PANCHAYAT_LEVEL("Y"),
	
	VILLAGE_PANCHAYAT_LEVEL("Z"),
	
	URBAN_LEVEL("U")
	;
	

	/**
	 * The {@code value} used for assign enum constructor parameter.
	 */
	private String value;

	/**
	 * Default Constructor
	 */
	private DepartmentConstent() {
	}

	private DepartmentConstent(String value) {
		this.value = value;
	}

	private DepartmentConstent(Integer value) {
		this.value = value.toString();
	}

	@Override
	public String toString() {
		return value != null ? value : name();
	}

}
