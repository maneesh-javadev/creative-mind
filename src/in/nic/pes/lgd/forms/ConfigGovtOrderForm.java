package in.nic.pes.lgd.forms;

import java.util.ArrayList;
import java.util.List;

public class ConfigGovtOrderForm {
	
	private int  operationCodeState;	
	private int operationCodeLandRegion;
	private boolean  isgovtorderuploadState;
	private boolean  isgovtorderuploadRegion;
	
	private int operationCodeCreateBlock;
	private int operationCodeModifyBlock;
	private boolean isgovtorderuploadBlock;
	private boolean isgovtorderuploadModBlk;
	
	private int operationCodeCreateWard;
	private int operationCodeModifyWard;
	private int operationCodeModiCovAreaWard;
	private boolean isgovtorderuploadCreateWard;
	private boolean isgovtorderuploadModifyWard;
	private boolean isgovtorderuploadModCovArWard;
	
	private int operationCodeDefLocalSetup;
	private int operationCodeCreNewLocBody;
	private int operationCodeModifyLocBody;
	private int operationCodeModifyTypLocBody;
	private int operationCodeModifyParentBody;
	private int operationCodeModCovrdArea;
	private boolean isgovtorderuploadLocalSetup;
	private boolean isgovtorderuploadNewLocBody;
	private boolean isgovtorderuploadModifyLocBody;
	private boolean isgovtorderuploadModTypLocBody;
	private boolean isgovtorderuploadModParentBody;
	private boolean isgovtorderuploadModCovrdArea;
	
	private int nomenclatureSubDist;
	private int createNewDist;
	private int modifyDistrict;
	private int mergeDistrict;
	private int createNewSubdist;
	private int modifySubDist;
	private int shiftSubDist;
	private int createVillage;
	private int modifyVillage;
	private int shiftVillage;
	private boolean isgovtorduploadNomenSubDist;
	private boolean isgovtorduploadCreateNewDis;
	private boolean isgovtorderuploadModDist;
	private boolean isgovtorderuploadMergDist;
	private boolean isgovtorderuploadCrtNewSubdist;
	private boolean isgovtorderuploadModSubdist;
	private boolean isgovtorderuploadShiftSubdist;
	private boolean isgovtorderuploadCrtVillage;
	private boolean isgovtorderuploadModVillage;
	private boolean isgovtorderuploadShiftVillage;
	
	private List<AdministratorUnit> listAdminUnits = new ArrayList<AdministratorUnit>();	
	private List <String> lrmData = new ArrayList<String>();
	private List<String> lgdmData = new ArrayList<String>();
	private List <String> constituencyData = new ArrayList<String>();
	private List<String> adminData = new ArrayList<String>();
	
	

	public List<AdministratorUnit> getListAdminUnits() {
		return listAdminUnits;
	}
	public void setListAdminUnits(List<AdministratorUnit> listAdminUnits) {
		this.listAdminUnits = listAdminUnits;
	}
	
	public int getOperationCodeState() {
		return operationCodeState;
	}
	public void setOperationCodeState(int operationCodeState) {
		this.operationCodeState = operationCodeState;
	}
	public int getOperationCodeLandRegion() {
		return operationCodeLandRegion;
	}
	public void setOperationCodeLandRegion(int operationCodeLandRegion) {
		this.operationCodeLandRegion = operationCodeLandRegion;
	}
	public boolean isIsgovtorderuploadState() {
		return isgovtorderuploadState;
	}
	public void setIsgovtorderuploadState(boolean isgovtorderuploadState) {
		this.isgovtorderuploadState = isgovtorderuploadState;
	}
	public boolean isIsgovtorderuploadRegion() {
		return isgovtorderuploadRegion;
	}
	public void setIsgovtorderuploadRegion(boolean isgovtorderuploadRegion) {
		this.isgovtorderuploadRegion = isgovtorderuploadRegion;
	}
	public int getOperationCodeCreateBlock() {
		return operationCodeCreateBlock;
	}
	public void setOperationCodeCreateBlock(int operationCodeCreateBlock) {
		this.operationCodeCreateBlock = operationCodeCreateBlock;
	}
	public int getOperationCodeModifyBlock() {
		return operationCodeModifyBlock;
	}
	public void setOperationCodeModifyBlock(int operationCodeModifyBlock) {
		this.operationCodeModifyBlock = operationCodeModifyBlock;
	}

