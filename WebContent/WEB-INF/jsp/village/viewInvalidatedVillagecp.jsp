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
<title><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message>
</title>

<script type="text/javascript">
function loadPage() {
		var mypath = window.location.href;

		document.getElementById('btnback').style.visibility='visible';
		document.getElementById('btnCancel').style.visibility='visible';
		document.getElementById('btnClose').style.visibility='hidden';
		var mySplitResult = mypath.split("&");

		if(mySplitResult[1]!="")
			{
		var type=mySplitResult[1].replace("type=","");
		

		if(type=='S')
			{
			document.getElementById('btnback').style.visibility='hidden';
			document.getElementById('btnCancel').style.visibility='hidden';
			document.getElementById('btnClose').style.visibility='visible';
			
			}
		else
			{
			document.getElementById('btnback').style.visibility='visible';
			document.getElementById('btnCancel').style.visibility='visible';
			document.getElementById('btnClose').style.visibility='hidden';
			}
			}
		
		
		
		
}
		</script>
</head>
<body onload="loadPage();">


<section class="content">
        <div class="row">
            <section class="col-lg-12">
              <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message htmlEscape="true" code="Label.INVALIDATEDVILLAGE"></spring:message></h3>
                </div>
              <table class="table table-bordered table-hover">
    
    <tbody>
      <tr>
		  <td width="6%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
		  <td width="16%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGECODE"></spring:message></td>
		  <td width="20%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGEVERSION"></spring:message></td>
		  <td width="20%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGEMINORVERSION"></spring:message></td>
		  <td width="20%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
		   <td width="20%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGESTATUS"></spring:message></td>
      </tr>
      <tr>
			<!-- <td><br /></td> -->
	</tr> 
	<c:if test="${empty villageList}">
	    <tr>
		   <td colspan="1" align="left"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></td>
		 </tr>
     </c:if>
      <c:if test="${! empty villageList}">
		<c:forEach var="villageListDetail" varStatus="listVillageRow" items="${villageList}">
			
			  <c:forEach var="villageL" varStatus="listVRow" items="${villageListDetail}">
			  <tr class="tblRowB">
				<td width="6%"><c:out value="${listVillageRow.index+1}" escapeXml="true"></c:out></td>
				<td align="left"><c:out value="${villageL.villageCode}" escapeXml="true"></c:out></td>
				<td align="left"><c:out value="${villageL.villageVersion}" escapeXml="true"></c:out></td>
				<td align="left"><c:out value="${villageL.minorVersion}" escapeXml="true"></c:out></td>
				<td align="left"><c:out value="${villageL.villageNameEnglish}" escapeXml="true"></c:out></td>											
				<td align="left">Invalidated</td>
				</tr>
			</c:forEach>
			
		</c:forEach>																		
	 </c:if>
    </tbody>
  </table>
     <div class="box-footer">  
    			    <button type="submit" class="btn btn-danger pull-right" name="Submit6" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                  </div>
              </div>
     </section>
   </div>
 </section>
</body>
</html>

