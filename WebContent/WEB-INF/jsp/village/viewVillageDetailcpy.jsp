<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<title><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message>
</title>
<style>
body {
  overflow-x:hidden;
}
</style>


</head>
<body>

<section class="content">
	<div class="row">
		<section class="col-lg-12">
			<div class="box">
				
				
		<div class="box-body">
		 <form:form class="form-horizontal" action="viewVillageAction.htm" method="POST" commandName="villageView" id="frmModifyVillage">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewVillageAction.htm"/>"/>
		
		
	
			<c:forEach var="listVillageDetails" varStatus="listVillageDetailsRow" items="${villageView.listVillageDetails}">
				<div class="box-header subheading">
		    <h4><spring:message code="Label.GENERAL.DETAIL.VILLAGE" htmlEscape="true"></spring:message></h4>
			</div>
			<table class="table table-bordered table-hover" width="80%">
    			 <tbody>
			      	<tr>
			      		<td width="30%"><spring:message htmlEscape="true" code="Label.VILLAGECODE"> : </spring:message></td>
					    <td width="50%"><spring:bind path="villageView.listVillageDetails[${listVillageDetailsRow.index}].villageCode">&nbsp;
							 <c:out value="${status.value}" escapeXml="false"/></spring:bind></td>
				   </tr>
					
					 <tr>
				        <td><spring:message htmlEscape="true" code="Label.VILLAGEVERSION"> : </spring:message></td>
				        <td><spring:bind path="villageView.listVillageDetails[${listVillageDetailsRow.index}].village_version">&nbsp;
								<c:out value="${status.value}" escapeXml="false"/></spring:bind></td>
				     </tr>
				      <tr>
				        <td><spring:message htmlEscape="true" code="Label.VILLAGEMINORVERSION"> : </spring:message></td>
				        <td><spring:bind path="villageView.listVillageDetails[${listVillageDetailsRow.index}].minorVersion">&nbsp;
								<c:out value="${status.value}" escapeXml="false"/></spring:bind></td>
				     </tr>
				      <tr>
				        <td><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"> : </spring:message></td>
				        <td><spring:bind path="villageView.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">&nbsp;
								 <c:out value="${status.value}" escapeXml="false"/></spring:bind></td>
				      </tr>
				      <tr>
				        <td><spring:message htmlEscape="true" code="Label.VILLAGENAMEINLOCAL"> : </spring:message></td>
				        <td><spring:bind path="villageView.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocal">&nbsp;
								<c:out value="${status.value}" escapeXml="false"/></spring:bind></td>
				      </tr>
				      <tr>
				        <td><spring:message htmlEscape="true" code="Label.ALIASENGLISH"> : </spring:message></td>
				        <td><spring:bind path="villageView.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglish">&nbsp;
							 <c:out value="${status.value}" escapeXml="false"/></spring:bind> </td>
				      </tr>
				      <tr>
				        <td><spring:message htmlEscape="true" code="Label.ALIASLOCAL"> : </spring:message></td>
				        <td><spring:bind path="villageView.listVillageDetails[${listVillageDetailsRow.index}].aliasLocal">&nbsp;
								<c:out value="${status.value}" escapeXml="false"/></spring:bind></td>
				      </tr>
				      <tr>
				        <td><spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"> : </spring:message></td>
				        <td><spring:bind path="villageView.listVillageDetails[${listVillageDetailsRow.index}].sscode">&nbsp;
								<c:out value="${status.value}" escapeXml="false"/></spring:bind></td>
				      </tr>
			 </tbody>
		</table>
				      
		<div class="box-header subheading">
		    <h4><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
		</div>
			<table class="table table-bordered table-hover" width="80%">
				<tbody>		      
					 <tr>
				        <td width="30%"><spring:message htmlEscape="true" code="Label.ORDERNO"> : </spring:message></td>
				        <td width="50%"><spring:bind path="villageView.listVillageDetails[${listVillageDetailsRow.index}].orderNocr">&nbsp;
							<c:out value="${status.value}" escapeXml="false"/></spring:bind></td>
				      </tr>
				      
				      
				      
				      <tr>
				        <td><spring:message htmlEscape="true" code="Label.ORDERDATE"> : </spring:message></td>
				        <td><fmt:formatDate var="orderDatecr" value="${listVillageDetails.orderDatecr}"  pattern="dd/MM/yyyy"/>
							<label>&nbsp;&nbsp;${orderDatecr}</label></td>
				      </tr>
				      
				      <tr>
				        <td><spring:message htmlEscape="true" code="Label.EFFECTIVEDATE"> : </spring:message></td>
				        <td><fmt:formatDate var="ordereffectiveDatecr" value="${listVillageDetails.ordereffectiveDatecr}"  pattern="dd/MM/yyyy"/>
							<label>&nbsp;&nbsp;${ordereffectiveDatecr} </label></td>
				      </tr>
				      
				      
				      <tr>
				        <td><spring:message htmlEscape="true" code="Label.GAZPUBDATE"> : </spring:message></td>
				        <td><fmt:formatDate var="gazPubDatecr" value="${listVillageDetails.gazPubDatecr}"  pattern="dd/MM/yyyy"/>
							<label>&nbsp;&nbsp;${gazPubDatecr} </label></td>
				     </tr>
				 </tbody>
			</table>
			
			
				
	</c:forEach>													


		<div class="table table-bordered table-hover">
		   <h4><spring:message htmlEscape="true" code="Label.VILLAGEHISTORY"></spring:message></h4>
		</div>
		
		<table class="table table-striped table-bordered" cellspacing="0" width="80%">
						 <thead>
					         <th width="5%"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
					         <th width="10%"><spring:message htmlEscape="true" code="Label.VILLAGECODE"></spring:message></th>
					         <th width="5%"><spring:message htmlEscape="true" code="Label.VILLAGEVERSION"></spring:message></th>
					         <th width="5%"><spring:message htmlEscape="true" code="Label.VILLAGEMINORVERSION"></spring:message></th>
					         <th width="40%"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></th>
					         <%-- <th width="10%"><spring:message htmlEscape="true" code="Label.ACTIVEVILLAGE"></spring:message></th>
		                     <th width="10%"><spring:message htmlEscape="true" code="Label.CREATEDFROM"></spring:message></th> --%>
						</thead>
					<tbody>					    
				         <c:forEach var="obj" varStatus="objRow" items="${villageHistory}">
							<tr>
								<td><c:out value="${objRow.index+1}" escapeXml="true"></c:out></td>
								<td><c:out value="${obj.entityCode}" escapeXml="true"></c:out></td>
								<td><c:out value="${obj.entityVersion}" escapeXml="true"></c:out></td>	
								<td><c:out value="${obj.entityMinorVersion}" escapeXml="true"></c:out></td>													
								<td><c:out value="${obj.entityNameEnglish}" escapeXml="true"></c:out></td>
								<%-- <td><c:out value="${obj.lrReplaces}" escapeXml="true"></c:out></td>
								<td><c:out value="${obj.active}" escapeXml="true"></c:out></td> --%>
						   </tr>
						</c:forEach>
					</tbody>
				</table>

				
		  </form:form> 
		  <script src="/LGD/JavaScriptServlet"></script>
		 </div>
       </div>
    </section>
   </div>
  </section>
 </body>
</html>