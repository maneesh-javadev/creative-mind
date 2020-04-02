package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="localbody_habitation", schema = "public")


public class LocalbodyHabitation {

	
	
	@Id
	@SequenceGenerator(name = "localbody_habitation_generator", initialValue=1, allocationSize=1,  sequenceName = "localbody_habitation_localbody_habitation_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "localbody_habitation_generator")
	@Column(name="localbody_habitation_id")
	private Integer localbodyHabitationId;
	
	@Column(name="habitation_code",nullable = false)
	private Integer habitationCode;
	
	@Column(name="habitation_version")
	private Integer habitationVersion;
	
	@Column(name="lbclr_code")
	private Integer lbclrCode;
	
	@Column(name="isactive")
	private boolean isactive;

	public Integer getLocalbodyHabitationId() {
		return localbodyHabitationId;
	}

	public void setLocalbodyHabitationId(Integer localbodyHabitationId) {
		this.localbodyHabitationId = localbodyHabitationId;
	}

	public Integer getHabitationCode() {
		return habitationCode;
	}

	public void setHabitationCode(Integer habitationCode) {
		this.habitationCode = habitationCode;
	}

	public Integer getHabitationVersion() {
		return habitationVersion;
	}

	public void setHabitationVersion(Integer habitationVersion) {
		this.habitationVersion = habitationVersion;
	}

	public Integer getLbclrCode() {
		return lbclrCode;
	}

	public void setLbclrCode(Integer lbclrCode) {
		this.lbclrCode = lbclrCode;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	
	
}
