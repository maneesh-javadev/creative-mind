package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
/**
*
* @author Sandeep
*/
@Entity
@NamedNativeQuery(query="SELECT * FROM invalidate_ward_fn(:wardCode, :userid)",name="invalidateWardFn",resultClass=InvalidateWardBean.class)
public class InvalidateWardBean {
	
    	boolean invalidate_ward_fn;

    	@Id
    	@Column(name = "invalidate_ward_fn",nullable=false)
		public boolean isInvalidate_ward_fn() {
			return invalidate_ward_fn;
		}

		public void setInvalidate_ward_fn(boolean invalidate_ward_fn) {
			this.invalidate_ward_fn = invalidate_ward_fn;
		}

}
