function authInterceptor(jwtService, ENV, $state, $q, swalService) {
    'ngInject';

    return {
        // dodavanje auth headera na sve zahtjeve prema nasem API-ju
        request: (config) => {
            if(config.url.indexOf(ENV.API_URL) === 0 && jwtService.getToken()) {
                config.headers.Authorization = 'Bearer ' + jwtService.getToken();
            }
            return config;
        },

        responseError: (rejection) => {
            if (rejection.status === 401) {
                // token vise nije validan 
                jwtService.destroyToken();     
                $state.go('home');
            }
            else if ( rejection.status === 403 || rejection.status ===-1) {
                $state.go('home');
            }
            
            swalService.error(rejection.data.error, rejection.data.message);

            return $q.reject(rejection);
        }
    }
}

export default authInterceptor;