/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

/**
 * 
 * @author Pooja
 */
@Entity
@NamedNativeQuery(query = "select * from local_body_setup_fn(26)", name = "GetTier", resultClass = CommonTier.class)

public class CommonTier implements Serializable { // NO_UCD (unused code)
	private static final long serialVersionUID = 1L;
		@Id
	@Column(name = "nomenclature_english", nullable = true)
	private String nomenclatureEnglish;
	@Column(name = "nomenclature_local", nullable = true)
	private String nomenclature_local;
	@Column(name = "local_body_type_name", nullable = true)
	private String localBodyTypeName;
	@Column(name = "parent_local_body_type_code", nullable = true)
	private String parentLocalBodyTypeCode;

	

	public String getNomenclatureEnglish() {
		return nomenclatureEnglish;
	}

	public void setNomenclatureEnglish(String nomenclatureEnglish) {
		this.nomenclatureEnglish = nomenclatureEnglish;
	}

	public String getNomenclature_local() {
		return nomenclature_local;
	}

	public void setNomenclature_local(String nomenclature_local) {
		this.nomenclature_local = nomenclature_local;
	}

	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getParentLocalBodyTypeCode() {
		return parentLocalBodyTypeCode;
	}

	public void setParentLocalBodyTypeCode(String parentLocalBodyTypeCode) {
		this.parentLocalBodyTypeCode = parentLocalBodyTypeCode;
	}

	
}