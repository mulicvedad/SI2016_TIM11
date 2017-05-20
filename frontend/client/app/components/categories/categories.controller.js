class CategoriesController {
	static $inject = ['categoryService'];

	constructor(categoryService) {
		this.categoryService = categoryService;
		this.loadCategories(1);
        this.loadAllCategories();
		this.setEmptyCategory();
	}

	registerCategory() {
        if (!this.form.$valid) {
            return;
        }

		this.categoryService.create(this.category).then(response => {
			this.categories.push(response.data);
        	this.loadCategories(1);
            this.loadAllCategories();
			this.resetForm();
		}, error => {});
	}

	setEmptyCategory() {
		this.category = {name: '', parentId: null};
	}

    loadCategories(page) {
        this.categoryService.getPage(page).then(response => {
            this.categories = response.data.content;
            this.number = response.data.number+1;
            this.totalPages = response.data.totalPages;
        });
    }

    loadAllCategories() {
        this.categoryService.all().then(response => {
            this.allCategories = response.data;
        });
    }

    goto(newPage) {
        this.loadCategories(newPage);
    }

    delete(id) {
		if (confirm('Da li ste sigurni da želite obrisati kategoriju?')) {
			this.categoryService.delete(id).then(response => {
				this.loadCategories(this.number);
			});
		}
	}

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyCategory();
    }
}

export default CategoriesController;
