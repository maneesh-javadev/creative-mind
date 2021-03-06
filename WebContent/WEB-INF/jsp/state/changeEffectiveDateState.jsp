<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="publicModule">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../common/taglib_includes.jsp"%>
<title><spring:message code="Label.CHANGE.EFFECTIVE.DATE.STATE" htmlEscape="true"></spring:message></title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/angularjs/angular-material.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-animate.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-route.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-aria.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-messages.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/angular-material.min.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/changeEffectiveDate/state/changeEffectiveDateController.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/changeEffectiveDate/state/changeEffectiveDateChildController.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/changeEffectiveDate/state/changeEffectiveDateService.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/angularjs/ui-bootstrap-tpls-0.11.0.js"></script>

<script>
var _JS_STATE_CODE="${StateCode}";
var _JS_CURRENT_DATE='${curDate}';
callActionUrl=function(url){
 $( 'form[id=frmModifyVillage]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=frmModifyVillage]' ).attr('method','post');
	$( 'form[id=frmModifyVillage]' ).submit();
};

</script>
</head>
<body class = "datepickerdemo" ng-controller = "changeEffectiveDateController as ctrl" layout = "column" ng-cloak>
<section class="content">
	<div class="row" >
		<section class="col-lg-12">
			<div class="box">
			<div class="box-body">

       <form class="form-horizontal" id="StateForm">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyVillageChangeEffectiveDate.htm"/>"/>
		<input type="hidden" name="subdistrictCode" value="{{villageDetail.parentCode}}" />

      <div class="box-header subheading">
		    <h4>
		    	<spring:message code="Label.CHANGE.EFFECTIVE.DATE" htmlEscape="true"></spring:message> of
		    	<spring:message code="Label.STATE" htmlEscape="true"></spring:message>
			</h4>
			</div>
			
			
			<table class="table table-bordered table-hover" width="80%">
    			 <tbody>
			      	<tr>
			      		<td width="30%"><spring:message htmlEscape="true" code="Label.STATECODE"> : </spring:message></td>
					    <td width="50%">{{State_Detail.entityCode}}</td>
				   </tr>
					
					<%--  <tr>
				        <td><spring:message htmlEscape="true" code="Label.VILLAGEVERSION"> : </spring:message></td>
				        <td>{{villageDetail.entityVersion}}</td>
				     </tr> --%>
				      <tr>
				        <td><spring:message htmlEscape="true" code="Label.STATENAMEINENGLISH"> : </spring:message></td>
				        <td>{{State_Detail.entityNameEnglish}}</td>
				      </tr>
				      <tr>
				        <td><spring:message htmlEscape="true" code="Label.STATENAMEINLOCAL"> : </spring:message></td>
				        <td>{{State_Detail.entityNameLocal}}</td>
				      </tr>
				      
			 </tbody>
		</table>
			
			
			
			<table><tr>
		<th><font color="red">*</font> Note : New effective date must be in assending order</th>
		</tr>
		
		</table>
		<table class="table table-bordered table-hover" width="80%">
    			 <thead>
    			 <tr>
	    			   <th width="5%" ><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
					   <th width="10%" ><spring:message htmlEscape="true" code="Label.STATECODE"></spring:message></th>
					   <th  width="20%"><spring:message htmlEscape="true" code="Label.STATENAMEINENGLISH"></spring:message></th>
					   <th width="5%">State  Version</th>
					   <th width="5%">State Minor Version</th>
					   <th width="5%">View Minor Version</th>
					    
					   <th width="15%">Previous Effective Date</th>   
					   <th width="15%">New Effective Date</th> 
    			 </tr>
			</thead>
			 <tbody>
			
			 <tr ng-repeat="obj in entityList | orderBy : 'obj.entityVersion'" >
			 <td>{{$index+1}}</td>
			 <td>{{obj.entityCode}}</td>
			 <td>{{obj.entityNameEnglish}}</td>
			 <td>{{obj.entityVersion}}</td>
			 <td>{{obj.entityMinorVersion}}</td>
			  <td><a href="#" ng-click="showMinorVersion(obj.entityNameEnglish,obj.entityCode,obj.entityVersion)">
			  <i class="fa fa-laptop fa-2x" aria-hidden="true"></i></a> </td>
			 
			 <td><md-datepicker 
                data-ng-model = "entityList[$index].effectiveDate" ng-disabled="true"
               md-placeholder = "Enter date"></md-datepicker> </td>
			 <td>  <md-datepicker 
               data-ng-model = "entityList[$index].newEffectiveDate"
               md-placeholder = "Enter date" md-max-date="myMaxDate"></md-datepicker> 
              </td>
	         </tr>
			</tbody>
			 
		</table>
		<div ng-show="errorbox" style="height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;" align="center">
				<label id="errorCommon">{{errormsg}}</label>
		</div>
		<div ng-show="msgbox" style="height: 40px; border: 1px solid green; background-color: #e5fff2; padding-top: 10px;" align="center">
				<label id="errorCommon">{{message}}</label>
		</div>
		
		<div class="box-footer">
	   <div class="col-sm-offset-2 col-sm-10">
		  <div class="pull-right">		
		 <button  type="button"  class="btn btn-success" ng-disabled="isDisabled" ng-click="saveEffectiveDate()"><i class="fa fa-floppy-o"></i>  <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>									
		<button style="width: 80px;" type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i>  <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
	   </div></div></div>
	
		</form>
		 <script src="/LGD/JavaScriptServlet"></script>
			
		
</div>
</div>
</section>
</div>
</section>

</body>
</html>