	public boolean isIsgovtorderuploadModBlk() {
		return isgovtorderuploadModBlk;
	}
	public void setIsgovtorderuploadModBlk(boolean isgovtorderuploadModBlk) {
		this.isgovtorderuploadModBlk = isgovtorderuploadModBlk;
	}
	public int getOperationCodeCreateWard() {
		return operationCodeCreateWard;
	}
	public void setOperationCodeCreateWard(int operationCodeCreateWard) {
		this.operationCodeCreateWard = operationCodeCreateWard;
	}
	public int getOperationCodeModifyWard() {
		return operationCodeModifyWard;
	}
	public void setOperationCodeModifyWard(int operationCodeModifyWard) {
		this.operationCodeModifyWard = operationCodeModifyWard;
	}
	public int getOperationCodeModiCovAreaWard() {
		return operationCodeModiCovAreaWard;
	}
	public void setOperationCodeModiCovAreaWard(int operationCodeModiCovAreaWard) {
		this.operationCodeModiCovAreaWard = operationCodeModiCovAreaWard;
	}
	public boolean isIsgovtorderuploadCreateWard() {
		return isgovtorderuploadCreateWard;
	}
	public void setIsgovtorderuploadCreateWard(boolean isgovtorderuploadCreateWard) {
		this.isgovtorderuploadCreateWard = isgovtorderuploadCreateWard;
	}
	public boolean isIsgovtorderuploadModifyWard() {
		return isgovtorderuploadModifyWard;
	}
	public void setIsgovtorderuploadModifyWard(boolean isgovtorderuploadModifyWard) {
		this.isgovtorderuploadModifyWard = isgovtorderuploadModifyWard;
	}
	public boolean isIsgovtorderuploadModCovArWard() {
		return isgovtorderuploadModCovArWard;
	}
	public void setIsgovtorderuploadModCovArWard(
			boolean isgovtorderuploadModCovArWard) {
		this.isgovtorderuploadModCovArWard = isgovtorderuploadModCovArWard;
	}
	public int getOperationCodeDefLocalSetup() {
		return operationCodeDefLocalSetup;
	}
	public void setOperationCodeDefLocalSetup(int operationCodeDefLocalSetup) {
		this.operationCodeDefLocalSetup = operationCodeDefLocalSetup;
	}
	public int getOperationCodeCreNewLocBody() {
		return operationCodeCreNewLocBody;
	}
	public void setOperationCodeCreNewLocBody(int operationCodeCreNewLocBody) {
		this.operationCodeCreNewLocBody = operationCodeCreNewLocBody;
	}
	public int getOperationCodeModifyLocBody() {
		return operationCodeModifyLocBody;
	}
	public void setOperationCodeModifyLocBody(int operationCodeModifyLocBody) {
		this.operationCodeModifyLocBody = operationCodeModifyLocBody;
	}
	public int getOperationCodeModifyTypLocBody() {
		return operationCodeModifyTypLocBody;
	}
	public void setOperationCodeModifyTypLocBody(int operationCodeModifyTypLocBody) {
		this.operationCodeModifyTypLocBody = operationCodeModifyTypLocBody;
	}
	public int getOperationCodeModifyParentBody() {
		return operationCodeModifyParentBody;
	}
	public void setOperationCodeModifyParentBody(int operationCodeModifyParentBody) {
		this.operationCodeModifyParentBody = operationCodeModifyParentBody;
	}
	public int getOperationCodeModCovrdArea() {
		return operationCodeModCovrdArea;
	}
	public void setOperationCodeModCovrdArea(int operationCodeModCovrdArea) {
		this.operationCodeModCovrdArea = operationCodeModCovrdArea;
	}
	public boolean isIsgovtorderuploadLocalSetup() {
		return isgovtorderuploadLocalSetup;
	}
	public void setIsgovtorderuploadLocalSetup(boolean isgovtorderuploadLocalSetup) {
		this.isgovtorderuploadLocalSetup = isgovtorderuploadLocalSetup;
	}
	public boolean isIsgovtorderuploadNewLocBody() {
		return isgovtorderuploadNewLocBody;
	}
	public void setIsgovtorderuploadNewLocBody(boolean isgovtorderuploadNewLocBody) {
		this.isgovtorderuploadNewLocBody = isgovtorderuploadNewLocBody;
	}
	public boolean isIsgovtorderuploadModifyLocBody() {
		return isgovtorderuploadModifyLocBody;
	}
	public void setIsgovtorderuploadModifyLocBody(
			boolean isgovtorderuploadModifyLocBody) {
		this.isgovtorderuploadModifyLocBody = isgovtorderuploadModifyLocBody;
	}
	public boolean isIsgovtorderuploadModTypLocBody() {
		return isgovtorderuploadModTypLocBody;
	}
	public void setIsgovtorderuploadModTypLocBody(
			boolean isgovtorderuploadModTypLocBody) {
		this.isgovtorderuploadModTypLocBody = isgovtorderuploadModTypLocBody;
	}
	public boolean isIsgovtorderuploadModParentBody() {
		return isgovtorderuploadModParentBody;
	}
	public void setIsgovtorderuploadModParentBody(
			boolean isgovtorderuploadModParentBody) {
		this.isgovtorderuploadModParentBody = isgovtorderuploadModParentBody;
	}
	public boolean isIsgovtorderuploadModCovrdArea() {
		return isgovtorderuploadModCovrdArea;
	}
	public void setIsgovtorderuploadModCovrdArea(
			boolean isgovtorderuploadModCovrdArea) {
		this.isgovtorderuploadModCovrdArea = isgovtorderuploadModCovrdArea;
	}
	public int getNomenclatureSubDist() {
		return nomenclatureSubDist;
	}
	public void setNomenclatureSubDist(int nomenclatureSubDist) {
		this.nomenclatureSubDist = nomenclatureSubDist;
	}
	public int getCreateNewDist() {
		return createNewDist;
	}
	public void setCreateNewDist(int createNewDist) {
		this.createNewDist = createNewDist;
	}
	public int getModifyDistrict() {
		return modifyDistrict;
	}
	public void setModifyDistrict(int modifyDistrict) {
		this.modifyDistrict = modifyDistrict;
	}
	public int getMergeDistrict() {
		return mergeDistrict;
	}
	public void setMergeDistrict(int mergeDistrict) {
		this.mergeDistrict = mergeDistrict;
	}
	
	
	
