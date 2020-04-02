package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.forms.BlockDataForm;
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.forms.ConvertRLBtoTLBForm;
import in.nic.pes.lgd.forms.ConvertRLBtoULBForm;
import in.nic.pes.lgd.forms.ConvertTLBtoRLBForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.LocalGovtTypeForm;
import in.nic.pes.lgd.forms.ShiftBlockForm;
import in.nic.pes.lgd.forms.ShiftDistrictForm;
import in.nic.pes.lgd.forms.ShiftSubDistrictForm;
import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.forms.StateForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.BlockService;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.GovtOrderTemplateService;
import in.nic.pes.lgd.service.IAddAttachmentService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.DocumentConverter;
import in.nic.pes.lgd.validator.GovernmentOrderValidator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.itextpdf.text.DocumentException;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class GovtOrderController { // NO_UCD (unused code)
	
	private static final Logger log = Logger.getLogger(GovtOrderController.class);

	@Autowired
	FileUploadUtility fileUploadUtility;

	@Autowired
	GovtOrderTemplateService govtOrderTemplateService;

	@Autowired
	private GovernmentOrderService govtOrderService;

	@Autowired
	GovernmentOrderValidator govtOrderValidator;

	@Autowired
	VillageService villageService;

	@Autowired
	DistrictService districtService;

	@Autowired
	SubDistrictService subdistrictService;

	@Autowired
	StateService stateService;

	@Autowired
	IAddAttachmentService addAttachmentService;

	@Autowired
	LocalGovtBodyService localGovtBodyService;

	@Autowired
	EmailService emailService;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	ConvertLocalbodyService convertLocalbodyService;
	
	@Autowired
	BlockService blockService;
	
	@Autowired
	DocumentConverter docconverter;
	
	private Integer stateCode;
	
	private Integer userId;
	
	private Integer districtCode;
	
	private String stateNameEng;
	

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "label.lgd", "userId", 1, e);
		}
	}
	
	
	/**
	 * 
	 * @param session
	 */
	private void setGlobalParams(HttpSession session) throws Exception{
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		if(districtCode!=null && districtCode>0){
			stateCode=stateService.getStateCode(districtCode);
		}
		stateNameEng=stateService.getStateNameEnglish(stateCode);
		userId = sessionObject.getUserId().intValue();
		
	}
	

	@RequestMapping(value = "/govtOrderCommon")
	public ModelAndView showGovtOrderForm(@ModelAttribute("governmentOrder") GovernmentOrderForm govtform, BindingResult result, SessionStatus status,
			Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mv;
		try {
			mv = new ModelAndView("govtOrderCommon");
			mv.addObject("governmentOrder", govtform);
			if (httpSession.getAttribute("formbean") instanceof VillageForm) {
				VillageForm villageForm = (VillageForm) httpSession.getAttribute("formbean");

				List<GovernmentOrderTemplate> templateList = new ArrayList<GovernmentOrderTemplate>();
				templateList = new ArrayList<GovernmentOrderTemplate>();
				String govtOrderConfig = null;
				char operation = villageForm.getOperation();
				govtOrderConfig = villageForm.getGovtOrderConfig();
				if (govtOrderConfig.equals("govtOrderGenerate")) {
					if (operation == 'C') {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(10);
						mv.addObject("templateList", templateList);
						if (villageForm.getAttachFile1() != null) {
							List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMap(request, villageForm, result, mv);
							httpSession.setAttribute("validFileMap", validFileMap);

						}
					} else if (operation == 'I') {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(14);
						mv.addObject("templateList", templateList);
					} else if (operation == 'M') {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(11);
						mv.addObject("templateList", templateList);
					}
				} else {
					if (villageForm.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMap(request, villageForm, result, mv);
						httpSession.setAttribute("validFileMap", validFileMap);

					}
				}
				mv.addObject("govtOrderConfig", govtOrderConfig);
				if(villageForm.getPreVersionMaxEffDate()!=null)
				 mv.addObject("preVersionMaxEffDate",villageForm.getPreVersionMaxEffDate());
			} else if (httpSession.getAttribute("formbean") instanceof LocalGovtBodyForm) {

				List<GovernmentOrderTemplate> templateListNew = new ArrayList<GovernmentOrderTemplate>();
				LocalGovtBodyForm lbForm = (LocalGovtBodyForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;

				String operation = lbForm.getOperation();
				govtOrderConfig = lbForm.getGovtOrderConfig();
				int operationCode = lbForm.getOperationCode();

				if (govtOrderConfig.equals("govtOrderGenerate")) {
					if (operation == "PC") {
						templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
						mv.addObject("templateList", templateListNew);

					} else if (operation.equals("IPRI")) {
						templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
						mv.addObject("templateList", templateListNew);

					} else if (operation.equals("ITRI")) {
						templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
						mv.addObject("templateList", templateListNew);

					} else if (operation.equals("IURB")) {
						templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
						mv.addObject("templateList", templateListNew);
					} else if (operation.equals("DLBR")) {
						templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
						mv.addObject("templateList", templateListNew);
					}
				} 
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate

			} else if (httpSession.getAttribute("formbean") instanceof SubDistrictForm) {
				SubDistrictForm sdForm = null;
				sdForm = new SubDistrictForm();
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();
				sdForm = (SubDistrictForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;

				char operation = sdForm.getOperation();
				govtOrderConfig = sdForm.getGovtOrderConfig();

				if (govtOrderConfig.equals("govtOrderGenerate")) {
					if (operation == 'C') {

						templateList = govtOrderTemplateService.getTemplateListByOperationCode(16);// 10
																									// its
						// fixed
						// for an
						// operation
						// see
						// table
						// operations
						mv.addObject("templateList", templateList);
					} else if (operation == 'I') {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(12);// 13
																									// its
						// fixed
						// for an
						// operation
						// see
						// table
						// operations
						mv.addObject("templateList", templateList);
					} else if (operation == 'M') {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(7);// 7
																									// its
						// fixed
						// for an
						// operation
						// see
						// table
						// operations
						mv.addObject("templateList", templateList);
					}
				}
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate
			} else if (httpSession.getAttribute("formbean") instanceof StateForm) {
				StateForm stateForm = null;
				stateForm = new StateForm();
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();
				stateForm = (StateForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;

				char operation = stateForm.getOperation();
				govtOrderConfig = stateForm.getGovtOrderConfig();

				if (govtOrderConfig.equals("govtOrderGenerate")) {
					if (operation == 'C') {

						templateList = govtOrderTemplateService.getTemplateListByOperationCode(1);// 1
																									// its
																									// fixed
																									// for
																									// an
																									// operation
																									// see
																									// table
																									// operations

						mv.addObject("templateList", templateList);
					} /*
					 * else if (operation == 'I') { templateList =
					 * govtOrderTemplateService
					 * .getTemplateListByOperationCode(14);// 13 its fixed for
					 * an // operation see table // operations
					 * mv.addObject("templateList", templateList); }
					 */
					else if (operation == 'M') {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(43);// 1
																									// its
																									// fixed
																									// for
																									// an
																									// operation
																									// see
																									// table
																									// operations

						mv.addObject("templateList", templateList);
					}

				}
				
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate
			}

			else if (httpSession.getAttribute("formbean") instanceof DistrictForm) {
				DistrictForm distForm = null;
				distForm = new DistrictForm();
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();
				distForm = (DistrictForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;

				char operation = distForm.getOperation();
				govtOrderConfig = distForm.getGovtOrderConfig();

				if (govtOrderConfig.equals("govtOrderGenerate")) {
					if (operation == 'C') {

						templateList = govtOrderTemplateService.getTemplateListByOperationCode(3);// 10
						mv.addObject("templateList", templateList);
																									// its
						// fixed
						// for an
						// operation
						// see
						// table
						// operations
						mv.addObject("templateList", templateList);
					} else if (operation == 'I') {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(6);// 13
																									// its
																									// fixed
																									// for
						// operation see table // operations
						mv.addObject("templateList", templateList);
					}

					else if (operation == 'M') {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(4);// 4
																									// its
						// fixed
						// for an
						// operation
						// see
						// table
						// operations
						mv.addObject("templateList", templateList);
					}
				}
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate
			}

			else if (httpSession.getAttribute("formbean") instanceof ShiftDistrictForm) {
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();
				ShiftDistrictForm distForm = (ShiftDistrictForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;
				govtOrderConfig = distForm.getGovtOrderConfig();
				int operationCode = distForm.getOperationCode();

				if (govtOrderConfig.equals("govtOrderGenerate")) {

					templateList = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
					mv.addObject("templateList", templateList);

				}
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate
			} else if (httpSession.getAttribute("formbean") instanceof ShiftSubDistrictForm) {
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();
				ShiftSubDistrictForm distForm = (ShiftSubDistrictForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;
				govtOrderConfig = distForm.getGovtOrderConfig();
				int operationCode = distForm.getOperationCode();

				if (govtOrderConfig.equals("govtOrderGenerate")) {

					templateList = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
					mv.addObject("templateList", templateList);

				}
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate
			} else if (httpSession.getAttribute("formbean") instanceof ShiftVillageForm) {
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();
				ShiftVillageForm distForm = (ShiftVillageForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;
				govtOrderConfig = distForm.getGovtOrderConfig();
				int operationCode = distForm.getOperationCode();

				if (govtOrderConfig.equals("govtOrderGenerate")) {

					templateList = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
					mv.addObject("templateList", templateList);

				}
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate
			} else if (httpSession.getAttribute("formbean") instanceof ConvertRLBtoULBForm) {
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();
				ConvertRLBtoULBForm convertForm = (ConvertRLBtoULBForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;
				govtOrderConfig = convertForm.getGovtOrderConfig();
				int operationCode = convertForm.getOperationCode();

				if (govtOrderConfig.equals("govtOrderGenerate")) {

					templateList = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
					mv.addObject("templateList", templateList);

				}
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate
			} else if (httpSession.getAttribute("formbean") instanceof ConvertRLBtoTLBForm) {
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();
				ConvertRLBtoTLBForm convertForm = (ConvertRLBtoTLBForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;
				govtOrderConfig = convertForm.getGovtOrderConfig();
				int operationCode = convertForm.getOperationCode();

				if (govtOrderConfig.equals("govtOrderGenerate")) {

					templateList = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
					mv.addObject("templateList", templateList);

				}
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate
			} else if (httpSession.getAttribute("formbean") instanceof ConvertTLBtoRLBForm) {
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();
				ConvertTLBtoRLBForm convertForm = (ConvertTLBtoRLBForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;
				govtOrderConfig = convertForm.getGovtOrderConfig();
				int operationCode = convertForm.getOperationCode();

				if (govtOrderConfig.equals("govtOrderGenerate")) {
					templateList = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
					mv.addObject("templateList", templateList);
				}
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate
			}

			else if (httpSession.getAttribute("formbean") instanceof BlockForm) {
				BlockForm blockForm = null;
				blockForm = new BlockForm();
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();
				blockForm = (BlockForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;
				char operation = blockForm.getOperation();
				govtOrderConfig = blockForm.getGovtOrderConfig();

				if (govtOrderConfig.equals("govtOrderGenerate")) {
					if (operation == 'C') {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(41);
						mv.addObject("templateList", templateList);
						if (blockForm.getAttachFile1() != null) {
							List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapForBlock(request, blockForm, result, mv);
							httpSession.setAttribute("validFileMap", validFileMap);
						}
						/*
						 * templateList = govtOrderTemplateService
						 * .getTemplateListByOperationCode(41);
						 * mv.addObject("templateList", templateList);
						 */
					} else if (operation == 'I') {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(39);
						// mv.addObject("templateList", templateList);
					}
					else if (operation == 'M') {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(40);
						mv.addObject("templateList", templateList);
						}
				} else {
					if (blockForm.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapForBlock(request, blockForm, result, mv);
						httpSession.setAttribute("validFileMap", validFileMap);

					}
				}
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate
			} else if (httpSession.getAttribute("formbean") instanceof LocalGovtTypeForm) {
				LocalGovtTypeForm lbTypeForm = new LocalGovtTypeForm();

				lbTypeForm = (LocalGovtTypeForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;
				govtOrderConfig = lbTypeForm.getGovtOrderConfig();
				mv.addObject("govtOrderConfig", govtOrderConfig);

			} else if (httpSession.getAttribute("formbean") instanceof ShiftBlockForm) {

				List<GovernmentOrderTemplate> templateList = new ArrayList<GovernmentOrderTemplate>();
				ShiftBlockForm shiftForm = (ShiftBlockForm) httpSession.getAttribute("formbean");

				String govtOrderConfig = null;
				int operationCode = shiftForm.getOperationCode();
				govtOrderConfig = shiftForm.getGovtOrderConfig();

				if (govtOrderConfig.equals("govtOrderGenerate")) {

					templateList = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);
					mv.addObject("templateList", templateList);

				}
				mv.addObject("govtOrderConfig", govtOrderConfig);// values==govtOrderUpload,govtOrderGenerate
			}

			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	/* Modified by sushil on 19-12-2012 */
	/**
	 * Comment the code to create Village operation.As the code shifted to VillageController form on 19-08-2014.
	 */
	@RequestMapping(value = "/publishform", method = RequestMethod.POST)
	public ModelAndView publishForm(@ModelAttribute("governmentOrder") GovernmentOrderForm govtOrderForm, BindingResult result, SessionStatus status, HttpServletRequest request, HttpSession session, Model model) {
		ModelAndView mav = null;
		ModelAndView mv = null;
		//int Emailflag = 0;
		//int Transactionid = 0;
		try {
			setGlobalParams(session);
			AddAttachmentBean addAttachmentBean = null;
			//boolean success = false;
			int templCode = 0;
			String govtOrderConfig = govtOrderForm.getGovtOrderConfig();
			govtOrderValidator.validate(session, govtOrderForm, result);
			if (result.hasErrors()) {
				mv = new ModelAndView("govtOrderCommon");
				mv.addObject("govtOrderConfig", govtOrderConfig);
				mv.addObject("governmentOrder", govtOrderForm);
				return mv;
			}
			if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
				addAttachmentBean = setAttachmentBean(request, govtOrderForm);
				AddAttachmentHandler attachmentHandlerOne = new AddAttachmentHandler();
				String validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
				
				if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
					mv = new ModelAndView("govtOrderCommon");
					mv.addObject("govtOrderConfig", govtOrderConfig);
					mv.addObject("governmentOrder", govtOrderForm);
					request.setAttribute("validationErrorOne", validateAttachmentOne);
					return mv;
				} else {
					session.setAttribute("addAttachmentBean", addAttachmentBean);
				}

			}
			session.setAttribute("govtOrderForm", govtOrderForm);
			if (session.getAttribute("formbean") instanceof ConvertRLBtoULBForm) {

				ConvertRLBtoULBForm convertForm = (ConvertRLBtoULBForm) session.getAttribute("formbean");
				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());

					convertForm.setTemplateList(govtOrderForm.getTemplateList());

					convertForm.setOrderNo(govtOrderForm.getOrderNo());
					convertForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
					convertForm.setGazPubDate(govtOrderForm.getGazPubDate());
					convertForm.setOrderDate(govtOrderForm.getOrderDate());

					session.setAttribute("formbean", convertForm);
					String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
					govtOrderForm.setTemplateBodySrc(templateBodySrc);
					mav = new ModelAndView("previewGovtOrder");
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					mav = new ModelAndView("forward:publishConversionRLBtoULB.htm");
				}
			}
			session.setAttribute("govtOrderForm", govtOrderForm);
			if (session.getAttribute("formbean") instanceof ConvertRLBtoTLBForm) {
				ConvertRLBtoTLBForm convertForm = (ConvertRLBtoTLBForm) session.getAttribute("formbean");
				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());

					// convertForm.setTemplateList(govtOrderForm.getTemplateList());

					convertForm.setOrderNo(govtOrderForm.getOrderNo());
					convertForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
					// convertForm.setGazPubDate(govtOrderForm.getGazPubDate());
					convertForm.setOrderDate(govtOrderForm.getOrderDate());

					session.setAttribute("formbean", convertForm);
					String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
					govtOrderForm.setTemplateBodySrc(templateBodySrc);
					mav = new ModelAndView("previewGovtOrder");
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					convertForm.setOrderNo(govtOrderForm.getOrderNo());
					convertForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
					convertForm.setGazPubDate(govtOrderForm.getGazPubDate());
					convertForm.setOrderDate(govtOrderForm.getOrderDate());

					session.setAttribute("formbean", convertForm);
					if (govtOrderForm.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, result);
						session.setAttribute("validGovermentUpload", validFileGovtUpload);
					}
					mav = new ModelAndView("forward:publishConversionRLBtoTLB.htm");
				}
			}
			session.setAttribute("govtOrderForm", govtOrderForm);
			if (session.getAttribute("formbean") instanceof ConvertTLBtoRLBForm) {
				ConvertTLBtoRLBForm convertForm = (ConvertTLBtoRLBForm) session.getAttribute("formbean");
				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());

					// convertForm.setTemplateList(govtOrderForm.getTemplateList());

					convertForm.setOrderNo(govtOrderForm.getOrderNo());
					convertForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
					// convertForm.setGazPubDate(govtOrderForm.getGazPubDate());
					convertForm.setOrderDate(govtOrderForm.getOrderDate());

					session.setAttribute("formbean", convertForm);
					String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
					govtOrderForm.setTemplateBodySrc(templateBodySrc);
					mav = new ModelAndView("previewGovtOrder");
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					convertForm.setOrderNo(govtOrderForm.getOrderNo());
					convertForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
					convertForm.setGazPubDate(govtOrderForm.getGazPubDate());
					convertForm.setOrderDate(govtOrderForm.getOrderDate());

					session.setAttribute("formbean", convertForm);
					if (govtOrderForm.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, result);
						session.setAttribute("validGovermentUpload", validFileGovtUpload);
					}
					mav = new ModelAndView("forward:publishConversionTLBtoRLB.htm");
				}
			} else if (session.getAttribute("formbean") instanceof ShiftDistrictForm) {

				ShiftDistrictForm shiftForm = (ShiftDistrictForm) session.getAttribute("formbean");
				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());

					shiftForm.setTemplateList(govtOrderForm.getTemplateList());
					shiftForm.setOrderNo(govtOrderForm.getOrderNo());
					shiftForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
					shiftForm.setGazPubDate(govtOrderForm.getGazPubDate());
					shiftForm.setOrderDate(govtOrderForm.getOrderDate());

					session.setAttribute("formbean", shiftForm);
					String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
					govtOrderForm.setTemplateBodySrc(templateBodySrc);
					mav = new ModelAndView("previewGovtOrder");
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					if (govtOrderForm.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, result);
						session.setAttribute("validGovermentUpload", validFileGovtUpload);
					}
					mav = new ModelAndView("forward:saveShiftDistrict.htm");
				}

			} else if (session.getAttribute("formbean") instanceof ShiftSubDistrictForm) {
				ShiftSubDistrictForm shiftForm = (ShiftSubDistrictForm) session.getAttribute("formbean");
				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());

					shiftForm.setTemplateList(govtOrderForm.getTemplateList());

					shiftForm.setOrderNo(govtOrderForm.getOrderNo());
					shiftForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
					shiftForm.setGazPubDate(govtOrderForm.getGazPubDate());
					shiftForm.setOrderDate(govtOrderForm.getOrderDate());
					session.setAttribute("formbean", shiftForm);

					String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
					govtOrderForm.setTemplateBodySrc(templateBodySrc);
					mav = new ModelAndView("previewGovtOrder");
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					if (govtOrderForm.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, result);
						session.setAttribute("validGovermentUpload", validFileGovtUpload);
					}
					mav = new ModelAndView("forward:saveShiftSubDistrict.htm");
				}

			} else if (session.getAttribute("formbean") instanceof ShiftBlockForm) {
				ShiftBlockForm shiftForm = (ShiftBlockForm) session.getAttribute("formbean");
				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());

					shiftForm.setTemplateList(govtOrderForm.getTemplateList());

					shiftForm.setOrderNo(govtOrderForm.getOrderNo());
					shiftForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
					shiftForm.setGazPubDate(govtOrderForm.getGazPubDate());
					shiftForm.setOrderDate(govtOrderForm.getOrderDate());
					session.setAttribute("formbean", shiftForm);

					String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
					govtOrderForm.setTemplateBodySrc(templateBodySrc);
					mav = new ModelAndView("previewGovtOrder");
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					if (govtOrderForm.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, result);
						session.setAttribute("validGovermentUpload", validFileGovtUpload);
					}
					mav = new ModelAndView("forward:saveShiftBlock.htm");
				}

			} else if (session.getAttribute("formbean") instanceof ShiftVillageForm) {
				ShiftVillageForm shiftForm = (ShiftVillageForm) session.getAttribute("formbean");
				//char operation = shiftForm.getOperation();
				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());

					shiftForm.setTemplateList(govtOrderForm.getTemplateList());
					shiftForm.setOrderNo(govtOrderForm.getOrderNo());
					shiftForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
					shiftForm.setGazPubDate(govtOrderForm.getGazPubDate());
					shiftForm.setOrderDate(govtOrderForm.getOrderDate());
					session.setAttribute("formbean", shiftForm);
					String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
					govtOrderForm.setTemplateBodySrc(templateBodySrc);
					mav = new ModelAndView("previewGovtOrder");
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					if (govtOrderForm.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, result);
						session.setAttribute("validGovermentUpload", validFileGovtUpload);
					}
					mav = new ModelAndView("forward:saveShiftVillage.htm");
				}

			} else if (session.getAttribute("formbean") instanceof LocalGovtTypeForm) {
				LocalGovtTypeForm localBform = (LocalGovtTypeForm) session.getAttribute("formbean");
				if (localBform.getOperation() == 'C'){
					mav = new ModelAndView("forward:savelocalgovtType.htm");
				}	
				else if (localBform.getOperation() == 'M'){
					mav = new ModelAndView("forward:modifyLogalGovtTypeAction.htm");
				}	
			} else if (session.getAttribute("formbean") instanceof LocalGovtBodyForm) {

				LocalGovtBodyForm localBform = (LocalGovtBodyForm) session.getAttribute("formbean");
				String operationLb = localBform.getOperation();
				if (govtOrderForm.getGovtOrderConfig().equalsIgnoreCase("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());
					if (operationLb == "PC") {
						localBform.setTemplateList(govtOrderForm.getTemplateList());

						localBform.setLgd_LBorderNo(govtOrderForm.getOrderNo());
						localBform.setLgd_LBeffectiveDate(govtOrderForm.getEffectiveDate());
						localBform.setLgd_LBgazPubDate(govtOrderForm.getGazPubDate());
						localBform.setLgd_LBorderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", localBform);
						String templateBodylbSrc = govtOrderService.previewTemplate(templCode, session);
						govtOrderForm.setTemplateBodySrc(templateBodylbSrc);
						mav = new ModelAndView("previewGovtOrder");
					}
					if (operationLb.equals("IPRI") || operationLb.equals("ITRI")) {
						localBform.setTemplateList(govtOrderForm.getTemplateList());

						localBform.setLgd_LBorderNo(govtOrderForm.getOrderNo());
						localBform.setLgd_LBeffectiveDate(govtOrderForm.getEffectiveDate());
						localBform.setLgd_LBgazPubDate(govtOrderForm.getGazPubDate());
						localBform.setLgd_LBorderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", localBform);
						String templateBodylbSrc = govtOrderService.previewTemplate(templCode, session);
						govtOrderForm.setTemplateBodySrc(templateBodylbSrc);
						mav = new ModelAndView("previewGovtOrder");
					}

					else if (operationLb.equals("IURB")) {
						localBform.setTemplateList(govtOrderForm.getTemplateList());

						localBform.setLgd_LBorderNo(govtOrderForm.getOrderNo());
						localBform.setLgd_LBeffectiveDate(govtOrderForm.getEffectiveDate());
						localBform.setLgd_LBgazPubDate(govtOrderForm.getGazPubDate());
						localBform.setLgd_LBorderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", localBform);
						String templateBodylbSrc = govtOrderService.previewTemplate(templCode, session);
						govtOrderForm.setTemplateBodySrc(templateBodylbSrc);
						mav = new ModelAndView("previewGovtOrder");
					}
					
					else if (operationLb.equals("DLBR")) {
						localBform.setTemplateList(govtOrderForm.getTemplateList());

						localBform.setLgd_LBorderNo(govtOrderForm.getOrderNo());
						localBform.setLgd_LBeffectiveDate(govtOrderForm.getEffectiveDate());
						localBform.setLgd_LBgazPubDate(govtOrderForm.getGazPubDate());
						localBform.setLgd_LBorderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", localBform);
						String templateBodylbSrc = govtOrderService.previewTemplate(templCode, session);
						govtOrderForm.setTemplateBodySrc(templateBodylbSrc);
						mav = new ModelAndView("previewGovtOrder");
					}

				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					localBform.setTemplateList(govtOrderForm.getTemplateList());

					localBform.setLgd_LBorderNo(govtOrderForm.getOrderNo());
					localBform.setLgd_LBeffectiveDate(govtOrderForm.getEffectiveDate());
					localBform.setLgd_LBgazPubDate(govtOrderForm.getGazPubDate());
					localBform.setLgd_LBorderDate(govtOrderForm.getOrderDate());

					// Data and upload file

					/*
					 * if(session.getAttribute("validFileMap")!=null) {
					 * List<AttachedFilesForm> validFileMap =
					 * (List<AttachedFilesForm
					 * >)session.getAttribute("validFileMap"); boolean
					 * insertTableFlag =
					 * localGovtBodyService.saveDataInMapLocalBody(localBform,
					 * validFileMap, session); }
					 */

					// Data and upload file

					// File upload govt order

					if (govtOrderForm.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, result);
						session.setAttribute("validGovermentUpload", validFileGovtUpload);
					}

					// file upload govt order

					session.setAttribute("formbean", localBform);
					if (operationLb.equals("IPRI")) {
						// odel.addAttribute("targetlb", arg1)
						model.addAttribute("localGovtBodyForm", localBform);
						mav = new ModelAndView("forward:invalidatePRILBFinal.htm");

					} else if (operationLb.equals("IURB")) {
						// odel.addAttribute("targetlb", arg1)
						model.addAttribute("localGovtBodyForm", localBform);
						mav = new ModelAndView("forward:invalidateURBFinal.htm");

					} else if (operationLb.equals("ITRI")) {
						// odel.addAttribute("targetlb", arg1)
						model.addAttribute("localGovtBodyForm", localBform);
						mav = new ModelAndView("forward:invalidateTRILBFinal.htm");

					} else if (operationLb.equals("DLBR")) {
						model.addAttribute("localGovtBodyForm", localBform);
						mav = new ModelAndView("forward:saveDisturbedLocalGovtBody.htm");
					} 
					else if (operationLb.equals("MULB")) {
						model.addAttribute("localGovtBodyForm", localBform);
						mav = new ModelAndView("forward:mergeUrbanLbFinal.htm");

					}
					else
						mav = new ModelAndView("forward:saveLocalGovtBody.htm");
				}
			} else if (session.getAttribute("formbean") instanceof SubDistrictForm) {
				SubDistrictForm subDistrictForm = null;
				subDistrictForm = new SubDistrictForm();
				subDistrictForm = (SubDistrictForm) session.getAttribute("formbean");
				char operation = subDistrictForm.getOperation();
                String districtNameEng=districtService.getDistrictNameEnglishbyDistrictCode(subDistrictForm.getDistrictCode()!=null?Integer.parseInt(subDistrictForm.getDistrictCode()):null);
                session.setAttribute("districtNameEng", districtNameEng);
                session.setAttribute("stateNameEng", stateNameEng);
				// values==govtOrderUpload,govtOrderGenerate
				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());
					if (operation == 'C' || operation == 'I' || operation == 'M') {
						
						subDistrictForm.setTemplateList(govtOrderForm.getTemplateList());

						subDistrictForm.setOrderNo(govtOrderForm.getOrderNo());
						subDistrictForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						subDistrictForm.setGazPubDate(govtOrderForm.getGazPubDate());
						subDistrictForm.setOrderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", subDistrictForm);
						String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
						govtOrderForm.setTemplateBodySrc(templateBodySrc);
						mav = new ModelAndView("previewGovtOrder");
					}
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					if (operation == 'C') {

						subDistrictForm.setOrderNo(govtOrderForm.getOrderNo());
						subDistrictForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						subDistrictForm.setGazPubDate(govtOrderForm.getGazPubDate());
						subDistrictForm.setOrderDate(govtOrderForm.getOrderDate());
						subDistrictForm.setAttachFile1(govtOrderForm.getAttachFile1());
						mav = new ModelAndView("forward:saveSubdistrict.htm");
					} else if (operation == 'I') {
						subDistrictForm.setOrderNo(govtOrderForm.getOrderNo());
						subDistrictForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						subDistrictForm.setGazPubDate(govtOrderForm.getGazPubDate());
						subDistrictForm.setOrderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", subDistrictForm);
						mav = new ModelAndView("forward:invalidateSubDFinal.htm");
					} else if (operation == 'M') {
						// ////commented
						subDistrictForm.setOrderNo(govtOrderForm.getOrderNo());
						subDistrictForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						subDistrictForm.setGazPubDate(govtOrderForm.getGazPubDate());
						subDistrictForm.setOrderDate(govtOrderForm.getOrderDate());
						subDistrictForm.setGovtOrderConfig(govtOrderForm.getGovtOrderConfig());
						session.setAttribute("formbean", subDistrictForm);
						mav = new ModelAndView("forward:modifySubdistrictChangeAction.htm");

					}

				}
			}

			else if (session.getAttribute("formbean") instanceof DistrictForm) {
				DistrictForm districtForm = null;
				districtForm = new DistrictForm();
				districtForm = (DistrictForm) session.getAttribute("formbean");
				char operation = districtForm.getOperation();

				// values==govtOrderUpload,govtOrderGenerate
				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());
					if (operation == 'C' || operation == 'I' || operation == 'M') {
						districtForm.setTemplateList(govtOrderForm.getTemplateList());

						districtForm.setOrderNo(govtOrderForm.getOrderNo());
						districtForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						districtForm.setOrderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", districtForm);
						session.setAttribute("stateNameEng", stateNameEng);
						String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
						govtOrderForm.setTemplateBodySrc(templateBodySrc);
						mav = new ModelAndView("previewGovtOrder");
					}
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					if (operation == 'C') {

						districtForm.setOrderNo(govtOrderForm.getOrderNo());
						districtForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						districtForm.setGazPubDate(govtOrderForm.getGazPubDate());
						districtForm.setOrderDate(govtOrderForm.getOrderDate());
						districtForm.setAttachFile1(govtOrderForm.getAttachFile1());
						mav = new ModelAndView("forward:savedistrict.htm");
					} else if (operation == 'I') {
						districtForm.setOrderNo(govtOrderForm.getOrderNo());
						districtForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						districtForm.setGazPubDate(govtOrderForm.getGazPubDate());

						districtForm.setOrderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", districtForm);
						mav = new ModelAndView("forward:invalidateDistrictFinal.htm");
					} else if (operation == 'M') {
						// ////commented
						mav = new ModelAndView("forward:modifyDistrictChangeAction.htm");

					}

				}

			}

			else if (session.getAttribute("formbean") instanceof StateForm) {

				StateForm stateForm = null;
				stateForm = new StateForm();
				stateForm = (StateForm) session.getAttribute("formbean");
				// session.removeAttribute("formbean");
				char operation = stateForm.getOperation();

				// values==govtOrderUpload,govtOrderGenerate
				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());
					if (operation == 'C' || operation == 'M') {
						stateForm.setTemplateList(govtOrderForm.getTemplateList());

						stateForm.setOrderNo(govtOrderForm.getOrderNo());
						stateForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						stateForm.setGazPubDate(govtOrderForm.getGazPubDate());
						stateForm.setOrderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", stateForm);
						String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
						govtOrderForm.setTemplateBodySrc(templateBodySrc);
						mav = new ModelAndView("previewGovtOrder");
					}
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					if (operation == 'C') {

						stateForm.setOrderNo(govtOrderForm.getOrderNo());
						stateForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						stateForm.setGazPubDate(govtOrderForm.getGazPubDate());
						stateForm.setOrderDate(govtOrderForm.getOrderDate());
						session.setAttribute("formbean", stateForm);
						// success = villageService.addVillage(villageForm,
						// request, session);
						mav = new ModelAndView("forward:savestate.htm");

					} else if (operation == 'M') {
						// ////commented
						stateForm.setOrderNo(govtOrderForm.getOrderNo());
						stateForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						stateForm.setGazPubDate(govtOrderForm.getGazPubDate());
						stateForm.setOrderDate(govtOrderForm.getOrderDate());
						session.setAttribute("formbean", stateForm);
						mav = new ModelAndView("forward:modifyStateChangeAction.htm");

					}

				}

			} else if (session.getAttribute("formbean") instanceof BlockForm) {

				BlockForm blockForm = null;
				blockForm = new BlockForm();
				blockForm = (BlockForm) session.getAttribute("formbean");
				char operation = blockForm.getOperation();

				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());
					if (operation == 'C' || operation == 'I' || operation == 'M') {
						blockForm.setTemplateList(govtOrderForm.getTemplateList());

						blockForm.setOrderNo(govtOrderForm.getOrderNo());
						blockForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						blockForm.setGazPubDate(govtOrderForm.getGazPubDate());
						blockForm.setOrderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", blockForm);
						String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
						govtOrderForm.setTemplateBodySrc(templateBodySrc);
						mav = new ModelAndView("previewGovtOrder");
					}
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					if (operation == 'C') {

						blockForm.setOrderNo(govtOrderForm.getOrderNo());
						blockForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						blockForm.setGazPubDate(govtOrderForm.getGazPubDate());
						blockForm.setOrderDate(govtOrderForm.getOrderDate());
						model.addAttribute("newblockform", blockForm);
						mav = new ModelAndView("forward:saveBlock.htm");
					} else if (operation == 'I') {
						blockForm.setOrderNo(govtOrderForm.getOrderNo());
						blockForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						blockForm.setGazPubDate(govtOrderForm.getGazPubDate());
						blockForm.setOrderDate(govtOrderForm.getOrderDate());
						session.setAttribute("formbean", blockForm);
						mav = new ModelAndView("forward:invalidateblockFinal.htm");

					} else if (operation == 'M') {
						blockForm.setTemplateList(govtOrderForm.getTemplateList());

						blockForm.setOrderNo(govtOrderForm.getOrderNo());
						blockForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						blockForm.setGazPubDate(govtOrderForm.getGazPubDate());
						blockForm.setOrderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", blockForm);
						// ////commented
					
						mav = new ModelAndView("forward:modifyBlockChangeAction.htm");

					}

				}

			} else if (session.getAttribute("formbean") instanceof VillageForm) {

				VillageForm villageForm = null;
				villageForm = new VillageForm();
				villageForm = (VillageForm) session.getAttribute("formbean");
				char operation = villageForm.getOperation();

				// values==govtOrderUpload,govtOrderGenerate
				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(govtOrderForm.getTemplateList());
					if (operation == 'C' || operation == 'I' || operation == 'M') {
						villageForm.setTemplateList(govtOrderForm.getTemplateList());

						villageForm.setOrderNo(govtOrderForm.getOrderNo());
						villageForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						villageForm.setGazPubDate(govtOrderForm.getGazPubDate());
						villageForm.setOrderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", villageForm);
						String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
						govtOrderForm.setTemplateBodySrc(templateBodySrc);
						mav = new ModelAndView("previewGovtOrder");
					}
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					if (operation == 'C') {
						// Comment the code to create Village operation.As the code shifted to VillageController form on 19-08-2014 
						/*
						Integer vcode = null;
						String tid = null;
						String vc = null;
						villageForm.setOrderNo(govtOrderForm.getOrderNo());
						villageForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						villageForm.setGazPubDate(govtOrderForm.getGazPubDate());
						villageForm.setOrderDate(govtOrderForm.getOrderDate());
						String villagedata = villageService.insertVillage(villageForm, request, session);
						if (villagedata != null) {
							String[] lbp = villagedata.split(",");
							vc = lbp[0];
							tid = lbp[1];
							vcode = Integer.parseInt(vc);
						}
						if (vcode != null) {
							// Data and upload file
							if (session.getAttribute("validFileMap") != null) {
								List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) session.getAttribute("validFileMap");
								villageForm.setVillageCode(vcode);
								boolean insertTableFlag = villageService.saveDataInMap(villageForm, validFileMap, session);
							}

							// file upload govt order
							List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, result);
							boolean insertGovtTableFlag = villageService.saveDataInAttach(govtOrderForm, validFileGovtUpload, vcode, request.getSession());
							// file upload govt order
							Emailflag = 1;
							if (tid != null) {
								Transactionid = Integer.parseInt(tid);
								Emailflag = 1;
							}
							mav = new ModelAndView("success");
							mav.addObject("msgid", "New Village Created Successfully");

							if (Emailflag == 1) {
								int t_code = Transactionid;
								char t_type = 'V';
								EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
								est.start();

							}

						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", "Village Creation failure please check logs");
						}
					*/} else if (operation == 'I') {
						villageForm.setOrderNo(govtOrderForm.getOrderNo());
						villageForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						villageForm.setGazPubDate(govtOrderForm.getGazPubDate());
						villageForm.setOrderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", villageForm);
						mav = new ModelAndView("forward:invalidatevill.htm");
					} else if (operation == 'M') {
						villageForm.setTemplateList(govtOrderForm.getTemplateList());

						villageForm.setOrderNo(govtOrderForm.getOrderNo());
						villageForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
						villageForm.setGazPubDate(govtOrderForm.getGazPubDate());
						villageForm.setOrderDate(govtOrderForm.getOrderDate());

						session.setAttribute("formbean", villageForm);
						// ////commented
						mav = new ModelAndView("forward:modifyVillageChangeAction.htm");

					}

				}
			}
		} catch (Exception e) {
			System.out.println("Exception GovtOrderController.publishForm()");
			e.printStackTrace();
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;

		}

		return mav;

	}

	@RequestMapping(value = "/createpdf", method = RequestMethod.POST)
	public ModelAndView createpdf(@ModelAttribute("governmentOrder") GovernmentOrderForm govtform, BindingResult result, SessionStatus status, Model model,
			HttpServletRequest request, HttpSession httpSession) throws DocumentException, IOException, ServletRequestBindingException {
		String htmlSource = null;

		boolean success = false;
		
		GenerateDetails generatedetails = new GenerateDetails();
		String fileName = null;
		htmlSource = govtform.getTemplateBodySrc();
		String filepath = null;
		Session session=null;
		//Generate PDF Document Process
		try {
			generatedetails = docconverter.ganaratePDFDocument(generatedetails, request, htmlSource);
			httpSession.setAttribute("validGovermentGenerateUpload", generatedetails);
			filepath = generatedetails.getFileLocation();
			fileName = generatedetails.getFileName();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e1);
		}

		
		ModelAndView mav = null;

		try {
			if (httpSession.getAttribute("formbean") instanceof VillageForm) {
				boolean flag = false;
				VillageForm villageFormBean = null;
				villageFormBean = new VillageForm();
				villageFormBean = (VillageForm) httpSession.getAttribute("formbean");
				villageFormBean.setOrderPath(filepath);
				char operation = villageFormBean.getOperation();
				if (operation == 'C') {

					flag = villageService.addvillagegenerate(villageFormBean, request, httpSession, govtform);
					if (flag == true) {
						mav = new ModelAndView("success");
						mav.addObject("msgid", "New Village Created Successfully");
					}

					else {
						mav = new ModelAndView("success");
						mav.addObject("msgid", "Village Creation failure please check logs");

					}
					/*
					 * success = villageService.addVillage(villageFormBean,
					 * request, httpSession);
					 */
				} else if (operation == 'I') {
					//success = villageService.invalidateVillage(villageFormBean, httpSession);
					mav = new ModelAndView("forward:invalidatevill.htm");
					
				} else if (operation == 'M') {
					mav = new ModelAndView("forward:modifyVillageChangeAction.htm");
					/*
					 * success = villageService.changeVillageforTemplate(
					 * villageFormBean, govtform, request);
					 */
					/*
					 * VillageForm villageform = (VillageForm) httpSession
					 * .getAttribute("formbean");
					 * villageform.setOrderPath(filepath); mav = new
					 * ModelAndView("forward:modifyVillageAction.htm");
					 */

				}
			}

			else if (httpSession.getAttribute("formbean") instanceof LocalGovtBodyForm) {
				String lboperation = null;
				LocalGovtBodyForm lbform = (LocalGovtBodyForm) httpSession.getAttribute("formbean");
				lbform.setOrderPath(filepath);
				lboperation = lbform.getOperation();
				if (lboperation.equals("IPRI") || lboperation.equals("ITRI") || lboperation.equals("IURB"))
				{	
					mav = new ModelAndView("forward:invalidatedLocalGovtBody.htm");
				}	
				else if(lboperation.equals("PC"))
				{	
					mav = new ModelAndView("forward:saveLocalGovtBody.htm");
				}
				else if(lboperation.equals("LBTC"))
				{
					mav = new ModelAndView("forward:saveUrbanLocalGovtBodyChangeType.htm");
				}
				else if(lboperation.equals("LBCCH"))
				{
					mav = new ModelAndView("forward:saveLocalBodyCoveredArea.htm");
				}
				else if(lboperation.equals("LBCCNM"))
				{
					mav = new ModelAndView("forward:saveLocalBodyChangeName.htm");
				}
				else if(lboperation.equals("LBGOCHN"))
				{
					mav = new ModelAndView("forward:saveLocalBodyCorrection.htm");
				}
				else if(lboperation.equals("LBHRC"))
				{
					mav = new ModelAndView("forward:saveParentHeirarchyChange.htm");
				}
				else if (lboperation.equals("DLBR")) {
					mav = new ModelAndView("forward:saveDisturbedLocalGovtBody.htm");
				} 
				else
				{
					mav = new ModelAndView("forward:modifyLocalBodyTypeForUrban.htm");
				}

			} else if (httpSession.getAttribute("formbean") instanceof StateForm) {

				StateForm stateFormBean = null;
				stateFormBean = new StateForm();
				stateFormBean = (StateForm) httpSession.getAttribute("formbean");
				char operation = stateFormBean.getOperation();
				if (operation == 'C') {

					mav = new ModelAndView("forward:savestate.htm?fileName=" + fileName);

				} else if (operation == 'I') {
					Log.info("Nothing to do.");

				} else if (operation == 'M') {
					success = stateService.changeStateforTemplate(stateFormBean, govtform, request, httpSession);
					Integer stateCode = null;
					if (stateFormBean.getListStateDetails().size() > 0) {
						stateCode = stateFormBean.getListStateDetails().get(0).getStateCode();

						if (success) {
							mav = new ModelAndView("redirect:viewStateDetailforModify.htm?id=" + stateCode + "&type=S");
							return mav;
						}
					}
				}
			}

			else if (httpSession.getAttribute("formbean") instanceof DistrictForm) {

				DistrictForm districtbean = null;
				districtbean = new DistrictForm();
				districtbean = (DistrictForm) httpSession.getAttribute("formbean");
				String orderCode  = null;
				String tid = null;
				String successResult = null;
				// districtbean.setOrderPath(filepath);
				char operation = districtbean.getOperation();
				if (operation == 'C') {
					mav = new ModelAndView("forward:savedistrict.htm?fileName=" + fileName);
				} else if (operation == 'I') {
					List<District> dlist=new ArrayList<District>();
					dlist=districtService.getDistrictListByDistCode(Integer.parseInt(districtbean.getDistrictNameEnglish()));
					
				
					List<DistrictForm> listdetail=new ArrayList<DistrictForm>();
					listdetail=districtService.getMergeDistrictWithSubDistrictWithSubdistrict(districtbean.getListformat());
					successResult=districtService.saveInvalidateDistrict(districtbean,httpSession);
					if(successResult==null)
					{
						mav = new ModelAndView("error");
						mav.addObject("message", "invalidate information is not save successfully");
						return mav;
					}
					else
					{
						String[] ldata = successResult.split(",");
						orderCode = ldata[0];
						tid = ldata[1];
						}
					int Transactionid = Integer.parseInt(tid);
					int govtOrderCode = Integer.parseInt(orderCode);
					
					session = sessionFactory.openSession();
					Transaction tx = session.beginTransaction();
					try {
						
						
						govtOrderService
								.saveDataInAttachmentWithOrderCodeInGenrate(
										govtOrderCode, generatedetails, session);

						tx.commit();
						success = true;
						session.close();

						EmailSmsThread est = new EmailSmsThread(Transactionid,
								'D', emailService);
						est.start();
					} catch (Exception e) {
						tx.rollback();
						session.close();
						success=false;
					}
					
					mav = new ModelAndView("invalidateDistSuccess");
					mav.addObject("districtBean", dlist);
					mav.addObject("districtBeanMerge", listdetail);
					return mav;
					// success =
					// SubDistrictService.invalidateVillage(sdForm,httpSession);

				} else if (operation == 'M') {
					success = districtService.changeDistrictforTemplate(districtbean, govtform, request, httpSession);
					Integer districtCode = null;
					if (districtbean.getListDistrictDetails().size() > 0) {
						districtCode = districtbean.getListDistrictDetails().get(0).getDistrictCode();
					}
					if (districtCode != null) {
						if (success) {
							mav = new ModelAndView("redirect:viewDistrictDetail.htm?id=" + districtCode + "&type=D");
							return mav;
						}
					}

				}
			}

			else if (httpSession.getAttribute("formbean") instanceof SubDistrictForm) {
				SubDistrictForm sdForm = new SubDistrictForm();
				sdForm = (SubDistrictForm) httpSession.getAttribute("formbean");
				char operation = sdForm.getOperation();
				if (operation == 'C') {
					mav = new ModelAndView("forward:saveSubdistrict.htm?fileName=" + fileName);

				} else if (operation == 'I') {
					mav = new ModelAndView("forward:invalidateSubDFinal.htm");

				} else if (operation == 'M') {
					/*
					 * mav = new
					 * ModelAndView("forward:modifySubdistrictChangeAction.htm"
					 * );
					 */
					success = subdistrictService.changeSubDistrictforTemplate(sdForm, govtform, request, httpSession);
					Integer subDistrictCode = sdForm.getListSubdistrictDetails().get(0).getSubdistrictCode();
					if (subDistrictCode != null) {
						if (success) {
							mav = new ModelAndView("redirect:viewSubDistrictDetailformodify.htm?id=" + subDistrictCode + "&type=T");
							return mav;
						}
					}

				}
			}

			else if (httpSession.getAttribute("formbean") instanceof ShiftDistrictForm) {

				ShiftDistrictForm shiftform = (ShiftDistrictForm) httpSession.getAttribute("formbean");
				shiftform.setOrderPath(filepath);
				mav = new ModelAndView("forward:saveShiftDistrict.htm");
			}

			else if (httpSession.getAttribute("formbean") instanceof ShiftSubDistrictForm) {

				ShiftSubDistrictForm shiftform = (ShiftSubDistrictForm) httpSession.getAttribute("formbean");
				shiftform.setOrderPath(filepath);
				mav = new ModelAndView("forward:saveShiftSubDistrict.htm");
			} else if (httpSession.getAttribute("formbean") instanceof ShiftBlockForm) {

				ShiftBlockForm shiftform = (ShiftBlockForm) httpSession.getAttribute("formbean");
				shiftform.setOrderPath(filepath);
				mav = new ModelAndView("forward:saveShiftBlock.htm");
			} else if (httpSession.getAttribute("formbean") instanceof ShiftVillageForm) {

				ShiftVillageForm shiftform = (ShiftVillageForm) httpSession.getAttribute("formbean");
				shiftform.setOrderPath(filepath);
				mav = new ModelAndView("forward:saveShiftVillage.htm");
			} else if (httpSession.getAttribute("formbean") instanceof ConvertRLBtoULBForm) {

				ConvertRLBtoULBForm convertform = (ConvertRLBtoULBForm) httpSession.getAttribute("formbean");
				convertform.setOrderPath(filepath);
				mav = new ModelAndView("forward:publishConversionRLBtoULB.htm");
			} else if (httpSession.getAttribute("formbean") instanceof ConvertRLBtoTLBForm) {
				ConvertRLBtoTLBForm convertform = (ConvertRLBtoTLBForm) httpSession.getAttribute("formbean");
				convertform.setOrderPath(filepath);
				mav = new ModelAndView("forward:publishConversionRLBtoTLB.htm");
			} else if (httpSession.getAttribute("formbean") instanceof ConvertTLBtoRLBForm) {
				ConvertTLBtoRLBForm convertform = (ConvertTLBtoRLBForm) httpSession.getAttribute("formbean");
				convertform.setOrderPath(filepath);
				mav = new ModelAndView("forward:publishConversionTLBtoRLB.htm");
			} else if (httpSession.getAttribute("formbean") instanceof BlockForm) {
				BlockForm blockForm = new BlockForm();
				blockForm = (BlockForm) httpSession.getAttribute("formbean");
				List<BlockDataForm> blockDataForm=blockForm.getListBlockDetails();
				Integer blockCode=0;
				if(!blockDataForm.isEmpty())
				{
					BlockDataForm element=blockDataForm.get(0);
					blockCode=element.getBlockCode();
				}
				char operation = blockForm.getOperation();
				if (operation == 'C') {
					mav = new ModelAndView("forward:saveBlock.htm?fileName=" + fileName);

				} else if (operation == 'I') {
					mav = new ModelAndView("forward:invalidateblockPost.htm?fileName=" + fileName);

				}
				else if (operation == 'M')
				{
					
					boolean flag=blockService.changeBlockforTemplate(blockForm,govtform,request,httpSession);
					if(flag)
					{
						mav = new ModelAndView("forward:viewBlockDetailforChange.htm?id="
								+ blockCode + "&type=S");
						
					}
					else
					{
						mav = new ModelAndView("success");
						mav.addObject("msgid", "modifcation not done");
					}
					mav = new ModelAndView("forward:modifyBlockChangeAction.htm");
				}
			}

			if (success) {
				mav = new ModelAndView("viewGovtOrder");
				mav.addObject("filepath", "govtorder/" + fileName);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mav;
	}

	public AddAttachmentBean setAttachmentBean(HttpServletRequest request, GovernmentOrderForm govtOrderForm) {

		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */

			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile1());// 1.List
																							// Of
																							// File
																							// To
																							// Be
																							// Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location
			// For
			// File
			// Upload
			// In
			// File
			// System.
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());// 3.File
			// Title
			// Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed
			// Total
			// Number
			// Of
			// Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed
			// Total
			// File
			// Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed
			// Individual
			// File
			// Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number
			// Of
			// Already
			// Attached
			// File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already
			// attached
			// file
			// total
			// size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File
			// Mime
			// Type
			// List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File
																								// Id
																								// array
			// to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File
																									// Name
			// Array To Be
			// Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted
			// File
			// Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
			// of
			// Mandatory
			// file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}

		return addAttachmentBeanTwo;
	}

	public AddAttachmentBean setAttachmentBeanMap(HttpServletRequest request, VillageForm villageForm) {

		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */

			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(villageForm.getAttachFile1());// 1.List
																							// Of
																							// File
																							// To
																							// Be
																							// Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location
			// For
			// File
			// Upload
			// In
			// File
			// System.
			addAttachmentBeanTwo.setFileTitle(villageForm.getFileTitle1());// 3.File
			// Title
			// Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed
			// Total
			// Number
			// Of
			// Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed
			// Total
			// File
			// Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed
			// Individual
			// File
			// Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number
			// Of
			// Already
			// Attached
			// File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already
			// attached
			// file
			// total
			// size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File
			// Mime
			// Type
			// List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File
																								// Id
																								// array
			// to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File
																									// Name
			// Array To Be
			// Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted
			// File
			// Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
			// of
			// Mandatory
			// file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}

		return addAttachmentBeanTwo;
	}

	/*private AddAttachmentBean getAttachmentBeanMapLocalbody(LocalGovtBodyForm lbForm, HttpServletRequest request) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 

			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(lbForm.getAttachFile2());// 1.List
																						// Of
																						// File
																						// To
																						// Be
																						// Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location
			// For
			// File
			// Upload
			// In
			// File
			// System.
			addAttachmentBeanTwo.setFileTitle(lbForm.getFileTitle1());// 3.File
			// Title
			// Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed
			// Total
			// Number
			// Of
			// Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed
			// Total
			// File
			// Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed
			// Individual
			// File
			// Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number
			// Of
			// Already
			// Attached
			// File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already
			// attached
			// file
			// total
			// size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File
			// Mime
			// Type
			// List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File
																								// Id
																								// array
			// to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File
																									// Name
			// Array To Be
			// Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted
			// File
			// Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
			// of
			// Mandatory
			// file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}

		return addAttachmentBeanTwo;
	}*/
	
	public AddAttachmentBean getAttachmentBeanUploadLocalbodyView(LocalGovtBodyForm lbForm, HttpServletRequest request) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */

			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(lbForm.getAttachFile1());// 1.List
																						// Of
																						// File
																						// To
																						// Be
																						// Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location
			// For
			// File
			// Upload
			// In
			// File
			// System.
			addAttachmentBeanTwo.setFileTitle(lbForm.getFileTitle1());// 3.File
			// Title
			// Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed
			// Total
			// Number
			// Of
			// Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed
			// Total
			// File
			// Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed
			// Individual
			// File
			// Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number
			// Of
			// Already
			// Attached
			// File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already
			// attached
			// file
			// total
			// size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File
			// Mime
			// Type
			// List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File
																								// Id
																								// array
			// to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File
																									// Name
			// Array To Be
			// Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted
			// File
			// Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
			// of
			// Mandatory
			// file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}

		return addAttachmentBeanTwo;
	}
	
	
}
