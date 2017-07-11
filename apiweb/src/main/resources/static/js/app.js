// criando modulo principal
var app = angular.module('app', ['ngRoute']);

app.config(function($routeProvider, $locationProvider) {
	  
	$routeProvider
		.when('/clientes', {
			templateUrl: 'view/cliente.html',
			controller: 'clienteController'
		})
		
		.otherwise({
			rediretTo: '/'
		});

	  // configure html5 to get links working on jsfiddle
	  $locationProvider.html5Mode(true);
});