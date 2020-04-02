package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

 
@Entity
@Table(name = "map_localbody", schema = "public")
public class MapLocalbody implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7600734748342542408L;
	private int mapLocalbodyCode;
    private Localbody localbody;
    private String coordinates;
    private String imagePath;
    private Date effectiveDate;
    private Date lastupdated;
    private Long lastupdatedby;
    private Date createdon;
    private long createdby;
    private boolean warningflag;

    public MapLocalbody() {
    }

// TODO Remove unused code found by UCDetector
//     public MapLocalbody(int mapLocalbodyCode, Localbody localbody,
//             Date effectiveDate, Date createdon, long createdby,
//             boolean warningflag) {
//         this.mapLocalbodyCode = mapLocalbodyCode;
//         this.localbody = localbody;
//         this.effectiveDate = effectiveDate;
//         this.createdon = createdon;
//         this.createdby = createdby;
//         this.warningflag = warningflag;
//     }

// TODO Remove unused code found by UCDetector
//     public MapLocalbody(int mapLocalbodyCode, Localbody localbody,
//             String coordinates, String imagePath, Date effectiveDate,
//             Date lastupdated, Long lastupdatedby, Date createdon,
//             long createdby, boolean warningflag) {
//         this.mapLocalbodyCode = mapLocalbodyCode;
//         this.localbody = localbody;
//         this.coordinates = coordinates;
//         this.imagePath = imagePath;
//         this.effectiveDate = effectiveDate;
//         this.lastupdated = lastupdated;
//         this.lastupdatedby = lastupdatedby;
//         this.createdon = createdon;
//         this.createdby = createdby;
//         this.warningflag = warningflag;
//     }

   /* @GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="seq_maplocalbody")}) */
    @GenericGenerator(name = "generator", strategy = "increment", parameters = {})
	@Id
	@GeneratedValue(generator = "generator")
    @Column(name = "map_localbody_code", unique = true, nullable = false)
    public int getMapLocalbodyCode() {
        return this.mapLocalbodyCode;
    }

    public void setMapLocalbodyCode(int mapLocalbodyCode) {
        this.mapLocalbodyCode = mapLocalbodyCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "localbody_code", referencedColumnName = "local_body_code", nullable = false),
            @JoinColumn(name = "localbody_version", referencedColumnName = "local_body_version", nullable = false) })
    public Localbody getLocalbody() {
        return this.localbody;
    }

    public void setLocalbody(Localbody localbody) {
        this.localbody = localbody;
    }

    @Column(name = "coordinates")
    public String getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Column(name = "image_path", length = 500)
    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "effective_date", nullable = false, length = 35)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastupdated", length = 35)
    public Date getLastupdated() {
        return this.lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    @Column(name = "lastupdatedby")
    public Long getLastupdatedby() {
        return this.lastupdatedby;
    }

    public void setLastupdatedby(Long lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdon", nullable = false, length = 35)
    public Date getCreatedon() {
        return this.createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    @Column(name = "createdby", nullable = false)
    public long getCreatedby() {
        return this.createdby;
    }

    public void setCreatedby(long createdby) {
        this.createdby = createdby;
    }

    @Column(name = "warningflag", nullable = false)
    public boolean isWarningflag() {
        return this.warningflag;
    }

    public void setWarningflag(boolean warningflag) {
        this.warningflag = warningflag;
    }

}
