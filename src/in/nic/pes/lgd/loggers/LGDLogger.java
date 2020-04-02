package in.nic.pes.lgd.loggers;
import org.apache.log4j.Logger;

public class LGDLogger extends Logger {
	
	protected LGDLogger(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static Logger getLogger(@SuppressWarnings("rawtypes") Class clazz){
		return Logger.getLogger(clazz);
	}
	
	public void log(String message){ // NO_UCD (unused code)
	
	
	}

}
