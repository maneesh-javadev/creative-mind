//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1.6-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.08 at 01:51:11 PM IST 
//


package in.nic.pes.lgd.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Category.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Category">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="U"/>
 *     &lt;enumeration value="R"/>
 *     &lt;enumeration value="T"/>
 *     &lt;enumeration value="A"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Category")
@XmlEnum
public enum Category { // NO_UCD (use default)

    U, // NO_UCD (unused code)
    R, // NO_UCD (unused code)
    T, // NO_UCD (unused code)
    A; // NO_UCD (unused code)

    public String value() {
        return name();
    }

    public static Category fromValue(String v) {
        return valueOf(v);
    }

}