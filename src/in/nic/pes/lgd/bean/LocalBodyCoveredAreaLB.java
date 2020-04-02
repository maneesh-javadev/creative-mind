package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(query="SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,'D')) then cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_forLB_fn(:localBodyCode)lb where land_region_type='D' ",name="getLocalGovtBodyforCoveredLBDistrictList",resultClass=LocalBodyCoveredAreaLB.class),
	@NamedNativeQuery(query="SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,'T')) then cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_forLB_fn(:localBodyCode)lb where land_region_type='T' ",name="getLocalGovtBodyforCoveredLBSubDistrictList",resultClass=LocalBodyCoveredAreaLB.class),
	@NamedNativeQuery(query="SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,'V')) then cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_forLB_fn(:localBodyCode)lb where land_region_type='V'",name="getLocalGovtBodyforCoveredLBVillageListName",resultClass=LocalBodyCoveredAreaLB.class),
	@NamedNativeQuery(query="SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,'T')) then cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_forLB_fn(:localBodyCode)lb where land_region_type='T' and land_region_code in(select tlc from subdistrict where dlc in(:distCode))",name="getLocalGovtBodyforCoveredLBSubDistListNamebyDistCode",resultClass=LocalBodyCoveredAreaLB.class),
	@NamedNativeQuery(query="SELECT case when lb.land_region_code  in (select * from get_draft_used_lb_lr_temp(lb.land_region_code,'V')) then cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_coverage_lb_list_forLB_fn(:localBodyCode)lb where land_region_type='V' and land_region_code in(select vlc from village where tlc in(:subdistCode))",name="getLocalGovtBodyforCoveredLBVillageListNamebyVillCode",resultClass=LocalBodyCoveredAreaLB.class),
})
public class LocalBodyCoveredAreaLB
{
		private int landRegionCode;
	    private int landRegionVersion;
	    private String landRegionNameEnglish;
	    private char landRegionType;
	    private char coverageType;
	    private String localbodyNameEnglish;
	    private String localbodyCoverage;
	    /**
		 * Added by ripunj 0n 08-12-2014 for Localbody Draft Mode
		 */
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
		@Column(name="local_body_name_english")
		public String getLocalbodyNameEnglish() {
			return localbodyNameEnglish;
		}
		public void setLocalbodyNameEnglish(String localbodyNameEnglish) {
			this.localbodyNameEnglish = localbodyNameEnglish;
		}
		@Column(name="local_body_with_coverage_code")
		public String getLocalbodyCoverage() {
			return localbodyCoverage;
		}
		public void setLocalbodyCoverage(String localbodyCoverage) {
			this.localbodyCoverage = localbodyCoverage;
		}
		@Column(name="operation_state")
		public Character getOperation_state() {
			return operation_state;
		}
		public void setOperation_state(Character operation_state) {
			this.operation_state = operation_state;
		}
		
		
		

		
}
