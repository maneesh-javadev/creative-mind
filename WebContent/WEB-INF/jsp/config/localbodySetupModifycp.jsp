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

<body >
<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
              <form:form action="local_gov_setup_step2.htm?modify=${value}" name="lgsform" method="post" commandName="lGSetupForm" class="form-horizontal">
			    <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="local_gov_setup_step2.htm"/>"/>
        		<input type="hidden" name="initialChkedValues" id="initialChkedValues" htmlEscape="true"/>
        		<input type="hidden" name="updateChkedValues" id="updateChkedValues"   htmlEscape="true"/> 
       			<input type="hidden" name="correctionChkedValues" id="correctionChkedValues" htmlEscape="true"/> 
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.LGSUP"></spring:message></h3>
                    </div>
                     
                    <table class="table table-bordered table-hover">
                        <tr>
							<th></th>
							<th><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPE"></spring:message></th>
							<th><spring:message htmlEscape="true" code="Label.NIE"></spring:message>&nbsp;<font style="color:red;">*</font></th>
							<th><spring:message htmlEscape="true" code="Label.NILE"></spring:message>&nbsp;<font style="color:red;">*</font></th>		
						</tr>
					<tbody>
					<c:if test="${! empty LgTypeDetails}">
					<c:set var="catval" value="" />
					<c:forEach var="LgTypeDetails" varStatus="LgTypeDetailsRow" items="${LgTypeDetails}">
                        <tr> 
							<td>
                             <input type="checkbox" name="check" id="${LgTypeDetails.localBodyTypeCode}" value="<c:out value='${LgTypeDetailsRow.index}' escapeXml='true'></c:out>"
									onclick="toggle('txtNE','txtNL',<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'></c:out>,<c:out value='${size}' escapeXml='true'></c:out>); " 
									<c:if test="${isCorrection}">
									
										<c:choose>
											<c:when test="${fn:contains(category , 'U')}">
												<c:if test="${LgTypeDetails.nomenEnglish != null}">disabled</c:if>
											</c:when>
										    <c:otherwise>disabled</c:otherwise>
										</c:choose></c:if> />
                                <form:hidden path="temp1" value="${LgTypeDetails.localBodyTypeCode}"/>
								<form:hidden path="levelLGB" value="${LgTypeDetails.level}" htmlEscape="true"/> 
								<form:hidden path="localBodyTypeName" value="${LgTypeDetails.localBodyTypeName}" htmlEscape="true"/> 
							    <input type="hidden" id='maxrows' value="<c:out value='${size}' escapeXml='true'></c:out>"/>

							</td>
							
	          				<td><c:out value="${LgTypeDetails.localBodyTypeName}" escapeXml="true"></c:out></td> 							
	          				<td><input name="nomenEnglish" value="<c:out value='${LgTypeDetails.nomenEnglish}' escapeXml='true'></c:out>" maxlength="50" id="txtNE<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'></c:out>" disabled="disabled" 
																		   <c:if test="${isCorrection}">onchange="enable();"</c:if>/> 
																	<label id="lblNE<c:out value="${LgTypeDetails.localBodyTypeCode}" escapeXml="false"></c:out>" style="color: red"></label>
																	<div class="errormsg"></div></td>
																	
	          				<td><input name="nomenLocal" value="<c:out value='${LgTypeDetails.nomenLocal}' escapeXml='true'></c:out>" maxlength="100" id="txtNL<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'></c:out>" disabled="disabled"
																		   <c:if test="${isCorrection}">onchange="enable();"</c:if>/> 
																	<label id="lblNL<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'></c:out>" style="color: red"></label>
																	<input type="hidden" id="tiresetup${LgTypeDetails.localBodyTypeCode}" name="tiresetup" value="<c:out value='${LgTypeDetails.tierSetupID}' escapeXml='true'></c:out>"/>
																	<div class="errormsg"></div></td>
									
									<td><div class="errormsg"><form:errors path="check"></form:errors><br/>
																	<form:errors path="nomenEnglish"></form:errors></td>
						</tr>
				</c:forEach>
				</c:if>
                  </tbody>
                  <input type="hidden" name="codes" id="codes" class="form-control" /> <input type="hidden" name="names" id="names" class="form-control" />
               </table>
              
              
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			             <c:choose>
							<c:when test="${isCorrection}">
								<button type="button" id="correct_button" class="btn btn-info" onclick="return test();" value="Apply Correction" disabled="disabled">Apply Correction</button>
							</c:when>
							<c:otherwise>
								<input type="button" id="disable_next_button" class="btn btn-info" onclick="checkedUpdateSetupRecords(); validatepage1();" value="Next .. >>" />
							</c:otherwise>
						</c:choose>
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
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
