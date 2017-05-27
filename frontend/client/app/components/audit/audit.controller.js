class AuditController {
    static $inject = ['auditService', 'accountService', 'locationService', 'reportService' ];
    
    constructor(auditService, accountService, locationService, reportService) {
        this.auditService = auditService;
        this.locationService = locationService;
        this.accountService = accountService;
        this.reportService = reportService;

        this.loadAllAudits();   
        this.loadAllAccounts();
        this.loadAllLocations();
    }
    
    loadAllAudits(){
        this.auditService.all().then(response => {
            this.allAudits = response.data;
        });
    }

    loadAllAccounts(){
        this.accountService.all().then(response => {
            this.allAccounts = response.data;
        });
    }
    
    loadAllLocations(){
        this.locationService.all().then(response => {
            this.allLocations = response.data;
        });
    }

    generateReport(auditID) {
        this.reportService.generateAuditReport(auditID);
    }  
}

export default AuditController;