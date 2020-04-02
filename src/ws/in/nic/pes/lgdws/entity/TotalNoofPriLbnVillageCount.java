package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select 'District Panchayats-:' || (select count(1) from localbody where local_body_type_code in(1) "
						+ " and isactive)||';Block/Intermediate Panchayats-:'||(select count(1) from localbody where local_body_type_code in(2) "
						+ " and isactive)||';Gram Panchayats-:'||(select count(1) from localbody where local_body_type_code in(3)  "
						+ "and isactive)||';Villages-:'||(select count(1) from village where isactive)||';' as status"
						,  name = "GET_INDIA_WISE_PRI_LOCALBODY_AND_VILLAGE_COUNT",resultClass = TotalNoofPriLbnVillageCount.class),

@NamedNativeQuery(query = "select 'States with notified Fifth Schedule Areas (FSAs)-:'|| (select count(1) from state where is_pesa in('P','F') "
						+ " and isactive) ||';Districts with fully/partially notified FSAs-:'||(select count(1) from district where is_pesa in('P','F') "
						+ " and isactive )||';Blocks with fully/partially notified FSAs-:'||(select count(1) from localbody  where local_body_type_code =2"
						+ " and is_pesa in('P','F') and isactive )||';GPs in FSAs-:'||(select count(1) from localbody  where local_body_type_code =3  "
						+ " and is_pesa in('P','F') and isactive )  ||';Villages notified as FSAs-:'||(select count(1) from village where is_pesa in('P','F')"
						+ " and isactive) as status"
						,  name = "GET_INDIA_WISE_PESA_STATUS",resultClass = TotalNoofPriLbnVillageCount.class)


})



public class TotalNoofPriLbnVillageCount {
	
	@Id
	@Column(name="status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	

}
