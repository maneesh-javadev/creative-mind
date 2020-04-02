<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <% String contextPath = request.getContextPath(); %> 
<head>
<%@include file="../common/taglib_includes.jsp"%>

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrWardService.js' > </script>
<c:choose>
<c:when test="${PANCHAYAT_TYPE eq 'P'}">
<c:set var="formTitle" value="Label.addnManagePRIWardCoverage"></c:set>
</c:when>
<c:when test="${PANCHAYAT_TYPE eq 'T'}">
<c:set var="formTitle" value="Label.addnManageTRIWardCoverage"></c:set>
</c:when>
<c:when test="${PANCHAYAT_TYPE eq 'U'}">
<c:set var="formTitle" value="Label.addnManageURBANWardCoverage"></c:set>
</c:when>
</c:choose>
<script type="text/javascript" src="js/addnManageWardCoverage.js"></script>

<link href="<%=contextpthval%>/resources-localbody/css/localbody.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<%@include file="../common/dwr.jsp"%>
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<script type="text/javascript" language="javascript">

dwr.engine.setActiveReverseAjax(true);
</script>


 <script type="text/javascript">

//jquery pagination  
$(document).ready(function() {
	
	$('#manageWardCoverage').DataTable();
	
	$( "#actionSearchClose" ).click(function() {
		callActionUrl('home.htm','selWardCoverageForm');
	});
	
	$( "#actionFetchWardDetails" ).click(function() {
		//alert("hi");
		if(checkNotUrbanProcess()){
		var lbTypeHierarchylement = $( '#lbTypeHierarchy' );
		//alert("lbTypeHierarchylement"+$( lbTypeHierarchylement ).val());
			if( $_checkEmptyObject($( lbTypeHierarchylement ).val()) ){
				$(lbTypeHierarchylement).addClass("error");
				$( '#errorLbTypeHierarchy' ).text("<spring:message code='error.CHOOSEHIERARCHY' htmlEscape='true'/>");
				return false;
			}
		}
		var localBodyTypeElement = $( '#localBodyType' );
		var selectedlocalBodyType = $( localBodyTypeElement ).val();
		if( $_checkEmptyObject(selectedlocalBodyType) ){
			$(localBodyTypeElement).addClass("error");
			$( '#errorLocalBodyType' ).text("<spring:message code='error.select.LBTYPE' htmlEscape='true'/>");
			return false;
		}
		
			var element = $( '[name = localBodyLevelCodes]' );
			var localBodyElement = $( element )[$( element ).length - 1];
			if(!$_checkEmptyObject(localBodyElement) && !validateLBCode(localBodyElement)){
				return false;
			}
		
		callActionUrl('selectWardCoverage.htm','selWardCoverageForm');
			
	 });
	
});
	 

showDialogBox=function(message){
	 $("#cAlert").html(message);
			$("#cAlert").dialog({
			title : '<spring:message htmlEscape="true" code="${formTitle}"></spring:message>',
			resizable : false,
			height : 200,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
					return false;
				}
			}
		});
};

