class AccessLogsController {
    static $inject = ["accessLogService"];

    constructor(accessLogService) {
        this.accessLogService = accessLogService;
        this.loadAccessLogs(1);
    }

    loadAccessLogs(page) {
        accessLogService.getPage(page).then(response => {
            console.log("uspjesno dohvacanje logeva");
            this.accessLogs = response.data.content;
            this.number = response.data.number+1;
			this.totalPages = response.data.totalPages;
        },
        (error) => {
            console.log("neuspjesno dohvacanje logeva\n" + JSON.stringify(error));
        });
    }

    goToPage(page) {
        this.loadAccessLogs(page);
    }
}

export default AccessLogsController;