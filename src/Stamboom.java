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
}
