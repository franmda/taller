angular.module('myapp').run(
		function($rootScope, $state, LoginService) {
			var routesForAdmins = [ '/cartelera', '/addCartelera',
					'/editCartelera', '/usuario', '/editUser',
					'/agregarPublicacion', '/editPublicacion',
					'/verPublicacion', '/addUser' ];
			var routesForAlumno = [ '/cartelera', '/verPublicacion' ];
			var routesForOtros = [ '/cartelera', '/agregarPublicacion',
					'/editPublicacion', '/verPublicacion' ];
			$rootScope.$on('$stateChangeStart', function(event, toState) {
				if (!LoginService.isLoggedIn() && toState.url != "/login") {
					event.preventDefault();
					$state.go('login');
				} else {
					if (angular.isDefined(localStorage.getItem('testObject'))
							&& localStorage.getItem('testObject') !== null) {
						var retrievedObject = localStorage
								.getItem('testObject');
						var rol = JSON.parse(retrievedObject).rol_id.nombreRol;
						if (rol == "Administrador") {
							if (!routesForAdmins.includes(toState.url)) {
								event.preventDefault();
								$state.go("cartelera");
							}
						} else if (rol == "Alumno") {
							if (!routesForAlumno.includes(toState.url)) {
								event.preventDefault();
								$state.go("cartelera");
							}
						} else if ((rol == "Profesor")||(rol=="Publicador")) {
							if (!routesForOtros.includes(toState.url)) {
								event.preventDefault();
								$state.go("cartelera");
							}

						}

					}

				}
			});

		});