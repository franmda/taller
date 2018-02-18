angular.module('myapp.cartelera')
.controller('DetailCtrl', function($scope, $stateParams,publicacionesService) {

  $scope.publicacion = $stateParams.publicacion;
  console.log($scope.publicacion);
  var retrievedObject = localStorage.getItem('testObject');
  $scope.usuario = JSON.parse(retrievedObject);
  $scope.viewComment = false;
  $scope.comments = $stateParams.publicacion.comentarios;
  $scope.imgsPub=$stateParams.publicacion.mediaContents[0];
  
  function arrayBufferToBase64( buffer ) {
	    var binary = '';
	    var bytes = new Uint8Array( buffer );
	    var len = bytes.byteLength;
	    for (var i = 0; i < len; i++) {
	        binary += String.fromCharCode( bytes[ i ] );
	    }
	    return window.btoa( binary );
	}
  
  $scope.submit = function(){
    $scope.viewComment = false;
    var comment = {
    	pubMComentario : {publicacion_id: $stateParams.publicacion.publicacion_id},
    	dia: new Date(),
        usuario_id: {usuario_id: $scope.usuario.usuario_id},
        estado: "habilitado",
        msj: $scope.newComment  //binding entre la vista y el controller a traves del $scope
    };

    publicacionesService.crearComentario(comment);
    comment.usuario=$scope.usuario.nombre;
    $scope.comments.push(comment);
  };
  
  
  

});
