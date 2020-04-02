package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
/**
*
* @author chandan
*/
@Entity
@NamedNativeQuery(query=" SELECT * FROM invalidate_village_fn(:villageCode,:villages,:roleCode)",name="invalidateVillageFn", resultClass = InvalidateVillageBean.class)
public class InvalidateVillageBean {
	
    @Id
    	boolean invalidate_village_fn;

	/**
	 * @return the invalidate_village_fn
	 */
    @Column(name = "invalidate_village_fn",nullable=false)
	public boolean isInvalidate_village_fn() {
		return invalidate_village_fn;
	}

	/**
	 * @param invalidate_village_fn the invalidate_village_fn to set
	 */
	public void setInvalidate_village_fn(boolean invalidate_village_fn) {
		this.invalidate_village_fn = invalidate_village_fn;
	}
	/**
	 * @return the invalidateResult
	 */

}
