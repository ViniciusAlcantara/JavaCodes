import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class ByteStuffing {
	public static String codeByteStuffing(String s)
	{
		char[] x = s.toCharArray();
		Vector y = new Vector();
		int count=0;
		//String nome;
		//nome.charAt(0);
		y.add('#');
		for(int i =0;i<x.length;i++)
		{
			if(x[i]=='@')
			{
				y.add(x[i]);
				y.add(x[i]);
			}else if(x[i]=='#')
			{
				y.add('@');
				y.add(x[i]);
			}else if(x[i]==' ')
			{
				y.add('#');
			}else
			{
				y.add(x[i]);
			}
			count++;
		}		
		y.add('#');
		return y.toString();
	}
	
	public static String decodeByteStuffing(String s)
	{
		char[] x = s.toCharArray();
		Vector y = new Vector();
		int count=0;
		for(int i =0;i<x.length;i++)
		{
			if(i==0 || i==x.length-1){
					continue;
			}else 
			{
				if(s.charAt(i)=='@' && s.charAt(i+1)=='@')
				{
					x[i+1]= (Character) null;
					y.add(x[i]);
				}else if( x[i]=='@' && x[i+1]=='#')
				{
					y.add(s.charAt(i+1));
					x[i+1]=(Character) null;
				}else if(i>0 && i<x.length-1 && x[i]=='#')
				{
					y.add(" ");
				}else
				{
					y.add(s.charAt(i));
				}
			}
		}
			count++;
		return y.toString();
	}
	
	public static char bitParidade(String s)
	{
		byte[] i = s.getBytes();
		//String c = String.toBinaryString(s);
		String[] b = new String[i.length];
		for(int x=0;x<i.length;x++)
		{
			b[x] = Integer.toBinaryString(i[x]); 
		}
		s = b[b.length-1].toString();
		char[] a = s.toCharArray();
		return a[a.length-1];
	}
	
	public static void main(String[] args)
	{
		ByteStuffing bs = new ByteStuffing();
		String x;
		System.out.println("Entre com a string para codificar: ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			String s = in.readLine();
			x = bs.codeByteStuffing(s);
			System.out.println("String codificada byte stuffing: " + x);
			x = bs.decodeByteStuffing(x);
			System.out.println("String decodificada byte stuffing: " + x);
			char a = bs.bitParidade(s);
			System.out.println("Bit de paridade da string codificada: " + a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
