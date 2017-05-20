class LoginController {
    static $inject = ["accountService", "$state", "sessionService"];

    constructor(accountService, $state, sessionService) {
        this.accountService = accountService;
        this.$state = $state;
        this.sessionService = sessionService;
        this.error = "";
        this.user = {};
    }

    login() {
        if (!this.validateFormData()) {
            return;
        } 
        this.accountService.login(this.user).then((response) => {
            this.loginSuccess(response.data);
        }, (error) => {
            this.loginFailure(error);
        });
    }

    loginSuccess(responseContent){
        this.loginError = "";
        this.sessionService.startSession(responseContent);
        this.$state.go("home");
    }

    loginFailure(response) {
        if (response.status == "401") {
            this.error = "Netačno korisničko ime i/ili lozinka."
        }
        else {
            this.error = "Greška na serveru: \n" + response.data.error 
                + "\nOpis: " + response.data.message;
        }
    }

    validateFormData() {
        this.error = "";
        if (this.user.username.length < 3 || !this.user.username.length > 16) {
            this.error = "Korisničko ime može imati najmanje 3 a najviše 16 znakova.\n";
        }
        if (this.user.password.length < 8) {
            this.error += "Lozinka mora imati bar 8 znakova.";
        }

        return this.error == "" ? true : false;
    }
}

export default LoginController;