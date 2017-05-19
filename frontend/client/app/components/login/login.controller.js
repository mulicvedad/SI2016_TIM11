class LoginController {
    static $inject = ['accountService', '$state', 'sessionService'];

    constructor(accountService, $state, sessionService) {
        this.accountService = accountService;
        this.$state = $state;
        this.sessionService = sessionService;
    }

    login() {
        this.accountService.login(this.user).then((response) => {
            this.loginSuccess(response.data);
        }, (error) => {
            this.loginFailure(error);
        });
    }

    loginSuccess(responseContent){
        this.loginError = '';
        console.log("odgovor tebra: " + JSON.stringify(responseContent));
        this.sessionService.startSession(responseContent);
        this.$state.go('home');
    }

    loginFailure(errors) {
        // treba dodati jednostavni prikaz errora
        this.loginError = JSON.stringify(errors);
        console.log(JSON.stringify(error));
    }
}

export default LoginController;