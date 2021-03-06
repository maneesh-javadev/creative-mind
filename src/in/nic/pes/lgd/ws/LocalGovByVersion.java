//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1.6-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.08 at 01:51:11 PM IST 
//


package in.nic.pes.lgd.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocalGovByVersion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocalGovByVersion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LocalGovId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LocalGovName" type="{http://www.ws.lgd.pes.nic.in}EntityName"/>
 *         &lt;element name="LocalGovTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LocalGovTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ParentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocalGovByVersion", propOrder = {
    "content"
})
public class LocalGovByVersion { // NO_UCD (use default)

    @XmlElementRefs({
        @XmlElementRef(name = "Version", namespace = "http://www.ws.lgd.pes.nic.in", type = JAXBElement.class),
        @XmlElementRef(name = "LocalGovId", namespace = "http://www.ws.lgd.pes.nic.in", type = JAXBElement.class),
        @XmlElementRef(name = "ParentId", namespace = "http://www.ws.lgd.pes.nic.in", type = JAXBElement.class),
        @XmlElementRef(name = "LocalGovName", namespace = "http://www.ws.lgd.pes.nic.in", type = JAXBElement.class),
        @XmlElementRef(name = "LocalGovTypeId", namespace = "http://www.ws.lgd.pes.nic.in", type = JAXBElement.class)
    })
    private List<JAXBElement<?>> content;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "LocalGovTypeId" is used by two different parts of a schema. See: 
     * line 578 of file:/D:/eclipse/workspace/LGD/resource/LGDNotifications.xsd
     * line 576 of file:/D:/eclipse/workspace/LGD/resource/LGDNotifications.xsd
     * <p>
     * To get rid of this property, apply a property customization to one 
     * of both of the following declarations to change their names: 
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link EntityName }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

}
