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
 * <p>Java class for ParentTypeSubdistrict.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ParentTypeSubdistrict">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="D"/>
 *     &lt;enumeration value="T"/>
 *     &lt;enumeration value="B"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ParentTypeSubdistrict")
@XmlEnum
public enum ParentTypeSubdistrict { // NO_UCD (use default)

    S,
    D,
    T,
    B; // NO_UCD (unused code)

    public String value() {
        return name();
    }

    public static ParentTypeSubdistrict fromValue(String v) { // NO_UCD (unused code)
        return valueOf(v);
    }

}