import java.util.ArrayList;
import java.util.List;

public class Stamboom
{
	private Persoon ouder;
	private Persoon getrouwdMet;
	private List<Stamboom> kinderen;

	public Stamboom(Persoon e)
	{
		ouder = e;
		kinderen = new ArrayList<Stamboom>();
	}
	
	public Persoon getOuder()
	{
		return ouder;
	}

	public Persoon getGetrouwdMet()
	{
		return getrouwdMet;
	}

	public List<Stamboom> getKinderen()
	{
		return kinderen;
	}
	
	public boolean voegHuwelijkToe(Persoon ouder, Persoon getrouwdMet)
	{
		if (this.ouder.equals(ouder))
		{
			this.getrouwdMet = getrouwdMet;
			return true;
		}
		
		else
		{
			for (Stamboom stamboom : kinderen)
			{
				if (stamboom.voegHuwelijkToe(ouder, getrouwdMet))
					return true;
			}
		}
		
		return false;
	}
	
	public boolean voegKindToeAanPersoon(Persoon ouder, Persoon kind)
	{
		if (this.ouder.equals(ouder))
		{
			kinderen.add(new Stamboom(kind));
			return true;
		}
		
		else
		{
			for (Stamboom stamboom : kinderen)
			{
				if (stamboom.voegKindToeAanPersoon(ouder, kind))
					return true;
			}
		}
		
		return false;
	}
	
	public int getAantalVrouwen()
	{
		int aantal = 0;
		
		if (!ouder.isMan())
		{
			aantal++;
		}
		
		if (getrouwdMet != null && !getrouwdMet.isMan())
		{
			aantal++;
		}
		
		for (Stamboom stamboom : kinderen)
		{
			aantal += stamboom.getAantalVrouwen();
		}
		
		return aantal;
	}
	
	private Persoon jongstePersoon(Persoon jongste)
	{
		if (jongste.compareTo(this.ouder) > 0)
		{
			jongste = this.ouder;
		}
		
		if (getrouwdMet != null && jongste.compareTo(getrouwdMet) > 0)
		{
			jongste = getrouwdMet;
		}
		
		for (Stamboom stamboom : kinderen)
		{
			jongste = stamboom.jongstePersoon(jongste);
		}	
		
		return jongste;
	}

	public Persoon jongstePersoon()
	{
		return jongstePersoon(ouder);
	}
	
	public List<Stamboom> getKinderenVan(String naam)
	{
		if (this.ouder.getNaam().equals(naam))
		{
			return kinderen;
		}
		
		else
		{
			for (Stamboom stamboom : kinderen)
			{
				List<Stamboom> kinderen = stamboom.getKinderenVan(naam);
				
				if (kinderen != null)
					return kinderen;
			}
			
			return null;
		}
		
	}
}
