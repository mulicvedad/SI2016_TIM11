class LoginController {
    static $inject = ['accountService', '$state', 'sessionService'];
    static br = 0;
    static br2 = 0;
    constructor(accountService, $state, sessionService){
        this.accountService = accountService;
        this.$state = $state;
        this.sessionService = sessionService;
    }

    login(){
        this.accountService.login(this.user).then((response) => {
            this.loginSuccess(response.content);
        },
        (error) => {
            this.loginFailure(error);
        }
        );
    }

    loginSuccess(responseContent){
        this.loginError = '';
        this.sessionService.startSession(responseContent);
        this.$state.go('home');
    }

    loginFailure(errors){
        //tmp
        this.loginError=JSON.stringify(errors);
    }
}

export default LoginController;