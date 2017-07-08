// criando modulo principal
var appCliente = angular.module('appCliente', []);

// criando os controllers
appCliente.controller('indexController', function($scope, $http) {

	$scope.clientes = [];
	$scope.cliente = {};

	$scope.carregarClientes = function() {
		$http.get('http://localhost:8080/clientes/').then(function(response) {
			$scope.clientes = response.data;
			console.log("Response " + response.data);
			console.log("Status " + response.status);
		}, function(response) {
			console.log(response);
		});
	};

	$scope.salvarCliente = function() {
		$http.post('http://localhost:8080/clientes/', $scope.cliente).then(function(response) {
			$scope.clientes.push(response.data);
		}, function(response) {
			console.log(response.data);
		});
	};
	
	$scope.excluirCliente = function(cliente) {
		$http.delete('http://localhost:8080/clientes/' + cliente.id).then(function(response) {
			var posicao = $scope.clientes.indexOf(cliente);
			$scope.clientes.splice(posicao, 1);
		}, function (response){
			console.log(response);
		});
	}

	$scope.carregarClientes();

});