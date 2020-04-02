package in.nic.pes.lgd.constant;

public enum VillageConstant {
	
	//OP --> Operation Code
	
	CREATE_VILLAGE_OP_CODE(10),
	
	CHANGE_VILLAGE_OP_CODE(11),
	
	CREATE_VILLAGE_NEW_LAND_FLAG_CODE(40),
	
	CREATE_VILLAGE_EXISTING_VILLAGE_FLAG_CODE(39),
	
	CREATE_VILLAGE_URBAN_VILLAGE_FLAG_CODE(41)
	;
	/**
	 * The {@code value} used for assign enum constructor parameter.
	 */
	private String value;

	/**
	 * Default Constructor
	 */
	private VillageConstant() {
	}

	private VillageConstant(String value) {
		this.value = value;
	}

	private VillageConstant(Integer value) {
		this.value = value.toString();
	}

	@Override
	public String toString() {
		return value != null ? value : name();
	}


}
