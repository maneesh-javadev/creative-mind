package in.nic.pes.lgd.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.controllers.ManageRestructuredLBController.EffectiveDateList;
import com.cmc.lgd.localbody.entities.ManageLBDetails;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.BlockPanchayatSyncBlock;
import in.nic.pes.lgd.bean.BlockVillage;
import in.nic.pes.lgd.bean.ConfigurationBlockVillageMapping;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.WsUserRegistrationForm;
import in.nic.pes.lgd.common.EsapiEncoder;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.common.ReleaseResources;
import in.nic.pes.lgd.forms.BlockDataForm;
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.BlockService;
import in.nic.pes.lgd.service.ConfigGovtOrderService;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.ShiftService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.validator.BlockValidator;
import in.nic.pes.lgd.validator.CommonValidatorImpl;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

@Controller
public class BlockController { // NO_UCD (unused code)

	private static final Logger log = Logger.getLogger(BlockController.class);
	
	int Itr = 0;
	

	@Autowired
	CommonValidatorImpl commonValidator;

	@Autowired
	BlockService blockService;

	@Autowired
	ShiftService shiftService;

	@Autowired
	ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	DistrictService districtService;

	@Autowired
	BlockValidator blockValidator;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	VillageService villageService;

	@Autowired
	ConfigGovtOrderService configGovtOrderService;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	FileUploadUtility fileUploadUtility;

	@Autowired
	EmailService emailService;
	
	@Autowired
	StateService stateService;
	
	private Integer stateCode;

	private Integer districtCode;

	private Integer userId;
	
