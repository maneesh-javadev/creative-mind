package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT * FROM invalidate_localbody_fn(:local_body_code,:local_body_name,:userId,:shift,:effectiveDate,:govtOrder,:orderDate,:local_body_type_code,:operation_code,:flag_code,:gzbDate,:order_path,:lblist,:unResolvedFlag)",name="invalidate_prilb_fn",resultClass=invalidatePRIlb.class)
public class invalidatePRIlb implements Serializable {
	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
	@Column(name = "invalidate_localbody_fn", nullable = false)
	private String invalidatePRILB;

	public String getInvalidatePRILB() {
		return invalidatePRILB;
	}

	public void setInvalidatePRILB(String invalidatePRILB) {
		this.invalidatePRILB = invalidatePRILB;
	}

	

}
