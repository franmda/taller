angular.module('myapp.home')
.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
	$stateProvider
	.state('home', {
		url:'/home',
		views:{
			'main':{
				templateUrl: 'js/home/views/home.html',
				controller: 'homeCtrl',
				
			}

		}
	})
	.state('cartelera',{
		parent: 'home',
		url:'/cartelera',
		 views: {
	            'data@home': {
	                 templateUrl: 'js/cartelera/views/verCarteleras.html',
	                 controller: 'CarteleraController'
	             }
	        }
		
		
	})
	
	
	.state('clientes',{
		parent: 'home',
		url:'/usuario',
		 views: {
	            'data@home': {
	                 templateUrl: 'js/usuarios/views/usuarios.html',
	                 controller: 'UserController'
	             }
	        }
		
		
	})
	
	.state('detail', {
		parent: 'home',
		url:'/verPublicacion',
		views:{
			'data@home':{
				templateUrl: 'js/cartelera/views/detail.html',
				controller: 'DetailCtrl'
			}
		},
		params:{
			publicacion: null
		}
	});

	//$urlRouterProvider.otherwise('/cartelera');
}]);
