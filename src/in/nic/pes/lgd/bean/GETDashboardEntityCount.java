package in.nic.pes.lgd.bean;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({ 
@NamedNativeQuery(query = "select * from dashboard_entities_count where state_code=:stateCode", name = "GETDashboardEntityCountDTO", resultClass = GETDashboardEntityCount.class),
@NamedNativeQuery(query = "select cast(0 as integer)state_code,cast('Center' as varchar )state_name_english, cast( null as "
+ " character)state_or_ut,cast(sum(invalidate_census_village)as integer)invalidate_census_village,"
+ "cast(sum(invalidate_village)as integer)invalidate_village,cast(sum(inhabitation_village)as integer)inhabitation_village,"
+ "cast(sum(total_no_of_village)as integer)total_no_of_village,cast(sum(unmapped_village_in_block)as integer)unmapped_village_in_block,"
+ "cast(sum(unmapped_village_in_lbp)as integer)unmapped_village_in_lbp,cast(sum(unmapped_village_in_lbt)as integer)unmapped_village_in_lbt,"
+ "cast(sum(unmapped_village_in_lbu)as integer)unmapped_village_in_lbu,cast(sum(mapped_village_to_multiple_gp)as integer)mapped_village_to_multiple_gp,"
+ "cast(sum(village_map_block_multi_dist)as integer)village_map_block_multi_dist,cast(sum(village_map_gp_multi_dist)as integer)village_map_gp_multi_dist,"
+ "cast(sum(ulb_map_multi_dist)as integer)ulb_map_multi_dist,cast(sum(distrub_state_p)as integer)distrub_state_p,cast(sum(distrub_state_t)as integer)distrub_state_t,"
+ "cast(sum(distrub_state_u)as integer)distrub_state_u,cast(sum(invalidate_lb_p)as integer)invalidate_lb_p,"
+ "cast(sum(invalidate_lb_u)as integer)invalidate_lb_u,cast(sum(invalidate_lb_t )as integer)invalidate_lb_t"
+ " from dashboard_entities_count", name = "GETDashboardAllEntityCountDTO", resultClass = GETDashboardEntityCount.class)

})
public class GETDashboardEntityCount {
	
