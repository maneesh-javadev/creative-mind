<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="buildtreeJs.jsp"%>

<script src="resource-tree/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/jquerysctipttop.css">

</head>

<body>
<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="${formTitle}" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->
		      
		      
		<c:choose>
			<c:when  test="${!empty deptList }">
				<form:form commandName="extendDpet" action="buildTree.htm" method="POST" id="deptform" class="form-horizontal">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rebuildSetupAdminDepartment.htm"/>" />
				<br/><br/>
				
				<div class="form-group">
					<label  class="col-sm-3 control-label" for="deptId"><spring:message htmlEscape="true" code="${selectCreteria}" /> <span class="mandatory">*</span></label>
				  	<div class="col-sm-6">
				  		<form:select path="olc" class="form-control" id="deptId">
								<form:option selected="selected" value="" label="select" />
								<form:options items="${deptList}" itemValue="olc" itemLabel="orgName"  />
						</form:select>
				  	</div>
				</div>
				
				 <div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                       		<button type="button" class="btn btn-success " onclick="getDeptSetup();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.GETDATA"/></button>
                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
                        </div>
                     </div>   
                   </div>
				</form:form>
				
			</c:when>
		
			<c:otherwise>
				<form:form id="adminOrgDeptForm" commandName="buildTree">
				<form:hidden path="hierarchyIds" />
				<form:hidden path="olc" />   
				
				<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
				  			<div class="modal-dialog" role="document">
				    			<div class="modal-content">
				      				<div class="modal-header">
				       				 	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        			  	<h4 class="modal-title" id="exampleModalLabel">Unit Levels</h4>
				      				</div>
				      				<div class="modal-body">
								        <form>
								          <div class="form-group">
								            <label for="recipient-name" class="control-label">Unit Levels:</label>
								           	<select id="childUnitLevel" >
								           		<option value="">Select</option>
												<optgroup id="landRegionUnits" label="Land Regions Unit Levels"></optgroup>
												<optgroup id="localBody" label="Local Body"></optgroup> 
												<optgroup id="adminUnits" label="Administrative Unit Levels"></optgroup> 
											</select>
											<span class="errormessage" id="errchildUnitLevel"></span>
								          	</div>
								        </form>
				      				</div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								        <button type="button" id="btnExtendHierarchy" class="btn btn-primary">Extend Hierarchy</button>
								      </div>
				    				</div>
				  				</div>
					</div>
					
					<div class="form-group">
						<div id="base" class="tree dept_tre_dashboard">
						</div>
						<script>
							buildTree();
						</script>
						
						<c:forEach items="${existDeptHierarchy}" var="obj">
						<script>
							createChildNode('${obj.parentId}','${obj.childId}','${obj.childName}',true,'${obj.parentCode}');
						</script>
						</c:forEach>
					</div>
				
				
					 <div class="box-footer">
	                     <div class="col-sm-offset-2 col-sm-10">
	                       <div class="pull-right">
	                      		<button type="button" class="btn btn-success " id="btnExtendDept"><i class="fa fa-floppy-o"></i> <c:out value='Click to Proceed' /></button>
	                            <button type="button" class="btn btn-danger " id="btnClose" ><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
	                        </div>
	                     </div>   
                   </div>
                   
                   <div class="modal fade " id="showOffice" tabindex="-1" role="dialog" >
			  			<div class="modal-dialog" >
			    			<div class="modal-content">
			      				<div class="modal-header">
			       				 
			        			  	<h4 class="modal-title" id="exampleModalLabel"><span id="offietitle"></span> </h4>
			      				</div>
			      				<div class="modal-body">
							        <form>
							          <div class="form-group_new">
							              <div id="officeTree" class="tree" style="width: 100%;">
							              
							          	</div>
							        </form>
			      				</div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							      
							      </div>
			    				</div>
			  				</div>
					</div>
					
					<div class="modal fade" id="alertbox" tabindex="-1" role="dialog" >
						<div class="modal-dialog" >
								<div class="modal-content">
					  				<div class="modal-header">
					   				 
					    			  	<h4 class="modal-title" id="alertboxTitle">Extend Department Setup</h4>
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
			</c:otherwise> 
		</c:choose>
		</div>
	</section>
  </div>
</section> 
</body>

<script src="js/MultiNestedList.js"></script>
<link rel="stylesheet" href="css/myStyleCss.css">

<style >

.anchortag{
margin: 15px;
font-size:12px;

}
.headertd{
margin:25px 0px 2px 0pxpx;
	font-size:14px;
	color:black;
}
 .myButton {
	-moz-box-shadow: 3px 4px 0px 0px #899599;
	-webkit-box-shadow: 3px 4px 0px 0px #899599;
	box-shadow: 3px 4px 0px 0px #899599;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ededed), color-stop(1, #bab1ba));
	background:-moz-linear-gradient(top, #ededed 5%, #bab1ba 100%);
	background:-webkit-linear-gradient(top, #ededed 5%, #bab1ba 100%);
	background:-o-linear-gradient(top, #ededed 5%, #bab1ba 100%);
	background:-ms-linear-gradient(top, #ededed 5%, #bab1ba 100%);
	background:linear-gradient(to bottom, #ededed 5%, #bab1ba 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ededed', endColorstr='#bab1ba',GradientType=0);
	background-color:#ededed;
	-moz-border-radius:15px;
	-webkit-border-radius:15px;
	border-radius:15px;
	border:1px solid #d6bcd6;
	display:inline-block;
	cursor:pointer;
	color:black;
	font-family:Arial;
	font-size:15px;
	padding:7px 25px;
	text-decoration:none;
	text-shadow:0px 1px 0px #e1e2ed;
}
.myButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #bab1ba), color-stop(1, #ededed));
	background:-moz-linear-gradient(top, #bab1ba 5%, #ededed 100%);
	background:-webkit-linear-gradient(top, #bab1ba 5%, #ededed 100%);
	background:-o-linear-gradient(top, #bab1ba 5%, #ededed 100%);
	background:-ms-linear-gradient(top, #bab1ba 5%, #ededed 100%);
	background:linear-gradient(to bottom, #bab1ba 5%, #ededed 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#bab1ba', endColorstr='#ededed',GradientType=0);
	background-color:#bab1ba;
}
.myButton:active {
	position:relative;
	top:1px;
} 

    
    #actionDiv {
	background-color: rgba(255, 255, 255, 0.5);
	width: 90%;
	height: 90%;
	position: fixed;
	top: 0%;
	left: 10%;
}

#nestActionDiv {
	background-color: rgba(255, 255, 255, 255);
	width: 250px;
	height: 400px;
	border-width: 2px;
	border-color: gray;
	border-style: solid;
	-webkit-border-radius: 50px;
	position: fixed;
	top: 30%;
	left: 70%;
}

#close {
	width: 55px;
	height: 30px;
}

#close:hover {
	width: 55px;
	height: 35px;
}
  
  </style>
  

</html>