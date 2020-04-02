var registrationUserForm = angular.module('registrationUserForm', []);

registrationUserForm.controller('registrationUserCtrl', function($scope, $http) {
	 $scope.otp = false;
	 $scope.account = true;
	 $scope.generatedOtp=0;
	 $scope.otpSend = true;
	 $scope.userRegistration = {};
	 $scope.userLogin={};
	 $scope.errorMsg=false;
	 $scope.msg=" ";
	 $scope.centerFlag=true;
	 $scope.inputType = 'text';
	 $scope.emailFormat = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
	 
	 
	 $scope.userRegistration.orgUnitCode='';
	 
	 $scope.userRegistration.deptName='';
	 $scope.userRegistration.stateDepartment='';
	 
	 $scope.userRegistration.stateCode='';
	 
	 $http.get('getCenterList.do?<csrf:token uri=getCenterList.do />').success(function(data) {
			$scope.centerlist = data;
	    })
	    .error(function(error,status){
	    	alert(status);
	  });
	 
	 
	 $scope.generateOTP = function() {
		 if($scope.userRegistration.stateDepartment!='' || $scope.userRegistration.deptName!=''){
			 $scope.userRegistration.orgUnitCode=0;
			 $scope.userRegistration.stateCode=0;
			 $scope.userRegistration.stateOrgUnitCode=0;
		 }
		 if($scope.userRegistration.orgUnitCode!=''){
			 $scope.userRegistration.stateCode=0;
			 $scope.userRegistration.stateOrgUnitCode=0;
		 }
		 if($scope.userRegistration.stateCode!=''){
			 $scope.userRegistration.orgUnitCode=0;
		 }
		 $http.post('generateOtpRegistration.do?<csrf:token uri=generateOtpRegistration.do/>', $scope.userRegistration).then(
				 
				 function(response){
					 if (response.data.responseRedirect) {
						 $("#progressbar li:first").removeClass("active");
						 $("ul li:nth-child(2)" ).addClass("active");
						 $scope.generatedOtp=response.data.otp;
						  $scope.otp = true;
						  $scope.otpSend = false;
						  toastr.success('OTP has been sent successfully on registered mobile number and email id.');
					 }
					 else{
						 if($scope.userRegistration.stateDepartment!='' || $scope.userRegistration.deptName!=''){
							 $scope.userRegistration.orgUnitCode='';
							 $scope.userRegistration.stateCode='';
							 $scope.userRegistration.stateOrgUnitCode='';
						 }
						 if($scope.userRegistration.stateCode!=''){
							 $scope.userRegistration.orgUnitCode='';
						 }
						 if($scope.userRegistration.orgUnitCode!=''){
							 $scope.userRegistration.stateCode='';
							 $scope.userRegistration.stateOrgUnitCode='';
						 }
						 alert(response.data.msg);
					 }
				  
   		          }, 
	   		      function(response){
   		        	  alert(response.status)
   		         }
	      	 );
		};
		
		
		 $scope.reGenerateOTP = function() {
			 $http.post('regenerateOTP.do?<csrf:token uri=regenerateOTP.do/>', $scope.userLogin).then(
					 function(response){
						 if (response.data.responseRedirect) {
							 $scope.generatedOtp=response.data.otp;
							 toastr.success('OTP has been sent successfully on registered mobile number and email id');
						 }
						 else{
							 $scope.errorMsg=true;
							 $scope.msg=response.data.msg;
						 }
	   		          }, 
		   		      function(response){
	   		        	  alert(response.status)
	   		         }
		      	 );
			};
		
		
		
		$scope.showCenterList = function() {
			$scope.userRegistration.stateOrgUnitCode='';
			$scope.userRegistration.deptName='';
			$scope.userRegistration.stateDepartment='';
			$scope.stateOrgL = false;
			$scope.userRegistration.orgUnitCode='';
			$scope.centerFlag=true;
			$scope.stateFlag=false;
			/*$http.get('getCenterList.do?<csrf:token uri=getCenterList.do />').success(function(data) {
				$scope.centerlist = data;
		    })
		    .error(function(error,status){
		    	alert(status);
		  });*/
		};
		
		$scope.showStateList = function() {
			$scope.userRegistration.stateCode='';
			$scope.userRegistration.stateOrgUnitCode='';
			$scope.userRegistration.deptName='';
			$scope.userRegistration.stateDepartment='';
			$scope.stateOrgL = false;
			$scope.centerFlag=false;
			$scope.stateFlag=true;
			$http.get('getStateList.do?<csrf:token uri=getStateList.do/>').success(function(data) {
				$scope.statelist = data;
			    })
			    .error(function(error,status){
			    	alert(status);
			  });
			};
			 $scope.saveData = function() {
				 if($scope.userRegistration.stateDepartment!='' || $scope.userRegistration.deptName!=''){
					 $scope.userRegistration.orgUnitCode=0;
					 $scope.userRegistration.stateCode=0;
					 $scope.userRegistration.stateOrgUnitCode=0;
				 }
				 $http.post('saveUserRegisterData.do?<csrf:token uri=saveUserRegisterData.do/>',$scope.userRegistration).then(
						  function(response){
							  if (response.data.newRegistr) {
								  alert(response.data.msg);
					                window.location.href = "userOtherServices.do";
					            }
							  else{
								  $scope.userRegistration.orgUnitCode='';
								  $scope.userRegistration.stateCode='';
								  $scope.userRegistration.stateOrgUnitCode='';
								  $scope.errorMsg=true;
								  $scope.msg=response.data.msg;
							  }
			   		          }, 
			   		      function(response){
			   		        	  alert(response.status)
			   		         }
			   		      	 );
				 
				};
				
			$scope.getStateWiseOrgList=function(stateCode){
				$scope.stateCode=stateCode;
				$http.post('orgListBasedOnState.do?<csrf:token uri=orgListBasedOnState.do/>',$scope.stateCode).then(
					function(response){
						$scope.organizationList=response.data;
						$scope.stateOrgL = true;
					},
					function(response){
						alert(response.status);
					}
				);
			};	
			
			$scope.loginForm = function() {
				 $http.post('validateCredintial.do?<csrf:token uri=validateCredintial.do/>',$scope.userLogin).then(
						  function(response){
							  
							  if (response.data.responseRedirect) {
					                window.location.href = "userOtherServices.do";
					            }
							  else{
								  $scope.errorMsg=true;
								  $scope.msg=response.data.msg;
							  }
			   		          }, 
			   		      function(response){
			   		        	  alert(response.status)
			   		         }
			   		      	 );
				 
				};	
			
			
				$scope.checkForMaxLength = function(){
					if($scope.userLogin.mobile.match('^[0-9 ()/-]*$') == null){
						$scope.maxLength = 100;
						$scope.inputType = 'email';
					}else{
						$scope.maxLength = 10;
						$scope.inputType = 'text';
					}
					console.log($scope.maxLength);
				}
			
			
			
		 
});

