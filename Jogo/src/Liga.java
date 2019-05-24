import java.util.Vector;

public class Liga {
	private Vector <Lutador> liga = new Vector();
	public Vector getVector()
	{
		return liga;
	}
	public void setVector(Vector <Lutador> v)
	{
		liga = v;
	}
	public void setLutador(Lutador l)
	{
		liga.add(l);
	}
	public Lutador getLutador(int i)
	{
		return liga.get(i);
	}
	public int retornaTamanhoVetor()
	{
		return liga.size();
	}
}
