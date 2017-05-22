class ItemsController {
    static $inject = ['itemService', 'locationService' , 'categoryService'];
    
    constructor(itemService, locationService, categoryService) {
        this.itemService = itemService;
        this.locationService = locationService;
        this.categoryService = categoryService;
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
        this.loadItems(newPage);
    }
    
    registerItem() {
        this.itemService.create(this.items).then((response) => {
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

    filter() {
        this.itemService.getByFilter(this.searchText).then(response => {
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