package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import org.hibernate.annotations.Entity;

@Entity
@NamedNativeQueries({@NamedNativeQuery(query = "select a.local_body_code,a.coordinates,a.local_body_name_english,a.local_body_name_local,a.alias_english,alias_local,a.slc,b.effective_date,b.gaz_pub_date,b.order_date,b.order_no,c.order_code from localbody a left join government_order_entitywise c on a.local_body_code = c.entity_code and a.local_body_version = c.entity_version AND c.entity_type = 'G' left join government_order b on c.order_code=b.order_code WHERE  a.local_body_code =:localBodyCode and a.isactive=true", name = "getviewlocalbody", resultClass = ViewLocalBodyforview.class),
					 @NamedNativeQuery(query = "select a.local_body_code,a.local_body_name_english,a.local_body_name_local,a.alias_english,a.alias_local,a.sscode,a.slc,a.coordinates,b.order_code,b.order_no,b.order_date,b.effective_date,b.gaz_pub_date,d.file_name,d.file_location,e.file_name as map_file_name,e.map_entity_type,e.file_location as map_file_path, from localbody a,government_order b, government_order_entitywise c, attachment d, map_attachment e where a.local_body_code = c.entity_code AND c.entity_type = 'G' and a.local_body_version = c.entity_version and c.order_code = b.order_code and a.local_body_code = :localBodyCode and a.isactive = true and c.order_code = d.announcement_id and a.map_attachment_code = e.map_attachment_code and c.entity_version = (select max(entity_version) FROM government_order_entitywise c where c.entity_code = :localBodyCode)", name = "getLocalBodyDetail_old", resultClass = ViewLocalBodyforview.class)})
public class ViewLocalBodyforview implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "local_body_code", nullable = false)
	private Integer localBodyCode;
	@Column(name = "local_body_name_english", nullable = false)
	private String localBodyNameEnglish;
	@Column(name = "local_body_name_local", nullable = false)
	private String localBodyNameLocal;
	@Column(name = "alias_english", nullable = false)
	private String aliasEnglish;
	@Column(name = "alias_local", nullable = false)
	private String aliasLocal;
	@Column(name = "slc", nullable = false)
	private Integer slc;
	@Column(name = "order_no", nullable = false)
	private String orderNo;
	@Column(name = "order_date", nullable = false)
	private Date orderDate;
	@Column(name = "effective_date", nullable = false)
	private Date effectiveDate;
	@Column(name = "gaz_pub_date", nullable = false)
	private Date gazPubDate;
	@Column(name = "order_code", nullable = false)
	private Integer orderCode;
	@Column(name = "coordinates", nullable = false)
	private String coordinates;
	
	/* Added by sushil on 15th Jan 2013 */
	@Column(name = "sscode")
	private Integer sscode;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_location")
    private String file_location;
    
	@Column(name = "map_file_name")
    private String mapFileName;
    
	@Column(name = "map_entity_type")
    private String mapEntityType;
	
	@Column(name = "map_file_path")
    private String mapFilePath;
	
	public String getMapFilePath() {
		return mapFilePath;
	}

	public void setMapFilePath(String mapFilePath) {
		this.mapFilePath = mapFilePath;
	}

	public Integer getSscode() {
		return sscode;
	}

	public void setSscode(Integer sscode) {
		this.sscode = sscode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFile_location() {
		return file_location;
	}

	public void setFile_location(String file_location) {
		this.file_location = file_location;
	}

	public String getMapFileName() {
		return mapFileName;
	}

	public void setMapFileName(String mapFileName) {
		this.mapFileName = mapFileName;
	}

	public String getMapEntityType() {
		return mapEntityType;
	}

	public void setMapEntityType(String mapEntityType) {
		this.mapEntityType = mapEntityType;
	}

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

	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}

	public String getAliasEnglish() {
		return aliasEnglish;
	}

	public void setAliasEnglish(String aliasEnglish) {
		this.aliasEnglish = aliasEnglish;
	}

	public String getAliasLocal() {
		return aliasLocal;
	}

	public void setAliasLocal(String aliasLocal) {
		this.aliasLocal = aliasLocal;
	}

	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getGazPubDate() {
		return gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

}
