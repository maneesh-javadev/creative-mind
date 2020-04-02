package in.nic.pes.lgd.bean;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
@Entity
@Table(name = "government_order_entitywise_new")
public class GovernmentOrderEntityWiseNew {
	
	private Integer id ;
	private Integer orderCode ;
	private Integer  entityCode ;
	private Integer  entityVersion ;
	private Character  entityType ;
	private GovernmentOrder governmentOrder;
	private Integer minorVersion;
	public GovernmentOrderEntityWiseNew() {
		
	}
	
// TODO Remove unused code found by UCDetector
// 	public GovernmentOrderEntityWise(Integer id, Integer orderCode,
// 			Integer entityCode, Integer entityVersion, Character entityType) {
// 		super();
// 		this.id = id;
// 		this.orderCode = orderCode;
// 		this.entityCode = entityCode;
// 		this.entityVersion = entityVersion;
// 		this.entityType = entityType;
// 	}

// TODO Remove unused code found by UCDetector
// 	public GovernmentOrderEntityWise(Integer id, Integer orderCode,
// 			Integer entityCode, Integer entityVersion, Character entityType,
// 			GovernmentOrder governmentOrder) {
// 		super();
// 		this.id = id;
// 		this.orderCode = orderCode;
// 		this.entityCode = entityCode;
// 		this.entityVersion = entityVersion;
// 		this.entityType = entityType;
// 		this.governmentOrder = governmentOrder;
// 	}

	 
	
    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "public.governmentorderentitywise_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "order_code",insertable=false,updatable=false)
	public Integer getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}
	 @Column(name = "entity_code")
	public Integer getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}
	@Column(name = "entity_version")
	public Integer getEntityVersion() {
		return entityVersion;
	}
	
	public void setEntityVersion(Integer entityVersion) {
		this.entityVersion = entityVersion;
	}
	
	
	
	@Column(name = "minor_version")
	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	@Column(name = "entity_type")
	public Character getEntityType() {
		return entityType;
	}
	public void setEntityType(Character entityType) {
		this.entityType = entityType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
	@JoinColumn(name = "order_code")
	public GovernmentOrder getGovernmentOrder() {
		return governmentOrder;
	}
	public void setGovernmentOrder(GovernmentOrder governmentOrder) {
		this.governmentOrder = governmentOrder;
	}
	
}


