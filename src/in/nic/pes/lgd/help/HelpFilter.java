package in.nic.pes.lgd.help;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.org.ep.exceptionhandler.XmlParser;


public class HelpFilter implements Filter {
	
	private static final Logger log = Logger.getLogger(HelpFilter.class);
	String context = null;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try{		
			HttpServletRequest req=(HttpServletRequest)request;
			//HttpServletResponse res=(HttpServletResponse)response;
			//HttpSession session=req.getSession(false);	
			String requestpath = req.getServletPath().replace("/","");
			Document doc = XmlParser.parse("/help.xml");			
			doc.getDocumentElement().normalize();			 
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Helpchild");	
			for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			   Node nNode = nList.item(temp);
			   if (nNode.getNodeType() == Node.ELEMENT_NODE) {	 
			      Element eElement = (Element) nNode;	 
			      String Url =  getTagValue("Url", eElement);
			     
			      String Filename = getTagValue("Filename", eElement);
			      String Foldermapping =  getTagValue("Foldermapping", eElement);
			     // if(Url.equals(requestpath)){
			    	  request.setAttribute("Foldermapping", Foldermapping);
			    	  request.setAttribute("Filename", Filename);
			    	  request.setAttribute("Url",Url); 
			    	  if(Url.equalsIgnoreCase(requestpath)){
					      break;
			    	  }    
			    

			     // }		 
		            
	 
			   }
			   
			}


			
			
		}
		catch(Exception e){
			log.debug("Exception" + e);
		}
		finally{
			chain.doFilter(request, response);

		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		context= config.getInitParameter("context");
		
		
	}
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();	 
	        Node nValue = (Node) nlList.item(0);	 
		return nValue.getNodeValue();
	  }


}
