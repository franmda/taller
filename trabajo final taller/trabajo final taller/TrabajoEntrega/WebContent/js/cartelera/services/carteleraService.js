'use strict';

angular.module('myapp.cartelera')
.factory('CarteleraService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/TrabajoEntrega/cartelera/cartelera';
    
    var factory = {
        fetchAllCartelera: fetchAllCartelera,
        createCartelera: createCartelera,
        updateCartelera: updateCartelera,
        deleteCartelera: deleteCartelera,
        getPublicacionDeCartelera:getPublicacionDeCartelera,
        updateInteres: updateInteres,
    };

    return factory;

    function fetchAllCartelera() {
        var deferred = $q.defer();
        $http.get('http://localhost:8080/TrabajoEntrega/cartelera/cartelera')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Cartelera');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    

    function createCartelera(cartelera) {
        var deferred = $q.defer();
        
        $http.post('http://localhost:8080/TrabajoEntrega/cartelera/cartelera', cartelera)
            .then(
            function (response) {
                deferred.resolve(response.data);
                
            },
            function(errResponse){
                console.error('Error while creating cartelera');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateCartelera(cartelera, id) {
        var deferred = $q.defer();
        $http.put('http://localhost:8080/TrabajoEntrega/cartelera/cartelera/'+id, cartelera)
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
    
    

    function deleteCartelera(id) {
        var deferred = $q.defer();
        $http.delete('http://localhost:8080/TrabajoEntrega/cartelera/cartelera'+'/'+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Cartelera');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    function getPublicacionDeCartelera(cartelera_id){
    	var deferred = $q.defer();
    	 $http.get('http://localhost:8080/TrabajoEntrega/publicacion/publicacionesDeCartelera/'+cartelera_id)
    	     .then(
    	    		 function (response) {
    	                 deferred.resolve(response.data);
    	             },
    	             function(errResponse){
    	                 console.error('Error buscando las publicaciones');
    	                 deferred.reject(errResponse);
    	             }	 
    	     
    	 );
    	 return deferred.promise;
    	
    }
    function updateInteres(id,cartelera) {
        var deferred = $q.defer();
        $http.put('http://localhost:8080/TrabajoEntrega/usuario/interes/'+id, cartelera)
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
}]);