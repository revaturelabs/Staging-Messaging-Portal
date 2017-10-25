/**
 * This is a template
 */
angApp.controller("tmpController",function($scope, $http, $state){
	
	/**
	 * Use a function like this if you wish to change states
	 * You should also be able to pass parameters this way
	 * 
	 * */
	$scope.changeState = function(toState){
		$state.go(toState, null);
	}
});