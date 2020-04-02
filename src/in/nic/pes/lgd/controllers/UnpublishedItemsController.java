/** 
 * Unsued Code comment for web application secuirity audit reason  24Nov2016
 */

package in.nic.pes.lgd.controllers;

/*import in.nic.pes.lgd.bean.UnpublishedItems;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;
*/

//@Controller
public class UnpublishedItemsController { // NO_UCD (unused code)
	
	/*private static final Logger log = Logger.getLogger(UnpublishedItemsController.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/viewUnpublishedItems", method=RequestMethod.GET)
	public ModelAndView viewUnpublishedItemsGet(Model model,HttpServletRequest request, 
						@RequestParam(value="idView", required=false)String idView,
						@RequestParam(value="idDel", required=false)String idDel,
						@RequestParam(value="idPub", required=false)String idPub,
						@ModelAttribute("viewUnpublishedItems") UnpublishedItems unpublishedItems)
	{
		ModelAndView mav=null;
		String pagePath=null;
		String xmlPath=null;
		Session session=null;
		int size=0;
		try{
		session=sessionFactory.openSession();
		if(idDel!=null && Integer.parseInt(idDel)>0){
			xmlPath=session.createSQLQuery("select u.item_xml_path from unpublished_items u inner join page_link p " +
					"on u.item_page_link_code=p.page_link_code where u.item_code=" + idDel).list().get(0).toString();
			removeFile(xmlPath);
			// call delete method
			session.createSQLQuery("update unpublished_items set isactive=false where item_code="+idDel).executeUpdate();
		}
	
		if(idView!=null && Integer.parseInt(idView)>0){
			
			pagePath=session.createSQLQuery("select p.page_link_path from unpublished_items u inner join page_link p " +
					"on u.item_page_link_code=p.page_link_code where u.item_code=" + idView).list().get(0).toString();
			xmlPath=session.createSQLQuery("select u.item_xml_path from unpublished_items u inner join page_link p " +
					"on u.item_page_link_code=p.page_link_code where u.item_code=" + idView).list().get(0).toString();
			mav=new ModelAndView("redirect:" + pagePath + "?xmlPath="+xmlPath);
			return mav;
		}
		
		List<UnpublishedItems> uList=new ArrayList<UnpublishedItems>();
		try {
			uList=session.createQuery("from UnpublishedItems where isactive=true order by itemCode").list();
			size=Integer.parseInt(session.createSQLQuery("select count(item_Code) from Unpublished_Items " +
					"where isactive=true").list().get(0).toString());
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		}
		model.addAttribute("unpublished", uList);
		model.addAttribute("size", size);
		mav=new ModelAndView("unpublishedItemsList");
		//session.close();
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mav;
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/viewUnpublishedItems", method=RequestMethod.POST)
	public ModelAndView viewUnpublishedItems(Model model,HttpServletRequest request, 
						@RequestParam(value="idView", required=false)String idView,
						@RequestParam(value="idDel", required=false)String idDel,
						@RequestParam(value="idPub", required=false)String idPub,
						@ModelAttribute("viewUnpublishedItems") UnpublishedItems unpublishedItems)
	{
		ModelAndView mav=null;
		try{
		Session session=sessionFactory.openSession();
		session.createSQLQuery("update unpublished_items set isactive=false where item_code in("+
										unpublishedItems.getItemDescription()+")").executeUpdate();
		List<UnpublishedItems> uList=new ArrayList<UnpublishedItems>();
		try {
			uList=session.createQuery("from UnpublishedItems where isactive=true order by itemCode").list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		model.addAttribute("unpublished", uList);
		mav=new ModelAndView("unpublishedItemsList");
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		
		return mav;
	}
	
	public boolean removeFile(String fileName)
	{
		File file = new File(fileName);
		boolean success=false;
		try{
			success = file.delete();
		
		if (!success)
			System.out.println("Deletion failed.");
		else
			System.out.println("File deleted.");
		}
		catch(Exception e)
		{
			//log.debug("Exception" + e);
			success=false;
		}
		return success;
	}*/
		 
}
