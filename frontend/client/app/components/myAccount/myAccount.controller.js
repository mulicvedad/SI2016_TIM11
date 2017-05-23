class MyAccountController {
    static $inject = ['myAccountService'];

    constructor(myAccountService) {
        this.name = 'myAccount';

        this.myAccountService = myAccountService;

        this.loadMyAccount();
    }

    loadMyAccount() {
        this.myAccountService.current().then(response => {
            this.myAccount = response.data;
        });
    }

    save() {
        if (!this.form.$valid) {
            return;
        }

        let data = {
            newPassword: this.myAccount.newPassword,
            currentPassword: this.myAccount.currentPassword
        };

        this.myAccountService.update(data).then(response => {
            swal({
                title: 'Bravo!',
                text: 'Novi podaci koje ste unijeli su sačuvani.',
                type: 'success'
            });
        });
    }
}

export default MyAccountController;
