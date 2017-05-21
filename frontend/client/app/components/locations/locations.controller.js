class LocationsController {
	static $inject = ['locationService', 'locationTypeService'];

	constructor(locationService, locationTypeService) {
		this.locationService = locationService;
		this.locationTypeService = locationTypeService;
		this.loadLocations(1);
		this.loadAllLocations();
        this.loadLocationTypes();
		this.setEmptyLocation();

	}

	registerLocation() {
		this.locationService.create(this.location).then( (response) => {
			console.log("Added a Location!");
			this.locations.push(response.data);
			this.loadLocations(1);
			this.setEmptyLocation();
		}, (error) => {
			console.log("Error while creating a Location.");
		});
	}

	setEmptyLocation() {
		this.location = {name: "", parentId: "null", typeId: ""};
	}

	/*loadLocations() {
		this.locationService.all().then( (response) => {
			this.locations = response.data;
		} );
	}*/

	loadAllLocations() {
        this.locationService.all().then(response => {
            this.allLocations = response.data;
        });
    }

    loadLocationTypes() {
		this.locationTypeService.all().then( (response) => {
			this.locationTypes = response.data;
		} );
	}


	delete(id) {
		if (confirm('Da li ste sigurni da želite obrisati salu?')) {
			this.locationService.delete(id).then(response => {
				this.loadLocations(this.number);
			});
		}
	}

	loadLocations(page) {
			this.locationService.getPage(page).then( (response) => {
					this.locations = response.data.content;
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
			this.loadLocations(newPage);
}

}

export default LocationsController;
