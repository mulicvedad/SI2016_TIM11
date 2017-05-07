class StatusController {
	static $inject = ['statusService'];

	constructor(statusService) {
		this.statusService = statusService;
		this.loadStatus();
		this.setEmptyStatus();
	}


	registerStatus() {
		this.statusService.create(this._status).then((response) => {
			console.log("Added a status!");
			this.status.push(response.data);
			this.setEmptyLocation();
		}, (error) => {
			console.log("Error while creating a status.");
		});
	}

	setEmptyStatus() {
		this.status = {name: ""};
	}

	loadStatus() {
		this.statusService.all().then( (response) => {
			this.status = response.data;
			// radi
		} );
	}

}

export default StatusController;
