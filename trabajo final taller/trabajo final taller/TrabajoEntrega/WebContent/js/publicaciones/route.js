angular.module('myapp.publicacion')
.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
	$stateProvider
	.state('agregarPublicacion',{
		parent: 'home',
		url:'/agregarPublicacion',
		 views: {
	            'data@home': {
	                 templateUrl: 'js/publicaciones/views/agregarPublicacion.html',
	                 controller: 'AddPublicacionController'
	             }
	        },
	        params:{
	        	deCartelera: null
			}
		
		
	})
	

	.state('modificarPublicacion',{
		parent: 'home',
		url:'/editPublicacion',
		 views: {
	            'data@home': {
	                 templateUrl: 'js/publicaciones/views/editPublicacion.html',
	                 controller: 'EditPublicacionController'
	             }
	        },
	        params:{
	        	publicacion: null
			}
		
	})

	//$urlRouterProvider.otherwise('/cartelera');
}]);