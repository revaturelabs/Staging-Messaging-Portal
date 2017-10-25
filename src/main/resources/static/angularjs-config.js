let angApp = angular.module("SMPApp", ["ui.router"]);

angApp.config(function($stateProvider, $urlRouterProvider)
		{
	
	$urlRouterProvider.otherwise('');
    let tmpState = {
    	url:'',
    	abtract: true,
        name: 'tmpState',
        views: {
            'tmpView': {
                templateUrl: "static/templates/tmp.html",
                controller: "tmpController"
            }
        }
    };

    let registerState = {
    		url:'register',
    		name: 'registerState',
    		views:{
    			'registerView' : {
    				templateUrl:"static/templates/register.html",
    				controller:"registerController"
    			}
    		}
    };
    $stateProvider.state(tmpState);
    $stateProvider.state(registerState);

});