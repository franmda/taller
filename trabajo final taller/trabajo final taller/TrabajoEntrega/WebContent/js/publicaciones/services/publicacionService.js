'use strict';

angular.module('myapp.publicacion')
.factory('publicacionesService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/TrabajoEntrega/publicacion/publicacion';
    
    var factory = {
        fetchAllPublicacion: fetchAllPublicacion,
        createPublicacion: createPublicacion,
        updatePublicacion: updatePublicacion,
        deletePublicacion: deletePublicacion,
        crearComentario: crearComentario,
        fileUpload:fileUpload,
        modifFile:modifFile,
    };

    return factory;

    function fetchAllPublicacion() {
        var deferred = $q.defer();
        $http.get('http://localhost:8080/TrabajoEntrega/publicacion/publicacion')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching publicaciones');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fileUpload(file,id){
    	var deferred = $q.defer();
    	var p={
    		    publicacion_id: id
    		};
    	$http({
    	    method: 'POST',
    	    url: 'http://localhost:8080/TrabajoEntrega/media/continueFileUpload',
    	    headers: {'Content-Type': undefined },
    	    transformRequest: function (data) {
    	        var formData = new FormData();

    	        formData.append('store', new Blob([JSON.stringify(data.store)], {
    	            type: "application/json"
    	        }));
    	        formData.append("file", data.file);
    	        return formData;
    	    },
    	    data: { store: p, file: file }

    	}).then(
    		 function (response) {
    	          deferred.resolve(response.data);        
    	         },
    	     function(errResponse){
                  console.error('Error while creating publicacion');
    	          deferred.reject(errResponse);
    	     } );
    	return deferred.promise;
    }

    function createPublicacion(publicacion) {
        var deferred = $q.defer();
        
        $http.post('http://localhost:8080/TrabajoEntrega/publicacion/publicacion', publicacion)
            .then(
            function (response) {
                deferred.resolve(response.data);
                
            },
            function(errResponse){
                console.error('Error while creating publicacion');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updatePublicacion(publicacion, id) {
    	 var deferred = $q.defer();
         $http.put('http://localhost:8080/TrabajoEntrega/publicacion/publicacion/'+id, publicacion)
             .then(
             function (response) {
                 deferred.resolve(response.data);
             },
             function(errResponse){
                 console.error('Error while updating Cartelera');
                 deferred.reject(errResponse);
             }
         );
         return deferred.promise;
    }
    
    
    function modifFile(file,media){
    	var deferred = $q.defer();
    	$http({
    	    method: 'POST',
    	    url: 'http://localhost:8080/TrabajoEntrega/media/media/'+media.mediaContents_id,
    	    headers: {'Content-Type': undefined },
    	    transformRequest: function (data) {
    	        var formData = new FormData();
    	        formData.append('store', new Blob([JSON.stringify(data.store)], {
    	            type: "application/json"
    	        }));
    	        formData.append("file", data.file);
    	        return formData;
    	    },
    	    data: { store: media ,file: file }

    	}).then(
    		 function (response) {
    	          deferred.resolve(response.data);        
    	         },
    	     function(errResponse){
                  console.error('Error while creating publicacion');
    	          deferred.reject(errResponse);
    	     } );
    	return deferred.promise;
    }
    

    function deletePublicacion(id) {
        var deferred = $q.defer();
        $http.delete('http://localhost:8080/TrabajoEntrega/publicacion/publicacion'+'/'+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Publicacion');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    
    function crearComentario (comentario) {
        var deferred = $q.defer();
        $http.post('http://localhost:8080/TrabajoEntrega/comentarios/crearComentario', comentario)
            .then(
            function (response) {
                deferred.resolve(response.data);
                
            },
            function(errResponse){
                console.error('Error while adding comentario');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);