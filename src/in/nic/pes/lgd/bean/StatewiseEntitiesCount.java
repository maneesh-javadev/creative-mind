package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="select st.state_code,s.state_name_english,cast(0 as integer)as no_of_states,st.no_of_districts,"
+ "st.no_of_subdistricts,st.no_of_blocks,st.no_of_villages,st.no_of_zps,st.no_of_bps,st.no_of_vps,st.no_of_tlbs,"
+ "st.no_of_ulbs from statewise_totals st inner join state s on s.state_code=st.state_code where s.isactive",                 
name="getStateWiseEntityCount",resultClass=StatewiseEntitiesCount.class),
@NamedNativeQuery(query="select cast('' as character varying)state_name_english,cast(0 as integer)state_code,"
+ "cast(count(1) as integer)no_of_states ,cast(sum(no_of_districts)as integer)no_of_districts,"
+ "cast(sum(no_of_subdistricts)as integer)no_of_subdistricts,cast(sum(no_of_blocks)as integer)no_of_blocks,"
+ "cast(sum(no_of_villages)as integer)no_of_villages,cast(sum(no_of_zps)as integer)no_of_zps,"
+ "cast(sum(no_of_bps)as integer)no_of_bps,cast(sum(no_of_vps)as integer)no_of_vps,"
+ "cast(sum(no_of_tlbs)as integer)no_of_tlbs,cast(sum(no_of_ulbs)as integer)no_of_ulbs from statewise_totals",     
name="getIndiaLevelEnitiesCount",resultClass=StatewiseEntitiesCount.class),
@NamedNativeQuery(query="select st.state_code,cast('' as character varying)state_name_english,cast(0 as integer)as "
+ "no_of_states,st.no_of_districts,st.no_of_subdistricts,st.no_of_blocks,st.no_of_villages,st.no_of_zps,st.no_of_bps,"
+ "st.no_of_vps,st.no_of_tlbs,st.no_of_ulbs  from statewise_totals_list_fn_new() st"
,name="updateStatewiseTotals",resultClass=StatewiseEntitiesCount.class)
		

})


  
public class StatewiseEntitiesCount {
	@Id
	@Column(name="state_code")
	private Integer state_code;
	
	@Column(name="state_name_english")
	private String state_name_english;
	
	@Column(name="no_of_states")
	private Integer no_of_states;
	
	@Column(name="no_of_districts")
	private Integer no_of_districts;
	
	@Column(name="no_of_subdistricts")
	private Integer no_of_subdistricts;
	
	@Column(name="no_of_blocks")
	private Integer no_of_blocks;
	
	@Column(name="no_of_villages")
	private Integer no_of_villages;
	
	
	@Column(name="no_of_zps")
	private Integer no_of_zps;
	
	@Column(name="no_of_bps")
	private Integer no_of_bps;
	
	@Column(name="no_of_vps")
	private Integer no_of_vps;
	
	@Column(name="no_of_tlbs")
	private Integer no_of_tlbs;
	
	@Column(name="no_of_ulbs")
	private Integer no_of_ulbs;

	public Integer getState_code() {
		return state_code;
	}

	public void setState_code(Integer state_code) {
		this.state_code = state_code;
	}

	public String getState_name_english() {
		return state_name_english;
	}

	public void setState_name_english(String state_name_english) {
		this.state_name_english = state_name_english;
	}

	public Integer getNo_of_states() {
		return no_of_states;
	}

	public void setNo_of_states(Integer no_of_states) {
		this.no_of_states = no_of_states;
	}

	public Integer getNo_of_districts() {
		return no_of_districts;
	}

	public void setNo_of_districts(Integer no_of_districts) {
		this.no_of_districts = no_of_districts;
	}

	public Integer getNo_of_subdistricts() {
		return no_of_subdistricts;
	}

	public void setNo_of_subdistricts(Integer no_of_subdistricts) {
		this.no_of_subdistricts = no_of_subdistricts;
	}

	public Integer getNo_of_blocks() {
		return no_of_blocks;
	}

	public void setNo_of_blocks(Integer no_of_blocks) {
		this.no_of_blocks = no_of_blocks;
	}

	public Integer getNo_of_villages() {
		return no_of_villages;
	}

	public void setNo_of_villages(Integer no_of_villages) {
		this.no_of_villages = no_of_villages;
	}

	public Integer getNo_of_zps() {
		return no_of_zps;
	}

	public void setNo_of_zps(Integer no_of_zps) {
		this.no_of_zps = no_of_zps;
	}

	public Integer getNo_of_bps() {
		return no_of_bps;
	}

	public void setNo_of_bps(Integer no_of_bps) {
		this.no_of_bps = no_of_bps;
	}

	public Integer getNo_of_vps() {
		return no_of_vps;
	}

	public void setNo_of_vps(Integer no_of_vps) {
		this.no_of_vps = no_of_vps;
	}

	public Integer getNo_of_tlbs() {
		return no_of_tlbs;
	}

	public void setNo_of_tlbs(Integer no_of_tlbs) {
		this.no_of_tlbs = no_of_tlbs;
	}

	public Integer getNo_of_ulbs() {
		return no_of_ulbs;
	}

	public void setNo_of_ulbs(Integer no_of_ulbs) {
		this.no_of_ulbs = no_of_ulbs;
	}

	
	
	
	
	

}
