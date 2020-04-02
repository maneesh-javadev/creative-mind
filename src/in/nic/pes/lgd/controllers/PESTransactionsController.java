package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.EntityTransactionsNew;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.service.PESTransactionService;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exception.UserAuthException;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;


@Controller
public class PESTransactionsController { // NO_UCD (unused code)
	
	@Autowired
	PESTransactionService pesTransactions;
	
	private Integer stateCode = null;
	
	/**
	 * Get state code object from session 
	 * @param session
	 */
	private void init(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
	}
	
	@RequestMapping(value = "/viewLGDTrans", method = RequestMethod.GET)
	public ModelAndView viewLgdTrans(HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView mview = null;
		try{
			init(session);
			if(stateCode == null){
				throw new UserAuthException();
			}
	    	List<EntityTransactionsNew> transactions = pesTransactions.getLGDTransactions(stateCode);
	    	model.addAttribute("searchForm", new EntityTransactionsNew());
	    	model.addAttribute("lgdTransList", transactions);
	    	mview = new ModelAndView("pes_transactions");
	    	
		}catch(Exception ex) {	   		    	
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, ex);
			mview = new ModelAndView(redirectPath);
		}
		return mview;
	}
	
	
	@RequestMapping(value = "/viewLGDTrans", method = RequestMethod.POST)
	public ModelAndView updateLgdTrans(HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView mview = null;
		try{
			init(session);
			if(stateCode == null){
				throw new UserAuthException();
			}
	    	String updatedetails = request.getParameter("scheduleDates");
	    	
	    	if(updatedetails == null || updatedetails.equals("")){
				throw new UserAuthException();
			}
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String[] updationRecords = updatedetails.split("\\@@");
			for(String record : updationRecords){
				String[] columns = record.split("\\|");
				String datecol = columns[0];
				Integer trnsId =  Integer.parseInt(columns[1]);
				
				EntityTransactionsNew trnsObj = pesTransactions.getEntityTransactionById(trnsId);
				if(trnsObj != null){
					trnsObj.setStatusFlg("S");
					
					if(datecol != null && !"".equals(datecol)){
						java.util.Date parsedDate = dateFormat.parse(datecol);
						trnsObj.setScheduledDate(parsedDate);
					}
					
					trnsObj.setTaskCreatedBy(BigInteger.valueOf(123));
					trnsObj.setTaskCreatedOn(new Date());
					pesTransactions.update(trnsObj, true);
				}
			}
	    	
			List<EntityTransactionsNew> transactions = pesTransactions.getLGDTransactions(stateCode);
	    	model.addAttribute("searchForm", new EntityTransactionsNew());
	    	model.addAttribute("lgdTransList", transactions);
	    	mview = new ModelAndView("pes_transactions");
	    	
		}catch(Exception ex) {	   		    	
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, ex);
			mview = new ModelAndView(redirectPath);
		}
		return mview;
	}

}