var callActionUrl = function (url,fromId) {
	
	$( 'form[id='+fromId+']' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
	$( 'form[id='+fromId+']' ).attr('method','post');
	$( 'form[id='+fromId+']' ).submit();
};

	

// jquery pagination  
</script>
<c:if test="${showTable eq true and PANCHAYAT_TYPE ne 'U'}">
<script>
$(window).load(function () {
	$('#localBodyType option[value != ""]').remove();
	var _lb_type_value = $('#lbTypeHierarchy option:selected').val();
	if (!$_checkEmptyObject(_lb_type_value)) {
		var optVaues = _lb_type_value.split(',');
		var setupCode = optVaues[0]; // local body setup code
		var setupVersion = optVaues[1]; // local body setup version
		dwrRestructuredLocalBodyService.buildLocalBodyHierarchy(parseInt(setupCode), parseInt(setupVersion), {
			callback : function(result) {
				populateLBType(result);
				$("#localBodyType option[value='${wardForm.paramLocalBodyTypeCode}']").attr("selected", "selected");
				registerCallDynamicHierarchy($('#localBodyType').get(0));
				setTimeout(function(){
					var localbodyLevelCodes =  '${wardForm.localBodyLevelCodes}'.split(",");
					var localBodyLevelElement = $("SELECT[name='localBodyLevelCodes']");
					var elementCount = $(localBodyLevelElement).size()-1;
					$(localBodyLevelElement).each(function(index){
						$("#" + $(this).attr('id') +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected");
						if(index < elementCount){
							configureLocalBodyTypeHierarchy(this);	
						}
						if(index == elementCount ){
							var elementId = $(this).attr('id');
							setTimeout(function(){
								$("#" + elementId +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected");
							},200);
						}
					});
				}, 200);
			},
			errorHandler : errorLbTypeProcess,
			async : true
		});
	}
}); 
</script>
</c:if>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ward Coverage</title>
</head>

<body >
  <section class="content">
                <div class="row">
                    <section class="col-lg-12">
                            <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="${formTitle}" /></h3>
                                </div>
               <div class="box-body">
                 <form:form action="selectWardCoveragePost.htm" method="POST" commandName="wardForm"  id="selWardCoverageForm" class="form-horizontal">
				    <input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="selectWardCoveragePost.htm"/>" />
				    <form:hidden path="PanchayatType" />
				    <%@include file="../common/showLBHierarchyHeadercp.jsp"%> 
						
                </div>
             <div class="box-footer">
                   <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                            <button type="button" id="actionFetchWardDetails" value="Fetch Wards"  class="btn btn-info " > Fetch Wards</button>
                             <button type="button" id="actionSearchClose" value="Close" class="btn btn-danger " value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" ><i class="fa fa-times-circle"></i> Close</button>
                        </div>
                     </div>   
                  </div>
               </form:form>
           </div>
           
           
 <c:if test="${dataExists eq true}"> 
		<div class="box">      
		<div class="box-header subheading">
             <h3 class="box-title">Ward Details</h3> </div>
                    <div class="box-body">
                    <form:form   commandName="wardForm"  id="wardCoverageForm">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="addnModifyWardCoverage.htm"/>" />
					<form:hidden path="wardCode" />
					<form:hidden path="localBodyCode" />
					<form:hidden path="paramLocalBodyTypeCode" />
					<form:hidden path="localBodyLevelCodes" />
					<form:hidden path="lblc" />
					<form:hidden path="selLevel" />
					<form:hidden path="PanchayatType" />
					<form:hidden path="URBAN_LEVEL" />
				
				<div class="form-group">
					
				<table class="table table-bordered table-striped dataTable no-footer" id="manageWardCoverage">
					<thead>
					<tr>
						<th><spring:message code="Label.SNO"/></th>
						<th><spring:message code="Label.WARDNUMBER"/></th>
						<th><spring:message code="Label.WARDNAMEINENG"/></th>
						<th><spring:message code="Label.WARDNAMEINLOCAL"/></th>
						<th><spring:message code="Label.AddnModify" text="Add and Modify"/></th>
					</tr>
					</thead>
						<tbody>
							<c:forEach var="obj" items="${resultList}" varStatus="sno">
								<tr>
									<td><esapi:encodeForHTMLAttribute>${sno.count}</esapi:encodeForHTMLAttribute></td>
									<td><esapi:encodeForHTMLAttribute>${obj.wardNumber}</esapi:encodeForHTMLAttribute></td>
									<td><esapi:encodeForHTMLAttribute>${obj.wardNameEnglish}</esapi:encodeForHTMLAttribute></td>
									<td><esapi:encodeForHTMLAttribute>${obj.wardNameLocal}</esapi:encodeForHTMLAttribute></td>
									<td><a href="#" onclick="javascript:editWard('${obj.wardCode}','${obj.lblc}');"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
              </div>
         <div class="box-footer">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="pull-right">
                         <button type="button" name="Submit3" class="btn btn-danger " value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                        </div>
                     </div>   
           </div>
                   </form:form>
                        <c:if test="${dataExists eq false}">
						<script>
						// customAlert("Ward not present selected Localbody");
						</script>
						</c:if>
                   </div>
             </c:if>   
          </section>
      </div>
</section>
</body>
</html>
							
								
								
								
								
								
								
								
								
								
						