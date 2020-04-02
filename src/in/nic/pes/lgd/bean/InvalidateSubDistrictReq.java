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
@NamedNativeQuery(query=" SELECT * FROM invalidate_subdistrict_fn(:sourceSubdist,:userId,:keyAppend,:effectiveDate,:orderNo,:orderDate,:gazPubDate,:orderPath)",name="invalidateSubdistrictFnCall", resultClass = InvalidateSubDistrictReq.class)
public class InvalidateSubDistrictReq {
	
    
   
    	private String invalidate_subdistrict_fn;
        
        @Id
        @Column(name = "invalidate_subdistrict_fn",nullable=false)
		public String getInvalidate_subdistrict_fn() {
			return invalidate_subdistrict_fn;
		}

		public void setInvalidate_subdistrict_fn(String invalidate_subdistrict_fn) {
			this.invalidate_subdistrict_fn = invalidate_subdistrict_fn;
		}

	


}
