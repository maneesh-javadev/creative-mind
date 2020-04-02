/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.common.menu.pojo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import in.nic.pes.lgd.bean.GetEntityEffectiveDate;



/**
 *
 * @author Admin
 */
@Entity
@Table(name = "menu_profile") 
//@Table(name = "menu_profile", catalog = "pes", schema = "lgd")
@XmlRootElement

@NamedNativeQueries({ 
	@NamedNativeQuery(query="select distinct mp2.* from menu_profile mp2 " +
			"left outer join menu_profile mp1 on (mp2.menu_id=mp1.parent)" +
			"left outer join menu_profile mp on (mp1.menu_id=mp.parent) " +
			"where  (mp2.form_name in (:parameter1)" +
			"or  mp1.form_name in (:parameter2) " +
			"or mp.form_name in (:parameter3) )" +
			"order by menu_id;",name="getMenu",resultClass=MenuProfile.class),
	
	 /**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	@NamedNativeQuery(query="select * from lgd.get_menu_profile_by_id(:menuIds);",name="getMenuProfilebyId",resultClass=MenuProfile.class)
	/**
	 * end External User
	 */

})

/*@NamedNativeQuery(query="select distinct mp2.* from lgd.menu_profile mp2 " +
		"left outer join lgd.menu_profile mp1 on (mp2.menu_id=mp1.parent)" +
		"left outer join lgd.menu_profile mp on (mp1.menu_id=mp.parent) " +
		"where  (mp2.form_name in (:parameter1)" +
		"or  mp1.form_name in (:parameter2) " +
		"or mp.form_name in (:parameter3) )" +
		"order by menu_id;",name="getMenu",resultClass=MenuProfile.class)
*/
public class MenuProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @Basic(optional = false)
    @Column(name = "menu_id", nullable = false)
    private Integer menuId;
    @Column(name = "resource_id", length = 99)
    private String resourceId;
   

	@Column(name = "parent")
    private Integer parent;
    @Column(name = "menu_source")
    private Integer menuSource;
    @Column(name = "menu_groupsea")
    private Integer menuGroupsea;
    @Column(name = "item_type", length = 60)
    private String itemType;
    @Column(name = "form_name", length = 99)
    private String formName;
    @Column(name = "group_id")
    private Integer groupId;
    
    @Transient
    private Integer appid;
    /* added by ashish dhupia for menu disable  functionality on 4/2/2015 */
    
    @Transient
    private boolean disabled;

    public Integer getAppid() {
    return appid;
    }


    public void setAppid(Integer appid) {
    this.appid = appid;
    }
    

    public MenuProfile() {
    }

    public MenuProfile(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getMenuSource() {
        return menuSource;
    }

    public void setMenuSource(Integer menuSource) {
        this.menuSource = menuSource;
    }

    public Integer getMenuGroupsea() {
        return menuGroupsea;
    }

    public void setMenuGroupsea(Integer menuGroupsea) {
        this.menuGroupsea = menuGroupsea;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuId != null ? menuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuProfile)) {
            return false;
        }
        MenuProfile other = (MenuProfile) object;
        if ((this.menuId == null && other.menuId != null) || (this.menuId != null && !this.menuId.equals(other.menuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication12.MenuProfile[ menuId=" + menuId + " ]";
    }


	public boolean isDisabled() {
		return disabled;
	}


	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}


}
