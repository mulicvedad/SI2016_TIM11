class StatusController {
	static $inject = ['statusService'];

	constructor(statusService) {
		this.statusService = statusService;
		this.loadStatuses(1);
		this.setEmptyStatus();
	}


	registerStatus() {
		 if (!this.form.$valid) {
            return;
        }

		this.statusService.create(this.status).then((response) => {
        	this.loadStatuses(1);
			this.resetForm();
		}, (error) => {
			console.log("Error while creating a status.");
		});
	}

	setEmptyStatus() {
		this.status = {name: ""};
	}

    loadStatuses(page) {
        this.statusService.getPage(page).then((response) => {
            this.statuses = response.data.content;
        	this.number = response.data.number+1;
        	this.totalPages = response.data.totalPages;
    	});
    }

    goto(newPage) {
		if (newPage > 0 && newPage <= this.totalPages) {
        	this.loadStatuses(newPage);
		}
    }

    delete(id) {
		if (confirm('Da li ste sigurni da želite obrisati status?')) {
			this.statusService.delete(id).then((response) => {
				if (this.statuses.length > 1) {
					this.loadStatuses(this.number);
				} else if (this.totalPages > 1) {
					// ako se obrise entitet koji je zadnji na stranici onda ucitaj prethodnu stranicu
					this.loadStatuses(this.number - 1);
				 }
				 else {
					 this.statuses = [];
				 }
			});
		}
	}

	resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyStatus();
    }

}

export default StatusController;
