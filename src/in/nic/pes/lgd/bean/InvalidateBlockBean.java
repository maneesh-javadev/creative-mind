package in.nic.pes.lgd.bean;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
/**
*
* @author chandan
*/
@Entity
@NamedNativeQuery(query=" SELECT * FROM invalidate_block_fn(:stateCode,:userId,:operationCode,:flagCode,:orderNo,:orderDate,:effectiveDate," +
		":ynFlag,:sourceBlock,:targetBlock,:gazpubDate,:blockVillages,:blockULB)",name="invalidateBlockFn", resultClass = InvalidateBlockBean.class)
public class InvalidateBlockBean {
	
    
	/**
	 * @return the invalidate_block_fn
	 */
    @Id
    @Basic(optional = false)
    @Column(name = "invalidate_block_fn",nullable=false)
	private String invalidatePRILB;

	public String getInvalidatePRILB() {
		return invalidatePRILB;
	}

	public void setInvalidatePRILB(String invalidatePRILB) {
		this.invalidatePRILB = invalidatePRILB;
	}

		
		
}
