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
 * @author Mukesh
 */
@Entity
@NamedNativeQuery(query = "select * from local_body_subtype_fn(:state)", name = "TypeSub", resultClass = Subtype.class)

public class Subtype implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "local_body_type_name", nullable = true)
	private String localBodyTypeName;
	@Column(name = "subtype_name_eng", nullable = true)
	private String subtypeNameEng;
	@Column(name = "subtype_name_local", nullable = true)
	private String subtypeNameLocal;
	
	
	
		public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}
	public String getSubtypeNameEng() {
		return subtypeNameEng;
	}
	public void setSubtypeNameEng(String subtypeNameEng) {
		this.subtypeNameEng = subtypeNameEng;
	}
	public String getSubtypeNameLocal() {
		return subtypeNameLocal;
	}
	public void setSubtypeNameLocal(String subtypeNameLocal) {
		this.subtypeNameLocal = subtypeNameLocal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}