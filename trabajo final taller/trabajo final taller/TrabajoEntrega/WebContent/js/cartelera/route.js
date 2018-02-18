angular.module('myapp.cartelera')
.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
	$stateProvider
	.state('editCartelera',{
		parent: 'home',
		url:'/editCartelera',
		 views: {
	            'data@home': {
	                 templateUrl: 'js/cartelera/views/editCartelera.html',
	                 controller: 'EditCarteleraController'
	             }
	        },
	        params:{
	        	cartelera: null
			}
		
	})
	
	.state('agregarCartelera',{
		parent: 'home',
		url:'/addCartelera',
		 views: {
	            'data@home': {
	                 templateUrl: 'js/cartelera/views/addCartelera.html',
	                 controller: 'AddCarteleraController'
	             }
	        }

		
	});
	


	//$urlRouterProvider.otherwise('/cartelera');
}]);
