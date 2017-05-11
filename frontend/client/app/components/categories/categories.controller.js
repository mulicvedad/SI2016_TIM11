class CategoriesController {
	static $inject = ['categoryService'];

	constructor(categoryService) {
		this.categoryService = categoryService;
		this.loadCategories(1);
		this.setEmptyCategory();
		
	}

	registerCategory() {
		this.categoryService.create(this.category).then( (response) => {
			console.log("Added a category!");
			this.categories.push(response.data);
        	this.loadCategories(1);
			this.setEmptyLocation();

		}, (error) => {
			console.log("Error while creating a category.");
		});
	}

	setEmptyCategory() {
		this.category = {name: "", parentId: "null"};
	}

	/*loadCategories() {
		this.categoryService.all().then( (response) => {
			this.categories = response.data;
			// radi
		} );
	}*/

    loadCategories(page) {
        this.categoryService.getPage(page).then( (response) => {
            this.categories = response.data.content;
        this.number = response.data.number+1;
        this.totalPages = new Array(response.data.totalPages);
        for(var i = 0; i< response.data.totalPages; i++)
        {
            this.totalPages[i]=i+1;
        }
        console.log(response.data);
    } );
    }

    goto(newPage)
    {
        this.loadCategories(newPage);
    }

}

export default CategoriesController;
