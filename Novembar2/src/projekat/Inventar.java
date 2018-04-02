package projekat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Inventar je klasa čije objekte ćemo koristiti u klasi Firma.
 * 
 * @author Bojana/Milan
 *
 */
public class Inventar {
	/*
	 * Podrazumevaćemo da će svaki inventar imati jedinstveni broj za id jer se na neki način moraju razlikovati objekti
	 * Na primer može postojati više inventara koji se zovu stolica, ali ne može postojati više stolica koje imaju id 1
	 */
	private int id; 
	private String ime;
	private String prostorija;
	private String imePrezime;
	private double procenjenaVrednost;
	private int godinaProizvodnje;
	private Date datum;
	
	public Inventar() {
		
	}
	
	public Inventar(int id, String ime, String prostorija, String imePrezime,
			double procenjenaVrednost, int godinaProizvodnje, Date datum) {
		this.id = id;
		this.ime = ime;
		this.prostorija = prostorija;
		this.imePrezime = imePrezime;
		this.procenjenaVrednost = procenjenaVrednost;
		this.godinaProizvodnje = godinaProizvodnje;
		this.datum = datum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getProstorija() {
		return prostorija;
	}

	public void setProstorija(String prostorija) {
		this.prostorija = prostorija;
	}

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public double getProcenjenaVrednost() {
		return procenjenaVrednost;
	}

	public void setProcenjenaVrednost(double procenjenaVrednost) {
		this.procenjenaVrednost = procenjenaVrednost;
	}

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	/*
	 * String ima metodu format koja prima kao prvi parametar neki proizvoljan tekst i specijalne karaktere koji će zauzeti
	 * onoliko mesta koliko je to iskazano brojem.
	 * Na primer %15s će uzeti 15 mesta za ispisivanje nekog stringa a %10.2f će uzeti 10 mesta za ispisivanje broja tipa float
	 * od čega će 8 mesta biti zauzeto za ceo deo, a 2 za brojeve iza decimale.
	 * Svaki sledeći parametar u metodi format predstavlja promenljivu čija će se vrednost upisati umesto tih specijalnih karaktera.
	 * Konkretno u metodi toString prvi parametar je "%15s %15s %15s %20s %10.2f %20s":
	 * Uzima se redom 15, 15, 15, 20 mesta za string, zatim 10 mesta za float (8 za ceo deo, 2 za deo iza zareza) i 20 za string
	 * Sledeći parametri su id, ime, prostorija, imePrezime, procenjenaVrednost, godinaProizvodnje koji će se upisati na mestima:
	 * id na mestu prvog %15s
	 * ime na mestu drugog %15s
	 * prostorija na mestu trećeg %15s
	 * imePrezime na mestu %20s
	 * procenjenaVrednost na mestu %10.2f
	 * godinaProizvodnje na mestu %20s
	 * 
	 * Metodu toString smo napisali u ovom formatu zbog lepšeg ispisa ali se može iskoristiti i ona osnovna verzija 
	 * koja je zakomentarisana ispod samo što će ispis biti "ružniji".
	 * 
	 */
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		return String.format("%15s %15s %15s %20s %10.2f %20s %20s", id, ime, prostorija, imePrezime, procenjenaVrednost, godinaProizvodnje, sdf.format(datum));
	}
	
	/*@Override
	public String toString() {
		return "Inventar [id=" + id + ", ime=" + ime + ", prostorija="
				+ prostorija + ", imePrezime=" + imePrezime
				+ ", procenjenaVrednost=" + procenjenaVrednost
				+ ", godinaProizvodnje=" + godinaProizvodnje + "]";
	}*/
	
	
	

}
