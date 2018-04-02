package projekat;

public class Kontakt {
	
	/*
	 * Podrazumevaćemo da će svaki kontakt imati jedinstveni broj za id jer se na neki način moraju razlikovati objekti
	 * Na primer može postojati više kontakata koji se zovu Jovan i prezivaju Jovanvic, ali ne može postojati više Jovana Jovanovica koje imaju id 1
	 */
	private int id;
	private String ime;
	private String prezime;
	private String nazivRadnogMesta;
	private String brojProstorije;
	private int brojLokala;
	
	public Kontakt() {
		
	}


	public Kontakt(int id, String ime, String prezime, String nazivRadnogMesta,
			String brojProstorije, int brojLokala) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.nazivRadnogMesta = nazivRadnogMesta;
		this.brojProstorije = brojProstorije;
		this.brojLokala = brojLokala;
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


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getNazivRadnogMesta() {
		return nazivRadnogMesta;
	}


	public void setNazivRadnogMesta(String nazivRadnogMesta) {
		this.nazivRadnogMesta = nazivRadnogMesta;
	}


	public String getBrojProstorije() {
		return brojProstorije;
	}


	public void setBrojProstorije(String brojProstorije) {
		this.brojProstorije = brojProstorije;
	}


	public int getBrojLokala() {
		return brojLokala;
	}


	public void setBrojLokala(int brojLokala) {
		this.brojLokala = brojLokala;
	}
	
	/*
	 * String ima metodu format koja prima kao prvi parametar neki proizvoljan tekst i specijalne karaktere koji će zauzeti
	 * onoliko mesta koliko je to iskazano brojem.
	 * Na primer %15s će uzeti 15 mesta za ispisivanje nekog stringa a %10.2f će uzeti 10 mesta za ispisivanje broja tipa float
	 * od čega će 8 mesta biti zauzeto za ceo deo, a 2 za brojeve iza decimale.
	 * Svaki sledeći parametar u metodi format predstavlja promenljivu čija će se vrednost upisati umesto tih specijalnih karaktera.
	 * Konkretno u metodi toString prvi parametar je "%15s %15s %15s %20s %10.2f %20s":
	 * Uzima se redom 15, 15, 15, 20 mesta za string, zatim 10 mesta za float (8 za ceo deo, 2 za deo iza zareza) i 20 za string
	 * Sledeći parametri su id, ime, prezime, nazivRadnogMesta, brojProstorije, brojLokala koji će se upisati na mestima:
	 * id na mestu prvog %15s
	 * ime na mestu drugog %15s
	 * prezime na mestu trećeg %15s
	 * nazivRadnogMesta na mestu %20s
	 * brojProstorije na mestu %15s
	 * brojLokala na mestu %15s
	 * 
	 * Metodu toString smo napisali u ovom formatu zbog lepšeg ispisa ali se može iskoristiti i ona osnovna verzija 
	 * koja je zakomentarisana ispod samo što će ispis biti "ružniji".
	 * 
	 */
	
	@Override
	public String toString() {
		return String.format("%15s %15s %15s %20s %15s %15s", id, ime, prezime, nazivRadnogMesta, brojProstorije, brojLokala);
	}


	/*
	@Override
	public String toString() {
		return "Kontakt [id=" + id + ", ime=" + ime + ", prezime=" + prezime
				+ ", nazivRadnogMesta=" + nazivRadnogMesta
				+ ", brojProstorije=" + brojProstorije + ", brojLokala="
				+ brojLokala + "]";
	}
*/	
	public static void main(String[] args) {
		Kontakt kontakt = new Kontakt(1, "Milan", "Stojkov", "sekretar", "12A", 23);
		System.out.println(kontakt);
	}

}
