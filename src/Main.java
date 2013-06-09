
public class Main
{
	private static Stamboom getStamboom()
	{
		Persoon opa = new Persoon("Piet", 1930, true);
		
		// Kinderen van opa en oma
		Persoon fred = new Persoon("Fred", 1958, true);
		Persoon paul = new Persoon("Paul", 1954, true);
		Persoon carice = new Persoon("Carice", 1950, false);
		
		// Kinderen van Fred (en Anna)
		Persoon kees = new Persoon("Kees", 1950, true);
		Persoon guus = new Persoon("Guus", 1992, true);
		
		// Kinderen van Carice (en Pieter)
		Persoon merel = new Persoon("Merel", 1985, false);
		
		// Stamboom bouwen
		Stamboom stamboom = new Stamboom(opa);
		
		stamboom.voegKindToeAanPersoon(opa, fred);
		stamboom.voegKindToeAanPersoon(opa, paul);
		stamboom.voegKindToeAanPersoon(opa, carice);
		stamboom.voegKindToeAanPersoon(fred, kees);
		stamboom.voegKindToeAanPersoon(fred, guus);
		stamboom.voegKindToeAanPersoon(carice, merel);
		
		stamboom.voegHuwelijkToe(opa, new Persoon("Clara", 1932, false));
		stamboom.voegHuwelijkToe(fred, new Persoon("Anna", 1959, false));
		stamboom.voegHuwelijkToe(carice, new Persoon("Pieter", 1952, true));
		
		return stamboom;
	}
	
	public static void main(String args[])
	{
		Stamboom stamboom = getStamboom();
	}
}
