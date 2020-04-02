package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GetLandRegionWise;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.common.EsapiEncoder;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.StandardCodeDataForm;
import in.nic.pes.lgd.forms.StandardCodeForm;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.AssemblyService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.LocalGovtSetupService;
import in.nic.pes.lgd.service.MapService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.validator.EditStandardCodeValidator;
import in.nic.pes.lgd.validator.StandardCodesValidator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.entities.LBAttributes;
import com.cmc.lgd.localbody.services.LocalBodyService;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class StandardCodeController { // NO_UCD (unused code)
	private static final Session Session = null;

	@Autowired
	DistrictService districtService;

	@Autowired
	private SubDistrictService subdistrictService;

	@Autowired
	LocalGovtBodyService localGovtBodyService;

	@Autowired
	VillageService villageService;

	@Autowired
	MapService MapService;

	@Autowired
	GovernmentOrderService governmentOrderService;

	@Autowired
	AssemblyService AssemblyService;

	@Autowired
	StateService stateService;

	@Autowired
	LocalGovtSetupService localGovtSetupService;
	
	@Autowired
	private LocalBodyService localBodyService;
	// int stateCode = 1;

	private Integer stateCode;

	private Integer districtCode;

	private Long userId;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId();
		binder.setAutoGrowCollectionLimit(5000);
	}
	
	@RequestMapping(value = "/standard_Code", method = RequestMethod.GET)
	public ModelAndView showform(HttpSession httpSession, @ModelAttribute("Standard_Update") StandardCodeForm standardCodeForm, BindingResult bindingResult, Model model, HttpServletRequest request)
	{
		String localGovtListFlag = "";
		return this.showformCommon(httpSession, standardCodeForm, bindingResult, model, request,localGovtListFlag);
	}
	
	@RequestMapping(value = "/standard_Code_Block", method = RequestMethod.GET)
	public ModelAndView showformStandardCodeB(HttpSession httpSession, @ModelAttribute("Standard_Update") StandardCodeForm standardCodeForm, BindingResult bindingResult, Model model, HttpServletRequest request)
	{
		String localGovtListFlag = "B";
		return this.showformCommon(httpSession, standardCodeForm, bindingResult, model, request,localGovtListFlag);
	}
	
	@RequestMapping(value = "/standard_Code_Admin", method = RequestMethod.GET)
	public ModelAndView showformStandardCodeA(HttpSession httpSession, @ModelAttribute("Standard_Update") StandardCodeForm standardCodeForm, BindingResult bindingResult, Model model, HttpServletRequest request)
	{
		String localGovtListFlag = "A";
		return this.showformCommon(httpSession, standardCodeForm, bindingResult, model, request,localGovtListFlag);
	}
	
	@RequestMapping(value = "/standard_Code_Panchayat", method = RequestMethod.GET)
	public ModelAndView showformStandardCodeP(HttpSession httpSession, @ModelAttribute("Standard_Update") StandardCodeForm standardCodeForm, BindingResult bindingResult, Model model, HttpServletRequest request)
	{
		String localGovtListFlag = "P";
		List<GetLocalGovtSetup> getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		try {
			getLocalGovtSetupList = localGovtSetupService.getLocalbodyDetail(stateCode,localGovtListFlag);
			model.addAttribute("getLocalGovtSetupList", getLocalGovtSetupList);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
		
		return this.showformCommon(httpSession, standardCodeForm, bindingResult, model, request,localGovtListFlag);
	}
	
	@RequestMapping(value = "/standard_Code_Urban", method = RequestMethod.GET)
	public ModelAndView showformStandardCodeU(HttpSession httpSession, @ModelAttribute("Standard_Update") StandardCodeForm standardCodeForm, BindingResult bindingResult, Model model, HttpServletRequest request)
	{
		String localGovtListFlag = "U";
		List<GetLocalGovtSetup> getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		try {
			getLocalGovtSetupList = localGovtSetupService.getLocalbodyDetail(stateCode,localGovtListFlag);
			model.addAttribute("getLocalGovtSetupList", getLocalGovtSetupList);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
		
		return this.showformCommon(httpSession, standardCodeForm, bindingResult, model, request,localGovtListFlag);
	}

	@RequestMapping(value = "/standard_Code_Territorial", method = RequestMethod.GET)
	public ModelAndView showformStandardCodeT(HttpSession httpSession, @ModelAttribute("Standard_Update") StandardCodeForm standardCodeForm, BindingResult bindingResult, Model model, HttpServletRequest request)
	{
		String localGovtListFlag = "T";
		List<GetLocalGovtSetup> getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		try {
			getLocalGovtSetupList = localGovtSetupService.getLocalbodyDetail(stateCode,localGovtListFlag);
			model.addAttribute("getLocalGovtSetupList", getLocalGovtSetupList);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
		
		return this.showformCommon(httpSession, standardCodeForm, bindingResult, model, request,localGovtListFlag);
	}
	//@RequestMapping(value = "/standard_Code", method = RequestMethod.GET)
	public ModelAndView showformCommon(HttpSession httpSession, StandardCodeForm standardCodeForm, BindingResult bindingResult, Model model, HttpServletRequest request,String localGovtListFlag) {
		ModelAndView mav = new ModelAndView();
		List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
		List<GetLandRegionWise> districtPanchayatListDisUser = new ArrayList<GetLandRegionWise>();
		List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
		int distid = 0;
		
		try {
			
			if(("P").equals(localGovtListFlag) || ("T").equals(localGovtListFlag) || ("U").equals(localGovtListFlag)) {
				LBAttributes attributes = localBodyService.onLoadLocalBody(stateCode, localGovtListFlag, null); 
				if(attributes.getIsGovernmentOrderUpload() == null || attributes.getIsMapUpload() == null){
					StringBuilder msgid=new StringBuilder("");
							if(("P").equals(localGovtListFlag)) {
								msgid.append("Panchayat");
							}else if(("U").equals(localGovtListFlag)) {
								msgid.append("Urban");
							}else if(("T").equals(localGovtListFlag)) {
								msgid.append("Traditional");
							}
							msgid.append(" Localbody does't exist in your State");
					return new ModelAndView("success").addObject("msgid",msgid.toString());
				}
			}
			Integer entityCode=stateCode;
			
			
			
			String entityType="S";
			if ("P".equals(localGovtListFlag))
				mav = new ModelAndView("standardCodePanchayat");
			else if ("U".equals(localGovtListFlag))
				mav = new ModelAndView("standardCodeUrban");
			else if ("T".equals(localGovtListFlag))
				mav = new ModelAndView("standardCodeTerritorial");
			else if ("B".equals(localGovtListFlag))
				mav = new ModelAndView("standardCodeBlock");
			else if ("A".equals(localGovtListFlag))
				mav = new ModelAndView("standardCodeAdmin");
			else
				mav = new ModelAndView("standardCode");

			
			
			if (districtCode > 0) {
				List<Subdistrict> subdistrictList = districtService.getSubdistrictListbyDistrictCode(districtCode);
				model.addAttribute("subdistrictList", subdistrictList);
				entityCode=districtCode;
				entityType="D";
			}
			DistrictForm districtform = new DistrictForm();
			List<District> districtList = null;
			districtList = new ArrayList<District>();
			standardCodeForm.setStateCode(stateCode.toString());
			//List<GetLocalGovtSetup> getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
			//getLocalGovtSetupList = localGovtSetupService.getLocalbodyDetail(stateCode);
			districtList = districtService.getDistrictList(stateCode);
			model.addAttribute("districtList", districtList);
			//model.addAttribute("getLocalGovtSetupList", getLocalGovtSetupList);
			localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			if (localBodyTypelist.size() == 2) {
				model.addAttribute("Tiertype", 2);
				mav.addObject("Tiertype", 2);
			} else if (localBodyTypelist.size() > 2) {

				model.addAttribute("Tiertype", 3);
				mav.addObject("Tiertype", 3);
			}
			if (distid > 0) {
				districtPanchayatListDisUser = localGovtBodyService.getLandRegionWise(1, 'D', distid, "P");
				if (districtPanchayatListDisUser.size() == 0) {
					districtPanchayatListDisUser = localGovtBodyService.getLandRegionWise(1, 'D', distid, "T");
				}
				model.addAttribute("districtPanchayatList", districtPanchayatListDisUser);
			} else {
				if (stateCode != 34) {
					districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
				} else {
					districtPanchayatList = localGovtBodyService.getExistingLBListTwoTier(stateCode);
				}
				model.addAttribute("districtPanchayatList", districtPanchayatList);
			}
			mav.addObject("Standard_Update", districtform);
			
			mav.addObject("StandardCodes", new DistrictForm());
			if (distid > 0) {
				model.addAttribute("distuser", "distuser");
			} else {
				model.addAttribute("distuser", null);
			}
			model.addAttribute("entityCode",entityCode);
			model.addAttribute("entityType",entityType);
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/ModifyStandardCodes", method = RequestMethod.POST)
	public ModelAndView modifyform(HttpSession httpSession, @ModelAttribute("standardCodeForm") StandardCodeForm standardCodeForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mv = null;
		String statecode = null;
		int lbType = 0;
		boolean urbanFlag=false;
		String[] vc=null;
		String entityName = standardCodeForm.getEntityName();
		try {
			standardCodeForm.setEntity_code(stateCode);
			standardCodeForm.setEntityTpye("S");
			if (districtCode > 0) {
				standardCodeForm.setDistrictCode(districtCode.toString());
				standardCodeForm.setEntity_code(districtCode);
				standardCodeForm.setEntityTpye("D");
			}
			if (entityName.contains(":")) {
				vc= entityName.split(":");
				lbType = Integer.parseInt(vc[0]);
				if("U".equalsIgnoreCase(vc[3])){
					urbanFlag=true;
					standardCodeForm.setEntityTpye("U");
				}
				
				standardCodeForm.setStateCode(stateCode!=null?String.valueOf(stateCode):null);
		 	}

			StandardCodesValidator cValidator = new StandardCodesValidator();
			cValidator.validate(standardCodeForm, bindingResult);
			if (bindingResult.hasErrors()) {
				List<District> districtList = null;
				districtList = new ArrayList<District>();
				stateCode = Integer.parseInt(statecode);
				districtList = districtService.getDistrictList(stateCode);
				if("U".equalsIgnoreCase(vc[3])){
					urbanFlag=true;
					standardCodeForm.setEntityTpye("U");
				}
				model.addAttribute("districtList", districtList);
				mv = new ModelAndView("standardCode");
				
			} else {

				List<StandardCodeDataForm> standardCodeDataList = districtService.getStandardCode(standardCodeForm);
				mv = new ModelAndView("ModifyStandardCodes");
				standardCodeForm.setStandardCodeDataDetails(standardCodeDataList);
				model.addAttribute("SEARCH_StandardCodes_RESULTS_KEY", standardCodeDataList);
				mv.addObject("standardCodeForm", standardCodeForm);

				entityName = standardCodeForm.getEntityName();
				if (standardCodeDataList.size() == 0) {
					mv.addObject("recordlength", 0);
				}
				if (entityName.contains(":")) {
					model.addAttribute("entityName", "locabody");
					model.addAttribute("censuseCondition",urbanFlag);
				} else {
					model.addAttribute("entityName", standardCodeForm.getEntityName());
					model.addAttribute("censuseCondition",urbanFlag);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/UpdateVillageDetails", method = RequestMethod.POST)
	public ModelAndView modifyVillageData(HttpSession httpSession, @ModelAttribute("standardCodeForm") StandardCodeForm standardCodeForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		boolean urbanFlag=false;

		standardCodeForm.setUserId(userId.intValue());
		standardCodeForm.setStateCode(stateCode.toString());
		try {
			if(standardCodeForm.isEntityTypeFlag()){
				urbanFlag=true;
			}
			EditStandardCodeValidator editStandardCodeValidator = new EditStandardCodeValidator();
			editStandardCodeValidator.validate(standardCodeForm, bindingResult);

			if (bindingResult.hasErrors()) {
				mav = new ModelAndView("ModifyStandardCodes");
				model.addAttribute("SEARCH_StandardCodes_RESULTS_KEY", standardCodeForm.getStandardCodeDataDetails());
				model.addAttribute("entityName", standardCodeForm.getEntityName());
				mav.addObject("standardCodeForm", standardCodeForm);
				mav.addObject("censuseCondition", urbanFlag);
				return mav;
			}

			List<StandardCodeDataForm> standardCodesDetailsupdate = districtService.updateStandardCode(standardCodeForm);
			if (standardCodesDetailsupdate != null) {
				if (standardCodesDetailsupdate.size() > 0) {
					model.addAttribute("standardCodesDetailsupdate", standardCodesDetailsupdate);
					model.addAttribute("entityName", standardCodeForm.getEntityName());
				}
				mav = new ModelAndView("SuccessStandardCodes");
				mav.addObject("censuseCondition", urbanFlag);
			}


		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView("/ModifyStandardCodes");

			return mav;
		}

		
		return mav;
	}

	@RequestMapping(value = "/modify_Standard_Codes", method = RequestMethod.GET)
	public ModelAndView modifyVillage(@ModelAttribute("Standard_Update") VillageForm villageForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, @RequestParam("id") Integer VillageCode,
			@RequestParam(value = "disturb", required = false) String disturb) {
		ModelAndView mv = null;
		try {
			List<VillageDataForm> listSubdistrictDetails = subdistrictService.getVillageDetail(VillageCode);
			EsapiEncoder.encode(villageForm);
			mv = new ModelAndView("modify_standard_Code");
			model.addAttribute("size", listSubdistrictDetails.size());
			model.addAttribute("listSubdistrictDetails", listSubdistrictDetails);
			model.addAttribute("disturb", disturb);
			
			villageForm.setListVillageDetails(listSubdistrictDetails);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/modifyStandardCodeAction", method = RequestMethod.POST)
	public String modifySubdistrict(@ModelAttribute("Standard_Update") VillageForm modifySubDistrictCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb, HttpSession session) {
		ModelAndView mv = null;
		try {
			String viewSubdistrictdetail = null;
			mv = new ModelAndView("modify_standard_Code");
			
			villageService.modify(modifySubDistrictCmd, request, session);

			EsapiEncoder.encode(modifySubDistrictCmd);
			int villageCode = 0;
			int villageVersion = 0;

			List<VillageDataForm> listSubdistrict = new ArrayList<VillageDataForm>();
			listSubdistrict = modifySubDistrictCmd.getListVillageDetails();
			Iterator<VillageDataForm> itr = listSubdistrict.iterator();
			while (itr.hasNext()) {
				VillageDataForm element = (VillageDataForm) itr.next();
				villageCode = element.getVillageCode();
				villageVersion = element.getVillage_version();
			}

			if (disturb.equals("true")) {
				viewSubdistrictdetail = "redirect:viewResolveEntitiesinDisturbedState.htm?resolved=" + villageCode + "," + villageVersion;
			} else {
				viewSubdistrictdetail = "redirect:viewVillagemodify.htm?id=" + villageCode + "&type=V";
			}

			return viewSubdistrictdetail;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@RequestMapping(value = "/viewVillagemodify", method = RequestMethod.GET)
	public ModelAndView viewSubDistrictDetail(@ModelAttribute("Standard_Update") VillageForm modifySubDistrictCmd, Model model, HttpServletRequest request, @RequestParam("id") Integer subdistrictCode, @RequestParam("type") String type) {
		ModelAndView mv = null;
		try {
			List<VillageDataForm> listSubdistrictDetails = subdistrictService.getVillageDetail(subdistrictCode);
			EsapiEncoder.encode(modifySubDistrictCmd);
			mv = new ModelAndView("viewStandardDetailmodify");
			modifySubDistrictCmd.setListVillageDetails(listSubdistrictDetails);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	//Added By Sushma Singh 14 Oct 2019
	
	
	@RequestMapping(value = "/UpdateSubdistrictDetails", method = RequestMethod.POST)
	public ModelAndView modifySubdistrictData(HttpSession httpSession, @ModelAttribute("standardCodeForm") StandardCodeForm standardCodeForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		boolean urbanFlag=false;
		
		standardCodeForm.setUserId(userId.intValue());
		standardCodeForm.setStateCode(stateCode.toString());
		try {
			if(standardCodeForm.isEntityTypeFlag()){
				urbanFlag=true;
			}
			EditStandardCodeValidator editStandardCodeValidator = new EditStandardCodeValidator();
			editStandardCodeValidator.validate(standardCodeForm, bindingResult);

			if (bindingResult.hasErrors()) {
				mav = new ModelAndView("ModifyStandardCodes");
				model.addAttribute("SEARCH_StandardCodes_RESULTS_KEY", standardCodeForm.getStandardCodeDataDetails());
				model.addAttribute("entityName", standardCodeForm.getEntityName());
				mav.addObject("standardCodeForm", standardCodeForm);
				mav.addObject("censuseCondition", urbanFlag);
				return mav;
			}

			List<StandardCodeDataForm> standardCodesDetailsupdate = districtService.updateStandardCode(standardCodeForm);
			if (standardCodesDetailsupdate != null) {
				if (standardCodesDetailsupdate.size() > 0) {
					model.addAttribute("standardCodesDetailsupdate", standardCodesDetailsupdate);
					model.addAttribute("entityName", standardCodeForm.getEntityName());
				}
				mav = new ModelAndView("SuccessStandardCodes");
				mav.addObject("censuseCondition", urbanFlag);
			}
			

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView("/ModifyStandardCodes");
       }
		return mav;
	}
	
	

}
