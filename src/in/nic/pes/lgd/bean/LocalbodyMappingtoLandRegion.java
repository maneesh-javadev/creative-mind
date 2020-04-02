package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;




	@Entity
	@NamedNativeQuery(query="SELECT row_number() OVER() as row_id, * FROM get_localBodyMappingtoCensusCodeRpt(:stateCode)",name="getLocalBodyMappingtoCensusCode",resultClass=LocalbodyMappingtoLandRegion.class)
	
public class LocalbodyMappingtoLandRegion implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Id
	    @Basic(optional = false)
		@Column(name="row_id")
	    private Integer rid;
	    @Column(name = "district_code")
		private Integer district_code;
		@Column(name = "district_name_english")
		private String district_name_english;
		@Column(name = "district_census2011_code")
		private Integer district_census2011_code;
		@Column(name = "district_census2001_code")
		private String district_census2001_code;
		@Column(name = "subdistrict_code")
		private Integer subdistrict_code;
		@Column(name = "subdistrict_name_english")
		private String subdistrict_name_english;
		@Column(name = "subdistrict_census2011_code")
		private Integer subdistrict_census2011_code;
		@Column(name = "subdistrict_census2001_code")
		private String subdistrict_census2001_code;
		@Column(name = "village_code", nullable = false)
		private Integer village_code;
		@Column(name = "village_name_english")
		private String village_name_english;
		@Column(name = "village_census2011_code")
		private Integer village_census2011_code;
		@Column(name = "village_census2001_code")
		private String village_census2001_code;
		@Column(name = "local_body_code")
		private Integer local_body_code;
		@Column(name = "local_body_name_english")
		private String local_body_name_english;
		
		
		public Integer getDistrict_census2011_code() {
			return district_census2011_code;
		}
		public void setDistrict_census2011_code(Integer district_census2011_code) {
			this.district_census2011_code = district_census2011_code;
		}
		
		public Integer getSubdistrict_code() {
			return subdistrict_code;
		}
		public void setSubdistrict_code(Integer subdistrict_code) {
			this.subdistrict_code = subdistrict_code;
		}
		public String getSubdistrict_name_english() {
			return subdistrict_name_english;
		}
		public void setSubdistrict_name_english(String subdistrict_name_english) {
			this.subdistrict_name_english = subdistrict_name_english;
		}
		public Integer getSubdistrict_census2011_code() {
			return subdistrict_census2011_code;
		}
		public void setSubdistrict_census2011_code(Integer subdistrict_census2011_code) {
			this.subdistrict_census2011_code = subdistrict_census2011_code;
		}
		
		public Integer getVillage_code() {
			return village_code;
		}
		public void setVillage_code(Integer village_code) {
			this.village_code = village_code;
		}
		public String getVillage_name_english() {
			return village_name_english;
		}
		public void setVillage_name_english(String village_name_english) {
			this.village_name_english = village_name_english;
		}
		public Integer getVillage_census2011_code() {
			return village_census2011_code;
		}
		public void setVillage_census2011_code(Integer village_census2011_code) {
			this.village_census2011_code = village_census2011_code;
		}
		
		public Integer getLocal_body_code() {
			return local_body_code;
		}
		public void setLocal_body_code(Integer local_body_code) {
			this.local_body_code = local_body_code;
		}
		public String getLocal_body_name_english() {
			return local_body_name_english;
		}
		public void setLocal_body_name_english(String local_body_name_english) {
			this.local_body_name_english = local_body_name_english;
		}
		public Integer getDistrict_code() {
			return district_code;
		}
		public void setDistrict_code(Integer district_code) {
			this.district_code = district_code;
		}
		public String getDistrict_name_english() {
			return district_name_english;
		}
		public void setDistrict_name_english(String district_name_english) {
			this.district_name_english = district_name_english;
		}
		public String getDistrict_census2001_code() {
			return district_census2001_code;
		}
		public void setDistrict_census2001_code(String district_census2001_code) {
			this.district_census2001_code = district_census2001_code;
		}
		public String getSubdistrict_census2001_code() {
			return subdistrict_census2001_code;
		}
		public void setSubdistrict_census2001_code(String subdistrict_census2001_code) {
			this.subdistrict_census2001_code = subdistrict_census2001_code;
		}
		public String getVillage_census2001_code() {
			return village_census2001_code;
		}
		public void setVillage_census2001_code(String village_census2001_code) {
			this.village_census2001_code = village_census2001_code;
		}
		public Integer getRid() {
			return rid;
		}
		public void setRid(Integer rid) {
			this.rid = rid;
		}
}
