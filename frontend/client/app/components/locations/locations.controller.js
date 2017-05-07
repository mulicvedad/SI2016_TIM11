class LocationsController {
	static $inject = ['locationService', 'locationTypeService'];

	constructor(locationService, locationTypeService) {
		this.locationService = locationService;
		this.locationTypeService = locationTypeService;
		this.loadLocations();
        this.loadLocationTypes();
		this.setEmptyLocation();
		
	}

	registerLocation() {
		this.locationService.create(this.location).then( (response) => {
			console.log("Added a Location!");
			this.locations.push(response.data);
			this.setEmptyLocation();
		}, (error) => {
			console.log("Error while creating a Location.");
		});
	}

	setEmptyLocation() {
		this.location = {name: "", parentId: "null", typeId: ""};
	}

	loadLocations() {
		this.locationService.all().then( (response) => {
			this.locations = response.data;
		} );
	}

    loadLocationTypes() {
		this.locationTypeService.all().then( (response) => {
			this.locationTypes = response.data;
		} );
	}

}

export default LocationsController;
