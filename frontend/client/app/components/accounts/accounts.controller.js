class AccountsController {
	static $inject = ['accountService'];

	constructor(accountService) {
		this.accountService = accountService;
		this.loadAccounts(1);
		this.setEmptyAccount();

	}

	registerAccount() {
		this.accountService.create(this.account).then( (response) => {
			console.log("Added an account!");
			this.accounts.push(response.data);
			this.loadAccounts(1);
			this.setEmptyAccount();
		}, (error) => {
			console.log("Error while creating an account.");
		});
	}

	setEmptyAccount() {
		this.account = {fullName: "", username: "", email: "", password: "", roleId: 1};
	}

	/*loadAccounts() {
		this.accountService.all().then( (response) => {
			this.accounts = response.data;
		} );
	}*/

	loadAccounts(page) {
			this.accountService.getPage(page).then( (response) => {
					this.accounts = response.data.content;
					this.number = response.data.number+1;
					this.totalPages = new Array(response.data.totalPages);
					for(var i = 0; i< response.data.totalPages; i++)
		{
			this.totalPages[i]=i+1;
		}
			console.log(response.data);
	} );
	}

	goto(newPage)
	{
		this.loadAccounts(newPage);
	}

	

}

export default AccountsController;
