//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1.6-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.08 at 01:51:11 PM IST 
//


package in.nic.pes.lgd.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 LGDNotification is the Root Element for all Notifications within LGD for interaction
 *                 with other modules within the sub-system
 *             
 * 
 * <p>Java class for LGDNotification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LGDNotification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NotificationType" type="{http://www.ws.lgd.pes.nic.in}NotificationTypes" minOccurs="0"/>
 *         &lt;element name="LandRegions" type="{http://www.ws.lgd.pes.nic.in}LandRegions" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element name="NewLandRegions" type="{http://www.ws.lgd.pes.nic.in}EntireLandRegion" minOccurs="0"/>
 *           &lt;element name="ModifiedLandRegions" type="{http://www.ws.lgd.pes.nic.in}EntireLandRegion" minOccurs="0"/>
 *           &lt;element name="OldLandRegions" type="{http://www.ws.lgd.pes.nic.in}EntireLandRegion" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LGDNotification", propOrder = {
    "notificationType",
    "landRegions",
    "newLandRegions",
    "modifiedLandRegions",
    "oldLandRegions"
})
public class LGDNotification { // NO_UCD (use default)

    @XmlElement(name = "NotificationType")
    private NotificationTypes notificationType;
    @XmlElement(name = "LandRegions")
    private LandRegions landRegions;
    @XmlElement(name = "NewLandRegions")
    private EntireLandRegion newLandRegions;
    @XmlElement(name = "ModifiedLandRegions")
    private EntireLandRegion modifiedLandRegions;
    @XmlElement(name = "OldLandRegions")
    private EntireLandRegion oldLandRegions;

    /**
     * Gets the value of the notificationType property.
     * 
     * @return
     *     possible object is
     *     {@link NotificationTypes }
     *     
     */
    public NotificationTypes getNotificationType() {
        return notificationType;
    }

    /**
     * Sets the value of the notificationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotificationTypes }
     *     
     */
    public void setNotificationType(NotificationTypes value) {
        this.notificationType = value;
    }

    /**
     * Gets the value of the landRegions property.
     * 
     * @return
     *     possible object is
     *     {@link LandRegions }
     *     
     */
    public LandRegions getLandRegions() {
        return landRegions;
    }

    /**
     * Sets the value of the landRegions property.
     * 
     * @param value
     *     allowed object is
     *     {@link LandRegions }
     *     
     */
    public void setLandRegions(LandRegions value) {
        this.landRegions = value;
    }

    /**
     * Gets the value of the newLandRegions property.
     * 
     * @return
     *     possible object is
     *     {@link EntireLandRegion }
     *     
     */
    public EntireLandRegion getNewLandRegions() {
        return newLandRegions;
    }

    /**
     * Sets the value of the newLandRegions property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntireLandRegion }
     *     
     */
    public void setNewLandRegions(EntireLandRegion value) {
        this.newLandRegions = value;
    }

    /**
     * Gets the value of the modifiedLandRegions property.
     * 
     * @return
     *     possible object is
     *     {@link EntireLandRegion }
     *     
     */
    public EntireLandRegion getModifiedLandRegions() {
        return modifiedLandRegions;
    }

    /**
     * Sets the value of the modifiedLandRegions property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntireLandRegion }
     *     
     */
    public void setModifiedLandRegions(EntireLandRegion value) {
        this.modifiedLandRegions = value;
    }

    /**
     * Gets the value of the oldLandRegions property.
     * 
     * @return
     *     possible object is
     *     {@link EntireLandRegion }
     *     
     */
    public EntireLandRegion getOldLandRegions() {
        return oldLandRegions;
    }

    /**
     * Sets the value of the oldLandRegions property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntireLandRegion }
     *     
     */
    public void setOldLandRegions(EntireLandRegion value) {
        this.oldLandRegions = value;
    }

}