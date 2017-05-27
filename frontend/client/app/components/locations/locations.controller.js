class LocationsController {
	static $inject = ['locationService', 'locationTypeService', 'swalService'];

	constructor(locationService, locationTypeService, swalService) {
		this.locationService = locationService;
		this.locationTypeService = locationTypeService;
		this.swalService = swalService;

        // Filters are disabled at first
        this.searchText = '';

        this.setEmptyLocation();

        this.locationTypeService.all().then(response => {
            this.locationTypes = response.data;

            this.loadLocations();
            this.loadAllLocations();
            this.loadAllLocationTypes();
        });
	}

	refresh() {
        if (this.searchText) {
            this.filter();
        } else {
            this.loadLocations();
        }
    }

    saveLocation(){
    	if (!this.form.$valid) {
            return;
        }

        /*this.location.parentId = this.location.parent.id;*/
       /*7 this.location.typeId = this.location.type.id;*/

        if (this.location.id) {
            this.updateLocation();
        } else {
            this.createLocation();
        }
    }
	
	loadLocations(page = 1) {
		this.locationService.getPage(page).then((response) => {
			this.locations = response.data.content;
			this.number = response.data.number+1;
			this.totalPages = response.data.totalPages;
		});
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

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyLocation();
    }

    setEmptyLocation() {
		this.location = {
			name: '', 
			parentId: null, 
			typeId: ''};
	}

	createLocation() {
        this.locationService.create(this.location).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Nova sala je uspješno kreirana.');
        }, error => {});
    }

    updateLocation() {
        this.locationService.update(this.location.id, this.location).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Izmjene su uspješno sačuvane.');
        }, error => {});
    }

    edit(id) {
        this.locationService.find(id).then(response => {
            this.location = {
                id: response.data.id,
                name: response.data.name,
                parentId: response.data.parent,
                typeId: response.data.type
            };

            this.openModal();
        });
    }

    delete(id) {
        this.swalService.areYouSure('Obrisana sala se ne može vratiti.', () => {
            this.locationService.delete(id).then(response => {
                this.refresh();

                this.swalService.success('Sala je uspješno obrisana.');
            });
        });
    }

    closeModal() {
        $('#location-modal').modal('close');
    }

    openModal() {
        $('#location-modal').modal({
            complete: () => this.resetForm(),
            ready: (modal, trigger) => Materialize.updateTextFields()
        }).modal('open');

    }

    goto(newPage) {
        if (newPage > 0 && newPage <= this.totalPages) {
            this.loadLocations(newPage);
		}
    }

    filter() {
        this.locationService.filterByName(this.searchText).then(response => {
            this.locations = response.data;
        });
    }

    getLocationName(locationID) {
        let location = this.allLocations.find(location=> location.id === locationID);

        return location ? location.name : null;
    }

	loadLocationTypes() {
		this.locationTypeService.all().then( (response) => {
			this.locationTypes = response.data;
		} );
	}
	  
}

export default LocationsController;
