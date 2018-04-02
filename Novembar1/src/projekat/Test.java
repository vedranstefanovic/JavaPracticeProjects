package projekat;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Test {

	public static Scanner scanner = new Scanner(System.in);

	/*
	 * Metoda proverava da li je uneti string sa tastature ceo broj.
	 */
	public static boolean isNumber(String string) {
		try {
			Integer.parseInt(string);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void unesiKontakt(Imenik imenik) {

		int id;
		String ime;
		String prezime;
		String nazivRadnogMesta;
		String brojProstorije;
		int brojLokala;
		String idKontakta = null;
		do {
			System.out.print("Identifikacioni broj: ");
			idKontakta = scanner.nextLine();
		} while(!isNumber(idKontakta));
		id = Integer.parseInt(idKontakta);
		System.out.print("Ime kontakta: ");
		ime = scanner.nextLine();
		System.out.print("Prezime kontakta: ");
		prezime = scanner.nextLine();
		System.out.print("Naziv radnog mesta kontakta: ");
		nazivRadnogMesta = scanner.nextLine();
		System.out.print("Broj prostorije: ");
		brojProstorije = scanner.nextLine();
		String brLokala = null;
		do {
			System.out.print("Broj lokala: ");
			brLokala = scanner.nextLine();
		} while(!isNumber(brLokala));
		
		brojLokala = Integer.parseInt(brLokala);
		Kontakt kontakt = new Kontakt(id, ime, prezime, nazivRadnogMesta, brojProstorije, brojLokala);
		boolean provera = imenik.dodavanjeKontakta(kontakt);
		if(provera == true) {
			System.out.println("Kontakt je uspešno dodat.");
		} else {
			System.out.println("Kontakt nije uspešno dodat.");
		}
	}

	public static void ispisKontakata(Imenik imenik) {

		imenik.ispisKontakata();
	}

	public static void brisanjeKontakta(Imenik imenik) {

		int id;
		String idKontakta = null;
		do {
			System.out.println("Identifikacioni broj kontakta za brisanje: ");
			idKontakta = scanner.nextLine();
		} while(!isNumber(idKontakta));
		id = Integer.valueOf(idKontakta);
		Kontakt provera = imenik.brisanjeKontakta(id);
		if(provera == null) {
			System.out.println("Kontakt sa zadatim brojem ne postoji.");
		}
	}

	public static void kontaktiULokalu(Imenik imenik) {

		String brLokala = null;
		do {
			System.out.println("Unesite broj lokala: ");
			brLokala = scanner.nextLine();
		} while(!isNumber(brLokala));
		int brojLokala = Integer.valueOf(brLokala);
		imenik.ispisKontakataSaIstimLokalom(brojLokala);
	}

	public static void kontaktiNaIstomRadnomMestu(Imenik imenik) {

		System.out.println("Unesite naziv radnog mesta: ");
		String radnoMesto = scanner.nextLine();
		imenik.ispisKontakataSaIstimRadnimMestom(radnoMesto);
	}

	public static void kontaktiSaIstimImenom(Imenik imenik) {

		System.out.println("Unesite ime kontakta: ");
		String ime = scanner.nextLine();
		imenik.ispisKontakataSaIstimImenom(ime);
	}

	public static void kontaktiPoUslovima8(Imenik imenik) {

		System.out.println("Unesite ime kontakta: ");
		String ime = scanner.nextLine();
		System.out.println("Unesite prezime kontakta: ");
		String prezime = scanner.nextLine();
		System.out.println("Unesite početna slova naziva radnog mesta: ");
		String radnoMesto = scanner.nextLine();
		imenik.ispisKontakataSaZadatimUslovima(ime, prezime, radnoMesto);
	}

	public static void izmenaKontakta(Imenik imenik) {

		String idKontakta = null;
		do {
			System.out.println("Unesite identifikacioni broj kontakta: ");
			idKontakta = scanner.nextLine();
		} while(!isNumber(idKontakta));
		int id = Integer.valueOf(idKontakta);
		String ime;
		String prezime;
		String nazivRadnogMesta;
		String brojProstorije;
		int brojLokala;
		System.out.print("Ime kontakta: ");
		ime = scanner.nextLine();
		System.out.print("Prezime kontakta: ");
		prezime = scanner.nextLine();
		System.out.print("Naziv radnog mesta: ");
		nazivRadnogMesta = scanner.nextLine();
		System.out.print("Broj prostorije: ");
		brojProstorije = scanner.nextLine();
		String brLokala = null;
		do {
			System.out.print("Broj lokala: ");
			brLokala = scanner.nextLine();
		} while(!isNumber(brLokala));
		brojLokala = Integer.valueOf(brLokala);
		Kontakt kontakt = new Kontakt(id, ime, prezime, nazivRadnogMesta, brojProstorije, brojLokala);
		Kontakt provera = imenik.izmenaKontakta(id, kontakt);
		if(provera != null) {
			System.out.println("Izmena je uspešno izvršena.");
		} else {
			System.out.println("Izmena je neuspešno izvršena.");
		}
	}

	public static void main(String[] args) {

		Imenik imenik = new Imenik();
		//da ne biste prvo morali da zakomentarišete liniju sa load pri prvom pokretanju jer će se Eclipse buniti da datoteka ne postoji
		//onda prvo pitamo da li datoteka kontakti.txt postoji, pa ako postoji učitaj informacije a ako ne postoji preskoči učitavanje
		//preskakanje će se desiti pri prvom pokretanju pod pretpostavkom da nismo ručno kreirali datoteku
		if(Files.exists(Paths.get("kontakti.txt"))) {
			imenik.load("kontakti.txt");
		}
		
		String answer = null;

		do {

			System.out.println("Meni:");
			System.out.println("1. Unesi kontakt");
			System.out.println("2. Ispis svih kontakata");
			System.out.println("3. Brisanje kontakta");
			System.out.println("4. Ispis kontakata u istom lokalu");
			System.out.println("5. Ispis kontakata sa istim radnim mestom");
			System.out.println("6. Ispis kontakata sa istim imenom");
			System.out.println("7. Ispis sa istim imenom, prezimenom i nazivom radnog mesta koje počinje sa prosleđenim stringom");
			System.out.println("8. Izmena kontakta");
			System.out.println("x. Izlaz");

			answer = scanner.nextLine();

			switch (answer) {
			case "1":
				unesiKontakt(imenik);
				break;
			case "2":
				ispisKontakata(imenik);
				break;
			case "3":
				brisanjeKontakta(imenik);
				break;
			case "4":
				kontaktiULokalu(imenik);
				break;
			case "5":
				kontaktiNaIstomRadnomMestu(imenik);
				break;
			case "6":
				kontaktiSaIstimImenom(imenik);
				break;
			case "7":
				kontaktiPoUslovima8(imenik);
				break;
			case "8":
				izmenaKontakta(imenik);
				break;
			case "x":
				break;
			default:
				System.out.println("Pogresan izbor opcije. Pokusajte ponovo.");
			}

		} while (!answer.equals("x"));

		imenik.save("kontakti.txt");
		scanner.close();

	}

}
