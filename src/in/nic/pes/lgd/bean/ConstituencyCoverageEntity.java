/*
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author
 */
@Entity
@Table(name = "constituency_covered_entity_new")
public class ConstituencyCoverageEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;


	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "ConstituencyCoverageEntity_Seq") })
	@Id
	@GeneratedValue(generator = "sequence")	
	@Column(name = "cce_code", unique = true, nullable = false)
	private Integer  id;
	
	@Column(name = "cc_code")
	private int cccode;

	@Column(name = "entity_lc")
	private int entityLc;

	@Column(name = "entity_type")
	private char entitytype;

	@Column(name = "coverage_type")
	private char coveragetype;

	@Column(name = "isactive", nullable = false)
	private boolean isactive;

	@Column(name = "mapped_on")
	private Date mappedOn;
	
	@Column(name = "unmapped_on")
	private Date UnMappedOn;
	
	@Column(name = "mapped_by")
	private Long mappedBy;
	
	@Column(name = "unmapped_by")
	private Long UnMappedBy;
	
	public ConstituencyCoverageEntity() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getCccode() {
		return cccode;
	}

	public void setCccode(int cccode) {
		this.cccode = cccode;
	}

	public char getEntitytype() {
		return entitytype;
	}

	public void setEntitytype(char entitytype) {
		this.entitytype = entitytype;
	}

	public char getCoveragetype() {
		return coveragetype;
	}

	public void setCoveragetype(char coveragetype) {
		this.coveragetype = coveragetype;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	public int getEntityLc() {
		return entityLc;
	}

	public void setEntityLc(int entityLc) {
		this.entityLc = entityLc;
	}

	public Date getMappedOn() {
		return mappedOn;
	}

	public void setMappedOn(Date mappedOn) {
		this.mappedOn = mappedOn;
	}

	public Date getUnMappedOn() {
		return UnMappedOn;
	}

	public void setUnMappedOn(Date unMappedOn) {
		UnMappedOn = unMappedOn;
	}

	public Long getMappedBy() {
		return mappedBy;
	}

	public void setMappedBy(Long mappedBy) {
		this.mappedBy = mappedBy;
	}

	public Long getUnMappedBy() {
		return UnMappedBy;
	}

	public void setUnMappedBy(Long unMappedBy) {
		UnMappedBy = unMappedBy;
	}
	
}
	