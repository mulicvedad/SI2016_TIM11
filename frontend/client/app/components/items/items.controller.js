class ItemsController {
    static $inject = ['itemService', 'locationService' , 'categoryService', 'swalService'];
    
    constructor(itemService, locationService, categoryService, swalService) {
        this.itemService = itemService;
        this.locationService = locationService;
        this.categoryService = categoryService;
        this.swalService = swalService;

        // Filters are disabled at first
        this.searchText = '';

	    this.loadCategories();
        this.loadLocations();
        this.loadItems(1);
        this.setEmptyItem();
    }
    
    refresh() {
        if (this.searchText) {
            this.filter();
        } else {
            this.load();
        }
    }

    loadLocations() {
		this.locationService.all().then(response => {
			this.locations = response.data;
		});
    }
    
    loadCategories() {
        this.categoryService.all().then(response => {
			this.categories = response.data;
		});
    }

    loadItems(page) {
        this.itemService.getPage(page).then(response => {
            this.items = response.data.content;  
            this.number = response.data.number + 1;
            this.totalPages = response.data.totalPages;
        });
    }

    goto(newPage) {
        if (newPage > 0 && newPage <= this.totalPages) {
            this.loadItems(newPage);
		}
    }

    saveItem() {
        if (!this.form.$valid) {
            return;
        }

        if (this.item.id) {
            this.updateItem();
        } else {
            this.registerItem();
        }
    }
    
    registerItem() {
        if (!this.form.$valid) {
            return;
        }

        this.itemService.create(this.item).then(response => {
            this.items.push(response.data); 
            this.setEmptyItem();

            this.swalService.success('Nova inventurna stavka je uspješno kreirana.');
            this.closeModal();
        }, error => {});
    }

     updateItem() {
        this.itemService.update(this.item.id, this.item).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Izmjene su uspješno sačuvane.');
        }, error => {});
    }

    setEmptyItem() {
        this.item = {
            skuNumber: '',
            name: '',
            purchasedBy: '',
            personResponsible: '',
            value: '',
            dateOfPurchase: '',
            unitOfMeasurement: '',
            categoryID: null,
            locationID: null
        };
    }

    edit(id) {
        this.itemService.find(id).then(response => {
           
            this.item = {
                id: response.data.id,
                skuNumber: response.data.skuNumber,
                name: response.data.name,
                purchasedBy: response.data.purchasedBy,
                personResponsible: response.data.personResponsible,
                value: response.data.value,
                dateOfPurchase: response.data.dateOfPurchase,
                unitOfMeasurement: response.data.unitOfMeasurement,
                categoryID: response.data.category,
                locationID: response.data.location
               
            };

            this.openModal();
        });
    }

    filter() {
        this.itemService.getByFilter(this.searchText).then(response => {
            this.item = response.data;
        });
    }
    
    delete(id) {
        this.swalService.confirm('Obrisana inventurna stavka se ne može vratiti.', () => {
			this.itemService.delete(id).then(response => {
                if (this.item.length > 1) {
					this.loadItems(this.number);
				} else if (this.totalPages > 1) {
                    this.goto(this.number - 1);
                } else {
                    this.item = [];
                }
			});
		});
    }   

    filter() {
        this.itemService.filterByName(this.searchText).then(response => {
            this.item = response.data;
        });
    }

    closeModal() {
        $('#item-modal').modal('close');
    }

    openModal() {
        $('#item-modal').modal({
            complete: () => this.resetForm()
        }).modal('open');
    }

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.setEmptyItem();
    }

    


}
export default ItemsController;