package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
@XmlAccessorType (XmlAccessType.FIELD)
public class VillagewiseLGDDetails {
	

	@XmlElement(name = "State")
	private List<StateEntity> stateEntityList=null;
	
	@XmlElement(name = "District")
	private List<DistrictEntity> districtEntityList=null;
	
	@XmlElement(name = "Sub-district")
	private List<SubdistrictEntity> SubdistrictEntityList=null;
	
	@XmlElement(name = "Block")
	private List<BlockLGDDetailRDDEPT> blockLGDDetailRDDEPTList=null;
	
	@XmlElement(name = "Localbody")
	private List<GPLGDDetailRDDEPT> gpLGDDetailRDDEPTList=null;

	public List<StateEntity> getStateEntityList() {
		return stateEntityList;
	}

	public void setStateEntityList(List<StateEntity> stateEntityList) {
		this.stateEntityList = stateEntityList;
	}

	public List<DistrictEntity> getDistrictEntityList() {
		return districtEntityList;
	}

	public void setDistrictEntityList(List<DistrictEntity> districtEntityList) {
		this.districtEntityList = districtEntityList;
	}

	public List<SubdistrictEntity> getSubdistrictEntityList() {
		return SubdistrictEntityList;
	}

	public void setSubdistrictEntityList(List<SubdistrictEntity> subdistrictEntityList) {
		SubdistrictEntityList = subdistrictEntityList;
	}

	public List<BlockLGDDetailRDDEPT> getBlockLGDDetailRDDEPTList() {
		return blockLGDDetailRDDEPTList;
	}

	public void setBlockLGDDetailRDDEPTList(List<BlockLGDDetailRDDEPT> blockLGDDetailRDDEPTList) {
		this.blockLGDDetailRDDEPTList = blockLGDDetailRDDEPTList;
	}

	public List<GPLGDDetailRDDEPT> getGpLGDDetailRDDEPTList() {
		return gpLGDDetailRDDEPTList;
	}

	public void setGpLGDDetailRDDEPTList(List<GPLGDDetailRDDEPT> gpLGDDetailRDDEPTList) {
		this.gpLGDDetailRDDEPTList = gpLGDDetailRDDEPTList;
	}

	
	
	


}
