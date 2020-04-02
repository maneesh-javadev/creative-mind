package in.nic.pes.common.interceptor;

import in.nic.pes.common.utils.ModelMapAndView;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HandlerInterceptor extends HandlerInterceptorAdapter
{
  private String themParamName;
  private String languagParamName;

  @SuppressWarnings("unchecked")
public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject, ModelAndView paramModelAndView)
    throws Exception
  {
    String str1 = paramHttpServletRequest.getParameter(this.themParamName);
    String str2 = paramHttpServletRequest.getParameter(this.languagParamName);
    HttpSession localHttpSession = paramHttpServletRequest.getSession(false);
    if (localHttpSession != null)
    {
    	if(paramModelAndView != null){
    		ModelMapAndView localModelMapAndView;
    	      if ((str1 != null) || (str2 != null)) {
    	    	  localModelMapAndView = (ModelMapAndView)localHttpSession.getAttribute("modelMapAndView");
    	    	  if(localModelMapAndView != null){
    	    		paramModelAndView.setViewName(localModelMapAndView.getViewName());
    	    		paramModelAndView.addAllObjects(localModelMapAndView.getModelMap());
    	    	  }  
    	      }
    	      else {
    	    	  	localModelMapAndView = new ModelMapAndView();
    	            @SuppressWarnings("rawtypes")
					Map localMap = paramModelAndView.getModel();
    	            String str3 = paramModelAndView.getViewName();
    	            localModelMapAndView.setModelMap(localMap);
    	            localModelMapAndView.setViewName(str3);
    	            localHttpSession.setAttribute("modelMapAndView", localModelMapAndView);
    	      }
    	}
    }
    super.postHandle(paramHttpServletRequest, paramHttpServletResponse, paramObject, paramModelAndView);
  }

  public String getThemParamName()
  {
    return this.themParamName;
  }

  public void setThemParamName(String paramString)
  {
    this.themParamName = paramString;
  }

  public String getLanguagParamName()
  {
    return this.languagParamName;
  }

  public void setLanguagParamName(String paramString)
  {
    this.languagParamName = paramString;
  }
}