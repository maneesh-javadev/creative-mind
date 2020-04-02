package in.nic.pes.lgd.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.WsUserRegistrationForm;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.ReactivateLocalBodyDAO;
import in.nic.pes.lgd.dao.impl.DistrictDAOImpl;
import in.nic.pes.lgd.dao.impl.ReactivateLocalBodyDAOImp;
import in.nic.pes.lgd.forms.InvalidateLocalBodyForm;
import in.nic.pes.lgd.response.DistrictResponse;
import in.nic.pes.lgd.response.InvalidatedVillageResponse;
import in.nic.pes.lgd.response.Response;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.ReactivateLocalBodyService;

@Controller
public class ReactivateLocalBodyController {
	
	private Integer stateCode;

	private Integer districtCode;

	private Long userId;
	
	@Autowired
	ReactivateLocalBodyService reactivateLocalBodyService;
	
	@Autowired
	ReactivateLocalBodyDAO reactivateLocalBodyDAO;
	
	@Autowired
	ReactivateLocalBodyDAOImp reactivateLocalBodyDAOImpl;
	
	@Autowired
	DistrictService districtService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
		/*SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));*/
	}
	
	private void setGlobalParams(HttpSession session){
		if(session.getAttribute("sessionObject")!=null){
			SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
			stateCode = sessionObject.getStateId();
			districtCode = sessionObject.getDistrictCode();
			userId = sessionObject.getUserId();
		}
	}
	
	/**
	 * @author sourabhrai
	 * @param createWsUser
	 * @param result
	 * @param httpSession
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reactivateLocalBody", method = RequestMethod.GET)
	public ModelAndView reactivateLocalBody(@ModelAttribute("createWsUser") WsUserRegistrationForm createWsUser, BindingResult result,  HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			Integer operationCode=null;
			List<Operations> operations= reactivateLocalBodyService.getInvalidateType();
			for(Operations obj:operations) {
				if(obj.getCategory()=='P') {
					operationCode=obj.getOperationCode();
				}
			}
			mav = new ModelAndView("reactivateInvalidatedLcalBody");
			mav.addObject("operations", operations);
			mav.addObject("operationCode", operationCode);
		}  catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	
	@RequestMapping(value = "/reactivateLocalBodyUrban", method = RequestMethod.GET)
	public ModelAndView reactivateLocalBodyUrban(@ModelAttribute("createWsUser") WsUserRegistrationForm createWsUser, BindingResult result,  HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			Integer operationCode=null;
			List<Operations> operations= reactivateLocalBodyService.getInvalidateType();
			for(Operations obj:operations) {
				if(obj.getCategory()=='U') {
					operationCode=obj.getOperationCode();
				}
			}
			mav = new ModelAndView("reactivateInvalidatedLcalBody");
			mav.addObject("operations", operations);
			mav.addObject("operationCode", operationCode);
		}  catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	@RequestMapping(value = "/reactivateLocalBody", method = RequestMethod.POST)
	public @ResponseBody List<InvalidateLocalBodyForm> reactivateLocalBodys(@RequestBody int operationCode,HttpSession httpSession, HttpServletRequest request) {
		List<Object> objects=null;
		List<InvalidateLocalBodyForm> bodyForms=new ArrayList<InvalidateLocalBodyForm>();
		try {
			objects=reactivateLocalBodyService.getInvalidatedLocalBody(operationCode, stateCode);
			for (Object object : objects) {
				InvalidateLocalBodyForm bodyForm=new InvalidateLocalBodyForm();
				Object[] dataObject = (Object[]) object;
				bodyForm.setLocalBodyCode((int)dataObject[0]);
				bodyForm.setLocalBodyTypeCode((int)dataObject[1]);
				bodyForm.setFlagCode((int)dataObject[2]);
				bodyForm.setDescription(dataObject[5]==null?"".toString():dataObject[5].toString());
				bodyForm.setLocalBodyName(dataObject[6].toString());
				bodyForm.setEffectiveDate((Date)dataObject[4]);
				bodyForms.add(bodyForm);
			}
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return bodyForms;
	}
	
	/**
	 * @author sourabhrai
	 * @param invalidateLocalBody
	 * @return
	 */
	@RequestMapping(value="/validateReactivation", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> validateReactivation(@RequestBody InvalidateLocalBodyForm invalidateLocalBody) {
			Map<String, Object> openView = null;
			try {
				invalidateLocalBody.setUserId(userId);
				openView = new HashMap<>();
				System.out.println("Starting Query Execution at :  " + new Date());
				List<Object> conditionalObjects = reactivateLocalBodyService.validateReactivation(invalidateLocalBody);
				System.out.println("Ended Query Execution at :  " + new Date());
				for (Object object : conditionalObjects) {
					Object[] dataObject = (Object[]) object;
					if("Condition Validated".equalsIgnoreCase(dataObject[3].toString())) {
						openView.put("governmentOrder", true);
						return openView;
					}
				}
				openView.put("exceptionModel", conditionalObjects);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return openView;
	}
	
	@RequestMapping(value="/submitGovernmentOrder", method=RequestMethod.POST)
	@ResponseBody
	public void createGovernmentOrderAndRevalidateGP(@RequestParam("file") List<CommonsMultipartFile> file,@RequestParam("governmentOrder") String governmentOrder) {
		try {
			System.out.println(governmentOrder);
			System.out.println("Submittion started at :  " + new Date());
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			InvalidateLocalBodyForm invalidateLocalBodyForm = gson.fromJson(governmentOrder, InvalidateLocalBodyForm.class);
			invalidateLocalBodyForm.setUserId(userId);
			invalidateLocalBodyForm.setFlagCode(91);
			System.out.println(invalidateLocalBodyForm);
			List<Object> updatedGP = reactivateLocalBodyDAO.validateReactivation(invalidateLocalBodyForm);
				if(!CollectionUtils.isEmpty(updatedGP)) {
					System.out.println("updatedGP object is not null");
					reactivateLocalBodyDAOImpl.submitGovernmentOrder(updatedGP, file);
				}else {
					System.out.println("updatedGP object is null");
				}
			System.out.println("Submittion ended at :  " + new Date());
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author sourabhrai
	 * @param createWsUser
	 * @param httpSession
	 * @param request
	 * @return
	 */
	//Reactivate villages
	@RequestMapping(value="/reactivateVillages", method=RequestMethod.GET)
	public ModelAndView reactivateVillages(@ModelAttribute("createWsUser") WsUserRegistrationForm createWsUser, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
			try {
				mav = new ModelAndView("reactivateInvalidatedVillages");
				mav.addObject("stateCode", stateCode);
			}catch(final Exception ex) {
				ex.printStackTrace();
			}
		return mav;
	}
	
	/**
	 * @author sourabhRai
	 * @param stateCode
	 * @return List<DistrictResponse>
	 */
	@RequestMapping(value="/fetchDistricts",method=RequestMethod.POST)
	@ResponseBody
	public List<DistrictResponse> fetchDistrict(@RequestBody int stateCode) {
		List<DistrictResponse> districtResponses = new ArrayList<>();
		try {
			List<District> districtsOfState = new ArrayList<>();
			if(districtCode != 0) { //for district user login
				districtsOfState = districtService.getDistrict(districtCode);
			}else {
				districtsOfState = districtService.getDistrictList(stateCode);
			}
			
			if(!CollectionUtils.isEmpty(districtsOfState)) {
				for (District district : districtsOfState) {
					DistrictResponse districtResponse = new DistrictResponse();
					districtResponse.setDistrictNameEnglish(district.getDistrictNameEnglish());
					districtResponse.setDistrictCode(district.getDistrictCode());
					districtResponse.setDlc(district.getDlc());
					districtResponses.add(districtResponse);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return districtResponses;
	}
	
	/**
	 * @author sourabhRai
	 * @param operationCode
	 * @param selectedDistrict
	 * @return List<InvalidatedVillageResponse>`
	 */
	@RequestMapping(value="/fetchInvalidatedVillages", method=RequestMethod.POST)
	@ResponseBody
	public List<InvalidatedVillageResponse> fetchInvalidatedVillageList(@RequestBody final int operationCode, @RequestParam final int selectedDistrict){
		List<InvalidatedVillageResponse> invalidatedVillageResponses = new ArrayList<>();
			try {
				List<Object> invalidatedVillages=reactivateLocalBodyService.getInvalidatedVillages(operationCode, stateCode, selectedDistrict);
				if(CollectionUtils.isEmpty(invalidatedVillages))
					return null;
				for (Object invalidatedVillage : invalidatedVillages) {
					InvalidatedVillageResponse invalidatedVillageResponse = new InvalidatedVillageResponse();
					Object[] invalidatedVillageObject = (Object[]) invalidatedVillage;
					invalidatedVillageResponse.setVillageCode((Integer)invalidatedVillageObject[0]);
					invalidatedVillageResponse.setVillageName(invalidatedVillageObject[1].toString());
//					invalidatedVillageResponse.setDistrictCode((Integer)invalidatedVillageObject[2]);
//					invalidatedVillageResponse.setDistrictName(invalidatedVillageObject[3].toString());
					invalidatedVillageResponse.setDescription(invalidatedVillageObject[2]==null?"".toString():invalidatedVillageObject[2].toString());
					invalidatedVillageResponse.setEffectiveDate((Date)invalidatedVillageObject[3]);
					invalidatedVillageResponses.add(invalidatedVillageResponse);
				}
				return invalidatedVillageResponses;
			}catch (Exception e) {
				e.printStackTrace();
			}
			return invalidatedVillageResponses;
	}
	
	
	/**
	 * @author sourabhRai
	 * @param invalidatedVillageResponse
	 * @param request
	 * @param httpServletResponse
	 */
	@RequestMapping(value="/performReactivationOfVillage",method=RequestMethod.POST)
	public @ResponseBody Response performReactivationOfVillage(@RequestBody final InvalidatedVillageResponse invalidatedVillageResponse,HttpServletRequest request, HttpServletResponse httpServletResponse) {
		Response response = new Response();
		try {	
			System.out.println(invalidatedVillageResponse);
			invalidatedVillageResponse.setUserId(userId);
			response = reactivateLocalBodyDAO.performReactivationOfVillage(invalidatedVillageResponse);
			/*if(response.getResponseCode()!=200){
				httpServletResponse.setStatus(500);
			}*/
//			List<Object> updatedGP = reactivateLocalBodyDAO.validateReactivation(null);
			return response;
		}catch (Exception e) {
			e.printStackTrace();
			response.setResponseMessage(e.getMessage());
			httpServletResponse.setStatus(500);
			return response;
		}
	}
}
