class AuditController {
    static $inject = ['auditService', 'accountService', 'locationService', 'reportService' ];
    
    constructor(auditService, accountService, locationService, reportService) {
        this.auditService = auditService;
        this.locationService = locationService;
        this.accountService = accountService;
        this.reportService = reportService;

        this.load();
    }

    load() {
        this.loadAllLocations().then(() => this.loadAllAudits());
    }

    loadAllLocations() {
        return this.locationService.all().then(response => {
            this.allLocations = response.data;
        });
    }
    
    loadAllAudits() {
        this.auditService.all().then(response => {
            this.allAudits = response.data;
        });
    }

    generateReport(auditID) {
        this.reportService.generateAuditReport(auditID);
    }

    getLocationName(locationID) {
        let location = this.allLocations.find(location => location.id === locationID);

        return location ? location.name : null;
    }
}

export default AuditController;