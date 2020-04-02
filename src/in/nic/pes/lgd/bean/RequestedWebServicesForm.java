package in.nic.pes.lgd.bean;

import java.util.List;

public class RequestedWebServicesForm {
	
	public List<String> ipAddressList;
	
	public List<String> applicationName;
	
	public boolean mobileApp;
	
	public String fileName;

	public List<String> getIpAddressList() {
		return ipAddressList;
	}

	public void setIpAddressList(List<String> ipAddressList) {
		this.ipAddressList = ipAddressList;
	}

	public List<String> getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(List<String> applicationName) {
		this.applicationName = applicationName;
	}

	public boolean isMobileApp() {
		return mobileApp;
	}

	public void setMobileApp(boolean mobileApp) {
		this.mobileApp = mobileApp;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	
	
	

}
