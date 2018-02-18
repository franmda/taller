/**
* Modulo principal.
*
* Este modulo depende de todos los modulos con conforman el sistema, y se encarga de inicializarlos
*
*/
angular.module('myapp', [
	'ui.router',
	'myapp.home',
	'LocalStorageModule',
	'wu.masonry',
	'myapp.login',
	'myapp.environment',
	'myapp.cartelera',
	'myapp.addCartelera',
	'myapp.usuario',
	'myapp.editUsuario',
	'myapp.publicacion'
]);

angular.module('myapp.home', []);
angular.module('myapp.login', []);

angular.module('myapp.cartelera', []);
angular.module('myapp.addCartelera', []);

angular.module('myapp.usuario', []);
angular.module('myapp.editUsuario', []);
angular.module('myapp.addUsuario', []);

angular.module('myapp.publicacion', []);
