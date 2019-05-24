import java.util.Scanner;
import java.awt.List;
import java.io.File;
import java.util.Random;
import java.util.Vector;

public class Jogo{
	public Liga inicializa()
	{
		Balrog balrog = new Balrog("Balrog",40,15,20,100,20);
		Liga l = new Liga();
		l.setLutador(balrog);
		Blanka blanka = new Blanka("Blanka",45,25,25,100,25);
		l.setLutador(blanka);
		Chunli chunli = new Chunli("Chun-Li",50,20,30,100,15);
		l.setLutador(chunli);
		Dhalsim dhalsim = new Dhalsim("Dhalsim",35,20,20,100,18);
		l.setLutador(dhalsim);
		Guile  guile = new Guile("Guile",40,20,25,100,22);
		l.setLutador(guile);
		Honda honda = new Honda("Honda",50,30,20,100,25);
		l.setLutador(honda);
		Ken ken = new Ken("Ken",55,30,30,100,20);
		l.setLutador(ken);
		Ryu ryu = new Ryu("Ryu",55,20,25,100,15);
		l.setLutador(ryu);
		Sagat sagat = new Sagat("Sagat",53,30,35,100,35);
		l.setLutador(sagat);
		Thawke thawke = new Thawke("Thawke",45,20,25,100,15);
		l.setLutador(thawke);
		Vega vega = new Vega("Vega",54,30,25,100,25);
		l.setLutador(vega);
		Zangief zangief = new Zangief("Zangief",100,20,25,100,15);
		l.setLutador(zangief);
		
		return l;
	}
	
	public char[] jogadas(File arquivo)
	{
		LerArquivo la = new LerArquivo(arquivo);
		java.util.List<String> x = la.getDataFile();
		String v = x.toString();
		return v.toCharArray();
	}
	
	public Lutador retornaProximoLutador(Liga v)
	{
		Random r = new Random();
		int n = r.nextInt(v.retornaTamanhoVetor());
		Lutador lut = v.getLutador(n);
		if(lut.getVida()==0)
		{
			return retornaProximoLutador(v);
		}else
		{
			return lut;
		}
	}
	public static void main(String[] args)
	{
		boolean y = true;
		while(y == true)
		{
			Jogo j = new Jogo();
			Liga x = j.inicializa();
			Luta luta = new Luta();
			Lutador retorno;
			Scanner ler = new Scanner(System.in);
			System.out.println("0-  Balrog");
			System.out.println("1-  Blanka");
			System.out.println("2-  Chun-Li");
			System.out.println("3-  Dhalsim");
			System.out.println("4-  Guile");
			System.out.println("5-  Honda");
			System.out.println("6-  Ken");
			System.out.println("7-  Ryu");
			System.out.println("8-  Sagat");
			System.out.println("9-  Thawke");
			System.out.println("10- Vega");
			System.out.println("11- Zangief");
			
			System.out.println("Entre com a opcao do lutador que voce quer:");
			int jogador1 = ler.nextInt();
			Lutador lutador1 = x.getLutador(jogador1);
			for(int i=0;i<x.retornaTamanhoVetor();i++)
			{
				File dir = new File("jogadas1.txt");
				char[] jogadas1 = j.jogadas(dir);
				File arquivo = new File("jogadas2.txt");
				char[] jogadas2 = j.jogadas(arquivo);
				Lutador lutador2 = j.retornaProximoLutador(x);
				System.out.println("Seu proximo adversario sera: " + lutador2.getNome());
				try{
					retorno = luta.rodada(lutador1,lutador2,jogadas1, jogadas2);
					if(retorno.getNome()==lutador1.getNome())
					{
						lutador1.setVida(100);
					}else
					{
						System.out.println("Game Over!");
						System.out.println("Deseja reiniciar?(yes=1/no=2)");
						int opcao;
						opcao = ler.nextInt();
						if(opcao==1)
						{
							i = x.retornaTamanhoVetor();
						}else
						{
							i = x.retornaTamanhoVetor();
							y = false;
						}
					}
				}catch(Exception e)
				{
					System.out.println("Erro:" + e);
					i = x.retornaTamanhoVetor();
					y = false;
				}
			}
		}
		
	}
}