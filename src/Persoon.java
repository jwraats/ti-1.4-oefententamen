public class Persoon
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
}
