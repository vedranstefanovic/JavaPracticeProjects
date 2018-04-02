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

	public Inventar hardCopy(Inventar inventarToCopy){
		return new Inventar(
			inventarToCopy.getId(),
			inventarToCopy.getIme(),
			inventarToCopy.getProstorija(),
			inventarToCopy.getImePrezime(),
			inventarToCopy.getProcenjenaVrednost(),
			inventarToCopy.getGodinaProizvodnje(),
			inventarToCopy.getDatum()
		);
	}

	//geteri manje vise, ali nema svrhe da pravis i getter i setter, onda enkapsulacija gubi svrhu, ako bas hoces da pratis tako neka uputstva za to barem
	//obrisi sve settere koje ne koristis i pravi ih po potrebi
	//ali nika nebi trebao da das pristup svim promenjivama, imas konstruktor za to
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
