class ItemsController {
    static $inject = ['itemService', 'locationService' , 'categoryService'];
    
    constructor(itemsService,locationService,categoryService) {
        this.itemsService = itemsService;
        this.locationService = locationService;
        this.categoryService = categoryService;
	this.loadCategories();
        this.loadItems();
        this.loadLocations();
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
            this.items.push(response.data);
            this.setEmptyItem();
        }, (error) => {
            console.log("Error while creating an item.");
        });
    }
    setEmptyItem() {
        //to be implemented
    }
    loadItems() {
        this.itemsService.all().then((response) => {
            this.items = response.data;
        });
    }

    filter(searchedText) {
    this.itemsService.getByFilter(searchedText).then((response) => {
           this.items = response.data;
        });
    }
    
    shouldHide(date){
        // funkcija nije gotova treba jos dodati logiku za sakrivanje delete ikone
        return date == 1495292400000 ? true : false;
    }
    
    delete(id) {
		if (confirm('Da li ste sigurni da želite obrisati inventurnu stavku?')) {
			this.itemsService.delete(id).then(response => {
				this.loadItems(this.number);
			});
		}
    }
    
}
export default ItemsController;