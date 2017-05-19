export default class SessionService {
    static $inject = ['$window', '$state'];

    constructor($window, $state){
        this.$window = $window;
        this.currentUser = JSON.parse($window.localStorage.getItem('user'));
        this.$state = $state;
    }

    startSession(authServerResponse){
        this.$window.localStorage.setItem('user', JSON.stringify(authServerResponse));
        this.currentUser = authServerResponse;
    }

    userInfo(){
        return this.currentUser;
    }

    destroySession(){
        this.$window.localStorage.setItem('user', null);
        this.currentUser = null;
        this.$state.go('pocetna');
    }

    isUserLoggedIn(){
        return this.currentUser == null ? false : true;
    }
}