	private Long userIdLong;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId().intValue();
		userIdLong = sessionObject.getUserId();
		
	}

	@RequestMapping(value = "/invalidateblock", method = RequestMethod.GET)
	public ModelAndView preInvalidateDistrict(@ModelAttribute("invalidateBlock") BlockForm blockForm, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mv = null;

		try {
			setGlobalParams(httpSession);
			if (stateCode == null) {
				return mv = new ModelAndView("redirect:login.htm");
			}
			
			//int districtCode = getDistrictId(httpSession);
			
			mv = new ModelAndView("invalidateblockDefault");
			List<District> districtList = null;
			districtList = null;
			if(districtCode>0)
			  {
	            	districtList = districtService.getDistrictListbyDistrictCodeForLocalBody(districtCode);
			  }
			  else{
				  districtList =districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
			  }
			mv.addObject("districtList", districtList);
			if (districtCode == 0) {
				mv.addObject("flag1", 0);
			} else {
				mv.addObject("flag1", 1);
				mv.addObject("flag2", districtCode);
			}
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	@RequestMapping(value = "/invalidateblockPost", method = RequestMethod.POST)
	public ModelAndView InvalidateBlockPost(@ModelAttribute("invalidateBlock") BlockForm blockForm, BindingResult result, SessionStatus status, HttpSession httpsession, HttpServletRequest request) {
		ModelAndView mv = null;

		try {
			if (blockForm.getDistrictCode() == null) {
				//Integer districtCode = getDistrictId(httpsession);
				blockForm.setDistrictCode(districtCode.toString());
			}
			blockValidator.invalidateBlockValidator(blockForm, result);
			if (result.hasErrors()) {
				mv = new ModelAndView("invalidateblockDefault");
				List<District> districtList = null;
				districtList = new ArrayList<District>();
				districtList = districtService.getDistrictList(stateCode);
				mv.addObject("districtList", districtList);
				mv.addObject(blockForm);
				return mv;
			} else {
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 39, 'B');
				String govtOrderConfig = hMap.get("govtOrder");
				blockForm.setGovtOrderConfig(govtOrderConfig);
				blockForm.setOperation('I');
				httpsession.setAttribute("formbean", blockForm);
				mv = new ModelAndView("redirect:govtOrderCommon.htm");
				mv.addObject("govtOrderConfig", govtOrderConfig);

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

		return mv;
	}

	@RequestMapping(value = "/invalidateblockFinal", method = RequestMethod.POST)
	public ModelAndView InvalidateBlockFinal(@ModelAttribute("invalidateBlock") BlockForm blockFormses, BindingResult result, SessionStatus status, HttpSession httpsession, HttpServletRequest request,Model model) {
		ModelAndView mv = null;
		int operationCode = 39;
		int flagCode = 43;
		String bvList = null;
		boolean success;
		BlockForm blockForm = null;
		blockForm = new BlockForm();
		blockForm = (BlockForm) httpsession.getAttribute("formbean");
		AddAttachmentBean addAttachmentBean = null;
		AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
		try {
			
			
			
			
		
		
			if (httpsession.getAttribute("addAttachmentBean") != null) {
				addAttachmentBean = (AddAttachmentBean) httpsession.getAttribute("addAttachmentBean");
				attachmentHandler.validateAttachment(addAttachmentBean);
			}
			
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
			
			
			if (metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {
				success = blockService.invalidateBlock(stateCode, userId, operationCode, flagCode, bvList, blockForm, httpsession,metaInfoOfToBeAttachedFileList);
				if (blockForm.getGovtOrderConfig().equals("govtOrderUpload")){
					attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
				}
				if (success) {
					mv = new ModelAndView("success");
					model.addAttribute("msgid", "Block is invalidated.");
					return mv;
				} else {
					mv = new ModelAndView("success");
					model.addAttribute("msgid", "Error Faced Please Check Logs.");
					
				}
			}
			
			
			

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} finally {
			httpsession.removeAttribute("formbean");

		}

		return mv;
	}

	@RequestMapping("/new_block")
	public ModelAndView publishBlockGet(HttpSession httpSession, @ModelAttribute("newblockform") BlockForm blockForm, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			// int districtCode = getDistrictId(httpSession);
			setGlobalParams(httpSession);
			if (stateCode == null) {
				mav = new ModelAndView("redirect:login.htm");
			} else {
				mav = new ModelAndView("newblock");
				Map<String, String> hMap = new HashMap<String, String>();

				hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 41, 'B');// 10 is operation code
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");

				if (govtOrderConfig != null && mapConfig != null) {

					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Please configure government order before preceeding");
				}

				mav.addObject("mapConfig", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);

				if (districtCode == 0) {
					List<District> distList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
					model.addAttribute("distList", distList);
					mav.addObject("flag1", 1);

				} else {
					List<District> distList = districtService.getDistrictListByDistCode(districtCode);
					model.addAttribute("distList", distList);
					mav.addObject("flag1", 0);
				}
				
				

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/new_block", method = RequestMethod.POST)
	public ModelAndView publishBlock(HttpSession httpSession, @ModelAttribute("newblockform") @Valid BlockForm blockForm, BindingResult bindingResult, Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		String temp = null;
		String[] temp1 = null;
		//int districtCode = getDistrictId(httpSession);
		try {
			
			String blockFullList = null;
			List<Block> listBlock = null;
			listBlock = new ArrayList<Block>();
			List<District> distList = blockService.getDistrictListbyStateCode(stateCode);
			model.addAttribute("distList", distList);
			model.addAttribute("stateCode", stateCode);
			
			// code to get sub district list----------

			if (blockForm.getContributedBlocks() != null) {
				temp = blockForm.getContributedBlocks().replace("PART", "").replace("FULL", "");
				temp1 = blockForm.getContributedBlocks().split(",");

				listBlock = blockService.getBlockViewList("from Block where blockCode IN (" + temp + ") and isactive=true");
				for (int i = 0; i < listBlock.size(); i++) {
					if (temp1[i].contains("FULL")) {
						if (blockFullList != null) {
							blockFullList += listBlock.get(i).getBlockCode() + ",";
						} else {
							blockFullList = listBlock.get(i).getBlockCode() + ",";
						}
					}
					listBlock.get(i).setBlockNameEnglish(listBlock.get(i).getBlockNameEnglish() + " (" + (temp1[i].contains("FULL") ? "FULL)" : "PART)"));
					listBlock.get(i).setAliasEnglish(listBlock.get(i).getBlockCode() + (temp1[i].contains("FULL") ? "FULL" : "PART")); // temporarily
																																		// holding
																																		// block
																																		// code
																																		// in
																																		// alias
				}
			}
			// code to get village list----------
			temp = null;
			List<Village> listVillage = new ArrayList<Village>();
			if (blockForm.getContributedVillages() != null) {
				temp = blockForm.getContributedVillages().replace("PART", "").replace("FULL", "");
				listVillage = villageService.getVillageViewList("from Village where villageCode IN (" + temp + ") and isactive=true");
				model.addAttribute("listVill", listVillage);
			}
			if (blockForm.getAttachFile1() != null && blockForm.getAttachFile1().size() > 0) {
				commonValidator.isValidMime(bindingResult, request, blockForm.getAttachFile1());
			}
			if (bindingResult.hasErrors()) {
				model.addAttribute("blockList", listBlock);
				model.addAttribute("newblockform", blockForm);
				if (districtCode == 0) {
					distList = blockService.getDistrictListbyStateCode(stateCode);
					model.addAttribute("distList", distList);
					mav = new ModelAndView("newblock");
					mav.addObject("flag1", 1);

				} else {
					distList = districtService.getDistrictListByDistCode(districtCode);
					model.addAttribute("distList", distList);
					mav = new ModelAndView("newblock");
					mav.addObject("flag1", 0);
				}
				return mav;
			} else {
				if (Itr == 0) {
					httpSession.setAttribute("newBlockDetail", blockForm);
				}
				if (blockForm.isContriBlock() == true) {
					mav = new ModelAndView("redirect:rename_ContriBlock.htm");
				}
				if (blockForm.getAddAnotherBlock().equals("true")) {
					Itr = 0;
					blockForm.setOperation('C');
					httpSession.setAttribute("formbean", blockForm);
					mav = new ModelAndView("redirect:govtOrderCommon.htm");
					/*
					 * if (persistanceTest==true){ blockForm=null;
					 * blockForm=(BlockForm)
					 * httpSession.getAttribute("newBlockDetail"); mav=new
					 * ModelAndView("redirect:add_AnotherBlock.htm"); mav=new
					 * ModelAndView("redirect:govtOrderCommon.htm"); }
					 */
				}
				if (blockForm.getAddAnotherBlock().equals("Save")) {
					Itr = 0;
					blockForm.setOperation('C');
					httpSession.setAttribute("formbean", blockForm);
					mav = new ModelAndView("redirect:govtOrderCommon.htm");
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	// modify Block
	@RequestMapping(value = "/modifyBlock")
	public ModelAndView modifyBlock(@ModelAttribute("modifyBlockCmd") BlockForm modifyBlockCmd, HttpSession session, HttpServletRequest request, Model model) {
		ModelAndView mv = null;
		int blockCode = modifyBlockCmd.getBlockId();
		int operationCode = 40;
		char operation = 'M';
		String blocOldkName = null;
		
		try {
			setGlobalParams(session);
			Map<String, String> hMap = new HashMap<String, String>();
			hMap = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
			String message = hMap.get("message");
			String govtOrderConfig = hMap.get("govtOrder");

			if (govtOrderConfig == null) {
				mv = new ModelAndView("success");
				mv.addObject("msgid", message);
				return mv;

			} else {
				List<BlockDataForm> listblockDetails = blockService.getBlockDetailsModify(blockCode);
				EsapiEncoder.encode(listblockDetails);
				mv = new ModelAndView("modify_block");

				model.addAttribute("size", listblockDetails.size());
				model.addAttribute("listblockDetails", listblockDetails);

				modifyBlockCmd.setListBlockDetails(listblockDetails);
				mv.addObject("modify_block", modifyBlockCmd);
				mv.addObject("govtOrderConfig", govtOrderConfig);
				blocOldkName = listblockDetails.get(0).getBlockNameEnglish();
				session.setAttribute("blkOldName", blocOldkName);
			}
			// mv.addObject("modifyBlockCmd", modifyBlockCmd);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	/*
	 * @RequestMapping(value = "/modifyBlockAction", method =
	 * RequestMethod.POST) public ModelAndView
	 * modifyBlock(@ModelAttribute("modifyBlockCmd")BlockForm modifyBlockCmd,
	 * BindingResult result, SessionStatus status, Model
	 * model,HttpServletRequest request) {
	 * 
	 * System.out.println("update----------------------------"); ModelAndView
	 * mav=new ModelAndView("modify_block");
	 * blockValidator.validate(modifyBlockCmd, result); if(result.hasErrors()) {
	 * mav.addObject("modifyBlockCmd",modifyBlockCmd); return mav; } String
	 * aMessage = "Block Detail Modified Successfully";
	 * blockService.modifyBlockInfo(modifyBlockCmd, request); ModelAndView mv =
	 * new ModelAndView("configview"); mv.addObject("msgid", aMessage);
	 * //mv.addObject("configview", modifyBlockCmd); status.setComplete();
	 * return mv; }
	 */
	@RequestMapping(value = "/modifyBlockAction", method = RequestMethod.POST)
	public ModelAndView modifyBlock(@ModelAttribute("modifyBlockCmd") BlockForm modifyBlockCmd, BindingResult result, SessionStatus status, Model model, HttpSession session, HttpServletRequest request) {

		ModelAndView mv = null;
		try {
			// stateCode=this.getStateCode(session);
			// ////////code for government order
			// checking///////////////////////////////////
			Map<String, String> hMap1 = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			hMap1 = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 40, 'B');// 40
																									// is
																									// operation
																									// code
																									// for
																									// Block
																									// and
																									// 'B'
																									// is
																									// Entity
																									// Type
			String govtOrderConfig = hMap1.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap1.get("mapUpload");// values==true,false
			// ////////code for government order
			// checking///////////////////////////////////
			// mv = new ModelAndView();
			
			modifyBlockCmd.setDlc(districtCode);
			modifyBlockCmd.setGovtOrderConfig(govtOrderConfig);
			modifyBlockCmd.setOperation('M');
			session.setAttribute("formbean", modifyBlockCmd);
			mv = new ModelAndView("redirect:govtOrderCommon.htm");
			modifyBlockCmd = blockValidator.validateBlockChange(modifyBlockCmd, result);
			if (result.hasErrors()) {
				if (govtOrderConfig != null && mapConfig != null) {
					mv = new ModelAndView("modify_block");
					mv.addObject("modify_block", modifyBlockCmd);
					List<BlockDataForm> listblockDetails = modifyBlockCmd.getListBlockDetails();
					model.addAttribute("size", listblockDetails.size());
					model.addAttribute("listblockDetails", listblockDetails);
					modifyBlockCmd.setListBlockDetails(listblockDetails);
					return mv;
				}
			} else {
				mv = new ModelAndView("redirect:govtOrderCommon.htm");
			}
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");
			}
			if (session.getAttribute("blkOldName") != null) {
				session.removeAttribute("blkOldName");
			}
			return mv;
		}

		// ////
		/*
		 * ModelAndView mav = null; try { Map<String, String> hMap1 = new
		 * HashMap<String, String>(); // Please first check your operation code
		 * then set it in place of 10 hMap1 =
		 * configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode,
		 * 10, 'V');// 10 is operation code for create new String
		 * govtOrderConfig = hMap1.get("govtOrder");//
		 * values==govtOrderUpload,govtOrderGenerate String mapConfig =
		 * hMap1.get("mapUpload");// values==true,false
		 * 
		 * 
		 * mav = new ModelAndView("modify_block"); int blockCode = 0;
		 * List<BlockDataForm> listBlock = new ArrayList<BlockDataForm>();
		 * listBlock = modifyBlockCmd.getListBlockDetails();
		 * Iterator<BlockDataForm> itr = listBlock.iterator(); while
		 * (itr.hasNext()) { BlockDataForm element = (BlockDataForm) itr.next();
		 * blockCode = element.getBlockCode(); } if
		 * (listBlock.get(0).getOrderCodecr() != null) {
		 * blockValidator.validate(modifyBlockCmd, result);
		 * 
		 * //Check for valid MimeType List<CommonsMultipartFile> files = new
		 * ArrayList<CommonsMultipartFile>();
		 * files.add((CommonsMultipartFile)modifyBlockCmd.getFilePathcr());
		 * 
		 * commonValidator.isValidMime(result, request,
		 * modifyBlockCmd.getAttachedFile());
		 * commonValidator.isValidMime(result, request, files);
		 * 
		 * if (result.hasErrors()) { if (govtOrderConfig != null && mapConfig !=
		 * null) { mav.addObject("hideMap", mapConfig);
		 * mav.addObject("govtOrderConfig", govtOrderConfig);
		 * mav.addObject("modifyBlockCmd", modifyBlockCmd); return mav; } } else
		 * { blockService.modifyBlockInfo(modifyBlockCmd, request); mav = new
		 * ModelAndView( "redirect:viewBlockDetailformodify.htm?id=" + blockCode
		 * + "&type=S"); } return mav;
		 * 
		 * }else { String aMessage =
		 * " Please Enter Govt Order Detail At Creation Time or At Change Time First Then Do Correction "
		 * ; mav = new ModelAndView("success"); mav.addObject("msgid",
		 * aMessage); return mav; } } catch (Exception e) { IExceptionHandler
		 * expHandler = ExceptionHandlerFactory .getInstance().create(); String
		 * redirectPath = expHandler.handleSaveRedirectException( request, "",
		 * "label.lgd", 1, e); mav = new ModelAndView(redirectPath); return mav;
		 * }
		 */

	}

	@RequestMapping(value = "/rename_ContriBlock", method = RequestMethod.GET)
	public String renameContriBlockGet(HttpSession httpSession, @ModelAttribute("newblockform") @Valid BlockForm blockForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		Itr = 1;
		BlockForm blockFrm = null;
		String temp = null;
		try {
			blockFrm = (BlockForm) httpSession.getAttribute("newBlockDetail");

			String contriBlock[] = blockFrm.getContributedBlocks().split(",");
			for (int i = 0; i < contriBlock.length; i++) {
				if (contriBlock[i].contains("PART")) {
					if (temp == null) {
						temp = contriBlock[i].replace("PART", "") + ",";
						temp += contriBlock[i].replace("PART", "") + ",";
					}
				}
			}
			temp = temp.substring(0, temp.length() - 1);
			List<Block> listBlock = null;
			listBlock = new ArrayList<Block>();
			listBlock = blockService.getBlockViewList("from Block where blockCode in(" + temp + ") and isactive=true");
			model.addAttribute("blockList", listBlock);
			return "rename_ContriBlock";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@RequestMapping(value = "/rename_ContriBlock", method = RequestMethod.POST)
	public String renameContriBlock(HttpSession httpSession, @ModelAttribute("newblockform") @Valid BlockForm blockForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		Itr = 1;
		List<BlockForm> listBlock = null;
		BlockForm blockFormBean = null;
		try {
			listBlock = new ArrayList<BlockForm>();
			blockFormBean = new BlockForm();
			String[] tempSDCode = blockForm.getAliasEnglish().split(",");// temporarily
																			// getting
																			// code
																			// in
																			// alias
																			// code
			String[] tempEName = blockForm.getBlockNameEnglish().split(",");
			String[] tempLName = blockForm.getBlockNameLocal().split(",");
			for (int i = 0; i < tempEName.length; i++) {
				if (tempEName[i].length() > 0) {
					blockFormBean = null;
					blockFormBean = new BlockForm();
					blockFormBean.setBlockCode(Integer.parseInt(tempSDCode[i]));
					blockFormBean.setBlockNameEnglish(tempEName[i]);
					if (tempLName.length > 0) {
						blockFormBean.setBlockNameLocal(tempLName[i]);
					}
					listBlock.add(blockFormBean);
				}
			}
			httpSession.setAttribute("contriBlock", listBlock);

			blockForm = null;
			blockForm = (BlockForm) httpSession.getAttribute("newBlockDetail");
			model.addAttribute("newblockform", blockForm);
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

		return "redirect:new_block.htm";

	}

	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/saveBlock", method = RequestMethod.POST)
	public ModelAndView saveBlockDetails(HttpSession httpSession, @ModelAttribute("newblockform") BlockForm blockFor, BindingResult bindingResult, Model model, HttpServletRequest request,
			@RequestParam(value = "fileName", required = false) String fileName) throws Exception {
		ModelAndView mav = null;
		boolean persistanceTest = true;
		BlockForm blockForm = null;
		List<Village> villListforFullBlock = null;
		String blockFullList = null;
		int blockCode = 0;
		List<Village> listVillage = new ArrayList<Village>();
		List<Block> listBlock = null;
		List<AttachedFilesForm> validFileMap = null;
		Integer oderCode = null;
		Integer blockId = null;
		int Blocksave = 2;
		int Transactionid = 0;
		char t_type = 'B';
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			blockForm = new BlockForm();
			blockForm = (BlockForm) httpSession.getAttribute("newBlockDetail");
			blockForm.setUserId(userId);
			List<BlockForm> contriRenamedBlockList = new ArrayList<BlockForm>();
			List<BlockForm> blockList = new ArrayList<BlockForm>();
			blockList = (List<BlockForm>) httpSession.getAttribute("contriBlock");
			if (httpSession.getAttribute("contriBlock") != null) {
				for (int i = 0; i < blockList.size(); i++) {
					contriRenamedBlockList.add(blockList.get(i));
				}
			}
			if (blockFullList != null) {
				blockFullList = blockFullList.substring(0, blockFullList.length() - 1);
				villListforFullBlock = new ArrayList<Village>();
				// commented by indu session = sessionFactory.openSession();
				villListforFullBlock = session.createSQLQuery("select v.* from village v inner join block_village bv on v.village_code=bv.village_code where bv.block_code in(" + blockFullList + ") and v.isactive=true").addEntity("v", Village.class)
						.list();
				session.flush();
				for (int i = 0; i < villListforFullBlock.size(); i++) {
					listVillage.add(villListforFullBlock.get(i));
				}
			}
			// commented by indu session = sessionFactory.openSession();
			String bcode = session.createSQLQuery("Select COALESCE(max(block_code),1) from block").list().get(0).toString();
			blockCode = Integer.parseInt(bcode);
			session.flush(); // added by indu
			blockList = session.createQuery("from Block b where b.blockPK.blockCode=" + blockCode).list();
			model.addAttribute("contributedBlocks", listBlock);
			model.addAttribute("contributedVills", listVillage);
			model.addAttribute("newBlock", blockList);
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) httpSession.getAttribute("govtOrderForm");
			AddAttachmentBean addAttachmentBean = (AddAttachmentBean) httpSession.getAttribute("addAttachmentBean");
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList;
			if (blockForm.getGovtOrderConfig().equals("govtOrderUpload")) {
				AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
				attachmentHandler.validateAttachment(addAttachmentBean);
				metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
				attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			} else {
				metaInfoOfToBeAttachedFileList = null;
			}

			

			String commonString = blockService.insertBlock(request, httpSession, blockForm);
			if (commonString != null) {
				
				tx = session.beginTransaction();
				
				String[] subStringsArray = commonString.split(",");
				oderCode = Integer.parseInt(subStringsArray[1]);
				blockId = Integer.parseInt(subStringsArray[0]);
				blockForm.setBlockCode(blockId);
				String tid = subStringsArray[2];
				Transactionid = Integer.parseInt(tid);
				if (httpSession.getAttribute("validFileMap") != null) {
					validFileMap = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap");
					blockService.saveDataInMap(blockForm, validFileMap, httpSession, session);
				}
				govtOrderForm.setOrderCode(oderCode);
				govtOrderForm.setOrderNo(blockForm.getOrderNo());
				govtOrderForm.setEffectiveDate(blockForm.getEffectiveDate());
				govtOrderForm.setGazPubDate(blockForm.getGazPubDate());
				govtOrderForm.setOrderDate(blockForm.getOrderDate());
				govtOrderForm.setAttachFile1(blockForm.getAttachFile1());

				List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, bindingResult);

				if (blockForm.getGovtOrderConfig().equals("govtOrderGenerate"))
					govtOrderService.saveDataInAttachment(govtOrderForm, null, httpSession, oderCode, session);
				else {

					AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
					attachmentHandler.validateAttachment(addAttachmentBean);
					metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
					attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
					govtOrderService.saveDataInAttachmentWithOrderCode(oderCode, metaInfoOfToBeAttachedFileList, session);
				}
				// govtOrderService.saveDataInAttachment(govtOrderForm,null,httpSession,oderCode,session);
				// blockService.saveDataInAttach(govtOrderForm,validFileGovtUpload,blockId,request.getSession(),session);

				tx.commit();
				Blocksave = 1;
				EmailSmsThread est = new EmailSmsThread(Transactionid, t_type, emailService);
				est.start();
			}

		} catch (Exception e) {
			tx.rollback();
			ReleaseResources.doReleaseResources(session);
			Blocksave = 0;
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} finally {
			httpSession.removeAttribute("newBlockDetail");
			httpSession.removeAttribute("contriBlock");
			httpSession.removeAttribute("validFileMap");
			httpSession.removeAttribute("formbean");
			httpSession.removeAttribute("addAttachmentBean");
			httpSession.removeAttribute("govtOrderForm");
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		if (Blocksave == 1) {
			mav = new ModelAndView("success");
			mav.addObject("msgid", "New Block Created Successfully");
		} else {
			mav = new ModelAndView("success");
			mav.addObject("msgid", "Block Creation failure please check logs");
		}
		return mav;
	}

	@RequestMapping(value = "/cancelBlock", method = RequestMethod.GET)
	public String cancel(HttpSession httpSession, @ModelAttribute("newblockform") SubDistrictForm sdForm, HttpServletRequest request, Model model) {
		try {
			httpSession.removeAttribute("newBlockDetail");
			httpSession.removeAttribute("contriBlock");
			Itr = 0;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}
		return "redirect:home.htm";
	}

	@RequestMapping(value = "/add_AnotherBlock", method = RequestMethod.GET)
	public String addAnotherSD(HttpSession httpSession, HttpServletRequest request, @ModelAttribute("newblockform") BlockForm blockForm, Model model) {
		Itr = 1;

		/*
		 * List<District> distList = blockService
		 * .getDistrictListbyStateCode(stateCode);
		 * model.addAttribute("distList", distList); //code to get sub district
		 * list---------- BlockForm blockFrm = (BlockForm)
		 * httpSession.getAttribute("newBlockDetail"); String
		 * temp=blockFrm.getContributedBlocks().replace("PART",
		 * "").replace("FULL", ""); String[]
		 * temp1=blockFrm.getContributedBlocks().split(","); List<Block>
		 * listBlock=null; listBlock=new ArrayList<Block>();
		 * listBlock=blockService
		 * .getBlockViewList("from Block where blockCode IN (" + temp +
		 * ") and isactive=true"); for(int i=0; i<listBlock.size();i++){
		 * listBlock
		 * .get(i).setBlockNameEnglish(listBlock.get(i).getBlockNameEnglish()
		 * +" (" + (temp1[i].contains("FULL")?"FULL)":"PART)"));
		 * listBlock.get(i).setAliasEnglish(listBlock.get(i).getBlockCode()
		 * +(temp1[i].contains("FULL")?"FULL":"PART")); //temporarily holding
		 * block code in alias } model.addAttribute("blockList", listBlock);
		 * model.addAttribute("newblockform", blockFrm);
		 */
		try {
			httpSession.removeAttribute("contriBlock");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}

		return "redirect:new_block.htm";
	}

	

	// modify Block change Get Method
	@RequestMapping(value = "/modifyBlockchangebyId")
	public ModelAndView modifyBlock(@ModelAttribute("modifyBlockCmd") BlockForm modifyBlockCmd, Model model, HttpSession session, @RequestParam(value = "disturb", required = false) String disturb, HttpServletRequest request) {
		int operationCode = 40;
		ModelAndView mv = null;
		int blockCode = modifyBlockCmd.getBlockId();
		try {
			setGlobalParams(session);
			char operation = 'M';
			request.setAttribute("stateCode", stateCode);
			List<BlockDataForm> listblockDetails = blockService.getBlockDetailsModify(blockCode);
			EsapiEncoder.encode(listblockDetails);
			Map<String, String> hMap = new HashMap<String, String>();
			hMap = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
			String message = hMap.get("message");
			// System.out.println("hmap--" + hMap);
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			model.addAttribute("size", listblockDetails.size());
			if (govtOrderConfig != null) {

				mv = new ModelAndView("modifyBlockchange");
				mv.addObject("govtOrderConfig", govtOrderConfig);
				model.addAttribute("listblockDetails", listblockDetails);
				model.addAttribute("disturb", disturb);
				modifyBlockCmd.setListBlockDetails(listblockDetails);
				mv.addObject("modifyBlockCmd", modifyBlockCmd);

			} else {
				mv = new ModelAndView("success");
				mv.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	// Attachment File
	public void setAttachmentDetails(BlockForm blockform, HttpServletRequest request) {
		AttachmentMaster attachmentList;
		try {
			attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);

			blockform.setAllowedFileType(attachmentList.getFileType());
			blockform.setAllowedIndividualFileSize(attachmentList.getIndividualFileSize());
			blockform.setAllowedNoOfAttachment(attachmentList.getFileLimit());
			blockform.setAllowedTotalFileSize(attachmentList.getTotalFileSize());
			blockform.setUploadLocation(attachmentList.getFileLocation());
			blockform.setRequiredTitle(attachmentList.getRequireTitle());
			blockform.setUniqueTitle(attachmentList.getUniqueTitle());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);

		}
	}

	

	@RequestMapping(value = "/modifyBlockChangeAction", method = RequestMethod.POST)
	public String modifyVillageChange(@ModelAttribute("modifyVillageCmd") BlockForm blkForm, BindingResult result, SessionStatus status, Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb) {
		String mv = null;
		String blkOldName = (String) session.getAttribute("blkOldName");

		try {

			String viewDistrictHistory = null;
			int blockCode = 0;
			//int villageVersion = 0;
			@SuppressWarnings("unused")
			boolean saveSuccess = false;
			AddAttachmentBean addAttachmentBean = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			blkForm.setStateCode(stateCode);

			BlockForm blockFrm = (BlockForm) session.getAttribute("formbean");
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");
			
			if (session.getAttribute("addAttachmentBean") != null) {
				addAttachmentBean = (AddAttachmentBean) session.getAttribute("addAttachmentBean");
				attachmentHandler.validateAttachment(addAttachmentBean);
			}
			
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
			blockFrm.setBlockNameEnglish(blkOldName);
			if (metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {

				saveSuccess = blockService.changeBlock(blockFrm, govtOrderForm, metaInfoOfToBeAttachedFileList, request, userId);
				attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			} else{
				blockService.changeBlockforTemplate(blockFrm, govtOrderForm, request, session);
			}
			
			
			List<BlockDataForm> listVillage = new ArrayList<BlockDataForm>();
			listVillage = blockFrm.getListBlockDetails();
			Iterator<BlockDataForm> itr = listVillage.iterator();
			while (itr.hasNext()) {
				BlockDataForm element = (BlockDataForm) itr.next();
				blockCode = element.getBlockCode();
				//villageVersion = element.getBlockVersion();
			}
			model.addAttribute("blockView", blockFrm);
			session.setAttribute("BlockCode", blockCode);
			viewDistrictHistory = "redirect:viewBlockDetailforChange.htm?id=" + blockCode + "&type=S";
			// mv = new ModelAndView("modify_block");
			return viewDistrictHistory;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			session.removeAttribute("formbean");
			session.removeAttribute("govtOrderForm");
			session.removeAttribute("blkOldName");
			if (session.getAttribute("BlockCode") != null) {
				session.removeAttribute("BlockCode");
			}
			return mv;
		}

	}

	@RequestMapping(value = "/viewBlockDetailforChange")
	public ModelAndView viewBlockDetailforChange(@ModelAttribute("blockView") BlockForm blockView, Model model, HttpSession session, HttpServletRequest request, @RequestParam("id") Integer blockCode, @RequestParam("type") String type) {
		ModelAndView mv = null;

		if (blockCode == null) {
			blockCode = (Integer) session.getAttribute("id");
		}
		//GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

		try {
			List<BlockDataForm> listBlockDetails = blockService.getBlockDetailsModify(blockCode);
			mv = new ModelAndView("view_blockdetail");
			/*
			 * listBlockDetails.get(0).setOrderNocr(govtOrderForm.getOrderNo());
			 * listBlockDetails
			 * .get(0).setOrderDatecr(govtOrderForm.getOrderDate());
			 * listBlockDetails
			 * .get(0).setOrdereffectiveDatecr(govtOrderForm.getEffectiveDate
			 * ());
			 * listBlockDetails.get(0).setGazPubDatecr(govtOrderForm.getGazPubDate
			 * ());
			 */
			model.addAttribute("successMsg", "The block was modified successfully");
			blockView.setListBlockDetails(listBlockDetails);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} finally {
			session.removeAttribute("formbean");
			session.removeAttribute("BlockCode");
			session.removeAttribute("govtOrderForm");
			session.removeAttribute("blkOldName");

		}
		return mv;
	}

	private List<MapAttachment> getMapAttachmentListbyBlock(BlockForm subDistrictForm, Integer subdistrictCode, List<BlockDataForm> listsubdistrictDetails) {
		List<MapAttachment> mapAttachmentList = null;
		try {
			char entityType = 'B';
			mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(subdistrictCode, entityType);
		} catch (Exception ex) {
			log.debug("Exception" + ex);
		}
		return mapAttachmentList;
	}

	// View Block Part
	@RequestMapping(value = "/viewblock", method = RequestMethod.GET)
	public ModelAndView showBlockPage(@ModelAttribute("blockView") BlockForm blockView, Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = null;
		try {
			setGlobalParams(httpSession);
			mav = new ModelAndView("view_block");
			mav.addObject("blockView", new BlockForm());
			
			
			if (districtCode > 0) {
				// Changes by ripunj for localbody Draft mode
				List<Block> blockList = blockService.getBlockList(districtCode);
				blockView.setDlc(districtCode);
				model.addAttribute("SEARCH_BLOCK_RESULTS_KEY", blockList);
				blockView.setOffset(1);
				blockView.setLimit(1000);
				model.addAttribute("viewPage", "abc");
				model.addAttribute("offsets", 0);
				model.addAttribute("limits", 1000);
				mav.addObject("blockView", blockView);
				return mav;
			}

			List<District> districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);

			model.addAttribute("viewPage", "");
			model.addAttribute("flag", "stateCode");

			mav.addObject("districtList", districtList);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewblock", method = RequestMethod.POST)
	public ModelAndView getBlockPage(@ModelAttribute("blockView") BlockForm blockView, BindingResult result, Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = null;
		try {

			
			mav = new ModelAndView("view_block");
			List<District> districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
			mav.addObject("districtList", districtList);
			blockValidator.viewValidateBlock(blockView, result);
			if (result.hasErrors()) {
				result.getAllErrors();

				//stateCode = Integer.parseInt(statecode);

				model.addAttribute("viewPage", "");
				model.addAttribute("flag", "stateCode");

				return mav;
			}
			String strDistrictCode = blockView.getDistrictNameEnglish();
			if (!strDistrictCode.equals("") && strDistrictCode != null) {

				Integer districtCode = Integer.parseInt(strDistrictCode);
				httpSession.setAttribute("districtCode", districtCode);

				List<Block> blockList = blockService.getBlockListbyDistrictCodeForLocalBody(districtCode);
				model.addAttribute("SEARCH_BLOCK_RESULTS_KEY", blockList);
				blockView.setDlc(districtCode);
				blockView.setOffset(1);
				blockView.setLimit(1000);
				model.addAttribute("viewPage", "abc");
				model.addAttribute("offsets", 0);
				model.addAttribute("limits", 1000);
				mav.addObject("blockView", blockView);

				return mav;
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	// NEW BLOCK MODIFY
	List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
	List<Attachment> attachmentList = new ArrayList<Attachment>();

	@RequestMapping(value = "/modifyBlockCrbyId")
	public ModelAndView modifyBlockCorrection(@ModelAttribute("modifyBlockCmd") BlockForm modifyBlockCmd, HttpSession session, Model model, HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		String strBlockCode = request.getParameter("id");
		String reqWarningFlag = request.getParameter("warningEntiesFlag");
		session.setAttribute("reqWarningFlag", reqWarningFlag);
		session.setAttribute("id", strBlockCode);
		Integer blockCode = modifyBlockCmd.getBlockId();
		if (blockCode == null) {
			blockCode = Integer.parseInt(strBlockCode);
		}
		try {
			setGlobalParams(session);
			List<BlockDataForm> listblockDetails = blockService.getBlockDetailsModify(blockCode);
			if (!listblockDetails.isEmpty()) {
				BlockDataForm element = listblockDetails.get(0);
				Integer orderCode = element.getOrderCodecr();
				boolean mandatoryFlag = true;

				int operationCode = 40;
				char operation = 'M';
				
				request.setAttribute("stateCode", stateCode);
				mv = new ModelAndView("modifyBlockCorrection");

				Map<String, String> govConfigMap = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				Map<String, String> mapConfigMap = govtOrderService.getMapConfiguration(stateCode, operationCode, 'B');
				String message = govConfigMap.get("message");
				String message2 = mapConfigMap.get("message");
				String govtOrderConfig = govConfigMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = mapConfigMap.get("mapUpload");// value=true,false

				if (govtOrderConfig == null && mapConfig == null) {
					String aMessage = "Please Configure Map and Govt Order ";
					mv = new ModelAndView("success");
					mv.addObject("msgid", aMessage);
					return mv;
				} else if (govtOrderConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message);
					return mv;
				} else if (mapConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message2);
					return mv;
				}

				modifyBlockCmd.setOrderCode(element.getOrderCode());
				modifyBlockCmd.setOrderDate(element.getOrderDate());
				modifyBlockCmd.setOrderNo(element.getOrderNo());
				modifyBlockCmd.setEffectiveDate(element.getEffectiveDate());
				modifyBlockCmd.setGazPubDate(element.getGazPubDate());
				setAttachmentDetails(modifyBlockCmd, request);

				if (orderCode != null) {
					attachmentList = govtOrderService.getAttachmentsbyOrderCode(orderCode);
					if (attachmentList.size() <= 0) {
						mandatoryFlag = false;
					}

				} else {
					mandatoryFlag = false;
					attachmentList = new ArrayList<Attachment>();
				}

				mapAttachmentList = getMapAttachmentListbyBlock(modifyBlockCmd, blockCode, listblockDetails);

				modifyBlockCmd.setListBlockDetails(listblockDetails);
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				mv.addObject("mapConfig", mapConfig);
				mv.addObject("govtOrderConfig", govtOrderConfig);
				mv.addObject("modifySubDistrictCmd", modifyBlockCmd);
				mv.addObject("govtfilecount", attachmentList.size());
				mv.addObject("mapfilecount", mapAttachmentList.size());
				mv.addObject("mandatoryFlag", mandatoryFlag);
				session.setAttribute("mandatoryFlag", mandatoryFlag);
				mv.addObject("pageWarningEntiesFlag", element.getWarningFlag());
				return mv;
			} else {

				String aMessage = "Sorry Data Not Found For Your Selection ";
				mv = new ModelAndView("success");
				mv.addObject("msgid", aMessage);
				return mv;
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	// Modify Correction Post Method
	@RequestMapping(value = "/modifyBlockCrAction", method = RequestMethod.POST)
	public ModelAndView modifyBlockcorrection(@ModelAttribute("modifyBlockCmd") BlockForm modifyBlockCmd, BindingResult result, SessionStatus status, Model model, HttpSession session, HttpServletRequest request) throws Exception {

		ModelAndView mav = null;
		try {
			List<BlockDataForm> listblockDetails = modifyBlockCmd.getListBlockDetails();

			if (!listblockDetails.isEmpty()) {
				BlockDataForm element = listblockDetails.get(0);

				int operationCode = 40;
				char operation = 'M';
				char entityType = 'B';
				

				Map<String, String> mapGovOrderConfig = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				Map<String, String> mapMapConfig = govtOrderService.getMapConfiguration(stateCode, operationCode, entityType);
				String govtOrderConfig = mapGovOrderConfig.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = mapMapConfig.get("mapUpload");// values==true,false

				modifyBlockCmd.setOrderNocr(element.getOrderNocr());
				modifyBlockCmd.setOrderDatecr(element.getOrderDatecr());
				modifyBlockCmd.setOrdereffectiveDatecr(element.getOrdereffectiveDatecr());
				modifyBlockCmd.setGazPubDatecr(element.getGazPubDatecr());
				modifyBlockCmd.setUserId(userId.intValue());

				mav = new ModelAndView("modifyBlockCorrection");
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				blockValidator.validateBlockCorrection(modifyBlockCmd, result);
				int filecount =modifyBlockCmd.getGovtfilecount()!=null && modifyBlockCmd.getGovtfilecount().length()>0?Integer.parseInt(modifyBlockCmd.getGovtfilecount()):0;
				if(filecount<0) {
					commonValidator.isValidMimeforGovOrderLandRegion(result, request, modifyBlockCmd.getAttachFile1());
					commonValidator.isValidMimeforMapLandRegion(result, request, modifyBlockCmd.getAttachFile2());
				}
			

				if (result.hasErrors()) {
					String Cordinate = null;
					if (element.getCordinate() != null) {
						if (!element.getCordinate().trim().equals("")) {
							Cordinate = element.getCordinate();
						}
					}
					element.setCordinate(Cordinate);
					listblockDetails.set(0, element);
					model.addAttribute("attachmentList", attachmentList);
					modifyBlockCmd.setListBlockDetails(listblockDetails);
					mav.addObject("modifyBlockCmd", modifyBlockCmd);
					mav.addObject("govtfilecount", modifyBlockCmd.getGovtfilecount());
					request.setAttribute("attachmentList", attachmentList);
					request.setAttribute("mapAttachmentList", mapAttachmentList);
					mav.addObject("mapConfig", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					mav.addObject("govtfilecount", attachmentList.size());
					mav.addObject("mapfilecount", mapAttachmentList.size());
					if (session.getAttribute("mandatoryFlag") != null) {
						mav.addObject("mandatoryFlag", session.getAttribute("mandatoryFlag"));

					}
					return mav;
				}
				if (session.getAttribute("mandatoryFlag") != null) {
					session.removeAttribute("mandatoryFlag");
				}
				List<AttachedFilesForm> validFileGovtUpload = new ArrayList<AttachedFilesForm>();
				List<AttachedFilesForm> validFileMap = new ArrayList<AttachedFilesForm>();
				boolean delflag = false;
				if (govtOrderConfig.equals("govtOrderUpload")) {

					AddAttachmentBean govAttachmentBean = getAttachmentBean(modifyBlockCmd, request);

					String deleteAttachmentId[] = null;
					if (govAttachmentBean != null) {
						deleteAttachmentId = govAttachmentBean.getDeletedFileID();
						validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govAttachmentBean, modifyBlockCmd.getAttachFile1(), result, mav);
					}

					if (mapConfig.equals("true")) {

						AddAttachmentBean mapAttachmentBean = getAttachmentBeanforMap(modifyBlockCmd, request);
						validFileMap = fileUploadUtility.ValidateAndUploadFileMap2(request, mapAttachmentBean, modifyBlockCmd.getAttachFile2(), result, mav);

						String deleteID[] = mapAttachmentBean.getDeletedFileID();
						if (deleteID != null && deleteID.length > 0) {
							delflag = true;
						}
						if (validFileMap != null) {
							boolean warningFlag = fileUploadUtility.validWarninngFlagMapUpload(mapAttachmentList, validFileMap);
							if (warningFlag) {
								modifyBlockCmd.setWarningFlag(!warningFlag);
							}
						}

						blockService.modifyBlockCrInfo(modifyBlockCmd, request, validFileGovtUpload, validFileMap, delflag, deleteAttachmentId, session);

					} else {

						blockService.modifyBlockCrInfo(modifyBlockCmd, request, validFileGovtUpload, null, delflag, deleteAttachmentId, session);

					}

				}

				else if (govtOrderConfig.equals("govtOrderGenerate")) {
					if (mapConfig.equals("true")) {

						AddAttachmentBean mapAttachmentBean = getAttachmentBeanforMap(modifyBlockCmd, request);
						validFileMap = fileUploadUtility.ValidateAndUploadFileMap2(request, mapAttachmentBean, modifyBlockCmd.getAttachFile2(), result, mav);

						String deleteID[] = mapAttachmentBean.getDeletedFileID();
						if (deleteID != null && deleteID.length > 0) {
							delflag = true;
						}
						if (validFileMap != null) {
							boolean warningFlag = fileUploadUtility.validWarninngFlagMapUpload(mapAttachmentList, validFileMap);
							if (warningFlag) {
								modifyBlockCmd.setWarningFlag(!warningFlag);
							}
						}

						blockService.modifyBlockCrInfo(modifyBlockCmd, request, null, validFileMap, delflag, null, session);

					} else {
						blockService.modifyBlockCrInfo(modifyBlockCmd, request, null, null, delflag, null, session);
					}
				}

				Object warningEntiesFlag = session.getAttribute("reqWarningFlag");
				session.removeAttribute("warningEntiesFlag");
				if (warningEntiesFlag != null && "W".equalsIgnoreCase(warningEntiesFlag.toString())) {
					mav = new ModelAndView("redirect:viewResolveEntitiesinDisturbedStateLR.htm?warningEntiesFlag=" + warningEntiesFlag.toString());
				} else {
					mav = new ModelAndView("redirect:viewBlockDetailformodify.htm?id=" + element.getBlockCode() + "&type=B");
				}

				return mav;

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "No Record(s) available for corrction.");
				return mav;
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	private AddAttachmentBean getAttachmentBean(BlockForm blockform, HttpServletRequest request) {

		List<Attachment> alreadyAttachList = null;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0;
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			Integer orderCode = 0;
			List<BlockDataForm> listBlock = new ArrayList<BlockDataForm>();
			listBlock = blockform.getListBlockDetails();
			Iterator<BlockDataForm> itr = listBlock.iterator();
			while (itr.hasNext()) {
				BlockDataForm element = (BlockDataForm) itr.next();
				orderCode = element.getOrderCodecr();
			}

			if (orderCode != null) {

				alreadyAttachList = govtOrderService.getAttachmentsbyOrderCode(orderCode);

				noOFAlreadyAttachedFiles1 = alreadyAttachList.size(); // Already
																		// Attach
																		// Number
																		// Of
																		// File.
				// Already attached file total size.
				Iterator<Attachment> attachmentsIterator1 = alreadyAttachList.iterator();
				while (attachmentsIterator1.hasNext()) {
					Attachment att = (Attachment) attachmentsIterator1.next();
					long fileSize = att.getFileSize();
					totalSizeOFAlreadyAttachedFiles1 = totalSizeOFAlreadyAttachedFiles1 + fileSize;
				}
			}

			AddAttachmentBean addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(blockform.getAttachFile1());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			addAttachmentBeanTwo.setFileTitle(blockform.getFileTitle1());
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File
																		// Mime
																		// Type
																		// List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File
																								// Id
																								// array
																								// to
																								// be
																								// deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File
																									// Name
																									// Array
																									// To
																									// Be
																									// Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted
																										// File
																										// Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
																			// of
																			// Mandatory
																			// file.

			return addAttachmentBeanTwo;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return null;
		}

	}

	private AddAttachmentBean getAttachmentBeanforMap(BlockForm blockForm, HttpServletRequest request) {
		try {
			List<MapAttachment> alreadyAttachList = null;
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);

			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0;
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;

			if (blockForm.getOrderCode() != null) {

				alreadyAttachList = govtOrderService.getMapAttachmentListbyEntity(blockForm.getBlockCode(), 'B');

				noOFAlreadyAttachedFiles1 = alreadyAttachList.size(); // Already
																		// Attach
																		// Number
																		// Of
																		// File.
				// Already attached file total size.
				Iterator<MapAttachment> attachmentsIterator1 = alreadyAttachList.iterator();
				while (attachmentsIterator1.hasNext()) {
					MapAttachment att = (MapAttachment) attachmentsIterator1.next();
					long fileSize = att.getFileSize();
					totalSizeOFAlreadyAttachedFiles1 = totalSizeOFAlreadyAttachedFiles1 + fileSize;
				}
			}

			AddAttachmentBean addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(blockForm.getAttachFile2());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			addAttachmentBeanTwo.setFileTitle(blockForm.getFileTitle2());
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File
																		// Mime
																		// Type
																		// List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID2"));// 10.File
																								// Id
																								// array
																								// to
																								// be
																								// deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName2"));// 11.File
																									// Name
																									// Array
																									// To
																									// Be
																									// Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList2"));// 12.Deleted
																										// File
																										// Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
																			// of
																			// Mandatory
																			// file.

			return addAttachmentBeanTwo;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
	}

	@RequestMapping(value = "/viewBlockDetailformodify")
	public ModelAndView viewBlockDetail(@ModelAttribute("blockView") BlockForm blockView, Model model, HttpSession session, HttpServletRequest request, @RequestParam("id") Integer blockCode, @RequestParam("type") String type) {
		ModelAndView mv = null;

		try {
			List<BlockDataForm> listBlockDetails = blockService.getBlockDetailsModify(blockCode);
			mv = new ModelAndView("success");
			model.addAttribute("msgid", "The Block was modified successfully ");
			blockView.setListBlockDetails(listBlockDetails);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

		return mv;

	}
	
	/**
	 * The {@code showBlockPanchayatMappingWithBlock} used to show list of block panchayat needs to be 
	 * mapped with blocks, if blocks are not available, facilitated to create a new block. 
	 * @param model
	 * @param request
	 * @return
	 * @author Vinay Yadav (Created on 30/12/2015)
	 */
	@RequestMapping(value = "/showBPSyncWithBlock", method = RequestMethod.GET)
	public ModelAndView showBlockPanchayatMappingWithBlock(Model model, HttpServletRequest request, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("show_bp_block_mapping");
		try {
			setGlobalParams(session);
			List<District> districtList = districtService.getDistrictList(stateCode);
			mav.addObject("districtList", districtList);
			mav.addObject("blockPanchayatSyncBlock", new BlockPanchayatSyncBlock());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value="/assignVillagestoBlock",method=RequestMethod.GET)
	private ModelAndView blockwiseVillageMapping(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("blockVillage") BlockVillage blockVillage)
	{
		ModelAndView mav= new ModelAndView("BLOCK_TO_VILLAGES_MAPPING");
		try{
			Boolean configVillagePartFullMap =false ;
			ConfigurationBlockVillageMapping configBlockVillageMapping1 =  stateService.getconfigureBlockVillage(null ,stateCode);
				 if(configBlockVillageMapping1 != null ) {
					 if(configBlockVillageMapping1.getCoverageType()) {
						 configVillagePartFullMap=true;
					 
					 }else {
						 mav = new ModelAndView("success");
						 mav.addObject("msgid", "Kindly Configure the Block Village(Partially/Fully) Form First ");
						 return mav; 
					 }
				}
			 model.addAttribute("configVillagePartFullMap",configVillagePartFullMap);
			if(districtCode>0){
			model.addAttribute("blockList",blockService.getBlockListbyDistrictCodeForLocalBody(districtCode));
			model.addAttribute("isDistrict",Boolean.TRUE);
			}else{
				model.addAttribute("districtList",districtService.getDistrictListbyCriteria(stateCode, "S"));
			}
			}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	
	@RequestMapping(value="/saveBlockVillageMapping",method=RequestMethod.POST)
	private ModelAndView blockwiseVillageMappingPost(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("blockVillage") BlockVillage blockVillage, BindingResult binding)
	{
		ModelAndView mav= new ModelAndView("success");
		try{
			
			Boolean configVillagePartFullMap =false ;
			ConfigurationBlockVillageMapping configBlockVillageMapping1 =  stateService.getconfigureBlockVillage(null ,stateCode);
				 if(configBlockVillageMapping1 != null ) {
					 if(configBlockVillageMapping1.getCoverageType()) {
						 configVillagePartFullMap=true;
					 
					 }else {
						 mav = new ModelAndView("success");
						 mav.addObject("msgid", "Kindly Configure the Block Village(Partially/Fully) Form First ");
						 return mav; 
					 }
				}
			
			if(configVillagePartFullMap) {
			blockValidator.validateBlocktoVillageMapping(blockVillage, binding , stateCode);
			 }
			if (binding.hasErrors()) {
				 mav= new ModelAndView("BLOCK_TO_VILLAGES_MAPPING");
				if(districtCode>0){
					model.addAttribute("blockList",blockService.getBlockListbyDistrictCodeForLocalBody(districtCode));
					model.addAttribute("isDistrict",Boolean.TRUE);
					}else{
						model.addAttribute("districtList",districtService.getDistrictListbyCriteria(stateCode, "S"));
					}
				model.addAttribute("configVillagePartFullMap",configVillagePartFullMap);
				return mav;
			}
			if(configVillagePartFullMap) {
				Scanner scanner = null;

				String addBlkVillMapping = blockVillage.getVillageMappedListNew();
				String  delBlkVillMapping =  blockVillage.getVillageMappedListDel();
				String listOfVlc = new String();
				
				String listOfVlcDel = new String();
				String unMappListVlc = new String();
				
				String vlcList = new String();
                  if(addBlkVillMapping !=null) {
				scanner = new Scanner(addBlkVillMapping);
				
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
				Character coverageType;
				Integer vlc = null;
				Integer vlc_version = null;

				String villDet[] = scanner.next().split("-");
				vlc = Integer.parseInt(villDet[0]);
				/* vlc_version = Integer.parseInt(villDet[1]);
				*/ String villDet1[] = villDet[1].split("_");
				vlc_version = Integer.parseInt(villDet1[0]);
				coverageType =villDet1[1].charAt(0);

				String s[]=blockVillage.getBlockCode().split("-");
				listOfVlc = listOfVlc +vlc+'_'+coverageType+',';
				//str.append(vlc+'_'+coverageType+',');
				}
				
				 vlcList =listOfVlc.substring(0, listOfVlc.length()-1);
				
                  }if(delBlkVillMapping !=null) {
      				scanner = new Scanner(delBlkVillMapping);
    				
    				scanner.useDelimiter(",");
    				while (scanner.hasNext()) {
    				
    				Integer vlcdel = null;

    				String villDet[] = scanner.next().split("-");
    				vlcdel = Integer.parseInt(villDet[0]);
    				
    				listOfVlcDel = listOfVlcDel +vlcdel+',';
    				//str.append(vlc+'_'+coverageType+',');
    				}
    				
    				unMappListVlc =listOfVlcDel.substring(0, listOfVlcDel.length()-1);
    				
                      }
				String blockDetails[]=blockVillage.getBlockCode().split("-");
				String blc =blockDetails[0];
				blockVillage.setUserId(userIdLong);
				blockVillage.setBlc(Integer.valueOf(blc));
				if(blockService.saveBlockVillageMappingLb(blockVillage ,vlcList, unMappListVlc)){
				mav.addObject("msgid", "Block to villages mapping saved successfully");
				}else{
					mav.addObject("msgid", "Block to villages mapping failed..");
				}
			}
			else if(!configVillagePartFullMap) {
				blockVillage.setUserId(userIdLong);
				if(blockService.saveBlockVillageMapping(blockVillage)){
				mav.addObject("msgid", "Block to villages mapping saved successfully");
				}else{
					mav.addObject("msgid", "Block to villages mapping failed..");
				}
			}
			
			}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	
	/**
	 * The {@code errorHandler} returns error path and saved required stack trace. 
	 * @param request
	 * @param e
	 * @return
	 */
	private String errorHandler(HttpServletRequest request, Exception e){
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		return expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
	}
	
	@RequestMapping(value = "/modifyBlockChangeEffectiveDate", method = RequestMethod.POST)
	public ModelAndView modifyVillageChangeEffectiveDate(@ModelAttribute("modifyBlockCmd") BlockForm modifyBlockCmd,@RequestParam("blockId") String  id, Model model, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("CHANGE_EFFECTIVE_DATE_OF_BLOCK");
		try {
			model.addAttribute("blcCode",(id!=null && id.length()>0)?Integer.parseInt(id):0);
			model.addAttribute("curDate",new Date());
			
			//model.addAttribute("villageDetail",villageService.getVillageDetailsModify(modifyVillageCmd.getVillageId()));
				} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/saveEffectiveDateBlock", headers="Accept=application/json", method = RequestMethod.POST)
	public @ResponseBody Response saveEffectiveDate(@RequestBody EffectiveDateList getEntityEffectiveDateList,HttpServletRequest request) {
		HttpSession httpsession= request.getSession();
		setGlobalParams(httpsession);
		return blockService.saveEffectiveDateEntityBlock(getEntityEffectiveDateList,userId.intValue(),stateCode);
	}
	
	@RequestMapping(value = "/reactivateBlock", method = RequestMethod.GET)
	public ModelAndView reactivateLocalBody( HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("reactivateInvalidatedBlock");
			mav.addObject("operationCode", 39);
		}  catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	
}
