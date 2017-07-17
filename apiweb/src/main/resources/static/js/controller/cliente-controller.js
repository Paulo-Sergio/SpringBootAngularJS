// criando os controllers
app.controller('clienteController', function($scope, $http) {

	$scope.clientes = [];
	$scope.cliente = {};
	$scope.estados = [];
	$scope.estado = {};

	$scope.carregarEstados = function() {
		$http.get('http://localhost:8080/estados/').then(function(response) {
			$scope.estados = response.data;
		}, function(err) {
			console.log(err);
		});
	};
	
	$scope.carregarClientes = function() {
		// pegando userToken do localStorage
		token = localStorage.getItem("userToken");
		
		// colocando no header da request http o token Authorization
		//$http.defaults.headers.common.Authorization = 'Bearer ' + token;
		
		$http.get('http://localhost:8080/admin/clientes/').then(function(response) {
			$scope.clientes = response.data;
			console.log("Response " + response.data);
			console.log("Status " + response.status);
		}, function(response) {
			console.log(response);
		});
	};

	$scope.salvarCliente = function() {
		if($scope.frmCliente.$valid){
			
			if($scope.cliente.id == null || $scope.cliente.id == undefined) {
				$http.post('http://localhost:8080/admin/clientes/', $scope.cliente).then(function(response) {
					$scope.clientes.push(response.data);
					$scope.cancelar();
				}, function(response) {
					console.log(response.data);
				});
			} else {
				$http.put('http://localhost:8080/admin/clientes/', $scope.cliente).then(function(response) {
					$scope.carregarClientes();
					$scope.cancelar();
				}, function(response){
					console.log(response);
				});
			}
			
			// settar pritine para não validar o form apos salvar um cliente
			$scope.frmCliente.$setPristine(true);
			
		} else {
			window.alert("Dados invalidos!");
		}
		
		
	};
	
	$scope.excluirCliente = function(cliente) {
		$http.delete('http://localhost:8080/admin/clientes/' + cliente.id).then(function(response) {
			var posicao = $scope.clientes.indexOf(cliente);
			$scope.clientes.splice(posicao, 1);
		}, function (response){
			console.log(response);
		});
	};
	
	$scope.alterarCliente = function(cliente){
		$scope.cliente = angular.copy(cliente);
	};
	
	$scope.cancelar = function() {
		$scope.cliente = {};
	};

	$scope.carregarClientes();
	$scope.carregarEstados();

});