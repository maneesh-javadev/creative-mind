package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from change_block_fn(:blkCode,:userId,:blkNameEnglishch,:orderNo,:orderDate,:effectiveDate,:order_path,:PeeviousBlkName,:gazPubDate,:blkNameLocal,:aliasEnglish,:aliasLocal,:slc)", name ="BlockChangeQuery",resultClass=ChangeBlock.class)
public class ChangeBlock {
	@Id
	@Column(name = "change_block_fn", nullable = false)
	private String change_block_fn;

	public String getChange_block_fn() {
		return change_block_fn;
	}

	public void setChange_block_fn(String change_block_fn) {
		this.change_block_fn = change_block_fn;
	}

	
	
	
	
}


