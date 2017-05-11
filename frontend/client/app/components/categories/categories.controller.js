class CategoriesController {
	static $inject = ['categoryService'];

	constructor(categoryService) {
		this.categoryService = categoryService;
		this.loadCategories();
		this.setEmptyCategory();
		
	}

	registerCategory() {
		this.categoryService.create(this.category).then( (response) => {
			console.log("Added a category!");
			this.categories.push(response.data);
			this.setEmptyLocation();
		}, (error) => {
			console.log("Error while creating a category.");
		});
	}

	setEmptyCategory() {
		this.category = {name: "", parentId: "null"};
	}

	loadCategories() {
		this.categoryService.all().then( (response) => {
			this.categories = response.data;
		} );
	}

}

export default CategoriesController;
