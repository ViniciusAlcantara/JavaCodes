
abstract public class Lutador {
	String nome;
	double poder;
	double v_chute;
	double v_soco;
	double vida;
	double defender;
	
	public Lutador(String n,double p, double vc, double vs, double v, double d)
	{
		nome = n;
		poder = p;
		v_chute = vc;
		v_soco = vs;
		vida = v;
		defender = d;
	}
	
	public void setNome(String n){
		nome = n;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setPoder(int p){
		poder = p;
	}
	
	public double getPoder(){
		return poder;
	}
	
	public void setVChute(double v_ch){
		v_chute = v_ch;
	}
	
	public double getVChute(){
		return v_chute;
	}
	
	public void setVSoco(double v_s){
		v_soco = v_s;
	}
	
	public double getVSoco(){
		return v_soco;
	}
	
	public void setVida(double v){
		vida = v;
	}
	
	public double getVida(){
		return vida;
	}
	public void setDefender(double df){
		
	}
	
	public double getDefender(){
		return vida;
	}
	
	
	abstract public double atacarSocar();
	
	abstract public double atacarChutar();

	abstract public double defender(double poder);

	abstract public double liberarPoder();
	
}
