// criando os controllers
app.controller('clienteDetalheController', function($scope, $routeParams, $http) {

	$scope.cliente = {};
	
	var id = $routeParams.clienteId;
	$http.get("http://localhost:8080/clientes/" + id).then(function(response) {
		$scope.cliente = response.data;
	}, function(err) {
		console.log(err);
	});

});