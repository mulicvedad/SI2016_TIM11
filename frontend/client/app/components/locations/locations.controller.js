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
	
	loadLocations(page) {
		this.locationService.getPage(page).then((response) => {
			this.locations = response.data.content;
			this.number = response.data.number+1;
			this.totalPages = response.data.totalPages;
		});
	}
	
	
	goto(newPage) {
		if (newPage > 0 && newPage <= this.totalPages) {
			this.loadLocations(newPage);
		}
	}

	delete(id) {
		if (confirm('Da li ste sigurni da želite obrisati salu?')) {
			this.locationService.delete(id).then(response => {
				this.loadAllLocations();
				if (this.locations.count > 0) {
					this.loadLocations(this.number);
				 }
				 else if (this.number > 0) {
					// ako se obrise entitet koji je zadnji na stranici onda ucitaj prethodnu stranicu
					this.loadLocations(this.number - 1);
				 }
				 else {
					 this.locations = [];
				 }
			});
		}
	}

}

export default LocationsController;
