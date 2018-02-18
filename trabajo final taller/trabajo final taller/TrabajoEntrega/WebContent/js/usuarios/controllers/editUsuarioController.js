'use strict';
 
angular.module('myapp.editUsuario')
.controller('EditUserController', function($scope, $state, $stateParams, UserService,LoginService){
    
   //---------------------------------------------------------
        //Vista de las carteleras y el detalle de la publicacion
   //---------------------------------------------------------
	$scope.textButton = "Editar Usuario";
	$scope.usuario = $stateParams.usuario;
	$scope.admin=($stateParams.usuario.rol_id.nombreRol=="Administrador");
	
	
	$scope.cargarRoles = function(){
    	UserService.traerRoles() 
		.then(function(response){
				$scope.roles = response;
		});
	};
	$scope.cargarRoles();
	
	
    $scope.updateUser=function updateUser(){
        UserService.updateUser($scope.usuario, $scope.usuario.usuario_id)
            .then( function(){
            	$state.go("clientes");
            })
        };
 
});