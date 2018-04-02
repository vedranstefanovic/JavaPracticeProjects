package projekat;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Firma {

	/*
	 * Firma sadrži listu objekata koji su tipa Inventar. Do sada je tip objekata koji se čuva u listi bio String,
	 * ali možete primetiti da je String isto klasa koja ima svoje metode kao i bilo koja druga klasa:
	 * 1. naziv joj počinje velikim slovom, 
	 * 2. kada napravite objekat tipa String to možete uraditi kao što smo do sad i radili:
	 * 
	 * String nazivFirme = "Microsoft";
	 * 
	 * a možete napraviti i kako se prave objekti za sve ostale klase:
	 * 
	 * String nazivFirme = new String("Microsoft");
	 * 
	 * 3. nad objektom "nazivFirme" možete pozivati razne metode koje su napisane u klasi String
	 * Na primer:
	 * if(nazivFirme.equals("Microsoft")) {
	 *     System.out.println("Stringovi su jednaki.");
	 * }
	 * 
	 * ili na primer:
	 * System.out.println(nazivFirme.toUpperCase()); ispisaće MICROSOFT na konzoli
	 * 
	 * Na isti način možete da razmišljate i o svakoj drugoj klasi koju vi sami napišete
	 * (počinje velikim slovom, objekte pravite pomoću ključne reči new i imena konstruktora, ima svoje metode)
	 * a koristite ih recimo nekoj listi.
	 * Tako sada koristimo listu koja sadrži objekte tipa Inventar (koja ima svoje konstruktore i svoje metode koje smo sami napisali).
	 */
	private ArrayList<Inventar> listaInventara;

	public Firma() {
		this.listaInventara = new ArrayList<Inventar>();
	}

	public ArrayList<Inventar> getListaInventara() {
		return listaInventara;
	}

	public void setListaInventara(ArrayList<Inventar> listaInventara) {
		this.listaInventara = listaInventara;
	}

	
	/*
	 * Metoda ispisuje sve objekte inventara koje lista inventara sadrži.
	 */
	public void ispisInventara() {

		//Koristimo System.out.printf koja prima parametre kao i metoda format koju smo koristili u metodi toString klase Inventar čisto zbog lepšeg ispisa.
		//Ne mora se koristiti.
		System.out.printf("%15s %15s %15s %20s %15s %20s %10s\n", "Id", "Ime inventara", "Ime prostorije", "Ime i prezime osobe", "Procenjena vrednost", "Godina proizvodnje", "Datum");
		
		//Prolazimo for petljom kroz listu kao i do sada jer nema nikakve razlike da li lista sadrži stringove ili neke druge objekte jer lista je lista.
		for(int i = 0; i < this.listaInventara.size(); i++) {
			/*
			 * Pomoću listaInventara.get(i) dobijamo objekat klase Inventar što se može i proveriti:
			 * 
			 * Inventar inventar = listaInventara.get(i);
			 * 
			 * Ako biste probali da uradite sledeće:
			 * 
			 * String inventar = listaInventara.get(i);
			 * 
			 * Eclipse bi se bunio i rekao da ne može da konvertuje objekat koji je tipa Inventar u nešto što je tipa String
			 * 
			 * Objekte klase Inventar možemo da koristimo u bilo kojoj drugoj klasi koju napišemo (ako smo do sad mogli da je koristimo u Test klasi, zašto ne bismo u nekoj drugoj?)
			 */
			Inventar inventar = listaInventara.get(i);
			/*
			 * Na kraju ispisujemo vrednost objekta inventar (pozvaće se ona naša metoda toString koju smo napisali u klasi Inventar)
			 * Ako ne verujete probajte da pozovete sledeći kod:
			 * 
			 * System.out.println(inventar.toString());
			 * 
			 * Dobićete isti efekat i svejedno je na koji način ćete raditi poziv.
			 */
			System.out.println(inventar);
			
		}
	}

	/*
	 * Metoda dodaje u listu prosleđeni objekat tipa Inventar
	 */
	public boolean dodavanjeInventara(Inventar inventar) {
		
		/*
		 * Prolazimo kroz listu inventara kao i kroz svaku drugu listu
		 */
		for (int i = 0; i < this.listaInventara.size(); i++) {
			/*
			 * pomoću this.listaInventara.get(i) bismo dobili objekat klase inventar nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Inventar (pa naravno i metoda getId()) koja će nam vratiti broj inventara koji je tipa int.
			 * Zatim na sličan način očitavamo broj inventara koji je prosleđen metodi i proveravamo da li su ta dva broja ista.
			 * Ako su isti to znači da inventar koji pokušavamo da dodamo već postoji u listi i nije moguće da se doda opet i metoda se tu završava.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Inventar inventarIzListe = this.listaInventara.get(i); //očitavamo objekat tipa Inventar
			int brojInventaraIzListe = inventarIzListe.getId(); //iz njega dobavljamo njegov broj pomoću metode getId() koja je definisana u klasi Inventar
			int brojProsledjenogInventara = inventar.getId(); //istu stvar radimo i sa objektom inventar koji je prosleđen metodi (kao i u liniji iznad)
			if(brojInventaraIzListe == brojProsledjenogInventara) {
				return false;
			}
			/*if ((this.listaInventara.get(i)).getId() == inventar.getId()) {
				return false;
			}*/
		}
		/*
		 * Kreiramo novi objekat tipa inventar koji ćemo dodati u listu. Objekat noviInventar ima sve vrednosti iste kao i objekat inventar koji se prosleđuje metodi dodavanjeInventara.
		 */
		Inventar noviInventar = new Inventar(inventar.getId(), inventar.getIme(), inventar.getProstorija(),
				inventar.getImePrezime(), inventar.getProcenjenaVrednost(), inventar.getGodinaProizvodnje(), inventar.getDatum());
		boolean daLiJeDodat = this.listaInventara.add(noviInventar);
		/*
		 * Pošto noviInventar ima sve iste vrednosti kao objekat inventar pomoću kojeg je kreiran moglo se direktno uraditi sledeće
		 * boolean daLiJeDodat = this.listaInventara.add(inventar);
		 */
		return daLiJeDodat;
	}

	/*
	 * Metoda briše inventar koji ima identifikacioni broj koji se prosleđuje metodi. Ako nema inventara sa prosleđenim brojem metoda će vratiti null
	 */
	public Inventar brisanjeInventara(int id) {

		//kreiramo privremenu promenljivu indeks koja će nam označavati poziciju inventara u listi listaInventara
		//promenljivoj dodeljujemo neku negativnu vrednost jer indeksiranje elemenata u listi kreće od 0
		//pa da u slučaju da ne nađemo inventar sa prosleđenim id u listi možemo sa sigurnošću da znamo da nismo uspeli da nađemo inventar
		int indeks = -1;
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaInventara.size(); i++) {
			/*
			 * pomoću this.listaInventara.get(i) bismo dobili objekat klase inventar nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Inventar (pa naravno i metoda getId()) koja će nam vratiti broj inventara koji je tipa int.
			 * Zatim proveravamo da li je taj broj jednak sa brojem id koji se prosleđuje metodi.
			 * Ako su isti to znači da inventar koji pokušavamo da obrišemo postoji u listi i može se izbrisati.
			 * U promenljivu indeks zapisujemo poziciju inventara za brisanje.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Inventar inventarIzListe = this.listaInventara.get(i); //očitavamo objekat tipa Inventar
			int brojInventaraIzListe = inventarIzListe.getId(); //iz njega dobavljamo njegov broj pomoću metode getId() koja je definisana u klasi Inventar
			if(brojInventaraIzListe == id) {
				indeks = i;
			}
			/*if ((this.listaInventara.get(i)).getId() == id) {
				indeks = i;
			}*/
		}
		
		/*
		 * Proveravamo da li je promenljiva indeks promenila vrednost (ako nije -1 znači da inventar postoji u listi).
		 * Metoda remove koju poseduje ArrayList kao povratnu vrednost daje objekat koji se briše, što znači da će u našem slučaju vratiti
		 * objekat klase Inventar i zatim ga obrisati iz liste.
		 */
		if(indeks != -1) {
			Inventar inventarKojiSeBrise = this.listaInventara.remove(indeks);
			return inventarKojiSeBrise;
		}
		return null;
	}

	/*
	 * Metoda za prosleđeni naziv prostorije ispisuje sve podatke o svakom inventaru koji se tu nalazi.
	 */
	public void ispisInventaraUIstojProstoriji(String prostorija) {

		//Koristimo System.out.printf koja prima parametre kao i metoda format koju smo koristili u metodi toString klase Inventar čisto zbog lepšeg ispisa.
		//Ne mora se koristiti.
		System.out.printf("%15s %15s %15s %20s %15s %20s %10s\n", "Id", "Ime inventara", "Ime prostorije", "Ime i prezime osobe", "Procenjena vrednost", "Godina proizvodnje", "Datum");
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaInventara.size(); i++) {
			/*
			 * pomoću this.listaInventara.get(i) bismo dobili objekat klase inventar nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Inventar (pa naravno i metoda getProstorija()) koja će nam vratiti naziv prostorije koja je tipa String.
			 * Zatim proveravamo da li je naziv te prostorije jednak sa nazivom prostorije koji se prosleđuje metodi.
			 * Ako su isti to znači da inventar koji pokušavamo da ispišemo postoji u listi i mogu se njegovi detalji ispisati na konzoli.
			 * Koristimo metodu equalsIgnoreCase koju poseduje klasa String da proverimo jednakost naziva bez obzira da li su slova u oba naziva velika ili mala.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Inventar inventarIzListe = this.listaInventara.get(i); //očitavamo objekat tipa Inventar
			String prostorijaInventaraIzListe = inventarIzListe.getProstorija(); //iz njega dobavljamo naziv pomoću metode getProstorija() koja je definisana u klasi Inventar
			if(prostorijaInventaraIzListe.equalsIgnoreCase(prostorija)) {
				Inventar inventarKojiSeNalaziUProsledjenojProstoriji = this.listaInventara.get(i);
				System.out.println(inventarKojiSeNalaziUProsledjenojProstoriji);
			}
			/*if ((this.listaInventara.get(i)).getProstorija().equalsIgnoreCase(prostorija)) {
				System.out.println(this.listaInventara.get(i));
			}*/
		}

	}

	/*
	 * Metoda za prosleđeni naziv inventara ispisuje sve podatke o inventarima istog naziva.
	 */
	public void ispisInventaraIstogNaziva(String naziv) {

		//Koristimo System.out.printf koja prima parametre kao i metoda format koju smo koristili u metodi toString klase Inventar čisto zbog lepšeg ispisa.
		//Ne mora se koristiti.
		System.out.printf("%15s %15s %15s %20s %15s %20s %10s\n", "Id", "Ime inventara", "Ime prostorije", "Ime i prezime osobe", "Procenjena vrednost", "Godina proizvodnje", "Datum");
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaInventara.size(); i++) {
			/*
			 * pomoću this.listaInventara.get(i) bismo dobili objekat klase inventar nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Inventar (pa naravno i metoda getIme()) koja će nam vratiti naziv inventara koji je tipa String.
			 * Zatim proveravamo da li je naziv tog jednak sa nazivom inventara koji se prosleđuje metodi.
			 * Ako su isti to znači da inventar koji pokušavamo da ispišemo postoji u listi i mogu se njegovi detalji ispisati na konzoli.
			 * Koristimo metodu equalsIgnoreCase koju poseduje klasa String da proverimo jednakost naziva bez obzira da li su slova u oba naziva velika ili mala.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Inventar inventarIzListe = this.listaInventara.get(i); //očitavamo objekat tipa Inventar
			String nazivInventaraIzListe = inventarIzListe.getIme(); //iz njega dobavljamo naziv pomoću metode getIme() koja je definisana u klasi Inventar
			if(nazivInventaraIzListe.equalsIgnoreCase(naziv)) {
				Inventar inventarKojiImaIstiNazivKaoProsledjeniString = this.listaInventara.get(i);
				System.out.println(inventarKojiImaIstiNazivKaoProsledjeniString);
			}
			/*if ((this.listaInventara.get(i)).getIme().equalsIgnoreCase(naziv)) {
				System.out.println(this.listaInventara.get(i));
			}*/
		}

	}
	
	/*
	 * Metoda ispisuje sve podatke o inventarima koji se nalaze u istoj prostoriji, duži ih ista osoba i imaju godinu proizvodnje veću od prosleđene.
	 */
	public void ispisInventaraDodatniParametri(String prostorija, String osoba, int zadataGodinaProizvodnje) {

		//Koristimo System.out.printf koja prima parametre kao i metoda format koju smo koristili u metodi toString klase Inventar čisto zbog lepšeg ispisa.
		//Ne mora se koristiti.
		System.out.printf("%15s %15s %15s %20s %15s %20s %10s\n", "Id", "Ime inventara", "Ime prostorije", "Ime i prezime osobe", "Procenjena vrednost", "Godina proizvodnje", "Datum");
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaInventara.size(); i++) {
			/*
			 * pomoću this.listaInventara.get(i) bismo dobili objekat klase inventar nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Inventar (pa naravno i metode getProstorija(), getImePrezime() i getGodinaProizvodnje()).
			 * Zatim proveravamo da li je naziv tog jednak sa nazivom inventara koji se prosleđuje metodi, da li ih duži ista osoba i da li je godina proizvodnje veća od zadate.
			 * Ako su uslovi ispunjeni, to znači da inventar koji pokušavamo da ispišemo postoji u listi i mogu se njegovi detalji ispisati na konzoli.
			 * Koristimo metodu equalsIgnoreCase koju poseduje klasa String da proverimo jednakost naziva bez obzira da li su slova u oba naziva velika ili mala.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Inventar inventarIzListe = this.listaInventara.get(i); //očitavamo objekat tipa Inventar
			String prostorijaInventaraIzListe = inventarIzListe.getProstorija(); //iz njega dobavljamo naziv pomoću metode getIme() koja je definisana u klasi Inventar
			String imeIPrezimeOsobeKojaDuziInventar = inventarIzListe.getImePrezime(); //iz njega dobavljamo ime i prezime osobe koja duži inventar pomoću metode getImePrezime() koja je definisana u klasi Inventar
			int godinaProizvodnjeInventaraIzListe = inventarIzListe.getGodinaProizvodnje(); //iz njega dobavljamo godinu proizvodnje pomoću metode getGodinaProizvodnje() koja je definisana u klasi Inventar
			if(prostorijaInventaraIzListe.equalsIgnoreCase(prostorija) &&
				imeIPrezimeOsobeKojaDuziInventar.equalsIgnoreCase(osoba) &&
				godinaProizvodnjeInventaraIzListe > zadataGodinaProizvodnje) {
				Inventar inventarKojiZadovoljavaSvaTriUslova = this.listaInventara.get(i);
				System.out.println(inventarKojiZadovoljavaSvaTriUslova);
			}
			/*if ((this.listaInventara.get(i)).getProstorija().equalsIgnoreCase(prostorija) &&
					this.listaInventara.get(i).getImePrezime().equalsIgnoreCase(osoba) &&
					this.listaInventara.get(i).getGodinaProizvodnje() > zadataGodinaProizvodnje) {
				System.out.println(this.listaInventara.get(i));
			}*/
		}

	}
	
	public void ispisInventaraDatum(String prostorija, String osoba, Date min, Date max) {

		//Koristimo System.out.printf koja prima parametre kao i metoda format koju smo koristili u metodi toString klase Inventar čisto zbog lepšeg ispisa.
		//Ne mora se koristiti.
		System.out.printf("%15s %15s %15s %20s %15s %20s %10s\n", "Id", "Ime inventara", "Ime prostorije", "Ime i prezime osobe", "Procenjena vrednost", "Godina proizvodnje", "Datum");
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaInventara.size(); i++) {
			/*
			 * pomoću this.listaInventara.get(i) bismo dobili objekat klase inventar nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Inventar (pa naravno i metode getProstorija(), getImePrezime() i getGodinaProizvodnje()).
			 * Zatim proveravamo da li je naziv tog jednak sa nazivom inventara koji se prosleđuje metodi, da li ih duži ista osoba i da li je godina proizvodnje veća od zadate.
			 * Ako su uslovi ispunjeni, to znači da inventar koji pokušavamo da ispišemo postoji u listi i mogu se njegovi detalji ispisati na konzoli.
			 * Koristimo metodu equalsIgnoreCase koju poseduje klasa String da proverimo jednakost naziva bez obzira da li su slova u oba naziva velika ili mala.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Inventar inventarIzListe = this.listaInventara.get(i); //očitavamo objekat tipa Inventar
			String prostorijaInventaraIzListe = inventarIzListe.getProstorija(); //iz njega dobavljamo naziv pomoću metode getIme() koja je definisana u klasi Inventar
			String imeIPrezimeOsobeKojaDuziInventar = inventarIzListe.getImePrezime(); //iz njega dobavljamo ime i prezime osobe koja duži inventar pomoću metode getImePrezime() koja je definisana u klasi Inventar
			Date datumInventaraizListe = inventarIzListe.getDatum(); //iz njega dobavljamo datum pomoću metode getDatum() koja je definisana u klasi Inventar
			if(prostorijaInventaraIzListe.equalsIgnoreCase(prostorija) &&
				imeIPrezimeOsobeKojaDuziInventar.equalsIgnoreCase(osoba) &&
				datumInventaraizListe.compareTo(min) >= 0 && datumInventaraizListe.compareTo(max) <= 0) {
				Inventar inventarKojiZadovoljavaSvaTriUslova = this.listaInventara.get(i);
				System.out.println(inventarKojiZadovoljavaSvaTriUslova);
			}
			/*if ((this.listaInventara.get(i)).getProstorija().equalsIgnoreCase(prostorija) &&
					this.listaInventara.get(i).getImePrezime().equalsIgnoreCase(osoba) &&
					this.listaInventara.get(i).getGodinaProizvodnje() > zadataGodinaProizvodnje) {
				System.out.println(this.listaInventara.get(i));
			}*/
		}

	}
	
	/*
	 * Metoda ispisuje podatke o inventarima koji imaju isto ime i čija se vrednost nalazi u zadatim min i max granicama.
	 */
	public void ispisInventaraProcenjenaVrednost(String ime, double minVrednost, double maxVrednost) {

		//Koristimo System.out.printf koja prima parametre kao i metoda format koju smo koristili u metodi toString klase Inventar čisto zbog lepšeg ispisa.
		//Ne mora se koristiti.
		System.out.printf("%15s %15s %15s %20s %15s %20s %10s\n", "Id", "Ime inventara", "Ime prostorije", "Ime i prezime osobe", "Procenjena vrednost", "Godina proizvodnje", "Datum");
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaInventara.size(); i++) {
			/*
			 * pomoću this.listaInventara.get(i) bismo dobili objekat klase inventar nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Inventar (pa naravno i metode getIme() i getProcenjenaVrednost()).
			 * Zatim proveravamo da li je ime tog jednak sa imenom inventara koji se prosleđuje metodi kao i da li je procenjena vrednost u zadatim granicama.
			 * Ako su uslovi ispunjeni, to znači da inventar koji pokušavamo da ispišemo postoji u listi i mogu se njegovi detalji ispisati na konzoli.
			 * Koristimo metodu equalsIgnoreCase koju poseduje klasa String da proverimo jednakost naziva bez obzira da li su slova u oba naziva velika ili mala.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Inventar inventarIzListe = this.listaInventara.get(i); //očitavamo objekat tipa Inventar
			String nazivInventaraIzListe = inventarIzListe.getIme(); //iz njega dobavljamo naziv pomoću metode getIme() koja je definisana u klasi Inventar
			double procenjenaVrednostInventaraIzListe = inventarIzListe.getProcenjenaVrednost(); //iz njega dobavljamo procenjenu vrednost pomoću metode getProcenjenaVrednost() koja je definisana u klasi Inventar
			if(nazivInventaraIzListe.equalsIgnoreCase(ime) &&
				procenjenaVrednostInventaraIzListe >= minVrednost &&
				procenjenaVrednostInventaraIzListe <= maxVrednost) {
				
				Inventar inventarKojiZadovoljavaSvaTriUslova = this.listaInventara.get(i);
				System.out.println(inventarKojiZadovoljavaSvaTriUslova);
			}
			/*if ((this.listaInventara.get(i)).getIme().equalsIgnoreCase(ime) &&
					this.listaInventara.get(i).getProcenjenaVrednost() >= minVrednost &&
					this.listaInventara.get(i).getProcenjenaVrednost() <= maxVrednost) {
				System.out.println(this.listaInventara.get(i));
			}*/
		}

	}
	
	/*
	 * Metoda menja inventar sa prosleđenim identifikacionim brojem, izmenjenim inventarom koji u opštem slučaju može imati sve
	 * nove vrednosti osim identifikacionog broja.
	 */
	public Inventar izmenaInventara(int id, Inventar inventar) {
		
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaInventara.size(); i++) {
			/*
			 * pomoću this.listaInventara.get(i) bismo dobili objekat klase inventar nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Inventar (pa naravno i metoda getId()) koja će nam vratiti broj inventara koji je tipa int.
			 * Zatim proveravamo da li je taj broj jednak sa brojem id koji se prosleđuje metodi.
			 * Ako su isti to znači da inventar koji pokušavamo da izmenimo postoji u listi i može se menjati.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Inventar inventarIzListe = this.listaInventara.get(i); //očitavamo objekat tipa Inventar
			int brojInventaraIzListe = inventarIzListe.getId(); //iz njega dobavljamo njegov broj pomoću metode getId() koja je definisana u klasi Inventar
			if(brojInventaraIzListe == id) {
				Inventar inventarKojiSeMenja = this.listaInventara.set(i, inventar);
				return inventarKojiSeMenja;
			}
			/*
			 * Metoda sez koju poseduje ArrayList kao povratnu vrednost daje objekat koji se postavlja u listu na određenu poziciju, što znači da će u našem slučaju
			 * izvršiti zamenu sa starim inventarom i vratiti izmenjen objekat.
			 */
			/*if ((this.listaInventara.get(i)).getId() == id) {
				return this.listaInventara.set(i, inventar);
			}*/
		}
		return null;
		
	}
	
	public void ispisiProsecnuCenuInventaraSaUslovima(String osoba, int godinaProizvodnje) {
		
		ArrayList<Inventar> inventariZaRacunanjeProsecneVrednosti = new ArrayList<>();
		for(int i = 0; i < this.listaInventara.size(); i++) {
			if(this.listaInventara.get(i).getImePrezime().equals(osoba) && this.listaInventara.get(i).getGodinaProizvodnje() > godinaProizvodnje) {
				inventariZaRacunanjeProsecneVrednosti.add(this.listaInventara.get(i));
			}
		}
		double suma = 0;
		for(int i = 0; i < inventariZaRacunanjeProsecneVrednosti.size(); i++) {
			suma += inventariZaRacunanjeProsecneVrednosti.get(i).getProcenjenaVrednost();
		}
		if(inventariZaRacunanjeProsecneVrednosti.size() > 0) {
			double prosecnaVrednost = suma / inventariZaRacunanjeProsecneVrednosti.size();
			System.out.println("Prosecna vrednost inventara koji zadovoljava uslove je: " + prosecnaVrednost);
		} else {
			System.out.println("Prosecna vrednost se ne moze izracunati jer nema podataka koji zadovoljavaju zadate uslove.");
		}
	}
	
	/*
	 * Metoda čuva podatke o inventarima u tekstualnom fajlu čiji se naziv prosleđuje.
	 */
	public void save(String path) {
		
		/*
		 * lista koja će se popuniti redovima koji će biti u formatu:
		 * 
		 * identifikacioniBroj;imeInventara;prostorija;imeIPrezimeOsobeKojaDuziInventar;procenjenaVrednost;godinaProizvodnje
		 */
		ArrayList<String> lines = new ArrayList<String>();
		
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaInventara.size(); i++) {
			/*
			 * Kreiramo String line koji predstavlja jedan red u tekstualnoj datoteci a sadrži informacije o pojedinačnom inventaru
			 * Kod koji radi istu stvar je zakomentarisan:
			 */ 
			 Inventar inventar = this.listaInventara.get(i); //očitavamo objekat tipa Inventar
			 int identifikacioniBroj = inventar.getId();
			 String imeInventara = inventar.getIme();
			 String prostorija = inventar.getProstorija();
			 String imeIPrezimeOsobeKojaDuziInventar = inventar.getImePrezime();
			 double procenjenaVrednost = inventar.getProcenjenaVrednost();
			 int godinaProizvodnje = inventar.getGodinaProizvodnje();
			 Date datum = inventar.getDatum();
			 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
			 String formatiraniDatum = sdf.format(datum); 
			 String line = identifikacioniBroj  + ";" + imeInventara + ";" + prostorija + ";" + imeIPrezimeOsobeKojaDuziInventar + ";" + procenjenaVrednost + ";" + godinaProizvodnje + ";" + formatiraniDatum;
			 lines.add(line);
			 
			/*String line = this.listaInventara.get(i).getId() + ";" + this.listaInventara.get(i).getIme() + ";"
							+ this.listaInventara.get(i).getProstorija() + ";" + this.listaInventara.get(i).getImePrezime() + ";"
							+ this.listaInventara.get(i).getProcenjenaVrednost() + ";" + this.listaInventara.get(i).getGodinaProizvodnje();
			lines.add(line);*/
		}
		
		try {
			//prvo se ispituje da li fajl sa datim imenom postoji metodom exists
			//ako ne postoji kreira se novi pomoću CREATE_NEW, a ako postoji, pomoću CREATE i TRUNCATE_EXISTING (oba parametra se moraju navesti)
			//se briše sve iz postojećeg i upisuje novi sadržaj
			if(!Files.exists(Paths.get(path))){
				Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE_NEW);
			} else {
				Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronađena.");
			e.printStackTrace();
		}
	}
	
	/*
	 * Metoda učitava podatke o inventarima iz tekstualnog fajla čiji se naziv prosleđuje u listaInventara koji je atribut klase Firma.
	 */
	public void load(String path) {
		
		this.listaInventara = new ArrayList<Inventar>();
		
		List<String> lines;
		try {
			//učitavaju se sve linije iz fajla
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
			
			/*
			 * prolazi se for petljom kroz svaku liniju koja je u formatu:
			 * identifikacioniBroj;imeInventara;prostorija;imeIPrezimeOsobeKojaDuziInventar;procenjenaVrednost;godinaProizvodnje
			 */
			for (String line: lines) {
				//svaka linija se "secka" na reči koje su odvojene znakom ; pomoću metode split klase String i tako se dobija niz stringova
				String[] attributes = line.split(";");
				
				/*
				 * Pošto znamo redosled vrednosti koje su pisane u datoteci znamo kojim redom da ih preuzimamo iz niza attributes
				 */
				int id = Integer.parseInt(attributes[0]); 
				String ime = attributes[1];
				String prostorija = attributes[2]; 
				String imePrezime = attributes[3]; 
				double procenjenaVrednost = Double.parseDouble(attributes[4]); 
				int godinaProizvodnje = Integer.parseInt(attributes[5]);
				String datum = attributes[6];
				Date datumZaCuvanje = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
				try {
					datumZaCuvanje = sdf.parse(datum);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				
				/*
				 * Pomoću izdvojenih vrednosti iz niza kreiramo novi objekat tipa Inventar i smeštamo u listu inventara
				 */
				Inventar inventar = new Inventar(id, ime, prostorija, imePrezime, procenjenaVrednost, godinaProizvodnje, datumZaCuvanje);
				this.listaInventara.add(inventar);
				
			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronađena.");
			e.printStackTrace();
		}
	
	}
	
	



}
