package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query=" SELECT * FROM get_coverage_lb_list_excluding_ward_coverage_fn(:localBodyCode)  where land_region_type='D' ",name="getLGBforCovDisListExWard",resultClass=LBCoverageWard.class),
@NamedNativeQuery(query=" SELECT * FROM get_coverage_lb_list_excluding_ward_coverage_fn(:localBodyCode)  where land_region_type='T' ",name="getLGBforCovSubDisListExWard",resultClass=LBCoverageWard.class),
@NamedNativeQuery(query=" SELECT * FROM get_coverage_lb_list_excluding_ward_coverage_fn(:localBodyCode) where land_region_type='V'",name="getLGBforCovVillListExWard",resultClass=LBCoverageWard.class),
@NamedNativeQuery(query=" SELECT * FROM get_coverage_lb_list_excluding_ward_coverage_fn(:localBodyCode) where land_region_type='V'",name="getLGBforCovVillListExWardforWard",resultClass=LBCoverageWard.class),
@NamedNativeQuery(query=" SELECT * FROM get_coverage_lb_list_excluding_ward_coverage_fn(:localBodyCode)",name="getLGBforUrbanCoveredListExWard",resultClass=LBCoverageWard.class),
@NamedNativeQuery(query=" SELECT * FROM get_coverage_list_excluding_ward_coverage_fn(:type,:entityCode)",name="getCoveredAreaOfNewWard",resultClass=LBCoverageWard.class)
})
public class LBCoverageWard {

	private Integer landRegionCode;
	private Integer landRegionVersion;
	private String landRegionNameEnglish;
	private char landRegionType;
	private char coverageType;
	private String landRegionCodeStr;
	
	@Id
    @Column(name="land_region_code")
	public Integer getLandRegionCode() {
		return landRegionCode;
	}
	public void setLandRegionCode(Integer landRegionCode) {
		this.landRegionCode = landRegionCode;
	}

	@Column(name="land_region_version")
    public Integer getLandRegionVersion() {
		return landRegionVersion;
	}
	public void setLandRegionVersion(Integer landRegionVersion) {
		this.landRegionVersion = landRegionVersion;
	}
	
    @Column(name="land_region_name_english")
    public String getLandRegionNameEnglish() {
		return landRegionNameEnglish;
	}
	public void setLandRegionNameEnglish(String landRegionNameEnglish) {
		this.landRegionNameEnglish = landRegionNameEnglish;
	}
	
    @Column(name="land_region_type")
    public char getLandRegionType() {
		return landRegionType;
	}
	public void setLandRegionType(char landRegionType) {
		this.landRegionType = landRegionType;
	}
	
    
	@Column(name="coverage_type")
	public char getCoverageType() {
		return coverageType;
	}
	public void setCoverageType(char coverageType) {
		this.coverageType = coverageType;
	}
	
	@Transient
	public String getLandRegionCodeStr() {
		return landRegionCodeStr;
	}
	public void setLandRegionCodeStr(String landRegionCodeStr) {
		this.landRegionCodeStr = landRegionCodeStr;
	}
}
