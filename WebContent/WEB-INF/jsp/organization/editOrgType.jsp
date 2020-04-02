<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<%!String allowedNoOfAttachment = "5";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>

<%!String contextPath;%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script src="js/jquery-1.7.js" type="text/javascript" 	language="javascript"></script>
<script src="js/jquery.MultiFile.js" type="text/javascript" language="javascript"></script>
<script src="js/attachedFiles.js" type="text/javascript" language="javascript"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>


<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/shiftsubdistrict.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/disturbedEntities.js"></script>

<title><spring:message code="Label.MODIFYMINISTRY" htmlEscape="true"></spring:message></title>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/common.js"></script>

<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
	<script type="text/javascript">
/* 	function MinistryName()
		{
         	if(document.getElementById("ministryName").value == "" )
			{
//				document.getElementById("ministryName_error").innerHTML="Ministry Name is Required";
				$("#ministryName_error").show();
				$("#ministryName_msg").hide();
				$("#ministryName").addClass("error_fld");
				$("#ministryName").addClass("error_msg");
				return false;

			}
			else 
			{
				$("#ministryName_msg").hide();
				return true;
					
			}
		} */
/* 	function MinistryShortName()
	{
      if(document.getElementById("ministryshortName").value == "" )
		{
//			document.getElementById("ministryshortName_error").innerHTML="Ministry Short Name is Required";
			$("#ministryshortName_error").show();
			$("#ministryshortName_msg").hide();
			$("#ministryshortName").addClass("error_fld");
			$("#ministryshortName").addClass("error_msg");
			return false;

		}
		else 
		{
			$("#ministryName_msg").hide();
			return true;
				
		}
	} */
	function validateMinistryCorrection()
	{
		
		
		var errors = new Array();
		var error = false;


			errors[0] = vlidateOnblur('ministryName','1','15','c');


		  if(errors[0]==true){
			  error = true;
		  }
		
			if(error == true)
			{
			
			showClientSideError();
		
			return false;
			}
		else
			{
				return true;
			}
					
/* 		var msg=null;	
				if (!MinistryName()) {
					if(msg!=null)
					{
						msg=msg+"Ministry Name is Required"+ '\n';	
					}
					else
						{
						msg="Ministry Name is Required"+ '\n';	
						}
				}
										
				if(msg!=null)
					{
					alert(msg);
					return false;
				}
				else{
					
					return true;
				}
				
			return false; */
	}
	
/////////////////////////////////////////	
	function chkVillageOnLoad(){
		$("#ministryName_error").hide();
		$("#ministryNamecr_error").hide();
		$("#OrderNo_error").hide();
		$("#OrderDate_error").hide();
		$("#EffectiveDate_error").hide();
		$("#filGovernmentOrder_error").hide();
	}	
	

	</script>
</head>
<body onload='chkVillageOnLoad();' >

	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><Div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><Div class= "failur"></div></td>
				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
	          </td></tr></table>
			
				</c:if>
							 
			</div>
       </div>
       
      <div class="box" id="box">
            <a class="boxclose" id="boxclose"></a>
            <div id="validate_error" >
							<table><tr><td rowspan="2"><div class= "errorImg"></div></td>
							<td><div  class="helpMsgHeader" style="width:275px;"><h4>Error Message</h4></div></td></tr>
			                <tr><td><div class="errorfont"><spring:message code="error.blank.commonAlert" htmlEscape="true"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>	

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div>



<div id="frmcontent">
					<div class="frmhd">
					  
                          <h3 class="subtitle"><spring:message code="Label.MODIFYORGTYPE" htmlEscape="true"></spring:message></h3>
                          <ul class="listing">
                          <li>
                     <%--//this link is not working    <a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message></a> --%>
                       </li>
                       </ul>
					</div>
					<div class="clear"></div>
					<div class="frmpnlbrdr">
						<form:form action="editOrganizationType.htm" method="POST" commandName="orgTypeForm" id="frmOrgType" enctype="multipart/form-data">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="editOrganizationType"/>"/>
							  <div class="frmpnlbg">
                              <%--   <div class="frmtxt">
                                  <div class="frmhdtitle"><label><spring:message code="Label.MODIFYMINISTRYOPTION" htmlEscape="true"></spring:message></label></div>
                                    <table width="100%" class="tbl_no_brdr">                                    
                                    <tr>
                                      <td width="14%">&nbsp;</td>
                                      <td width="86%"><label><spring:message code="Label.MODIFYMINISTRYOPTION" htmlEscape="true"></spring:message></label><br />
                                          <table width="259" height="21" class="tbl_no_brdr">
                                            <tr>
                                              <td width="20" height="25" class="tblclear"><label>
                                               <form:radiobutton path="correction" type="radio" id='chkcrvillage' value="true" 
                                                onclick='toggledisplay2("chkcrvillage","correctionvillage")'/>
                                                 </label></td>
                                              <td width="83" class="tblclear"><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message></td>
                                              <td width="20" class="tblclear"><label>
                                                <form:radiobutton path="correction"  type="radio"  id='chkchvillage' value="false"
                                                 onclick='toggledisplay2("chkchvillage","changevillage")' />
                                              </label></td>
                                              <td width="136" class="tblclear"><spring:message code="Label.CHANGE" htmlEscape="true"></spring:message></td>
                                            </tr>
                                          </table>
                                      <div class="errormsg"> </div></td>
                                    </tr>
                                  </table>
                                </div>
						      </div>
							  <div   id='correctionvillage' class="frmpnlbg" style=" visibility: hidden; display:none"> --%>
							  <div class="frmtxt" >
								  <div class="frmhdtitle"  ><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message> </div>
								 
								<ul class="blocklist"  >
								<li>
								 <label for="OrgTypeName"><spring:message code="Label.ORGTYPENAMEINENG" htmlEscape="true"></spring:message></label><span class="errormsg">*</span> <br /> 
										<form:input path="orgType" class="frmfield" id="OrgTypeName" cssClass="frmfield" 
										 onfocus="validateOnFocus('OrgTypeName');show_msg('OrgTypeName')" 
										 onblur="vlidateOnblur('OrgTypeName','1','15','c');"  htmlEscape="true"/>
										<%-- onfocus="show_msg('ministryName')" onblur="MinistryName()"--%>   							
					    	                <!--  <span class="error" id="ministryName_error">Ministry Name is Required</span> -->
					    	             <form:errors path="orgType" class="errormsg" htmlEscape="true"></form:errors>  
										<div class="errormsg"></div>
										</li>
										<li>
										
										<form:input path="orgTypeCode" class="frmfield" type="hidden"/>
										</li>
										</ul>
										
									
								  </div>
                           <div class="btnpnl">                    
                            <input type="submit" name="Submit" class="btn" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return validateORGTYPE();"  />                           
                            <input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:go('home.htm');"/>                                   
					      </div>
							  
						
						</form:form>
						<script src="/LGD/JavaScriptServlet"></script>
					</div></div>
</body>
</html>