	public int getCreateNewSubdist() {
		return createNewSubdist;
	}
	public void setCreateNewSubdist(int createNewSubdist) {
		this.createNewSubdist = createNewSubdist;
	}
	public int getModifySubDist() {
		return modifySubDist;
	}
	public void setModifySubDist(int modifySubDist) {
		this.modifySubDist = modifySubDist;
	}
	public int getShiftSubDist() {
		return shiftSubDist;
	}
	public void setShiftSubDist(int shiftSubDist) {
		this.shiftSubDist = shiftSubDist;
	}
	public int getCreateVillage() {
		return createVillage;
	}
	public void setCreateVillage(int createVillage) {
		this.createVillage = createVillage;
	}
	public int getModifyVillage() {
		return modifyVillage;
	}
	public void setModifyVillage(int modifyVillage) {
		this.modifyVillage = modifyVillage;
	}
	public int getShiftVillage() {
		return shiftVillage;
	}
	public void setShiftVillage(int shiftVillage) {
		this.shiftVillage = shiftVillage;
	}
	public boolean isIsgovtorduploadNomenSubDist() {
		return isgovtorduploadNomenSubDist;
	}
	public void setIsgovtorduploadNomenSubDist(boolean isgovtorduploadNomenSubDist) {
		this.isgovtorduploadNomenSubDist = isgovtorduploadNomenSubDist;
	}
	public boolean isIsgovtorduploadCreateNewDis() {
		return isgovtorduploadCreateNewDis;
	}
	public void setIsgovtorduploadCreateNewDis(boolean isgovtorduploadCreateNewDis) {
		this.isgovtorduploadCreateNewDis = isgovtorduploadCreateNewDis;
	}
	public boolean isIsgovtorderuploadModDist() {
		return isgovtorderuploadModDist;
	}
	public void setIsgovtorderuploadModDist(boolean isgovtorderuploadModDist) {
		this.isgovtorderuploadModDist = isgovtorderuploadModDist;
	}
	public boolean isIsgovtorderuploadMergDist() {
		return isgovtorderuploadMergDist;
	}
	public void setIsgovtorderuploadMergDist(boolean isgovtorderuploadMergDist) {
		this.isgovtorderuploadMergDist = isgovtorderuploadMergDist;
	}
	public boolean isIsgovtorderuploadCrtNewSubdist() {
		return isgovtorderuploadCrtNewSubdist;
	}
	public void setIsgovtorderuploadCrtNewSubdist(
			boolean isgovtorderuploadCrtNewSubdist) {
		this.isgovtorderuploadCrtNewSubdist = isgovtorderuploadCrtNewSubdist;
	}
	public boolean isIsgovtorderuploadModSubdist() {
		return isgovtorderuploadModSubdist;
	}
	public void setIsgovtorderuploadModSubdist(boolean isgovtorderuploadModSubdist) {
		this.isgovtorderuploadModSubdist = isgovtorderuploadModSubdist;
	}
	public boolean isIsgovtorderuploadShiftSubdist() {
		return isgovtorderuploadShiftSubdist;
	}
	public void setIsgovtorderuploadShiftSubdist(
			boolean isgovtorderuploadShiftSubdist) {
		this.isgovtorderuploadShiftSubdist = isgovtorderuploadShiftSubdist;
	}
	public boolean isIsgovtorderuploadCrtVillage() {
		return isgovtorderuploadCrtVillage;
	}
	public void setIsgovtorderuploadCrtVillage(boolean isgovtorderuploadCrtVillage) {
		this.isgovtorderuploadCrtVillage = isgovtorderuploadCrtVillage;
	}
	public boolean isIsgovtorderuploadModVillage() {
		return isgovtorderuploadModVillage;
	}
	public void setIsgovtorderuploadModVillage(boolean isgovtorderuploadModVillage) {
		this.isgovtorderuploadModVillage = isgovtorderuploadModVillage;
	}
	public boolean isIsgovtorderuploadShiftVillage() {
		return isgovtorderuploadShiftVillage;
	}
	public void setIsgovtorderuploadShiftVillage(
			boolean isgovtorderuploadShiftVillage) {
		this.isgovtorderuploadShiftVillage = isgovtorderuploadShiftVillage;
	}
	public List<String> getLrmData() {
		return lrmData;
	}
	public void setLrmData(List<String> lrmData) {
		this.lrmData = lrmData;
	}
	public List<String> getLgdmData() {
		return lgdmData;
	}
	public void setLgdmData(List<String> lgdmData) {
		this.lgdmData = lgdmData;
	}
	public List<String> getConstituencyData() {
		return constituencyData;
	}
	public void setConstituencyData(List<String> constituencyData) {
		this.constituencyData = constituencyData;
	}
	public List<String> getAdminData() {
		return adminData;
	}
	public void setAdminData(List<String> adminData) {
		this.adminData = adminData;
	}
	public boolean getIsgovtorderuploadBlock() {
		return isgovtorderuploadBlock;
	}
	public void setIsgovtorderuploadBlock(boolean isgovtorderuploadBlock) {
		this.isgovtorderuploadBlock = isgovtorderuploadBlock;
	}

	
	
	
	
	
	
	
	

}
