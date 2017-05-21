class MyAccountController {
    static $inject = ['myAccountService'];

    constructor(myAccountService) {
        this.name = 'myAccount';

        this.myAccountService = myAccountService;

        this.loadMyAccount();
    }

    loadMyAccount() {
        this.myAccountService.current().then(response => {
            console.log(response.data);
        });
    }

    save() {}
}

export default MyAccountController;
