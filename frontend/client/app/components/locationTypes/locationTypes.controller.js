class LocationTypesController {
    static $inject = ['locationTypeService', 'swalService'];

    constructor(locationTypeService, swalService) {
        this.locationTypeService = locationTypeService;
        this.swalService = swalService;
        // Filters are disabled at first
        this.searchText = '';
        this.setEmptyLocationType();

            this.loadLocationTypes();
            this.loadAllLocationTypes();
    }

    setEmptyLocationType() {
        this.locationType = {
            id: null,
            name: '',
            description: ''
        };
    }

    refresh() {
        if (this.searchText) {
            this.filter();
        } else {
            this.loadAllLocationTypes();
        }
    }

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyLocationType();
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

    saveLocationType() {
        if (!this.form.$valid) {
            return;
        }

        if (this.locationType.id) {
            this.updateLocationType();
        } else {
            this.createLocationType();
        }
    }

    createLocationType() {
        this.locationTypeService.create(this.locationType).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Novi tip sale je uspješno kreiran.');
        }, error => {});
    }

    updateLocationType() {
        this.locationTypeService.update(this.locationType.id, this.locationType).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Izmjene su uspješno sačuvane.');
        }, error => {});
    }

    goto(newPage) {
        if (newPage > 0 && newPage <= this.totalPages) {
            this.loadLocationTypes(newPage);
        }
    }

    edit(id) {
        this.locationTypeService.find(id).then(response => {
            this.locationType = {
                id: response.data.id,
                name: response.data.name,
                description: response.data.description
            };

            this.openModal();
        });
    }

    delete(id) {
        this.swalService.areYouSure('Obrisani tip sale se ne može vratiti.', () => {
            this.locationTypeService.delete(id).then(response => {
                this.refresh();

                this.swalService.success('Tip sale je uspješno obrisan.');
            });
        });
    }

     closeModal() {
        $('#locationTypes-modal').modal('close');
    }

    openModal() {
        $('#locationTypes-modal').modal({
            complete: () => this.resetForm()
        }).modal('open');
    }
}

export default LocationTypesController;
