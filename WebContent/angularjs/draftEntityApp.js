var renameEntity = angular.module('draftEntity', [  'ui.grid', 'ui.grid.pagination','ui.bootstrap']);


renameEntity.controller('draftEntityCtrl', function($scope, $http,$compile,$modal) {
	
	  $scope.data = [];
	  $scope.draftFlag = true;
	  $http.get('draftEntityList.htm?<csrf:token uri=draftEntityList.htm />').success(function(data) {
		$scope.data = data;
		
	    })
	    .error(function(error,status){
	    	alert(status);
	  });
	  
	
	   $scope.columnDefs = [
	     {field: 'adminEntityNameEnglish',displayName: 'Admin Entity Name English'},
	     {field: 'adminUnitEntityCode'},
	     {field: 'adminUnitLevelCode'},
	     { field: '_actions_',
	          displayName: 'Actions',
	          cellTemplate: '' +
	'<div class="ui-grid-cell-contents actions-column">' +
	' <button ng-click="grid.appScope.openModal(row.entity, $event)">Test</button>' +
	'</div>'
	        }
	    ];
	   
	   $scope.openModal = function(row, $ev){
		   //row.isPublishOrDraft = 'P';
		   $http.post('fetchGovOrderDetails.htm?<csrf:token uri=fetchGovOrderDetails.htm />', row)
			 .then(
		         function(response){
		         // success callback
		        	 console.log(response.data);
		        	 $scope.GovOrder=response.data;
		        	 $scope.GovOrder.effectiveDate = new Date($scope.GovOrder.effectiveDate1);
		        	 $scope.GovOrder.orderDate = new Date($scope.GovOrder.orderDate1);
		        	 $scope.GovOrder.gazPubDate = new Date($scope.GovOrder.gazPubDate1);
		        	 var modalInstance = $modal.open({
		                 templateUrl: 'angularjs/renameEntity.html', 
		                 controller: 'renameEntityController',
		                 windowClass: 'app-modal-window',
		                 resolve: {
		                 	entity: function () {
		                         return row;
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
		        	 
		        	 
		        	 
		        	 
		                }, 
		                function(response){
		         console.log(response);
		                }
		          );
		   
	  
	   }
	   
	   $scope.gridOptions = { 
			     columnDefs: $scope.columnDefs, 
			     data: 'data',
			     paginationPageSizes: [10, 15,20],
			     paginationPageSize: 10,
			     useExternalPagination: false
			   };
	  

		 
});
	      


