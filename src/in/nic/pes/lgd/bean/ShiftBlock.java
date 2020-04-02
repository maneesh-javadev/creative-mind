package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from shift_block_fn(:districtSourceCode,:districtTargetCode,:blockCode,:userId,:effectiveDate,:order_no,:order_date,:gaz_pub_date,:order_path,:description)", name = "ShiftBlockQuery", resultClass = ShiftBlock.class)
public class ShiftBlock {
	@Id
	private String shift_block_fn;

	@Column(name = "shift_block_fn", nullable = false)
	public String getShift_block_fn() {
		return shift_block_fn;
	}

	public void setShift_block_fn(String shift_block_fn) {
		this.shift_block_fn = shift_block_fn;
	}

}
