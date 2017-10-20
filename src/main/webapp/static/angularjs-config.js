let angApp = angular.module("SMPApp", ["ui.router"]);

angApp.config(function($stateProvider)
		{

    let tmp = {
        name: "tmpState",
        views: {
            "tmpView": {
                templateUrl: "templates/tmp.html",
                controller: "tmpController"
            }
        }
    };
    
    $stateProvider.state(tmpState);

});