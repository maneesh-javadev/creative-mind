<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/disturbedEntities.js"></script>

<title><spring:message code="Label.MODIFYSTATE"></spring:message></title>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="frmcontent">
					<div class="frmhd">
					  <table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message code="Label.UGO"></spring:message>&nbsp;</td>
                          <td>&nbsp;</td>
                         <%--//these links are not working  <td width="50" align="right"><a href="#" class="frmhelp"><spring:message code="Button.HELP"></spring:message></a></td> --%>
                        </tr>
                      </table>
					</div>
					<div class="clear"></div>
					<div class="frmpnlbrdr">
					 <form:form action="uploadGovOrder.htm" method="POST" commandName="uploadGovCmd"  enctype="multipart/form-data">
							  <div class="frmpnlbg">
                                <div class="frmtxt">
                                  <div class="frmhdtitle"><spring:message code="Label.UGO"></spring:message></div>
                                    <table width="100%" class="tbl_no_brdr">                                    
                                    <tr>
									<td><spring:message code="Label.UGO"></spring:message><span class="errormsg">*</span><br />
										 <form:input id="filGovernmentOrder" path="filePath"	class="frmfield" type="file" size="25"
										onfocus="show_msg('filGovernmentOrder');" onblur="validateSFile();" /> 
										<span class="msg" id="filGovernmentOrder_msg">
										<spring:message code="Msg.UPLOADGOVTORDER"></spring:message> </span>
										 <span class="error" id="filGovernmentOrder_error"></span>
									</td>
									</tr>
                                       </table>
                                      <div class="errormsg"> </div>
                                
                                </div>
                                       <div class="btnpnl">
                                 <table width="100%" class="tbl_no_brdr">
                                <tr>
                                  <td ><label>
                                    <input type="submit" name="Submit" class="btn" value="<spring:message code="Button.SAVE"></spring:message>" onclick="return validateAllGov();"/>
                                    </label>
                                    <input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLEAR"></spring:message>"
																	id="btnClear" onclick="resetVillageModifyForm();" />
                                    <label>
                                     <input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/> </label>
                                  
                                  </td>
                                </tr>
                              </table>
					      </div>
						      </div>
						</form:form> 
					</div>
</div> 
</body>
</html>