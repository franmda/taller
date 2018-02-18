/**
 * 
 */

'use strict';
 
angular.module('myapp.publicacion')
.controller('EditPublicacionController', function($scope, $state, $stateParams, publicacionesService){
    
   //---------------------------------------------------------
        //Vista de las carteleras y el detalle de la publicacion
   //---------------------------------------------------------
	$scope.textButton = "Editar Publicacion";
	$scope.publicacion = $stateParams.publicacion;
	var imgsPub=$stateParams.publicacion.mediaContents[0];
	
	
    $scope.updatePublicacion=function updatePublicacion(){
    	var pub={
    			usuario_id: $scope.publicacion.usuario,
    			cartPub:$scope.publicacion.cartPub,
    			tituloPublicacion: $scope.publicacion.tituloPublicacion,
    			cuerpo: $scope.publicacion.cuerpo,
    			fecha: new Date(),
    			comentariosHabilitados: $scope.publicacion.comentariosHabilitados
    	    };
    	publicacionesService.updatePublicacion(pub, $scope.publicacion.publicacion_id)
            .then( function(){
            	publicacionesService.modifFile($scope.file,imgsPub)
            	.then(function(result){
        				 console.log(result);
        				 }, function(error) {
        					 console.log("hubo errores");
        				 });
            })
        };
 
});