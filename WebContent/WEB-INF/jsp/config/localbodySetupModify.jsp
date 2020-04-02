<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/local_govt_setup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script src="js/lgd_js.js"></script>
<script src="js/jquery.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js"></script>
<script>
	function initialUpdateSetupRecords(formaction){
		var elements = document.getElementsByName('check');
		var checkedvalues = "";
		for(var i=0; i<elements.length;i++) {
			var checkbx = elements[i];
			if(checkbx.checked){
				checkedvalues += checkbx.id + ",";
			}
		}
		document.getElementById('initialChkedValues').value = checkedvalues.substring(0, checkedvalues.length-1);
	}
	
	function checkedUpdateSetupRecords(formaction){
		var elements = document.getElementsByName('check');
		var checkedvalues = "";
		for(var i=0; i<elements.length;i++) {
			var checkbx = elements[i];
			if(checkbx.checked){
				checkedvalues += checkbx.id + ",";
			}
		}
		document.getElementById('updateChkedValues').value = checkedvalues.substring(0, checkedvalues.length-1);
	}
	
	function ClearAll()
	{
		 onloadSelect();
		 initialUpdateSetupRecords('${value}');
	}
	
	
	  if ( window.addEventListener ) { 
		     window.addEventListener( "load",ClearAll, false );
		  }
		  else 
		     if ( window.attachEvent ) { 
		        window.attachEvent( "onload", ClearAll );
		  } else 
		        if ( window.onLoad ) {
		           window.onload = ClearAll;
		  }
</script>
</head>

<body > <%-- onload="onloadSelect(); initialUpdateSetupRecords('${value}');"> --%>
	<form:form action="local_gov_setup_step2.htm?modify=${value}" name="lgsform" method="post" commandName="lGSetupForm" >
        <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="local_gov_setup_step2.htm"/>"/>
        <input type="hidden" name="initialChkedValues" id="initialChkedValues" htmlEscape="true"/>
        <input type="hidden" name="updateChkedValues" id="updateChkedValues"   htmlEscape="true"/> 
        <input type="hidden" name="correctionChkedValues" id="correctionChkedValues" htmlEscape="true"/> 
		<div id="tab1">
			<!-- <table width="100%" class="tbl_no_brdr">
				<tr>
					<td valign="top" style="padding: 0px"> -->
						<div id="frmcontent">
							<div class="frmhd">
								
								  
				    <h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.LGSUP"></spring:message></h3>
							<ul id="showhelp" class="listing">
							<%-- //this link is not working <li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
				 			
							</ul>
			
								
								
								<%-- <table width="100%" class="tbl_no_brdr">
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.LGSUP"></spring:message></td>
										<td align="right">
											<a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a>
										</td>
									</tr>
								</table> --%>
							</div>
							<div class="frmpnlbg">
								<div class="frmtxt">
									<!-- <table width="100%" class="tbl_no_brdr">
										<tr align="center">
											<td style="padding: 0px"> -->
												 <div class="table col_3"> <!-- Table starts -->				
									<div class="trow thead">
									<div class="th col0"></div>
										           <div class="th col2">
														
															<strong><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPE"></spring:message></strong>
														</div>
														<div class="th col3">
															<strong><spring:message htmlEscape="true" code="Label.NIE"></spring:message>&nbsp;<font style="color:red;">*</font></strong>
														</div>
														<div class="th col1">
															<strong><spring:message htmlEscape="true" code="Label.NILE"></spring:message>&nbsp;<font style="color:red;">*</font></strong>
														</div>
													</div>
													<c:if test="${! empty LgTypeDetails}">
														<c:set var="catval" value="" />
														<c:forEach var="LgTypeDetails" varStatus="LgTypeDetailsRow" items="${LgTypeDetails}">
															  <div class="trow red">  
																<div class="td column col0"> 
																	<input type="checkbox" name="check" id="${LgTypeDetails.localBodyTypeCode}" value="<c:out value='${LgTypeDetailsRow.index}' escapeXml='true'></c:out>"
																	onclick="toggle('txtNE','txtNL',<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'></c:out>,<c:out value='${size}' escapeXml='true'></c:out>); " 
																	<c:if test="${isCorrection}">
																		<c:choose>
																			<c:when test="${fn:contains(category , 'U')}">
																				<c:if test="${LgTypeDetails.nomenEnglish != null}">disabled</c:if>
																			</c:when>
																			<c:otherwise>
																				disabled
																			</c:otherwise>
																		</c:choose>
																	</c:if>/>
																	<form:hidden path="temp1" value="${LgTypeDetails.localBodyTypeCode}"/>
																	<form:hidden path="levelLGB" value="${LgTypeDetails.level}" htmlEscape="true"/> 
																	<form:hidden path="localBodyTypeName" value="${LgTypeDetails.localBodyTypeName}" htmlEscape="true"/> 
																	<input type="hidden" id='maxrows' value="<c:out value='${size}' escapeXml='true'></c:out>"/></label>
																</div>
																<div class="td column col1"><c:out value="${LgTypeDetails.localBodyTypeName}" escapeXml="true"></c:out></div>

																<div class="td column col1">
																	<br/>
																	<input name="nomenEnglish" value="<c:out value='${LgTypeDetails.nomenEnglish}' escapeXml='true'></c:out>" maxlength="50" id="txtNE<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'></c:out>" disabled="disabled" 
																		   <c:if test="${isCorrection}">onchange="enable();"</c:if>/> 
																	<label id="lblNE<c:out value="${LgTypeDetails.localBodyTypeCode}" escapeXml="false"></c:out>" style="color: red"></label>
																	<div class="errormsg"></div>
																</div>

																<div class="td column col1">
																	<br/>
																	<input name="nomenLocal" value="<c:out value='${LgTypeDetails.nomenLocal}' escapeXml='true'></c:out>" maxlength="100" id="txtNL<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'></c:out>" disabled="disabled"
																		   <c:if test="${isCorrection}">onchange="enable();"</c:if>/> 
																	<label id="lblNL<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'></c:out>" style="color: red"></label>
																	<input type="hidden" id="tiresetup${LgTypeDetails.localBodyTypeCode}" name="tiresetup" value="<c:out value='${LgTypeDetails.tierSetupID}' escapeXml='true'></c:out>"/>
																	<div class="errormsg"></div>
																</div>
															</div>
														</c:forEach>
														<!-- <tr>
															<td colspan="4" align="left"> -->
															<div  >		<div class="errormsg">
																	<form:errors path="check"></form:errors><br/>
																	<form:errors path="nomenEnglish"></form:errors>
																   </div></div>
															<!-- </td>
														</tr> -->
													</c:if>
													<!-- <tr>
														<td colspan="4"> -->
														<div
																style="padding: 10px 0px 10px 0px" align="center">
																
																	<input type="hidden" name="codes" id="codes" class="frmfield" /> <input type="hidden" name="names" id="names" class="frmfield" />
																
															</div>
														<!-- </td>
													</tr> -->
												<!-- </table> -->
											
								</div>
							
				 <div class="btnpnl">
				      <ul class="listing">
				      <li>
						<c:choose>
							<c:when test="${isCorrection}">
								<input type="button" id="correct_button" class="btn" onclick="return test();" value="Apply Correction" disabled="disabled"/>
							</c:when>
							<c:otherwise>
								<input type="button" id="disable_next_button" class="btn" onclick="checkedUpdateSetupRecords(); validatepage1();" value="Next .. >>" />
							</c:otherwise>
						</c:choose>
					</li></ul></div></div></div></div>
		</div>
