/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author
 */
@Embeddable
public class MapPK { // NO_UCD (use default)
  //@NotNull
    @Column(name = "map_code")
    private int mapCode;
  //@NotNull
    @Column(name = "map_version")
    private int mapVersion;

    public MapPK() {
    }

    public MapPK(int mapCode, int mapVersion) { // NO_UCD (use default)
        this.mapCode = mapCode;
        this.mapVersion = mapVersion;
    }

    public int getMapCode() {
        return mapCode;
    }

    public void setMapCode(int mapCode) {
        this.mapCode = mapCode;
    }

    public int getMapVersion() {
        return mapVersion;
    }

    public void setMapVersion(int mapVersion) {
        this.mapVersion = mapVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) mapCode;
        hash += (int) mapVersion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapPK)) {
            return false;
        }
        MapPK other = (MapPK) object;
        if (this.mapCode != other.mapCode) {
            return false;
        }
        if (this.mapVersion != other.mapVersion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.MapPK[ mapCode=" + mapCode + ", mapVersion=" + mapVersion + " ]";
    }
    
}
