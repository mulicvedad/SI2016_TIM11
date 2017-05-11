class StatusController {
	static $inject = ['statusService'];

	constructor(statusService) {
		this.statusService = statusService;
		this.loadStatus(1);
		this.setEmptyStatus();
	}


	registerStatus() {
		this.statusService.create(this._status).then((response) => {
			console.log("Added a status!");
			this.status.push(response.data);
        	this.loadStatus(1);
			this.setEmptyLocation();
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

}

export default StatusController;
