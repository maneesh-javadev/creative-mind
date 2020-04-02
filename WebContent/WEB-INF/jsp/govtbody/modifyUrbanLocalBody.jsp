<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/shiftsubdistrict.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>

<head>
<title><spring:message code="Label.MODIFYURBANLOCALBODYTYPE" htmlEscape="true"></spring:message></title>
<script src="js/shiftdistrict.js"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
</head>
 
<body>
 		<div id="frmcontent">
					<div class="frmhd">
					 
					  <table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message code="Label.MODIFYURBANLOCALBODYTYPE" htmlEscape="true"></spring:message> </td>
                          <td>&nbsp;</td>
                         <%--//this link is not working  <td width="50" align="right"><a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message></a></td> --%>
                        </tr>
                      </table>
					</div>
					
					<div class="frmpnlbrdr">
					<form:form action="modifyUrbanLocalBodyAction.htm" method="POST" commandName="localGovtBodyForm">
							  <input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="modifyUrbanLocalBodyAction.htm"/>" />
							  
							  <div class="frmpnlbg">
							 
                                <div class="frmtxt">
                                 
						      </div>
							  <div class="frmtxt">
								  <div class="frmhdtitle"><spring:message code="Label.MODIFYURBANLOCALBODYTYPE" htmlEscape="true"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								 <%--  <c:forEach var="localBodyUrbanNameList" varStatus="localBodyUrbanNameListRow" items="${localGovtBodyForm.localBodyUrbanNameList}">
                                    <tr>
                                      <td width="14%" rowspan="14">&nbsp;</td>
                                      <td><spring:message code="Label.LOCALBODYNAMEENGLISH" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="localGovtBodyForm.localBodyUrbanNameList[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind>
                                           <spring:bind path="localGovtBodyForm.localBodyUrbanNameList[${localBodyUrbanNameListRow.index}].localBodyCode">							
								            <input type="hidden" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind>
					    	               <spring:bind path="localGovtBodyForm.localBodyUrbanNameList[${localBodyUrbanNameListRow.index}].localBodyNameLocal">							
							  				<input type="hidden" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
										  </spring:bind>
                                         </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.LOCALBODYNAMELOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="localGovtBodyForm.localBodyUrbanNameList[${localBodyUrbanNameListRow.index}].localBodyNameLocal">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind>
                                         </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="localGovtBodyForm.localBodyUrbanNameList[${localBodyUrbanNameListRow.index}].aliasNameEnglish">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" onfocus="this.blur()" readonly  />							
					    	               </spring:bind>
                                          </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="localGovtBodyForm.localBodyUrbanNameList[${localBodyUrbanNameListRow.index}].alisNameLocal">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" onfocus="this.blur()" readonly />							
					    	               </spring:bind>
                                         </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
									      <tr>
                                      <td width="86%"><spring:message code="Label.TYPEURBANLOCALBODY" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="localGovtBodyForm.localBodyUrbanNameList[${localBodyUrbanNameListRow.index}].localBodyTypeName">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" readonly />							
					    	               </spring:bind>
                                          </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
									                                
                                    <tr>
                                      <td width="86%"><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="localGovtBodyForm.localBodyUrbanNameList[${localBodyUrbanNameListRow.index}].stateCode">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" readonly />							
					    	               </spring:bind>
                                          </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    
	                            </c:forEach> --%>
                                <tr>									
									<td width="86%"><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message><br /> 
									<form:select path="lgTypeName" name="tierSetupCode" class="frmsfield" style="width:295px">
                                    <form:option value=""><spring:message code="Label.MODIFYTYPEGOVTBODY" htmlEscape="true"></spring:message></form:option>
                                    <form:options items="${lgtLocalBodyType}" itemLabel="nomenclatureEnglish" itemValue="level"/> 
                                  </form:select> &nbsp;<span><form:errors htmlEscape="true"  path="lgTypeName" class="errormsg"></form:errors></span>
                                  <br/><br/>
								</td>
						     </tr>                                  
									 	 
		  </table>
							  </div>
							
						
						    <div class="btnpnl">
                              <table width="100%" class="tbl_no_brdr">
                                <tr>
                                  <td><label>
                                    <input type="submit" class="btn" name="Submit" value="Update"  />
                                    </label>                                   
                                      <label>
                                      <input type="button" class="btn" name="Submit3" value="<spring:message code="Button.C"   ></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                                      </label>
                                  </td>
                                </tr>
                              </table>
					      </div>
						
					</div>
				
		</form:form>
</div>
					
</body>
</html>