	@Id
	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name="state_name_english")
	private String stateNameEnglish;
	
	@Column(name="state_or_ut")
	private Character stateorUT;
	
	@Column(name="invalidate_census_village")
	private Integer invalidateCensusVillage;
	
	@Column(name="invalidate_village")
	private Integer invalidateVillage;
	
	@Column(name="inhabitation_village")
	private Integer unHabitationVillage;
	
	@Column(name="total_no_of_village")
	private Integer totelVillage;
	
	@Column(name="unmapped_village_in_block")
	private Integer unmappedVillageinBlock;

	@Column(name="unmapped_village_in_lbp")
	private Integer unmappedVillageinLbp;
	
	@Column(name="unmapped_village_in_lbt")
	private Integer unmappedVillageinLbt;
	
	@Column(name="unmapped_village_in_lbu")
	private Integer unmappedVillageinLbu;
	
	@Column(name="mapped_village_to_multiple_gp")
	private Integer mappedVillagetoMultipleGp;
	
	@Column(name="village_map_block_multi_dist")
	private Integer villageMapBlockMultiDist;
	
	@Column(name="village_map_gp_multi_dist")
	private Integer villageMapGpMultiDist;
	
	@Column(name="ulb_map_multi_dist")
	private Integer ulbMapMultiDist;
	
	@Column(name="distrub_state_p")
	private Integer distrubStateP;
	
	@Column(name="distrub_state_t")
	private Integer distrubStateT;
	
	@Column(name="distrub_state_u")
	private Integer distrubStateU;
	
	@Column(name="invalidate_lb_p")
	private Integer invalidateLbP;
	
	@Column(name="invalidate_lb_u")
	private Integer invalidateLbU;
	
	@Column(name="invalidate_lb_t")
	private Integer invalidateLbT;

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}

	public Character getStateorUT() {
		return stateorUT;
	}

	public void setStateorUT(Character stateorUT) {
		this.stateorUT = stateorUT;
	}

	public Integer getInvalidateCensusVillage() {
		return invalidateCensusVillage;
	}

	public void setInvalidateCensusVillage(Integer invalidateCensusVillage) {
		this.invalidateCensusVillage = invalidateCensusVillage;
	}

	public Integer getInvalidateVillage() {
		return invalidateVillage;
	}

	public void setInvalidateVillage(Integer invalidateVillage) {
		this.invalidateVillage = invalidateVillage;
	}

	public Integer getUnHabitationVillage() {
		return unHabitationVillage;
	}

	public void setUnHabitationVillage(Integer unHabitationVillage) {
		this.unHabitationVillage = unHabitationVillage;
	}

	public Integer getTotelVillage() {
		return totelVillage;
	}

	public void setTotelVillage(Integer totelVillage) {
		this.totelVillage = totelVillage;
	}

	public Integer getUnmappedVillageinBlock() {
		return unmappedVillageinBlock;
	}

	public void setUnmappedVillageinBlock(Integer unmappedVillageinBlock) {
		this.unmappedVillageinBlock = unmappedVillageinBlock;
	}

	public Integer getUnmappedVillageinLbp() {
		return unmappedVillageinLbp;
	}

	public void setUnmappedVillageinLbp(Integer unmappedVillageinLbp) {
		this.unmappedVillageinLbp = unmappedVillageinLbp;
	}

	public Integer getUnmappedVillageinLbt() {
		return unmappedVillageinLbt;
	}

	public void setUnmappedVillageinLbt(Integer unmappedVillageinLbt) {
		this.unmappedVillageinLbt = unmappedVillageinLbt;
	}

	public Integer getUnmappedVillageinLbu() {
		return unmappedVillageinLbu;
	}

	public void setUnmappedVillageinLbu(Integer unmappedVillageinLbu) {
		this.unmappedVillageinLbu = unmappedVillageinLbu;
	}

	public Integer getMappedVillagetoMultipleGp() {
		return mappedVillagetoMultipleGp;
	}

	public void setMappedVillagetoMultipleGp(Integer mappedVillagetoMultipleGp) {
		this.mappedVillagetoMultipleGp = mappedVillagetoMultipleGp;
	}

	public Integer getVillageMapBlockMultiDist() {
		return villageMapBlockMultiDist;
	}

	public void setVillageMapBlockMultiDist(Integer villageMapBlockMultiDist) {
		this.villageMapBlockMultiDist = villageMapBlockMultiDist;
	}

	public Integer getVillageMapGpMultiDist() {
		return villageMapGpMultiDist;
	}

	public void setVillageMapGpMultiDist(Integer villageMapGpMultiDist) {
		this.villageMapGpMultiDist = villageMapGpMultiDist;
	}

	public Integer getUlbMapMultiDist() {
		return ulbMapMultiDist;
	}

	public void setUlbMapMultiDist(Integer ulbMapMultiDist) {
		this.ulbMapMultiDist = ulbMapMultiDist;
	}

	public Integer getDistrubStateP() {
		return distrubStateP;
	}

	public void setDistrubStateP(Integer distrubStateP) {
		this.distrubStateP = distrubStateP;
	}

	public Integer getDistrubStateT() {
		return distrubStateT;
	}

	public void setDistrubStateT(Integer distrubStateT) {
		this.distrubStateT = distrubStateT;
	}

	public Integer getDistrubStateU() {
		return distrubStateU;
	}

	public void setDistrubStateU(Integer distrubStateU) {
		this.distrubStateU = distrubStateU;
	}

	public Integer getInvalidateLbP() {
		return invalidateLbP;
	}

	public void setInvalidateLbP(Integer invalidateLbP) {
		this.invalidateLbP = invalidateLbP;
	}

	public Integer getInvalidateLbU() {
		return invalidateLbU;
	}

	public void setInvalidateLbU(Integer invalidateLbU) {
		this.invalidateLbU = invalidateLbU;
	}

	public Integer getInvalidateLbT() {
		return invalidateLbT;
	}

	public void setInvalidateLbT(Integer invalidateLbT) {
		this.invalidateLbT = invalidateLbT;
	}

	 
	
	
	
	
	
	
	
	
	

	
	

	
	

}
