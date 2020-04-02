package in.nic.pes.lgd.constant;

public enum StateConstant {
	
	
	STATE_OPEARATION_CODE(43),
	
	STATE_MODIFY_OPERATION("M"),
	
	STATE_ENTITY_TYPE("S");
	/**
	 * The {@code value} used for assign enum constructor parameter.
	 */
	private String value;

	/**
	 * Default Constructor
	 */
	private StateConstant() {
	}

	private StateConstant(String value) {
		this.value = value;
	}

	private StateConstant(Integer value) {
		this.value = value.toString();
	}

	@Override
	public String toString() {
		return value != null ? value : name();
	}


}
