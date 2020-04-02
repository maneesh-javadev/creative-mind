package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.ConvertRLBtoTLB;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.forms.ConvertRLBtoTLBForm;
import in.nic.pes.lgd.forms.ConvertRLBtoULBForm;
import in.nic.pes.lgd.forms.ConvertTLBtoRLBForm;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

public interface ConvertLocalbodyService {

	public Integer saveConversionRLBtoULB(ConvertRLBtoULBForm convertform,
			GovernmentOrderForm govtForm,List<AttachedFilesForm> attachedList,int stateCode, HttpSession session)throws Exception;
	
	public GovernmentOrder saveDataInGovtOrder(GovernmentOrderForm govtForm,Session session)throws Exception;
	
	public void saveDataInAttachment(GovernmentOrder govtOrder,
			GovernmentOrderForm govtForm,List<AttachedFilesForm> attachedList,Session session)throws Exception;
	
	public boolean saveDataInGovtOrderEntityWise(GovernmentOrder govtOrder,String lbUrbanEnglish,String declareType,
			Session session)throws Exception;

	public Integer saveConversionRLBtoULBforTemplate(
			ConvertRLBtoULBForm convertform,GovernmentOrderForm govtForm, int stateCode, HttpSession session)throws Exception;

	boolean saveDataInAttachmentWithOrderCode(Integer orderCode,List<AttachedFilesForm> attachedList, Session session)	throws Exception;

	public List<ConvertRLBtoTLB> saveConversionRLBtoTLB(ConvertRLBtoTLBForm convertform,GovernmentOrderForm govtForm, List<AttachedFilesForm> attachedList,int stateCode,Integer userId) throws Exception;
	public List<ConvertRLBtoTLB> saveConversionTLBtoRLB(ConvertTLBtoRLBForm convertform,GovernmentOrderForm govtForm, List<AttachedFilesForm> attachedList,int stateCode,Integer userId) throws Exception; 
	public  boolean saveDataInAttachmentWithOrderCodeGenrate(Integer orderCode,GenerateDetails generatedetails,Session session) throws Exception;
	public List<LocalbodyforStateWise> getLocalBodyListStateWise(char localBodyType, int stateCode) throws Exception; 
	public boolean saveConversionRLBtoTLBforTemplate(GovernmentOrderForm governmentOrderForm,ConvertRLBtoTLBForm convertform, HttpSession session,int getordercode) throws Exception;
	public boolean saveConversionTLBtoRLBforTemplate(GovernmentOrderForm governmentOrderForm,ConvertTLBtoRLBForm convertform, HttpSession session,int getordercode) throws Exception;
	public boolean saveDataInAttachRLBtoTLB(GovernmentOrderForm governmentOrderForm,ConvertRLBtoTLBForm convertform,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception;
	public boolean saveDataInAttachTLBtoRLB(GovernmentOrderForm governmentOrderForm,ConvertTLBtoRLBForm convertform,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception;
}
