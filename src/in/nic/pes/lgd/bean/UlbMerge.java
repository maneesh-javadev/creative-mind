
package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="Select * from merge_ulb(:lbcode,:p_order_no,:p_order_date,:p_effective_date,:typechanged,:lbtypenew,:ulbfull,:userid,:gaz_pub_date)",resultClass=UlbMerge.class, name = "ulbMerge")
public class UlbMerge
{
	@Id 
	private String merge_ulb;
	@Column(name = "merge_ulb", nullable = false)
	public String getMerge_ulb() {
		return merge_ulb;
	}

	public void setMerge_ulb(String merge_ulb) {
		this.merge_ulb = merge_ulb;
	}
	
	

		
}
