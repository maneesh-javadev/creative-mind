package in.nic.pes.lgd.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity

@NamedNativeQueries({
		@NamedNativeQuery(query = "select d.dlc,d.district_name_english, cast(case when  ldc.status is null then 'N' else "
				+ " ldc.status end as character),cast(case when status='F' then 'Freeze by District User'"
				+ " when status='U' then 'Unfreeze by State user' end as character varying)action_status,ldc.updated_on,"
				+ "cast(null as character varying)remark,cast(n.user_id as integer),"
				+ "(select count(1) from subdistrict t where t.isactive and t.dlc=d.dlc)as no_of_tlc,"
				+ "( select count(1) from village v where v.isactive and v.tlc in(select t.tlc from subdistrict t where t.isactive and t.dlc=d.dlc))as no_of_vlc"
				+ " from district d left join  nodal_officer n on d.slc=n.state_code and d.dlc=n.district_code and "
				+ " n.user_type=:userType and n.isactive left join lgd_data_confirmation ldc on n.user_id=ldc.user_id and ldc.isactive "
				+ " where d.slc=:stateCode and d.isactive order by 2 ", name = "getDistrictwiseFreezeStatus", resultClass = DistrictFreezeEntity.class),
		
		@NamedNativeQuery(query = "select dlc,district_name_english,status,action_status,updated_on,remark,user_id,sum(no_of_tlc)no_of_tlc ,sum(no_of_vlc)no_of_vlc from"
				+ " (select d.dlc,d.district_name_english, cast(case when  ldc.status is null then 'N' else "
				+ " ldc.status end as character) status,cast(case when status='F' then 'Freeze by District User 'when status='U' then 'Unfreeze by State user' end as character varying)action_status,ldc.updated_on,cast(null as character varying)remark,cast(n.user_id as integer),"
				+ " (select count(ll.local_body_code) from localbody ll where ll.isactive and ll.local_body_code=l.local_body_code)as no_of_tlc,"
				+ " (select count(1) from localbody_ward w where w.isactive and w.lblc=l.lblc )as no_of_vlc"
				+ " from localbody l, local_body_type lt,localbody_districts ld,district d left join  nodal_officer n on d.slc=n.state_code and d.dlc=n.district_code and "
				+ " n.user_type=:userType and n.isactive left join lgd_data_confirmation ldc on n.user_id=ldc.user_id and ldc.isactive "
				+ " where d.slc=:stateCode and d.isactive and l.isactive and l.local_body_code=ld.local_body_code and l.local_body_version=ld.local_body_version and ld.district_code=d.dlc and lt.isactive and l.local_body_type_code=lt.local_body_type_code and lt.category='U'  )x group by dlc,district_name_english,status,action_status,updated_on,remark,user_id order by 2 ",name = "getDistrictwiseFreezeStatusULB", resultClass = DistrictFreezeEntity.class)})






public class DistrictFreezeEntity {

	@Id
	@Column(name = "dlc")
	private Integer dlc;

	@Column(name = "district_name_english")
	private String districtNameEnglish;

	@Column(name = "status")
	private Character entityStatus;

	@Column(name = "action_status")
	private String actionStatus;

	@Column(name = "updated_on")
	private Timestamp updatedOn;

	@Column(name = "remark")
	private String remark;

	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "no_of_tlc")
	private Integer nooftlc;
	
	@Column(name = "no_of_vlc")
	private Integer noofvlc;
	

	public Integer getNooftlc() {
		return nooftlc;
	}

	public void setNooftlc(Integer nooftlc) {
		this.nooftlc = nooftlc;
	}

	public Integer getNoofvlc() {
		return noofvlc;
	}

	public void setNoofvlc(Integer noofvlc) {
		this.noofvlc = noofvlc;
	}

	@Transient
	private Character unfreezeStatus;

	public Integer getDlc() {
		return dlc;
	}

	public void setDlc(Integer dlc) {
		this.dlc = dlc;
	}

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public Character getEntityStatus() {
		return entityStatus;
	}

	public void setEntityStatus(Character entityStatus) {
		this.entityStatus = entityStatus;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Character getUnfreezeStatus() {
		return unfreezeStatus;
	}

	public void setUnfreezeStatus(Character unfreezeStatus) {
		this.unfreezeStatus = unfreezeStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
