package in.nic.pes.lgd.common;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.nic.pes.lgd.bean.SessionObject;



public class UsageLogFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		String userLoginID = "";
		Integer stateId = null;
		String ipAddress = req.getRemoteAddr();
		String referer = req.getHeader("referer");
		String url = req.getRequestURL().toString();
		String userAgent = req.getHeader("User-Agent");
	
		String session_id=null;
		if(session!=null)
		{	
			session_id=session.getId();
			SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
			userLoginID =sessionObject!=null && sessionObject.getUserId()!=null?sessionObject.getUserId().toString():null;
			
			if(userLoginID!=null && req.getMethod().equalsIgnoreCase("post")){
				String query="insert into logs (annotation,date,user_login_id,state,ipaddress,url,referer,user_agent,session_id) values ('"+req.getServletPath().replace("/","")+"','"+new Date()+"','"+userLoginID+"',"+stateId+",'"+ipAddress+"','"+url+"','"+referer+"','"+userAgent+"'"+",'"+session_id+"'"+")";
				Logger logger=Logger.getLogger("log4j.rootLogger");
				logger.error(query);
			}
		}
		
		
	
		
		

		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
