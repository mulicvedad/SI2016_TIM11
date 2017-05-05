class LocationsController {
	static $inject = ['locationsService'];

	constructor(locationService) {
		this.locationService = locationService;
		this.loadLocations();
		this.setEmptyLocation();
		
	}

	registerLocation() {
		this.locationService.create(this.location).then( (response) => {
			console.log("Added an Location!");
			this.locations.push(response.data);
			this.setEmptyLocation();
		}, (error) => {
			console.log("Error while creating an Location.");
		});
	}

	setEmptyLocation() {
		this.location = {name: "", parentId: "", typeId: ""};
	}

	loadLocations() {
		this.locationService.all().then( (response) => {
			this.locations = response.data;
		} );
	}

}

export default LocationsController;
