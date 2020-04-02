app.controller('textOrReplyController',function($scope,$http,$modalInstance,textData){  
	
	$scope.reportedTextContent=textData.reportedIssueText;
	$scope.replyReportedContent=textData.replyText;
	
	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
	
});




