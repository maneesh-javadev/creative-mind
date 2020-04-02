package in.nic.pes.lgd.dao;

import in.nic.pes.common.menu.pojo.Form;
import in.nic.pes.common.menu.pojo.MenuProfile;

import java.util.List;
import java.util.Set;


public interface MenuDAO {

	abstract List<MenuProfile> getMenuList(Set<Form> formList)throws Exception;

}
