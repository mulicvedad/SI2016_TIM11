class ItemsController {
	static $inject = ['itemsService'];

	constructor(itemsService) {
		this.itemsService = itemsService;
		this.loadItems();
		this.setEmptyItem();
		
	}

	registerItem() {
		this.itemService.create(this.item).then( (response) => {
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

	loadItem() {
		this.itemService.all().then( (response) => {
			this.items = response.data;
		} );
	}

}

export default ItemsController;
