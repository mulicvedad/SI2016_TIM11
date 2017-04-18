class AccountsController {
	static $inject = ['accountService'];

	constructor(accountService) {
		this.accountService = accountService;
	}

	loadAccounts() {
		this.accountService.all().then( (response) => {
			this.accounts = response.data;
		} );
	}
}

export default AccountsController;
