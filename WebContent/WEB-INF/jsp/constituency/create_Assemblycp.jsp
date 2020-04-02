<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<title>E-Panchayat</title>

<!-- <script type="text/javascript" src="js/district.js" charset="utf-8"></script>
 -->
<!-- <script type="text/javascript" src="js/createDistrict.js"></script> -->
<script src="js/Parliament.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<script src="js/validation.js"></script>


<!-- <script src="js/govtorder.js"></script> -->


<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
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
	<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrParlimentECIService.js'></script>
	<script type='text/javascript'
	src='<%=contextPath%>/dwr/call/plaincall/lgdDwrParlimentECIService.existEntityName.dwr'></script>
	<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrParlimentService.js'></script>


<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">
	
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
	dwr.engine.setActiveReverseAjax(true);

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

	function selectDistrict(id, name) {

		if (id.match('PART') == 'PART') {

			var selObj = document.getElementById('ddDestDistrict');

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

		} else {
			dwr.util.removeAllOptions('villageList');
			alert("Kindly Select the Part State From the List");
		}
	}

	function handleDistrictSuccess(data) {
		// Assigns data to result id

		var fieldId = 'ddDistrict';
		var valueText = 'districtCode';
		var nameText = 'districtNameEnglish';

		dwr.util.removeAllOptions(fieldId);

		dwr.util.addOptions(fieldId, data, valueText, nameText);

	}

	function handleDistrictError() {
		// Show a popup message
		alert("No data found!");
	}
	//DWR Dropdownlist Population

	//DWR Dropdownlist Population

	function formSubmitAdd() {
		document.getElementById('addAnotherSD').value = "true";
		selectFinal();
	}

	function hideAll() {

		$("#ddStateParliamnetSource_error").hide();
		$("#districtNameInEn_error").hide();
		$("#districtNameInEns_error").hide();
		$("#districtNameInLoc_error").hide();
		$("#AssemblyList_error").hide();
		$("#AssemblyListData_error").hide();
		$("#districtNameInEnExist_error").hide();
		$("#ECICODEData_error").hide();
		$("#ECICODEExist_error").hide();
		
		
		/* 	$("#ddassemblySource_error").hide();
		 */
	}

	function getList() {

		var districtList = "";
		var selDestDistrict = document.getElementById('ddDestinctDistrict');

		for (i = 0; i < selDestDistrict.options.length; i++) {
			selDestDistrict.options[i].selected = true;
			districtList += selDestDistrict.options[i].text + ",";
		}
	}
	
	 if ( window.addEventListener ) { 
	     window.addEventListener( "load",hideAll, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", hideAll );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = hideAll;
	  }
</script>

</head>

<body>



<%-- 	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center>
									<c:out value="${param.family_msg}" escapeXml="true"></c:out>
								</center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont">
								<c:out value="${family_error}" escapeXml="true"></c:out>
							</div></td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
				</tr>
				<tr>
					<td><div class="errorfont">
							<spring:message htmlEscape="true" code="error.blank.commonAlert"></spring:message>
						</div></td>
				</tr>
			</table>

		</div>
	</div>

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div>

	<div class="clear"></div>
	<div class="frmhd">
	<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.AC"></spring:message></h3>
		<ul class="listing">
			//this link is not working <li>
				<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
			</li>
			<li>
				<a href="#" class="frmhelp">Help</a>
			</li>
		</ul>
	</div>

	<div class="clear"></div> --%>
	
<section class="content">
	<div class="row">
        <section class="col-lg-12 ">
	        <div class="box ">
        		<div class="box-header with-border">
               		<h3 class="box-title"><spring:message htmlEscape="true" code="Label.AC"></spring:message></h3>
               </div>
               
            <div class="box-body">
            	<div class="box-header subheading">
                        	<h4><spring:message htmlEscape="true" code="Label.AC"></spring:message></h4>
		       </div>
		        <form:form class="form-horizontal"  action="addAssembly.htm" method="POST" commandName="assemblyConstituency" id="form1" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="addAssembly.htm"/>" />
				
                <div class="form-group">
               		<label class="col-sm-3 control-label">
                  	  	<spring:message	htmlEscape="true" code="Label.SELECTPC"></spring:message><span class="mandatory">*</span>
				   </label>
					  <div class="col-sm-6">
						<form:select path="contributedParliament" class="form-control" id="ddStateParliamnetSource" onchange="getAssemblyListForAssembly(this.value);" onkeypress="hideAll();"
							onfocus="validateOnFocus('ddStateParliamnetSource');helpMessage(this,'ddStateParliamnetSource_msg');" htmlEscape="true"  
							onblur="vlidateOnblur('ddStateParliamnetSource','1','25','c');hideHelp();"  onkeyup="hideMessageOnKeyPress('ddStateParliamnetSource');">
							<form:option value="" htmlEscape="true"><esapi:encodeForHTMLAttribute><spring:message htmlEscape="true" code="Label.SELECTPC"></spring:message></esapi:encodeForHTMLAttribute></form:option>
							<form:options items="${distList}" itemValue="id.pcCode" itemLabel="pcNameEnglish" htmlEscape="true"></form:options>
						</form:select>
						<span class="errormsg" id="ddStateParliamnetSource_error"><spring:message htmlEscape="true" code="Error.PARLIAMENTSELECT"></spring:message></span> 
						<label><form:errors htmlEscape="true" path="contributedParliament" class="errormsg"></form:errors> </label>
						<span style="display: none;" id="ddStateParliamnetSource_msg">Please Select Parliament Constituency</span><br></br>
					</div> 						
				</div>
								
								
								
			   <div class="form-group">
                 	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.NAMEASSEMBLYCONSTEN"></spring:message><span class="mandatory">*</span></label>
                  		<div class="col-sm-6">
                   		 <form:input path="newAssemblyNameEnglish" id="districtNameInEn" onkeypress="hideAll();" onfocus="validateOnFocus('districtNameInEn');helpMessage(this,'districtNameInEn_msg');"
							htmlEscape="true" class="form-control" onblur="validateEntityNameExistforParliamnet('${stateCode}',this.value,'A');hideAll();vlidateOnblur('districtNameInEn','1','25','m');hideHelp();"  onkeyup="hideMessageOnKeyPress('districtNameInEn');" maxlength="50"/> 
							<div class="errormsg" id="districtNameInEn_error">
								<spring:message htmlEscape="true" code="Error.PARLIAMENTENTCONENGLISH"></spring:message>
						    </div>
						    <div class="errormsg" id="districtNameInEns_error">
								<spring:message htmlEscape="true" code="Error.ASSEMBLYCONENGLISHData"></spring:message>
						    </div>
						    <div class="errormsg" id="districtNameInEnExist_error">
								<spring:message htmlEscape="true" code="Error.ASSEMBLYCONENGLISHExist"></spring:message>
						    </div>
						    <span><form:errors htmlEscape="true" path="newAssemblyNameEnglish" class="errormsg"></form:errors> </span>
							<span style="display: none;" id="districtNameInEn_msg">Please Enter Name of Assembly Constituency in English</span><br></br>
                  	</div>
              </div>
               				
              <div class="form-group">
                 <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.NAMEASSEMBLYCONSTLOCAL"></spring:message></label>
                  	<div class="col-sm-6">
                  		<form:input path="newAssemblyNameLocal" id="districtNameInLcl" onkeypress="hideAll();" onfocus="validateOnFocus('districtNameInLcl');helpMessage(this,'districtNameInLcl_msg');"
							class="form-control" htmlEscape="true" onblur="vlidateOnblur('districtNameInLcl','1','25','o');hideHelp();"  onkeyup="hideMessageOnKeyPress('districtNameInLcl');" maxlength="50"/>
							<form:errors htmlEscape="true" path="newAssemblyNameLocal" class="errormsg"></form:errors> 
							<div class="errormsg" id="districtNameInLoc_error">
								<spring:message htmlEscape="true" code="Error.ASSEMBLYCONLOCALData"></spring:message>
						    </div>
							<span class="msg" id="districtNameInLcl_msg">Please Enter Name of Assembly Constituency in Local</span><br></br>
                   	</div>
              </div>
              
                <div class="form-group">
                 <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ECICODE"></spring:message></label>
                  	<div class="col-sm-6">
                  		<form:input path="AssemblyECICODE" maxlength="10" id="census2011Code" htmlEscape="true" class="form-control" onfocus="validateOnFocus('census2011Code');helpMessage(this,'census2011Code_msg');"
							onkeypress="hideAll();" onkeyup="hideMessageOnKeyPress('census2011Code');" onblur="vlidateOnblur('census2011Code','1','25','o');hideHelp();validateNumericdigit('${stateCode}','A');"  />
							<form:errors htmlEscape="true" path="AssemblyECICODE" class="errormsg"></form:errors> 	<span class="msg" id="census2011Code_msg">Please enter ECI Code here</span>
							<div class="errormsg" id="ECICODEData_error">
								<spring:message htmlEscape="true" code="Error.ECICODEData"></spring:message>
							</div> 
							<div class="errormsg" id="ECICODEExist_error">
								<spring:message htmlEscape="true" code="Error.ECICODEExist"></spring:message>
							</div>
                   	</div>
              </div>
            	<div class="box-header subheading">
  					 <h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
				</div>				
			  <div class="form-group">
			  
                  	<%@ include file="../common/gis_nodes_constituency.jspf"%>
              </div>
								
			 <div id='district'>				
				<div class="box-header subheading">
					<h4><spring:message htmlEscape="true" code="Label.CONTRIBUTINGASSEMBLYLIST"></spring:message></h4>
                </div>
             
				<span class="errormsg" id="ddassemblySource_error"></span> <form:errors htmlEscape="true" path="contributedAssembly" class="errormsg"></form:errors>											
					
					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
							<label><spring:message htmlEscape="true" code="Label.AVAILABLEASSEMBLYL"></spring:message></label><br/>
								<form:select path="AssemblyList" class="form-control" id="ddassemblySource" multiple="true" htmlEscape="true"></form:select>
						</div>
						
						<div class="ms_buttons col-sm-2"><br>
							<button type="button" name="button3" onclick="addItemPC('ddDestinctDistrict','ddassemblySource','FULL',true);" class="btn btn-primary btn-xs btn-block">Full Assembly  <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
							<button type="button" name="button22" onclick="addItemPC('ddDestinctDistrict','ddassemblySource', 'PART',true);" class="btn btn-primary btn-xs btn-block"> Part Assembly <i class="fa fa-angle-right" aria-hidden="true"></i></button>
							<button type="button" name="button22" onclick="removeItem('ddDestinctDistrict', 'ddassemblySource', true)" class="btn btn-primary btn-xs btn-block"> Back <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
						</div>
						
						<div class="ms_selection col-sm-5">
						  	<label><spring:message htmlEscape="true" code="Label.CONTRIBUTINGASSEMBLYLIST"></spring:message></label><br/>
								<form:select name="select4" id="ddDestinctDistrict" multiple="multiple" path="contributedAssembly" class="form-control" htmlEscape="true"></form:select>
								<div class="errormsg" id="AssemblyList_error">
									<spring:message htmlEscape="true" code="Error.ASSEMBLYCONLISTBLANK"></spring:message>
				      			</div>
				      			<div class="errormsg" id="AssemblyListData_error">
									<spring:message htmlEscape="true" code="Error.ASSEMBLYCONLISTData"></spring:message>
				      			</div>
						 </div>
                    </div>
               
               <div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
	                        <button type="button" id="Submit" class="btn btn-success" onclick="getList();selectAssembly();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button>
							<button type="button" name="Submit2" class="btn btn-warning" onclick="javascript:location.href='assembly_Constituency.htm?<csrf:token uri="assembly_Constituency.htm"/>'"> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
							<button type="button" name="Cancel" id="btnCancel" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>	
                        
                       </div>
                    </div>   
              	</div>
			</div>
								
          </form:form>
         <script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
		</div>
	</div>
</section>
</div>
</section>	
</body>
</html>