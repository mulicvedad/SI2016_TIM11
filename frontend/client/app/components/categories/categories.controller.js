class CategoriesController {
	static $inject = ['categoryService', 'swalService'];

	constructor(categoryService, swalService) {
		this.categoryService = categoryService;
        this.swalService = swalService;

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

            this.swalService.success('Nova kategorija je uspješno kreirana.');
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
        if (newPage > 0 && newPage <= this.totalPages) {
            this.loadCategories(newPage);
		}
    }

    delete(id) {
        this.swalService.areYouSure('Obrisana kategorija se ne može vratiti.', () => {
			this.categoryService.delete(id).then(response => {
               if (this.categories.length > 1) {
					this.loadCategories(this.number);
                    this.loadAllCategories();
				} else if (this.totalPages > 1) {
                    this.goto(this.number - 1);
                    this.loadAllCategories();
                } else {
                    this.categories = [];
                    this.allCategories = [];
                }
			});
		});
	}

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyCategory();
    }
}

export default CategoriesController;
