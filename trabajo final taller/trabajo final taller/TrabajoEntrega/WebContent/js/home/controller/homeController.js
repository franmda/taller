angular.module('myapp.home')
.controller('homeCtrl', function($scope, $state,  LoginService){
	var retrievedObject = localStorage.getItem('testObject');
    $scope.usuarioNombre=JSON.parse(retrievedObject).nombre;
    
    var user = LoginService.getUserLogueado('usuario');
	$scope.logout = function() {
		LoginService.logout()
		.then(function(){
			$state.go('login');
		});
	};
	
	$scope.Acarteleras = function() {
		$state.go('cartelera');

	};
	
	$scope.Aclientes = function() {
       $state.go('clientes');
	};
	
	
});