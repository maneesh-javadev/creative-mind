package in.nic.pes.lgd.forms;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class LGSetupForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2249588565343080101L;
	private int parentTypeCode;
	private char type;
	@NotEmpty(message = "Please enter nomenclature in english")
	@NotBlank(message="Nomenclature in english cant be blank")
	//@Length(max = 50)
	//@Pattern(regexp="^[A-Za-z0-9 -]{1,50}$", message="Please use alphabets - and space only")
	//@Pattern(regexp="^[a-zA-Z\\s]+$", message="Please use alphabets and space only")
	private String nomenEnglish;
	//@NotEmpty(message = "Please enter nomenclature in local language")
	//@Pattern(regexp="([1-9][0-9]*)$", message="Please Select Sub-District")
	//@Pattern(regexp="^[a-zA-Z\\s]+$", message="Please use alphabets and space only")
	private String nomenLocal;
	private String officialType;
	private List<DesignationForm> designation;
	private List<LGSubTypeForm> subType;
	private String[] desgName;
	private String[] desgNameLocal;
	private String subTypeName;
	private String subTypeLocal;
	private boolean isElected;
	private String childOrder;///JSP's text box's value
	private String codes;///JSP's text box's value
	private String names;///JSP's text box's value
	private String hierarchy;///JSP's text box's value
	private String subTyp;///JSP's text box's value
	private Integer localBodyTypeCode;
	private String localBodyTypeName;
	private char category;
	private String lBTList;
	@NotEmpty(message = "Please select Localbody Type")
	@NotNull(message = "Please select Localbody Type")
	private String check;
	
	private String levelLGB;
	private char level;
	private String temp1;
	private String tab4txt;
	private String hasTiers;
	private int tierSetupID;
public int getTierSetupID() {
		return tierSetupID;
	}

	public void setTierSetupID(int tierSetupID) {
		this.tierSetupID = tierSetupID;
	}

/**
	 * @return the tab4txt
	 */
	public String getTab4txt() {
		return tab4txt;
	}

	/**
	 * @param tab4txt the tab4txt to set
	 */
	public void setTab4txt(String tab4txt) {
		this.tab4txt = tab4txt;
	}

	//	private List<LocalBodyType> localBodyTypeList;
	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getTemp1() {
		return temp1;
	}



	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}

	public char getLevel() {
		return level;
	}

	public void setLevel(char level) {
		this.level = level;
	}

	public String getlBTList() {
		return lBTList;
	}

	public String getLevelLGB() {
		return levelLGB;
	}

	public void setLevelLGB(String levelLGB) {
		this.levelLGB = levelLGB;
	}

