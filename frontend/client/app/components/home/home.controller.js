class HomeController {
  static $inject = ['reportService', "$http"];
  constructor(reportService, $http) {
    this.error = {
      error: "Primjer greske na serveru",
      message: "Ovdje ide opis greske. Pogledajte /account.html i /account.controller.js "
          + "da vidite kako da koristite ovu jednostavnu komponentu."
    };
    this.reportService = reportService;
    this.$http = $http;
  }

  testPdf() {
    this.reportService.generateAuditReport(1);
  }

}

export default HomeController;
