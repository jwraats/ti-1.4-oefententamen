import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Main
{
	private Stamboom getStamboom()
	{
		Persoon opa = new Persoon("Piet", 1930, true);

		// Kinderen van opa en oma
		Persoon fred = new Persoon("Fred", 1958, true);
		Persoon paul = new Persoon("Paul", 1954, true);
		Persoon carice = new Persoon("Carice", 1992, false);

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

	public Main()
	{
		Stamboom stamboom = getStamboom();

		System.out.println("Aantal vrouwen in stamboom: " + stamboom.getAantalVrouwen());
		System.out.println("Jongste persoon: " + stamboom.jongstePersoon());

		try
		{
			ServerSocket server = new ServerSocket(8000);

			while (true)
			{
				Socket socket = server.accept();

				Thread th = new Thread(new Connection(socket));
				th.start();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		new Main();
	}

	public class Connection implements Runnable
	{
		private Socket socket;

		public Connection(Socket socket)
		{
			this.socket = socket;
		}

		@Override
		public void run()
		{
			try
			{
				// This is *one* way to send strings across, using newlines as terminator
				// DataOutputStream and DataInputStream are more useful when you're sending about numbers
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				while (true)
				{
					String line = input.readLine();

					if (line == null)
					{
						break;
					}

					if (line.equals("bye"))
					{
						socket.close();
						break;
					}

					else
					{
						List<Stamboom> kinderen = getStamboom().getKinderenVan(line);

						String str;

						if (kinderen == null)
						{
							str = line + " bestaat niet.";
						}

						else
						{
							str = line + " heeft " + kinderen.size() + " kinderen: ";

							boolean first = true;

							for (Stamboom kind : kinderen)
							{
								if (!first)
								{
									str += ", ";
								}

								first = false;
								str += kind.getOuder();
							}
						}

						output.write(str);
						output.newLine();
						output.flush();
					}
				}

			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
