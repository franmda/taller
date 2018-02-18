'use strict';
 
angular.module('myapp.usuario').factory('UserService', ['$http', '$q', function($http, $q){
 
 
    var factory = {
        fetchAllUsers: fetchAllUsers,
        createUser: createUser,
        updateUser:updateUser,
        deleteUser:deleteUser,
        buscarUsrsPermitidos:buscarUsrsPermitidos,
        traerRoles:traerRoles
        
    };
 
    return factory;
 
    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get('http://localhost:8080/TrabajoEntrega/usuario/usuario/')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function buscarUsrsPermitidos() {
        var deferred = $q.defer();
        $http.get('http://localhost:8080/TrabajoEntrega/usuario/usuariosConPermisoPublicacion')
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

 
    function createUser(user) {
        var deferred = $q.defer();
        $http.post('http://localhost:8080/TrabajoEntrega/usuario/usuario', user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put('http://localhost:8080/TrabajoEntrega/usuario/usuario/'+id, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteUser(id) {
        var deferred = $q.defer();
        $http.delete('http://localhost:8080/TrabajoEntrega/usuario/usuario/'+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function traerRoles(){
    	var deferred = $q.defer();
        $http.get('http://localhost:8080/TrabajoEntrega/rol/rol/')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching roles');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;    	
    }
 
}]);