class ItemsCRDController {
    static $inject = ['itemCRDService', 'categoryService', 'locationService'];
    constructor(itemsCRDService, categoryService, locationService) {
        this.itemsCRDService = itemsCRDService;
        this.categoryService = categoryService;
        this.locationService = locationService;
        this.loadItemsCRD();
        this.setEmptyItemCRD();
        this.loadCategories();
        this.loadLocations();
    }
    registerItemCRD() {
        this.itemsCRDService.create(this.itemCRD).then((response) => {
            console.log("Added an item!");
            this.itemsCRD.push(response.data);
            this.setEmptyItemCRD();
        }, (error) => {
            console.log(error);
            console.log("Error while creating an item.");
        });
    }
    setEmptyItemCRD() {
        this.itemCRD = {id: "null", skuNumber: "", name: "", unitOfMeasurement: "", purchasedBy: "", personResponsible: "", dateOfPurchase: "", value: "" , categoryID: "", locationID: ""};
    }
    loadItemsCRD() {
        this.itemsCRDService.all().then((response) => {
            this.itemsCRD = response.data;
        });
    }
    loadCategories() {
        this.categoryService.all().then((response) => {
            this.categories = response.data;
        });
    }

    loadLocations() {
		this.locationService.all().then( (response) => {
			this.locations = response.data;
		} );
	}

}
export default ItemsCRDController;