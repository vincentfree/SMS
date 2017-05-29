var app = angular.module("sms", ['ui.router']);

app.config(function($stateProvider, $urlRouterProvider, $httpProvider){

	/**
	 * ui.router is similar to ngRoute, advantage of using ui.router is handling complex heirarchy of templates
	 */
	
	$stateProvider
	.state('home', {
		url: "/home",
		views: {
			'': {
				templateUrl: "home.html",
				controller: "homeCtrl",
			}
		}
	})
	.state('home.contacts', {
		url: "/contacts",
		views: {
			'default@home': {
				templateUrl: "contacts.html",
				controller: "contactsCtrl",
			}
		}
	})
	.state('home.sentMessages', {
		url: "/sentMessages",
		views: {
			'default@home': {
				templateUrl: "sentMessages.html",
				controller: "sentMessagesCtrl",
			}
		}
	})
	.state('home.feedback', {
		url: "/feedback",
		views: {
			'default@home': {
				templateUrl: "feedback.html",
				controller: "feedbackCtrl",
			}
		}
	})
	.state('home.contactInfo', {
		url: "/contactInfo",
		views: {
			'default@home': {
				templateUrl: "contactInfo.html",
				controller: "contactInfoCtrl",
			}
		}
	})
	$urlRouterProvider.otherwise("/home");

});