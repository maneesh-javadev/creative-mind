package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries(
		{
			@NamedNativeQuery(
					name="GET_LGD_UPDATION_STATE",
					query="select * from get_lgd_updation_report_fn()",
					resultClass=LGDUpdationEntity.class
				
			),
			
			@NamedNativeQuery(
					name="GET_LGD_UPDATION_DISTRICT",
					query="select * from get_lgd_updation_report_fn(:stateCode)",
					resultClass=LGDUpdationEntity.class
				
			)
		}
)

public class LGDUpdationEntity {
	
	
	@Id
	@Column(name="entity_code")
	private Integer entityCode;
	
	
	@Column(name="entity_name")
	private String entityName;
	
	
	@Column(name="revenue_status")
	private String revenueStatus;
	
	@Column(name="ulb_status")
	private String ulbStatus;
	
	
	@Column(name="pri_status")
	private String priStatus;
	
	@Column(name="trd_status")
	private String trdStatus;
	
	@Column(name="revenue_color")
	private String revenueColor;
	
	@Column(name="ulb_color")
	private String ulbColor;
	
	
	@Column(name="pri_color")
	private String priColor;
	
	@Column(name="trd_color")
	private String trdColor;

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getRevenueStatus() {
		return revenueStatus;
	}

	public void setRevenueStatus(String revenueStatus) {
		this.revenueStatus = revenueStatus;
	}

	public String getUlbStatus() {
		return ulbStatus;
	}

	public void setUlbStatus(String ulbStatus) {
		this.ulbStatus = ulbStatus;
	}

	public String getPriStatus() {
		return priStatus;
	}

	public void setPriStatus(String priStatus) {
		this.priStatus = priStatus;
	}

	public String getTrdStatus() {
		return trdStatus;
	}

	public void setTrdStatus(String trdStatus) {
		this.trdStatus = trdStatus;
	}

	public String getRevenueColor() {
		return revenueColor;
	}

	public void setRevenueColor(String revenueColor) {
		this.revenueColor = revenueColor;
	}

	public String getUlbColor() {
		return ulbColor;
	}

	public void setUlbColor(String ulbColor) {
		this.ulbColor = ulbColor;
	}

	public String getPriColor() {
		return priColor;
	}

	public void setPriColor(String priColor) {
		this.priColor = priColor;
	}

	public String getTrdColor() {
		return trdColor;
	}

	public void setTrdColor(String trdColor) {
		this.trdColor = trdColor;
	}

	
	
	
}
