'use strict';
angular
		.module('myapp.login')
		.factory(
				'LoginService',
				function(ENV, $http, $q) {

					var config = {
						headers : {
							'Content-Type' : 'application/json;charset=utf-8;',
						}
					};

					var login = function(user, password) {
						var defer = $q.defer();
						$http
								.post(
										"http://localhost:8080/TrabajoEntrega/usuario/usuario/login",
										{
											'usrName' : user,
											'pass' : password
										}, config)
								.success(
										function(data) {
											localStorage.setItem('testObject',
													JSON.stringify(data));
											// Retrieve the object from storage
											var retrievedObject = localStorage
													.getItem('testObject');
											console
													.log(
															'retrievedObject: ',
															JSON
																	.parse(retrievedObject));
											localStorage.setItem(
													'tokenSeguridad', true);
											defer.resolve(data);
										}).error(defer.reject);
						return defer.promise;
					};

					var loginAlum = function(user, password) {
						var defer = $q.defer();
						$http
								.post(
										"http://localhost:8080/TrabajoEntrega/autenticar/alumnos/chequearlogin",
										{
											'nombres' : user,
											'clave' : password
										}, config).success(
										function(data) {
											localStorage.setItem('usuario',
													data);
											localStorage.setItem(
													'tokenSeguridad', true);
											defer.resolve(data);
										}).error(defer.reject);
						return defer.promise;
					};

					var loginProfe = function(user, password) {
						var defer = $q.defer();
						$http
								.post(
										"http://localhost:8080/TrabajoEntrega/autenticar/profesor/chequearlogin",
										{
											'nombres' : user,
											'clave' : password
										}, config).success(
										function(data) {
											localStorage.setItem(
													'tokenSeguridad', true);
											defer.resolve(data);
										}).error(defer.reject);
						return defer.promise;

					};

					var logout = function() {
						var defer = $q.defer();
						// invalido el token
						localStorage.removeItem('tokenSeguridad');
						localStorage.removeItem('testObject');
						defer.resolve();

						return defer.promise;
					};

					var isLoggedIn = function() {
						var isToken = angular.isDefined(getToken())
								&& getToken() !== null;
						return isToken;
					};

					var getToken = function() {
						return localStorage.getItem('tokenSeguridad');
					};

					var getUserLogueado = function() {
						return localStorage.getItem('usuario');
					}

					return {
						login : login,
						logout : logout,
						getToken : getToken,
						isLoggedIn : isLoggedIn,
						loginAlum : loginAlum,
						loginProfe : loginProfe,
						getUserLogueado : getUserLogueado
					};
				})