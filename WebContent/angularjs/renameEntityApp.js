var renameEntity = angular.module('renameEntity', [  'ui.grid', 'ui.grid.pagination','ui.bootstrap']);


renameEntity.controller('renameEntityCtrl', function($scope, $http,$compile,$modal) {
	
	
	$scope.entityList = [];
	$scope.entityFlag = false;
	$scope.fieldLabel = '';
	$scope.gridFlag = false;
	$scope.draftFlag = false;
	  $http.get('createUnitLevelList.htm?<csrf:token uri=unitLevelList.htm />').success(function(data) {
		  data.splice(0, 1);
		$scope.adminUnitList = data;
		
	    })
	    .error(function(error,status){
	    	alert(status);
	  });
	  
	  $scope.data = [];
	   $scope.columnDefs = [
	     {field: 'adminEntityNameEnglish',displayName: 'First Name'},
	     {field: 'adminUnitEntityCode'},
	     {field: 'adminUnitLevelCode'},
	     { field: '_actions_',
	          displayName: 'Actions',
	          cellTemplate: '' +
	'<div class="ui-grid-cell-contents actions-column">' +
	'<a href="#"  ng-class="{disabled: row.entity.islocked}" ng-click="grid.appScope.openModal(row.entity, $event)"><i class="fa fa-pencil"  aria-hidden="true"></i></a>'+
	'</div>'
	        }
	    ];
	   
	   $scope.openModal = function(row, $ev){
		   $scope.GovOrder = {};
		   var selectedRow = angular.copy(row);
		   selectedRow.previousEntityName = selectedRow.adminEntityNameEnglish;
	   var modalInstance = $modal.open({
           templateUrl: 'angularjs/renameEntity.html', 
           controller: 'renameEntityController',
           windowClass: 'app-modal-window',
           resolve: {
           	entity: function () {
                   return selectedRow;
               },
               GovOrder: function () {
                return $scope.GovOrder;
            },
            fieldLabel: function () {
                return $scope.fieldLabel;
            },
            draftFlag: function () {
                return $scope.draftFlag;
            }
           }
       });
	   }
	   
	   $scope.gridOptions = { 
			     columnDefs: $scope.columnDefs, 
			     data: 'data',
			     paginationPageSizes: [10, 15,20],
			     paginationPageSize: 10,
			     useExternalPagination: false
			   };
	  
	
	  
	  $scope.renameEntity = function(entityobj){
		  console.log(entityobj);
		  $scope.data = [];
		  $scope.gridFlag = false;
		  console.log($scope.data);
		  var json = $.parseJSON(entityobj);
		  var sortOrder = json.hierarchyEntrry.sortOrder;
		  $scope.sortOrder = json.hierarchyEntrry.sortOrder;
		  $scope.unitLevelCode = json.adminUnitCode;
		  console.log(json.adminUnitCode);
		  console.log($scope.unitLevelCode);
		  console.log(json);
		  if(json.hierarchyEntrry.sortOrder === 2){
			  console.log(json.hierarchyEntrry.sortOrder);
			  console.log(json.slc);
			  $http.post('annFetchEntityList.htm?<csrf:token uri=annFetchEntityList.htm />',json.slc).then(
					  function(response){
						  console.log(response.data);
						  $scope.data = response.data;
						  $scope.gridFlag = true;
		   		          }, 
		   		      function(response){
		   		         console.log("failed");
		   		         });
		  }
		  document.getElementById('dropdown').innerHTML="";
	      angular.forEach($scope.adminUnitList, function(tp,i) {
			  if(tp.hierarchyEntrry.sortOrder < sortOrder){
				  var nextTemp = null;
				  var drpdwnhtml='';
				  var temp1 = tp.adminLevelNameEng;
				  var adminUnitCode = tp.adminUnitCode;
				  console.log(sortOrder);
				  console.log(adminUnitCode);
				  if($scope.adminUnitList.length-1>i){
						 nextTemp= $scope.adminUnitList[i+1].adminLevelNameEng;
						 $scope.fieldLabel = $scope.adminUnitList[i+1].adminLevelNameEng;
					 }
				  $scope[temp1+'List'] = [];
				  $scope[nextTemp+'List'] = [];
				  drpdwnhtml =  '<div class=form-group><label class="col-sm-3 control-label">Select '+temp1+'*</label><div class=col-sm-6 ><select class=form-control ng-model="'+temp1+'dropdown" ng-change="fetchEntityList('+temp1+'List,'+temp1+'dropdown,'+nextTemp+'List)"> <option ng-repeat="st in '+temp1+'List" value="{{st}}">{{st.adminEntityNameEnglish}}</option></select></div></div>';
				  var temp2 = $compile(drpdwnhtml)($scope);
				  angular.element(document.getElementById('dropdown')).append(temp2);
				  if(i===0){
					  $http.post('fetchEntityList.htm?<csrf:token uri=fetchEntityList.htm />',adminUnitCode).then(
							  function(response){
								  $scope[temp1+'List'] = response.data.adminUnitEntities;
				   		          }, 
				   		      function(response){
				   		         console.log("failed");
				   		         });
				  }
		  
			  }
			  });
			  };
		  
			  $scope.fetchEntityList = function(currentList,adminUnit,temp1){
				  var json = $.parseJSON(adminUnit);
				  var adminUnitObj = json.adminUnitEntityCode;
				  var j = temp1.length;
				  while (j--) {
					  temp1.pop();
					}
				  $http.post('annFetchEntityList.htm?<csrf:token uri=annFetchEntityList.htm />', adminUnitObj).then(
						  function(response){
							  if(response.data[0].adminUnitLevelCode === $scope.unitLevelCode ){
								  $scope.data = response.data;
								  $scope.gridFlag = true;
							  }else{
							  var data=response.data;
							  
					        	 angular.forEach(data, function(item) {
					        		 temp1.push(item);
					        		});
					        	 console.log(temp1);
							  }
			   		          }, 
			   		      function(response){
			   		         console.log(response);
			   		         }
			   		      	 );
			  };
	 

		 
});
	      


