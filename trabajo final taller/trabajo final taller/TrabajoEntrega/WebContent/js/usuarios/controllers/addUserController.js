'use strict';
 
angular.module('myapp.usuario')
.controller('AddUserController', function($scope, $state, UserService,LoginService){
    
   //---------------------------------------------------------
        //Vista de las carteleras y el detalle de la publicacion
   //---------------------------------------------------------

	$scope.cargarRoles = function(){
    	UserService.traerRoles() 
		.then(function(response){
				$scope.roles = response;
		});
	};
	$scope.cargarRoles();
	
    $scope.addUser=function addUser(){
    	var user={
    			usrName:$scope.usrName,
    	        pass: $scope.pass,
    	        nombre: $scope.nombre,
    	        apellido: $scope.apellido,
    	        estado: "habilitado",
    	        rol_id: $scope.rol_id,
    	        dni:111,
    	        habilitacionSist:true,
    	        mail:$scope.mail
    	};
        UserService.createUser(user)
            .then( function(){
            	$state.go("clientes");
            })
        };
 



});