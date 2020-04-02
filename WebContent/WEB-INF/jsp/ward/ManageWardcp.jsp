<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>

<%@include file="ManageWardJs.jsp"%>
<head>
<c:choose>
<c:when test="${PANCHAYAT_TYPE eq 'P'}">
<c:set var="formTitle" value="label.publish.pri.ward.changes"></c:set>
</c:when>
<c:when test="${PANCHAYAT_TYPE eq 'T'}">
<c:set var="formTitle" value="label.publish.tri.ward.changes"></c:set>
</c:when>
<c:when test="${PANCHAYAT_TYPE eq 'U'}">
<c:set var="formTitle" value="label.publish.urban.ward.changes"></c:set>
</c:when>
</c:choose>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
 <script src="js/govtorder.js"></script>
<!-- <script src="js/common.js"></script> -->
<script src="js/new_ward.js"></script>
<script src="js/validationWard.js"></script>
<script src="js/validation.js"></script>
<script src="js/wardAfterPost.js"></script>


<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrWardService.js'></script>
 
<script type="text/javascript" language="javascript">

dwr.engine.setActiveReverseAjax(true);
</script>


<%-- <c:if test="${message ne null }">
<script>
customAlert("${message}");
</script>
</c:if> --%>

<script >
$(document).ready(function() {
	   $( '#demand1' ).DataTable();
	    $('#demand').DataTable();
	    $('#demand2').DataTable();
	} ); 


