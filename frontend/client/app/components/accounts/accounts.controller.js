class AccountsController {
	static $inject = ['accountService'];

	constructor(accountService) {
		this.accountService = accountService;
		this.loadAccounts();
		this.setEmptyAccount();
		
	}

	registerAccount() {
		this.accountService.create(this.account).then( (response) => {
			console.log("Added an account!");
			this.accounts.push(response.data);
			this.clearNewAccount();
		}, (error) => {
			console.log("Error while creating an account.");
		});
	}

	setEmptyAccount() {
		this.account = {fullName: "", username: "", email: "", password: "", roleId: 1};
	}

	loadAccounts() {
		this.accountService.all().then( (response) => {
			this.accounts = response.data;
		} );
	}

}

export default AccountsController;
