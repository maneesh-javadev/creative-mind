package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;



@Entity
@NamedNativeQuery(query="SELECT case when ul.land_region_code  in (select local_body_code from get_draft_used_lb_lr_temp(ul.land_region_code,'T')) then cast('F' as character)  else cast('A' as character) end as operation_state ,* from get_partly_unmapped_land_region_list_localbody_levelwise_fn(:landRegionType,:stateCode,:level)ul where category=:lbtype",name="getpartillymappedLBListLevelWise",resultClass=PartillyMappedLRListLevelWise.class)

public class PartillyMappedLRListLevelWise {
	

		private int landRegionCode;
		private int land_region_version;
		private String localBodyNameEnglish;
		private String land_region_name_local;
		private char land_region_type;
		private char category;
		//private char mapped;
		/**
		 * Added by ripunj 0n 22-11-2014 for Localbody Draft Mode
		 */
		private String operation_state;
		
		
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
		public String getOperation_state() {
			return operation_state;
		}
		public void setOperation_state(String operation_state) {
			this.operation_state = operation_state;
		}
		
		
		
}
