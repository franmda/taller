angular.module('myapp.usuario')
.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
	$stateProvider
	.state('editUser',{
		parent: 'home',
		url:'/editUser',
		 views: {
	            'data@home': {
	                 templateUrl: 'js/usuarios/views/editUser.html',
	                 controller: 'EditUserController'
	             }
	        },
	        params:{
	        	usuario: null
			}
		
	})
	
	.state('addUser',{
		parent: 'home',
		url:'/addUser',
		 views: {
	            'data@home': {
	                 templateUrl: 'js/usuarios/views/addUser.html',
	                 controller: 'AddUserController'
	             }
	        }

		
	});
	


	//$urlRouterProvider.otherwise('/cartelera');
}]);
