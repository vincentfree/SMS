app.controller("contactsCtrl", function($scope, contactsService, $http){
	
	/**
	 * requesting server for all the contacts in the contact list.
	 */
	$http.post("getAllContacts").then(function(contacts){
		$scope.contacts = contacts.data;
	});
	
	/**
	 * when user selects one contact, this function triggers,
	 * uses contactsService to communicate between controllers.
	 */
	$scope.pass = function(index){
		contactsService.addContact($scope.contacts[index]);
	}
	
});

/**
 * to communicate between contactsCtrl and contactsInfoCtrl, this service is used.
 * it uses browser session storage so that if accidently, user refreshes or even intentionally
 * data is retained.
 */
app.service("contactsService", function(){
	
	var addContact = function(contactObject){
		sessionStorage.setItem("contact", JSON.stringify(contactObject));
	}
	
	var getContact = function(){
		return JSON.parse(sessionStorage.getItem("contact"));
	}
	
	var flushContact = function(){
		sessionStorage.clear();
	}
	
	return {
		addContact			: 	addContact,
		getContact			: 	getContact,
		flush				: 	flushContact
	}
	
});