package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.ConfigurationSubdistrict;
import in.nic.pes.lgd.bean.NomenclatureSubdistrict;
import in.nic.pes.lgd.bean.NomenclatureSubdistrictPK;

import java.util.List;

import org.hibernate.Session;

public interface NomenclatureSubDistDAO {
	public int getMaxRecords(String query)throws Exception;
	public boolean nomenclatureInactivate(NomenclatureSubdistrictPK nomenclatureSubdistrictPK,Session session)throws Exception;
	boolean addNomenclatureSudDist(NomenclatureSubdistrict nomenclatureSubdistrict, Session session)throws Exception;
	boolean addConfigSudDist(ConfigurationSubdistrict configurationSubdistrict,
			Session session)throws Exception;
	public List<NomenclatureSubdistrict> getNomenclatureDetails(int slc)throws Exception;

}
