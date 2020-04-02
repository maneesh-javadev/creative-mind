<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
 
		</script>


</head>
<body >

<section class="content">
  <div class="row" id="frmcontent">
	<section class="col-lg-12">
	  <div class="box">
		<div class="box-header with-border">
			<h3 class="box-title"><spring:message htmlEscape="true" code="Label.PARCONSTITUENCYDETAIL"></spring:message></h3>
		</div>	
				
		<div class="box-body">
		   <form:form class="form-horizontal" action="modifySubDistrictAction.htm" method="POST" commandName="modifyParliamentConstituencyCmd" id="frmModifySubDistrict">
			  <div class="box-header subheading"><h4><spring:message htmlEscape="true" code="Label.PARCONSTITUENCYDETAIL"></spring:message></h4></div>
			
		   <c:forEach var="Parliamentconstituencymodify" varStatus="listSubdistrictDetailsRow" items="${modifyParliamentConstituencyCmd.listParliamentFormDetail}">
			<table class="table table-bordered table-hover">
    			 <tbody>
			      	<tr>
			      		<td><spring:message htmlEscape="true" code="Label.PARLIAMENTCONSTITUENCYNAMEINENG"></spring:message></td>
					    <td><spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].pcNameEnglish">&nbsp;
								<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind><div class="errormsg"></div></td>
				   </tr>
					
				   <tr>
				        <td><spring:message htmlEscape="true" code="Label.PARLIAMENTCONSTITUENCYNAMEINLOCAL"></spring:message></td>
				        <td><spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].pcNameLocal">&nbsp;
							<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind><div class="errormsg"></div></td>
				   </tr>
			     
			        <tr>
				        <td><spring:message htmlEscape="true" code="Label.ECICODE"></spring:message></td>
				        <td><spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].eciCode">&nbsp;
							<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind><div class="errormsg"></div></td>
			        </tr>
			   </tbody>
		  </table>				
        </c:forEach>													

		<div class="box-footer">
			<div class="col-sm-offset-2 col-sm-10">
		  	  <div class="pull-right">												
				<button type="button" name="Close" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" id="btnClose"><i class="fa fa-times-circle"></i>  <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>										
			 </div>
		   </div>
	   </div>
		  </form:form> 
		  <script src="/LGD/JavaScriptServlet"></script>
	   </div>
     </div>
   </section>
  </div>
 </section>

</body>
</html>