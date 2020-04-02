package in.nic.pes.lgd.draft.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.draft.dao.DraftVillageDao;
import in.nic.pes.lgd.draft.service.DraftVillageService;

@Service
public class DraftVillageServiceImpl implements DraftVillageService{

	@Autowired
	DraftVillageDao draftVillageDao;

	@Override
	public List<Village> getDraftVillageList(String subdistrictCodes, String villagesFull) throws Exception {
		List<Integer> subdistrictCodeList =new ArrayList<Integer>();
		
		List<Integer> villageListFull=new ArrayList<Integer>();
		villageListFull.add(-1);
		
		if (subdistrictCodes!=null &&  subdistrictCodes.contains("#")) {
			Scanner scanner = new Scanner(subdistrictCodes);
			scanner.useDelimiter("#");
			while (scanner.hasNext()) {
				String subdistrctArray[]= scanner.next().split("@");
				subdistrictCodeList.add(Integer.parseInt(subdistrctArray[0]));
			}
			scanner.close();
		}
		
		if (villagesFull!=null && !("").equals(villagesFull.trim())) {
			Scanner scanner = new Scanner(villagesFull);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				villageListFull.add(Integer.parseInt(scanner.next()));
			}
			scanner.close();
		}
		
		return draftVillageDao.getDraftVillageList(subdistrictCodeList, villageListFull);
	}

	@Override
	public List<Village> getEditDraftVillageList(String subdistrictCodes, Integer paramCode,boolean isContribute) throws Exception {
		List<Integer> subdistrictCodeList =new ArrayList<Integer>();
		if (subdistrictCodes!=null &&  subdistrictCodes.contains("#")) {
			Scanner scanner = new Scanner(subdistrictCodes);
			scanner.useDelimiter("#");
			while (scanner.hasNext()) {
				String subdistrctArray[]= scanner.next().split("@");
				subdistrictCodeList.add(Integer.parseInt(subdistrctArray[0]));
			}
			scanner.close();
		}
		return draftVillageDao.getEditDraftVillageList(subdistrictCodeList, paramCode,isContribute);
	}

	

	
	
	

}
