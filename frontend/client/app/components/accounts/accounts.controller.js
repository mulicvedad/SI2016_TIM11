class AccountsController {
	static $inject = ['accountService'];

	constructor(accountService) {
		this.accountService = accountService;
		this.loadAccounts(1);
		this.setEmptyAccount();
	}

	registerAccount() {
		if (!this.form.$valid) {
			return;
		}

		this.accountService.create(this.account).then(response => {
			this.loadAccounts(1);
			this.resetForm();
		}, error => {});
	}

	setEmptyAccount() {
		this.account = {fullName: "", username: "", email: "", password: "", roleId: 1};
	}

	loadAccounts(page) {
		this.accountService.getPage(page).then(response => {
			this.accounts = response.data.content;
			this.number = response.data.number + 1;
			this.totalPages = response.data.totalPages;
		});
	}

 	loadAllAccounts() {
        this.accountService.all().then(response => {
            this.allAccounts = response.data;
        });
    }

	goto(newPage) {
		if (newPage > 0 && newPage <= this.totalPages) {
			this.loadAccounts(newPage);
		}
	}

	edit(id) {}

	delete(id) {
		if (confirm('Da li ste sigurni da želite obrisati korisnički račun?')) {
			this.accountService.delete(id).then(response => {
				if (this.accounts.count > 0) {
					this.loadAccounts(this.number);
				 }
				 else if (this.number > 0) {
					// ako se obrise entitet koji je zadnji na stranici onda ucitaj prethodnu stranicu
					this.loadAccounts(this.number - 1);
				 }
				 else {
					 this.accounts = [];
				 }
			});
		}
	}

	resetForm() {
		this.form.$setPristine();
		this.form.$setUntouched();
		this.form.$submitted = false;
		this.setEmptyAccount();
	}
}

export default AccountsController;
