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
    $stateProvider.state(tmpState);

});