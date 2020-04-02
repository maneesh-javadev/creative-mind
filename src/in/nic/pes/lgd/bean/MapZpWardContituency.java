package in.nic.pes.lgd.bean;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
/**
 **
 * chandra
 */
@Entity
@Table(name = "covered_zpward_landregion")
public class MapZpWardContituency implements Serializable {
    private static final long serialVersionUID = 1L;
 	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "zpward_landregion_Seq") })
	@Id
	@GeneratedValue(generator = "sequence")	
    @Column(name = "cwv_code", unique = true, nullable = false)
    private Integer  cwv_code;
 	
	@Column(name = "ward_code")
    private int ward_code;
	
	@Column(name = "ward_version")
    private int ward_version;
	
	@Column(name = "coverage_lc")
    private int coverage_lc;
	
	@Column(name = "coverage_lc_version")
    private int coverage_lc_version;
	
	
	@Column(name = "coverage_lc_type")
    private char coverage_lc_type;
	
	@Column(name = "isactive")
    private boolean isactive;
	
	@Column(name = "coverage_type")
    private char coverage_type;

	public Integer getCwv_code() {
		return cwv_code;
	}

	public void setCwv_code(Integer cwv_code) {
		this.cwv_code = cwv_code;
	}

	public int getWard_code() {
		return ward_code;
	}

	public void setWard_code(int ward_code) {
		this.ward_code = ward_code;
	}

	public int getWard_version() {
		return ward_version;
	}

	public void setWard_version(int ward_version) {
		this.ward_version = ward_version;
	}

	public int getCoverage_lc() {
		return coverage_lc;
	}

	public void setCoverage_lc(int coverage_lc) {
		this.coverage_lc = coverage_lc;
	}

	public int getCoverage_lc_version() {
		return coverage_lc_version;
	}

	public void setCoverage_lc_version(int coverage_lc_version) {
		this.coverage_lc_version = coverage_lc_version;
	}

	public char getCoverage_lc_type() {
		return coverage_lc_type;
	}

	public void setCoverage_lc_type(char coverage_lc_type) {
		this.coverage_lc_type = coverage_lc_type;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public char getCoverage_type() {
		return coverage_type;
	}

	public void setCoverage_type(char coverage_type) {
		this.coverage_type = coverage_type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}	