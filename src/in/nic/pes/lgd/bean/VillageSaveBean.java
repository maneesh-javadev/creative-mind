package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

/*
 * Add  parameter  @operationState
 *                 @stateCode
 *                 @districtCode 
 * on 22-07-2014
 */
@Entity
@NamedNativeQuery(query="Select * from create_village_fn(:subDistrict,:userId,:villageNameEnglish,:orderNo,:ordertimeStampDate,:effectivetimeStampDate,:govtOrder,"
		               + ":gazPubtimeStampDate,:villageNameLocale,:villageNameAliasEn,:villageNameAliasLocal,:villageType,:census,:ssCode,:survayNumber,:gisNodes,"
		               + ":upLoadMap,:formationTypeString,:ulbCodeValid,:fullContributedVillage,:surveyNumberList,:surveyNumberListOld,:renameNameVillageList,"
		               + ":withoutRenameNameVillageList,:renameIdVillageList,:operationCode,:flagCode,:operationState,:stateCode,:districtCode,:isExistGovtOrder,"
		               + ":orderCode,:isPesa)",name="VillagePublishQuery",resultClass=VillageSaveBean.class)

public class VillageSaveBean
{
	@Id 
	private String  create_village_fn;
    
	@Column(name = "create_village_fn", nullable = false)
	public String getCreate_village_fn() {
		return create_village_fn;
	}

	public void setCreate_village_fn(String create_village_fn) {
		this.create_village_fn = create_village_fn;
	}
	
	

	/*@Column(name = "create_village_fn", nullable = false)
	public boolean isCreate_village_fn() {
		return create_village_fn;
	}

	public void setCreate_village_fn(boolean create_village_fn) {
		this.create_village_fn = create_village_fn;
	}
*/
	
	
}