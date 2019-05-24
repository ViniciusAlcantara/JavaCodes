
public class Dhalsim extends Lutador{
	public Dhalsim(String n,double p, double vc, double vs, double v, double d)
	{
		super(n,p,vc,vs,v,d);
	}
	public double atacarSocar(){
		return v_soco;
	}
	public double atacarChutar()
	{
		return v_chute;
	}

	public double defender(double poder){
		return poder - defender;
	}

	public double liberarPoder(){
		return poder;
	}
}
