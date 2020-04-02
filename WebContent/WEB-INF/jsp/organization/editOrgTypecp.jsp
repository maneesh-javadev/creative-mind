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
<script src="js/validation.js"></script>
<script src="js/shiftsubdistrict.js"></script>
<script type="text/javascript" src="js/disturbedEntities.js"></script>

<title><spring:message code="Label.MODIFYMINISTRY" htmlEscape="true"></spring:message></title>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/common.js"></script>


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

	<section class="content">
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

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="editOrganizationType.htm" method="POST" commandName="orgTypeForm" id="frmOrgType" enctype="multipart/form-data" class="form-horizontal">
				     <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="editOrganizationType"/>"/>
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.MODIFYORGTYPE" htmlEscape="true"></spring:message></h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 ><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message></h4>
                                </div>
                        <div class="box-body">
                            <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message code="Label.ORGTYPENAMEINENG" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
								<div class="col-sm-6" >
									<form:input path="orgType" id="OrgTypeName" class="form-control"   onfocus="validateOnFocus('OrgTypeName');show_msg('OrgTypeName')" 
										 onblur="vlidateOnblur('OrgTypeName','1','15','c');"  htmlEscape="true"/>
										
					    	             <form:errors path="orgType" class="errormsg" htmlEscape="true"></form:errors>  
					    	             <form:input path="orgTypeCode" class="form-control" type="hidden"/>
								  </div>
						</div>   
                 </div> 
             
                     
              
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                    <button type="submit" name="Submit" class="btn btn-success" onclick="return validateORGTYPE();"  ><spring:message code="Button.SAVE" htmlEscape="true"></spring:message> </button>             
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>