package in.nic.pes.lgd.controllers;

import in.nic.pes.common.menu.pojo.UserSelection;
import in.nic.pes.lgd.bean.DRAFTWARDCOVERAGE;
import in.nic.pes.lgd.bean.LocalGovtBodyWard;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.WardCoverageDetail;
import in.nic.pes.lgd.bean.localbodywardtemp;
import in.nic.pes.lgd.forms.StateForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.forms.WardForm;
import in.nic.pes.lgd.service.LocalGovtSetupService;
import in.nic.pes.lgd.service.WardService;
import in.nic.pes.lgd.utils.ApplicationConstant;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class WardController { // NO_UCD (unused code)
	
	private static final Logger log = Logger.getLogger(WardController.class);
	
	@Autowired
	private WardService wardService;
	
	@Autowired
	private LocalGovtSetupService localGovtSetupService;
	
	private Integer stateCode;
	private Integer districtCode;
	public Integer userCode;
	
	private static final String ADD_AND_MODIFY_WARD_COVERAGE = "addnModifyWardCoverage"; 
	
	private static final String MANAGE_WARD = "manageWard"; 
	
	

	public void init(HttpSession session) throws Exception {
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		if(sessionObject!=null){
			stateCode = sessionObject.getStateId();
			districtCode = sessionObject.getDistrictCode();
			userCode = sessionObject.getUserId().intValue();
		}else{
			UserSelection selection=(UserSelection) session.getAttribute("USERS_SELECTION");
			stateCode = selection.getStateId();
			districtCode = selection.getDistrictCode();
			userCode = selection.getUserId().intValue();
		}
		
	}
	/**
	 * @author Maneesh Kumar
	 * @since 05Oct2015
	 * @param wardForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/createWardforPRI", method = RequestMethod.GET)
	public ModelAndView getLBHierarchyofPRI(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("createPRIWard");

		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_PANCHAYAT.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_PANCHAYAT.toString());
			model.addAttribute("showTable", false);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewWardPRIAndTRI", method = RequestMethod.POST)
	public ModelAndView viewWardList(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;

		try {

			init(session);
			Integer localBodyCode = null;
			if (wardForm.getLocalBodyLevelCodes() != null) {
				String[] lbCodeArray = wardForm.getLocalBodyLevelCodes().split(",");
				localBodyCode = Integer.parseInt(lbCodeArray[lbCodeArray.length - 1]);
			}
			if (localBodyCode != null) {
				Integer lblc=wardService.getlblcCodeofLocalbody(localBodyCode);
				model.addAttribute("lblc",lblc);
				wardForm.setOffset(0);
				wardForm.setLimit(25);
				setPublishProperties(localBodyCode,model,session,wardForm);
				mav=setHierarchyProperties(mav,model, stateCode, wardForm.getPanchayatType());
				if (("S").equals(wardForm.getStatus())) {
					mav = new ModelAndView("createPRIWard");
				} else if (("P").equals(wardForm.getStatus())) {
					model.addAttribute("listWardDetailstemp", wardService.getLocalbodyWardTempList(lblc));
					model.addAttribute("listWardDetailsdetails", wardService.getDeleteWardList(lblc));
					mav = new ModelAndView(MANAGE_WARD);
				}

			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);

		}
		return mav;
	}

	@RequestMapping(value = "/createWard", method = RequestMethod.POST)
	public ModelAndView createPanchayatWard(@ModelAttribute("wardForm") WardForm wardForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;

		try {
			init(session);
			@SuppressWarnings("unchecked")
			List<LocalGovtBodyWard> listWardDetails = (List<LocalGovtBodyWard>) session.getAttribute("wardlist");

			String arrayupdate = request.getParameter("formNominations");
			String arrayinsert = request.getParameter("newWardList");
			String array_DeleteWardData = request.getParameter("deleteWardList");

			ArrayList<String> wardnames = new ArrayList<String>();
			ArrayList<String> wardnumbers = new ArrayList<String>();
			
			Integer lblc = wardService.getlblcCodeofLocalbody(wardForm.getLocalBodyCode());

			if (!arrayupdate.equalsIgnoreCase("")) {
				String[] wards = arrayupdate.split("@@");

				if (wards.length > 0) {

					for (String warddetail : wards) {

						String[] warddetailfull = warddetail.split("\\|");
						Long wardListindex = Long.parseLong(warddetailfull[0]);
						for (int i = 0; i < listWardDetails.size(); i++) {
							if (Long.parseLong(listWardDetails.get(i).getWardCode().toString()) == wardListindex) {
								listWardDetails.remove(i);
							}
						}

						String wardName = warddetailfull[1];
						String wardNumber = warddetailfull[2];
						wardnames.add(wardName);
						wardnumbers.add(wardNumber);
					}
				}
			}

			if (!arrayinsert.equalsIgnoreCase("")) {
				String[] wards = arrayinsert.split("@@");

				if (wards.length > 0) {

					for (String warddetail : wards) {

						String[] warddetailfull = warddetail.split("\\|");

						String wardName = warddetailfull[0];
						String wardNumber = warddetailfull[1];
						wardnames.add(wardName);
						wardnumbers.add(wardNumber);

					}
				}
			}
			if (listWardDetails != null) {
				for (LocalGovtBodyWard ward : listWardDetails) {
					wardnames.add(ward.getWardNameInEnglish());
					wardnumbers.add(ward.getWardNumber());
				}

			}

			boolean checkduplicatenames = true;
			boolean checkduplicatenumber = true;
			checkduplicatenames = checkDuplicate(wardnames);
			checkduplicatenumber = checkDuplicate(wardnumbers);

			if (checkduplicatenames == false || checkduplicatenumber == false) {
				String aMessage = null;
				aMessage = "Duplicate values found No ward details affected";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				session.removeAttribute("wardlist");
				return mav;

			}

			List<localbodywardtemp> updateListWard = null;
			if (!arrayupdate.equalsIgnoreCase("")) {
				String[] wards = arrayupdate.split("@@");
				if (wards.length > 0) {
					updateListWard = new ArrayList<localbodywardtemp>();
					localbodywardtemp localbodyWardtempupdate = null;
					for (String warddetail : wards) {
						String wardNamelocal = "";
						String[] warddetailfull = warddetail.split("\\|");
						Integer wardListindex = Integer.parseInt(warddetailfull[0]);
						String wardName = warddetailfull[1];
						String wardNumber = warddetailfull[2];
						if (warddetailfull.length > 3)
							wardNamelocal = warddetailfull[3];

						List<LocalbodyWard> ListWard = wardService.getwardDetail(wardListindex);          

						if (ListWard != null && !ListWard.isEmpty()) {
							LocalbodyWard localbodyWard = ListWard.get(0);
							localbodyWardtempupdate = new localbodywardtemp();
							java.util.Date defaultValue = null;
						    Converter converter = new DateConverter(defaultValue);
							BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
						    beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);
						    //BeanUtils.copyProperties(dest, src);
							
							BeanUtils.copyProperties(localbodyWardtempupdate, localbodyWard);
							localbodyWardtempupdate.setWardNameEnglish(wardName);
							localbodyWardtempupdate.setWardNameLocal(wardNamelocal);
							localbodyWardtempupdate.setWardNumber(wardNumber);
							localbodyWardtempupdate.setLastupdated(new Date());
							localbodyWardtempupdate.setLastupdatedby((long) userCode);
							localbodyWardtempupdate.setWardVersion(localbodyWardtempupdate.getWardVersion());
							updateListWard.add(localbodyWardtempupdate);

						}
					}

				}

			}

			List<localbodywardtemp> insertListWard = null;
			if (!arrayinsert.equalsIgnoreCase("")) {
				String[] wards = arrayinsert.split("@@");
				if (wards.length > 0) {
					
					insertListWard = new ArrayList<localbodywardtemp>();
					localbodywardtemp localbodyWard = null;
					for (String warddetail : wards) {
						String wardNamelocal = "";
						String[] warddetailfull = warddetail.split("\\|");
						// Integer wardListindex =
						// Integer.parseInt(warddetailfull[0]);
						String wardName = warddetailfull[0];
						String wardNumber = warddetailfull[1];
						if (warddetailfull.length > 2)
							wardNamelocal = warddetailfull[2];
						localbodyWard = new localbodywardtemp();
						localbodyWard.setWardNameEnglish(wardName);
						localbodyWard.setWardNameLocal(wardNamelocal);
						localbodyWard.setWardNumber(wardNumber);
						localbodyWard.setEffectiveDate(new Date());
						localbodyWard.setLastupdated(new Date());
						localbodyWard.setLastupdatedby(Long.valueOf(userCode));
						localbodyWard.setCreatedon(new Date());
						localbodyWard.setCreatedby(userCode);
						localbodyWard.setLblc(lblc);
						insertListWard.add(localbodyWard);
					}

				}

			}

			List<localbodywardtemp> deleteListWard = null;
			if (!array_DeleteWardData.equalsIgnoreCase("")) {
				String[] wards = array_DeleteWardData.split("@@");
				if (wards.length > 0) {
					deleteListWard = new ArrayList<localbodywardtemp>();
					localbodywardtemp localbodyWardtempdelete = null;
					for (String warddetail : wards) {
						//String wardNamelocal = "";
						String[] warddetailfull = warddetail.split("\\|");
						Integer wardListindex = Integer.parseInt(warddetailfull[0]);
						//String wardName = warddetailfull[1];
						//String wardNumber = warddetailfull[2];
						/*if (warddetailfull.length > 3)
							wardNamelocal = warddetailfull[3];*/

						List<LocalbodyWard> listWard = wardService.getwardDetail(wardListindex);
						if (listWard != null && !listWard.isEmpty()) {
							localbodyWardtempdelete = new localbodywardtemp();
							LocalbodyWard localbodyWard = listWard.get(0);
							java.util.Date defaultValue = null;
						    Converter converter = new DateConverter(defaultValue);
							BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
						    beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);
							BeanUtils.copyProperties(localbodyWardtempdelete, localbodyWard);
							localbodyWardtempdelete.setLastupdated(new Date());
							localbodyWardtempdelete.setLastupdatedby((long) userCode);
							deleteListWard.add(localbodyWardtempdelete);

						}
					}

				}

			}

			
			List<localbodywardtemp> localbodyWardTempList =wardService.getLocalbodyWardTempList(lblc);
			List<String> existInDraft=new ArrayList<String>();
			
			Map<Integer,Integer> existWardCodeMap=new HashMap<Integer,Integer>();
			for(localbodywardtemp obj:localbodyWardTempList) {
				if(obj.getWardCode()!=null ) {
					existWardCodeMap.put(obj.getWardCode(), obj.getWardCode());
				}
			}
			
			
			if(updateListWard!=null && !updateListWard.isEmpty()) {
				for(localbodywardtemp obj:updateListWard) {
					if(existWardCodeMap.containsKey(obj.getWardCode()) ) {
						existInDraft.add(obj.getWardNameEnglish()+"("+obj.getWardCode()+")");
					}
				}
			}
		
			if(deleteListWard!=null && !deleteListWard.isEmpty()) {
				for(localbodywardtemp obj:deleteListWard) {
					if(existWardCodeMap.containsKey(obj.getWardCode()) ) {
						existInDraft.add(obj.getWardNameEnglish()+"("+obj.getWardCode()+")");
					}
				}
			}
			
			
			if(existInDraft.size()>0) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Ward "+existInDraft.toString()+" already Exist in Draft");
			}
			
			else {
				boolean resultFlag =wardService.saveTempWards(insertListWard, updateListWard, deleteListWard);

				if (resultFlag) {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Changes is saved in draft mode,please publish them.");

				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "problem when Changes is saved in draft mode.");
				}
			}
			

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewwardforPRI", method = RequestMethod.GET)
	public ModelAndView publishWardofPri(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView(MANAGE_WARD);

		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_PANCHAYAT.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_PANCHAYAT.toString());
			model.addAttribute("showTable", false);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/createWardforTraditional", method = RequestMethod.GET)
	public ModelAndView getLBHierarchyofTRI(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("createPRIWard");

		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_TRADITIONAL.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_TRADITIONAL.toString());
			model.addAttribute("showTable", false);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewwardforTraditional", method = RequestMethod.GET)
	public ModelAndView publishWardofTri(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView(MANAGE_WARD);

		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_TRADITIONAL.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_TRADITIONAL.toString());
			model.addAttribute("showTable", false);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	public void setPublishProperties(Integer localBodyCode, Model model, HttpSession session, WardForm wardForm) throws Exception {
	
		Object[] objectArray=wardService.getLocalbodyWardListbyLoclbodyCode(localBodyCode);
		@SuppressWarnings("unchecked")
		List<LocalGovtBodyWard> listWardDetails = (List<LocalGovtBodyWard>)objectArray[0];
		Integer counter = (Integer)objectArray[1];
		
		session.setAttribute("limit", wardForm.getLimit());
		boolean movenext = true;
		session.setAttribute("offset", wardForm.getOffset());
		session.setAttribute("lbCode", localBodyCode);
		session.setAttribute("counter", counter);
		if (listWardDetails != null && !listWardDetails.isEmpty()) {
			model.addAttribute("wardsize", listWardDetails.size());
		}
		model.addAttribute("wardList", listWardDetails);
		model.addAttribute("districtCode", districtCode);
		model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
		model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
		model.addAttribute("counter", counter);
		Integer movePointer = counter / wardForm.getLimit();
		movePointer = (counter % wardForm.getLimit() == 0) ? movePointer : movePointer + 1;
		if (wardForm.getOffset() == (movePointer - 1)) {
			movenext = false;
		} else {
			movenext = true;
		}
		model.addAttribute("movenext", movenext);
		model.addAttribute("wardCount", "Page " + (wardForm.getOffset() + 1) + " of " + (movePointer));
		model.addAttribute("wardList", listWardDetails);
		if (session.getAttribute("wardlist") != null) {
			session.setAttribute("wardlist", null);
		}
		if (session.getAttribute("wardlist") == null) {
			session.setAttribute("wardlist", listWardDetails);
		}
		model.addAttribute("viewPage", "abc");
		model.addAttribute("showTable", true);
	}

	@RequestMapping(value = "/publishWardAll", method = RequestMethod.POST)
	public ModelAndView publishWarAll(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView(MANAGE_WARD);
		try {
			init(session);
			try {
			boolean resultFlag = wardService.publishWards(wardForm);
			
			if (resultFlag) {
				model.addAttribute("message", "Ward List publihsed Successfully");
			} else {
				model.addAttribute("message", "Ward List publihsed Unsuccessfully");
			}
			}catch(Exception e) {
				model.addAttribute("message", e.getCause().getMessage());
			}
			
			
			Integer lblc=  wardService.getlblcCodeofLocalbody(wardForm.getLocalBodyCode());
			if (lblc != null) {
				wardForm.setOffset(0);
				wardForm.setLimit(25);
				if(wardForm.getPublishWardsList().length()>0) {
					wardForm.setPublishWardsList(null);
				}else if(wardForm.getDeleteWardsList().length()>0) {
					wardForm.setDeleteWardsList(null);
				}
				setPublishProperties(wardForm.getLocalBodyCode(),model,session,wardForm);
				mav=setHierarchyProperties(mav,model, stateCode, wardForm.getPanchayatType());
				model.addAttribute("listWardDetailstemp", wardService.getLocalbodyWardTempList(lblc));
				model.addAttribute("listWardDetailsdetails", wardService.getDeleteWardList(lblc));
				mav = new ModelAndView(MANAGE_WARD);
				

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	@RequestMapping(value = "/createWardforUrban", method = RequestMethod.GET)
	public ModelAndView createWardforUrban(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("createPRIWard");


		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_URBAN.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_URBAN.toString());
			model.addAttribute("showTable", false);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	public ModelAndView setHierarchyProperties(ModelAndView mav,Model model, Integer stateCode, String PANCHAYAT_TYPE) throws Exception {
		
		if(wardService.checkLocalbodtSetupbyState(stateCode, PANCHAYAT_TYPE)){
			if(PANCHAYAT_TYPE.equals(LocalBodyConstant.LB_URBAN.toString())){
				model.addAttribute("localBodyTypeList", wardService.getLBTypeDetailsList(stateCode, PANCHAYAT_TYPE));
			}else{
				model.addAttribute("lbTypeHierarchy", wardService.getLBHierarchyList(stateCode, PANCHAYAT_TYPE));
			}
			model.addAttribute("PANCHAYAT_TYPE",PANCHAYAT_TYPE);
		}else{
			 mav = new ModelAndView("success").addObject("msgid",getLocalbodySetupMessage(PANCHAYAT_TYPE));
		}
		
		return mav;
		
	}
	
	public String getLocalbodySetupMessage(String PANCHAYAT_TYPE) throws Exception{
		String message=null;
		if(PANCHAYAT_TYPE!=null && ("P").equals(PANCHAYAT_TYPE)){
			message="Panchayat Local Body  Setup does not define for this State";	
		}else if(PANCHAYAT_TYPE!=null && ("T").equals(PANCHAYAT_TYPE)){
			message="Traditional Local Body  Setup does not define for this State";	
		}else if(PANCHAYAT_TYPE!=null && ("U").equals(PANCHAYAT_TYPE)){
			message="Urban Local Body  Setup does not define for this State";	
		}
		return message;
	}
	
	@RequestMapping(value = "/viewwardforUrban", method = RequestMethod.GET)
	public ModelAndView viewwardforUrban(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView(MANAGE_WARD);

		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_URBAN.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_URBAN.toString());
			model.addAttribute("showTable", false);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean checkDuplicate(List list) {
		HashSet set = new HashSet();
		for (int i = 0; i < list.size(); i++) {
			boolean val = set.add(list.get(i));
			if (val == false) {
				return val;
			}
		}
		return true;
	}
	
	/**
	 * These Methods add for 'Add n Manage Ward Covergae '
	 * 
	 * @author Maneesh Kumar
	 * @since 01DEc2014
	 */

	@RequestMapping(value = "/selectWardCoverage", method = RequestMethod.GET)
	public ModelAndView selectWardCoveragePri(@ModelAttribute("wardForm") WardForm wardForm,Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("selectWardCoverage");;
		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_PANCHAYAT.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_PANCHAYAT.toString());
			model.addAttribute("showTable", false);
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/selectTRIWardCoverage", method = RequestMethod.GET)
	public ModelAndView selectWardCoverageTRI(@ModelAttribute("wardForm") WardForm wardForm,Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("selectWardCoverage");;
		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_TRADITIONAL.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_TRADITIONAL.toString());
			model.addAttribute("showTable", false);
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	@RequestMapping(value = "/selectURBANWardCoverage", method = RequestMethod.GET)
	public ModelAndView selectWardCoverageURBAN(@ModelAttribute("wardForm") WardForm wardForm,Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("selectWardCoverage");;
		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_URBAN.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_URBAN.toString());
			model.addAttribute("showTable", false);
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	
	@RequestMapping(value = "/selectWardCoverage", method = RequestMethod.POST)
	public ModelAndView selectWardCoveragePriPOST(@ModelAttribute("wardForm") WardForm wardForm,Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("selectWardCoverage");;
		try {
			init(session);
			Integer localBodyCode = null;
			if (wardForm.getLocalBodyLevelCodes() != null) {
				String[] lbCodeArray = wardForm.getLocalBodyLevelCodes().split(",");
				localBodyCode = Integer.parseInt(lbCodeArray[lbCodeArray.length - 1]);
			}
			
			Object[] objectArray = wardService.getBasicDetailofLocalbody(localBodyCode,wardForm.getPanchayatType());
		    if(objectArray!=null){
		    	Integer lblc=(Integer)objectArray[0];
		    	wardForm.setLblc(lblc);
		    	wardForm.setSelLevel(String.valueOf(objectArray[1]));
		    	wardForm.setURBAN_LEVEL(String.valueOf(objectArray[2]));
		    	
				List<LocalbodyWard> localbodyWardList = wardService.getWardDetails(lblc);
				model.addAttribute("showTable", true);
				if (localbodyWardList != null && !localbodyWardList.isEmpty()) {
					model.addAttribute("resultList", localbodyWardList);
					model.addAttribute("dataExists", true);
					model.addAttribute("lbCode", localBodyCode);
					
				} else {
					model.addAttribute("dataExists", false);
	
				}
				mav=setHierarchyProperties(mav,model, stateCode,wardForm.getPanchayatType());
		    }
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	

	@RequestMapping(value = "/addnModifyWardCoverage", method = RequestMethod.POST)
	public ModelAndView addnModifyWardCoverageGET(@ModelAttribute("wardForm") WardForm wardForm,Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			init(session);
			mav = new ModelAndView(ADD_AND_MODIFY_WARD_COVERAGE);
			setWardCoverageProperties(wardForm,model);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/saveWardCoverage", method = RequestMethod.POST)
	public ModelAndView addnModifyWardCoveragePost(@ModelAttribute("wardForm") WardForm wardForm,Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(ADD_AND_MODIFY_WARD_COVERAGE);
		try {
			init(session);
			boolean saveFlag=false;
			boolean errorMessage=false;
			Object selWardCoverageDistrictList[] = request.getParameterValues("wardCoverageDistrictList");
			Object selWardCoverageSubdistrictList[] = request.getParameterValues("wardCoverageSubdistrictList");
			Object selWardCoverageVillageList[] = request.getParameterValues("wardCoverageVillageList");
			
			Character Level = wardForm.getSelLevel() != null ? wardForm.getSelLevel().charAt(0) : null;
			if (Level == 'D' && selWardCoverageDistrictList != null || (Level == 'T'|| Level == 'I') && selWardCoverageSubdistrictList != null || Level == 'V' && selWardCoverageVillageList != null) {
				mav = new ModelAndView("success");
				try {
					 saveFlag = wardService.saveWardCoverageDetail(wardForm.getWardCode(), wardForm.getSelLevel(), selWardCoverageDistrictList, selWardCoverageSubdistrictList, selWardCoverageVillageList,userCode,wardForm.getPanchayatType());
					 
					}catch(Exception e) {
						 errorMessage=true;
						 model.addAttribute("msgid", e.getCause().getMessage());
					}
				
				if(!errorMessage) {
					
					String message = "Ward Coverage Save not successfully in Draft.";
					if (saveFlag)
						message = "Ward Coverage Save successfully in Draft.";
					mav.addObject("msgid", message);
				}
				
				

			} else {
				mav = new ModelAndView(ADD_AND_MODIFY_WARD_COVERAGE);
				setWardCoverageProperties(wardForm,model);
				model.addAttribute("errorMsg", "Please Select mandatory fields(*)");
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);

		}
		return mav;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void setWardCoverageProperties(WardForm wardForm,Model model)throws Exception{
		boolean isUrban=(wardForm.getPanchayatType().equals("U"));
		Object objArray[] = wardService.getWardCoverageDetail(wardForm.getLblc(), wardForm.getWardCode(), wardForm.getSelLevel(),isUrban);
		LocalbodyWard localbodyWard = (LocalbodyWard) objArray[0];
		List<WardCoverageDetail> localbodyCoverageDistrictList = (List<WardCoverageDetail>) objArray[1];
		List<WardCoverageDetail> wardCoverageDistrictList = (List<WardCoverageDetail>) objArray[2];
		List<WardCoverageDetail> localbodyCoverageSubdistrictList = (List<WardCoverageDetail>) objArray[3];
		List<WardCoverageDetail> wardCoverageSubdistrictList = (List<WardCoverageDetail>) objArray[4];
		List<WardCoverageDetail> localbodyCoverageVillageList = (List<WardCoverageDetail>) objArray[5];
		List<WardCoverageDetail> wardCoverageVillageList = (List<WardCoverageDetail>) objArray[6];
		model.addAttribute("localbodyWard", localbodyWard);
		model.addAttribute("localbodyCoverageDistrictList", localbodyCoverageDistrictList);
		model.addAttribute("wardCoverageDistrictList", wardCoverageDistrictList);
		model.addAttribute("localbodyCoverageSubdistrictList", localbodyCoverageSubdistrictList);
		model.addAttribute("wardCoverageSubdistrictList", wardCoverageSubdistrictList);
		model.addAttribute("wardCoverageVillageList", wardCoverageVillageList);
		model.addAttribute("localbodyCoverageVillageList", localbodyCoverageVillageList);
		model.addAttribute("PANCHAYAT_TYPE",wardForm.getPanchayatType());
		String selLevel=wardForm.getSelLevel();
		if(isUrban){
			selLevel="D";
			wardForm.setSelLevel(selLevel);
		}
		model.addAttribute("selLevel", selLevel);
			
		
	}

	/**
	 * End of 'Add n Manage Ward Covergae ' methods.
	 */
	
	
	@RequestMapping(value = "/selectDraftWardCoverage", method = RequestMethod.GET)
	public ModelAndView selectDraftWardCoveragePri(@ModelAttribute("wardForm") WardForm wardForm,Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("manageWardCoverage");;
		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_PANCHAYAT.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_PANCHAYAT.toString());
			model.addAttribute("showTable", Boolean.FALSE);
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	
	@RequestMapping(value = "/selectDraftWardCoverageTRI", method = RequestMethod.GET)
	public ModelAndView selectDraftWardCoverageTRI(@ModelAttribute("wardForm") WardForm wardForm,Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("manageWardCoverage");;
		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_TRADITIONAL.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_TRADITIONAL.toString());
			model.addAttribute("showTable", Boolean.FALSE);
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	
	@RequestMapping(value = "/selectDraftWardCoverageURBAN", method = RequestMethod.GET)
	public ModelAndView selectDraftWardCoverageURBAN(@ModelAttribute("wardForm") WardForm wardForm,Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("manageWardCoverage");;
		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_URBAN.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_URBAN.toString());
			model.addAttribute("showTable", Boolean.FALSE);
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	
	@RequestMapping(value = "/viewWardCoveragePRIAndTRI", method = RequestMethod.POST)
	public ModelAndView viewWardCoveragePRIAndTRI(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;

		try {
			init(session);
			Integer localBodyCode = null;
			if (wardForm.getLocalBodyLevelCodes() != null) {
				String[] lbCodeArray = wardForm.getLocalBodyLevelCodes().split(",");
				localBodyCode = Integer.parseInt(lbCodeArray[lbCodeArray.length - 1]);
			}
			if (localBodyCode != null) {	
				Integer lblc=wardService.getlblcCodeofLocalbody(localBodyCode);
				model.addAttribute("coveredWardLandregionList", wardService.fetchDraftWardCoverage(lblc));
				mav = new ModelAndView("manageWardCoverage");
				model.addAttribute("showTable", Boolean.TRUE);
			}
			mav=setHierarchyProperties(mav,model, stateCode,wardForm.getPanchayatType());

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);

		}
		return mav;
	}
	
	@RequestMapping(value = "/publishWardCoverage", method = RequestMethod.POST)
	public ModelAndView publishWardCoverage(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;

		try {
			init(session);
			mav = new ModelAndView("success");
			boolean resultFlag=false;
			boolean errorMessage=false;
			try {
			 resultFlag = wardService.publishWardCoverage(wardForm);
			}catch(Exception e) {
				model.addAttribute("msgid", e.getCause().getMessage());
				errorMessage=true;
			}if(!errorMessage) {
				if (resultFlag) {
					model.addAttribute("msgid", "Ward Coverage publihsed Successfully");
				} else {
					model.addAttribute("msgid", "Ward Coverage publihsed Unsuccessfully");
				}
			}
			
			
			
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);

		}
		return mav;
	}
	
	
	@RequestMapping(value = "/updateWardLocalNamePri", method = RequestMethod.GET)
	public ModelAndView updateWardLocalName( @ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session ) {
		ModelAndView mav = new ModelAndView("updateWardLocalNamePri");

		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_PANCHAYAT.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_PANCHAYAT.toString());
			model.addAttribute("showTable", false);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}	return mav;
		
	
	}
	
	@RequestMapping(value = "/viewWardLocalNamePRIAndTRI", method = RequestMethod.POST)
	public ModelAndView viewWardLocalNamePRIAndTRIList(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;

		try {

			init(session);
			Integer localBodyCode = null;
			if (wardForm.getLocalBodyLevelCodes() != null) {
				String[] lbCodeArray = wardForm.getLocalBodyLevelCodes().split(",");
				localBodyCode = Integer.parseInt(lbCodeArray[lbCodeArray.length - 1]);
			}
			if (localBodyCode != null) {
				Integer lblc=wardService.getlblcCodeofLocalbody(localBodyCode);
				model.addAttribute("lblc",lblc);
				wardForm.setOffset(0);
				wardForm.setLimit(25);
				setPublishProperties(localBodyCode,model,session,wardForm);
				mav=setHierarchyProperties(mav,model, stateCode, wardForm.getPanchayatType());
				if (("S").equals(wardForm.getStatus()) &&  ("P").equals( wardForm.getPanchayatType())) {
					mav = new ModelAndView("updateWardLocalNamePri");
				} 
				else if(("S").equals(wardForm.getStatus()) && ("U").equals( wardForm.getPanchayatType()))
				{
					mav = new ModelAndView("updateWardLocalNameUrban");

				}
				else if(("S").equals(wardForm.getStatus()) && ("T").equals( wardForm.getPanchayatType()))
				{
					mav = new ModelAndView("updateWardLocalNameTraditional");

				}
				
				/*else if (("P").equals(wardForm.getStatus())) {
					model.addAttribute("listWardDetailstemp", wardService.getLocalbodyWardTempList(lblc));
					model.addAttribute("listWardDetailsdetails", wardService.getDeleteWardList(lblc));
					mav = new ModelAndView(MANAGE_WARD);
				}*/

			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);

		}
		return mav;
	}

	@RequestMapping(value = "/updateWardLocalNameUrban", method = RequestMethod.GET)
	public ModelAndView updateWardLocalNameUrban(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("updateWardLocalNameUrban");


		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_URBAN.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_URBAN.toString());
			model.addAttribute("showTable", false);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	@RequestMapping(value = "/updateWardLocalNameTraditional", method = RequestMethod.GET)
	public ModelAndView updateWardLocalNameTraditional(@ModelAttribute("wardForm") WardForm wardForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("updateWardLocalNameTraditional");

		try {
			init(session);
			wardForm.setPanchayatType(LocalBodyConstant.LB_TRADITIONAL.toString());
			mav=setHierarchyProperties(mav,model, stateCode, LocalBodyConstant.LB_TRADITIONAL.toString());
			model.addAttribute("showTable", false);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		
		}return mav;
		}
	
	@RequestMapping(value = "/updateLocalNameOfWard", method = RequestMethod.POST)
	public ModelAndView updateLocalNameOfWard(@ModelAttribute("wardForm") WardForm wardForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView 	mav = new ModelAndView("success");
	    ModelAndView mavHome = new ModelAndView();

		try {
			init(session);
			@SuppressWarnings("unchecked")
			List<LocalGovtBodyWard> listWardDetails = (List<LocalGovtBodyWard>) session.getAttribute("wardlist");

			String arrayupdate = request.getParameter("formNominations");
			if(arrayupdate != null && arrayupdate.length() > 0)
			{
				arrayupdate = arrayupdate.substring(0, arrayupdate.length() - 1);
			
			
			

			boolean resultFlag = wardService.saveLocalNameOfWard(arrayupdate ,userCode);
			
			if (resultFlag) {
				mavHome = new ModelAndView("success");
				mavHome.addObject("msgid", "Ward Local Name Update successfully");
			}
			} 
			else
			{
				mavHome = new ModelAndView("success");
				mavHome.addObject("msgid", "You have not done any changes");
			}
		} catch (Exception e) {
			 IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		      String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", Integer.valueOf(1), e);
		      return new ModelAndView(redirectPath);
		}

		return mavHome;
	}
	
	@RequestMapping(value = "/viewDraftWardCoverage")
	public ModelAndView viewDraftWardCoverage( HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("DRAFT_WARD_COVERAGE");
		String wardCode = request.getParameter("ward_code");
		String wardVersion= request.getParameter("ward_version");

		model.addAttribute("completedCoverageDetailsDraft",wardService.getDraftWardCoverageDetails(Integer.parseInt(wardCode),Integer.parseInt(wardVersion),Boolean.TRUE ));
		model.addAttribute("completedCoverageDetails",wardService.getDraftWardCoverageDetails(Integer.parseInt(wardCode),Integer.parseInt(wardVersion),Boolean.FALSE ));
		return mav;
		
	}
	
}
