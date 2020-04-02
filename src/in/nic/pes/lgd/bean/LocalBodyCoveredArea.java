package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;


@Entity
@NamedNativeQueries({
@NamedNativeQuery(query=" SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,lb.land_region_type)) then  cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_fn(:localBodyCode)lb  where land_region_type='D' ",name="getLocalGovtBodyforCoveredDistrictList",resultClass=LocalBodyCoveredArea.class),
@NamedNativeQuery(query=" SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,lb.land_region_type)) then  cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_fn(:localBodyCode)lb  where land_region_type='T' ",name="getLocalGovtBodyforCoveredSubDist",resultClass=LocalBodyCoveredArea.class),
@NamedNativeQuery(query=" SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,'V')) then  cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_fn(:localBodyCode) lb where land_region_type='V'",name="getLocalGovtBodyforCoveredVillageListName",resultClass=LocalBodyCoveredArea.class),
@NamedNativeQuery(query=" SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,lb.land_region_type)) then  cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_fn(:localBodyCode) lb",name="getLocalGovtBodyforUrbanCoveredList",resultClass=LocalBodyCoveredArea.class),
@NamedNativeQuery(query=" SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,lb.land_region_type)) then  cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_excluding_ward_coverage_fn(:localBodyCode)lb where land_region_type='V'",name="getCoveredAreaForPart",resultClass=LocalBodyCoveredArea.class),
@NamedNativeQuery(query=" SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,lb.land_region_type)) then  cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_fn(:localBodyCode)lb  where land_region_type='T' and land_region_code in(select tlc from subdistrict where dlc in(:distCode))",name="getLocalGovtBodyforCoveredSubDistFinal",resultClass=LocalBodyCoveredArea.class),
@NamedNativeQuery(query=" SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,lb.land_region_type)) then  cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_fn(:localBodyCode)lb  where land_region_type='V' and land_region_code in(select vlc from village where tlc in(:subdistCode))",name="getLocalGovtBodyforCoveredVillageFinal",resultClass=LocalBodyCoveredArea.class),
@NamedNativeQuery(query=" SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,lb.land_region_type)) then  cast ('F' as character) else cast('A' as character) end as operation_state,land_region_code,land_region_version,land_region_name_english,land_region_type,coverage_type FROM get_coverage_lb_list_forLB_fn(:localBodyCodes) lb order by land_region_name_english ",name="COVERED_VILLAGES_OF_LOCALBODIES",resultClass=LocalBodyCoveredArea.class),
})
public class LocalBodyCoveredArea{
	/**
	 * 
	 */
    private int landRegionCode;
    private int landRegionVersion;
    private String landRegionNameEnglish;
    private char landRegionType;
    private char coverageType;
    private String landRegionCodeStr;
    
    /*added on 30/12/2014 by kirandeep for localbody impact*/
    private Character operation_state;
    
    
    @Id
    @Column(name="land_region_code")
    public int getLandRegionCode() {
		return landRegionCode;
	}
	public void setLandRegionCode(int landRegionCode) {
		this.landRegionCode = landRegionCode;
	}
	
	  @Column(name="land_region_version")
	public int getLandRegionVersion() {
		return landRegionVersion;
	}
	public void setLandRegionVersion(int landRegionVersion) {
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
	public Character getOperation_state() {
		return operation_state;
	}
	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}
	   
}