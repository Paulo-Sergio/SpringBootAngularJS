// criando modulo principal
var app = angular.module('app', ['ngRoute']);

app.config(function($routeProvider, $locationProvider) {
	  
	$routeProvider
		.when('/clientes', {
			templateUrl: 'view/cliente.html',
			controller: 'clienteController'
		})
		
		.when('/clientes/:clienteId', {
			templateUrl: 'view/cliente-detalhe.html',
			controller: 'clienteDetalheController'
		})
		
		.when('/cidades', {
			templateUrl: 'view/cidade.html',
			controller: 'cidadeController'
		})
		
		.when('/estados', {
			templateUrl: 'view/estado.html',
			controller: 'estadoController'
		})
		
		.otherwise({
			rediretTo: '/'
		});

	  // configure html5 to get links working on jsfiddle
	  $locationProvider.html5Mode(true);
});