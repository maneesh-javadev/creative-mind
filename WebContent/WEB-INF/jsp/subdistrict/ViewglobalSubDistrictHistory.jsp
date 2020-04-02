<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<%@page import="in.nic.pes.lgd.utils.ApplicationConstant"%>
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
var timeStatmp="";
var fileLocation="";
function checkFileExists(filePath,fileName) {	
	fileLocation  =filePath;
	timeStatmp=fileName;
	lgdDwrStateService.openFile(filePath, {
			  callback: openFileCallBack  
		  });
}

function openFileCallBack(data) {
	
	if(data == null ) {
	 alert("File has been moved or deleted.");
} else {
	//str.substring(3,7)
	if(data.length>5)
		{
		var d=data.substring(0,5);
		if(d=="ERROR")
			{
			 alert("File has been moved or deleted.");
			}
		else
			{
				var form = document.form1;
				var tempTarget = form.target;
				var tempAction = form.action;
				form.target = 'download_page';
				form.method = "post";
				form.action = "fileToDownloads.do?<csrf:token uri='fileToDownloads.do'/>";
				document.form1.fileDisplayType.value = "<%=ApplicationConstant.FILE_INLINE%>";
				document.form1.fileLocation.value = fileLocation;
				document.form1.timeStatmp.value =timeStatmp;
				if ($.browser.msie) {
					p_windowProperties = "width=700px,height=450px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
					newWindow = window.open('', 'download_page', p_windowProperties);
					 if (newWindow) {
						form.submit();
						form.target = tempTarget;
						form.action = tempAction;
						newWindow.focus(); 
				 	 } else {
					      alert('You must allow popups for this to work.');
				    }  
				} else if($.browser.mozilla) {
					form.submit();		
				}
				
				else {
					NewWindow = window.open('',"download_page","width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised") ;
					form.submit();
				}
		  }
		
		}
	else
		{
		alert("File has been moved or deleted.");
		}
	


}
} 

</script>

</head>
<body >
		<div id="frmcontent">
			<div class="frmhd">
					<h2 class="Subtitle"><spring:message htmlEscape="true"  code="Label.SUBDISTRICT" ></spring:message></h3>
										 
			
		    </div>
		    
			<div class="clear"></div>
			<div class="frmpnlbrdr">	
			<form name="form1" id="form1" method="post" action="globalviewstateforcitizen.do" >
			<input type="hidden" name="fileLocation" id="fileLocation" />
			<input type="hidden" name="timeStatmp" id="timeStatmp" />
			<input type="hidden" name="fileDisplayType" id="fileDisplayType" />
					<div class="frmpnlbg">
						<div class="frmtxt">
						   <div class="frmhdtitle"><spring:message htmlEscape="true" text="Hisory Details " ></spring:message></div>
							 <table width="100%" class="tbl_with_brdr">
								<tr>
									<td width="" align="center">
										     <tr class="tblRowTitle tblclear">
												<td width="10%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO" text="Serail No"></spring:message></td>
												<td width="20%" rowspan="2"><spring:message htmlEscape="true" code="Label.SUBDISTRICTCODE" text="SubDistrict CODE"></spring:message></td>
												<td width="20%" rowspan="2"><spring:message htmlEscape="true" code="Label.SUBDISTVER" text="SubDistrict Version"></spring:message></td>
												<td width="40%" rowspan="2"><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH" text="SubDistrict Name English"></spring:message></td>
												<td width="10%" rowspan="2"><spring:message htmlEscape="true" code="Label.ACTIVESTATE" text="Status"></spring:message></td>
												<%-- <td width="40%" rowspan="2"><spring:message htmlEscape="true" code="Label.DESCRIPTION" text="description"></spring:message></td>
												 <td width="40%" rowspan="2"><spring:message htmlEscape="true" code="Label.GOVERMENTORDER" text="Govt Order"></spring:message></td> --%>
								           </tr>
								<tr><td><br/></td></tr>
										
								    		  <c:forEach var="subDistrictHistoryDetail" varStatus="listSubDistrictRow" items="${subDistrictHistoryDetail}">
											 <tr class="tblRowB">
											    <td><c:out value="${listSubDistrictRow.index+1}" escapeXml="true"></c:out></td>
										        <td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictCode}" escapeXml="true"></c:out></td>
											    <td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictVersion}" escapeXml="true"></c:out></td>
											    <td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
												<td align="left"><c:out value="${subDistrictHistoryDetail.lrReplaces}" escapeXml="true"></c:out></td>
												<%-- <td align="left"><c:out value="${subDistrictHistoryDetail.description}" escapeXml="true"></c:out></td>
												 <td width="10%" align="center">
												<c:if test="${subDistrictHistoryDetail.replacedByLocal ne '' }">
												<a href="#"><img
																			src="images/gvt.order.png"
																			onclick="javascript:checkFileExists('${subDistrictHistoryDetail.lrReplacedby}','${subDistrictHistoryDetail.replacedByLocal}');"
																			width="18" height="19" border="0" alt="Sub-District Details" />
												</a>
												</c:if>
												</td> --%>
												

											 </tr>
											</c:forEach>						    
									  </td>
							  </tr>
 						   </table>
						</div>
				       <%-- <div class="btnpnl">
					         <table width="100%" class="tbl_no_brdr">
						        <tr>
							        <td>
<!-- BACK button removed - client requirement -->
							     <label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="self.close();" id="btnClose"/></label>
							        </td>
						      </tr>
					     </table>
				    </div> --%>
			     </div>
			<%-- </c:if> --%>
			</form>
		</div>
		
		 
	</div>

</body>
</html>

