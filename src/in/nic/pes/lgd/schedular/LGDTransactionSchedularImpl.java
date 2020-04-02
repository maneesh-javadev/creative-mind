package in.nic.pes.lgd.schedular;

import in.nic.pes.lgd.bean.EntityTransactionsNew;
import in.nic.pes.lgd.dao.PESTransactionDAO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;



public class LGDTransactionSchedularImpl implements LGDTransactionSchedular{
	private static final Logger log = Logger.getLogger(LGDTransactionSchedularImpl.class);

	@Autowired
	private PESTransactionDAO transactionDAO;
	
	/*@Autowired
	private LGDImpactService impactService;*/
	
	/**
	 * Spring based scheduler (cron)
	 * perform every day 12:00 AM mid-night
	 */

	//@Scheduled(cron = ApplicationConstant.CRON_TIME)
	public void processSchedular() {
		//System.out.println("Start Pocessing of scheulded task at cron time.");
		try{
			
			List<EntityTransactionsNew> scheduledTransactions = transactionDAO.getScheduledTransactions();
			Calendar calender1 = Calendar.getInstance();
			calender1.setTime(new Date());
			Calendar calendar2 = Calendar.getInstance();
		    for(EntityTransactionsNew entity : scheduledTransactions){
		    	boolean taskCompletedFlag = false;
				Integer transactionId = entity.gettId();
				
				Date scheduleDate = entity.getScheduledDate();
				calendar2.setTime(scheduleDate);
				
				
				if(calender1.equals(calendar2) || calender1.after(calendar2)){
					//System.out.println("Date comparision with current date, executed if matched with today or passed date. Transaction Id : " + transactionId);
					taskCompletedFlag = transactionDAO.isPerformedActionforPES(transactionId);
					
					if(taskCompletedFlag){
						//System.out.println("Record(s) for transaction id inserted / updated into PES. Transaction Id : " + transactionId);
						entity.setStatusFlg("C");
						//boolean completed = 
						transactionDAO.update(entity, false);
					}
				}
				
				calendar2.clear();
		    }	
			
			
		}catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
			//System.err.println("LGD Schedular Exception  : " + e.getMessage());
		}
	}

	/*@Override
	@Scheduled(cron = "0 34 17 ? * *")
	public void processImpactByServiceOrURL() {
		// TODO Auto-generated method stub
		try {
			//requestToServerViaURL();
			//requestToServerViaService();
			impactService.writeJsonToFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		private void requestToServerViaService() throws ConnectException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			InputStream inputStream = getClass().getResourceAsStream("/ws.properties");
			Properties prop = new Properties();
			prop.load(inputStream);
			String impactPostURL = MessageFormat.format(prop.getProperty("impactPostURLByService"), new Object[] {});

			
			System.out.println("service level " + impactPostURL);
			
			System.out.println("Http Post request calling method.............");
			HttpPost getRequest = new HttpPost(impactPostURL);
			
			//HttpParams params1 = new BasicHttpParams();
			//HttpConnectionParams.setConnectionTimeout(params1, 30 * 1000);
			//HttpConnectionParams.setSoTimeout(params1, 30 * 1000);
			
			//params1.setParameter(LBConstant.PARAM_ENTITY_TYPE.get(), params.get(LBConstant.LGD_PARENT_TYPE.get()));
			//params1.setParameter(LBConstant.PARAM_ENTITY_CODE.get(), params.get(LBConstant.LGD_PARENT_CODE.get()));
			
			//getRequest.setParams(params1);
			System.out.println("GOT object HTTP post Request .............");

			// Set the API media type in http accept header
			getRequest.addHeader("accept", "Application/Json");

			// Send the request; It will immediately return the response in
			// HttpResponse object
			System.out.println("HttpClient calling execute Methodmethod.............");
			HttpResponse response = httpClient.execute(getRequest);

			System.out.println("Got HttpResponse Object............." + response);
			
			// verify the valid error code first
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("Http Response Status Code ..... " + statusCode);
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}

			// Now pull back the response object
			HttpEntity httpEntity = response.getEntity();
			String apiOutput = EntityUtils.toString(httpEntity);
			System.out.println("Final Output.............:: " + apiOutput);

		} catch (Exception ce) {
			ce.printStackTrace();
			throw new ConnectException("Connection Could not be Established");
		} finally {
			System.out.println("inside lgd client finally .............");
			// Important: Close the connect
			httpClient.getConnectionManager().shutdown();
			System.out.println("http client shutdown done.............");
		}
		return;

	}*/
	
	
	
	
	
	}
