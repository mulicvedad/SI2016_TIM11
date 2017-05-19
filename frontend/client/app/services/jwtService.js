export default class JwtService {
    static $inject = ['$window'];

    constructor($window){
        this.$window = $window;
    }

    saveToken(jwt){
        this.$window.localStorage.setItem('user.jwt', JSON.stringify(jwt));
    }

    getToken(){
        return JSON.parse(this.$window.localStorage.getItem('user')).jwt;
    }

    destroyToken(){
        this.$window.localStorage.setItem('user.jwt', null);
    }
}