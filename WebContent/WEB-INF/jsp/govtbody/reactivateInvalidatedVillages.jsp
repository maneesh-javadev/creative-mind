<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html ng-app="reactivateVillage">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>

<script type="text/javascript" src="<%=contextPath%>/angularjs/angular.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/ui-bootstrap-tpls-0.12.0.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/reactivateVillage.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/toastr.min.js"></script>

<script type="text/javascript" src="<%=contextPath%>/angularjs/pagination.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/jquerysctipttop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/toastr.css">




<style type="text/css">
 input[type='text']{
	text-align: right;
} 

</style>
<script type="text/javascript">
var stateCode = '${stateCode}'
</script>



</head>
	<body ng-controller="reactivateVillageController" ng-cloak>
		<section class="content">
			<div class="row">
				<section class="col-lg-12">
					<form ng-init="fetchDistricts()" class="form-horizontal">
						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">Reactivate Villages</h3>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Select district</label>
									<div class="col-sm-6">
										<select class="form-control" ng-disabled="disableDropdown" ng-model="selectedDistrict" ng-change="getInvalidatedVillages()">
											<option ng-show="{{districts.length==1}}" value="">Select</option>
											<option ng-repeat="district in districts" ng-value="{{district.dlc}}" value="{{district.dlc}}" ng-selected="{{districts.length==1}}" >
												{{district.districtNameEnglish}}
											</option>
										</select>
									</div>
							</div>
							
							<div ng-show="showPaginationDiv" class="form-inline" ng-cloak>
								<div class="col-sm-6 form-inline">
									<div >
										<label>Show 
											<select ng-model="selectedLength" class="form-control input-sm">
												<option ng-repeat="size in pageSizeLength" >{{size}}</option>
											</select> entries
										</label>
									</div>
								</div>
								<div class="col-sm-4"></div>
								<div class="col-sm-2">
									<input style="width: 154px;" type="text" ng-model="searchElement" class="form-control" placeholder="Search"></input>
								</div>
							</div>
							
							<div class="box-body">
								<table style="width: 100%;" class="data_grid" id="invalidatedLocalBodyTable" ng-if="invalidatedVillages.length>0" ng-cloak>
										<thead style="background-color: #f1f1f1">
											<tr>
											<th style="text-align: center;" ng-click="sort('sNo')">Sr.No
												<span class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
											</th>
											<th style="text-align: center;" ng-click="sort('villageCode')">Village Code
												<span class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
											</th>
											<th style="text-align: center;" ng-click="sort('villageName')">Village Name
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
											<tr dir-paginate="village in invalidatedVillages|filter:searchElement|itemsPerPage:selectedLength|orderBy:sortKey:reverse">
											<td style="text-align: center;">{{setSerialNumber+($index)}}</td>
											<td style="text-align: center;">{{village.villageCode}}</td>
											<td style="text-align: center;">{{village.villageName}}</td>
											<td style="text-align: center;">{{village.description}}</td>
											<td style="text-align: center;width: 137px;">{{village.effectiveDate|date:"dd-MM-yyyy"}}</td>
											<td style="text-align: center;"><button ng-disabled="disableClick" type="button" ng-click="revalidateInvalidatedVillage(village)" class="btn btn-success" id="btnReactivate"><i class="fa fa-floppy-o"></i>Reactivate</button></td>
											</tr>
										</tbody>
									
									</table>
							</div>
							<div style="color: red;" class="box-body" ng-if="invalidatedVillages.length<=0">
								<label>No records found</label>
							</div>
							
							<div ng-if="invalidatedVillages.length>0" align="right" class="form-group">
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