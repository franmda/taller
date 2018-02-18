angular.module('myapp.login')
.controller('loginController', function($scope, $state, LoginService, UserService){
  $scope.username='';
  $scope.password='';
  
  
  
  $scope.login=function login(){
	    $scope.loading = true;
	     LoginService.login($scope.username, $scope.password)
	    .then(function (response) {
	    	localStorage.setItem("usuario", response);
	        $scope.loginErrorMessage = ''; //reset error message
	        $state.go('cartelera');
	     },
	     function(error){
	         LoginService.loginAlum($scope.username, $scope.password)
	         .then(function(response){
	                var usuario={
	                        usrName: response.nombres,
	                        pass: response.clave,
	                        nombre: response.nombres,
	                        apellido: response.apellidos,
	                        habilitacionSist: true,
	                        mail: "lala@lala",
	                        dni: 123123,
	                        template_id: null,
	                        rol_id: {
	                            id: 2,
	                            nombreRol: "Alumno"
	                        }
	                    };
	            localStorage.setItem("usuario", usuario);
	            UserService.createUser(usuario).then(function(response){
    	          	LoginService.login($scope.username, $scope.password)
    	    	    .then(function (response) {
    	    	    	localStorage.setItem("usuario", response);
    	    	        $scope.loginErrorMessage = ''; //reset error message
    	    	        $state.go('cartelera');
    	    	     },
    	    	     function(error){
    	    	    	 $scope.loginErrorMessage = 'hubo algun error al querer loguearse el profesor';
    	                 $scope.loading = false;
    	    	     });  },
    	        function(error){
    	    	    $scope.loginErrorMessage ="error al agregar el usuario ";
    	     	    $scope.loading =false;
    	        });  
		        },
		        function(error){
		    	   LoginService.loginProfe($scope.username, $scope.password)
		    	   .then(function(response){
		    	          	  var usuario={
		  	                        usrName: response.nombres,
			                        pass: response.clave,
			                        nombre: response.nombres,
			                        apellido: response.apellidos,
			                        habilitacionSist: true,
			                        mail: "lala@lala",
			                        dni: 123123,
			                        template_id: null,
			                        rol_id: {
			                            id: 3,
			                            nombreRol: "Profesor"
			                        }
			                    };
		    	          	localStorage.setItem("usuario", usuario);
		    	          	UserService.createUser(usuario).then(function(response){
				    	          	LoginService.login($scope.username, $scope.password)
				    	    	    .then(function (response) {
				    	    	    	localStorage.setItem("usuario", response);
				    	    	        $scope.loginErrorMessage = ''; //reset error message
				    	    	        $state.go('cartelera');
				    	    	     },
				    	    	     function(error){
				    	    	    	 $scope.loginErrorMessage = 'hubo algun error al querer loguearse el profesor';
				    	                 $scope.loading = false;
				    	    	     });  },
				    	    function(error){
				    	    	$scope.loginErrorMessage ="error al agregar el usuario ";
				    	     	$scope.loading =false;
				    	    });
				    	    },
				    	    function(error){
				    	         $scope.loginErrorMessage = 'Usuario o Contraseña invalido. Por favor, vuelva a intentarlo';
				                 $scope.loading = false;
				    	    	
				    	    }
				     )
		    	    
		    	   
		          })
	    	
	       
	    })
	        
	    $state.go('cartelera');
  };
  
  

  function reset(){
	  self.user={
		  	    pass: "",
		  	    usrName: "",};
      $scope.myForm.$setPristine(); //reset Form
  }
  
  
  
  
});