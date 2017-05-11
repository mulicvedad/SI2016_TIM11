class LocationTypesController {
  static $inject = ['locationTypeService'];

	constructor(locationTypeService) {
		this.locationTypeService = locationTypeService;
		this.loadLocationTypes(1);
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



   /*loadLocationTypes() {
        this.locationTypeService.all().then( (response) => {
            this.locationTypes = response.data;
        console.log(response.data);
    } );
    }*/

    loadLocationTypes(page) {
        this.locationTypeService.getPage(page).then( (response) => {
            this.locationTypes = response.data.content;
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
        this.loadLocationTypes(newPage);
	}

    deleteLocationType(index) {
    //.....
  }
}

export default LocationTypesController;
