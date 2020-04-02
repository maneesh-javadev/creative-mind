package in.nic.pes.lgd.menu;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu_disable_table")
public class MenuDisableTable implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 882003482764923092L;
	@Id
	@Column(name="menu_id")
	private Integer menuId;
	@Column(name="entity_type")
	private String entityType;
	@Column(name="state_code")
	private Integer stateCode;
	@Column(name="lb_lr_type")
	private String lblrType;
	
	
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	public String getLblrType() {
		return lblrType;
	}
	public void setLblrType(String lblrType) {
		this.lblrType = lblrType;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuDisableTable other = (MenuDisableTable) obj;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		return true;
	}
	

}
