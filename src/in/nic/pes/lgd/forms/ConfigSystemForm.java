package in.nic.pes.lgd.forms;

import java.util.List;

public class ConfigSystemForm { // NO_UCD (unused code)

	private List<LGSetupForm> lgsetupform;
	private List<LGSetupForm> tiers;
	
	@SuppressWarnings("unused")
	private List<LGSetupForm> getLgsetupform() {
		return lgsetupform;
	}
	public void setLgsetupform(List<LGSetupForm> lgsetupform) {
		this.lgsetupform = lgsetupform;
	}
	public List<LGSetupForm> getTiers() {
		return tiers;
	}
	public void setTiers(List<LGSetupForm> tiers) {
		this.tiers = tiers;
	}


}
