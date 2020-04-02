<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State" %>

<%!String contextPath;%>
<%!int pageNumber = 1;%>
<%
	contextPath = request.getContextPath();
	String jsPath = contextPath + "/js";
%>
<head>
<!--Expend and Collaps Section of Form-->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
 <script type="text/javascript" src="js/animatedcollapse.js"></script> 
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

	function submitForm() {
		document.getElementById('form3').action = "modifyLbSetup.htm?category=${category}";
		document.getElementById('btnModify').disabled = true;
		document.forms["form3"].submit();
	}
	
	function manageSetup(url,id)
	{
		
		dwr.util.setValue('tierSetupID', id, {	escapeHtml : false	});
		document.getElementById('form3').action = url;
		document.getElementById('form3').submit(); 
	}

	function correctionForm() {
		document.getElementById('form3').action = "modifyLbSetup.htm?category=${category}";
		document.getElementById('btnCorrection').disabled = true;
		document.getElementById('btnModify').disabled = true;
		document.getElementById('btnCancel').disabled = true;
		document.getElementById('isCorrection').value=true;
		document.forms["form3"].submit();
	}
	
</script>
</head>
<body>
	<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
              <form:form action="modifyLbSetup.htm?category=${category}" method="POST" id="form3" commandName="lGSetupForm">
			     <input type="hidden" id="isCorrection" name="isCorrection"/>
			    <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLbSetup.htm"/>"/>
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.CGD"></spring:message></h3>
                    </div>
                    <c:if test="${getLocalGovtSetupList.size()>0}">
                     <div class="box-header subheading">
                        <h4><spring:message htmlEscape="true"  code="Label.LBTS"></spring:message></h4>
                    </div>
                    <div class="box-body">
                    <table class="table table-bordered table-hover">
                        <tr>
							<th><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPE"></spring:message></th>
							<th><spring:message htmlEscape="true"  code="Label.NIE"/></th>
							<th><spring:message htmlEscape="true"  code="Label.NILE"></spring:message></th>		
							<th><spring:message htmlEscape="true"  code="Label.PARENTLOCALBODYTYPE"></spring:message></th>
							<th><spring:message htmlEscape="true"  code="Label.VST"></spring:message></th>										
						</tr>
					<tbody>
					<c:forEach var="current" varStatus="currentRow" items="${getLocalGovtSetupList}">
                        <tr> 
							<td><c:out value="${current.localBodyTypeName}" escapeXml="true"></c:out></td>
	          				<td><c:out value="${current.nomenclatureEnglish}" escapeXml="true"></c:out></td> 							
	          				<td><c:out value="${current.nomenclatureLocal}" escapeXml="true"></c:out></td>
	          				<td><c:out value="${current.parentLocalBodyTypeName}" escapeXml="true"></c:out></td>
							<td><a href="#" onclick="javascript:manageSetup('viewSubtype.htm',${current.tierSetupCode});" ><i class="fa fa-eye" aria-hidden="true"></i>
							</a></td>
									
						</tr>
				</c:forEach>
                  </tbody>
                  <form:input path="tierSetupID" type="hidden" name="tierSetupID" id="tierSetupID"  />	
               </table>
               </div>
              </c:if>
              <c:if test="${subType.size()>0}">
					
							 <div class="box-header subheading"><h4>Sub Type</h4></div>
							 <div class="box-body">
							 <table class="table table-bordered table-hover">
								<tr>
									 <th>Local Body Type Name</th>
									 <th class="th col2">Sub Type Name (In English)</th>
									 <th>Sub Type Name (In Local)</th>
								</tr>
								<c:forEach var="subType" varStatus="listVillageDetailsRow" items="${addVillageNew.subType}">

									<tr> 
										<td>
											<spring:bind path="addVillageNew.subType[${listVillageDetailsRow.index}].localBodyTypeName">
		                                               <option name="${status.expression}" />
														<c:out value="${status.value}" escapeXml="true"></c:out>
														</option>
												</spring:bind>
										</td>
                                        <td>
											<spring:bind path="addVillageNew.subType[${listVillageDetailsRow.index}].subtypeNameEng">
													<option name="${status.expression}" />
													<c:out value="${status.value}" escapeXml="true"></c:out>
											</spring:bind>
							     </td>
							     <td>		
								   <spring:bind path="addVillageNew.subType[${listVillageDetailsRow.index}].subtypeNameLocal">
												<option name="${status.expression}" />
												<c:out value="${status.value}" escapeXml="true"></c:out>
												</option>
										</spring:bind>
							    </td>				
                                  	</tr>
								</c:forEach>
						
						</table>
								<!-- <td align="right"> -->
							<!-- <div class="btnpnl">
								<ul class="listing">
				                <li>
									<a href="#">Previous</a>
									<div class="td column col1"></div>
									<div class="td column col1"><a
										href="#">Next</a></div>
								</li>
								</ul>
							</div> -->
						</div>
				
					</c:if>
              
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			             <button type="button" class="btn btn-success" id="btnCorrection" onclick="correctionForm()" value="Correction" >Corrections</button>
			             <div style="display: none;"><button type="button" id="btnModify" class="btn btn-success" onclick="submitForm()" value="Modify" >Modify</button></div>
			             <button type="button" id="btnCancel" class="btn btn-danger" name="Submit6"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> </button>
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
 <script src="/LGD/JavaScriptServlet"></script>
 
 <c:if test="${isCorrectionDone}">
	 <div id="dialog-message" title="Operational Message"> <esapi:encodeForHTML>${successmsg}</esapi:encodeForHTML></div>
	 <script>
		$(function() {		
			$("#dialog:ui-dialog").dialog("destroy");
			$("#dialog-message" ).dialog({
				modal: true,
				buttons: {
					Ok: function() {$( this).dialog("close");}
				}
			});
		});
	 </script>
  </c:if>
</body>
</html>