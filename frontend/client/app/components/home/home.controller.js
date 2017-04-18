class HomeController {
  constructor() {
    this.name = 'home';
    this.informacija = 'informacija';
    this.clanovi = ["Ahmed", "Ajla", "Amina", "Benjamin", "Dario", "Muhamed"];
  }

  dodajClana() {
  	var clan = prompt('Unesite novog clana: ');
  	this.clanovi.push(clan);
  	this.informacija = `zadnji uneseni clan: ${clan}`;
  }

  izbrisiClana(index) {
  	this.clanovi.splice(index, 1);
  }
}

export default HomeController;
