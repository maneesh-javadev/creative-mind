package in.nic.pes.lgd.constant;

public enum DesignationConstant {
	
	
	CREATE_NEW_DESIGNATION_OPERATION_CODE("102"),
	 
	 RENAME_DESIGNATION_NAME_OPERATION_CODE("103"),
	 
	 ADD_REMOVE_DESIGNATION_LEVEL_OPERATION_CODE("104"),
	 
	 CHANGE_DESIGNATION_SORT_ORDER_OPERATION_CODE("105"),
	 
	 MANAGE_DESIGNATION_OPERATION_CODE("106"),
	
	 DESIGNATION_LEVEL("C"),
	 
	 DESIGNATION_ENTITY_TYPE("C");
	 
	 	/**
		 * The {@code value} used for assign enum constructor parameter.
		 */
		private String value;

		/**
		 * Default Constructor
		 */
		private DesignationConstant() {
		}

		private DesignationConstant(String value) {
			this.value = value;
		}

		private DesignationConstant(Integer value) {
			this.value = value.toString();
		}

		@Override
		public String toString() {
			return value != null ? value : name();
		}

}
