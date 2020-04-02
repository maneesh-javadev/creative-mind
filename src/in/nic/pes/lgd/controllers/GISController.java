package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.service.MapService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class GISController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(GISController.class);
	@Autowired
	@Qualifier("MapService")
	MapService mapService;
	/*
	 * method to return report for ATR submitted
	 */
	@RequestMapping(value = "/testMap", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody
	String getATRSubmittedMap(@RequestParam("inparam") String inparam,
							  @RequestParam("paramType") String paramType) {
		String stateCensusJsonString = new Gson().toJson(null);
		return stateCensusJsonString;
	}

	@RequestMapping(value = "/gisMapViewInModal", method = RequestMethod.GET)
	public String gisMap(HttpServletRequest request, HttpSession session, Model model) {
		try {
			
			return "config/GISMap";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	@RequestMapping(value = "/fetchURL", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getMapURL(@RequestParam("inputLevel") Integer inputLevel,
										  @RequestParam("localGovBodyType") String localGovBodyType,
										  @RequestParam("vpFlag") String vpFlag,
										  @RequestParam("stateCode") Integer stateCode) {
		log.info("Inside fetch URL method - execution started.");
		try{
			Object[] urlDetails = null;
			List<Object[]> map = mapService.mapConfigurations(inputLevel, localGovBodyType, vpFlag, stateCode);
			log.info("Fetching map URL details.");
			if(!map.isEmpty()){
				urlDetails = map.get(0);
			}
			log.info("Inside fetch URL method - execution completed.");
			return new Gson().toJson(urlDetails);
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Inside fetch URL method - exception occured.");
			return new Gson().toJson("ERROR : " + e.getMessage());
		}
	}
}
