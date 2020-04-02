package in.nic.pes.lgd.menu;


import in.nic.pes.common.menu.pojo.Form;
import in.nic.pes.common.menu.pojo.MenuProfile;

import java.util.List;
import java.util.Set;

public interface LgdMenuAdapter {

	List<MenuProfile> getParentInformation(Set<Form> menu);
}
