<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../taglib_includes.jsp"%>

<%!String contextPath;%>
<%@include file="../../homebody/commanInclude.jsp"%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- <script type="text/javascript" src="js/common.js"></script> -->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>

<script type="text/javascript" language="Javascript">
$(document).ready(function() {
	$("[id^=searchBy]").change(function() {
		$('#' + $(this).attr('paramShow')).show();
		$('#' + $(this).attr('paramHide')).hide();
		
	});
});

var parentLevel='S';
function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
} 
function manageState1(url,stateid,type,level){	
 	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentCode', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentLevel',parentLevel , {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }

function manageState2(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentCode', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentLevel',parentLevel , {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState3(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentCode', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentLevel',parentLevel , {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState4(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState5(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState6(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState7(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }

$(document).ready(function(){
	$("#tbl_with_brdr tr:even").css("background-color", "#dedede");
	$("#tbl_with_brdr tr:odd").css("background-color", "#ffffff");
});
	

         
         
function PrintDiv() {
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("printable2");
	var Win4Print = window.open('', '', 'left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0');
	Win4Print.document.write(printContent.innerHTML);
	Win4Print.document.close();
	Win4Print.focus();
	Win4Print.print();
	Win4Print.close();
	document.getElementById('footer').style.display = 'none';
	document.getElementById('footer').style.visibility = 'hidden';
	return false;
}

function displayMap (stateCode) {
	openGISModal(stateCode, 1, "P", null, null);
 }
 
 validateReport=function(){
	 var error=false;
	 if ($("#searchByPartiFinYear").is(':checked')){
			var financialYear=$("#financialYear").val();
			if($_checkEmptyObject(financialYear)){
				error=true; 
				 $( '#errrparentType').text("Select Financial Year");
			}
	 }
	 
	 var capchaAns=$("#captchaAnswer").val();
	 if(capchaAns==""){
		 document.getElementById("errEmptyCaptcha").innerHTML = "<spring:message code="Error.EmptyCaptcha"/>"; 
		    document.getElementById("errEmptyCaptcha").focus();
		error=true;
	 }
	 
	 if(!error){
		 callActionUrl('rptConsolidateforRuralLB.do');
	 }
	 
	 
 };
 
 /* The {@code $_checkEmptyObject} used to check object / element  
  * is empty or undefined.
  */
  var $_checkEmptyObject = function(obj) {
  	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
  		return true;
  	}
  	return false;
  };

 
callActionUrl=function(url){
 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
	document.forms['sectionForm'].method = "POST";
    document.forms['sectionForm'].submit(); */
   
    $( 'form[id=form1]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=form1]' ).attr('method','post');
	$( 'form[id=form1]' ).submit();
};
</script>
</head>
<body>

<section class="content">

<!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <c:choose>
				<c:when test="${empty consolidateLBList}">
                 <h3 class="box-title"><spring:message code="Label.ConsolidatedReportForRuralLB" htmlEscape="true"></spring:message></h3>
                </c:when>
                <c:otherwise>
                	<a id="showprint" href="#" class="pull-right"><img src='<%=contextPath%>/images/printer_icon.png' border="0" alt="Print" onclick="PrintDiv();" /></a></td>
				</c:otherwise>
			  </c:choose>
            </div><!-- /.box-header -->
                
                <!-- form start -->
                <form:form class="form-horizontal" commandName="consolidateReportForRuralLB" id="form1" name="form1" action="rptConsolidateforRuralLB.do">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rptConsolidateforRuralLB.do"/>" />
						<form:input path="parentCode" type="hidden"  />
  						<form:input path="parentLevel" type="hidden"  />
 							<c:if test="${empty consolidateLBList}">
				<div class="box-body" id="divCenterAligned">
					 <div class="box-header subheading">
						<h4><spring:message htmlEscape="true" code="Label.FILTEROPTFORVIEWConsolidatedReportPanchayats" text="Filter Options For Consolidated Report of Panchayats"></spring:message></h4>
	                </div>
	                 <div class="form-group">
	                    <label class="col-sm-4 control-label"></label>
	                    <div class="col-sm-6">
	                     	<form:radiobutton path="searchCriteriaType" id='searchByActiveFinYear' value="A" checked="checked" paramShow="displayNameCode" paramHide="displayHierarchy"/>List of localbodies Currently Active&nbsp;&nbsp;
							<form:radiobutton path="searchCriteriaType" id='searchByPartiFinYear' value="P" paramShow="displayHierarchy" paramHide="displayNameCode"/>List of localbodies for financial Year
	                    </div>
	                 </div>
	                    <h4><!-- Used header for blank head, please dont remove.  --></h4>
	                    
		                    
		                   <%--  <div id="displayNameCode" class="form-group ">
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message></label>
		                      <div class="col-sm-6">
		                      	<form:input id="entityCode" path="paramEntityCode" class="form-control" /><br>
		                     </div>
	                       
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.LOCALGOVTBODYNAME"></spring:message></label>
		                      <div class="col-sm-6">
		                      	<form:input id="entityName" path="paramEntityName" class="form-control" /><br>
		                     </div>
		                     	<span class="errormessage" id="errorentityCodeorentityName"></span>
	                       </div> --%>
	                       
	                       <div id="displayHierarchy" style="display: none;" class="form-group">
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Select Financial Year"></spring:message></label>
		                      <div class="col-sm-6">
		                      	<form:select path="financialYear" class="form-control" id="financialYear" onchange="error_remove();">
		                      	<form:option value="2018-2019"><spring:message htmlEscape="true" code="" text="2018-2019"></spring:message></form:option>
								<form:option value="2017-2018"><spring:message htmlEscape="true" code="" text="2017-2018"></spring:message></form:option>
								<form:option value="2016-2017"><spring:message htmlEscape="true" code="2016-2017"></spring:message></form:option>											
								<form:option value="2015-2016"><spring:message htmlEscape="true" code="2015-2016"></spring:message></form:option>
								<form:option value="2014-2015"><spring:message htmlEscape="true" code="2014-2015"></spring:message></form:option>
								<form:option value="2013-2014"><spring:message htmlEscape="true" code="2013-2014"></spring:message></form:option>
								<form:option value="2012-2013"><spring:message htmlEscape="true" code="2012-2013"></spring:message></form:option>
								<form:option value="2011-2012"><spring:message htmlEscape="true" code="2011-2012"></spring:message></form:option>
								<form:option value="2010-2011"><spring:message htmlEscape="true" code="2009-2010"></spring:message></form:option>
								<form:option value="2008-2009"><spring:message htmlEscape="true" code="2008-2009"></spring:message></form:option>
								<form:option value="2007-2008"><spring:message htmlEscape="true" code="2007-2008"></spring:message></form:option>
								<form:option value="2006-2007"><spring:message htmlEscape="true" code="2006-2007"></spring:message></form:option>
								<form:option value="2005-2006"><spring:message htmlEscape="true" code="2005-2006"></spring:message></form:option>	
								</form:select><br>
								<span class="errormsg" id="errorfinancialYear"></span>
		                     </div>
		                  </div>
	                       <div>
	                       <div class="form-group">
		                     <label for="captchaImage" class="col-sm-4 control-label"></label>
		                       <div class="col-sm-6">
		                        <img src="captchaImage" alt="Captcha Image" id ="captchaImageId" /><br>
		                       </div>
		                   </div>
		                    
		                   <div class="form-group">
		                     <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		                       <div class="col-sm-6">
		                      	 <form:input path="captchaAnswers" id="captchaAnswer" maxlength="5" autocomplete="off"  />
		                      	  <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
								   <span class="errormsg" id="errorcaptchaAnswers"></span>
								   <span class="errormsg" id="errEmptyCaptcha"></span>
									<c:if test="${not empty captchaSuccess1 and not captchaSuccess1}"><br/>
									<label><!-- Used Label, please dont remove. --></label>
									<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
									</c:if>
		                       </div>
	                    	</div>
					  </div>
				 
						<div class="box-footer">
	                     <div class="col-sm-offset-2 col-sm-10">
	                       <div class="pull-right">
	                      		<button type="button" class="btn btn-success " onclick="validateReport();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"></spring:message></button>
	                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
	                        </div>
	                     </div>   
		                </div><!-- /.box-footer -->
		          </c:if>  
		                  
		                  
               <div class="box-body" id="printable2">
               <c:if test="${!empty consolidateLBList}">
				<form:input path="financialYear" type="hidden"  />
				  <h3 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;"><spring:message code="Label.ConsolidatedReportForRuralLB" htmlEscape="true"></spring:message>
						<c:if test="${consolidateReportLBRPT.searchCriteriaType ne 'A'  and consolidateReportLBRPT.financialYear ne ''}">
							<c:out value="(${consolidateReportLBRPT.financialYear})" escapeXml="true"></c:out>
						</c:if>
				 </h3>
									  
				* N.A.- Not Applicable <br />
				** Due to periodic elections, data is dynamic in nature and keep on changing <br />
				*** Local Government Directory is now mapped to Census 2011 village codes <br />
			 <div class="table-responsive ">	
	           <table class="table table-striped table-bordered dataTable" id="tbl_no_brdr" >
					<thead>
						<tr>
							<th ><spring:message code="Label.SRNO" htmlEscape="true"></spring:message></th>
							<th ><spring:message code="Label.STATENAME" htmlEscape="true"></spring:message></th>
							<th ><spring:message code="Label.DISTRICTPANCHYATNME" htmlEscape="true"></spring:message></th>
							<th ><spring:message code="Label.INTERPANCHYATNME" htmlEscape="true"></spring:message></th>
							<th ><spring:message code="Label.VILLAGEPANCHYATNME" htmlEscape="true"></spring:message></th>
							 <!-- Merging issue revert beck 05/10/2017 --><!-- <th style="text-align: center">Map</th> -->
						</tr>
					</thead>
					
					<tbody>
					 	<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow" items="${consolidateLBList}">
							<c:set var="dpLevel" value="${panchaytSetUp.dp_count}"/>
							<c:set var="ipLevel" value="${panchaytSetUp.ip_count}"/>
							<c:set var="vpLevel" value="${panchaytSetUp.vp_count}"/>
							<c:set var="stateCode" value="${panchaytSetUp.state_code}"/>
							<c:set var="dpTotal" value="${dpTotal + dpLevel}"/>
							<c:set var="ipTotal" value="${ipTotal + ipLevel}"/>
							<c:set var="vpTotal" value="${vpTotal + vpLevel}"/>
							<tr>
								<td width="6%"><c:out value="${panchaytSetUpRow.count}" escapeXml="true"></c:out></td>
								<td width="16%" align="left"><c:out	value="${panchaytSetUp.state_name_english}" escapeXml="true"></c:out></td>
								<td width="11%" align="center">
									<c:choose>
										<c:when test="${dpLevel eq 0}">
											<c:out value="N.A."/>
										</c:when>
										<c:otherwise>
											<a 	href="#" onclick="javascript:manageState1('rptConsolidateforPanbyLevelRural.do',${stateCode},'P','D');">
											<c:out value="${dpLevel}" escapeXml="true"></c:out></a>
										</c:otherwise> 
									</c:choose>
								</td>
								<td width="11%" align="center">
									<c:choose>
										<c:when test="${ipLevel eq 0}">
											<c:out value="N.A."/>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${dpLevel eq 0}">
													<a href="#" onclick="javascript:manageState2('rptConsolidateforPanbyLevelRural.do',${stateCode},'P','I');"><c:out
													value="${ipLevel}" escapeXml="true"></c:out></a>
												</c:when>
												<c:otherwise>
													<c:out value="${ipLevel}" escapeXml="true"></c:out>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</td>
								<td width="11%" align="center">
									<c:choose>
										<c:when test="${vpLevel eq 0}">
											<c:out value="N.A."/>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${dpLevel eq 0 && ipLevel eq 0}">
													<a href="#" onclick="javascript:manageState3('rptConsolidateforPanbyLevelRural.do',${stateCode},'P','V');">
													<c:out value="${vpLevel}" escapeXml="true"></c:out></a>
												</c:when>
												<c:otherwise>
														<c:out value="${vpLevel}" escapeXml="true"></c:out>
												</c:otherwise>
											</c:choose>	
										</c:otherwise>
									</c:choose>
								</td>
								<%-- <td align="center" width="10%">
									<c:if test="${dpLevel gt 0}">
									<a href="#" onclick="javascript:displayMap('${panchaytSetUp.state_code}');"><i class="fa fa-map-marker" aria-hidden="true"></i></a>
										<a style="text-decoration: none;" href="#" onclick="javascript:showMap('${panchaytSetUp.state_code}')">view</a>
									</c:if>
								</td> --%>
							</tr>
						</c:forEach>
						<form:input path="stateId" type="hidden" name="stateId" id="stateId" />
							<form:input path="statetype" type="hidden" name="statetype" id="statetype" />
							<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" />
							<tr>
								<td width="4%"></td>
								<td width="16%" align="left"><label><spring:message code="Label.TOTALS"></spring:message> </label></td>
								<td width="11%" align="center"><c:out value="${dpTotal}" escapeXml="true"></c:out></td>
								<td width="11%" align="center"><c:out value="${ipTotal}" escapeXml="true"></c:out></td>
								<td width="11%" align="center"><c:out value="${vpTotal}" escapeXml="true"></c:out></td>
								
							</tr>
						</tbody>
					</table>
					</div>
					</c:if>
						 <c:if test="${! empty consolidateLBList}">
						<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
							<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
							</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%></label>
							</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						</div>
					</c:if>
					<div id="footer"></div>
	           	 </div> 
	           
					
				<c:if test="${! empty consolidateLBList}">
					<div class="box-footer" id="showbutton">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
							<button type="button" name="Submit3" id="btn1" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
					   </div>
					 </div>
				  </div>
				</c:if>
				</div>
		      </form:form>
			</div>
		</section>
		</div>
	</div>
</section>
	
	
</body>
</html>