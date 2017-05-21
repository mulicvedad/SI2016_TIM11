class ItemsController {
    static $inject = ['itemService', 'locationService' , 'categoryService','itemCRDService'];
    
    constructor(itemService,locationService,categoryService,itemsCRDService) {
        this.itemService = itemService;
        this.locationService = locationService;
        this.categoryService = categoryService;
	    this.loadCategories();
        this.loadItems();
        this.loadLocations();
        this.loadAllItems();
        this.setEmptyItem();
        
    }
    
    loadLocations() {
		this.locationService.all().then( (response) => {
			this.locations = response.data;
		} );
    }
    
    loadCategories(){
        this.categoryService.all().then( (response) => {
			this.categories = response.data;
		} );
    }
    
    registerItem() {
        this.itemService.create(this.item).then((response) => {
            console.log("Added an item!");
            this.item.push(response.data);
            this.setEmptyItem();
        }, (error) => {
            console.log("Error while creating an item.");
        });
    }
    setEmptyItem() {
        //to be implemented
    }
    loadItems() {
        this.itemService.all().then((response) => {
            this.items = response.data;
        });
    }

    loadAllItems() {
        this.itemService.all().then(response => {
            this.allItems = response.data;
        });
    }

    filter(searchedText) {
    this.itemService.getByFilter(searchedText).then((response) => {
           this.items = response.data;
        });
    }
    
    shouldHide(date){
        // funkcija nije gotova treba jos dodati logiku za sakrivanje delete ikone
        return date == 1495292400000 ? true : false;
    }
    
    delete(id) {
		if (confirm('Da li ste sigurni da želite obrisati inventurnu stavku?')) {
			this.itemService.delete(id).then(response => {
				this.loadItems(this.number);
			});
		}
    }

  
    
}
export default ItemsController;