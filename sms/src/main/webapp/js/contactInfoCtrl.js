app.controller("contactInfoCtrl", function($scope, contactsService, $state, $http){
	
	/**
	 * getting selected contact from a custom service which is defined in contactsCtrl.js.
	 */
	$scope.contact = contactsService.getContact();
	
	/**
	 * In case an undefined data comes, which it won't, still, if it comes, it will redirect to contacts.html
	 */
	if(typeof($scope.contact.id) == "undefined")
		$state.go("home.contacts");
	
	/**
	 * This function requests server for an OTP.
	 * Earlier I implemented in front-end itself, but sometimes script kiddies try to mess up things.
	 * Although it doesn't affects the work-flow, but to disappoint script-kiddies so that they get less to play.
	 */
	$scope.generateOtp = function(){
		$http.post("getOtp").then(function(otp){
			$scope.otpString = otp.data.otp;
		});
	}
	
	/**
	 * When user clicks "send" in the modal, which appears after user clicks "send message",
	 * This function gets triggered.
	 * It stores the current date-time, merges OTP string with additional text entered by user,
	 * and sends to server for further operations.
	 * Again, Could have done at back end, the entire operation. But if we consider worst case, 
	 * that say 100-500 people are using this application at LAN, pushing too many operations at server
	 * will slow it down.
	 * Moreover, user if at all tampers, can only with the message, or the number they wish to send message, or date-time.
	 */
	$scope.sendMessage = function(){
		
		/**
		 * to calculate current time stamp
		 */
		var date = new Date();
		var year = date.getFullYear(), month = date.getMonth(), day = date.getDate(), hours = date.getHours();
		var	minutes = date.getMinutes(), seconds = date.getSeconds();
		var now = new Date(year, month, day, hours, minutes, seconds);
		now = now.toString();
		
		/**
		 * art work to avoid trailing spaces in message body.
		 */
		if(typeof($scope.messageBody)=="undefined")
			$scope.messageBody = "";
		else
			$scope.messageBody = " "+$scope.messageBody;
		
		$scope.object = {};
		/**
		 * date: contains current time stamp
		 * message: contains message body which is typed by user. "" if left blank.
		 * toNumber: is the number which they selected to send sms.
		 * to: name of receiver.
		 */
		$scope.object.date = now.substr(0,24);
		$scope.object.message = $scope.messageBody;
		$scope.object.toNumber = $scope.contact.number;
		$scope.object.to = $scope.contact.firstName + " " + $scope.contact.lastName;
		
		$http.post("sendMessage", $scope.object).then(function(b){
			/**
			 * b.data is boolean type returned from controller. 
			 * sort of handling responses from server to front-end.
			 */
			if(b.data)
				alert("Message sent Successfully!");
			else
				alert("Only verified numbers can receive through trial account!");
			
		}).catch(function(){
			alert("Error! Something went wrong!\nCould be one of these\n1. Message length too long\n2. Number doesn't exists");
		});
		
	}

	
});