class LocationTypesController {
	static $inject = ['locationTypeService'];

	constructor(locationTypeService) {
		this.locationTypeService = locationTypeService;
		this.loadLocationTypes(1);
		this.setEmptyLocationType();
	}

	registerLocationType() {
		this.locationTypeService.create(this.locationType).then((response) => {
			console.log("Added a Location Type!");
			//this.locationTypes.push(response.data);
        	this.loadLocationTypes(1);
			this.setEmptyLocationType();
		}, (error) => {
			console.log("Error while creating a Location Type.");
		});
	}

	setEmptyLocationType() {
		this.locationType = {name: "", description: ""};
	}

	loadLocationTypes(page) {
        this.locationTypeService.getPage(page).then((response) => {
            this.locationTypes = response.data.content;
            this.number = response.data.number+1;
            this.totalPages = response.data.totalPages;
    	});
    }

     loadAllLocationTypes() {
        this.locationTypeService.all().then(response => {
            this.locationTypes = response.data;
        });
    }

    goto(newPage) {
		if (newPage > 0 && newPage <= this.totalPages) {
			this.loadLocationTypes(newPage);
		}
	}

	delete(id) {
 		if (confirm('Da li ste sigurni da želite obrisati tip sale?')) {
 			this.locationTypeService.delete(id).then((response) => {
				 if (this.locationTypes.length > 1) {
					this.loadLocationTypes(this.number);
				} else if (this.totalPages > 1) {
					// ako se obrise entitet koji je zadnji na stranici onda ucitaj prethodnu stranicu
					this.loadLocationTypes(this.number - 1);
				 }
				 else {
					 this.locationTypes = [];
				 }
 			});
 		}
 	}
}

export default LocationTypesController;
