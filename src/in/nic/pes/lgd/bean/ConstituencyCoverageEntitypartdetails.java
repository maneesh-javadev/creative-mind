/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author
 */
@Entity
@Table(name = "constituency_covered_entity_part_details")
public class ConstituencyCoverageEntitypartdetails {
    //@NotNull
    @Id
    @Column(name = "ccepd_code")
    private Integer  id;
  //@NotNull
    @Column(name = "cce_code")
    private int cccode;
  //@NotNull
    @Column(name = "entity_code")
    private int entitycode;
  //@NotNull
    @Column(name = "entity_version")
    private int entityversion;
  /*//@NotNull
    @Column(name = "constituency_id")
    private int constituencyId;*/
/*  //@NotNull
    @Column(name = "coverage_body_code")
    private int coverageBodyCode;*/
  //@NotNull
    @Column(name = "entity_type")
  //@Temporal(TemporalType.TIMESTAMP)
    private char entitytype;
  //@NotNull
    @Column(name = "coverage_type")
    private char coveragetype;
  //@NotNull
    
    @Column(name = "isactive", nullable = false)
	private boolean isactive;

  //@OneToMany(cascade = CascadeType.ALL, mappedBy = "ccCode")
/*    private Collection<CoveredConstituencyBody> coveredConstituencyBodyCollection;
*/
    public ConstituencyCoverageEntitypartdetails() {
    }

// TODO Remove unused code found by UCDetector
//     public ConstituencyCoverageEntitypartdetails(Integer ccCode) {
//         this.id = id;
//     }

// TODO Remove unused code found by UCDetector
//     public ConstituencyCoverageEntitypartdetails(Integer id, int cccode, int entityversion, char entitytype) {
//         this.id = id;
//         this.cccode = cccode;
//         this.entityversion = entityversion;
//         this.entitytype = entitytype;
// 
//        /* this.constituencyId = constituencyId;
//      */
//        /* this.coverageBodyCode = coverageBodyCode;*/
//        
//     }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCccode() {
		return cccode;
	}

	public void setCccode(int cccode) {
		this.cccode = cccode;
	}

	public int getEntitycode() {
		return entitycode;
	}

	public void setEntitycode(int entitycode) {
		this.entitycode = entitycode;
	}

	public int getEntityversion() {
		return entityversion;
	}

	public void setEntityversion(int entityversion) {
		this.entityversion = entityversion;
	}

	public char getEntitytype() {
		return entitytype;
	}

	public void setEntitytype(char entitytype) {
		this.entitytype = entitytype;
	}

	public char getCoveragetype() {
		return coveragetype;
	}

	public void setCoveragetype(char coveragetype) {
		this.coveragetype = coveragetype;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

// TODO Remove unused code found by UCDetector
// 	public static long getSerialversionuid() {
// 		return serialVersionUID;
// 	}
    }

	