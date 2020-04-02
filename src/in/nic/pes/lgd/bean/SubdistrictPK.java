package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class SubdistrictPK implements java.io.Serializable {

	private int subdistrictCode;
	private int subdistrictVersion;
	private Integer minorVersion;
		
	public SubdistrictPK() {
	}

	public SubdistrictPK(int subdistrictCode, int subdistrictVersion,int minorVersion) {
		this.subdistrictCode = subdistrictCode;
		this.subdistrictVersion = subdistrictVersion;
		this.minorVersion = minorVersion;
	}

	@Column(name = "subdistrict_code", nullable = false)
	public int getSubdistrictCode() {
		return this.subdistrictCode;
	}

	public void setSubdistrictCode(int subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	@Column(name = "subdistrict_version", nullable = false)
	public int getSubdistrictVersion() {
		return this.subdistrictVersion;
	}

	public void setSubdistrictVersion(int subdistrictVersion) {
		this.subdistrictVersion = subdistrictVersion;
	}
	
	@Column(name="minor_version")
	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	public boolean equals(Object other) {
		if ((this == other)){
			return true;
		}	
		if ((other == null)){
			return false;
		}	
		if (!(other instanceof SubdistrictPK)){
			return false;
		}	
		SubdistrictPK castOther = (SubdistrictPK) other;

		return (this.getSubdistrictCode() == castOther.getSubdistrictCode())
				&& (this.getSubdistrictVersion() == castOther
						.getSubdistrictVersion());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getSubdistrictCode();
		result = 37 * result + this.getSubdistrictVersion();
		result = 37 * result + this.getMinorVersion();
		return result;
	}

	

}