package in.nic.pes.lgd.dao;

import in.nic.pes.common.menu.pojo.MenuProfile;

/***
 * @author Maneesh Kumar
 * @Since 20-May-2014
 * @Desciption  This Dao is add method for "Single Sing On" 
 */

import in.nic.pes.lgd.bean.PesApplication;


import java.util.List;

public interface UtilDAO {
	public List<PesApplication> getAllApplication(Integer userId)  throws Exception; 
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	public List<MenuProfile> getMenuProfilebyId(String menuIds);
	/**
	 * end External User
	 */
}
