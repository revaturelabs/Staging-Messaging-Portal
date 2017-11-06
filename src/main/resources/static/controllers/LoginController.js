/**
 * 
 */
angApp.controller("loginController",function($scope, $http,$state, $log){
	$scope.user = null;
	
	$scope.login = function(){
		$http.post(
				"/logger/access",
				$scope.user
		).then(function success(response){
			$log.log(response);
			//Take user to next state here
			$state.go("tmpState", null);
		}, function error(response){
			$log.error(response);
		});
	}
})