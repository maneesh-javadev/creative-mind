//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1.6-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.30 at 05:27:49 PM IST 
//


package in.nic.pes.lgd.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvalidateSubdistrictForm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvalidateSubdistrictForm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SourceSubdistrict" type="{http://www.ws.lgd.pes.nic.in}District"/>
 *         &lt;element name="DestinationSubdistricts" type="{http://www.ws.lgd.pes.nic.in}Subdistricts"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvalidateSubdistrictForm", propOrder = {
    "sourceSubdistrict",
    "destinationSubdistricts"
})
public class InvalidateSubdistrictForm {

    @XmlElement(name = "SourceSubdistrict", required = true)
    private District sourceSubdistrict;
    @XmlElement(name = "DestinationSubdistricts", required = true)
    private Subdistricts destinationSubdistricts;

    /**
     * Gets the value of the sourceSubdistrict property.
     * 
     * @return
     *     possible object is
     *     {@link District }
     *     
     */
    public District getSourceSubdistrict() {
        return sourceSubdistrict;
    }

    /**
     * Sets the value of the sourceSubdistrict property.
     * 
     * @param value
     *     allowed object is
     *     {@link District }
     *     
     */
    public void setSourceSubdistrict(District value) {
        this.sourceSubdistrict = value;
    }

    /**
     * Gets the value of the destinationSubdistricts property.
     * 
     * @return
     *     possible object is
     *     {@link Subdistricts }
     *     
     */
    public Subdistricts getDestinationSubdistricts() {
        return destinationSubdistricts;
    }

    /**
     * Sets the value of the destinationSubdistricts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subdistricts }
     *     
     */
    public void setDestinationSubdistricts(Subdistricts value) {
        this.destinationSubdistricts = value;
    }

}
