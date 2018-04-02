package projekat;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Firma {

	private Map<Integer, Inventar> mapaInventara = new HashMap<>();

	public Firma() {
	}

	public Map<Integer, Inventar> getMapaInventara() {
		return mapaInventara;
	}

	public void setMapaInventara(ArrayList<Inventar> listaInventara) {
		for (Inventar inventar : listaInventara){
			this.mapaInventara.put(inventar.getId(), inventar);
		}
	}

	
	public void ispisInventara() {
		Utils.ispisHeaderaInventara();

		for (Inventar inventar : this.mapaInventara.values()) {
			System.out.println(inventar);
		}
	}

	public boolean dodavanjeInventara(Inventar inventar) {

		if(!this.mapaInventara.containsKey(inventar.getId())){
			Inventar noviInventar = inventar.hardCopy(inventar);
			this.mapaInventara.put(noviInventar.getId(), noviInventar);
			return true;
		}
		return false;
	}

	public Inventar brisanjeInventara(int id) {

		if(this.mapaInventara.containsKey(id)){
			return this.mapaInventara.remove(id);
		}
		return null;
	}

	/*
	 * Metoda za prosleđeni naziv prostorije ispisuje sve podatke o svakom inventaru koji se tu nalazi.
	 */
	public void ispisInventaraUIstojProstoriji(String prostorija) {

		Utils.ispisHeaderaInventaraProstorije();

		for (Inventar inventar : this.mapaInventara.values()) {
			if(inventar.getProstorija().equalsIgnoreCase(prostorija)) {
				System.out.println(inventar);
			}
		}
	}

	/*
	 * Metoda za prosleđeni naziv inventara ispisuje sve podatke o inventarima istog naziva.
	 */
	public void ispisInventaraIstogNaziva(String naziv) {

		Utils.ispisHeaderaInventaraIstogNaziva();

		for (Inventar inventar : this.mapaInventara.values()) {
			if(inventar.getIme().equalsIgnoreCase(naziv)) {
				System.out.println(inventar);
			}
		}

	}
	
	/*
	 * Metoda ispisuje sve podatke o inventarima koji se nalaze u istoj prostoriji, duži ih ista osoba i imaju godinu proizvodnje veću od prosleđene.
	 */
	public void ispisInventaraDodatniParametri(String prostorija, String osoba, int zadataGodinaProizvodnje) {

		Utils.ispisHeaderaProstorijaOsobaGodina();

		for (Inventar inventar : this.mapaInventara.values()) {
			if(		inventar.getProstorija().equalsIgnoreCase(prostorija) &&
					inventar.getImePrezime().equalsIgnoreCase(osoba) &&
					inventar.getGodinaProizvodnje() > zadataGodinaProizvodnje){

				System.out.println(inventar);
			}
		}
	}
	//zameni sam
	public void ispisInventaraDatum(String prostorija, String osoba, Date min, Date max) {

		//nastavi da izvlacis u utils
		System.out.printf("%15s %15s %15s %20s %15s %20s %10s\n", "Id", "Ime inventara", "Ime prostorije", "Ime i prezime osobe", "Procenjena vrednost", "Godina proizvodnje", "Datum");
		//nikako toliko komentara, pravis kod za programera ne za bilo koga, kod treba da bude jasan sam po sebi
		//komentarises ponekad ako je nesto ne jasno, ali uglavnom ako je nejasno, nisi ga dobro zapisao
		for (int i = 0; i < this.listaInventara.size(); i++) {

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
	//zameni sam
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
	//zameni sam
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
		double suma = 0;
		double brojac = 0;

		for (Inventar inventar : this.mapaInventara.values()) {
			if(inventar.getImePrezime().equalsIgnoreCase(osoba) &&
					inventar.getGodinaProizvodnje() > godinaProizvodnje){
				suma+= inventar.getProcenjenaVrednost();
				brojac++;
			}
		}

		if(brojac > 0) {
			System.out.println("Prosecna vrednost inventara koji zadovoljava uslove je: " + suma / brojac);
		} else {
			System.out.println("Prosecna vrednost se ne moze izracunati jer nema podataka koji zadovoljavaju zadate uslove.");
		}
	}
	
	/*
	 * Metoda čuva podatke o inventarima u tekstualnom fajlu čiji se naziv prosleđuje.
	 */
	public void save(String path) {
		

		ArrayList<String> lines = new ArrayList<String>();

		for (Inventar inventar : this.mapaInventara.values()) {

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
		
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());

			for (String line: lines) {
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
				

				Inventar inventar = new Inventar(id, ime, prostorija, imePrezime, procenjenaVrednost, godinaProizvodnje, datumZaCuvanje);

				//ovo ce dodati vrednosti ako vec postoje vrednosti u listi,
				//ako hoces cistu mapu napravi novu hash mapu
				this.mapaInventara.put(inventar.getId(), inventar);
				
			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronađena.");
			e.printStackTrace();
		}
	
	}
	
	



}
