class HomeController {
  static $inject = ["swalService"];
  constructor(swalService) {
    this.swalService = swalService;
  }

  clickMeh() {
    this.swalService.error("FATAL ERROR", "YOU SHOULDN'T HAVE CLICKED THE BUTTON. IT WAS CURSED!");
  }
}

export default HomeController;
