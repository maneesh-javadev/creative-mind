package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.BlockHistory;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictHistory;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictHistory;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillageHistory;
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.ShiftBlockForm;
import in.nic.pes.lgd.forms.ShiftDistrictForm;
import in.nic.pes.lgd.forms.ShiftSubDistrictForm;
import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.BlockService;
import in.nic.pes.lgd.service.ComboFillingService;
import in.nic.pes.lgd.service.ConfigGovtOrderService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.ShiftService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.validator.ShiftBlockValidator;
import in.nic.pes.lgd.validator.ShiftDistrictValidator;
import in.nic.pes.lgd.validator.ShiftSubdistrictValidator;
import in.nic.pes.lgd.validator.ShiftVillageBlockValidator;
import in.nic.pes.lgd.validator.ShiftVillageSDValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class ShiftController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(ShiftController.class);

	@Autowired
	StateService stateService;

	@Autowired
	ShiftService shiftService;

	@Autowired
	DistrictService districtService;

	@Autowired
	VillageService villageService;

	@Autowired
	SubDistrictService subDistrictService;

	@Autowired
	BlockService blockService;

	@Autowired
	private GovernmentOrderService govtOrderService;

	@Autowired
	ShiftDistrictValidator shiftDistrictValidator;

	@Autowired
	ShiftSubdistrictValidator shiftSubdistrictValidator;

	@Autowired
	ShiftBlockValidator shiftBlockValidator;

	@Autowired
	ShiftVillageBlockValidator shiftVillageBlockValidator;

	@Autowired
	ShiftVillageSDValidator shiftVillageSDValidator;

	@Autowired
	ComboFillingService comboFillingService;
	@Autowired
	ConfigGovtOrderService configGovtOrderService;

	@Autowired
	EmailService emailService;

	private Integer stateCode;

	private Integer districtCode;

	private Integer userId;
	
	private String isCenterUser;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request,HttpSession session) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
			setGlobalParams(session);
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "label.lgd", "userId", 1, e);
		}
	}

	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
		isCenterUser=sessionObject.getIsCenterorstate();
	}

	@RequestMapping(value = "/shiftdistrict", method = RequestMethod.GET)
	public ModelAndView showShiftDistrictform(@ModelAttribute("shiftdistrict") ShiftDistrictForm shiftDistrictForm, HttpSession session, HttpServletRequest request) {

		int operationCode = 17;
		ModelAndView mav = null;
		try {
			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");
				session.removeValue("formbean");
			}
			
			
			// List<District> districtList=
			// districtService.getDistrictList(Integer.parseInt(statecode));

			shiftDistrictForm.setOperationCode(operationCode);
			Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, ' ');

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String message = hMap.get("message");
			if (govtOrderConfig != null) {

				mav = new ModelAndView("shiftdistrict");
				// mav.addObject("districtSourceList",districtList);
				mav.addObject("isCenterState", isCenterUser);
				mav.addObject("stateCode", stateCode);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("shiftdistrict", shiftDistrictForm);

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/shiftblock", method = RequestMethod.GET)
	public ModelAndView showShiftBlock(@ModelAttribute("shiftblock") ShiftBlockForm shiftblockForm, Model model, HttpSession session, HttpServletRequest request) {

		int operationCode = 42;
		ModelAndView mav = null;
		try {
			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");
				session.removeValue("formbean");
			}
			
			List<District> districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);

			shiftblockForm.setOperationCode(operationCode);
			Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, ' ');

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String message = hMap.get("message");
			if (govtOrderConfig != null) {

				mav = new ModelAndView("shiftblock");
				model.addAttribute("stateCode", stateCode);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("shiftblock", shiftblockForm);
				mav.addObject("districtList", districtList);

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/shiftSubDistrict", method = RequestMethod.GET)
	public ModelAndView showShiftSubDistrictform(@ModelAttribute("shiftSubDistrict") ShiftSubDistrictForm shiftsubDistrictForm, HttpSession session, HttpServletRequest request) {

		int operationCode = 0;
		
		ModelAndView mav;
		try {
			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");
				session.removeValue("formbean");
			}
			///////////// Handling central
			///////////// level//////////////////////////////////
			
			if (isCenterUser.equalsIgnoreCase("S")) {
			
				request.setAttribute("stateCode", stateCode);
				operationCode = 8;
			} else if (isCenterUser.equalsIgnoreCase("C")) {
				operationCode = 61;
			}
			mav = null;
			shiftsubDistrictForm.setOperationCode(operationCode);
			Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, ' ');

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String message = hMap.get("message");

			if (govtOrderConfig != null) {
				mav = new ModelAndView("shiftSubDistrict");

				List<District> distrinctlist = null;
				
				distrinctlist = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
				mav.addObject("distrinctlist", distrinctlist);
				mav.addObject("isCenterState", isCenterUser);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				// mav.addObject("shiftSubDistrict", shiftsubDistrictForm);

				if (isCenterUser.toUpperCase().equals("S")) {
					shiftsubDistrictForm.setStateNameEnglish(stateCode.toString());
					shiftsubDistrictForm.setStateNameEnglishTarget(stateCode.toString());
				}
				mav.addObject("shiftSubDistrict", shiftsubDistrictForm);
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
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

	@RequestMapping(value = "/shiftvillageblock", method = RequestMethod.GET)
	public ModelAndView showShiftVillageBlock(@ModelAttribute("shiftvillageblock") ShiftVillageForm shiftvillageForm, HttpSession session, HttpServletRequest request) {

		ModelAndView mav = null;
	
		int operationCode = 15;
		try {
			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");
				session.removeValue("formbean");
			}
			
			request.setAttribute("stateCode", stateCode);
			
			shiftvillageForm.setDistrictCode(districtCode);

			List<Block> blockList = blockService.getBlockList(districtCode);

			shiftvillageForm.setOperationCode(operationCode);
			shiftvillageForm.setOperation('B');
			Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, ' ');

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String message = hMap.get("message");
			// if (govtOrderConfig != null)
			if (govtOrderConfig != null) {

				mav = new ModelAndView("shiftvillageblock");
				List<District> distrinctlist = null;
				
				distrinctlist = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
				mav.addObject("distrinctlist", distrinctlist);
				mav.addObject("districtCode", districtCode);
				mav.addObject("blockSourceList", blockList);
				mav.addObject("blockTargetList", blockList);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("shiftvillageblock", shiftvillageForm);
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/shiftvillageSubDistrict", method = RequestMethod.GET)
	public ModelAndView showShiftVillageSubDistrict(@ModelAttribute("shiftvillageSubDistrict") ShiftVillageForm shiftVillageForm, Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		int operationCode = 0;

		try {
			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");
				session.removeValue("formbean");
			}
			///////////// Handling central
			///////////// level//////////////////////////////////

			
			if (isCenterUser.equalsIgnoreCase("S")) {
				operationCode = 13;
			} else if (isCenterUser.equalsIgnoreCase("C")) {
				operationCode = 62;
			}
			///////////// Handling central
			///////////// level//////////////////////////////////
		

			/// Handling District User/////
			
			shiftVillageForm.setDistrictCode(districtCode);

			List<District> districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);

			List<Subdistrict> subdistrictList = subDistrictService.getSubDistrictListForLocalbody(districtCode);

			shiftVillageForm.setOperationCode(operationCode);
			shiftVillageForm.setOperation('T');
			Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, ' ');

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String message = hMap.get("message");
			if (govtOrderConfig != null) {
				mav = new ModelAndView("shiftvillageSubDistrict");
				mav.addObject("isCenterState", isCenterUser);
				model.addAttribute("isCenterState", isCenterUser);
				mav.addObject("districtCode", districtCode);
				mav.addObject("districtSourceList", districtList);
				mav.addObject("districtTargetList", districtList);
				mav.addObject("subdistrictSourceList", subdistrictList);
				mav.addObject("subdistrictTargetList", subdistrictList);
				mav.addObject("stateCode", stateCode);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("shiftvillageSubDistrict", shiftVillageForm);

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
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

	@ModelAttribute("stateSourceList")
	public List<State> populateSourceStateList(HttpServletRequest request) {

		try {
			return stateService.getStateSourceList();
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}

	}

	@ModelAttribute("districtList")
	public List<District> getDistrictList(HttpSession session, HttpServletRequest request) {
		try {
			
			return districtService.getDistrictList(stateCode);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
	}

	// ShiftDistrict methods
	@RequestMapping(value = "/draftShiftDistrict", method = RequestMethod.POST)
	public ModelAndView draftShiftDistrict(@ModelAttribute("shiftdistrict") ShiftDistrictForm shiftDistrictForm, BindingResult result, SessionStatus status, HttpServletRequest request, HttpSession session, Model model) {
		ModelAndView mav = new ModelAndView("shiftdistrict");
		ModelAndView mv;
		try {

			String[] listDistNames = null;
			String[] listDistCodes = null;
			String distName = null;
			Integer distCode = 0;

			String contsourceStateName = null;
			String conttargetStateName = null;
			String contDistrictName = null;

			List<District> districtSourceList = new ArrayList<District>();
			shiftDistrictValidator.validate(shiftDistrictForm, result);
			if (result.hasErrors()) {
				mav.addObject("shiftdistrict", shiftDistrictForm);
				return mav;
			} else {
				if (shiftDistrictForm.getStateNameEnglish().contains(",")) {
					String[] stateName = shiftDistrictForm.getStateNameEnglish().split(",");
					shiftDistrictForm.setStateSName(stateName[0].toString());
					shiftDistrictForm.setStateDName(stateName[1].toString());

					List<State> contsourceState = shiftService.getStateNamebySourcestateID(shiftDistrictForm);
					if (contsourceState != null) {
						Iterator<State> stateItr = contsourceState.iterator();
						StringBuffer finalcontstate = new StringBuffer();

						while (stateItr.hasNext()) {
							finalcontstate.append(stateItr.next().getStateNameEnglish().trim());
						}
						contsourceStateName = finalcontstate.toString();

						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						shiftDistrictForm.setFinalsourceStateName(contsourceStateName);
					}

					List<State> contdestState = shiftService.getStateNamebyDeststateID(shiftDistrictForm);
					if (contdestState != null) {
						Iterator<State> statedestItr = contdestState.iterator();
						StringBuffer finalcontdesrstate = new StringBuffer();

						while (statedestItr.hasNext()) {
							finalcontdesrstate.append(statedestItr.next().getStateNameEnglish().trim());
						}
						conttargetStateName = finalcontdesrstate.toString();

						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						shiftDistrictForm.setFinaltargetStateName(conttargetStateName);
					}

					List<District> contDist = shiftService.getdistrictNamebyDistID(shiftDistrictForm);
					if (contDist != null) {
						Iterator<District> disttItr = contDist.iterator();
						StringBuffer finaldist = new StringBuffer();

						while (disttItr.hasNext()) {
							finaldist.append(disttItr.next().getDistrictNameEnglish().trim() + ",");
						}
						contDistrictName = finaldist.toString();
						contDistrictName = contDistrictName.substring(0, contDistrictName.length() - 1);

						shiftDistrictForm.setFinalDestName(contDistrictName);
					}

					districtSourceList = districtService.getDistrictList(Integer.parseInt(stateName[0].toString()));
				}
				districtSourceList.remove(shiftDistrictForm.getDistrictNameEnglish());

				Map<Integer, String> distCodeNameMap = new HashMap<Integer, String>();

				if (shiftDistrictForm.getDistrictName() != null && !shiftDistrictForm.getDistrictName().isEmpty()) {
					listDistNames = shiftDistrictForm.getDistrictName().split(",");
				}
				if (shiftDistrictForm.getDistrictNameEnglish() != null && !shiftDistrictForm.getDistrictNameEnglish().isEmpty()) {
					listDistCodes = shiftDistrictForm.getDistrictNameEnglish().split(",");
				}

				if (listDistNames != null) {
					for (int i = 0; i < listDistNames.length; i++) {
						if (listDistCodes != null && listDistCodes[i] != null && !listDistCodes[i].equals("")) {
							distCode = new Integer(listDistCodes[i]);
						}
						if (listDistNames != null && listDistNames[i] != null && !listDistNames[i].equals("")) {
							distName = listDistNames[i];
						}
						distCodeNameMap.put(distCode, distName);
					}
				}

				model.addAttribute("districtSourceList", districtSourceList);
				model.addAttribute("districtDestList", distCodeNameMap);
				session.setAttribute("formbean", shiftDistrictForm);
				mv = new ModelAndView("redirect:govtOrderCommon.htm");
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mv;
	}

	@RequestMapping(value = "/saveShiftDistrict", method = RequestMethod.POST)
	public ModelAndView shiftDistrict(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = null;
		String viewDistrictHistory = "";
		String getordercode = null;
		try {
			
			ShiftDistrictForm shiftDistrictForm = (ShiftDistrictForm) session.getAttribute("formbean");
			boolean saveSuccess = false;
			/*
			 * AddAttachmentHandler attachmentHandler = new
			 * AddAttachmentHandler(); AddAttachmentBean addAttachmentBean =
			 * null; List<AttachedFilesForm> metaInfoOfToBeAttachedFileList =
			 * null; GovernmentOrderForm govtForm = null; if
			 * (session.getAttribute("govtOrderForm") != null) { govtForm =
			 * (GovernmentOrderForm) session .getAttribute("govtOrderForm"); }
			 * 
			 * if (session.getAttribute("addAttachmentBean") != null) {
			 * addAttachmentBean = (AddAttachmentBean) session
			 * .getAttribute("addAttachmentBean"); String validateAttachment =
			 * attachmentHandler .validateAttachment(addAttachmentBean);
			 * metaInfoOfToBeAttachedFileList = attachmentHandler
			 * .getMetaDataListOfFiles(addAttachmentBean); }
			 * 
			 * if (metaInfoOfToBeAttachedFileList != null &&
			 * metaInfoOfToBeAttachedFileList.size() != 0) { saveSuccess =
			 * shiftService.shiftDistrict(shiftDistrictForm, govtForm,
			 * metaInfoOfToBeAttachedFileList, request); String saveAttachment =
			 * attachmentHandler .saveMetaDataINFileSystem(
			 * metaInfoOfToBeAttachedFileList, addAttachmentBean); }
			 */

			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");
			if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
				getordercode = shiftService.shiftDistrict(shiftDistrictForm, govtOrderForm, request, userId);
				String[] val = getordercode.split(",");

				if (getordercode != null) {
					if (session.getAttribute("validGovermentUpload") != null) {
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) session.getAttribute("validGovermentUpload");
						boolean insertGovtTableFlag = shiftService.saveDataInAttachShiftDistrict(govtOrderForm, shiftDistrictForm, validGovermentUpload, request.getSession(), Integer.parseInt(val[1]));
					}
				}
			} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
				getordercode = shiftService.shiftDistrictforTemplate(shiftDistrictForm, govtOrderForm, request, userId);
				String[] val = getordercode.split(",");

				// localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				if (getordercode != null) {
					if (session.getAttribute("validGovermentGenerateUpload") != null) {
						// GenerateDetails validGovermentGenerateUpload =
						// GenerateDetails.session.getAttribute("validGovermentGenerateUpload");
						boolean insertGovtTableFlag = shiftService.saveDataInAttachShiftGenerateDistrict(govtOrderForm, shiftDistrictForm, request.getSession(), Integer.parseInt(val[1]));
					}
				}

			}

			if (getordercode != null) {
				/*
				 * Iterator itr = getordercode.iterator(); while (itr.hasNext())
				 * { ShiftDistrict tranvillsdistCode = (ShiftDistrict)
				 * itr.next(); int t_code =
				 * tranvillsdistCode.getShift_district_fn(); char t_type='D';
				 * int flag; flag =emailService.sendEmail(t_code,t_type);
				 * if(flag==1) { log.debug("Mail is sent"); } }
				 */

				String[] val = getordercode.split(",");

				// localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				int t_code = Integer.parseInt(val[0]);
				char t_type = 'D';
				EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
				est.start();

				String updatedDistrictCode = null;

				String listdistrictCodes = shiftDistrictForm.getDistrictNameEnglish();
				if (listdistrictCodes.contains(",")) {
					String[] distrcitCodes = listdistrictCodes.split(",");
					for (int i = 0; i < distrcitCodes.length; i++) {
						if (distrcitCodes[i] != null) {
							if (updatedDistrictCode != null) {
								updatedDistrictCode = updatedDistrictCode + distrcitCodes[i].toString() + ":";
							} else {
								updatedDistrictCode = distrcitCodes[i].toString() + ":";
							}
						}
					}
				} else {
					updatedDistrictCode = listdistrictCodes;
				}

				String aMessage = "The Districts was shifted successfully";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);

				// viewDistrictHistory =
				// "redirect:viewDistrictHistoryforShift.htm?id=" +
				// updatedDistrictCode + "&type=S";

				session.removeAttribute("formbean");
				session.removeValue("formbean");
				session.removeAttribute("govtOrderForm");
				session.removeValue("govtOrderForm");
				session.removeAttribute("addAttachmentBean");
				session.removeValue("addAttachmentBean");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			// return redirectPath;
		}
		return mav;
	}

	@RequestMapping(value = "/viewDistrictHistoryforShift", method = RequestMethod.GET)
	public ModelAndView viewDistrictHistoryDetail(@ModelAttribute("districtForm") DistrictForm districtForm, Model model, @RequestParam("id") String listdistrictCodes, @RequestParam("type") String type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("view_districthistory");

		try {
			List<DistrictHistory> districtHistoryDetail = new ArrayList<DistrictHistory>();

			char districtNameEnglish = 'D';
			int districtCode = 0;
			if (listdistrictCodes.contains(":")) {

				String[] districtCodes = listdistrictCodes.split(":");

				for (int i = 0; i < districtCodes.length; i++) {

					if (districtCodes[i] != null) {
						districtCode = Integer.parseInt(districtCodes[i].toString());

						List<DistrictHistory> districtHistoryDetailperCode = districtService.getDistrictHistoryDetail(districtNameEnglish, districtCode);

						districtHistoryDetail.addAll(districtHistoryDetailperCode);

					}
				}
			} else {
				districtCode = Integer.parseInt(listdistrictCodes);
				List<DistrictHistory> districtHistoryDetailperCode = districtService.getDistrictHistoryDetail(districtNameEnglish, districtCode);

				districtHistoryDetail.addAll(districtHistoryDetailperCode);

			}

			model.addAttribute("districtHistory", districtHistoryDetail);
			districtForm.setDistrictHistoryDetail(districtHistoryDetail);
			mav.addObject("districtForm", districtForm);
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// ShiftSubdistrict methods

	@RequestMapping(value = "/draftShiftSubDistrict", method = RequestMethod.POST)
	public ModelAndView draftShiftSubDistrict(@ModelAttribute("shiftSubDistrict") ShiftSubDistrictForm shiftsubDistrictForm, BindingResult result, SessionStatus status, HttpServletRequest request, HttpSession session, Model model) {

		ModelAndView mav = new ModelAndView("shiftSubDistrict");
		ModelAndView mv = null;
		try {
			String[] listSubdistNames = null;
			String[] listSubdistCodes = null;

			String contsourceStateName = null;
			String conttargetStateName = null;
			String contsourceDistName = null;
			String conttargetDistName = null;
			String contSubDistrictName = null;

			String subdistName = null;
			Integer subdistCode = 0;
			List<District> districtSourceList = new ArrayList<District>();
			List<Subdistrict> subDistSourceList = new ArrayList<Subdistrict>();

			
			shiftSubdistrictValidator.validate(shiftsubDistrictForm, result, stateCode);
			if (result.hasErrors()) {
				mav.addObject("shiftSubDistrict", shiftsubDistrictForm);
				return mav;
			} else {
				if (shiftsubDistrictForm.getStateNameEnglish().contains(",")) {
					String[] stateName = shiftsubDistrictForm.getStateNameEnglish().split(",");
					shiftsubDistrictForm.setStateSName(stateName[0].toString());
					shiftsubDistrictForm.setStateDName(stateName[1].toString());

					List<State> contsourceState = shiftService.getsourcestateNamebyStateID(shiftsubDistrictForm);
					if (contsourceState != null) {
						Iterator<State> stateSourceItr = contsourceState.iterator();
						StringBuffer finalsourcestate = new StringBuffer();

						while (stateSourceItr.hasNext()) {
							finalsourcestate.append(stateSourceItr.next().getStateNameEnglish().trim());
						}
						contsourceStateName = finalsourcestate.toString();

						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						shiftsubDistrictForm.setFinalsourceStateName(contsourceStateName);
					}

					List<State> conttargetState = shiftService.gettargetstateNamebyStateID(shiftsubDistrictForm);
					if (conttargetState != null) {
						Iterator<State> stateTargetItr = conttargetState.iterator();
						StringBuffer finaltargetstate = new StringBuffer();

						while (stateTargetItr.hasNext()) {
							finaltargetstate.append(stateTargetItr.next().getStateNameEnglish().trim());
						}
						conttargetStateName = finaltargetstate.toString();

						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						shiftsubDistrictForm.setFinaltargetStateName(conttargetStateName);
					}

					districtSourceList = districtService.getDistrictList(Integer.parseInt(stateName[0].toString()));
				}

				if (shiftsubDistrictForm.getStateNameEnglish() != null) {
					List<State> contsourceState = shiftService.getsourcestateNamebyStateIDforSD(shiftsubDistrictForm);
					if (contsourceState != null) {
						Iterator<State> stateSourceItr = contsourceState.iterator();
						StringBuffer finalsourcestate = new StringBuffer();

						while (stateSourceItr.hasNext()) {
							finalsourcestate.append(stateSourceItr.next().getStateNameEnglish().trim());
						}
						contsourceStateName = finalsourcestate.toString();

						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						shiftsubDistrictForm.setFinalsourceStateName(contsourceStateName);
					}
					if (shiftsubDistrictForm.getStateNameEnglishTarget() != null) {
						List<State> conttargetState = shiftService.gettargetstateNamebyStateIDforSD(shiftsubDistrictForm);
						if (conttargetState != null) {
							Iterator<State> stateTargetItr = conttargetState.iterator();
							StringBuffer finaltargetstate = new StringBuffer();

							while (stateTargetItr.hasNext()) {
								finaltargetstate.append(stateTargetItr.next().getStateNameEnglish().trim());
							}
							conttargetStateName = finaltargetstate.toString();

							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							shiftsubDistrictForm.setFinaltargetStateName(conttargetStateName);
						}
					}
				}

				if (shiftsubDistrictForm.getDistrictNameEnglish() != null && !shiftsubDistrictForm.getDistrictNameEnglish().isEmpty()) {
					if (shiftsubDistrictForm.getDistrictNameEnglish().contains(",")) {
						String[] districtName = shiftsubDistrictForm.getDistrictNameEnglish().split(",");
						shiftsubDistrictForm.setDistrictSName(districtName[0].toString());
						shiftsubDistrictForm.setDistrictDName(districtName[1].toString());

						List<District> contsourceDistrict = shiftService.getsourcedistrictNamebyDistIDforShftSubDist(shiftsubDistrictForm);
						if (contsourceDistrict != null) {
							Iterator<District> distSourceItr = contsourceDistrict.iterator();
							StringBuffer finalsourcedist = new StringBuffer();

							while (distSourceItr.hasNext()) {
								finalsourcedist.append(distSourceItr.next().getDistrictNameEnglish().trim());
							}
							contsourceDistName = finalsourcedist.toString();

							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							shiftsubDistrictForm.setFinalsourceDistName(contsourceDistName);
						}

						List<District> contdestDistrict = shiftService.gettargetdistrictNamebyDistIDforShftSubDist(shiftsubDistrictForm);
						if (contdestDistrict != null) {
							Iterator<District> destTargetItr = contdestDistrict.iterator();
							StringBuffer finalcontdesrstate = new StringBuffer();

							while (destTargetItr.hasNext()) {
								finalcontdesrstate.append(destTargetItr.next().getDistrictNameEnglish().trim());
							}
							conttargetDistName = finalcontdesrstate.toString();

							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);

							shiftsubDistrictForm.setFinaltargetDistName(conttargetDistName);
						}

						List<Subdistrict> contsubDist = shiftService.getsubdistrictNamebySubDistIDforShftSubDist(shiftsubDistrictForm);
						if (contsubDist != null) {
							Iterator<Subdistrict> subdistItr = contsubDist.iterator();
							StringBuffer finaldist = new StringBuffer();

							while (subdistItr.hasNext()) {
								finaldist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDistrictName = finaldist.toString();
							contSubDistrictName = contSubDistrictName.substring(0, contSubDistrictName.length() - 1);

							shiftsubDistrictForm.setFinalSubdistName(contSubDistrictName);
						}

						subDistSourceList = subDistrictService.getSubDistListbyDistCodeShift(Integer.parseInt(districtName[0].toString()));
					} else {
						String districtname = shiftsubDistrictForm.getDistrictNameEnglish();
						shiftsubDistrictForm.setDistrictSName(districtname);
						if (districtname != null) {
							subDistSourceList = subDistrictService.getSubDistListbyDistCodeShift(Integer.parseInt(districtname));
						}
					}
				}
				subDistSourceList.remove(shiftsubDistrictForm.getDistrictNameEnglish());
				Map<Integer, String> subdistCodeNameMap = new HashMap<Integer, String>();

				if (shiftsubDistrictForm.getSubDistrictName() != null && !shiftsubDistrictForm.getSubDistrictName().isEmpty()) {
					listSubdistNames = shiftsubDistrictForm.getSubDistrictName().split(",");
				}
				if (shiftsubDistrictForm.getDistrictNameEnglish() != null && !shiftsubDistrictForm.getDistrictNameEnglish().isEmpty()) {
					listSubdistCodes = shiftsubDistrictForm.getSubdistrictNameEnglish().split(",");
				}
				if (listSubdistNames != null) {
					for (int i = 0; i < listSubdistNames.length; i++) {
						if (listSubdistCodes != null && listSubdistCodes[i] != null && !listSubdistCodes[i].equals("")) {
							subdistCode = new Integer(listSubdistCodes[i]);
						}
						if (listSubdistNames != null && listSubdistNames[i] != null && !listSubdistNames[i].equals("")) {
							subdistName = listSubdistNames[i];
						}
						subdistCodeNameMap.put(subdistCode, subdistName);
					}
				}

				model.addAttribute("subdistrictSourceList", subDistSourceList);
				model.addAttribute("subdistrictDestList", subdistCodeNameMap);

				mav.addObject("shiftSubDistrict", shiftsubDistrictForm);
				session.setAttribute("formbean", shiftsubDistrictForm);
				return mv = new ModelAndView("redirect:govtOrderCommon.htm");

			}
		} catch (NumberFormatException e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			// return mav;
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mv;
	}

	@RequestMapping(value = "/saveShiftSubDistrict", method = RequestMethod.POST)
	public ModelAndView shiftSubDistrict(HttpServletRequest request, HttpSession session) throws Exception {
		boolean saveSuccess = false;
		String viewSubDistrictHistory = "";
		String getordercode = null;
		ModelAndView mav = null;
		try {
			
			
			ShiftSubDistrictForm shiftSubDistrictForm = (ShiftSubDistrictForm) session.getAttribute("formbean");
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

			
			if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
				getordercode = shiftService.shiftSubDistrict(shiftSubDistrictForm, govtOrderForm, request, userId, isCenterUser);

				String[] val = getordercode.split(",");

				// localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				if (getordercode != null) {
					if (session.getAttribute("validGovermentUpload") != null) {
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) session.getAttribute("validGovermentUpload");
						boolean insertGovtTableFlag = shiftService.saveDataInAttachShiftSubDistrict(govtOrderForm, shiftSubDistrictForm, validGovermentUpload, request.getSession(), Integer.parseInt(val[1]));
					}
				}
			} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
				getordercode = shiftService.shiftSubDistrictforTemplate(shiftSubDistrictForm, govtOrderForm, request, userId, isCenterUser);

				String[] val = getordercode.split(",");

				// localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				if (getordercode != null) {
					if (session.getAttribute("validGovermentGenerateUpload") != null) {
						// GenerateDetails validGovermentGenerateUpload =
						// GenerateDetails.session.getAttribute("validGovermentGenerateUpload");
						boolean insertGovtTableFlag = shiftService.saveDataInAttachShiftGenerateSubDistrict(govtOrderForm, shiftSubDistrictForm, request.getSession(), Integer.parseInt(val[1]));
					}
				}

			}

			if (getordercode != null) {
				/*
				 * Iterator itr = getordercode.iterator(); while (itr.hasNext())
				 * { ShiftSubDistrict transubdistCode = (ShiftSubDistrict)
				 * itr.next(); int t_code =
				 * transubdistCode.getShift_subdistrict_fn(); char t_type='S';
				 * int flag; flag =emailService.sendEmail(t_code,t_type);
				 * if(flag==1) { log.debug("Mail is sent"); } }
				 */

				String[] val = getordercode.split(",");

				// localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				int t_code = Integer.parseInt(val[0]);
				char t_type = 'S';
				int flag;
				EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
				est.start();

				String listsubdistrictCodes = shiftSubDistrictForm.getSubdistrictNameEnglish();
				String updatedSubDistrictCode = null;
				if (listsubdistrictCodes.contains(",")) {
					String[] subdistrcitCodes = listsubdistrictCodes.split(",");

					for (int i = 0; i < subdistrcitCodes.length; i++) {
						if (subdistrcitCodes[i] != null) {
							if (updatedSubDistrictCode != null) {
								updatedSubDistrictCode = updatedSubDistrictCode + subdistrcitCodes[i].toString() + ":";
							} else {
								updatedSubDistrictCode = subdistrcitCodes[i].toString() + ":";
							}
						}
					}
				} else {
					updatedSubDistrictCode = listsubdistrictCodes;
				}
				String aMessage = "The sub-districts was shifted successfully";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);

				// viewSubDistrictHistory =
				// "redirect:viewSubDistrictHistoryforShift.htm?id=" +
				// updatedSubDistrictCode + "&type=S";
				session.removeAttribute("formbean");
				session.removeValue("formbean");
				session.removeAttribute("govtOrderForm");
				session.removeValue("govtOrderForm");
				session.removeAttribute("addAttachmentBean");
				session.removeValue("addAttachmentBean");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			// return redirectPath;
		}
		return mav;
	}

	@RequestMapping(value = "/viewSubDistrictHistoryforShift", method = RequestMethod.GET)
	public ModelAndView viewSubDistrictHistoryDetail(@ModelAttribute("subDistrictForm") SubDistrictForm subDistrictForm, Model model, @RequestParam("id") String listsubdistrictCodes, @RequestParam("type") String type, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("view_subdistricthistory");

		try {
			char subdistrictNameEnglish = 'T';

			int subdistrictCode = 0;
			List<SubdistrictHistory> subDistrictHistoryDetail = new ArrayList<SubdistrictHistory>();

			if (listsubdistrictCodes.contains(":")) {

				String[] subdistrictCodes = listsubdistrictCodes.split(":");
				for (int i = 0; i < subdistrictCodes.length; i++) {

					if (subdistrictCodes[i] != null) {
						subdistrictCode = Integer.parseInt(subdistrictCodes[i].toString());

						List<SubdistrictHistory> subDistrictHistoryDetailpercode = subDistrictService.getSubDistrictHistoryDetail(subdistrictNameEnglish, subdistrictCode);

						subDistrictHistoryDetail.addAll(subDistrictHistoryDetailpercode);
					}
				}
			} else {
				subdistrictCode = Integer.parseInt(listsubdistrictCodes);
				List<SubdistrictHistory> subDistrictHistoryDetailpercode = subDistrictService.getSubDistrictHistoryDetail(subdistrictNameEnglish, subdistrictCode);

				subDistrictHistoryDetail.addAll(subDistrictHistoryDetailpercode);

			}

			model.addAttribute("subDistrictHistory", subDistrictHistoryDetail);
			subDistrictForm.setSubDistrictHistoryDetail(subDistrictHistoryDetail);
			mav.addObject("subDistrictForm", subDistrictForm);
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// End ShiftSubdistrict

	// ShiftBlock methods

	@RequestMapping(value = "/draftShiftBlock", method = RequestMethod.POST)
	public ModelAndView draftShiftBlock(@ModelAttribute("shiftblock") ShiftBlockForm shiftblockForm, BindingResult result, SessionStatus status, HttpServletRequest request, HttpSession session, Model model) {
		ModelAndView mav = new ModelAndView("shiftblock");
		ModelAndView mv = null;
		
		try {
			String[] listBlockNames = null;
			String[] listBlockCodes = null;
			String blockName = null;
			Integer blockCode = 0;
			String contsourceDistName = null;
			String conttargetDistName = null;
			String contBlockName = null;

			List<Block> blockSourceList = new ArrayList<Block>();
			
			shiftBlockValidator.validate(shiftblockForm, result, stateCode);
			if (result.hasErrors()) {
				mav.addObject("shiftblock", shiftblockForm);
				return mav;
			} else {
				if (shiftblockForm.getDistrictNameEnglish().contains(",")) {
					String[] districtName = shiftblockForm.getDistrictNameEnglish().split(",");
					shiftblockForm.setDistrictSName(districtName[0].toString());
					shiftblockForm.setDistrictDName(districtName[1].toString());

					List<District> contsourceDistrict = shiftService.getsourcedistrictNamebyDistIDforShiftBlock(shiftblockForm);
					if (contsourceDistrict != null) {
						Iterator<District> distSourceItr = contsourceDistrict.iterator();
						StringBuffer finalsourcedist = new StringBuffer();

						while (distSourceItr.hasNext()) {
							finalsourcedist.append(distSourceItr.next().getDistrictNameEnglish().trim());
						}
						contsourceDistName = finalsourcedist.toString();

						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						shiftblockForm.setFinalsourceDistName(contsourceDistName);
					}

					List<District> contdestDistrict = shiftService.gettargetdistrictNamebyDistIDforShiftBlock(shiftblockForm);
					if (contdestDistrict != null) {
						Iterator<District> destTargetItr = contdestDistrict.iterator();
						StringBuffer finalcontdesrstate = new StringBuffer();

						while (destTargetItr.hasNext()) {
							finalcontdesrstate.append(destTargetItr.next().getDistrictNameEnglish().trim());
						}
						conttargetDistName = finalcontdesrstate.toString();

						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);

						shiftblockForm.setFinaltargetDistName(conttargetDistName);
					}
					blockSourceList = blockService.getBlockList(Integer.parseInt(districtName[0].toString()));
				}

				blockSourceList.remove(shiftblockForm.getBlockNameEnglish());
				Map<Integer, String> blockCodeNameMap = new HashMap<Integer, String>();

				if (shiftblockForm.getBlockName() != null && !shiftblockForm.getBlockName().isEmpty()) {
					listBlockNames = shiftblockForm.getBlockName().split(",");
				}
				if (shiftblockForm.getBlockNameEnglish() != null && !shiftblockForm.getBlockNameEnglish().isEmpty()) {
					listBlockCodes = shiftblockForm.getBlockNameEnglish().split(",");

					List<Block> conttargetBlock = shiftService.getblockNamebyBlockID(shiftblockForm);
					if (conttargetBlock != null) {
						Iterator<Block> blockTargetItr = conttargetBlock.iterator();
						StringBuffer finaltargetblock = new StringBuffer();

						while (blockTargetItr.hasNext()) {
							finaltargetblock.append(blockTargetItr.next().getBlockNameEnglish().trim());
						}
						contBlockName = finaltargetblock.toString();

						contBlockName = contBlockName.substring(0, contBlockName.length() - 1);
						shiftblockForm.setFinaltargetBlockName(contBlockName);
					}
				}

				if (listBlockNames != null) {
					for (int i = 0; i < listBlockNames.length; i++) {
						if (listBlockCodes != null && listBlockCodes[i] != null && !listBlockCodes[i].equals("")) {
							blockCode = new Integer(listBlockCodes[i]);
						}
						if (listBlockNames != null && listBlockNames[i] != null && !listBlockNames[i].equals("")) {
							blockName = listBlockNames[i];
						}
						blockCodeNameMap.put(blockCode, blockName);
					}
				}

				model.addAttribute("blockSourceList", blockSourceList);
				// model.addAttribute("blockDestList", blockCodeNameMap);
				mav.addObject("shiftblock", shiftblockForm);
				// return mav;
				session.setAttribute("formbean", shiftblockForm);
				mv = new ModelAndView("redirect:govtOrderCommon.htm");
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mv;
	}

	@RequestMapping(value = "/saveShiftBlock", method = RequestMethod.POST)
	public ModelAndView shiftBlock(HttpServletRequest request, HttpSession session) throws Exception {
		// boolean saveSuccess = false;
		String getordercode = null;
		String viewBlockHistory = "";
		ModelAndView mav = null;

		try {
			setGlobalParams(session);

			ShiftBlockForm shiftblockForm = (ShiftBlockForm) session.getAttribute("formbean");
			/*
			 * AddAttachmentBean addAttachmentBean = null;
			 * List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
			 * AddAttachmentHandler attachmentHandler = new
			 * AddAttachmentHandler(); GovernmentOrderForm govtOrderForm =
			 * (GovernmentOrderForm) session.getAttribute("govtOrderForm"); if
			 * (session.getAttribute("addAttachmentBean") != null) {
			 * addAttachmentBean = (AddAttachmentBean) session
			 * .getAttribute("addAttachmentBean"); String validateAttachment =
			 * attachmentHandler.validateAttachment(addAttachmentBean);
			 * metaInfoOfToBeAttachedFileList =
			 * attachmentHandler.getMetaDataListOfFiles(addAttachmentBean); }
			 */
			/*
			 * ======================================Attached File Saving Part
			 * ==========================================
			 */
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");
			if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
				getordercode = shiftService.shiftBlock(shiftblockForm, govtOrderForm, request, userId);

				String[] val = getordercode.split(",");

				// localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				if (getordercode != null) {
					if (session.getAttribute("validGovermentUpload") != null) {
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) session.getAttribute("validGovermentUpload");
						boolean insertGovtTableFlag = shiftService.saveDataInAttachShiftBlock(govtOrderForm, shiftblockForm, validGovermentUpload, request.getSession(), Integer.parseInt(val[1]));
					}
				}

			} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
				getordercode = shiftService.shiftBlockforTemplate(shiftblockForm, govtOrderForm, request, userId);

				String[] val = getordercode.split(",");

				// localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				if (getordercode != null) {
					if (session.getAttribute("validGovermentGenerateUpload") != null) {
						// GenerateDetails validGovermentGenerateUpload =
						// GenerateDetails.session.getAttribute("validGovermentGenerateUpload");
						boolean insertGovtTableFlag = shiftService.saveDataInAttachGenerateShiftBlock(govtOrderForm, shiftblockForm, request.getSession(), Integer.parseInt(val[1]));
					}
				}
			}

			if (getordercode != null) {

				String[] val = getordercode.split(",");


				int t_code = Integer.parseInt(val[0]);
				char t_type = 'B';
				int flag;
				EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
				est.start();
				/*
				 * flag =emailService.sendEmail(t_code,t_type); if(flag==1) {
				 * log.debug("Mail is sent"); }
				 */
				String listblockCodes = shiftblockForm.getBlockNameEnglish();
				String updatedBlockCode = null;
				if (listblockCodes.contains(",")) {
					String[] blockCodes = listblockCodes.split(",");

					for (int i = 0; i < blockCodes.length; i++) {
						if (blockCodes[i] != null) {
							if (updatedBlockCode != null) {
								updatedBlockCode = updatedBlockCode + blockCodes[i].toString() + ":";
							} else {
								updatedBlockCode = blockCodes[i].toString() + ":";
							}

						}
					}
				} else {
					updatedBlockCode = listblockCodes;
				}

				String aMessage = "The block was shifted successfully";

				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);

				// viewBlockHistory =
				// "redirect:viewBlockHistoryforShift.htm?id=" +
				// updatedBlockCode + "&type=S";

			}

			session.removeAttribute("formbean");
			session.removeValue("formbean");
			session.removeAttribute("govtOrderForm");
			session.removeValue("govtOrderForm");
			session.removeAttribute("addAttachmentBean");
			session.removeValue("addAttachmentBean");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			// return redirectPath;
		}

		return mav;
	}

	@RequestMapping(value = "/viewBlockHistoryforShift", method = RequestMethod.GET)
	public ModelAndView viewBlockHistoryDetail(@ModelAttribute("blockForm") BlockForm blockForm, Model model, @RequestParam("id") String listblockCodes, @RequestParam("type") String type, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("view_blockhistory");
		try {
			char blockNameEnglish = 'B';
			int blockCode = 0;
			List<BlockHistory> blockHistoryDetail = new ArrayList<BlockHistory>();

			if (listblockCodes.contains(":")) {

				String[] blockCodes = listblockCodes.split(":");
				for (int i = 0; i < blockCodes.length; i++) {

					if (blockCodes[i] != null) {
						blockCode = Integer.parseInt(blockCodes[i].toString());

						List<BlockHistory> blockHistoryDetailpercode = blockService.getBlockHistoryDetail(blockNameEnglish, blockCode);

						blockHistoryDetail.addAll(blockHistoryDetailpercode);
					}
				}
			} else {
				blockCode = Integer.parseInt(listblockCodes);
				List<BlockHistory> blockHistoryDetailpercode = blockService.getBlockHistoryDetail(blockNameEnglish, blockCode);

				blockHistoryDetail.addAll(blockHistoryDetailpercode);

			}

			model.addAttribute("blockHistory", blockHistoryDetail);
			blockForm.setBlockHistoryDetail(blockHistoryDetail);
			mav.addObject("blockForm", blockForm);
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// /End ShiftBlock methods

	// Shift Village from one subdistrict to another

	@RequestMapping(value = "/draftShiftVillageSD", method = RequestMethod.POST)
	public ModelAndView draftShiftVillageSD(@ModelAttribute("shiftvillageSubDistrict") ShiftVillageForm shiftVillageForm, BindingResult result, SessionStatus status, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("shiftvillageSubDistrict");
		ModelAndView mv = null;
		try {

			String contsourceStateName = null;
			String conttargetStateName = null;
			String contsourceDistName = null;
			String conttargetDistName = null;
			String contsourceSubDistrictName = null;
			String contdestSubDistrictName = null;
			String contVillageName = null;
			int operationCode = 0;
			///////////// DROP DOWN VALIDATIONS /////////////////////


			///////////// CODE FOR GOVERNMENT ORDER CHECKING
			///////////// /////////////////////
			
			
			if (isCenterUser.equalsIgnoreCase("S")) {
				operationCode = 13;
			} else if (isCenterUser.equalsIgnoreCase("C")) {
				operationCode = 62;
			}

			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			// hMap =
			// configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode,
			// operationCode, 'V');// 10 is operation code for create new

			// String govtOrderConfig = hMap.get("govtOrder");//
			// values==govtOrderUpload,govtOrderGenerate
			// String message = hMap.get("message");
			shiftVillageForm.setStateSName(shiftVillageForm.getStateNameEnglish());
			shiftVillageForm.setStateSName(stateCode.toString());
			if (operationCode != 0) {
				///////////// DROP DOWN VALIDATIONS /////////////////////

				shiftVillageSDValidator.validate(shiftVillageForm, result, stateCode);
				if (result.hasErrors()) {
					mav.addObject("shiftvillageSubDistrict", shiftVillageForm);
					return mav;
				} else {
					if (shiftVillageForm.getDistrictNameEnglish() != null) {
						if (shiftVillageForm.getStateNameEnglish() != null) {
							if (shiftVillageForm.getStateNameEnglish().contains(",")) {
								String[] stateCodes = shiftVillageForm.getStateNameEnglish().split(",");
								String sourceState = stateCodes[0].toString();
								String targetState = stateCodes[1].toString();
								shiftVillageForm.setStateSName(sourceState);
								shiftVillageForm.setStateTName(targetState);

								List<State> contsourceState = shiftService.getsourcestateNamebyStateIDforSD(shiftVillageForm);
								if (contsourceState != null) {
									Iterator<State> stateSourceItr = contsourceState.iterator();
									StringBuffer finalsourcestate = new StringBuffer();

									while (stateSourceItr.hasNext()) {
										finalsourcestate.append(stateSourceItr.next().getStateNameEnglish().trim());
									}
									contsourceStateName = finalsourcestate.toString();

									// finalcontDist =
									// finalcontDist.substring(0,
									// finalcontDist.length() - 1);
									shiftVillageForm.setFinalsourceStateName(contsourceStateName);
								}
							}

							List<State> conttargetState = shiftService.gettargetstateNamebyStateIDforSD(shiftVillageForm);
							if (conttargetState != null) {
								Iterator<State> stateTargetItr = conttargetState.iterator();
								StringBuffer finaltargetstate = new StringBuffer();

								while (stateTargetItr.hasNext()) {
									finaltargetstate.append(stateTargetItr.next().getStateNameEnglish().trim());
								}
								conttargetStateName = finaltargetstate.toString();

								// finalcontDist = finalcontDist.substring(0,
								// finalcontDist.length() - 1);
								shiftVillageForm.setFinaltargetStateName(conttargetStateName);
							}
						}

						if (shiftVillageForm.getDistrictNameEnglish().contains(",")) {
							String[] districtCodes = shiftVillageForm.getDistrictNameEnglish().split(",");
							String districtSName = districtCodes[0].toString();
							String districtDName = districtCodes[1].toString();
							shiftVillageForm.setDistrictSName(districtSName);
							shiftVillageForm.setDistrictDName(districtDName);

							List<District> contsourceDistrict = shiftService.getsourcedistrictNamebyDistIDforShftVillageSD(shiftVillageForm);
							if (contsourceDistrict != null) {
								Iterator<District> distSourceItr = contsourceDistrict.iterator();
								StringBuffer finalsourcedist = new StringBuffer();

								while (distSourceItr.hasNext()) {
									finalsourcedist.append(distSourceItr.next().getDistrictNameEnglish().trim());
								}
								contsourceDistName = finalsourcedist.toString();

								// finalcontDist = finalcontDist.substring(0,
								// finalcontDist.length() - 1);
								shiftVillageForm.setFinalsourceDistName(contsourceDistName);
							}

							List<District> contdestDistrict = shiftService.gettargetdistrictNamebyDistIDforShftVillageSD(shiftVillageForm);
							if (contdestDistrict != null) {
								Iterator<District> destTargetItr = contdestDistrict.iterator();
								StringBuffer finalcontdesrstate = new StringBuffer();

								while (destTargetItr.hasNext()) {
									finalcontdesrstate.append(destTargetItr.next().getDistrictNameEnglish().trim());
								}
								conttargetDistName = finalcontdesrstate.toString();

								// finalcontDist = finalcontDist.substring(0,
								// finalcontDist.length() - 1);

								shiftVillageForm.setFinaltargetDistName(conttargetDistName);
							}
						} else {
							shiftVillageForm.setDistrictSName(shiftVillageForm.getDistrictNameEnglish());
						}
					}
					if (shiftVillageForm.getSubdistrictNameEnglish() != null) {
						if (shiftVillageForm.getSubdistrictNameEnglish().contains(",")) {
							String[] subdistrictCodes = shiftVillageForm.getSubdistrictNameEnglish().split(",");
							String subdistrictSName = subdistrictCodes[0].toString();
							String subdistrictDName = subdistrictCodes[1].toString();
							shiftVillageForm.setSubdistrictSName(subdistrictSName);
							shiftVillageForm.setSubdistrictDName(subdistrictDName);
						} else {
							shiftVillageForm.setSubdistrictSName(shiftVillageForm.getSubdistrictNameEnglish());
							List<Subdistrict> contsourcesubDist = shiftService.getsourcesubdistrictNamebySubDistIDforShftVillageSD(shiftVillageForm);
							if (contsourcesubDist != null) {
								Iterator<Subdistrict> sourcesubdistItr = contsourcesubDist.iterator();
								StringBuffer sourcesubdist = new StringBuffer();

								while (sourcesubdistItr.hasNext()) {
									sourcesubdist.append(sourcesubdistItr.next().getSubdistrictNameEnglish().trim());
								}
								contsourceSubDistrictName = sourcesubdist.toString();

								shiftVillageForm.setFinalsourceSubdistName(contsourceSubDistrictName);
							}

							List<Subdistrict> conttargetsubDist = shiftService.gettargetsubdistrictNamebySubDistIDforShftVillageSD(shiftVillageForm);
							if (conttargetsubDist != null) {
								Iterator<Subdistrict> targetsubdistItr = conttargetsubDist.iterator();
								StringBuffer targetsubdist = new StringBuffer();

								while (targetsubdistItr.hasNext()) {
									targetsubdist.append(targetsubdistItr.next().getSubdistrictNameEnglish().trim());
								}
								contdestSubDistrictName = targetsubdist.toString();

								shiftVillageForm.setFinaltargetSubdistName(contdestSubDistrictName);
							}

							List<Village> contvillage = shiftService.getVillageNamebyVillageIDforShftVillageSD(shiftVillageForm);
							if (contvillage != null) {
								Iterator<Village> villageItr = contvillage.iterator();
								StringBuffer villlist = new StringBuffer();

								while (villageItr.hasNext()) {
									villlist.append(villageItr.next().getVillageNameEnglish().trim() + ",");
								}
								contVillageName = villlist.toString();
								contVillageName = contVillageName.substring(0, contVillageName.length() - 1);

								shiftVillageForm.setFinalVillageName(contVillageName);
							}
						}
					}
					mav.addObject("shiftvillageSubDistrict", shiftVillageForm);
					session.setAttribute("formbean", shiftVillageForm);
					mv = new ModelAndView("redirect:govtOrderCommon.htm");
				}
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "No operation Code Defined");
				return mav;
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mv;
	}

	@RequestMapping(value = "/saveShiftVillageSD", method = RequestMethod.POST)
	public ModelAndView shiftVillageforSubDistrict(HttpServletRequest request, HttpSession session) throws Exception {
		// boolean saveSuccess = false;
		// List<ShiftVillageSD> getordercode = null;
		ModelAndView mav = null;
		String getordercode = null;
		String viewVillageHistory = "";
		
		try {
			
			ShiftVillageForm shiftvillageForm = (ShiftVillageForm) session.getAttribute("formbean");
			

			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");
			if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
				getordercode = shiftService.shiftVillageforSubdistrict(shiftvillageForm, govtOrderForm, request, userId, isCenterUser,districtCode);

				String[] val = getordercode.split(",");

				// localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				if (getordercode != null) {
					if (session.getAttribute("validGovermentUpload") != null) {
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) session.getAttribute("validGovermentUpload");
						boolean insertGovtTableFlag = shiftService.saveDataInAttachShiftVillageSD(govtOrderForm, shiftvillageForm, validGovermentUpload, request.getSession(), Integer.parseInt(val[1]));
					}
				}
			} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
				getordercode = shiftService.shiftVillageforSubdistrictforTemplate(shiftvillageForm, govtOrderForm, request, userId, isCenterUser);

				String[] val = getordercode.split(",");

				// localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				if (getordercode != null) {
					if (session.getAttribute("validGovermentGenerateUpload") != null) {
						// GenerateDetails validGovermentGenerateUpload =
						// GenerateDetails.session.getAttribute("validGovermentGenerateUpload");
						boolean insertGovtTableFlag = shiftService.saveDataInAttachShiftGenerateVillageSD(govtOrderForm, shiftvillageForm, request.getSession(), Integer.parseInt(val[1]));
					}
				}

			}

			if (getordercode != null) {
				/*
				 * Iterator itr = getordercode.iterator(); while (itr.hasNext())
				 * { ShiftVillageSD tranvillsdistCode = (ShiftVillageSD)
				 * itr.next(); int t_code =
				 * tranvillsdistCode.getShift_village_fn(); char t_type='V'; int
				 * flag; flag =emailService.sendEmail(t_code,t_type);
				 * if(flag==1) { log.debug("Mail is sent"); } }
				 */
				String[] val = getordercode.split(",");

				// localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				int t_code = Integer.parseInt(val[0]);
				char t_type = 'V';

				EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
				est.start();

				String listVillageCodes = shiftvillageForm.getVillageNameEnglish();
				String updatedVillageCode = null;
				if (listVillageCodes.contains(",")) {
					String[] villageCodes = listVillageCodes.split(",");

					for (int i = 0; i < villageCodes.length; i++) {
						if (villageCodes[i] != null) {
							if (updatedVillageCode != null) {
								updatedVillageCode = updatedVillageCode + villageCodes[i].toString() + ":";
							} else {
								updatedVillageCode = villageCodes[i].toString() + ":";
							}
						}
					}
				} else {
					updatedVillageCode = listVillageCodes;
				}

				String aMessage = "Villages shifted successfully between Sub-districts";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);

				// viewVillageHistory =
				// "redirect:viewVillageHistoryforShift.htm?id=" +
				// updatedVillageCode + "&type=S";

			}
			session.removeAttribute("formbean");
			session.removeValue("formbean");
			session.removeAttribute("govtOrderForm");
			session.removeValue("govtOrderForm");
			session.removeAttribute("addAttachmentBean");
			session.removeValue("addAttachmentBean");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			// return redirectPath;
		}

		return mav;
	}

	@RequestMapping(value = "/viewVillageHistoryforShift", method = RequestMethod.GET)
	public ModelAndView viewBlockHistoryDetail(@ModelAttribute("villageForm") VillageForm villageForm, Model model, @RequestParam("id") String listvillageCodes, @RequestParam("type") String type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("view_villagehistory");
		try {
			char villageNameEnglish = 'V';

			int villageCode = 0;
			List<VillageHistory> villageHistoryDetail = new ArrayList<VillageHistory>();

			if (listvillageCodes.contains(":")) {

				String[] villageCodes = listvillageCodes.split(":");
				for (int i = 0; i < villageCodes.length; i++) {

					if (villageCodes[i] != null) {
						villageCode = Integer.parseInt(villageCodes[i].toString());
						List<VillageHistory> villageHistoryDetailpercode = villageService.getVillageHistoryDetail(villageNameEnglish, villageCode);

						villageHistoryDetail.addAll(villageHistoryDetailpercode);
					}
				}
			} else {
				villageCode = Integer.parseInt(listvillageCodes);

				List<VillageHistory> villageHistoryDetailpercode = villageService.getVillageHistoryDetail(villageNameEnglish, villageCode);

				villageHistoryDetail.addAll(villageHistoryDetailpercode);
			}

			model.addAttribute("villageHistory", villageHistoryDetail);
			villageForm.setVillageHistoryDetail(villageHistoryDetail);
			mav.addObject("villageForm", villageForm);
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// End ShiftVillage from one Subdistrict to another

	// ShiftVillage from one Block to another methods

	@RequestMapping(value = "/draftShiftVillageBlock", method = RequestMethod.POST)
	public ModelAndView draftShiftVillageBlock(@ModelAttribute("shiftvillageblock") ShiftVillageForm shiftvillageForm, BindingResult result, SessionStatus status, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("shiftvillageblock");
		ModelAndView mv = null;
		String contdistName = null;
		String consourceBlock = null;
		String contargetBlock = null;
		String contVillage = null;
		int districtCode = 0;
		try {

			shiftVillageBlockValidator.validate(shiftvillageForm, result, stateCode);
			if (result.hasErrors()) {
				mav.addObject("shiftvillageblock", shiftvillageForm);
				return mav;
			} else {
				districtCode = shiftvillageForm.getDistrictCode();
				String districtname = shiftvillageForm.getDistrictNameEnglish();
				shiftvillageForm.setDistrictSName(districtname);
				if (shiftvillageForm.getBlockNameEnglish() != null) {
					if (shiftvillageForm.getBlockNameEnglish().contains(",")) {
						String[] blockCodes = shiftvillageForm.getBlockNameEnglish().split(",");
						String blockSourceName = blockCodes[0].toString();
						String blockDName = blockCodes[1].toString();
						shiftvillageForm.setBlockDName(blockDName);
						shiftvillageForm.setBlockSName(blockSourceName);

						if (districtCode == 0) {
							List<District> contsourceDistrict = shiftService.getsourcedistrictNamebyDistIDforShftVillageSD(shiftvillageForm);
							if (contsourceDistrict != null) {
								Iterator<District> distSourceItr = contsourceDistrict.iterator();
								StringBuffer finalsourcedist = new StringBuffer();

								while (distSourceItr.hasNext()) {
									finalsourcedist.append(distSourceItr.next().getDistrictNameEnglish().trim());
								}
								contdistName = finalsourcedist.toString();

								// finalcontDist = finalcontDist.substring(0,
								// finalcontDist.length() - 1);
								shiftvillageForm.setFinalsourceDistName(contdistName);
							}
						}

						List<Block> contsourceBlock = shiftService.getsourceblockNamebyBlockID(shiftvillageForm);
						if (contsourceBlock != null) {
							Iterator<Block> blockSourceItr = contsourceBlock.iterator();
							StringBuffer finalsourceblock = new StringBuffer();

							while (blockSourceItr.hasNext()) {
								finalsourceblock.append(blockSourceItr.next().getBlockNameEnglish().trim());
							}
							consourceBlock = finalsourceblock.toString();

							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							shiftvillageForm.setFinalsourceBlockName(consourceBlock);
						}

						List<Block> conttargetBlock = shiftService.gettargetblockNamebyBlockID(shiftvillageForm);
						if (conttargetBlock != null) {
							Iterator<Block> blockTargetItr = conttargetBlock.iterator();
							StringBuffer finaltargetblock = new StringBuffer();

							while (blockTargetItr.hasNext()) {
								finaltargetblock.append(blockTargetItr.next().getBlockNameEnglish().trim());
							}
							contargetBlock = finaltargetblock.toString();

							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							shiftvillageForm.setFinaltargetBlockName(contargetBlock);
						}

						List<Village> contvillage = shiftService.getVillageNamebyVillageIDforShftVillageSD(shiftvillageForm);
						if (contvillage != null) {
							Iterator<Village> villageItr = contvillage.iterator();
							StringBuffer villlist = new StringBuffer();

							while (villageItr.hasNext()) {
								villlist.append(villageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillage = villlist.toString();

							shiftvillageForm.setFinalVillageName(contVillage);
						}
					} else {
						shiftvillageForm.setBlockSName(shiftvillageForm.getBlockNameEnglish());
					}
				}
				shiftvillageForm.setDistrictSName(districtname);
				mav.addObject("shiftvillageblock", shiftvillageForm);
				session.setAttribute("formbean", shiftvillageForm);
				mv = new ModelAndView("redirect:govtOrderCommon.htm");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mv;
	}

	@RequestMapping(value = "/saveShiftVillageBlock", method = RequestMethod.POST)
	public ModelAndView shiftVillageforBlock(HttpServletRequest request, HttpSession session) throws Exception {
		boolean saveSuccess = false;
		ModelAndView mav = null;
		try {
			
			ShiftVillageForm shiftvillageForm = (ShiftVillageForm) session.getAttribute("formbean");

			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			AddAttachmentBean addAttachmentBean = null;
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

			if (session.getAttribute("addAttachmentBean") != null) {
				addAttachmentBean = (AddAttachmentBean) session.getAttribute("addAttachmentBean");
				attachmentHandler.validateAttachment(addAttachmentBean);
			}

			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);

			saveSuccess = shiftService.shiftVillageforBlock(shiftvillageForm, govtOrderForm, request, userId, metaInfoOfToBeAttachedFileList);
			if (metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {

				attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}

			if (saveSuccess) {
				String aMessage = "The Villages were shifted successfully between Blocks";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
			} else {
				String aMessage = "Error Faced Please Check Logs.";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
			}
			session.removeAttribute("formbean");
			session.removeAttribute("govtOrderForm");
			session.removeAttribute("addAttachmentBean");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/saveShiftVillage", method = RequestMethod.POST)
	public ModelAndView shiftVillageforBlock(HttpSession session, HttpServletRequest request) {
		ModelAndView mv = null;
		try {
			char type = ' ';
			ShiftVillageForm shiftform = (ShiftVillageForm) session.getAttribute("formbean");

			if (shiftform.getOperation() == 'T') {
				mv = new ModelAndView("forward:saveShiftVillageSD.htm");
			} else if (shiftform.getOperation() == 'B') {
				mv = new ModelAndView("forward:saveShiftVillageBlock.htm");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	

}
