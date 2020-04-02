package ws.in.nic.pes.lgdws.webservice;

public enum WebServiceConstant {
	
	ENTITY_TYPE_STATE("S"),
	 ENTITY_TYPE_DISTRICT("D"),
	 ENTITY_TYPE_SUBDISTRICT("T"),
	 ENTITY_TYPE_BLOCK("B"),
	 ENTITY_TYPE_VILLAGE("V"),
	 ENTITY_TYPE_LOCALBODY("L"),
	 
	 ENTITY_NAME_STATE("STATE"),
	 ENTITY_NAME_DISTRICT("DISTRICT"),
	 ENTITY_NAME_SUBDISTRICT("SUBDISTRICT"),
	 ENTITY_NAME_BLOCK("BLOCK"),
	 ENTITY_NAME_VILLAGE("VILLAGE"),
	 ENTITY_NAME_LOCALBODY("LOCALBODY"),
	 LOCALBODY_TYPE_CODE_VILLAGE_PANCHAYAT("3") , 
	 NULL_STRING("null"),
	 STRING_BOOLEAN_TRUE("true"),
	 STRING_BOOLEAN_FALSE("false"),
	 LB_AND_VILLAGE_COUNT("GET_INDIA_WISE_PRI_LOCALBODY_AND_VILLAGE_COUNT"),
	 PESA_STATUS_COUNT("GET_INDIA_WISE_PESA_STATUS")
	  ;
	   

		/**
		 * The {@code value} used for assign enum constructor parameter.
		 */
		private String value;

		/**
		 * Default Constructor
		 */
		private WebServiceConstant() {
		}

		private WebServiceConstant(String value) {
			this.value = value;
		}

		private WebServiceConstant(Integer value) {
			this.value = value.toString();
		}

		@Override
		public String toString() {
			return value != null ? value : name();
		}

}
