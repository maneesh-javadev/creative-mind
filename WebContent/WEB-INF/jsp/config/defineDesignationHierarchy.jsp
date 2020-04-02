<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<%!String contextPath;%>
<%!int pageNumber = 1;%>
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
<br/>
<br/>
				
				
						
	<div
		style="margin: 20px 20px 0px 60px; background: #F7F7F7; padding: 10px">
		<label> <input type="submit" name="Submit22"
				id="addLocalGovSetup" value="Add New Designation" onchange="WEB-INF/jsp/config/defineLocalGovSetup.jsp" /> </label> <label> 
				</label>
		<div class="frmtxt"
			style="position: relative; background: inherit; padding-top: 20px;">
			<div
				style="position: absolute; z-index: 1; width: 90px; text-align: right; top: 11px; left: 12px"
				class="frmhdtitle">Tier Set UP</div>

			<div class="frmtxt"
				style="position: relative; background: inherit; padding-top: 20px;left: 12px">
				<display:table style="width:900px;" cellspacing="10" cellpadding="2"
					id="data" requestURI="/DefineDesignation.htm" name="SubTier"
					pagesize="2">
					
					<table width="100%" border="1" cellpadding="0" cellspacing="1"
						bgcolor="#dfdfdf">

						<tr class="tblRowB">
							<td width="20">
							<br/>
					<br/>
					<br/>
					<br/>
							
									 <display:column
									property="nomenclatureEnglish" 
									title="Nomenclature English"  style="position: relative;  width: 190px; text-align: center; top: -11px; right: 12px"/> <display:column
									property="nomenclature_local" 
									title="Nomenclature Local" style="position: relative;  width: 190px; text-align: center; top: -11px; right: 12px"/> <display:column
									property="localBodyTypeName"  
									title="local Body Type Name" style="position: relative;  width: 190px; text-align: center; top: -11px; right: 12px"/> <display:column
									property="parentLocalBodyTypeCode"  title="parent Local Body Type Code" style="position: relative;  width: 190px; text-align: center; top: -11px; right: 12px"/>
						</tr>
					</table>
					</tr>
					</tr>
					</table>
					</td>
	</display:table>
			</div>
	</div>
	</div>
	
</body>
</html>