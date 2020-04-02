package in.nic.pes.lgd.common;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Locale;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import in.nic.pes.lgd.bean.NotificationLog;
import in.nic.pes.lgd.dao.EmailDAO;

public class SmsManager {
	private static final Logger log = Logger.getLogger(SmsManager.class);
	private  final String hostname = PESResourceBundle.getBundle("hibernate", Locale.ENGLISH).getObject("sms.hostname").toString();
	private  final String uname = PESResourceBundle.getBundle("hibernate", Locale.ENGLISH).getObject("sms.username").toString();
	private  final String pwd = PESResourceBundle.getBundle("hibernate", Locale.ENGLISH).getObject("sms.password").toString();
	@Autowired
	EmailDAO emailDao;
	
	@SuppressWarnings("restriction")
	public void makeHTTPConnection(String phoneNumber,String textMessage) throws Exception {
		log.info("Sms Service start!");
		StringBuffer xmlBuf = new StringBuffer();
		try {
			String message = URLEncoder.encode(textMessage,"ISO-8859-1");	
			String urlString = "https://smsgw.sms.gov.in/failsafe/HttpLink?username="+ uname +"&pin="+ pwd +"&message="+ message +"&mnumber="+ phoneNumber.trim() +"&signature="+ hostname;
			log.info(urlString);
			//URL url = new URL(urlString);
			
			URL url = new URL(null, urlString, new sun.net.www.protocol.https.Handler());
			SSLContext ssl_ctx = SSLContext.getInstance("SSL");
			TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
				
            }
        };
			/*TrustManager[] trust_mgr = get_trust_mgr();*/
			ssl_ctx.init(null, // key manager
					trustAllCerts, // trust manager
					new SecureRandom()); // random number generator
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
			HostnameVerifier allHostsValid = new HostnameVerifier() {
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        };
			
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			conn.connect();
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			BufferedReader in= new BufferedReader(new InputStreamReader(conn.getInputStream()));
		   	String str;
		    	while((str = in.readLine()) != null) {
		    		xmlBuf.append(str);
		    }
	    	
		    	System.out.println("SMS send....@@");
		    	in.close();
	    	log.info("\n\n Sms is sent \n\n");
		}catch(Exception e){
			e.printStackTrace();
			e.getCause().getMessage();
			log.debug("Exception" + e);
		}
	}
	
	@SuppressWarnings("restriction")
	public void smsNotification(NotificationLog notificationlog) throws Exception {
		log.info("Sms Service start!");
		StringBuffer xmlBuf = new StringBuffer();
		String phoneNumber = notificationlog.getMobileNo();
		try {
			String message = URLEncoder.encode(notificationlog.getMsgDesc(),"ISO-8859-1");	
			String urlString = "https://smsgw.sms.gov.in/failsafe/HttpLink?username="+ uname +"&pin="+ pwd +"&message="+ message +"&mnumber="+ phoneNumber.trim() +"&signature="+ hostname;
			log.info(urlString);
			//URL url = new URL(urlString);
			
			URL url = new URL(null, urlString, new sun.net.www.protocol.https.Handler());
			SSLContext ssl_ctx = SSLContext.getInstance("SSL");
			TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
				
            }
        };
			/*TrustManager[] trust_mgr = get_trust_mgr();*/
			ssl_ctx.init(null, // key manager
					trustAllCerts, // trust manager
					new SecureRandom()); // random number generator
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
			HostnameVerifier allHostsValid = new HostnameVerifier() {
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        };
			
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			conn.connect();
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			BufferedReader in= new BufferedReader(new InputStreamReader(conn.getInputStream()));
		   	String str;
		    	while((str = in.readLine()) != null) {
		    		xmlBuf.append(str);
		    }
		    	notificationlog.setMsgStatus("Success");
		    	System.out.println("SMS send....@@");
		    	in.close();
	    	log.info("\n\n Sms is sent \n\n");
		}catch(Exception e){
			notificationlog.setMsgStatus("Failed  "+e.getMessage());
			e.printStackTrace();
			e.getCause().getMessage();
			log.debug("Exception" + e);
		}finally {
				_postSuccess(notificationlog);
		}
	}
	
	private void _postSuccess(NotificationLog notificationlog) {
		emailDao._postSuccess(notificationlog);
	}
	
}
