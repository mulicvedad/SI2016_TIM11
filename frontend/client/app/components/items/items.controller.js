class ItemsController {
    static $inject = ['itemService', 'locationService' , 'categoryService', 'swalService'];
    
    constructor(itemService, locationService, categoryService, swalService) {
        this.itemService = itemService;
        this.locationService = locationService;
        this.categoryService = categoryService;
        this.swalService = swalService;

	    this.loadCategories();
        this.loadLocations();
        this.loadItems(1);
        this.setEmptyItem();
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
    
    registerItem() {
        if (!this.form.$valid) {
            return;
        }

        this.itemService.create(this.item).then(response => {
            this.items.push(response.data);
            this.setEmptyItem();

            this.swalService.success('Nova inventurna stavka je uspješno kreirana.');
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

    filter() {
        this.itemService.getByFilter(this.searchText).then(response => {
            this.items = response.data;
        });
    }
    
    delete(id) {
        this.swalService.confirm('Obrisana inventurna stavka se ne može vratiti.', () => {
			this.itemService.delete(id).then(response => {
                if (this.items.length > 1) {
					this.loadItems(this.number);
				} else if (this.totalPages > 1) {
                    this.goto(this.number - 1);
                } else {
                    this.items = [];
                }
			});
		});
    }   
}
export default ItemsController;