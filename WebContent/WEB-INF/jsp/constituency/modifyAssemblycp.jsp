<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>

<title>E-Panchayat</title>

<!-- <script type="text/javascript" src="js/district.js" charset="utf-8"></script>
 --><!-- <script type="text/javascript" src="js/createDistrict.js"></script> -->
  <script src="js/Parliament.js"></script>
 <script type="text/javascript" src="js/common.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<script src="js/validation.js"></script> 
<script src="js/govtorder.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubVillageService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>

<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">
dwr.engine.setActiveReverseAjax(true);
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
/* function validateform()

{
		var districtName=document.getElementById('districtNameInEn').value;
	
	if(districtName=="")
	{
	alert("Please Enter Name Of the Districit");
	document.getElementById('districtNameInEn').focus();
	return false;
	}
	return true;
} */
function getData()
{
	document.forms['form1'].submit();	
}

function doRefresh()

{
	
	document.getElementById('ddStateParliamnetSource').selectedIndex=0;
}
function selectDistrict(id,name)
{     
	
		if (id.match('PART')=='PART'){
		
	var selObj=document.getElementById('ddDestDistrict');	
	
	//var selObj2=document.getElementById('surveyListNew');
	//var selObj=document.getElementById('subDistrictListNew');	
	/*var subDistrict="";
		for (i = 0; i < selObj.options.length; i++) {
		     selObj.options[i].selected=true;
		     subDistrict +=selObj.options[i].value+",";
	 }*/
		lgdDwrStateService.getDistricts(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});
	
		
	}else {
		dwr.util.removeAllOptions('villageList');
		alert("Kindly Select the Part State From the List");
	}
	}

function handleDistrictSuccess(data) {
	// Assigns data to result id
	Label.ASSCONSTITUENCYDETAIL
	var fieldId = 'ddDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
		
	dwr.util.removeAllOptions(fieldId);	

	dwr.util.addOptions(fieldId, data,valueText,nameText);
	
}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}
	//DWR Dropdownlist Population


	//DWR Dropdownlist Population

	function hideAll() {
		
		
		$("#ddStateParliamnetSource_error").hide();
		
	}	

	function getData() {

		//selectDistrict();
		
		var errors = new Array();
		var error = false;
		errors[0] = !validatePc();
		
		for ( var i = 0; i < 1; i++)
		{
			
			if (errors[i] == true) {
				
				error = true;
				break;
			}
		}

		if (error == true) {

			showClientSideError();

			return false;
		} else {
		// added by Kirandeep on 18/06/2014
		
			$('input[name=Submit]').attr('disabled', true);
			document.forms['form1'].submit();

			return true;
			
		}
		return false;
	}

	
	function validatePc() {

		var ddStateParliamnetSource = document.getElementById('ddStateParliamnetSource');

		if (ddStateParliamnetSource.selectedIndex == 0) {
			$("#ddStateParliamnetSource_error").show();
			return false;
		}
		$("#ddStateParliamnetSource_error").hide();
		return true;
	}
	
function formSubmitAdd(){
	document.getElementById('addAnotherSD').value="true";
	selectFinal();
}
	
$( document ).ready(function() {
	var rightpanel = document.getElementById("rpnls");
	
	rightpanel.style.overflow = "hidden";
	
	
});
</script>
</head>

<body onload="hideAll();">
<%-- 	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><div class= "failur"></div></td>
				
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
			                <tr><td><div class="errorfont"><spring:message htmlEscape="true" code="error.blank.commonAlert"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>	

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div>
<div class="clear"></div> --%>
	
<section class="content">
	<div class="row">
		<section class="col-lg-12">
			<div class="box">
				
				<div class="box-header with-border">
					<h3 class="box-title"><spring:message htmlEscape="true"  code="Label.MODIFYASSEMBLY"></spring:message></h3>
				</div>
					
				
				<form:form class="form-horizontal" action="ModifyAssembly_Constituency.htm" method="POST" commandName="AssemblyForm" id="form1">
				<input type="hidden" htmlEscape="true" name="<csrf:token-name/>" value="<csrf:token-value uri="ModifyAssembly_Constituency.htm"/>" />
				  
				
				
	<div class="box-body">				       
		<div class="col-sm-12">
			<div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.MODIFYASSEMBLY"></spring:message></h4></div>
		
			<div class="form-group">							
			  <label class="col-sm-3 control-label"> <spring:message htmlEscape="true"  code="Label.SELECTPC"></spring:message><span class="mandatory">*</span></label>
				 <div class="col-sm-6">					           
		          <form:select path="selectedAssembly" class="form-control" id="ddStateParliamnetSource" onchange="getAssemblyListForAssembly(this.value);" htmlEscape="true"
					 onfocus="validateOnFocus('ddStateParliamnetSource');" onblur="vlidateOnblur('ddStateParliamnetSource','1','15','c');">
					<form:option value=""><esapi:encodeForHTMLAttribute><spring:message htmlEscape="true" code="Label.SELECTPC"></spring:message></esapi:encodeForHTMLAttribute></form:option>
					<form:options items="${distList}" itemValue="id.pcCode" itemLabel="pcNameEnglish" htmlEscape="true"></form:options>
				  </form:select>
					 <span class="errormsg" id="ddStateParliamnetSource_error"><spring:message htmlEscape="true" code="Error.PARLIAMENTSELECT"></spring:message></span>
					 <form:errors htmlEscape="true"  path="selectedAssembly" class="errormsg"></form:errors>
				</div>
			</div>

			<div class="box-footer">
			  <div class="col-sm-offset-2 col-sm-10"> 
				 <div class="pull-right">
					<button  type="button" name="Submit" class="btn btn-success" onclick="getData();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
					<button  type="button" name="Submit2" class="btn btn-warning" onclick="doRefresh();populateDistricts();"><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
					<button  type="button" name="Cancel" id="btnCancel" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
				</div>
			 </div>
		  </div>
		</div>
	 </div>
	</form:form>
   </div>
 </section>
</div>
</section>			

<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
</body>
</html>