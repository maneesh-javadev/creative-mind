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
@NamedNativeQuery(query=" SELECT * FROM invalidate_subdistrict_fn(:subdistrictCodes,:subdistrictCode,:roleCode,:villageCodes)",name="invalidateSubdistrictFn", resultClass = InvalidateSubdistrictBean.class)
public class InvalidateSubdistrictBean {
	
    
   
    	boolean invalidate_subdistrict_fn;

	/**
	 * @return the invalidate_subdistrict_fn
	 */
    @Id
    @Column(name = "invalidate_subdistrict_fn",nullable=false)
	public boolean isInvalidate_subdistrict_fn() {
		return invalidate_subdistrict_fn;
	}

	/**
	 * @param invalidate_subdistrict_fn the invalidate_subdistrict_fn to set
	 */
	public void setInvalidate_subdistrict_fn(boolean invalidate_subdistrict_fn) {
		this.invalidate_subdistrict_fn = invalidate_subdistrict_fn;
	}


}
