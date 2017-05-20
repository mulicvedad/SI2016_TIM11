function authInterceptor(jwtService, ENV, $state, $q) {
    'ngInject';

    return {
        // dodavanje auth headera na sve zahtjeve prema nasem API-ju
        request: (config) => {
            if(config.url.indexOf(ENV.API_URL) === 0 && jwtService.getToken()) {
                config.headers.Authorization = 'Bearer ' + jwtService.getToken();
            }
            return config;
        },

        responseError: function(rejection) {
            if (rejection.status === 401) {
                // token vise nije validan 
                // ovdje ce mozda ici provjera da li postoji refresh token kako bi se obnovio JWT
                jwtService.destroyToken();     
                //$state.go('home');
            }
            return $q.reject(rejection);
        }
    }
}

export default authInterceptor;