package in.nic.pes.lgd.forms;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class NomenclatureSubDistForm {
	
	
	@NotEmpty(message = "Please Enter the Name.")
	@Pattern(regexp="[a-zA-Z][a-zA-Z.]*$", message="Please use [A-Z], [a-z] ,[.] in name.")
	private String nameinEnglish;
	@Pattern(regexp="^[a-zA-Z\\s]+$", message="Please use alphabets and space in.")
	private String nameinlocalLanguage;
	private boolean blockExist;
	private boolean  nameofTheBlockisSameforTheState;
	
	
	
	public boolean isBlockExist() {
		return blockExist;
	}
	public void setBlockExist(boolean blockExist) {
		this.blockExist = blockExist;
	}
	public boolean isNameofTheBlockisSameforTheState() {
		return nameofTheBlockisSameforTheState;
	}
	public void setNameofTheBlockisSameforTheState(
			boolean nameofTheBlockisSameforTheState) {
		this.nameofTheBlockisSameforTheState = nameofTheBlockisSameforTheState;
	}
	public String getNameinEnglish() {
		return nameinEnglish;
	}
	public void setNameinEnglish(String nameinEnglish) {
		this.nameinEnglish = nameinEnglish;
	}
	public String getNameinlocalLanguage() {
		return nameinlocalLanguage;
	}
	public void setNameinlocalLanguage(String nameinlocalLanguage) {
		this.nameinlocalLanguage = nameinlocalLanguage;
	}
	
	

}
