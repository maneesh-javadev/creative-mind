package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;



@Entity
@NamedNativeQueries
({
@NamedNativeQuery(query="SELECT * from get_partly_unmapped_land_region_list_fn(:landRegionType,:stateCode) where category=:lbtype",name="getpartillymappedLBList",resultClass=PartillyMappedLRList.class),
@NamedNativeQuery(query="SELECT * from get_statewise_partly_unmapped_land_region_list_fn(:landRegionType,:stateCode)",name="getpartillymapLandRegionList",resultClass=PartillyMappedLRList.class)
})
public class PartillyMappedLRList {
	

		private int landRegionCode;
		private int land_region_version;
		private String localBodyNameEnglish;
		private String land_region_name_local;
		private char land_region_type;
		private char category;
		//private char mapped;
		
		
		
		@Id
		@Column(name="land_region_code")
		public int getLandRegionCode() {
			return landRegionCode;
		}
		public void setLandRegionCode(int landRegionCode) {
			this.landRegionCode = landRegionCode;
		}
		
		@Column(name="land_region_name_english")
		public String getLocalBodyNameEnglish() {
			return localBodyNameEnglish;
		}
		public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
			this.localBodyNameEnglish = localBodyNameEnglish;
		}
		
		
		
		@Column(name="land_region_version")
		public int getLand_region_version() {
			return land_region_version;
		}
	
		public void setLand_region_version(int land_region_version) {
			this.land_region_version = land_region_version;
		}
		
		
		@Column(name="land_region_name_local")
		public String getLand_region_name_local() {
			return land_region_name_local;
		}
		public void setLand_region_name_local(String land_region_name_local) {
			this.land_region_name_local = land_region_name_local;
		}
		@Column(name="land_region_type")
		public char getLand_region_type() {
			return land_region_type;
		}
		public void setLand_region_type(char land_region_type) {
			this.land_region_type = land_region_type;
		}
		@Column(name="category")
		public char getCategory() {
			return category;
		}
		public void setCategory(char category) {
			this.category = category;
		}
		/*@Column(name="mapped")
		public char getMapped() {
			return mapped;
		}
		public void setMapped(char mapped) {
			this.mapped = mapped;
		}*/
		
		
}
