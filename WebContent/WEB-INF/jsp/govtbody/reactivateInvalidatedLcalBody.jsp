<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html ng-app="reactivateLocalBody">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>


<script type="text/javascript" src="<%=contextPath%>/angularjs/angular.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/ui-bootstrap-tpls-0.12.0.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/reactivateLocalBody.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/submitGovernmentOrderForm.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/exceptionalModelController.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/toastr.min.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/jquerysctipttop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/toastr.css">

<script type="text/javascript" src="<%=contextPath%>/angularjs/pagination.js"></script>

<style type="text/css">
 input[type='text']{
	text-align: right;
} 

</style>
<script type="text/javascript">

</script>



</head>
	<body ng-controller="reactivateLocalBodyContrl" ng-init="getLocalBodyList('${operationCode}')" ng-cloak>
		<section class="content">
			<div class="row">
				<section class="col-lg-12">
					<form class="form-horizontal">
						<div class="box">
							<div class="box-header with-border">
                        	<h3 class="box-title">Reactivate Local Body</h3>
                        </div>
                        <!-- <div class="box-header subheading">
                        	<h4 class="box-title">Select Type</h4>
                        </div> -->
							<%-- <div class="box-body">
                            	<label class="radio-inline">
      								<input type="radio" name="optradio" ng-value="${operations[1].operationCode}" ng-checked="true" ng-click="getLocalBodyList(optionCode)" ng-model="optionCode"><b>${operations[1].operationName}</b>
    							</label>
                            	<label class="radio-inline">
      								<input type="radio" name="optradio" ng-value="${operations[0].operationCode}" ng-click="getLocalBodyList(optionCode)" ng-model="optionCode"><b>${operations[0].operationName}</b>
    							</label>
								<label class="radio-inline">
									<input type="radio" name="optradio" ng-value="${operations[2].operationCode}" ng-click="getLocalBodyList(optionCode)" ng-model="optionCode"><b>${operations[2].operationName}</b>
								</label>
								
							</div> --%>
							<br></br>
								<div class="form-inline" ng-cloak>
									<div class="col-sm-6 form-inline">
										<div >
											<label>Show 
												<select ng-model="selectedLength" class="form-control input-sm">
													<option ng-selected="{{size == pageSizeLength[0]}}" ng-repeat="size in pageSizeLength" >{{size}}</option>
												</select> entries
											</label>
										</div>
									</div>
									<div class="col-sm-4"></div>
									<div class="col-sm-2">
										<input style="width: 154px;" type="text" ng-model="searchElement" class="form-control" placeholder="Search"></input>
									</div>
								</div>
								
		  						<div class="box-body" ng-if="invalidateLocalBody.length>0">
									<table style="width: 100%;" class="data_grid" id="invalidatedLocalBodyTable">
										<thead style="background-color: #f1f1f1">
											<tr>
											<th style="text-align: center;" ng-click="sort('sNo')">Sr.No
												<span class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
											</th>
											<th style="text-align: center;" ng-click="sort('localBodyName')">Local Body Name
												<span class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
											</th>
											<th style="text-align: center;" ng-click="sort('localBodyCode')">Local Body Code
												<span class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
											</th>
											<th style="text-align: center;" ng-click="sort('description')">Description
												<span class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
											</th>
											<th style="text-align: center;" ng-click="sort('effectiveDate')">Effective Date
												<span class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
											</th>
											<th style="text-align: center;">Action</th>
											</tr>
										</thead>
										<tbody>
											<tr dir-paginate="localBody in invalidateLocalBody|filter:searchElement|itemsPerPage:selectedLength|orderBy:sortKey:reverse">
											<td style="text-align: center;">{{setSerialNumber+($index)}}</td>
											<td style="text-align: center;">{{localBody.localBodyName}}</td>
											<td style="text-align: center;">{{localBody.localBodyCode}}</td>
											<td style="text-align: center;">{{localBody.description}}</td>
											<td style="text-align: center;width: 137px;">{{localBody.effectiveDate|date:"dd-MM-yyyy"}}</td>
											<td style="text-align: center;"><button ng-disabled="disable" type="button" ng-click="validateReactivation(localBody)" class="btn btn-success" id="btnReactivate"><i class="fa fa-floppy-o"></i>Reactivate</button></td>
											</tr>
										</tbody>
									
									</table>
								</div>
								<div style="color: red;" class="box-body" ng-if="invalidateLocalBody.length<=0">
									<label>No records found</label>
								</div>
								
								<div align="right" class="form-group">
									<div class="col-sm-12">
									<dir-pagination-controls
										max-size="10"
										direction-links="true"
										boundary-links="true" 
										on-page-change="setPageNumber(newPageNumber, oldPageNumber)">
									</dir-pagination-controls>
									</div>
								</div>
							
						</div>
					</form>
				</section>
			</div>
		</section>
	</body>
</html>