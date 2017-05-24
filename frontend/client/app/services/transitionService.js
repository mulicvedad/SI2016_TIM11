// mislio sam ga koristiti za redirect ali sam se ipak odlucio za httpInterceptor
// u te svrhe tako da ce ovaj servis vjerovatno ubrzo biti obrisan

export default class TransitionService {
    static $inject = ["sessionService"];

    constructor(sessionService) {
        this.sessionService = sessionService;
        this.roleStatesMapping = {
            "ROLE_FINANCE": ["home", "audits", "inventory", "myAccount", "locations", "accessLogs"],
            "ROLE_AUDITTEAM": ["home", "audits", "inventory", "myAccount", "items"]
        }
    }  

    canAcccessState(stateName) {
        if (this.sessionService.currentRole() == "ROLE_ADMIN") {
            return true;
        }

        return this.roleStatesMapping[this.sessionService.currentRole()].includes(stateName);
    }
}