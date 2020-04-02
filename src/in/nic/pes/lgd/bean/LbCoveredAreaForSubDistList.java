package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
//@NamedNativeQuery(query=" SELECT * FROM getlbcovered_subdistrict_fn(:localBodyCode)",name="getLocalGovtBodyforCoveredSubDist",resultClass=LbCoveredAreaForSubDistList.class)
@NamedNativeQuery(query=" SELECT * FROM get_coverage_lb_list_fn(:localBodyCode)",name="getLocalGovtBodyforCoveredSubDistforWard",resultClass=LbCoveredAreaForSubDistList.class)
public class LbCoveredAreaForSubDistList {
	
	  private int landRegionCode;
	    private int landRegionVersion;
	    private String landRegionNameEnglish;
	    private char landRegionType;
	    private char coverageType;
		
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

}
