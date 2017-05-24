class CategoriesController {
	static $inject = ['categoryService', 'swalService'];

	constructor(categoryService, swalService) {
		this.categoryService = categoryService;
        this.swalService = swalService;

        // Filters are disabled at first
        this.searchText = '';

		this.load();
        this.setEmptyCategory();
	}

    refresh() {
        if (this.searchText) {
            this.filter();
        } else {
            this.load();
        }
    }

    load(page = 1) {
        this.loadAllCategories().then(response => {
            this.loadCategories(page);
        });
    }

    loadCategories(page = 1) {
        this.categoryService.getPage(page).then(response => {
            this.categories = response.data.content;
            this.number = response.data.number + 1;
            this.totalPages = response.data.totalPages;
        });
    }

    loadAllCategories() {
        return this.categoryService.all().then(response => {
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
            this.category = {
                id: response.data.id,
                name: response.data.name,
                parentId: response.data.parent
            };

            this.openModal();
        });
    }

    delete(id) {
        this.swalService.areYouSure('Obrisana kategorija se ne može vratiti.', () => {
            this.categoryService.delete(id).then(response => {
                this.refresh();

                this.swalService.success('Kategorija je uspješno obrisana.');
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
        this.categoryService.filterByName(this.searchText).then(response => {
            this.categories = response.data;
        });
    }

    getCategoryName(categoryID) {
        let category = this.allCategories.find(category => category.id === categoryID);

        return category ? category.name : null;
    }
}

export default CategoriesController;
