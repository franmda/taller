angular.module('myapp.addCartelera')
.controller('AddCarteleraController', function($scope, $state, CarteleraService, UserService, LoginService){
    
	
	//---------------------------------------------------------
	     //funciones para una crear una nueva cartelera
	//---------------------------------------------------------
	$scope.tipoCartelera="";
	$scope.tituloCartelera="";
	$scope.textButton = "Agregar Cartelera";	
    
    $scope.cargarUsrsPermitidos=function(){
    	//busca los usuarios que están permitidos para publicar
    	UserService.buscarUsrsPermitidos()
    	.then(function(response){
    		$scope.usersPermitidos=response;
    		
    	});
    	
    };
    
    $scope.cargarUsrsPermitidos();
    
	$scope.usuariosPermitidos= [];
	$scope.usrs= [];
	$scope.nuevoUsuarioConPermisos=function(id_usuario){
		//darle permisos para que pueda publicar en la cartelera
		
		var usuario={
				usuario_id:id_usuario
		};
		$scope.usrs.push(id_usuario);
		$scope.usuariosPermitidos.push(usuario);
	};
	
	$scope.removerUsrConPermisos=function(id_usuario){
		//darle permisos para que pueda publicar en la cartelera
		var i;
		for (i =0; i < $scope.usuariosPermitidos.length; i++)
			   if ($scope.usuariosPermitidos[i].usuario_id === id_usuario) {
				   $scope.usuariosPermitidos.splice(i,1);
			      break;
		}
		
		for (i =0; i < $scope.usrs.length; i++)
			   if ($scope.usrs[i] === id_usuario) {
				   $scope.usrs.splice(i,1);
			      break;
		}
	};
	
	
	
	$scope.crearCartelera=function(){
		var cartelera={
		    titulo: $scope.tituloCartelera,
		    tipoCartelera: $scope.tipoCartelera,
		    visibilidad: null,
		    usuariosConPermisos: $scope.usuariosPermitidos,
		    estado:'habilitado'
	    };
		CarteleraService.createCartelera(cartelera)
        .then(
        		function (response) {
        			$scope.reset();
        			alert("Cartelera creada correctamente");
                },		
		        function(errResponse){
                	$scope.reset();
                	alert("ERROR CREANDO CARTELERA");
		           }
        );
		
	};
	
	
	$scope.reset=function(){
		$scope.tituloCartelera="";
		$scope.tipoCartelera="";
		$scope.visibilidad=0;
		$scope.usrs= [];
		$scope.usuariosPermitidos= [];
	};
	
	//---------------------------------------------------------
	//---------------------------------------------------------
    
	
});