</script>
<c:if test="${showTable eq true and PANCHAYAT_TYPE ne 'U'}">
<script>
//alert('${dataExists}');
$(window).load(function () {
	$('#localBodyType option[value != ""]').remove();
	//alert('${wardForm.paramLocalBodyTypeCode}');
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
					//alert("index:"+index+" elementCount:"+elementCount+"localbodyLevelCodes:"+localbodyLevelCodes);
						$("#" + $(this).attr('id') +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected");
						if(index < elementCount ){
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

hideLoadingImage();
</script>
</c:if>

</head>
<body>

<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                            <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="${formTitle}" /></h3>
                                </div>
               <div class="box-body">
                 <form:form action="createWard.htm" method="POST" commandName="wardForm"  id="localGovtBodyForm" class="form-horizontal">
				    <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveandUpdateWard.htm"/>" />
				   <form:hidden path="status" value="P" />
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
           
          
          
  <c:if test="${showTable eq true}">  
		<div class="box">      
		<div class="box-header subheading">
             <h3 class="box-title">Published Wards</h3> </div>
                    <div class="box-body">
                    <form:form  method="POST" commandName="wardForm"  id="publishWardForm">
			        <form:hidden path="publishWardsList" />
			        <form:hidden path="deleteWardsList" />
			        <form:hidden path="localBodyCode"  value="${lbCode}"/>
			        <form:hidden path="PanchayatType" />
			        <form:hidden path="lbTypeHierarchy" />
				     <form:hidden path="paramLocalBodyTypeCode" />
				     <form:hidden path="localBodyLevelCodes" />
				
				<div class="form-group">
					
				<table class="table table-bordered table-striped dataTable no-footer"  id="demand1">
							<thead>
								<tr class="tblRowTitle tblclear" id="trhead">
									<th align="left" >Serial No.</th>
									<th align="left">Ward Number</th>
									<th align="left">Ward Name In English</th>
									<th align="left">Ward Name In Local</th>
									
								</tr>
							</thead>
							<tbody>
							     <c:forEach var="adminEntityDetail" varStatus="listAdminRow"  items="${wardList}">
									<tr  id="trid${offsets*limits+(listAdminRow.index+1)}"  >
										<td> ${offsets*limits+(listAdminRow.index+1)}</td>
										<td> ${adminEntityDetail.wardNumber}</td>
										<td> ${adminEntityDetail.wardNameInEnglish}	</td>
										<td> ${adminEntityDetail.wardNameInLocal}</td>
									</tr>
																			
									
								</c:forEach>
							</tbody>
						</table>
				</div>
              </div>
       
                  
                   </div>
   
<div class="form-group">                 
 <c:forEach var="wardList" varStatus="listAdminRow" items="${wardList}">
  <c:if test="${!empty listWardDetailstemp}">
	<c:forEach var="tempWardList" varStatus="listAdminRow1" items="${listWardDetailstemp}">
		<c:if test="${wardList.wardCode eq tempWardList.wardCode}">
		<c:if test="${tempWardList.isupdate}">
		<script>
	     $('#trid'+'${offsets*limits+(listAdminRow.index+1)}').css("background-color", "#FFFF94");
		</script></c:if>
        <c:if test="${tempWardList.isdelete}">
        <script>   $('#trid'+'${offsets*limits+(listAdminRow.index+1)}').css("background-color", "#FF9999"); </script>
         </c:if>
       </c:if>
   </c:forEach>			
   </c:if>
  </c:forEach>
</div> 
<div class="box-body" >
 <div class="form-group">
	  <table class="table table-bordered table-hover" width="90%">
	  <tr>
		<td class="bg-success col-sm-2"  ></td>
		<td >New Ward</td>
		<td class="bg-warning col-sm-2"></td>
		<td >Update</td>
		<td class="bg-danger col-sm-2"></td>
		<td >Delete</td>
	</tr>
  </table>
 </div>
</div>

    <div class="form-group">
		<div class="box">      
		     <div class="box-header subheading">
             <h3 class="box-title">Ward Details</h3> </div>
             <div class="box-body">
					<table class="table table-bordered table-striped dataTable no-footer" id="demand">
							<thead>
								<tr  id="trhead">
									<th  align="left">Serial No.</th>
									<th  align="left">Ward Number</th>
									<th  align="left">Ward Name In English</th>
									<th  align="left">Ward Name In Local</th>
								    <th  align="left">Publish Changes <input type="checkbox"  id="publishAll" onchange="selectAll();"/>Publish All Changes </th>
								    <th  align="left">Delete Changes <input type="checkbox"   id="deleteAll"  onchange="deleteAllWards()"/> Delete  All Changes </th>
								</tr>
							</thead>
							<tbody>
							     <c:forEach var="adminEntityDetail" varStatus="listAdminRow" items="${listWardDetailstemp}">
									<tr id="${adminEntityDetail.temp_ward_code}" <c:if test="${adminEntityDetail.isnew}" > style="background-color:#70DB70" </c:if> <c:if test="${adminEntityDetail.isupdate}" > style="background-color: 	#FFFF94" </c:if><c:if test="${adminEntityDetail.isdelete}" > style="background-color: 	#FF9999" </c:if>><!-- </tr> --> 
										<td><c:out value="${offsets*limits+(listAdminRow.index+1)}"/></td>
										<td><c:out value="${adminEntityDetail.wardNumber}"/></td>
										<td><c:out value="${adminEntityDetail.wardNameEnglish}"/>	</td>
										<td><c:out value="${adminEntityDetail.wardNameLocal}"/></td>
									    <td> <input type="checkbox"  id="p${adminEntityDetail.temp_ward_code}" value="${adminEntityDetail.temp_ward_code}" onchange="selectWard(this.value)"/> </td>
									    <td> <input type="checkbox"   id="d${adminEntityDetail.temp_ward_code}" value="${adminEntityDetail.temp_ward_code}" onchange="deleteWard(this.value)"/> </td>
									</tr>
								</c:forEach>
							
							</tbody>
		</table>
    </div>
  

 
   <div class="box-footer">
                   <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                            <button type="button" name="PublishWards" value="Publish Wards Changes" onclick="fetchWardValues();"  class="btn btn-info " > Publish Wards Changes</button>
                             <button type="button"  name="PublishWards" value="Delete Wards Changes" onclick="deleteWardValues();" class="btn btn-info " > Delete Wards Changes</button>
                        </div>
                     </div>   
             </div>
  </div>
  </div>
  
  
  
  
  <div class="form-group">
		<div class="box">      
		     <div class="box-header subheading">
             <h3 class="box-title">Deleted Wards List</h3> </div>
             <div class="box-body">
					<table class="table table-bordered table-striped dataTable no-footer" id="demand2">
								<thead>
									<tr class="tblRowTitle tblclear" id="trhead">
										<th align="left" >Serial No.</th>
										<th align="left">Ward Number</th>
										<th align="left" >Ward Name In English</th>
										<th align="left">Ward Name In Local</th>
										<th align="left">Restore Ward</th>
										
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty listWardDetailsdetails}">
								     <c:forEach var="adminEntityDetail" varStatus="listAdminRow" items="${listWardDetailsdetails}">
										<tr>
											<td> ${offsets*limits+(listAdminRow.index+1)}</td>
											<td> ${adminEntityDetail.wardNumber}</td>
											<td> ${adminEntityDetail.wardNameEnglish}</td>
											<td> ${adminEntityDetail.wardNameLocal}</td>
											<td>  <button type="button" id="${offsets*limits+(listAdminRow.index+1)}" class="btn btn-info" value="Restore ward" onclick="restoreWard('${adminEntityDetail.wardCode}','${adminEntityDetail.wardVersion}','${adminEntityDetail.lastupdatedby}',$(this).parent().parent())" >Restore Ward </button>                               </td>
										</tr>
									</c:forEach>
									</c:if>
								
								</tbody>
								</table>		
    </div>
   </div>

 
  
  </div> 
  </form:form>
  </c:if>    
</section>
   </div>
    </section>
 <script src="/LGD/JavaScriptServlet"></script>    
</body>
</html>