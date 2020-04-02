package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
@Entity
@NamedNativeQuery(query = "select * from get_map_configuaration_by_tier_setup_fn(:stateCode,:category) where tier_setup_code is not null", name ="configMaptier", resultClass=GetMapConfigLocalBody.class)
public class GetMapConfigLocalBody
{
		
	      
			 private int stateCode;
			
			 private char category;			 
			
			 private Integer tier_setup_code;			
			 
			 private String nomenclature_english;
			
			 private String nomenclature_local;
			
			 private boolean ispartixgovt;
			
			 private String base_url;
			
			 private boolean ismapupload;
			
			 private int master_tier_setup_code;
			
			 private boolean isactive;

			 @Column(name ="state_code")
			public int getStateCode() {
				return stateCode;
			}

			public void setStateCode(int stateCode) {
				this.stateCode = stateCode;
			}
			 @Column(name = "category")
			public char getCategory() {
				return category;
			}

			public void setCategory(char category) {
				this.category = category;
			}
			 @Column(name = "tier_setup_code")
			 public Integer getTier_setup_code() {
					return tier_setup_code;
				}

				public void setTier_setup_code(Integer tier_setup_code) {
					this.tier_setup_code = tier_setup_code;
				}
			
			@Column(name = "nomenclature_english")
			public String getNomenclature_english() {
				return nomenclature_english;
			}

			

			public void setNomenclature_english(String nomenclature_english) {
				this.nomenclature_english = nomenclature_english;
			}
			
			 @Column(name = "nomenclature_local")
			public String getNomenclature_local() {
				return nomenclature_local;
			}

			public void setNomenclature_local(String nomenclature_local) {
				this.nomenclature_local = nomenclature_local;
			}
			
			 @Column(name = "ispartixgovt")
			public boolean isIspartixgovt() {
				return ispartixgovt;
			}

			public void setIspartixgovt(boolean ispartixgovt) {
				this.ispartixgovt = ispartixgovt;
			}
			 @Column(name = "base_url")
			public String getBase_url() {
				return base_url;
			}

			public void setBase_url(String base_url) {
				this.base_url = base_url;
			}
			 @Column(name = "ismapupload")
			public boolean isIsmapupload() {
				return ismapupload;
			}

			public void setIsmapupload(boolean ismapupload) {
				this.ismapupload = ismapupload;
			}
			 @Id
			 @Column(name = "master_tier_setup_code")
			public int getMaster_tier_setup_code() {
				return master_tier_setup_code;
			}

			public void setMaster_tier_setup_code(int master_tier_setup_code) {
				this.master_tier_setup_code = master_tier_setup_code;
			}
			 @Column(name = "isactive")
			public boolean isIsactive() {
				return isactive;
			}

			public void setIsactive(boolean isactive) {
				this.isactive = isactive;
			}

			

		  
	}

