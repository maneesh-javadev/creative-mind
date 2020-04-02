package ws.in.nic.pes.lgdws.entity;


	import java.util.List;

	import javax.xml.bind.annotation.XmlAccessType;
	import javax.xml.bind.annotation.XmlAccessorType;
	import javax.xml.bind.annotation.XmlElement;
	import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement(name = "VillageListWithHierarchy")
	@XmlAccessorType (XmlAccessType.FIELD)
	public class VillageListWithHierarchyListXML {


		
		@XmlElement(name = "VillageListWithHierarchy")
		private List<VillageListWithHierarchyXML> villageListWithHierarchyXMLList = null;

		public List<VillageListWithHierarchyXML> getVillageListWithHierarchyXMLList() {
			return villageListWithHierarchyXMLList;
		}

		public void setVillageListWithHierarchyXMLList(List<VillageListWithHierarchyXML> villageListWithHierarchyXMLList) {
			this.villageListWithHierarchyXMLList = villageListWithHierarchyXMLList;
		}

		

		

		

	}


