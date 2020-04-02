package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.ConvertRLBtoTLB;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.forms.ConvertRLBtoTLBForm;
import in.nic.pes.lgd.forms.ConvertRLBtoULBForm;
import in.nic.pes.lgd.forms.ConvertTLBtoRLBForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

public interface ConvertLocalbodyDAO {


	
	public Integer saveConversionRLBtoULBforMerge(String lbUrbanEnglish,
			int userId, int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1, String lbPARTlist1, String coveredArealist, String orderNo, Date orderDate, String orderPath, Date gazPubDate, Session session,int lbCode)throws Exception;

	

	public Integer saveConversionRLBtoULBforNew(String lbUrbanEnglish, int userId,
			int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1, String lbPARTlist1,
			String coveredArealist, String localBodyNameInEn,
			String localBodyNameInLcl, String localBodyliasInEn,
			String localBodyliasInLcl, String orderNo, Date orderDate, String orderPath, Date gazPubDate, Session session,int lbCode)throws Exception;



	public boolean saveDataInAttach(ConvertRLBtoULBForm convertform,List<AttachedFilesForm> attachedList, Session session) throws Exception;

	public boolean saveDataInAttachRLBtoTLB(GovernmentOrderForm governmentOrderForm,ConvertRLBtoTLBForm convertform,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception; 
	
	public boolean saveDataInAttachTLBtoRLB(GovernmentOrderForm governmentOrderForm,ConvertTLBtoRLBForm convertform,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception; 

	public boolean saveDataInAttachforNewUlb(ConvertRLBtoULBForm convertform,List<AttachedFilesForm> attachedList, Session session);
	public List<ConvertRLBtoTLB> saveConversionRLBtoTLBforMerge(String sourcerlbType,String lbcodeformerger,
			int userId, int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1,String lbPARTlist1, String lbFULLlist2, String coveredArealist, String orderNo, Date orderDate, String orderPath, Date gazPubDate,String destLBTypeCode,Session session)throws Exception;
	
	public List<ConvertRLBtoTLB> saveConversionTLBtoRLBforMerge(String sourcerlbType,String lbcodeformerger,
			int userId, int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1,String lbPARTlist1, String lbFULLlist2, String coveredArealist, String orderNo, Date orderDate, String orderPath, Date gazPubDate,String destLBTypeCode, Session session)throws Exception;

	
	public List<ConvertRLBtoTLB> saveConversionRLBtoTLBforNew(String sourcerlbType,String lbcodeformerger, int userId,
			int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1,String lbPARTlist1, String lbFULLlist2,
			String coveredArealist, String localBodyNameInEn,
			String localBodyNameInLcl, String localBodyliasInEn,
			String localBodyliasInLcl, String orderNo, Date orderDate, String orderPath, Date gazPubDate, String destLBTypeCode,String parentLBCode,Session session)throws Exception;
	
	public List<ConvertRLBtoTLB> saveConversionTLBtoRLBforNew(String sourcerlbType,String lbcodeformerger, int userId,
			int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1,String lbPARTlist1, String lbFULLlist2,
			String coveredArealist, String localBodyNameInEn,
			String localBodyNameInLcl, String localBodyliasInEn,
			String localBodyliasInLcl, String orderNo, Date orderDate, String orderPath, Date gazPubDate, String destLBTypeCode,String parentLBCode,Session session)throws Exception;
	
	public List<LocalbodyforStateWise> getLocalBodyListStateWise(char localBodyType, int stateCode) throws Exception;
	public boolean saveConversionRLBtoTLBforTemplate(GovernmentOrderForm governmentOrderForm,ConvertRLBtoTLBForm convertform, HttpSession session,int getordercode) throws Exception;
	public boolean saveConversionTLBtoRLBforTemplate(GovernmentOrderForm governmentOrderForm,ConvertTLBtoRLBForm convertform, HttpSession session,int getordercode) throws Exception;
	
}
