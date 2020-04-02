package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(query="select a.local_body_code as localBodyCode,a.local_body_name_english as lBNameEnglish,a.local_body_name_local as lBNameLocal,a.transaction_id as transactionid,'' as coveraged from localbody a, " +
							"(select x.local_body_code,max(x.local_body_version) max_version from localbody x where x.parent_lblc = (select lblc from localbody where local_body_code=:localbodycode and isactive) group by x.local_body_code) z " +
                            "where a.local_body_code=z.local_body_code and a.local_body_version=z.max_version and a.local_body_type_code=3 and a.isactive =false and a.flag_code in(21,22) and a.parent_lblc " +
                            "=(select lblc from localbody where local_body_code=:localbodycode and isactive) and not exists (select b.local_body_code from localbody b " +
                            "where a.local_body_code=b.local_body_code and b.isactive) order by 2",name="getDeNotifiedVillagePanchayats",resultClass=VillagePanchyatDeNotified.class),
	@NamedNativeQuery(query="select a.local_body_code as localBodyCode,a.local_body_name_english as lBNameEnglish,a.local_body_name_local as lBNameLocal,(select array_to_string(array(select (CASE WHEN b.land_region_type='V' THEN " +
							"(SELECT * FROM get_lr_coverage_for_inactive('V',b.lrlc )) WHEN b.land_region_type='T' THEN (SELECT * FROM get_lr_coverage_for_inactive('T',b.lrlc )) " + 
							"WHEN b.land_region_type='D' THEN (SELECT * FROM get_lr_coverage_for_inactive('D',b.lrlc ))END) FROM  (SELECT * FROM   lb_covered_landregion where " + 
							"lb_covered_region_code=a.lb_covered_region_code) b) ,',')) as coveraged,1 as transactionid from localbody a where a.transaction_id=:tranId and a.local_body_type_code=3 " +
							"and exists (select y.local_body_code,y.lb_version from (select local_body_code, max(local_body_version) lb_version from localbody x where a.local_body_code=x.local_body_code group by x.local_body_code) y " +
							"where a.local_body_code=y.local_body_code and a.local_body_version= y. lb_version)",name="getDeNotifiedVPRLB",resultClass=VillagePanchyatDeNotified.class),
	@NamedNativeQuery(query="select a.local_body_code as localBodyCode,a.local_body_name_english as lBNameEnglish,a.local_body_name_local as lBNameLocal, " +
							"(select array_to_string(array(select (CASE WHEN b.land_region_type='V'	THEN (SELECT * FROM get_lr_coverage_for_inactive('V',b.lrlc )) " +
							"WHEN b.land_region_type='T' THEN (SELECT * FROM get_lr_coverage_for_inactive('T',b.lrlc )) WHEN b.land_region_type='D' THEN " +
							"(SELECT * FROM get_lr_coverage_for_inactive('D',b.lrlc ))END) FROM  (SELECT * FROM   lb_covered_landregion where lb_covered_region_code=a.lb_covered_region_code) b) ,',')) as coveraged,1 as transactionid " +
							"from localbody a where isactive and local_body_code=(select local_body_code from localbody where transaction_id=:tranId and local_body_type_code<>3)",name="getDeNotifiedVPULB",resultClass=VillagePanchyatDeNotified.class),
})

public class VillagePanchyatDeNotified implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "localBodyCode")
     private int localBodyCode;
	
	@Column(name = "lBNameEnglish")
	 private String lBNameEnglish;
	
	@Column(name = "lBNameLocal")
	 private String lBNameLocal;
	
	@Column(name = "transactionid")
	private Integer transactionid;

	@Column(name = "coveraged")
	private String coveraged;
	/**
	 * @return the localBodyCode
	 */
	public int getLocalBodyCode() {
		return localBodyCode;
	}

	/**
	 * @param localBodyCode the localBodyCode to set
	 */
	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	/**
	 * @return the lBNameEnglish
	 */
	public String getlBNameEnglish() {
		return lBNameEnglish;
	}

	/**
	 * @param lBNameEnglish the lBNameEnglish to set
	 */
	public void setlBNameEnglish(String lBNameEnglish) {
		this.lBNameEnglish = lBNameEnglish;
	}

	/**
	 * @return the lBNameLocal
	 */
	public String getlBNameLocal() {
		return lBNameLocal;
	}

	/**
	 * @param lBNameLocal the lBNameLocal to set
	 */
	public void setlBNameLocal(String lBNameLocal) {
		this.lBNameLocal = lBNameLocal;
	}

	/**
	 * @return the transactionid
	 */
	public Integer getTransactionid() {
		return transactionid;
	}

	/**
	 * @param transactionid the transactionid to set
	 */
	public void setTransactionid(Integer transactionid) {
		this.transactionid = transactionid;
	}

	/**
	 * @return the coveraged
	 */
	public String getCoveraged() {
		return coveraged;
	}

	/**
	 * @param coveraged the coveraged to set
	 */
	public void setCoveraged(String coveraged) {
		this.coveraged = coveraged;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VillagePanchyatDeNotified [localBodyCode=" 
				+ localBodyCode + ", " 
				+ (lBNameEnglish != null ? "lBNameEnglish=" 
				+ lBNameEnglish + ", " : "") 
				+ (lBNameLocal != null ? "lBNameLocal=" 
				+ lBNameLocal + ", " : "")
				+ (transactionid != null ? "transactionid=" 
				+ transactionid : "") + "]";
	}
	
	
}
