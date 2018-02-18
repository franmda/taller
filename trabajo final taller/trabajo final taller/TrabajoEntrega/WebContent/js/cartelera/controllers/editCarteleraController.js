angular.module('myapp.cartelera')
.controller('EditCarteleraController', function($scope, $state,$stateParams, CarteleraService, UserService,LoginService){
    
	
	//---------------------------------------------------------
	     //funciones para una crear una nueva cartelera
	//---------------------------------------------------------
	$scope.textButton = "Editar Cartelera";
	$scope.cartelera = $stateParams.cartelera;
    
    $scope.cargarUsrsPermitidos=function(){
    	//busca los usuarios que están permitidos para publicar
    	UserService.buscarUsrsPermitidos()
    	.then(function(response){
    		$scope.usersPermitidos=response;
    		
    	});
    	
    };
    
    $scope.cargarUsrsPermitidos();
    
	$scope.usuariosAnterioresPermitidos= $stateParams.cartelera.usuariosConPermisos;
	
	
	$scope.cargarUsrsQueFueronPermitidos=function(){
	    	//para quedarme con las ids solamente de los usuarios anteriormente permitidos
		    $scope.usrs= [];
		    for (i in $scope.usuariosAnterioresPermitidos) {
		    	$scope.usrs.push( $scope.usuariosAnterioresPermitidos[i].usuario_id);
		    };
	    	
	    };
    $scope.cargarUsrsQueFueronPermitidos();
    
    

	$scope.nuevoUsuarioConPermisos=function(id_usuario){
		//darle permisos para que pueda publicar en la cartelera
		
		var usuario={
				usuario_id:id_usuario
		};
		$scope.usrs.push(id_usuario);
		$scope.usuariosAnterioresPermitidos.push(usuario);
	};
	
	$scope.removerUsrConPermisos=function(id_usuario){
		//darle permisos para que pueda publicar en la cartelera
		var i;
		for (i =0; i < $scope.usuariosAnterioresPermitidos.length; i++)
			   if ($scope.usuariosAnterioresPermitidos[i].usuario_id === id_usuario) {
				   $scope.usuariosAnterioresPermitidos.splice(i,1);
			      break;
		}
		
		for (i =0; i < $scope.usrs.length; i++)
			   if ($scope.usrs[i] === id_usuario) {
				   $scope.usrs.splice(i,1);
			      break;
		}
	};
	
	
	
	$scope.editCartelera=function(){
		var cartelera={
		    titulo: $scope.tituloCartelera,
		    tipoCartelera: $scope.tipoCartelera,
		    visibilidad: $scope.visibilidad,
		    usuariosConPermisos: $scope.usuariosAnterioresPermitidos
	    };
		
		$scope.cartelera.usuariosConPermisos=$scope.usuariosAnterioresPermitidos;
		$scope.cartelera.estado='habilitado';
		CarteleraService.updateCartelera($scope.cartelera, $stateParams.cartelera.cartelera_id)
        .then(
        		function (response) {
        			$scope.reset();
        			alert("Cartelera editada correctamente");
        			$state.go("home");
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