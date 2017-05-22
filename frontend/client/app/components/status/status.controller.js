class StatusController {
	static $inject = ['statusService'];

	constructor(statusService) {
		this.statusService = statusService;
		this.loadStatus(1);
		this.setEmptyStatus();
	}


	registerStatus() {
		 if (!this.form.$valid) {
            return;
        }

		this.statusService.create(this.status).then((response) => {
			console.log("Added a status!");
			this.status.push(response.data);
        	this.loadStatus(1);
			this.setEmptyStatus();
		}, (error) => {
			console.log("Error while creating a status.");
		});
	}

	setEmptyStatus() {
		this.status = {name: ""};
	}

	/*loadStatus() {
		this.statusService.all().then( (response) => {
			this.status = response.data;
			// radi
		} );
	}*/

    loadStatus(page) {
        this.statusService.getPage(page).then( (response) => {
            this.status = response.data.content;
        this.number = response.data.number+1;
        this.totalPages = new Array(response.data.totalPages);
        for(var i = 0; i< response.data.totalPages; i++) {
            this.totalPages[i] = i + 1;
        }
    } );
    }

    goto(newPage)
    {
        this.loadStatus(newPage);
    }

      delete(id) {
		if (confirm('Da li ste sigurni da želite obrisati status?')) {
			this.statusService.delete(id).then(response => {
				this.loadStatus(this.number);
                this.loadAllStatus();
			});
		}
	}

}

export default StatusController;