<!-- 	<input type="button" onclick="submit4()" name="Submit" id="Submit" value="Submit" align="right" style="visibility:hidden"/> -->
	</form:form>
	<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
	<script type="text/javascript" language="javascript">
		test = function (){
		    if(validateForm()){
		    	var elements = document.getElementsByName('check');
				var checkedvalues = "";
				for(var i=0; i<elements.length;i++) {
					var checkbox = elements[i];
					if(checkbox.checked){
						checkedvalues += checkbox.id + "|";
						checkedvalues += document.getElementById('txtNE'+checkbox.id).value + "|";
						checkedvalues += document.getElementById('txtNL'+checkbox.id).value + "|";
						checkedvalues += document.getElementById('tiresetup'+checkbox.id).value;
						checkedvalues += "@@";
					};
				};
				document.getElementById('correctionChkedValues').value = checkedvalues.substring(0, checkedvalues.length-2);
				document.getElementById('correct_button').disabled = true;
				document.forms['lgsform'].action = "correctionLbSetup.htm";
				document.forms['lgsform'].method = "post";
				document.forms['lgsform'].submit();
				return true;
		    };
		};
	
		validateForm = function() {
			var elements = document.getElementsByName('check');
			for(var i=0; i<elements.length;i++) {
				var checkbox = elements[i];
				if(checkbox.checked){
					var nameEnglish = document.getElementById('txtNE'+checkbox.id).value;
					if(trim(nameEnglish) == ""){
						document.getElementById('lblNE'+checkbox.id).innerHTML="<br/>Please Enter Nomenclature (In English).";
						return false;
					}
					if(!validateLetterSpace(nameEnglish)){
						document.getElementById('lblNE'+checkbox.id).innerHTML="<br/>Invalid Nomenclature (In English).";
						return false;
					}
					var nameLocal = document.getElementById('txtNL'+checkbox.id).value;
					if(trim(nameLocal) == ""){
						document.getElementById('lblNL'+checkbox.id).innerHTML="<br/>Please Enter Nomenclature (In Local language).";
						return false;
					};
					if(!validateSpecialCharacters(nameLocal)){
						document.getElementById('lblNL'+checkbox.id).innerHTML="<br/>Invalid Nomenclature (In Local language).";
						return false;
					};
				};
			};
			return true;
		};
		
		clearMsgs = function(){
			var elements = document.getElementsByName('check');
			for(var i=0; i<elements.length;i++) {
				var checkbox = elements[i];
				document.getElementById('lblNE'+checkbox.id).innerHTML="";
				document.getElementById('lblNL'+checkbox.id).innerHTML="";
			};	
		};
		enable = function(){
			clearMsgs();
			document.getElementById('correct_button').disabled = false;
		};
	</script>
</body>
</html>
