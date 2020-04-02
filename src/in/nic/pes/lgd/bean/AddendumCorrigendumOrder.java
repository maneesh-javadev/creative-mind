/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author
 */
@Entity
@Table(name = "addendum_corrigendum_order")
public class AddendumCorrigendumOrder { // NO_UCD (use default)
    @Column(name = "ac_order_code")
    private Integer acOrderCode;
  //@NotNull
    @Column(name = "order_type")
    private char orderType;
  //@NotNull
    @Column(name = "order_no")
    private String orderNo;
  //@NotNull
    @Column(name = "order_date")
  //@Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
  //@NotNull
    @Column(name = "gaz_pub_date")
  //@Temporal(TemporalType.TIMESTAMP)
    private Date gazPubDate;
  //@NotNull
    @Column(name = "issued_by")
    private String issuedBy;
  //@NotNull
    @Column(name = "order_path")
    private String orderPath;
  //@NotNull
    @Column(name = "user_id")
    private long userId;
  //@Size(max = 100)
    @Column(name = "description")
    private String description;
  //@Size(max = 10)
    @Column(name = "level")
    private String level;
    @Column(name = "status")
    private Character status;
  //@NotNull
    @Column(name = "effective_date")
  //@Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
  //@NotNull
    @Column(name = "createdby")
    private String createdby;
  //@NotNull
    @Column(name = "createdon")
  //@Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @Column(name = "lastupdatedby")
    private String lastupdatedby;
    @Column(name = "lastupdated")
  //@Temporal(TemporalType.TIMESTAMP)
    private Date lastupdated;
  //@JoinColumn(name = "order_code", referencedColumnName = "order_code")
  //@ManyToOne(optional = false)
    private GovernmentOrder orderCode;

    public AddendumCorrigendumOrder() {
    }

// TODO Remove unused code found by UCDetector
//     public AddendumCorrigendumOrder(Integer acOrderCode) {
//         this.acOrderCode = acOrderCode;
//     }

// TODO Remove unused code found by UCDetector
//     public AddendumCorrigendumOrder(Integer acOrderCode, char orderType, String orderNo, Date orderDate, Date gazPubDate, String issuedBy, String orderPath, long userId, Date effectiveDate, String createdby, Date createdon) {
//         this.acOrderCode = acOrderCode;
//         this.orderType = orderType;
//         this.orderNo = orderNo;
//         this.orderDate = orderDate;
//         this.gazPubDate = gazPubDate;
//         this.issuedBy = issuedBy;
//         this.orderPath = orderPath;
//         this.userId = userId;
//         this.effectiveDate = effectiveDate;
//         this.createdby = createdby;
//         this.createdon = createdon;
//     }

    public Integer getAcOrderCode() {
        return acOrderCode;
    }

    public void setAcOrderCode(Integer acOrderCode) {
        this.acOrderCode = acOrderCode;
    }

    public char getOrderType() {
        return orderType;
    }

    public void setOrderType(char orderType) {
        this.orderType = orderType;
    }

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

    public Date getGazPubDate() {
        return gazPubDate;
    }

    public void setGazPubDate(Date gazPubDate) {
        this.gazPubDate = gazPubDate;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getOrderPath() {
        return orderPath;
    }

    public void setOrderPath(String orderPath) {
        this.orderPath = orderPath;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    public GovernmentOrder getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(GovernmentOrder orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acOrderCode != null ? acOrderCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AddendumCorrigendumOrder)) {
            return false;
        }
        AddendumCorrigendumOrder other = (AddendumCorrigendumOrder) object;
        if ((this.acOrderCode == null && other.acOrderCode != null) || (this.acOrderCode != null && !this.acOrderCode.equals(other.acOrderCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.AddendumCorrigendumOrder[ acOrderCode=" + acOrderCode + " ]";
    }
    
}
