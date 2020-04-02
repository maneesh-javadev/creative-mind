<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/local_govt_setup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script src="js/lgd_js.js"></script>
<script src="js/jquery.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
</head>


<body onload="onloadSelect()">
	<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
              <form:form action="local_gov_setup_step2.htm?modify=${value}"  name="lgsform" id="lgsform" method="post" commandName="lGSetupForm">
		          <input type="hidden" name="<csrf:token-name/>"  value="<csrf:token-value uri="local_gov_setup_step2.htm"/>" />
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.LGSUP"></spring:message></h3>
                    </div>
                     
                    <table class="table table-bordered table-hover">
                        <tr>
                            <th></th>
							<th><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPE"></spring:message></th>
							<th><spring:message htmlEscape="true" code="Label.NIE"></spring:message></th>
							<th><spring:message htmlEscape="true" code="Label.NILE"></spring:message> </th>		
						</tr>
					<tbody>
					<c:if test="${! empty LgTypeDetails}">
					<c:set var="catval" value="" />
                    <c:forEach var="LgTypeDetails" varStatus="LgTypeDetailsRow" items="${LgTypeDetails}">
                        <tr> 
							<td>
                             <input type="checkbox" name="check" id="${LgTypeDetails.localBodyTypeCode}"   value="<c:out value='${LgTypeDetailsRow.index}' escapeXml='true'></c:out>" 
																		       onclick="toggle('txtNE','txtNL',<c:out value="${LgTypeDetails.localBodyTypeCode}"/>,<c:out value='${size}' escapeXml='true'></c:out>)" />
							<form:hidden path="temp1" value="${LgTypeDetails.localBodyTypeCode}" htmlEscape="true"/> 
							<form:hidden path="levelLGB" value="${LgTypeDetails.level}" htmlEscape="true"/> 
							<form:hidden path="localBodyTypeName" value="${LgTypeDetails.localBodyTypeName}" htmlEscape="true"/> 
							<input type="hidden" id='maxrows' value="<c:out value='${size}' escapeXml='true'></c:out>" /> 
							</td>
							
	          				<td><c:out value="${LgTypeDetails.localBodyTypeName}" escapeXml="true"></c:out></td> 							
	          				<td><input name="nomenEnglish" value="<c:out value='${LgTypeDetails.nomenEnglish}' escapeXml='true'></c:out>" onkeypress="validateforTextanddash()" id="txtNE<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'></c:out>" disabled htmlEscape="true" /> 
																	<label id="lblNE<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'></c:out>" style="color: red"></label>
																	<div class="errormsg"> </div></td>
																	
	          				<td><input name="nomenLocal" value="<c:out value='${LgTypeDetails.nomenLocal}' escapeXml='true'></c:out>"
																		   id="txtNL<c:out value='${LgTypeDetails.localBodyTypeCode}'></c:out>" disabled></input> 
																	<label id="lblNL<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'></c:out>" style="color: red"></label>
																	<div class="errormsg"> </div></td>
									
							<td><div class="errormsg">	<form:errors path="check" htmlEscape="true"></form:errors>
																		<br />
																		<form:errors path="nomenEnglish" htmlEscape="true"></form:errors>
																		<form:errors path="nomenLocal" htmlEscape="true"></form:errors></div>
																		
							</td>
						</tr>
				</c:forEach>
				</c:if>
                  </tbody>
                  <input type="hidden" name="codes" id="codes" class="frmfield" /> 
				  <input type="hidden" name="names" id="names" class="frmfield" />
               </table>
              
              
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			            <button type="button" id="disable_next_button" class="btn btn-info" onclick="validatepage1()" value="Next .. >>" >Next .. >></button>
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
</body>
</html>
