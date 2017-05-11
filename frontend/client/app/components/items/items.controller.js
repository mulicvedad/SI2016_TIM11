class ItemsController {
    static $inject = ['itemService'];
    constructor(itemsService) {
        this.itemsService = itemsService;
        this.loadItems();
        this.setEmptyItem();
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
}
export default ItemsController;