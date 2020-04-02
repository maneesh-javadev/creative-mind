<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="createPRIWardJs.jsp"%>
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<!-- <script src="resource/dashboard-resource/plugins/datatables/jquery-1.12.4.js"></script>   -->
 
</script>
<script>
$(document).ready(function() {
	
	//$('#demand').DataTable();
	
	
	
});
</script>
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
              <form:form action="createWard.htm" method="POST" class="form-horizontal" commandName="wardForm"  id="localGovtBodyForm">
				 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveandUpdateWard.htm"/>" />
				 <form:hidden path="status" value="S" />
				 <form:hidden path="PanchayatType"  />
				 <%@include file="../common/showLBHierarchyHeadercp.jsp"%> 
		</div> 
            <div class="box-footer">
                   <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                            <button type="button" id="actionFetchWardDetails" value="Fetch Wards"  class="btn btn-success" > Fetch Wards</button>
                             <button type="button" id="actionSearchClose" value="Close" class="btn btn-danger " value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" ><i class="fa fa-times-circle"></i> Close</button>
                           
                        </div>
                     </div>   
                  </div>
                </form:form>
                </div>
                    </section>
                
            
<c:if test="${showTable eq true}">
 <section class="col-lg-12">
 <div class="box">
            <div class="box-body">
            <form:form  commandName="wardForm"  id="wardForm" name="wardForm" class="">
			  <input type="hidden" id="formNominations"	name="formNominations" />
			  <input type="hidden" id="newWardList" name="newWardList" />
			  <input type="hidden" id="deleteWardList" name="deleteWardList" />
			  <form:hidden path="localBodyCode" value="${lbCode}"  class="form-horizontal"/>
                    <div id="UniqueWardNameError" style="color: red;"></div>
					<div id="wardnameMsg" style="display:none"><spring:message code="error.blank.WARDNAME" htmlEscape="true"/></div>
					<div id="UniqueWardCodeError" style="color: red;"></div>
					<div id="wardnumberMsg" style="display:none"><spring:message code="error.blank.WARDNUMBER" htmlEscape="true"/></div>
					<div class="errormsg" id="wardnumber_error1"><form:errors path="ward_number" htmlEscape="true"/></div>  
					<div class="errormsg" id="wardnumber_error2" style="display: none" ></div>	
					<div class="errormsg" id="wardnamdlocal_error1"><form:errors path="ward_NameLocal" htmlEscape="true"/></div>	
					<div id="wardnamdlocalMsg" style="display:none"><spring:message code="error.blank.WARDNAMELOCAL" htmlEscape="true"/></div>
					<div class="errormsg" id="wardnamdlocal_error2" style="display: none" ></div>	
					
				    
				     <div class="box-header subheading">
                           <h4><spring:message code="Label.listofWards" text="List of Wards"></spring:message></h3> </div>
				    <div class="form-group">
				      <label class="col-sm-3 control-label"><spring:message code="Label.NoofWards" text="Enter No. of  New Wards to be Added"></spring:message></label>
				       <div class="col-sm-6">
				       <input type="text" id="noofWards" class="form_control" maxlength="3" onkeypress="return validateNumericKeys(event);"></input>
				    <buttton type="button" onclick="addarow('1')" class="btn btn-default btn-sm" value="Add Rows" >Add Rows </buttton>
				    </div>	
				    </div>
				    <br/><br/>
				  <div class="form-group">
				    <table class="table table-bordered table-striped dataTable no-footer" id="demand">
					<thead>
						<tr >
							<th >Serial No.</th>
							<th><spring:message    code="Label.enableeDiting" htmlEscape="true" text="Enable a Ward For Editing"></spring:message></th>
							<th><spring:message	code="Label.WARDNUMBER" htmlEscape="true"></spring:message></th>
							<th><spring:message	code="Label.WARDNAMEEnglish" htmlEscape="true" text="Ward Name(In English)"></spring:message> </th>
							<th><spring:message    code="Label.WARDNAMEINLOCAL" htmlEscape="true"></spring:message></th>
							<th><spring:message    code="Label.Delete" htmlEscape="true" text="Delete Ward"></spring:message></th>
						</tr>
					</thead>
					<tbody>
					<input type="hidden" name="wardName" value="" />
					<input type="hidden" name="listWardDetails" value="${wardList}"/>
					<c:forEach var="adminEntityDetail" varStatus="listAdminRow"	items="${wardList}">
							<tr>
								<td> ${offsets*limits+(listAdminRow.index+1)}</td>
								
								<td align="center">
								   <input type="checkbox" text="Update" onchange="" value="Update" id="checkbox${offsets*limits+(listAdminRow.index+1)}" onclick="getExistingWardCheck('${adminEntityDetail.wardCode}','${offsets*limits+(listAdminRow.index+1)}','U');"></input>
								    <input type="checkbox" text=check value="${adminEntityDetail.wardCode}" id="check${offsets*limits+(listAdminRow.index+1)}" style="display: none" ></input>
								</td>
								<td>  <input type="text" id="wardnumber${offsets*limits+(listAdminRow.index+1)}" name="wardnumber${offsets*limits+(listAdminRow.index+1)}"  size="10" maxlength="10" value="${adminEntityDetail.wardNumber}" disabled="true"
										      onfocus="validateOnFocus('wardnumber');changeErrorClass(this.id);" onblur="UniqueWardValidation(this.value,2,'${offsets*limits+(listAdminRow.index+1)}');vlidateOnblur('wardnumber','1','15','c');"
												class="form-control" onkeypress="return validateNumberWardNumUrban(event);"></input>
									  <input type="hidden" id="wardnumberOld${offsets*limits+(listAdminRow.index+1)}" value="${adminEntityDetail.wardNumber}" />						
								
								</td>
								<td> <input type="text" id="wardname${offsets*limits+(listAdminRow.index+1)}" name ="wardName${offsets*limits+(listAdminRow.index+1)}" value="${adminEntityDetail.wardNameInEnglish}" disabled="true"
													   class="form-control" maxlength="250"  onfocus="validateOnFocus('wardname');changeErrorClass(this.id);"
													    onblur="UniqueWardValidation(this.value,1,'${offsets*limits+(listAdminRow.index+1)}');vlidateOnblur('wardname','1','15','m');"
														onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);" htmlEscape="true"></input>
									<input type="hidden" id="wardnameOld${offsets*limits+(listAdminRow.index+1)}" value="${adminEntityDetail.wardNameInEnglish}" />							
								</td>	
								<td> <input type="text"	id="wardnamelocal${offsets*limits+(listAdminRow.index+1)}" name="wardnamelocal${offsets*limits+(listAdminRow.index+1)}" disabled="true" value="${adminEntityDetail.wardNameInLocal}"
															class="form-control" onfocus="validateOnFocus('wardnamelocal');changeErrorClass(this.id);"  maxlength="250"  onblur="validateSpecialCharactersWardNLocal(this.value);"></input>
									<input type="hidden" id="wardnamelocalOld${offsets*limits+(listAdminRow.index+1)}" value="${adminEntityDetail.wardNameInLocal}" />					
								</td>
								<td align="center"><input type="checkbox" text="Delete" onchange="getExistingWardCheck('${adminEntityDetail.wardCode}','${offsets*limits+(listAdminRow.index+1)}','D'); " value="delete" id="delete${offsets*limits+(listAdminRow.index+1)}" onclick=""></input>
								    <input type="checkbox" text="delete" value="${adminEntityDetail.wardCode}" id="deletev${offsets*limits+(listAdminRow.index+1)}" style="display: none" ></input>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				
            </div>
            <div class="box-footer">
                   <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                       		<input class="btn btn-success" type="button" id="btnSave" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return saveEnabledvalues(); " />
                            <%-- <button class="btn btn-success " type="button" id="btnSave" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return saveEnabledvalues(); "><i class="fa fa-floppy-o"></i> Submit</button> --%>
							<button  type="button" onclick="addarow('2')" value="Add Another Row" class="btn btn-primary"  >Add Another Row</button>
                             <button type="button" name="Submit3" class="btn btn-danger " value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                        </div>
                     </div>   
            </div>
 </form:form>
 
 	<div class="modal fade" id="alertbox" tabindex="-1" role="dialog" >
		<div class="modal-dialog" >
				<div class="modal-content">
	  				<div class="modal-header">
	   				 
	    			  	<h4 class="modal-title"><spring:message htmlEscape="true" code="${formTitle}"></spring:message></h4>
	  				</div>
	  				<div class="modal-body" id="alertboxbody">
	        
	  				</div>
	     			 <div class="modal-footer">
	        		  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      
	      			</div>
			</div>
		</div>
	</div>  
	
	 
 </div>
</section>        
 </div>
 </section>
</c:if>
<script src="/LGD/JavaScriptServlet"></script> 
</body>
</html>