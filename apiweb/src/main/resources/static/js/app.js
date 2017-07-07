// criando modulo principal
var appCliente = angular.module('appCliente', []);

// criando os controllers
appCliente.controller('indexController', function($scope, $http) {

	$scope.nome = "Paulo";
	$scope.sobrenome = "França";

	$http.get('http://localhost:8080/clientes/').then(function(response) {
		console.log(response.data);
	}, function(response) {
		console.log(response);
	});

});