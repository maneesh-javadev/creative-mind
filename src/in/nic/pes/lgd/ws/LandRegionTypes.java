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
 * <p>Java class for LandRegionTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LandRegionTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="STATE"/>
 *     &lt;enumeration value="UT"/>
 *     &lt;enumeration value="DISTRICT"/>
 *     &lt;enumeration value="SUBDISTRICT"/>
 *     &lt;enumeration value="BLOCK"/>
 *     &lt;enumeration value="VILLAGE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LandRegionTypes")
@XmlEnum
public enum LandRegionTypes { // NO_UCD (use default)

    STATE, // NO_UCD (unused code)
    UT, // NO_UCD (unused code)
    DISTRICT, // NO_UCD (unused code)
    SUBDISTRICT, // NO_UCD (unused code)
    BLOCK, // NO_UCD (unused code)
    VILLAGE; // NO_UCD (unused code)

    public String value() {
        return name();
    }

    public static LandRegionTypes fromValue(String v) {
        return valueOf(v);
    }

}
