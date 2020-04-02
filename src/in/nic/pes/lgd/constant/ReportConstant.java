package in.nic.pes.lgd.constant;

/**
 * This Constant is used for Reports and download directory
 * 
 * @author Maeesh Kumar
 *
 */

public enum ReportConstant { // NO_UCD (unused code)
	
	
	
   PARLIAMENT_CONSTITUENCY("parliamentConstituency"),
   
   ASSEMBLY_CONSTITUENCY("assemblyConstituency"),
   
   DOWNLOAD_DIRECTORY_PDF("pdf"),
   
   DOWNLOAD_DIRECTORY_XLS("xls"),
   
   DOWNLOAD_DIRECTORY_CSV("csv"),
   
   DOWNLOAD_DIRECTORY_ODT("odt"),
   
   DOWNLOAD_DIRECTORY_HTM("htm")
   
   ;

	/**
	 * The {@code value} used for assign enum constructor parameter.
	 */
	private String value;

	/**
	 * Default Constructor
	 */
	private ReportConstant() {
	}

	private ReportConstant(String value) {
		this.value = value;
	}

	private ReportConstant(Integer value) {
		this.value = value.toString();
	}

	@Override
	public String toString() {
		return value != null ? value : name();
	}

}