registrationUserForm.directive('restrictInput', function() {
	  return {
	    restrict: 'A',
	    require: 'ngModel',
	    link: function(scope, element, attr, ctrl) {
	      ctrl.$parsers.unshift(function(viewValue) {
	        var options = scope.$eval(attr.restrictInput);
	        if (!options.regex && options.type) {
	          switch (options.type) {
	            case 'digitsOnly': options.regex = '^[0-9]*$'; break;
	            case 'lettersOnly': options.regex = /^[a-zA-Z\s]*$/; break;
	            case 'lowercaseLettersOnly': options.regex = '^[a-z]*$'; break;
	            case 'uppercaseLettersOnly': options.regex = '^[A-Z]*$'; break;
	            case 'lettersAndDigitsOnly': options.regex = '^[a-zA-Z0-9]*$'; break;
	            case 'validPhoneCharsOnly': options.regex = '^[0-9 ()/-]*$'; break;
	            default: options.regex = '';
	          }
	        }
	        var reg = new RegExp(options.regex);
	        if (reg.test(viewValue)) { //if valid view value, return it
	          return viewValue;
	        } else { //if not valid view value, use the model value (or empty string if that's also invalid)
	          var overrideValue = (reg.test(ctrl.$modelValue) ? ctrl.$modelValue : '');
	          element.val(overrideValue);
	          return overrideValue;
	        }
	      });
	    }
	  };
	});

