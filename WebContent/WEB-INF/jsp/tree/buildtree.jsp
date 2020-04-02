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
<div id="frmcontent">
  	<div class="frmhd">
		<h3 class="subtitle">
			<spring:message htmlEscape="true" code='${formTitle}'></spring:message>
		</h3>
  	</div>
	<div class="clear"></div>
	
	<c:choose>
	<c:when  test="${!empty deptList }">
		<form:form commandName="extendDpet" action="buildTree.htm" method="POST" id="deptform">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rebuildSetupAdminDepartment.htm"/>" />
		<div class="frmpnlbg" id='showbystatelinelevel'>
			<div class="frmtxt">
				<div>
					<ul class="blocklist">
						<li><label for="deptId"><spring:message htmlEscape="true" code="${selectCreteria}" /></label><span class="errormsg">*</span>
						 	<form:select path="olc" class="combofield" id="deptId">
								<form:option selected="selected" value="" label="select" />
								<form:options items="${deptList}" itemValue="olc" itemLabel="orgName"  />
							</form:select>
							</li>
					</ul>

				</div>
			</div>
		</div>
		<div class="btnpnl">
			<input type="button" id="btnGet" value="<spring:message htmlEscape="true"  code="Button.GET"/>" onclick="getDeptSetup();" /> 
			<input class="bttn" id="btnActionClose" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="callActionUrl('home.htm')"/>
		</div>
					
		</form:form>
	</c:when>
	<c:otherwise>
		<form:form id="adminOrgDeptForm" commandName="buildTree">
		<form:hidden path="hierarchyIds" />
		<form:hidden path="olc" />
		<div class="tree_frmpnlbg" style="background: #FFFFFF;">
			<div class="tree_frmtxt">
				<div class="container1">
					<div class="inner_left">
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
						
					</div>
					<div class="inner_right">
						<div id="base" class="tree" style="width: 100%;">
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
					<div class="clear"></div>
				</div>
				<br />
			</div>
		</div>
		<div class="btnpnl">
			<button type="button" id="btnExtendDept" ><c:out value='Click to Proceed' /></button>
			<button type="button" id="btnClose" ><spring:message htmlEscape="true" code="Button.CLOSE"/></button>
			
		</div>
		<div class="modal fade modal-admin" id="showOffice" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
				  			<div class="modal-dialog" role="document">
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
		</form:form>
	</c:otherwise>
	</c:choose>
	
</div>
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
<style>
    
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
  
</style>
</html>