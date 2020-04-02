var fileExistFlag=false;
var app=angular.module('otherServicesForm',['ng-ip-address','ui.bootstrap','ngMaterial']);

app.controller('consumeServiceController',function($scope,$http,$modal){
	
	$scope.sucessFlag=false;
	$scope.errorFlag=false;
	$scope.btnDisabled=true;
	$scope.requestWeb=true;
	$scope.reportingIssue=false;
	$scope.subscribeNotificationFlag=false;
	$scope.defaultvalue=0;
	 $scope.fileExist=false;
	
	$scope.webConsume=true;
	$scope.ipAddressList = [{'ipAddress':'','nonIpEdit':false}];
	$scope.applicationNameList = [{'applicationName':'','nonEdit':false}];
	
	$scope.removeAppLength=1;
	$scope.removeIpLength=1;
	
	 $scope.addNew = function(personalDetail){
		 $scope.ipAddressList.push({'ipAddress': '','nonIpEdit':false});
	 };
  
     $scope.addNewApp = function(personalDetail){
      $scope.applicationNameList.push({'applicationName': '','nonEdit':false});
     };
     
     $scope.removeAppRow = function(personalDetail){
    	var appNameSize = $scope.applicationNameList.length;
    	if(appNameSize > 1){
    		$scope.applicationNameList.pop($scope.applicationNameList.length)
    	}
     };
     
     $scope.removeIpRow = function(personalDetail){
     	var ipRowSize = $scope.ipAddressList.length;
     	if(ipRowSize > 1){
     		$scope.ipAddressList.pop($scope.ipAddressList.length)
     	}
      };
     
     $scope.showReportingIssue=function(){
    	 $scope.requestWeb=false;
    	 $scope.reportingIssue=true;
    	 $scope.subscribeNotificationFlag=false;
     }
     
     $scope.showRequestWeb=function(){
    	 $scope.requestWeb=true;
    	 $scope.reportingIssue=false;
    	 $scope.subscribeNotificationFlag=false;
     }
     
     $scope.subscribeNotification=function(){
    	 $scope.requestWeb=false;
    	 $scope.reportingIssue=false;
    	 $scope.subscribeNotificationFlag=true;
     }
     
     
     $http.get('listOfReportingIssue.do?<csrf:token uri=listOfReportingIssue.do />').success(function(data) {
		 $scope.reportingIssueList=data.reportingIssues;
	    })
	    .error(function(error,status){
	    	alert(status);
	  });
     
     $http.get('getRequestedWebServices.do?<csrf:token uri=getRequestedWebServices.do />').success(function(data) {
    	
    	 $('#mobileAppCheck').prop('checked', data.mobileApp);
    	 if(data.applicationName!=null && data.applicationName.length!=0){
    		 $scope.applicationNameList = [];
    		 angular.forEach(data.applicationName, function (applicationName) {
        		 $scope.applicationNameList.push({'applicationName': applicationName,'nonEdit':true});
        		 
        		 
             });
    		 $scope.removeAppLength=data.applicationName.length;
    	 }
    	 if(data.ipAddressList!=null && data.ipAddressList.length!=0){
    		 $scope.ipAddressList=[];
    		 angular.forEach(data.ipAddressList, function (ipAddressList) {
        		 $scope.ipAddressList.push({'ipAddress': ipAddressList,'nonIpEdit':true});
             });
    		 
    		 $scope.removeIpLength=data.ipAddressList.length;
    	 }
    	 if(data.fileName!=null && data.fileName.length!=0){
    		 $scope.fileName=data.fileName;
    		$scope.fileExist=true;
    		fileExistFlag=true;
    		
    	 }
    	 $scope.btnDisabled=false;
    	 
	    })
	    .error(function(error,status){
	    	alert(status);
	  });
     
     
    
     
     
     
     $scope.requestConsumeWebService=function(){
    	 $scope.btnDisabled=true;
    	 var file = $('#uploadFile').prop('files');
    	 if(file.length>0 || $scope.fileExist){
        	 $scope.applicationNameList = $scope.applicationNameList.filter(function (applicationName) {
     		    return (applicationName.nonEdit == false);
     		});
     	 $scope.ipAddressList = $scope.ipAddressList.filter(function (ipAddress) {
  		    return (ipAddress.nonIpEdit == false);
  		});
     	 var formdata = new FormData();
     	if(!$scope.fileExist){
     		formdata.append('file', file[0]);
     	}
     	formdata.append('applicationNameList',angular.toJson($scope.applicationNameList,true));
  		formdata.append('ipAddressList',angular.toJson($scope.ipAddressList,true));
  		formdata.append('mobileAppCheck',$('#mobileAppCheck').prop('checked'));
     	 $http.post('requestWebServices.do?<csrf:token uri=requestWebServices.do/>', formdata, {
     		   headers:{'Content-Type': undefined},
     	 		transformRequest: angular.identity
     		}).then(
 				  function(response){
 					  alert("Data has been saved sucessfully");
 					 $scope.btnDisabled=false;
 					  /*$scope.ipAddressList = [{'ipAddress':'','nonIpEdit':false}];
 						$scope.applicationNameList = [{'applicationName':'','nonEdit':false}];*/
 					  
 					  
 					  $http.get('getRequestedWebServices.do?<csrf:token uri=getRequestedWebServices.do />').success(function(data) {
 					    	 $('#mobileAppCheck').prop('checked', data.mobileApp);
 					    	 if(data.applicationName.length!=0){
 					    		 $scope.applicationNameList = [];
 					    		 angular.forEach(data.applicationName, function (applicationName) {
 					        		 $scope.applicationNameList.push({'applicationName': applicationName,'nonEdit':true});
 					        		 
 					        		 
 					             });
 					    		 $scope.removeAppLength=data.applicationName.length;
 					    	 }
 					    	 if(data.ipAddressList.length!=0){
 					    		 $scope.ipAddressList=[];
 					    		 angular.forEach(data.ipAddressList, function (ipAddressList) {
 					        		 $scope.ipAddressList.push({'ipAddress': ipAddressList,'nonIpEdit':true});
 					             });
 					    		 
 					    		 $scope.removeIpLength=data.ipAddressList.length;
 					    	 }
 					    	 
 						    })
 						    .error(function(error,status){
 						    	alert(status);
 						  });
 					  
 					  
 					  
 						$('#uploadFile').val('')
 	   		          }, 
 	   		      function(response){
 	   		         console.log("failed");
 	   		         $scope.btnDisabled=false;
 	   		         });
    	 }else{
    		 $scope.btnDisabled=false;
    	 }

     	};
     
     
     $scope.createNewReport = function(){
	   var modalInstance = $modal.open({
         templateUrl: 'angularjs/createReportingForm.html', 
         controller: 'reportingFormController',
         windowClass: 'app-modal-window',
         backdrop: 'static',
         resolve: {
        	 reportingList: function () {
                 return $scope.reportingIssueList;
             }
        	 
         }
	   });
	   
	   modalInstance.result.then(function (entity) {
		   $http.get('listOfReportingIssue.do?<csrf:token uri=listOfReportingIssue.do />').success(function(data) {
				 $scope.reportingIssueList=data.reportingIssues;
			    })
			    .error(function(error,status){
			    	alert(status);
			  });
		  /* $scope.reportingIssueList=entity;*/
		    }, function () {
		      $log.info('Modal dismissed at: ' + new Date());
		    });
	   
	 }
     
     $scope.showReportedText = function(index){
    	 
    	 angular.forEach($scope.reportingIssueList, function(tp,i) {
    		 if(i==index){
    			 $scope.selectedText=tp;
    		 }
    	 });
    	 
  	   var modalInstance = $modal.open({
           templateUrl: 'angularjs/viewReportedIssueText.html', 
           controller: 'textOrReplyController',
           windowClass: 'app-modal-window',
           resolve: {
        	   textData: function () {
                   return $scope.selectedText;
               }
           }
  	   });
  	 }
     
     
     /*subscribe email/sms notification*/
     $scope.emailNotification={};
     
     
     
     $http.get('getStateList.do?<csrf:token uri=getStateList.do/>').success(function(data) {
 		$scope.statelist = data;
 	})
 	.error(function(error,status){
 		alert(status);
 	});
     
     $scope.items = ['District','Sub-District','Village'];
     
     $scope.itemsLocalBody = ['Panchyat','Urban Local Body','Traditional Local Body'];
     
     $scope.selected = [];
     
     $scope.selectedLocalBody = [];
     
     $scope.toggle = function (item, list) {
       var idx = list.indexOf(item);
       if (idx > -1) {
         list.splice(idx, 1);
       }
       else {
         list.push(item);
       }
     };

     $scope.exists = function (item, list) {
       return list.indexOf(item) > -1;
     };

     $scope.isIndeterminate = function() {
       return ($scope.selected.length !== 0 &&
           $scope.selected.length !== $scope.items.length);
     };
     
     $scope.isIndeterminateLocal = function() {
         return ($scope.selectedLocalBody.length !== 0 &&
             $scope.selectedLocalBody.length !== $scope.itemsLocalBody.length);
       };
     

     $scope.isChecked = function() {
       return $scope.selected.length === $scope.items.length;
     };
     
     $scope.isCheckedLocalBody = function() {
         return $scope.selectedLocalBody.length === $scope.itemsLocalBody.length;
       };

     $scope.toggleAll = function() {
       if ($scope.selected.length === $scope.items.length) {
         $scope.selected = [];
       } else if ($scope.selected.length === 0 || $scope.selected.length > 0) {
         $scope.selected = $scope.items.slice(0);
       }
       
     };
     
     
     $scope.toggleAllLocal = function() {
         
         if ($scope.selectedLocalBody.length === $scope.itemsLocalBody.length) {
             $scope.selectedLocalBody = [];
           }  else if ($scope.selectedLocalBody.length === 0 || $scope.selectedLocalBody.length > 0) {
             $scope.selectedLocalBody = $scope.itemsLocalBody.slice(0);
           }
       };
     
     $scope.saveNotificationDetails=function(){
    	 $scope.emailNotification.district=false;
    	 $scope.emailNotification.subdistrict=false;
    	 $scope.emailNotification.village=false;
    	 
    	 angular.forEach($scope.selected, function (value, key) {
             if(value=='District'){
            	 $scope.emailNotification.district=true;
             }
             if(value=='Sub-District'){
            	 $scope.emailNotification.subdistrict=true;
             }
             if(value=='Village'){
            	 $scope.emailNotification.village=true;
             }
             if(value=='Panchyat'){
            	 $scope.emailNotification.ruralbody=true;
             }
             if(value=='Urban Local Body'){
            	 $scope.emailNotification.urbanbody=true;
             }
             if(value=='Traditional Local Body'){
            	 $scope.emailNotification.tradionbody=true;
             }
             
          });
    	 
    	 angular.forEach($scope.selectedLocalBody, function (value, key) {
             if(value=='Panchyat'){
            	 $scope.emailNotification.ruralbody=true;
             }
             if(value=='Urban Local Body'){
            	 $scope.emailNotification.urbanbody=true;
             }
             if(value=='Traditional Local Body'){
            	 $scope.emailNotification.tradionbody=true;
             }
             
          });
    	
    	 $http.post('saveSubscriptionDetails.do?<csrf:token uri=saveSubscriptionDetails.do/>', $scope.emailNotification).then(
				 function(response){
					 toastr.success('data has been saved successfully');
   		          }, 
	   		      function(response){
   		        	  alert(response.status)
   		         }
	      	 );
     }
     
     $http.get('getSubscriberUser.do?<csrf:token uri=getSubscriberUser.do/>').success(function(data) {
    	 
  		if(data!=null && data!=""){
  			$scope.emailNotification.block=data.block;
  			/*$scope.emailNotification.tradionbody=data.tradionbody;
  			$scope.emailNotification.urbanbody=data.urbanbody;
  			$scope.emailNotification.ruralbody=data.ruralbody*/
  			$scope.emailNotification.slc=data.slc;
  			$scope.defaultvalue=data.slc;
  			if(data.district){
  				$scope.selected.push('District');
            }
            if(data.subdistrict){
           	 $scope.emailNotification.subdistrict=true;
           	$scope.selected.push('Sub-District');
            }
            if(data.village){
            	$scope.selected.push('Village');
            }
            
            if(data.ruralbody){
  				$scope.selectedLocalBody.push('Panchyat');
            }
            if(data.urbanbody){
           	$scope.selectedLocalBody.push('Urban Local Body');
            }
            if(data.tradionbody){
            	$scope.selectedLocalBody.push('Traditional Local Body');
            }
            
  		}
  	})
  	.error(function(error,status){
  		alert(status);
  	});
     
     
     $scope.downloadExistFile=function(){
    	  $http.get('uploadUserFile.do?<csrf:token uri=uploadUserFile.do/>',{params: {"fileName":$scope.fileName}}).success(function(data) {
    		  
    	  });
    	  return true;
     }
     
     $scope.removeFileFlag=function(){
    		if($scope.fileExist){
    			$scope.fileExist=false;
    			fileExistFlag=false;
    			
    		}
     }
     
});


function validateEmptyFile(){
	if(fileExistFlag){
		alert("Please Delete Earlier Attached File First");
		return false;
	}else{
		return true;
	}
	
	
}
