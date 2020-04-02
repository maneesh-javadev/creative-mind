package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * GovernmentOrder generated
 */
@Entity
@Table(name = "government_order", schema = "public")
//@NamedNativeQuery(query = "select * from governmentOrder go  inner join governmentOrderEntitywises gowe   on gowe.orderCode = go.orderCode where  gowe.entityCode  =:localBoadyCode " , name = "GET_ORDER_CODE_THROUGH_LB_CODE", resultClass = GovernmentOrder.class)

public class GovernmentOrder implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4532429935979861274L;
	private int orderCode;
	private GovernmentOrderTemplate governmentOrderTemplate;
	private String orderNo;
	private Date orderDate;
	private Date effectiveDate;
	private Date gazPubDate;
	private String issuedBy;
	private String orderPath;
	private String xmlOrderPath;
	private String xmlDbPath;
	private long userId;
	private String description;
	private String level;
	private Character status;
	private long createdby;
	private Date createdon;
	private Date lastupdated;
	private Long lastupdatedby;
	/*
	 * private Set<GovernmentOrderStatus> governmentOrderStatuses = new
	 * HashSet<GovernmentOrderStatus>( 0);
	 */
	private Set<GovernmentOrderEntityWiseNew> governmentOrderEntitywises = new HashSet<GovernmentOrderEntityWiseNew>(
			0);
	private Set<Attachment> attachments = new HashSet<Attachment>(0);

	/*
	 * private Set<AddendumCorrigendumOrder> addendumCorrigendumOrders = new
	 * HashSet<AddendumCorrigendumOrder>( 0);
	 */
	
	private String govtOrderConfig;
	private List<CommonsMultipartFile> attachFile1;
	private String templateList;
	
	public GovernmentOrder() {
	}
	
// TODO Remove unused code found by UCDetector
// 	public GovernmentOrder(int orderCode) {
// 		this.orderCode = orderCode;
// 	}
	
// TODO Remove unused code found by UCDetector
// 	public GovernmentOrder(int orderCode, String orderNo, Date orderDate,
// 			Date effectiveDate, Date gazPubDate, String issuedBy, long userId,
// 			long createdby, Date createdon) {
// 		this.orderCode = orderCode;
// 		this.orderNo = orderNo;
// 		this.orderDate = orderDate;
// 		this.effectiveDate = effectiveDate;
// 		this.gazPubDate = gazPubDate;
// 		this.issuedBy = issuedBy;
// 		this.userId = userId;
// 		this.createdby = createdby;
// 		this.createdon = createdon;
// 	}

