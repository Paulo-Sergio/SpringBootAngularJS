// criando os controllers
app.controller('loginController', function($scope, $http) {

	$scope.usuario = {};
	
	$scope.token = "";
	
	$scope.error = {};
	
	$scope.autenticar = function() {
		$http.post("http://localhost:8080/autenticar", $scope.usuario).then(function(response) {
			console.log(response);
			$scope.token = response.data.token;
			
			// armazenando o Token que esta vindo da API no browser [local storage]
			localStorage.setItem("userToken", response.data.token);
		}, function(err) {
			console.log(err);
			$scope.error = err.data;
		});
		
	};

});