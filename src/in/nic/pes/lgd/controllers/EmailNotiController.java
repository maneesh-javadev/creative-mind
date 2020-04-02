package in.nic.pes.lgd.controllers;

import in.nic.pes.common.beans.SubscribeApplication;
import in.nic.pes.common.menu.pojo.UserSelection;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.forms.EmailNotiForm;
import in.nic.pes.lgd.forms.SubscribeApplicationForm;
import in.nic.pes.lgd.interceptor.LanguageListener;
import in.nic.pes.lgd.loggers.LGDLogger;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.validator.SubscribeValidator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailNotiController { // NO_UCD (unused code)

	private static final String USERS_SELECTION = "USERS_SELECTION";

	@Autowired
	EmailService emailService;

	@RequestMapping(value = "/subscribeemail", method = RequestMethod.GET)
	public ModelAndView showEmailNoti(HttpSession session, @ModelAttribute("email") EmailNotiForm email, Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		Integer userId = null;
		Long userid = null;
		UserSelection selection = (UserSelection) session.getAttribute(USERS_SELECTION);
		if (selection != null) {
			userid = selection.getUserId();
		}
		if (userid != null) {
			userId = Integer.valueOf(userid.intValue());
		}
		try {
			if (userId != null) {
				mav = new ModelAndView("email");
				EmailNotification en = emailService.getEmailNotififcation(userId);
				if (en != null) {
					if (en.getDistrict() == true) {
						mav.addObject("flag1", 1);
					}
					if (en.getSubdistrict() == true) {
						mav.addObject("flag2", 1);
					}
					if (en.getVillage() == true) {
						mav.addObject("flag3", 1);
					}
					if (en.getUrbanbody() == true) {
						mav.addObject("flag4", 1);
					}
					if (en.getRuralbody() == true) {
						mav.addObject("flag5", 1);
					}
					if (en.getTradionbody() == true) {
						mav.addObject("flag6", 1);
					}
					if (en.getBlock() == true) {
						mav.addObject("flag7", 1);
					}
				}
				return mav;

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "User is Not Defiend");

			}
		} catch (NullPointerException e) {
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		}

		return mav;

	}

	@RequestMapping(value = "/subscribeemaildata", method = RequestMethod.POST)
	public ModelAndView saveEmailNoti(HttpSession session, @ModelAttribute("email") EmailNotiForm email, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		Long userid = null;
		Integer userId = null;
		UserSelection selection = (UserSelection) session.getAttribute(USERS_SELECTION);
		if (selection != null) {
			userid = selection.getUserId();
		}
		if (userid != null) {
			userId = Integer.valueOf(userid.intValue());
		}
		String user = "super";
		if (userId != null) {
			email.setUserid(userId);
			email.setUser(user);
			mav = new ModelAndView("emailSuccess");
			boolean messageFlag = emailService.saveNotification(email);
			mav.addObject("flag", messageFlag);
			mav.addObject("page", 1);
		} else {
			mav = new ModelAndView("success");
			mav.addObject("msgid", "User is Not Defiend");
		}
		return mav;

	}

	@RequestMapping(value = "/unsubscribeemail", method = RequestMethod.POST)
	public ModelAndView deleteEmailNoti(HttpSession session, @ModelAttribute("email") EmailNotiForm email, Model model, HttpServletRequest request) {
		ModelAndView mav;
		Long userid = null;
		Integer userId = null;
		UserSelection selection = (UserSelection) session.getAttribute(USERS_SELECTION);
		if (selection != null) {
			userid = selection.getUserId();
		}
		if (userid != null) {
			userId = Integer.valueOf(userid.intValue());
		}
		if (userId != null) {
			mav = new ModelAndView("emailSuccess");
			boolean messageFlag = emailService.deleteNotification(userId);
			mav.addObject("flag", messageFlag);
			mav.addObject("page", 0);
		} else {
			mav = new ModelAndView("success");
			mav.addObject("msgid", "User is Not Defiend");

		}
		return mav;
	}

	public ModelAndView senderdetails(HttpSession session, @ModelAttribute("email") EmailNotiForm email, Model model, HttpServletRequest request) {
		ModelAndView mav;
		Long userid = null;
		Integer userId = null;
		UserSelection selection = (UserSelection) session.getAttribute(USERS_SELECTION);
		if (selection != null) {
			userid = selection.getUserId();
		}
		if (userid != null) {
			userId = Integer.valueOf(userid.intValue());
		}
		if (userId != null) {
			mav = new ModelAndView("emailSuccess");
			boolean messageFlag = emailService.deleteNotification(userId);
			mav.addObject("flag", messageFlag);
			mav.addObject("page", 0);
		} else {
			mav = new ModelAndView("success");
			mav.addObject("msgid", "User is Not Defiend");

		}
		return mav;
	}

	@RequestMapping(value = "/subscriberApplication", method = RequestMethod.GET)
	public ModelAndView subscriberApplicationforRegisterUser(HttpSession httpSession, @ModelAttribute("subscribeFrom") SubscribeApplicationForm subscribeFrom, Model model, HttpServletRequest request) {
		ModelAndView mav;
		mav = new ModelAndView("subscribeApplication");
		try {
			List<SubscribeApplication> subscribeDetails = null;
			subscribeDetails = new ArrayList<SubscribeApplication>();

			SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
			Long uid = sessionObject.getUserId();

			subscribeDetails = emailService.getSubscribingApplicationDetail(uid);
			subscribeFrom.setScbscribeDetails(subscribeDetails);
			model.addAttribute("subscribelist", subscribeDetails);
			if (subscribeDetails != null) {
				model.addAttribute("size", subscribeDetails.size());
			} else {
				model.addAttribute("size", 0);
			}
			mav.addObject("subscribeFrom", subscribeFrom);
			// subscribeFrom.getScbscribeDetails()

		} catch (Exception e) {
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		}
		return mav;
	}

	@RequestMapping(value = "/subscriberApplicationAction", method = RequestMethod.POST)
	public ModelAndView subscriberApplicationforRegisterUserAction(HttpSession httpSession, @ModelAttribute("subscribeFrom") SubscribeApplicationForm subscribeFrom, BindingResult result, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		mav = new ModelAndView("subscribeApplication");
		String op = null;
		List<SubscribeApplication> listdetail = null;
		listdetail = new ArrayList<SubscribeApplication>();
		boolean success = true;
		try {

			SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
			Long uid = sessionObject.getUserId();

			if (uid != null) {

				listdetail = subscribeFrom.getScbscribeDetails();

				SubscribeValidator subscribeValidator = new SubscribeValidator();
				subscribeValidator.validate(subscribeFrom, result);
				if (result.hasErrors()) {

					List<SubscribeApplication> subscribeDetails = emailService.getSubscribingApplicationDetail(uid);
					subscribeFrom.setScbscribeDetails(subscribeDetails);
					model.addAttribute("subscribelist", subscribeDetails);

					if (subscribeDetails != null)
						model.addAttribute("size", subscribeDetails.size());
					else
						model.addAttribute("size", 0);
					mav.addObject("subscribeFrom", subscribeFrom);

					return mav;

				}
				if (listdetail.size() > 0) {
					Iterator<SubscribeApplication> itr = listdetail.iterator();

					while (itr.hasNext()) {

						SubscribeApplication element = (SubscribeApplication) itr.next();
						op = element.getFlag();

						if (op.equalsIgnoreCase("U")) {
							success = emailService.updateSubscribeInformation(element);
						} else if (op.equalsIgnoreCase("I")) {
							success = emailService.insertSubscribeInformation(element, httpSession);
						} else if (op.equalsIgnoreCase("D")) {
							success = emailService.deleteSubscribeInformation(element);
						}

					}

					if (success) {

						mav = new ModelAndView("redirect:subscriberApplication.htm");

					} else {
						mav = new ModelAndView("error");
						mav.addObject("error", "Subscribe Operation Failure Please Check Logs");
					}
				}
			} else {
				mav = new ModelAndView("error");
				mav.addObject("error", "user are not login");
			}

		} catch (Exception e) {
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		}

		return mav;

	}

}
