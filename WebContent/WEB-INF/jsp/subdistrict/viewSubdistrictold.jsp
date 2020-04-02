<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<script type="text/javascript" src="js/cancel.js"></script>
<html>
<%!String contextPath;
%>
<%!int pageNumber=1;
%>
<%
	contextPath = request.getContextPath();
	String jsPath = contextPath + "/js";
	
  %>
<head>
<link href="<%=contextPath%>/css/displayTag.css" rel="stylesheet"
	type="text/css" />
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />

<title>ContributeVillage</title>
</head>
<body>
	
		<div
			style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px">
			<div class="frmtxt"
				style="position: relative; background: inherit; padding-top: 20px;">
				<div
					style="position: absolute; z-index: 1; width: 90px; text-align: center; top: -11px; left: 12px"
					class="frmhdtitle">View Sub District</div>

				<div class="frmtxt"
				style="position: relative; background:inherit; padding-top: 20px;">
			<display:table style="width:900px;" cellspacing="2" cellpadding="2"
			id="data" requestURI="/viewsubdistrict.htm?51-100" name="listsubdistrictDetails"
			pagesize="5"></br>
			</br>
			</br>
			</br>
			
			<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dfdfdf">
                                     
                       <tr class="tblRowB">
                          <td><display:column property="subdistrictNameEnglish" sortable="true"	title="Name of Sub District(In English)" /></td>
                          <td><display:column property="subdistrictNameLocal" sortable="true"	title="Name of Sub District(In Local)" /></td>
                          <td><display:column property="aliasEnglish" sortable="true" title="Alias (In English)" /></td>
                          <td><display:column property="aliasLocal" sortable="true" title="Alias (In Hindi)" /></td>
                        <%--     <td> <display:column &nbsp;<a href="modifySubDistrict.htm?id=${SubDistrict.id}"/>Modify</a></td>  --%>
                      </tr>
                  
                                    
             </table>
			<%-- <table width="100%" border="1" cellpadding="10" cellspacing="0">
			<tr>
			<td>
			<display:column property="subdistrictNameEnglish" sortable="true"
				title="sub district Name English" /></td>
			<td>	<display:column property="subdistrictNameEnglish" sortable="true"
				title="sub district Name English" decorator=""/></td>
				<td><display:column property="subdistrictNameEnglish" sortable="true"
				title="sub district Name English" /></td>
				<td><display:column property="subdistrictNameEnglish" sortable="true"
				title="sub district Name English" /></td>
				<td><display:column property="subdistrictNameEnglish" sortable="true"
				title="sub district Name English" /></td>
				<td><display:column property="subdistrictNameEnglish" sortable="true"
				title="sub district Name English" /></td>
				<td><display:column property="subdistrictNameEnglish" sortable="true"
				title="sub district Name English" /></td>
				<td><display:column property="subdistrictNameEnglish" sortable="true"
				title="sub district Name English" /></td>
				<td><display:column property="subdistrictNameEnglish" sortable="true"
				title="sub district Name English" /></td>
				<td><display:column property="subdistrictNameEnglish" sortable="true"
				title="sub district Name English" /></td>
				<td><display:column property="subdistrictNameEnglish" sortable="true"
				title="sub district Name English" /><td>
			</td></tr>
			</table> --%>
		</display:table>
				
				
					
					
					
					
					
				
				
			</div>
				
				
				
										
								

			</div>

		</div>
	

</body>
</html>