package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
@Entity
@NamedNativeQuery(	query=" SELECT row_number() OVER () as rid, * FROM pri_wards_by_state(:stateCode)",name="PrilWardsDetailsbyState",resultClass=PrilWardsDetails.class)
public class PrilWardsDetails {
	
	@Id
	@Column(name = "rid", nullable = false)
	private Integer rid;
	
    @Column(name="ward_number")
	private String wardCode;
	 @Column(name="local_body_code")
	private Integer lbCode;
	 @Column(name="local_body_name_english")
	private String lbName;
	 @Column(name="local_body_type_name")
	private String lbtype;
	 @Column(name="district_name")
	private String lbNameDPtype;
	 @Column(name="intermediate_name")
	private String lbNameIPtype;
	 @Column(name="ward_name_english")
	private String wardName;
	 @Column(name="ward_name_local")
	private String wardNameLocal;
	
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public Integer getLbCode() {
		return lbCode;
	}
	public void setLbCode(Integer lbCode) {
		this.lbCode = lbCode;
	}
	public String getLbName() {
		return lbName;
	}
	public void setLbName(String lbName) {
		this.lbName = lbName;
	}
	public String getLbtype() {
		return lbtype;
	}
	public void setLbtype(String lbtype) {
		this.lbtype = lbtype;
	}
	public String getLbNameDPtype() {
		if(lbNameDPtype!=null)
		return lbNameDPtype;
		else 
			return "";
	}
	public void setLbNameDPtype(String lbNameDPtype) {
		this.lbNameDPtype = lbNameDPtype;
	}
	public String getLbNameIPtype() {
		if(lbNameIPtype!=null)
		return lbNameIPtype;
		else 
			return "";
	}
	public void setLbNameIPtype(String lbNameIPtype) {
		this.lbNameIPtype = lbNameIPtype;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getWardNameLocal() {
		return wardNameLocal;
	}
	public void setWardNameLocal(String wardNameLocal) {
		this.wardNameLocal = wardNameLocal;
	}
	
	
}	