export default class JwtService {
    static $inject = ['$window'];

    constructor($window){
        this.$window = $window;
    }

    saveToken(jwt){
        console.log('Session service ');
        this.$window.localStorage.setItem('user.jwt', jwt);
    }

    getToken(){
        return this.$window.localStorage.getItem('user.jwt');
    }

    destroyToken(){
        this.$window.localStorage.setItem('user.jwt', null);
    }
}