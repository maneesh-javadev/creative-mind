package in.nic.pes.common.beans;




import in.nic.pes.common.menu.pojo.Form;

import java.util.List;

public class ApplicationRole { // NO_UCD (unused code)
	private String roleName;
	private int roleId;
	private List<Form> forms;
	
	public String getRoleName() {
		
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public List<Form> getForms() {
		return forms;
	}
	public void setForms(List<Form> forms) {
		this.forms = forms;
	}
	
}
