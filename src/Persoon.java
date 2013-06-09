public class Persoon implements Comparable<Persoon>
{
	private String naam;
	private int geboorteJaar;
	private boolean isMan;
	
	public Persoon(String naam, int geboorteJaar, boolean isMan)
	{
		super();
		this.naam = naam;
		this.geboorteJaar = geboorteJaar;
		this.isMan = isMan;
	}

	public String getNaam()
	{
		return naam;
	}

	public int getGeboorteJaar()
	{
		return geboorteJaar;
	}

	public boolean isMan()
	{
		return isMan;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Persoon))
			return false;
		
		if (obj == this)
			return true;
		
		Persoon persoon = (Persoon)obj;
		return (persoon.naam == this.naam && persoon.geboorteJaar == this.geboorteJaar && persoon.isMan == this.isMan); 
	}

	@Override
	public int compareTo(Persoon o)
	{
		return o.geboorteJaar - this.geboorteJaar;
	}
}
