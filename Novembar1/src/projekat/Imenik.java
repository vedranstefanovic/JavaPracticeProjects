package projekat;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Imenik {
	
	private ArrayList<Kontakt> listaKontakta;

	public Imenik() {
		this.listaKontakta = new ArrayList<Kontakt>();
	}

	public ArrayList<Kontakt> getListaKontakta() {
		return listaKontakta;
	}

	public void setListaKontakta(ArrayList<Kontakt> listaKontakta) {
		this.listaKontakta = listaKontakta;
	}
	
	/*
	 * Metoda dodaje u listu prosleđeni objekat tipa Kontakt
	 */
	public boolean dodavanjeKontakta(Kontakt kontakt) {
		
		/*
		 * Prolazimo kroz listu kontakata kao i kroz svaku drugu listu
		 */
		for (int i = 0; i < this.listaKontakta.size(); i++) {
			/*
			 * pomoću this.listaInventara.get(i) bismo dobili objekat klase Kontakt nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Kontakt (pa naravno i metoda getId()) koja će nam vratiti id kontakta koji je tipa int.
			 * Zatim na sličan način očitavamo id kontakta koji je prosleđen metodi i proveravamo da li su ta dva broja ista.
			 * Ako su isti to znači da kontakt koji pokušavamo da dodamo već postoji u listi i nije moguće da se doda opet i metoda se tu završava.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Kontakt kontaktIzListe = this.listaKontakta.get(i); //očitavamo objekat tipa Kontakt
			int idKontaktaIzListe = kontaktIzListe.getId(); //iz njega dobavljamo njegov id pomoću metode getId() koja je definisana u klasi Kontakt
			int idProsledjenogKontakta = kontakt.getId(); //istu stvar radimo i sa objektom kontakt koji je prosleđen metodi (kao i u liniji iznad)
			if(idKontaktaIzListe == idProsledjenogKontakta) {
				return false;
			}
			/*if ((this.listaKontakta.get(i)).getId() == kontakt.getId()) {
				return false;
			}*/
		}
		/*
		 * Kreiramo novi objekat tipa kontakt koji ćemo dodati u listu. Objekat noviKontakt ima sve vrednosti iste kao i objekat kontakt koji se prosleđuje metodi dodavanjeKontakta.
		 */
		Kontakt noviKontakt = new Kontakt(kontakt.getId(), kontakt.getIme(), kontakt.getPrezime(),
				kontakt.getNazivRadnogMesta(), kontakt.getBrojProstorije(), kontakt.getBrojLokala());
		boolean daLiJeDodat = this.listaKontakta.add(noviKontakt);
		/*
		 * Pošto noviKontakt ima sve iste vrednosti kao objekat kontakt pomoću kojeg je kreiran moglo se direktno uraditi sledeće
		 * boolean daLiJeDodat = this.listaKontakta.add(kontakt);
		 */
		return daLiJeDodat;
	}
	
	/*
	 * Metoda ispisuje sve objekte kontakata koje lista kontakata sadrži.
	 */
	public void ispisKontakata() {

		//Koristimo System.out.printf koja prima parametre kao i metoda format koju smo koristili u metodi toString klase Kontakt čisto zbog lepšeg ispisa.
		//Ne mora se koristiti.
		System.out.printf("%15s %15s %15s %25s %15s %15s \n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala");
		System.out.println("-----------------------------------------------------------------------------------------------");
		//Prolazimo for petljom kroz listu kao i do sada jer nema nikakve razlike da li lista sadrži stringove ili neke druge objekte jer lista je lista.
		for(int i = 0; i < this.listaKontakta.size(); i++) {
			/*
			 * Pomoću listaKontakta.get(i) dobijamo objekat klase Kontakt što se može i proveriti:
			 * 
			 * Kontakt kontakt = listaKontakta.get(i);
			 * 
			 * Ako biste probali da uradite sledeće:
			 * 
			 * String kontakt = listaKontakta.get(i);
			 * 
			 * Eclipse bi se bunio i rekao da ne može da konvertuje objekat koji je tipa Kontakt u nešto što je tipa String
			 * 
			 * Objekte klase Kontakt možemo da koristimo u bilo kojoj drugoj klasi koju napišemo (ako smo do sad mogli da je koristimo u Test klasi, zašto ne bismo u nekoj drugoj?)
			 */
			Kontakt kontakt = listaKontakta.get(i);
			/*
			 * Na kraju ispisujemo vrednost objekta kontakt (pozvaće se ona naša metoda toString koju smo napisali u klasi Kontakt)
			 * Ako ne verujete probajte da pozovete sledeći kod:
			 * 
			 * System.out.println(kontakt.toString());
			 * 
			 * Dobićete isti efekat i svejedno je na koji način ćete raditi poziv.
			 */
			System.out.println(kontakt);
			
		}
	}
	
	/*
	 * Metoda briše kontakt koji ima identifikacioni broj koji se prosleđuje metodi. Ako nema kontakta sa prosleđenim brojem metoda će vratiti null
	 */
	public Kontakt brisanjeKontakta(int id) {

		//kreiramo privremenu promenljivu indeks koja će nam označavati poziciju kontakta u listi listaKontakta
		//promenljivoj dodeljujemo neku negativnu vrednost jer indeksiranje elemenata u listi kreće od 0
		//pa da u slučaju da ne nađemo kontakt sa prosleđenim id u listi možemo sa sigurnošću da znamo da nismo uspeli da nađemo kontakta
		int indeks = -1;
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaKontakta.size(); i++) {
			/*
			 * pomoću this.listaKontakta.get(i) bismo dobili objekat klase Kontakt nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Kontakt (pa naravno i metoda getId()) koja će nam vratiti id kontakta koji je tipa int.
			 * Zatim proveravamo da li je taj broj jednak sa brojem id koji se prosleđuje metodi.
			 * Ako su isti to znači da kontakt koji pokušavamo da obrišemo postoji u listi i može se izbrisati.
			 * U promenljivu indeks zapisujemo poziciju kontakta za brisanje.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Kontakt kontaktIzListe = this.listaKontakta.get(i); //očitavamo objekat tipa Kontakt
			int idKontaktaIzListe = kontaktIzListe.getId(); //iz njega dobavljamo njegov id pomoću metode getId() koja je definisana u klasi Kontakt
			if(idKontaktaIzListe == id) {
				indeks = i;
			}
			/*if ((this.listaKontakta.get(i)).getId() == id) {
				indeks = i;
			}*/
			
		}
		
		/*
		 * Proveravamo da li je promenljiva indeks promenila vrednost (ako nije -1 znači da kontakt postoji u listi).
		 * Metoda remove koju poseduje ArrayList kao povratnu vrednost daje objekat koji se briše, što znači da će u našem slučaju vratiti
		 * objekat klase Kontakt i zatim ga obrisati iz liste.
		 */
		if(indeks != -1) {
			Kontakt kontaktKojiSeBrise = this.listaKontakta.remove(indeks);
			return kontaktKojiSeBrise;
		}
		return null;
	}
	
	/*
	 * Metoda menja kontakt sa prosleđenim identifikacionim brojem, izmenjenim kontaktom koji u opštem slučaju može imati sve
	 * nove vrednosti osim identifikacionog broja.
	 */
	public Kontakt izmenaKontakta(int id, Kontakt kontakt) {
		
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaKontakta.size(); i++) {
			/*
			 * pomoću this.listaKontakta.get(i) bismo dobili objekat klase Kontakt nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Kontakt (pa naravno i metoda getId()) koja će nam vratiti id kontakta koji je tipa int.
			 * Zatim proveravamo da li je taj broj jednak sa brojem id koji se prosleđuje metodi.
			 * Ako su isti to znači da kontakt koji pokušavamo da izmenimo postoji u listi i može se menjati.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Kontakt kontaktIzListe = this.listaKontakta.get(i); //očitavamo objekat tipa Kontakt
			int idKontaktaIzListe = kontaktIzListe.getId(); //iz njega dobavljamo njegov id pomoću metode getId() koja je definisana u klasi Kontakt
			if(idKontaktaIzListe == id) {
				Kontakt kontaktKojiSeMenja = this.listaKontakta.set(i, kontakt);
				return kontaktKojiSeMenja;
			}
			/*
			 * Metoda sez koju poseduje ArrayList kao povratnu vrednost daje objekat koji se postavlja u listu na određenu poziciju, što znači da će u našem slučaju
			 * izvršiti zamenu sa starim kontaktom i vratiti izmenjen objekat.
			 */
			/*if ((this.listaKontakta.get(i)).getId() == id) {
				return this.listaKontakta.set(i, kontakt);
			}*/
		}
		return null;
		
	}
	
	/*
	 * Metoda čuva podatke o kontaktima u tekstualnom fajlu čiji se naziv prosleđuje.
	 */
	public void save(String path) {
		
		/*
		 * lista koja će se popuniti redovima koji će biti u formatu:
		 * 
		 * identifikacioniBroj;imeKontakta;prezimeKontakta;nazivRadnogMesta;brojProstorije;brojLokala
		 */
		ArrayList<String> lines = new ArrayList<String>();
		
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaKontakta.size(); i++) {
			/*
			 * Kreiramo String line koji predstavlja jedan red u tekstualnoj datoteci a sadrži informacije o pojedinačnom kontaktu
			 * Kod koji radi istu stvar je zakomentarisan:
			 */ 
			 Kontakt kontakt = this.listaKontakta.get(i); //očitavamo objekat tipa Kontakt
			 int identifikacioniBroj = kontakt.getId();
			 String imeKontakta = kontakt.getIme();
			 String prezimeKontakta = kontakt.getPrezime();
			 String nazivRadnogMesta = kontakt.getNazivRadnogMesta();
			 String brojProstorije = kontakt.getBrojProstorije();
			 int brojLokala = kontakt.getBrojLokala();
			 String line = identifikacioniBroj  + ";" + imeKontakta + ";" + prezimeKontakta + ";" + nazivRadnogMesta + ";" + brojProstorije + ";" + brojLokala;
			 lines.add(line);
			 
			/*String line = this.listaKontakta.get(i).getId() + ";" + this.listaKontakta.get(i).getIme() + ";"
							+ this.listaKontakta.get(i).getPrezime() + ";" + this.listaKontakta.get(i).getNazivRadnogMesta() + ";"
							+ this.listaKontakta.get(i).getBrojProstorije() + ";" + this.listaKontakta.get(i).getBrojLokala();
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
	 * Metoda učitava podatke o kontaktima iz tekstualnog fajla čiji se naziv prosleđuje u listaKontakata koji je atribut klase Imenik.
	 */
	public void load(String path) {
		
		this.listaKontakta = new ArrayList<Kontakt>();
		
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
				String prezime = attributes[2]; 
				String radnoMesto = attributes[3]; 
				String brojProstorije = attributes[4]; 
				int brojLokala = Integer.parseInt(attributes[5]);
				
				/*
				 * Pomoću izdvojenih vrednosti iz niza kreiramo novi objekat tipa Kontakt i smeštamo u listu kontakata
				 */
				Kontakt kontakt = new Kontakt(id, ime, prezime, radnoMesto, brojProstorije, brojLokala);
				this.listaKontakta.add(kontakt);
				
			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronađena.");
			e.printStackTrace();
		}
	
	}
	
	/*
	 * Metoda za prosleđeni naziv lokala ispisuje sve podatke o svakom kontaktu koji se tu nalazi.
	 */
	public void ispisKontakataSaIstimLokalom(int lokal) {

		//Koristimo System.out.printf koja prima parametre kao i metoda format koju smo koristili u metodi toString klase Kontakt čisto zbog lepšeg ispisa.
		//Ne mora se koristiti.
		System.out.printf("%15s %15s %15s %25s %15s %15s \n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala");
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaKontakta.size(); i++) {
			/*
			 * pomoću this.listaKontakta.get(i) bismo dobili objekat klase Kontakt nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Kontakt (pa naravno i metoda getBrojLokala()) koja će nam vratiti broj lokala koja je tipa int.
			 * Zatim proveravamo da li je broj tog lokala jednak sa brojem lokala koji se prosleđuje metodi.
			 * Ako su isti to znači da kontakt koji pokušavamo da ispišemo postoji u listi i mogu se njegovi detalji ispisati na konzoli.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Kontakt kontaktIzListe = this.listaKontakta.get(i); //očitavamo objekat tipa Kontakt
			int lokalKontaktaIzListe = kontaktIzListe.getBrojLokala(); //iz njega dobavljamo broj lokala pomoću metode getBrojLokala() koja je definisana u klasi Kontakt
			if(lokalKontaktaIzListe == lokal) {
				Kontakt kontaktKojiSeNalaziUProsledjenomLokalu = this.listaKontakta.get(i);
				System.out.println(kontaktKojiSeNalaziUProsledjenomLokalu);
			}
			/*if ((this.listaKontakta.get(i)).getBrojLokala() == lokal) {
				System.out.println(this.listaKontakta.get(i));
			}*/
		}

	}
	
	/*
	 * Metoda za prosleđeni naziv radnog mesta ispisuje sve podatke o svakom kontaktu koji ima to radno mesto.
	 */
	public void ispisKontakataSaIstimRadnimMestom(String nazivRadnogMesta) {

		//Koristimo System.out.printf koja prima parametre kao i metoda format koju smo koristili u metodi toString klase Kontakt čisto zbog lepšeg ispisa.
		//Ne mora se koristiti.
		System.out.printf("%15s %15s %15s %25s %15s %15s \n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala");
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaKontakta.size(); i++) {
			/*
			 * pomoću this.listaKontakta.get(i) bismo dobili objekat klase Kontakt nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Kontakt (pa naravno i metoda getNazivRadnogMesta()) koja će nam vratiti naziv radnog mesta koja je tipa String.
			 * Zatim proveravamo da li je naziv tog radnog mesta jednak sa nazivom radnog mesta koji se prosleđuje metodi.
			 * Ako su isti to znači da kontakt koji pokušavamo da ispišemo postoji u listi i mogu se njegovi detalji ispisati na konzoli.
			 * Koristimo metodu equalsIgnoreCase koju poseduje klasa String da proverimo jednakost naziva bez obzira da li su slova u oba naziva velika ili mala.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
			Kontakt kontaktIzListe = this.listaKontakta.get(i); //očitavamo objekat tipa Kontakt
			String nazivRadnogMestaKontaktaIzListe = kontaktIzListe.getNazivRadnogMesta(); //iz njega dobavljamo naziv radnog mesta pomoću metode getNazivRadnogMesta() koja je definisana u klasi Kontakt
			if(nazivRadnogMestaKontaktaIzListe.equalsIgnoreCase(nazivRadnogMesta)) {
				Kontakt kontaktKojiImaZadatoRadnoMesto = this.listaKontakta.get(i);
				System.out.println(kontaktKojiImaZadatoRadnoMesto);
			}
			/*if ((this.listaKontakta.get(i)).getNazivRadnogMesta().equalsIgnoreCase(nazivRadnogMesta)) {
				System.out.println(this.listaKontakta.get(i));
			}*/
		}

	}
	
	/*
	 * Metoda za prosleđeno ime kontakta ispisuje sve podatke o svakom kontaktu koji ima to ime.
	 */
	public ArrayList<Kontakt> ispisKontakataSaIstimImenom(String ime) {

		ArrayList<Kontakt> rezultat = new ArrayList<Kontakt>();
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaKontakta.size(); i++) {
			if ((this.listaKontakta.get(i)).getIme().equalsIgnoreCase(ime)) {
				rezultat.add(this.listaKontakta.get(i));
				System.out.println(this.listaKontakta.get(i));
			}
		}
		return rezultat;	

	}
	
	/*
	 * Metoda za prosleđeno ime i prezime kontakta i početkom naziva radnog mesta ispisuje sve podatke o svakom kontaktu koji zadovoljava sva 3 uslova.
	 */
	public void ispisKontakataSaZadatimUslovima(String ime, String prezime, String nazivRadnogMesta) {

		//Koristimo System.out.printf koja prima parametre kao i metoda format koju smo koristili u metodi toString klase Kontakt čisto zbog lepšeg ispisa.
		//Ne mora se koristiti.
		System.out.printf("%15s %15s %15s %25s %15s %15s \n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala");
		//prolazimo kroz listu na standardan način
		for (int i = 0; i < this.listaKontakta.size(); i++) {
			/*
			 * pomoću this.listaKontakta.get(i) bismo dobili objekat klase Kontakt nad kojim se može pozvati bilo koja metoda koja je public a koju smo
			 * napisali u klasi Kontakt (pa naravno i metode getIme(), getPrezime() i getNazivRadnogMesta()) koja će nam vratiti ime i prezime kontakta i naziv radnog mesta koje su tipa String.
			 * Zatim proveravamo da li je ispunjen uslov koji zahteva jednakost imena, prezimena i početka naziva radnog mesta kontakta sa stringovima koji se prosleđuju metodi.
			 * Ako je uslov ispunjen to znači da kontakt koji pokušavamo da ispišemo postoji u listi i mogu se njegovi detalji ispisati na konzoli.
			 * Koristimo metodu equalsIgnoreCase koju poseduje klasa String da proverimo jednakost imena i prezimena bez obzira da li su slova u oba naziva velika ili mala.
			 * Koristimo metodu startsWith koju poseduje klasa String da proverimo da li naziv radnog mesta kontakta počinje sa nazivom koji se prosleđuje metodi.
			 * Isti efekat se postiže i zakomentarisanim kodom:
			 */
/*			Kontakt kontaktIzListe = this.listaKontakta.get(i); //očitavamo objekat tipa Kontakt
			String imeKontaktaIzListe = kontaktIzListe.getIme(); //iz njega dobavljamo ime kontakta pomoću metode getIme() koja je definisana u klasi Kontakt
			String prezimeKontaktaIzListe = kontaktIzListe.getPrezime(); //iz njega dobavljamo prezime kontakta pomoću metode getPrezime() koja je definisana u klasi Kontakt
			String nazivRadnogMestaKontaktaIzListe = kontaktIzListe.getNazivRadnogMesta(); //iz njega dobavljamo naziv radnog mesta kontakta pomoću metode getNazivRadnogMesta() koja je definisana u klasi Kontakt
			if(imeKontaktaIzListe.equalsIgnoreCase(ime) &&
					prezimeKontaktaIzListe.equalsIgnoreCase(prezime) &&
					nazivRadnogMestaKontaktaIzListe.startsWith(nazivRadnogMesta)) {
				Kontakt kontaktKojiZadovoljavaUslove = this.listaKontakta.get(i);
				System.out.println(kontaktKojiZadovoljavaUslove);
			}
*/			if ((this.listaKontakta.get(i)).getIme().equalsIgnoreCase(ime) &&
					(this.listaKontakta.get(i)).getPrezime().equalsIgnoreCase(prezime) &&
					(this.listaKontakta.get(i)).getNazivRadnogMesta().startsWith(nazivRadnogMesta)) {
				System.out.println(this.listaKontakta.get(i));
			}
		}

	}
	
}
