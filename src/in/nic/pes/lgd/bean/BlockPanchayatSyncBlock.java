package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQueries({ 
	@NamedNativeQuery(query = "select localbody.local_body_code, localbody.local_body_name_english, "
							+ "case when (select count(1) from synced_block_with_bp where local_body_code = localbody.local_body_code and district_code = localbody_districts.district_code) > 0 then true else false end as is_synced "
							+ "from localbody, localbody_districts where "
			                + "localbody.local_body_code = localbody_districts.local_body_code and "
			                + "localbody.local_body_version = localbody_districts.local_body_version and "
			                + "localbody.local_body_type_code = 2 and localbody.slc = :stateCode and localbody.isactive and localbody_districts.district_code = :districtCode "
			                + "order by localbody.local_body_name_english asc", name = "Fetch_Block_Panchayats", resultClass = BlockPanchayatSyncBlock.class), 
})

public class BlockPanchayatSyncBlock implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8865665817591949843L;

	@Id
	@Column(name = "local_body_code")
	private Integer localBodyCode;
	
	@Column(name = "local_body_name_english")
	private String localBodyNameEnglish;
	
	@Column(name = "is_synced")
	private Boolean isSynced;
		
	@Transient
	private Integer paramDistrictCode;

	@Transient
	private List<BlockPanchayatSyncBlock> listOfBlockPanchayats = new ArrayList<>();
	
	@Transient
	private List<Block> listOfBlocks = new ArrayList<>();

	@Transient
	private List<Object[]> listSyncedBlocks = new ArrayList<>();
	
	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	
	public Boolean getIsSynced() {
		return isSynced;
	}

	public void setIsSynced(Boolean isSynced) {
		this.isSynced = isSynced;
	}

	public Integer getParamDistrictCode() {
		return paramDistrictCode;
	}

	public void setParamDistrictCode(Integer paramDistrictCode) {
		this.paramDistrictCode = paramDistrictCode;
	}

	public List<BlockPanchayatSyncBlock> getListOfBlockPanchayats() {
		return listOfBlockPanchayats;
	}

	public void setListOfBlockPanchayats(
			List<BlockPanchayatSyncBlock> listOfBlockPanchayats) {
		this.listOfBlockPanchayats = listOfBlockPanchayats;
	}

	public List<Block> getListOfBlocks() {
		return listOfBlocks;
	}

	public void setListOfBlocks(List<Block> listOfBlocks) {
		this.listOfBlocks = listOfBlocks;
	}

	public List<Object[]> getListSyncedBlocks() {
		return listSyncedBlocks;
	}

	public void setListSyncedBlocks(List<Object[]> listSyncedBlocks) {
		this.listSyncedBlocks = listSyncedBlocks;
	}
	
	
}
