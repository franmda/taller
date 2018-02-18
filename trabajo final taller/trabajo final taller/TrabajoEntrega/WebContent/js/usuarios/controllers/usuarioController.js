'use strict';
 
angular.module('myapp.usuario')
.controller('UserController', function($scope, $state, UserService,LoginService){
    
   //---------------------------------------------------------
        //Vista de las carteleras y el detalle de la publicacion
   //---------------------------------------------------------
    
	$scope.fetchAllUsers=function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
            	$scope.users = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    
    $scope.fetchAllUsers();
    
   $scope.crearUsuario=function crearUsuario(){
	   $state.go('addUser');
	   
   }
 
    $scope.deleteUser=function deleteUser(id){
    	UserService.deleteUser(id)
		.then(function(){
			$scope.fetchAllUsers();
			
		});
    }
 


    
	
});