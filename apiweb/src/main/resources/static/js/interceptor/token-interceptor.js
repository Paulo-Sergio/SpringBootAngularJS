app.factory('tokenInterceptor', function($q, $location) {
	
	return {
		
		// interceptor da chave request
		// config contem acesso ao request
		'request' : function(config) {
			config.headers.Authorization = 'Bearer ' + localStorage.getItem("userToken");
			
			return config;
		},
		
		'responseError' : function(rejection) {
			if(rejection.status == 401) {
				$location.path("/login");
			}
			
			return $q.reject(rejection);
		}
		
	};
	
});