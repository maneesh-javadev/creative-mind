package in.nic.pes.lgd.interceptor;

import in.nic.pes.common.menu.pojo.Form;
import in.nic.pes.common.menu.pojo.Privilege;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter implements Filter
{

	@Override
	public void destroy() 
	{}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession session=req.getSession(false);
		
		List<String> ignoreList=new ArrayList<String>();
		ignoreList.add("login.do");
		ignoreList.add("welcome.do");
		ignoreList.add("logout.htm");
		ignoreList.add("home.htm");
		ignoreList.add("switchunit.htm");
		ignoreList.add("getEntityType.htm");
		ignoreList.add("getAssignedUnit.htm");
/*		ignoreList.add("globalviewdistrictforcitizen.do");
		ignoreList.add("globalviewvillageforcitizen.do");
		ignoreList.add("globalviewsubdistrictforcitizen.do");
		ignoreList.add("globalviewstateforcitizen.do");
*/	
		//System.out.println("==============");
		String annotation=req.getServletPath().replace("/","");
		
		
		//System.out.println("=======annotation is======="+annotation);
		Set<Form> formList=(Set<Form>) session.getAttribute("forms");
		boolean allow=false;
		
		if(formList!=null)
		{
			for(Form formLocal:formList)
			{
				//System.out.println("=============="+formLocal.getAction());
				if(formLocal.getAction().contains(annotation)||ignoreList.contains(annotation))
				{
					allow=true;
				}
				else if(formLocal.getPrivileges()!=null&&formLocal.getPrivileges().size()>0)
				{
					for(Privilege privilege:formLocal.getPrivileges())
					{
						//System.out.println("============="+privilege.getAction());
						if(privilege.getAction()!=null){
							if(privilege.getAction().contains(annotation)||privilege.getType().contains(annotation))
							{
								allow=true;
								break;
							}
						}	
					}
				}
			}
		}
		else if(ignoreList.contains(annotation))
		{	
			allow=true;
		}	
		//System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+allow);
	
		
		//SESSION HIJACKING CODE     START		
		
		String remoteHost=(String) req.getSession().getAttribute("UserIp");
	    List<String> sessionIgnoreList=new ArrayList<String>();
	    sessionIgnoreList.add("login");
	    sessionIgnoreList.add("welcome");
	    sessionIgnoreList.add("switchunit");
	    sessionIgnoreList.add("home");
	    String url=req.getServletPath();
	    boolean allowSession=false;
	    for(String ignore:sessionIgnoreList)
	    {
	      if(url.contains(ignore))
	      {  
	    	  allowSession=true;
	      }	  
	    }
	    if(remoteHost!=null && !remoteHost.equals(""))
	    {
	    	if(remoteHost.equals(req.getRemoteHost())){
	    		allowSession=true;
	    	}	
	    }
		
		//SESSION HIJACKING CODE    END	
	    
	    if(allow && allowSession)
	    {	
			chain.doFilter(request, response);
	    }
		else
		{	
			req.getRequestDispatcher("/error.html").forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{}

}

