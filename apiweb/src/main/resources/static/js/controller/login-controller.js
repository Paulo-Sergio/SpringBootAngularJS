// criando os controllers
app.controller('loginController', function($scope, $http) {

	$scope.usuario = {};
	
	$scope.autenticar = function() {
		
		$http.post("http://localhost:8080/autenticar", $scope.usuario).then(function(response) {
			console.log(response);
		}, function(err) {
			console.log(err);
		});
		
	};

});