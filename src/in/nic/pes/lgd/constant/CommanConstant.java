package in.nic.pes.lgd.constant;

public enum CommanConstant {
	
	
	   ENTITY_TYPE_DISTRICT("D"),
	   
	   ENTITY_TYPE_STATE("S"),
	   
	   /**
	    * IF District value null from switch unit then default value set to be 0 
	    */
	   DISTRICT_CODE_DEFAULT_VALUE(0),
	   
	   GOVERMENT_ORDER_ENTITY_TYPE_LOCALBODY("G"),  
	
	/**
	 * In create Village send flag for GIS PREVIEW 
	 */
	     CREATE_VILLAGE_GIS_PREVIEW("CP"),
	
	
	
		HABITATION_PARENT_TYPE_GRAMPANCHAYAT("G"),
		
		HABITATION_PARENT_TYPE_VILLAGE("V"),
	
		HABITHABITATION_PARENT_TYPE_NOT_CONFIGURE("N");

		/**
		 * The {@code value} used for assign enum constructor parameter.
		 */
		private String value;

		/**
		 * Default Constructor
		 */
		private CommanConstant() {
		}

		private CommanConstant(String value) {
			this.value = value;
		}

		private CommanConstant(Integer value) {
			this.value = value.toString();
		}

		@Override
		public String toString() {
			return value != null ? value : name();
		}

}
