/**
 * 
 */

angular.module('myapp.cartelera')
.filter('startFromGrid', function() {
	  return function(input, start) {
		    start = +start;
		    return input.slice(start);
		  }
		})