package in.nic.pes.lgd.bean;

import in.nic.pes.common.beans.Category;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class SessionObject implements Serializable
{

	    private static final long serialVersionUID = 1L;
	    private String assignedUnitHierarchy;
	    private Integer stateId;
	    private Integer slc;
	    private Integer assignUnit;
	    @SuppressWarnings("rawtypes")
		private List listLanguagePackages;
	    @SuppressWarnings("rawtypes")
		private List menuProfile;
	    @SuppressWarnings("rawtypes")
		private Set formList;
	    private String selectedEntity;
	    private String district;
	    private String subDist;
	    private Integer districtCode;
	    private Integer subDistCode;
	    private Category category;
	    private Long userId;
	    private String loginId;
	    private String isCenterorstate;
	    private String remoteAddress;
	    private boolean isActive;
	
    public String getRemoteAddress() {
			return remoteAddress;
		}

		public void setRemoteAddress(String remoteAddress) {
			this.remoteAddress = remoteAddress;
		}

	public Integer getSlc() {
			return slc;
		}

		public void setSlc(Integer slc) {
			this.slc = slc;
		}

		public String getIsCenterorstate() {
			return isCenterorstate;
		}

		public void setIsCenterorstate(String isCenterorstate) {
			this.isCenterorstate = isCenterorstate;
		}

	public SessionObject()
    {
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getSubDist()
    {
        return subDist;
    }

    public void setSubDist(String subDist)
    {
        this.subDist = subDist;
    }

    public Integer getDistrictCode()
    {
        return districtCode;
    }

    public void setDistrictCode(Integer districtCode)
    {
        this.districtCode = districtCode;
    }

    public Integer getSubDistCode()
    {
        return subDistCode;
    }

    public void setSubDistCode(Integer subDistCode)
    {
        this.subDistCode = subDistCode;
    }

    public String getSelectedEntity()
    {
        return selectedEntity;
    }

    public void setSelectedEntity(String selectedEntity)
    {
        this.selectedEntity = selectedEntity;
    }

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public String getAssignedUnitHierarchy()
    {
        return assignedUnitHierarchy;
    }

    public void setAssignedUnitHierarchy(String assignedUnitHierarchy)
    {
        this.assignedUnitHierarchy = assignedUnitHierarchy;
    }

    public Integer getStateId()
    {
        return stateId;
    }

    public void setStateId(Integer stateId)
    {
        this.stateId = stateId;
    }

    public Integer getAssignUnit()
    {
        return assignUnit;
    }

    public void setAssignUnit(Integer assignUnit)
    {
        this.assignUnit = assignUnit;
    }

    @SuppressWarnings("rawtypes")
	public List getListLanguagePackages()
    {
        return listLanguagePackages;
    }

    public void setListLanguagePackages(@SuppressWarnings("rawtypes") List listLanguagePackages)
    {
        this.listLanguagePackages = listLanguagePackages;
    }

    @SuppressWarnings("rawtypes")
	public List getMenuProfile()
    {
        return menuProfile;
    }

    public void setMenuProfile(@SuppressWarnings("rawtypes") List menuProfile)
    {
        this.menuProfile = menuProfile;
    }

    @SuppressWarnings("rawtypes")
	public Set getFormList()
    {
        return formList;
    }

    public void setFormList(@SuppressWarnings("rawtypes") Set formList)
    {
        this.formList = formList;
    }

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

   
}