package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.NomenclatureSubdistrict;
import in.nic.pes.lgd.forms.NomenclatureSubDistForm;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface NomenclatureSubDistService {
	
	public boolean addNomenclatureSudDist(NomenclatureSubDistForm nonSubDistForm,int stateCode, HttpSession session)throws Exception;
	public boolean modifyNomenclatureSudDist(NomenclatureSubDistForm nomenclatureSubDistForm,int stateCode, HttpSession httpsession)throws Exception;
	public List<NomenclatureSubdistrict> checkNomenclature(int stateCode, long roleCode)throws Exception;

}
