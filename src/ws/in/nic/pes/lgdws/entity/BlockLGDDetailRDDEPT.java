package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select b.block_code,cast(trim(block_name_english)as character varying)block_name_english from village v left join block_village bv on v.vlc=bv.vlc left join block b on b.blc=bv.blc "
						+ "where v.isactive and  b.isactive and bv.isactive and v.vlc=:villageCode order by block_name_english ", name = "Block_LGD_Detail_RD_DEPT", resultClass = BlockLGDDetailRDDEPT.class)})

@XmlRootElement(name = "BlockLGDDetailRDDEPT")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"blockCode","blockNameEnglish"})
public class BlockLGDDetailRDDEPT {
	

	
	@Id
	@Column(name="block_code")
	@XmlElement(name="block-code")
	private Integer blockCode;
	
	@Column(name="block_name_english")
	@XmlElement(name="block-name-english")
	private String blockNameEnglish;

	

	public Integer getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(Integer blockCode) {
		this.blockCode = blockCode;
	}

	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}

	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}
	
	

}
