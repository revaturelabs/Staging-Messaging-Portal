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
    let loginState={
    		url:'login',
    		name:'loginState',
    		views:{
    			'loginView':{
    				templateUrl:"static/templates/login.html",
    				controller:"loginController"
    			}
    		}
    };
    $stateProvider.state(tmpState);
    $stateProvider.state(registerState);
    $stateProvider.state(loginState);
});