package in.nic.pes.lgd.schedular;

import org.springframework.stereotype.Component;

/**
 * @interface LGDTransactionSchedular.java
 * @author Vinay Yadav
 * @created October 17, 2012 11:05:44 AM
 */

@Component
public interface LGDTransactionSchedular { // NO_UCD (use default)
	
	public void processSchedular();
	
	//public void processImpactByServiceOrURL();

}
