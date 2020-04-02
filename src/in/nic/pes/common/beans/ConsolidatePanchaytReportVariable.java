package in.nic.pes.common.beans;

import java.io.Serializable;

public class ConsolidatePanchaytReportVariable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char level;
	private Integer Code;
	private Boolean otherInformationFlag;
	public char getLevel() {
		return level;
	}
	public void setLevel(char level) {
		this.level = level;
	}
	public Integer getCode() {
		return Code;
	}
	public void setCode(Integer code) {
		Code = code;
	}
	public Boolean getOtherInformationFlag() {
		return otherInformationFlag;
	}
	public void setOtherInformationFlag(Boolean otherInformationFlag) {
		this.otherInformationFlag = otherInformationFlag;
	}

}
