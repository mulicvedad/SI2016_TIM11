class CategoriesController {
	static $inject = ['categoryService', 'swalService'];

	constructor(categoryService, swalService) {
		this.categoryService = categoryService;
        this.swalService = swalService;

		this.refresh();
        this.setEmptyCategory();
	}

    refresh() {
        if (this.searchText) {
            this.filter();
        } else {
            this.loadCategories();
            this.loadAllCategories();
        }
    }

    loadCategories(page = 1) {
        this.categoryService.getPage(page).then(response => {
            this.categories = response.data.content;
            this.number = response.data.number + 1;
            this.totalPages = response.data.totalPages;
        });
    }

    loadAllCategories() {
        this.categoryService.all().then(response => {
            this.allCategories = response.data;
        });
    }

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyCategory();
    }

    setEmptyCategory() {
        this.category = {
            id: null,
            name: '',
            parentId: null
        };
    }

	saveCategory() {
        if (!this.form.$valid) {
            return;
        }

        if (this.category.id) {
            this.updateCategory();
        } else {
            this.createCategory();
        }
	}

    createCategory() {
        this.categoryService.create(this.category).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Nova kategorija je uspješno kreirana.');
        }, error => {});
    }

    updateCategory() {
        this.categoryService.update(this.category.id, this.category).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Izmjene su uspješno sačuvane.');
        }, error => {});
    }

    edit(id) {
        this.categoryService.find(id).then(response => {
            this.category = response.data;
            this.openModal();
        });
    }

    delete(id) {
        this.swalService.areYouSure('Obrisana kategorija se ne može vratiti.', () => {
            this.categoryService.delete(id).then(response => {
                this.refresh();
            });
        });
    }

    closeModal() {
        $('#category-modal').modal('close');
    }

    openModal() {
        $('#category-modal').modal({
            complete: () => this.resetForm()
        }).modal('open');
    }

    goto(newPage) {
        if (newPage > 0 && newPage <= this.totalPages) {
            this.loadCategories(newPage);
		}
    }

    filter() {
        if (!this.searchText) {
            this.loadCategories();
        } else {
            this.categoryService.filterByName(this.searchText).then(response => {
                this.categories = response.data;
            });
        }
    }
}

export default CategoriesController;
