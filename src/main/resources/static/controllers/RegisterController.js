/**
 * 
 */
angApp.controller("registerController",function($scope, $http,$state, $log){
	
	$scope.locations = [
		{id:1, place: "Virginia"},
		{id:2, place: "New York"},
		{id:3, place: "Florida" }
	];	
	$scope.newUser = null;
	$scope.register = function(){
		$log.log($scope.newUser);	
		if($scope.newUser && $scope.newUser.firstName && $scope.newUser.lastName && $scope.newUser.email && $scope.newUser.locationId){
			$http.post(
			"/api/register-user",
			$scope.newUser
			).then(function success(response){
				$log.log(response);
			}, function error(response){
				$log.error(response);
			});
			
		} else {
			
		}
		
	}
});