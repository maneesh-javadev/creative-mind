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
 *                 This Type will provide details of a Covered Area
 *             
 * 
 * <p>Java class for CoveredArea complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CoveredArea">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LandRegionTypes" type="{http://www.ws.lgd.pes.nic.in}LandRegionTypes"/>
 *         &lt;element name="LandRegionCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LandRegionName" type="{http://www.ws.lgd.pes.nic.in}EntityName"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoveredArea", propOrder = {
    "landRegionTypes",
    "landRegionCode",
    "landRegionName"
})
public class CoveredArea { // NO_UCD (use default)

    @XmlElement(name = "LandRegionTypes", required = true)
    private LandRegionTypes landRegionTypes;
    @XmlElement(name = "LandRegionCode")
    private int landRegionCode;
    @XmlElement(name = "LandRegionName", required = true)
    private EntityName landRegionName;

    /**
     * Gets the value of the landRegionTypes property.
     * 
     * @return
     *     possible object is
     *     {@link LandRegionTypes }
     *     
     */
    public LandRegionTypes getLandRegionTypes() {
        return landRegionTypes;
    }

    /**
     * Sets the value of the landRegionTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link LandRegionTypes }
     *     
     */
    public void setLandRegionTypes(LandRegionTypes value) {
        this.landRegionTypes = value;
    }

    /**
     * Gets the value of the landRegionCode property.
     * 
     */
    public int getLandRegionCode() {
        return landRegionCode;
    }

    /**
     * Sets the value of the landRegionCode property.
     * 
     */
    public void setLandRegionCode(int value) {
        this.landRegionCode = value;
    }

    /**
     * Gets the value of the landRegionName property.
     * 
     * @return
     *     possible object is
     *     {@link EntityName }
     *     
     */
    public EntityName getLandRegionName() {
        return landRegionName;
    }

    /**
     * Sets the value of the landRegionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityName }
     *     
     */
    public void setLandRegionName(EntityName value) {
        this.landRegionName = value;
    }

}