// TODO Remove unused code found by UCDetector
// 	public GovernmentOrder(int orderCode,
// 			GovernmentOrderTemplate governmentOrderTemplate, String orderNo,
// 			Date orderDate, Date effectiveDate, Date gazPubDate,
// 			String issuedBy, String orderPath, String xmlOrderPath,
// 			String xmlDbPath, long userId, String description, String level,
// 			Character status, long createdby, Date createdon, Date lastupdated,
// 			Long lastupdatedby,
// 			Set<GovernmentOrderStatus> governmentOrderStatuses,
// 			// Set<GovernmentOrderEntitywise> governmentOrderEntitywises,
// 			Set<Attachment> attachments,
// 			Set<AddendumCorrigendumOrder> addendumCorrigendumOrders) {
// 		this.orderCode = orderCode;
// 		this.governmentOrderTemplate = governmentOrderTemplate;
// 		this.orderNo = orderNo;
// 		this.orderDate = orderDate;
// 		this.effectiveDate = effectiveDate;
// 		this.gazPubDate = gazPubDate;
// 		this.issuedBy = issuedBy;
// 		this.orderPath = orderPath;
// 		this.xmlOrderPath = xmlOrderPath;
// 		this.xmlDbPath = xmlDbPath;
// 		this.userId = userId;
// 		this.description = description;
// 		this.level = level;
// 		this.status = status;
// 		this.createdby = createdby;
// 		this.createdon = createdon;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		// this.governmentOrderStatuses = governmentOrderStatuses;
// 		// this.governmentOrderEntitywises = governmentOrderEntitywises;
// 		this.attachments = attachments;
// 		// this.addendumCorrigendumOrders = addendumCorrigendumOrders;
// 	}
	
	
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "public.governmentorder_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
    @Basic(optional = false)
	@Column(name = "order_code", unique = true, nullable = false)
	public int getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_code")
	public GovernmentOrderTemplate getGovernmentOrderTemplate() {
		return this.governmentOrderTemplate;
	}

	public void setGovernmentOrderTemplate(
			GovernmentOrderTemplate governmentOrderTemplate) {
		this.governmentOrderTemplate = governmentOrderTemplate;
	}

	@Column(name = "order_no", nullable = false, length = 20)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_date", nullable = false, length = 35)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effective_date", nullable = false, length = 35)
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gaz_pub_date", nullable = true, length = 35)
	public Date getGazPubDate() {
		return this.gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

	@Column(name = "issued_by", nullable = false, length = 15)
	public String getIssuedBy() {
		return this.issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	@Column(name = "order_path")
	public String getOrderPath() {
		return this.orderPath;
	}

	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}

	@Column(name = "xml_order_path")
	public String getXmlOrderPath() {
		return this.xmlOrderPath;
	}

	public void setXmlOrderPath(String xmlOrderPath) {
		this.xmlOrderPath = xmlOrderPath;
	}

	@Column(name = "xml_db_path")
	public String getXmlDbPath() {
		return this.xmlDbPath;
	}

	public void setXmlDbPath(String xmlDbPath) {
		this.xmlDbPath = xmlDbPath;
	}

	@Column(name = "user_id", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "level", length = 10)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "status", length = 1)
	public Character getStatus() {
		return this.status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	@Column(name = "createdby", nullable = false)
	public long getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdon", nullable = false, length = 35)
	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastupdated", length = 35)
	public Date getLastupdated() {
		return this.lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	@Column(name = "lastupdatedby")
	public Long getLastupdatedby() {
		return this.lastupdatedby;
	}

	public void setLastupdatedby(Long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "governmentOrder") public
	 * Set<GovernmentOrderStatus> getGovernmentOrderStatuses() { return
	 * this.governmentOrderStatuses; }
	 * 
	 * public void setGovernmentOrderStatuses( Set<GovernmentOrderStatus>
	 * governmentOrderStatuses) { this.governmentOrderStatuses =
	 * governmentOrderStatuses; }
	 */

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "governmentOrder")
	public Set<GovernmentOrderEntityWiseNew> getGovernmentOrderEntitywises() {
		return this.governmentOrderEntitywises;
	}

	public void setGovernmentOrderEntitywises(
			Set<GovernmentOrderEntityWiseNew> governmentOrderEntitywises) {
		this.governmentOrderEntitywises = governmentOrderEntitywises;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "governmentOrder", cascade = {CascadeType.ALL})
	public Set<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Transient
	public String getGovtOrderConfig() {
		return govtOrderConfig;
	}

	public void setGovtOrderConfig(String govtOrderConfig) {
		this.govtOrderConfig = govtOrderConfig;
	}

	@Transient
	public List<CommonsMultipartFile> getAttachFile1() {
		return attachFile1;
	}

	public void setAttachFile1(List<CommonsMultipartFile> attachFile1) {
		this.attachFile1 = attachFile1;
	}

	@Transient
	public String getTemplateList() {
		return templateList;
	}

	public void setTemplateList(String templateList) {
		this.templateList = templateList;
	}

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "governmentOrder") public
	 * Set<AddendumCorrigendumOrder> getAddendumCorrigendumOrders() { return
	 * this.addendumCorrigendumOrders; }
	 * 
	 * public void setAddendumCorrigendumOrders( Set<AddendumCorrigendumOrder>
	 * addendumCorrigendumOrders) { this.addendumCorrigendumOrders =
	 * addendumCorrigendumOrders; }
	 */

}
