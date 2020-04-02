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
@NamedNativeQuery(query=" SELECT * FROM invalidate_district_fn(:districtCodes,:districtCode,:roleCode,:subDistrictCodes)",name="invalidateDistrictFn", resultClass = InvalidateDistrictBean.class)
public class InvalidateDistrictBean {
	
    
   
    	boolean invalidate_district_fn;

		/**
		 * @return the invalidate_district_fn
		 */
        @Id
        @Column(name = "invalidate_district_fn",nullable=false)
		public boolean isInvalidate_district_fn() {
			return invalidate_district_fn;
		}

		/**
		 * @param invalidate_district_fn the invalidate_district_fn to set
		 */
		public void setInvalidate_district_fn(boolean invalidate_district_fn) {
			this.invalidate_district_fn = invalidate_district_fn;
		}





}
