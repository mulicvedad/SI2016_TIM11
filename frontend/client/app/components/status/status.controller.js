class StatusController {
	static $inject = ['statusService', 'swalService'];

	constructor(statusService, swalService) {
        this.statusService = statusService;
        this.swalService = swalService;

        // Filters are disabled at first
        this.searchText = '';

        this.load();
        this.setEmptyStatus();
	}

    refresh() {
        if (this.searchText) {
            this.filter();
        } else {
            this.load();
        }
    }

    load(page = 1) {
        this.loadAllStatuses().then(response => {
            this.loadStatuses(page);
        });
    }

    loadStatuses(page) {
        this.statusService.getPage(page).then((response) => {
            this.statuses = response.data.content;
            this.number = response.data.number+1;
            this.totalPages = response.data.totalPages;
        });
    }

    loadAllStatuses() {
        return this.statusService.all().then(response => {
            this.allStatuses = response.data;
        });
    }

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyStatus();
    }

    setEmptyStatus() {
        this.status = {
            id: null,
            name: '',
        };
    }

    saveStatus() {
        if (!this.form.$valid) {
            return;
        }

        if (this.status.id) {
            this.updateStatus();
        } else {
            this.createStatus();
        }
    }
 
    createStatus() {
        this.statusService.create(this.status).then(response => {
        this.refresh();
        this.closeModal();

        this.swalService.success('Novi status je uspješno kreiran.');
        }, error => {});
    }


    updateStatus() {
       this.statusService.update(this.status.id, this.status).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Izmjene su uspješno sačuvane.');
        }, error => {});
    }

    delete(id) {
        this.swalService.areYouSure('Obrisani status se ne može vratiti.', () => {
            this.statusService.delete(id).then(response => {
                this.refresh();
                this.swalService.success('Status je uspješno obrisan.');
            });
        });
    }

    closeModal() {
        $('#status-modal').modal('close');
    }

    openModal() {
        $('#status-modal').modal({
            complete: () => this.resetForm()
        }).modal('open');
    }

    goto(newPage) {
        if (newPage > 0 && newPage <= this.totalPages) {
            this.loadStatuses(newPage);
        }
    } 

    filter() {
        this.statusService.filterByName(this.searchText).then(response => {
            this.statuses = response.data;
        });
    }

  
/*	registerStatus() {
		 if (!this.form.$valid) {
            return;
        }

		this.statusService.create(this.status).then((response) => {
        	this.loadStatuses(1);
        	this.loadAllStatuses();
			this.resetForm();
		}, (error) => {
			console.log("Error while creating a status.");
		});
	}
*/
 
  

}

export default StatusController;
