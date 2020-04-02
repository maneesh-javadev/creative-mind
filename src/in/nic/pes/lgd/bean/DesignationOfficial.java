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
@NamedNativeQuery(query = "select * from designation_setup_fn(:state,:islocalbody)", name ="DesignationOfficial", resultClass = DesignationOfficial.class)

//@NamedNativeQuery(query = "select * from designation_setup_fn(integer,integer,boolean)", name ="DesignationOfficial", resultClass = DesignationOfficial.class)

public class DesignationOfficial implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
		@Column(name = "nomenclature_english", nullable = true)
	private String NomenclatureEnglish;
		@Id
	@Column(name = "designation_name", nullable = true)
	private String DesignationName;
	@Column(name = "designation_name_local", nullable = true)
	private String DesignationNameLocal;
	@Column(name = "istopdesignation", nullable = true)
	private boolean IsTopDesignation;
	@Column(name = "ismultiple", nullable = true)
	private boolean IsMultiple;
	

	public String getNomenclatureEnglish() {
		return NomenclatureEnglish;
	}


	public void setNomenclatureEnglish(String nomenclatureEnglish) {
		NomenclatureEnglish = nomenclatureEnglish;
	}


	public String getDesignationName() {
		return DesignationName;
	}


	public void setDesignationName(String designationName) {
		DesignationName = designationName;
	}


	public String getDesignationNameLocal() {
		return DesignationNameLocal;
	}


	public void setDesignationNameLocal(String designationNameLocal) {
		DesignationNameLocal = designationNameLocal;
	}


	public boolean isIsTopDesignation() {
		return IsTopDesignation;
	}


	public void setIsTopDesignation(boolean isTopDesignation) {
		IsTopDesignation = isTopDesignation;
	}


	public boolean isIsMultiple() {
		return IsMultiple;
	}


	public void setIsMultiple(boolean isMultiple) {
		IsMultiple = isMultiple;
	}


}