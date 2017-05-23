class AccessLogsController {
    static $inject = ["accessLogService"];

    constructor(accessLogService) {
        this.accessLogService = accessLogService;
        this.loadAccessLogs(1);
        this.searchedText = "";
    }

    loadAccessLogs(page) {
        this.accessLogService.getPage(page).then((response) => {
            this.accessLogs = response.data.content;
            this.number = response.data.number+1;
			this.totalPages = response.data.totalPages;
        },
        (error) => {
            //ispisati gresku umjesto tabele
        });
    }

    goToPage(page) {
        this.loadAccessLogs(page);
    }

    filter() {
        if(this.searchedText) {
            this.accessLogService.getByFilter(this.searchedText).then((response) => {
                this.accessLogs = response.data;
            });
        }

    }
}

export default AccessLogsController;