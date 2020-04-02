<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/common.js"></script>

<link rel="stylesheet" href="<%=contextpthval%>/jquery.treeview/css/jquery.treeview.css" />
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>

<script type="text/javascript" src="<%=contextpthval%>/jquery.treeview/jquery.treeview.js"></script>
<script type="text/javascript" src='<%=contextpthval%>/js/hierarchy-admin-department.js'></script>
<%@include file="../common/dwr.jsp"%>
<script type="text/javascript" language="javascript">
    var isCenterUser=isParseJson('${isCenterUser}');
    //alert(isCenterUser);
	var _state_code = '<c:out value="${stateCode}" escapeXml="true"></c:out>';
	var _process_url = "continueCreateAdminDepartmentProcess.htm?<csrf:token uri='continueCreateAdminDepartmentProcess.htm'/>";
	dwr.engine.setActiveReverseAjax(true);
	$(document).ready(function(){
		$("#browser").treeview();
	});
</script>
</head>
<body>
    <c:choose>
		<c:when test="${isOrganizationFlow}" >
			<c:set var="formTitle" value="Label.CREATEORG"></c:set>
		</c:when>
		<c:when test="${isCenterUser}">
			<c:set var="formTitle" value="label.create.minstordept"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="formTitle" value="Label.CREATEDEPT"></c:set>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${isOrganizationFlow}">
		
	</c:if>
	
		
	<div class="row">
        <!-- main col -->
        <section class="col-lg-12">

        <div class="box">
	      <div class="box-header with-border">
	        <h3 class="box-title"><spring:message code="${formTitle}" htmlEscape="true"></spring:message></h3>
	      </div><!-- /.box-header -->
	      
	    <form:form id="adminOrgDeptForm" commandName="adminOrgDeptForm" class="form-horizontal">
   		<input type="hidden" id="hierarchySequence" name="hierarchySequence"/>
   		<form:hidden path="topNode" value="true"/>
   		
   		
   		 	<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true" code="Label.BuildHierarchy"/></h4>
            </div><!-- /.box-header -->
            
            
            <div class="form-group">
				  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.UnitLevels"/> </label>
				  <div class="col-sm-6">
				  		<select id="adminLevelNameLocal" name="adminLevelNameLocal" class="form-control" style="width: 200px;">
				        		<option value="0">Select</option>
				        		<optgroup id="landRegionUnits" label="Land Regions Unit Levels">
				        			<c:forEach var="lrUnitLevel" items="${landRegionUnitLevels}">
				        				<option value="${lrUnitLevel.adminUnitCode}"><c:out value="${lrUnitLevel.adminLevelNameEng}" escapeXml="true"></c:out></option>
				        			</c:forEach>
				        		</optgroup>
				        		<c:if test="${not isCenterUser}">
				        		<optgroup id=" localbBodyType" label=" Local Body Types">
				        			<c:forEach var="localBody" items="${localBodyTypes}">
				        				<option value="-${localBody.localBodyTypeCode}"><c:out value="${localBody.name}" escapeXml="true"></c:out></option>
				        			
				        			</c:forEach>
				        		</c:if>
				        		</optgroup>
				        		<optgroup id="adminUnits" label="Administrative Unit Levels">
				        			<c:forEach var="adminUnitLevel" items="${adminUnitLevels}">
				        				<option value="${adminUnitLevel.adminUnitCode}"><c:out value="${adminUnitLevel.adminLevelNameEng}" escapeXml="true"></c:out></option>
				        			</c:forEach>
				        		</optgroup>
									        		
						</select>
				  </div>
				  <div class="col-sm-3">
						<ul id="browser" class="filetree"></ul>
					</div>
					</div>
				<div class="form-group">
				<c:forEach var="localBodys" items="${localBodyTypes}">
					<input type="hidden" id="u-${localBodys.localBodyTypeCode}" value="${localBodys.lbLevel}" ></input>
				</c:forEach>
				<input  type="checkbox" id="chk" title="Check to add as Parent" checked="checked" disabled="disabled" style="display: none;"/>
				
				 <label  class="col-sm-3 control-label" for="sel1"> </label>
				 <div class="col-sm-6">
				<button type="button" id="btnBuildHrchy" onclick="buildHierarchy();" class="btn btn-primary">Add Hierarchy</button>
				
				</div>
				</div>
				<div class="box-footer">
    			 <div class="col-sm-offset-2 col-sm-10">
      				 <div class="pull-right">
      					 <button type="button" id="btnCreateDept"  onclick="return submitForm();" class="btn btn-success"><i class="fa fa-floppy-o"></i>Click to Proceed</button>
						 <button type="button" id="clear" onclick="javascript:go('startDepartmentCreatetion.htm');" class="btn btn-primary"><i class="fa fa-repeat"></i>Clear</button>
						<button type="button" id="close" onclick="javascript:go('home.htm');" class="btn btn-danger"><i class="fa fa-floppy-o"></i>Close</button>

					</div>
					</div>
				</div>
				
				<div class="modal fade modal-admin" id="alertbox" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
			  				<div class="modal-header">
			   				 
			    			  	<h4 class="modal-title" id="alertboxTitle">Alert </h4>
			  				</div>
			  				<div class="modal-body" id="alertboxbody">
			        
			  				</div>
		     			 <div class="modal-footer">
		        		 	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      			</div>
					 </div>
				</div>
			</div>
			
		</form:form>
	</div>
	      </section>
	 </div>
            	<c:choose>
					<c:when test="${isCenterUser}">
				                   <script>
									  createHirarchy('Center',0);
								   </script>
					</c:when>
					<c:otherwise>
			                    <script>
						             createHirarchy('State',1);
					            </script>
					</c:otherwise>
				</c:choose>	
			  <script src="/LGD/JavaScriptServlet"></script>
		  </div>
		</div>
</body>
</html>