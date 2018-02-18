/**
 * 
 */

angular.module('myapp.publicacion')
.controller('AddPublicacionController', function($scope, $state,$stateParams, publicacionesService){
	$scope.CreadaPublicacion=false;

	
	
	$scope.crearPublicacion=function(){
		
		var retrievedObject = localStorage.getItem('testObject');
	    $scope.usuario=JSON.parse(retrievedObject);
		var publicacion={
			usuario_id: $scope.usuario,
			cartPub:$stateParams.deCartelera,
			tituloPublicacion: $scope.tituloPublicacion,
			cuerpo: $scope.cuerpo,
			fecha: new Date(),
			comentariosHabilitados: $scope.activarComentarios
	    };

		publicacionesService.createPublicacion(publicacion)
        .then(
        		function (response) {
        			publicacionesService.fileUpload($scope.file,response)
        			.then(function(result){
        				 console.log(result);
        				 }, function(error) {
        					 console.log("hubo errores");
        				 });
        			$scope.reset();
        			
        			$scope.loginErrorMessage = 'publicacion creada correctamente'; 
        			$scope.CreadaPublicacion=true;
        			
                },		
		        function(errResponse){
                	$scope.reset();
		           }
        );
	};
	
	$scope.ok=function(){
		$scope.CreadaPublicacion=false;
	}
	
	
	$scope.reset=function(){
		$scope.tituloPublicacion="";
		$scope.cuerpo="";
		$scope.comentariosHabilitados= 0;
	};
	
	
});