app.controller("sentMessagesCtrl", function($scope, $http){

	$scope.allSentMessages = [];
	
	/**
	 * getting track of all the sent messages till date.
	 */
	$http.post("getSentMessages").then(function(response){
		$scope.allSentMessages = response.data;		
		
		/**
		 * to print in reverse chronological data, we needed length in html file.
		 */
		$scope.length = $scope.allSentMessages.length;
		
	});
	
});