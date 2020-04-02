package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
/*@NamedNativeQuery(query="select local_body_code as entity_code,local_body_name_english as entity_name_english,local_body_name_local as entity_name_local,'ULB' as entity_type from localbody" +
		                " where lblc in (select lblc from block_ulb where blc=:blc and isactive=true) and isactive=true"
                      +" union"
                      +" select village_code as entity_code,village_name_english as entity_name_english,village_name_local as entity_name_local, 'Village' as entity_type from village" +
                      " where vlc in (select vlc from block_village where blc=:blc and isactive=true) and isactive=true order by 2",name="getBlockWiseVillagesNUlb",resultClass=BlockWiseVillagesAndUlb.class)*/

@NamedNativeQuery(query=/*"select l.local_body_code as entity_code,l.local_body_name_english as entity_name_english,l.local_body_name_local as entity_name_local,'ULB' as entity_type,"
					   + " COALESCE(A.file_location,'')  as filelocation,A.file_timestamp from localbody l LEFT OUTER JOIN  government_order_entitywise Ge ON (l.local_body_code =Ge.entity_code "
					   + " AND l.local_body_version=Ge.entity_version and Ge.entity_type='G' ) LEFT OUTER JOIN  attachment A ON ge.order_code=A.announcement_id "
					   + "where l.lblc in (select lblc from block_ulb where blc=:blc and isactive=true) and isactive=true "
					   + " union "
					   + */"select v.village_code as entity_code,v.village_name_english as entity_name_english,v.village_name_local as entity_name_local, 'Village' as entity_type,"
					   + "COALESCE(A.file_location,'')  as filelocation,A.file_timestamp from village v LEFT OUTER JOIN  government_order_entitywise Ge ON (v.village_code =Ge.entity_code "
					   + " AND v.village_version=Ge.entity_version and Ge.entity_type='V' ) LEFT OUTER JOIN  attachment A ON ge.order_code=A.announcement_id "
					   + "where vlc in (select vlc from block_village where blc=:blc and isactive=true) and isactive=true "
					   + "order by 2",name="getBlockWiseVillagesNUlb",resultClass=BlockWiseVillagesAndUlb.class)

public class BlockWiseVillagesAndUlb {

	@Id
	@Column(name = "entity_code")
	private int entityCode;
	
	@Column(name = "entity_name_english")
	 private String entityNameEnglish;
	
	@Column(name = "entity_name_local")
	 private String entityNameLocal;
	
	@Column(name = "entity_type")
	 private String entityType;
	
	@Column(name = "filelocation")
	private String fileLocation;
	
	@Column(name = "file_timestamp")
	private String fileTimestamp;
	
	public int getEntityCode() {
		return entityCode;
	}
	
	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileTimestamp() {
		return fileTimestamp;
	}

	public void setFileTimestamp(String fileTimestamp) {
		this.fileTimestamp = fileTimestamp;
	}

	public void setEntityCode(int entityCode) {
		this.entityCode = entityCode;
	}
	
	public String getEntityNameEnglish() {
		return entityNameEnglish;
	}
	
	public void setEntityNameEnglish(String entityNameEnglish) {
		this.entityNameEnglish = entityNameEnglish;
	}
	
	public String getEntityNameLocal() {
		return entityNameLocal;
	}
	
	public void setEntityNameLocal(String entityNameLocal) {
		this.entityNameLocal = entityNameLocal;
	}
	
	public String getEntityType() {
		return entityType;
	}
	
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	
}






