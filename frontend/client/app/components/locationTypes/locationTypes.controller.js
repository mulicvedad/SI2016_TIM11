class LocationTypesController {
  static $inject = ['locationTypeService'];

	constructor(locationTypeService) {
		this.locationTypeService = locationTypeService;
		this.loadLocationTypes();
		this.setEmptyLocationType();

	}

	registerLocationType() {
		this.locationTypeService.create(this.locationType).then( (response) => {
			console.log("Added a Location Type!");
			this.locationTypes.push(response.data);
			this.setEmptyLocationType();
		}, (error) => {
			console.log("Error while creating a Location Type.");
		});
	}

	setEmptyLocationType() {
		this.locationType = {name: "", description: ""};
	}

	loadLocationTypes() {
		this.locationTypeService.all().then( (response) => {
			this.locationTypes = response.data;
		} );
	}

  deleteLocationType(index) {
    //...
  }
}

export default LocationTypesController;
