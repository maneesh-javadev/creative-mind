package in.nic.pes.lgd.bean;

import in.nic.pes.lgd.forms.LocalGovtBodyForm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

import pes.attachment.util.AttachedFilesForm;

@Entity
@NamedNativeQuery(query = " SELECT * FROM get_lb_details(:localBodyCode)", name = "getLocalBodyDetails", resultClass = LocalBodyDetails.class)
public class LocalBodyDetails  implements Serializable {
	private static final long serialVersionUID = 4675304883504927727L;
	private Integer localBodyTypeCode;
	private Integer localBodyCode;
	private Integer localBodyVersion;
	private String localBodyNameEnglish;
	private String localBodyNameLocal;
	private String aliasNameEnglish;
	private String alisNameLocal;
	private Integer stateCode;
	private String localBodyTypeName;
	private Integer parentLocalBodyCode;
	private String category;
	private String level;
	private String parentLocalBodyNameEnglish;
	private String parentLocalBodyNameLocal;
	private boolean isdisturbed;
	private Integer mapLocalbodyCode;
	private String coordinates;
	//Warning Flag code by Arnab on 09/04/2013 
	private boolean warningFlag;
	
	@Column(name = "warningflag")
	public boolean isWarningFlag() {
		return warningFlag;
	}

	public void setWarningFlag(boolean warningFlag) {
		this.warningFlag = warningFlag;
	}
	//Map Attachment Name code by Arnab on 11/04/2013 
	
	private String mapFileName;
	
	@Column(name = "map_file_name")
	public String getMapFileName() {
		return mapFileName;
	}
	
	public void setMapFileName(String mapFileName) {
		this.mapFileName = mapFileName;
	}

	//Ste Specific Code adde by Arnab on 20/03/2013 
	private String sscode;
	
	public String getSscode() {
		return sscode;
	}

	public void setSscode(String sscode) {
		this.sscode = sscode;
	}

	/* Transient members added by sushil on 20-02-2013*/
	private String govtOrderConfig;
	private String mapConfig;
	private String message;
	private LocalGovtBodyForm localGovtBodyForm;
	private List<Attachment> listAttachments;
	private List<AttachedFilesForm> attachedFilesList;
	
	@Column(name = "map_localbody_code")
	public Integer getMapLocalbodyCode() {
		return mapLocalbodyCode;
	}

	public void setMapLocalbodyCode(Integer mapLocalbodyCode) {
		this.mapLocalbodyCode = mapLocalbodyCode;
	}

	@Column(name = "coordinate")
	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	@Id
	@Column(name = "local_body_code")
	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	@Column(name = "local_body_version")
	public Integer getLocalBodyVersion() {
		return localBodyVersion;
	}

	public void setLocalBodyVersion(Integer localBodyVersion) {
		this.localBodyVersion = localBodyVersion;
	}

	@Column(name = "parent_local_body_code")
	public Integer getParentLocalBodyCode() {
		return parentLocalBodyCode;
	}

	public void setParentLocalBodyCode(Integer parentLocalBodyCode) {
		this.parentLocalBodyCode = parentLocalBodyCode;
	}

	@Column(name = "local_body_type_code")
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	@Column(name = "local_body_name_english")
	public String getLocalBodyNameEnglish() {
		if (localBodyNameEnglish != null)
			return localBodyNameEnglish.trim();
		else
			return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		if (localBodyNameEnglish != null)
			this.localBodyNameEnglish = localBodyNameEnglish.trim();
		else
			this.localBodyNameEnglish = localBodyNameEnglish;
	}

	@Column(name = "local_body_name_local")
	public String getLocalBodyNameLocal() {
		if (localBodyNameLocal != null)
			return localBodyNameLocal.trim();
		else
			return localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		if (localBodyNameLocal != null)
			this.localBodyNameLocal = localBodyNameLocal.trim();
		else
			this.localBodyNameLocal = localBodyNameLocal;
	}

	@Column(name = "alias_english")
	public String getAliasNameEnglish() {
		if (aliasNameEnglish != null)
			return aliasNameEnglish.trim();
		else
			return aliasNameEnglish;
	}

	public void setAliasNameEnglish(String aliasNameEnglish) {
		if (aliasNameEnglish != null)
			this.aliasNameEnglish = aliasNameEnglish.trim();
		else
			this.aliasNameEnglish = aliasNameEnglish;
	}

	@Column(name = "alias_local")
	public String getAlisNameLocal() {
		if (alisNameLocal != null)
			return alisNameLocal.trim();
		else
			return alisNameLocal;
	}

	public void setAlisNameLocal(String alisNameLocal) {
		if (alisNameLocal != null)
			this.alisNameLocal = alisNameLocal.trim();
		else
			this.alisNameLocal = alisNameLocal;

	}

	@Column(name = "state_code")
	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	@Column(name = "local_body_type_name")
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	@Column(name = "category")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "level")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "parent_local_body_name_english")
	public String getParentLocalBodyNameEnglish() {
		return parentLocalBodyNameEnglish;
	}

	public void setParentLocalBodyNameEnglish(String parentLocalBodyNameEnglish) {
		this.parentLocalBodyNameEnglish = parentLocalBodyNameEnglish;
	}

	@Column(name = "parent_local_body_name_local")
	public String getParentLocalBodyNameLocal() {
		return parentLocalBodyNameLocal;
	}

	public void setParentLocalBodyNameLocal(String parentLocalBodyNameLocal) {
		this.parentLocalBodyNameLocal = parentLocalBodyNameLocal;
	}

	@Column(name = "isdisturbed")
	public boolean isIsdisturbed() {
		return isdisturbed;
	}

	public void setIsdisturbed(boolean isdisturbed) {
		this.isdisturbed = isdisturbed;
	}

	@Transient
	public String getGovtOrderConfig() {
		return govtOrderConfig;
	}

	public void setGovtOrderConfig(String govtOrderConfig) {
		this.govtOrderConfig = govtOrderConfig;
	}

	@Transient
	public String getMapConfig() {
		return mapConfig;
	}

	public void setMapConfig(String mapConfig) {
		this.mapConfig = mapConfig;
	}

	@Transient
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Transient
	public LocalGovtBodyForm getLocalGovtBodyForm() {
		return localGovtBodyForm;
	}

	public void setLocalGovtBodyForm(LocalGovtBodyForm localGovtBodyForm) {
		this.localGovtBodyForm = localGovtBodyForm;
	}

	@Transient
	public final List<Attachment> getListAttachments() {
		return listAttachments;
	}

	public final void setListAttachments(List<Attachment> listAttachments) {
		this.listAttachments = listAttachments;
	}

	@Transient
	public final List<AttachedFilesForm> getAttachedFilesList() {
		return attachedFilesList;
	}

	public final void setAttachedFilesList(List<AttachedFilesForm> attachedFilesList) {
		this.attachedFilesList = attachedFilesList;
	}
}