package in.nic.pes.lgd.common;

import java.util.Comparator;

import in.nic.pes.lgd.bean.StatewiseEntitiesCount;

public class CompareStatewiseEntitiesCount  implements Comparator<StatewiseEntitiesCount>{

	@Override
	public int compare(StatewiseEntitiesCount p1, StatewiseEntitiesCount p2) {
		if(p1.getState_name_english().equalsIgnoreCase(p2.getState_name_english()))
        {
            return p1.getState_name_english().compareTo(p2.getState_name_english());
        }
		return p1.getState_name_english().compareTo(p2.getState_name_english());
		
	}

}
