/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author
 */
@Entity
@Table(name = "government_order_status")
public class GovernmentOrderStatus { // NO_UCD (use default)
  //private static final long serialVersionUID = 1L;
  //@NotNull
    @Column(name = "gos_id")
    private Integer gosId;
  //@NotNull
    @Column(name = "order_sent")
    private char orderSent;
  //@NotNull
    @Column(name = "order_acknowledged")
    private char orderAcknowledged;
    @Column(name = "ispes")
    private Boolean ispes;
    @Column(name = "xmlsent")
    private Character xmlsent;
  //@JoinColumn(name = "system_code", referencedColumnName = "system_code")
  //@ManyToOne(optional = false)
    private PesSystem systemCode;
  //@JoinColumn(name = "order_code", referencedColumnName = "order_code")
  //@ManyToOne(optional = false)
    private GovernmentOrder orderCode;

    public GovernmentOrderStatus() {
    }

// TODO Remove unused code found by UCDetector
//     public GovernmentOrderStatus(Integer gosId) {
//         this.gosId = gosId;
//     }

// TODO Remove unused code found by UCDetector
//     public GovernmentOrderStatus(Integer gosId, char orderSent, char orderAcknowledged) {
//         this.gosId = gosId;
//         this.orderSent = orderSent;
//         this.orderAcknowledged = orderAcknowledged;
//     }

    public Integer getGosId() {
        return gosId;
    }

    public void setGosId(Integer gosId) {
        this.gosId = gosId;
    }

    public char getOrderSent() {
        return orderSent;
    }

    public void setOrderSent(char orderSent) {
        this.orderSent = orderSent;
    }

    public char getOrderAcknowledged() {
        return orderAcknowledged;
    }

    public void setOrderAcknowledged(char orderAcknowledged) {
        this.orderAcknowledged = orderAcknowledged;
    }

    public Boolean getIspes() {
        return ispes;
    }

    public void setIspes(Boolean ispes) {
        this.ispes = ispes;
    }

    public Character getXmlsent() {
        return xmlsent;
    }

    public void setXmlsent(Character xmlsent) {
        this.xmlsent = xmlsent;
    }

    public PesSystem getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(PesSystem systemCode) {
        this.systemCode = systemCode;
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
        hash += (gosId != null ? gosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GovernmentOrderStatus)) {
            return false;
        }
        GovernmentOrderStatus other = (GovernmentOrderStatus) object;
        if ((this.gosId == null && other.gosId != null) || (this.gosId != null && !this.gosId.equals(other.gosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.GovernmentOrderStatus[ gosId=" + gosId + " ]";
    }
    
}
