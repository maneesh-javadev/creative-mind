package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select * from get_govt_order_details_fn(:entityType,:entityCode,:entityVersion)", name ="GovOrderDetail",resultClass=GetGovernmentOrderDetail.class),
@NamedNativeQuery(query = "select * from get_govt_order_details_fn(:entityType,:entityCode,:entityVersion,:minorVersion)", name ="GovOrderDetailMinor",resultClass=GetGovernmentOrderDetail.class)
})
public class GetGovernmentOrderDetail
{
			@Id
	     
		    @Column(name = "order_no")
		    private String orderNo;
			@Column(name ="order_code")
			private int orderCode;

		    @Column(name = "order_date")
		
		    private Date orderDate;

		    @Column(name = "effective_date")
		 
		    private Date effectiveDate;
		    @Basic(optional = false)
		 
		    @Column(name = "gaz_pub_date")
		    
		    private Date gazPubDate;
		  
		
		  
		    @Column(name = "order_path")
		    private String orderPath; 
		    
		  
			public String getOrderNo() {
				return orderNo;
			}
			public void setOrderNo(String orderNo) {
				this.orderNo = orderNo;
			}
			public Date getOrderDate() {
				return orderDate;
			}
			public void setOrderDate(Date orderDate) {
				this.orderDate = orderDate;
			}
			public Date getEffectiveDate() {
				return effectiveDate;
			}
			public void setEffectiveDate(Date effectiveDate) {
				this.effectiveDate = effectiveDate;
			}
			public Date getGazPubDate() {
				return gazPubDate;
			}
			public void setGazPubDate(Date gazPubDate) {
				this.gazPubDate = gazPubDate;
			}
			
			public String getOrderPath() {
				return orderPath;
			}
			public void setOrderPath(String orderPath) {
				this.orderPath = orderPath;
			}
			public int getOrderCode() {
				return orderCode;
			}
			
			public void setOrderCode(int orderCode) {
				this.orderCode = orderCode;
			}
			
	}