// TODO Remove unused code found by UCDetector
// 	public void setlBTList(String lBTList) {
// 		this.lBTList = lBTList;
// 	}

	/*Method to get NomenMap (Tab1)*/
	public Map<Object,String> getLGNomenMap(){
		String nomen;
		Integer TypeCd;
		String[] tempE=null;
		String[] tempL=null;
		
		Map<Object,String> nomenMp=new HashMap<Object,String>();
		tempE=this.getNomenEnglish().split(",");
		tempL=this.getNomenLocal().split(",");
		
		nomen="";
		for(int i=0; i<this.getCodes().split("%").length; i++)
		{
			TypeCd=this.getTypeCode().get(i);
			//nomen=this.getNomenEnglish()[i]+","+this.getNomenLocal()[i];
			nomen=tempE[i]+","+tempL[i];
			nomenMp.put(TypeCd, nomen);
		}
		return nomenMp;
		
		}
	
	/*Method to get HierarchyMap (Tab2)*/
	public Map<Object,String> getHierarchyMap(){
		String[] typeCd=null;
		String[] temp=null;
				
		Map<Object,String> hierarchyMap=new HashMap<Object,String>();
		temp=this.getHierarchy().split("~");
		for (int i=0; i<temp.length-1; i++)
		{
			if (i%2==0)
			{
				typeCd=temp[i].split("@");
				hierarchyMap.put(typeCd[0] + "," + typeCd[1], temp[i+1].replace('%',','));
			}
		}
		return hierarchyMap;
		}
	
	/*Method to get ChildOrderMap (Tab3)*/
	public Map<Object,String> getChildOrderMap(){
		String[] child;
		child=this.getChildOrder().split("@@@@");
		Map<Object,String> childOrder=new HashMap<Object,String>();
		String strCategory="";
		String strHeirarchy ="";
		strCategory=child[0];
		for (int i=0;i<child.length-1;i++)
		{
			strHeirarchy+=child[i+1]+",";
		}
		childOrder.put(strCategory, strHeirarchy);
		return childOrder;
		}
	
	/*Method to get SubTypeMap (Tab4)*/
	public Map<Object,String> getSubTypeMap(){
		String[] temp=null;
		
		Map<Object,String> subTypeMap=new HashMap<Object,String>();
		temp=this.getSubTyp().split("~");
		//System.out.println("getSubTypeMap()-================================================>");
		for (int i=0; i<temp.length-1; i++)
		{
			if (i%2==0)
			{
				//System.out.println("temp[i+1] Before : "+temp[i+1]);
				temp[i+1] = temp[i+1].replaceAll(":%",":").replace('%',',');
				//System.out.println("temp[i+1] After : "+temp[i+1]);
				subTypeMap.put(temp[i],temp[i+1]);
			}
		}
	
		return subTypeMap;
		}
	
	/*Method to get LGTypeCodes*/
	public ArrayList<Integer> getTypeCode() {
		String[] cdstr;
		cdstr=this.getCodes().split("%");
		ArrayList<Integer> TypeCode = new ArrayList<Integer>();
		for (int i=0;i<cdstr.length;i++)
		{
			TypeCode.add(Integer.parseInt(cdstr[i]));
		}
		return TypeCode;
	}
	
	/*Method to get LGTypes (Names)*/
	public ArrayList<String> getTypeName() {
		String[] name;
		name=this.getNames().split("%");
		ArrayList<String> typeName = new ArrayList<String>();
		for (int i=0;i<name.length;i++)
		{
			typeName.add(name[i]);
		}
		return typeName;
	}
	
	public int getParentTypeCode() {
		return parentTypeCode;
	}
	public void setParentTypeCode(int parentTypeCode) {
		this.parentTypeCode = parentTypeCode;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public String getNomenEnglish() {
		return nomenEnglish;
	}
	public void setNomenEnglish(String nomenEnglish) {
		this.nomenEnglish = nomenEnglish;
	}
	public String getNomenLocal() {
		return nomenLocal;
	}
	public void setNomenLocal(String nomenLocal) {
		this.nomenLocal = nomenLocal;
	}
	public List<DesignationForm> getDesignation() {
		return designation;
	}
	public void setDesignation(List<DesignationForm> designation) {
		this.designation = designation;
	}
	public List<LGSubTypeForm> getSubType() {
		return subType;
	}
	public void setSubType(List<LGSubTypeForm> subType) {
		this.subType = subType;
	}
	public String getOfficialType() {
		return officialType;
	}
	public void setOfficialType(String officialType) {
		this.officialType = officialType;
	}
	public String[] getDesgName() {
		return desgName;
	}
	public void setDesgName(String[] desgName) {
		this.desgName = desgName;
	}
	public String[] getDesgNameLocal() {
		return desgNameLocal;
	}
	public void setDesgNameLocal(String[] desgNameLocal) {
		this.desgNameLocal = desgNameLocal;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	public String getSubTypeLocal() {
		return subTypeLocal;
	}
	public void setSubTypeLocal(String subTypeLocal) {
		this.subTypeLocal = subTypeLocal;
	}
	public boolean isElected() {
		return isElected;
	}
	public void setElected(boolean isElected) {
		this.isElected = isElected;
	}
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}

	public String getChildOrder() {
		return childOrder;
	}

	public void setChildOrder(String childOrder) {
		this.childOrder = childOrder;
	}

	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	public String getSubTyp() {
		return subTyp;
	}

	public void setSubTyp(String subTyp) {
		this.subTyp = subTyp;
	}

	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	public char getCategory() {
		return category;
	}

	public void setCategory(char category) {
		this.category = category;
	}

	/**
	 * @return the hasTiers
	 */
	public String getHasTiers() {
		return hasTiers;
	}

	/**
	 * @param hasTiers the hasTiers to set
	 */
	public void setHasTiers(String hasTiers) {
		this.hasTiers = hasTiers;
	}

}
