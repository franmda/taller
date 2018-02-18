angular.module('myapp.cartelera')
.controller('CarteleraController', function($scope, $state, CarteleraService,UserService,LoginService,publicacionesService){
    
   //---------------------------------------------------------
        //Vista de las carteleras y el detalle de la publicacion
   //---------------------------------------------------------
	var retrievedObject = localStorage.getItem('testObject');
    $scope.usuario=JSON.parse(retrievedObject);
    
    
    $scope.rolAdmin= ($scope.usuario.rol_id.nombreRol=="Administrador");
    $scope.rolAdminProfe=(($scope.usuario.rol_id.nombreRol=="Administrador")||($scope.usuario.rol_id.nombreRol=="Profesor"));
    $scope.rolAlumno=($scope.usuario.rol_id.nombreRol=="Alumno");
    
    $scope.cargarCartelera = function(){
    	CarteleraService.fetchAllCartelera() 
		.then(function(response){
				$scope.carteleras = response;
		});
	};
	 $scope.cargarCartelera();
	
	 
	 
	$scope.verUsrsInteresados=function (){
		$scope.verUsersInteresados=true;
		$scope.users=$scope.carteleraActiva.usuariosInteresados;
		$scope.publicaciones=[];
	}
	 
	 
	 
	
	
	
	$scope.nuevoInteresEnCartelera=function(cartelera){
		var cartInteresada={
				Cartelera_id:cartelera.Cartelera_id
		};
		$scope.usuario.cartelerasInteresadas.add(cartInteresada);
		UsuarioService.updateUser($scope.usuario,$scope.usuario.usuario_id)
		.then(function(response){
			$scope.cargarCartelera();
		    },
		    function(errResponse){
            	alert("ERROR CREANDO CARTELERA");
	           }
		
		);
	}
	
	
	$scope.cargarPublicacion =function(cartelera){
		
		$scope.verUsersInteresados=false;
		$scope.usersInteresados=[];
		var cartsUser=$scope.usuario.cartelerasInteresadas;
		$scope.hayInteres=cartsUser.includes(cartelera.Cartelera_id );
		CarteleraService.getPublicacionDeCartelera(cartelera.Cartelera_id)
		.then(function(response){
			$scope.carteleraActiva=cartelera;
			$scope.publicaciones=response;
			$scope.activa=true;
			
			$scope.verInteres();
			if($scope.usuario.rol_id.nombreRol=="Administrador"){
				$scope.puedePublicar=true;
			}else{
				for(i=0;i<cartelera.usuariosConPermisos.length;i++){
					if(cartelera.usuariosConPermisos[i].usuario_id == $scope.usuario.usuario_id){
						$scope.puedePublicar=true;
					}
				}
			};
		});
	};
	
	$scope.activa=false;
	$scope.verUsersInteresados=false;
	$scope.agregarNuevaCartelera=function(){
		$state.go('agregarCartelera');
	};
	
	//---------------------------------------------------------
	//---------------------------------------------------------
	
	
	$scope.eliminarCartelera=function(){
		CarteleraService.deleteCartelera($scope.carteleraActiva.Cartelera_id)
		.then(function(){
			$scope.carteleraActiva=null;
			$scope.activa=false;
			$scope.cargarCartelera();
			
		});
		
		
	};
	
	$scope.eliminarPublicacion=function(publicacion){
		publicacionesService.deletePublicacion(publicacion.publicacion_id)
		.then(function(){
			$scope.publicaciones=$scope.cargarPublicacion($scope.carteleraActiva);
			$scope.cargarCartelera();
		});
	};
	//---------------------------------------------------------
	//---------------------------------------------------------
	$scope.cargarCartelerasInteresadas=function (){
		var carts=$scope.carteleras;
		var cartsInteresadas=[];
		var cartsUser=$scope.usuario.cartelerasInteresadas;
		for(i=0;i<carts.length;i++){
			if(cartsUser.includes(carts[i].Cartelera_id )){
				cartsInteresadas.add(carts[i]);
			}
		}
		$scope.carteleras=cartsInteresadas;
	}
	
	$scope.verInteres=function(){
		$scope.hayInteres=false;
		var cartsUser=$scope.usuario.cartelerasInteresadas;
		for(i=0;i<cartsUser.length;i++){
			if(cartsUser[i].Cartelera_id == $scope.carteleraActiva.Cartelera_id){
				$scope.hayInteres=true;
				break;
			}
		}
	}
	
	
	
	
	$scope.nuevoInteresEnCartelera=function(cartelera){
		var cartInteresada={
				Cartelera_id:$scope.carteleraActiva.Cartelera_id
		};
		$scope.usuario.cartelerasInteresadas.push(cartInteresada);
		CarteleraService.updateInteres($scope.usuario.usuario_id,cartelera)
		.then(function(response){
			$scope.cargarCartelera();
			$scope.hayInteres=true;
		    },
		    function(errResponse){
            	alert("ERROR Agregando interes");
	           }
		);
	}

	
	
});