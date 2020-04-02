package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "unpublished_items")
public class UnpublishedItems implements Serializable{
	private static final long serialVersionUID = 1L;

	@GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="seq_unpublished_items")})
    @Id
    @GeneratedValue(generator = "sequence")
    @Column(name = "item_code")
	private int itemCode;
	@Column(name = "item_type")
	private char itemType;
	@Column(name = "item_description")
	private String itemDescription;
	@Column(name = "item_page_link_code")
	private int itemPageLinkCode;
	@Column(name = "item_xml_path")
	private String itemXmlPath;
	@Column(name = "lastupdated")
	private Date lastupdated;
	@Column(name = "lastupdatedby")
	private int lastupdatedby;
	@Column(name = "createdon")
	private Date createdon;
	@Column(name = "createdby")
	private int createdby;
	@Column(name = "isactive")
	private boolean isactive;
	
	
// TODO Remove unused code found by UCDetector
// 	public UnpublishedItems(int itemCode, char itemType,
// 			String itemDescription, int itemPageLinkCode, String itemXmlPath,
// 			Date lastupdated, int lastupdatedby, Date createdon, int createdby,
// 			boolean isactive) {
// 		super();
// 		this.itemCode = itemCode;
// 		this.itemType = itemType;
// 		this.itemDescription = itemDescription;
// 		this.itemPageLinkCode = itemPageLinkCode;
// 		this.itemXmlPath = itemXmlPath;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.isactive = isactive;
// 	}

	public UnpublishedItems() {
		
	}
	
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public int getItemPageLinkCode() {
		return itemPageLinkCode;
	}
	public void setItemPageLinkCode(int itemPageLinkCode) {
		this.itemPageLinkCode = itemPageLinkCode;
	}
	public String getItemXmlPath() {
		return itemXmlPath;
	}
	public void setItemXmlPath(String itemXmlPath) {
		this.itemXmlPath = itemXmlPath;
	}
	public Date getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	public int getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(int lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public char getItemType() {
		return itemType;
	}

	public void setItemType(char itemType) {
		this.itemType = itemType;
	}
	
	
}
