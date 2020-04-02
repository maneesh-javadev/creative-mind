package in.nic.pes.lgd.service;



public class EmailSmsThread extends Thread{
	
	private int t_code  = 0;
	private char t_type='V';
	private EmailService emailService1 = null;
	private Thread threadToInterrupt = null;  
	public EmailSmsThread(int t_code, char t_type,EmailService emailService) {
		super();	
		this.t_code = t_code;
		this.t_type = t_type;
		this.emailService1=emailService;
		
	} 
	 /* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run()   {
		    try {
		    	
		    	 Thread.currentThread();
				Thread.sleep(5000L);
			
		    	
		    	emailService1.sendEmail(t_code,t_type);
		    	
		    }
		    catch(Exception e)     {
		    	e.printStackTrace();
				e.getCause().getMessage();
		    	threadToInterrupt.interrupt();
		    }
		  }
	
	}