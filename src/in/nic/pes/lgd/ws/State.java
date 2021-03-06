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
 *                 This Type will provide Entire Heirarchy of a particular STATE
 *             
 * 
 * <p>Java class for State complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="State">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StateDetails" type="{http://www.ws.lgd.pes.nic.in}LandDetails"/>
 *         &lt;element name="Districts" type="{http://www.ws.lgd.pes.nic.in}Districts" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "State", propOrder = {
    "stateDetails",
    "districts"
})
public class State { // NO_UCD (use default)

    @XmlElement(name = "StateDetails", required = true)
    private LandDetails stateDetails;
    @XmlElement(name = "Districts")
    private Districts districts;

    /**
     * Gets the value of the stateDetails property.
     * 
     * @return
     *     possible object is
     *     {@link LandDetails }
     *     
     */
    public LandDetails getStateDetails() {
        return stateDetails;
    }

    /**
     * Sets the value of the stateDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link LandDetails }
     *     
     */
    public void setStateDetails(LandDetails value) {
        this.stateDetails = value;
    }

    /**
     * Gets the value of the districts property.
     * 
     * @return
     *     possible object is
     *     {@link Districts }
     *     
     */
    public Districts getDistricts() {
        return districts;
    }

    /**
     * Sets the value of the districts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Districts }
     *     
     */
    public void setDistricts(Districts value) {
        this.districts = value;
    }

}
