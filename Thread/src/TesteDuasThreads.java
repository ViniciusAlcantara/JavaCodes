
public class TesteDuasThreads {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadSimples santo = new ThreadSimples("Santos");
		santo.setPriority(4);
		
		ThreadSimples sampa = new ThreadSimples("S�o Paulo");
		sampa.setPriority(6);
		
		ThreadSimples bernado = new ThreadSimples("S�o Bernado");
		bernado.setPriority(5);
		
		Thread.currentThread().setPriority(1);
		
		santo.start();
		sampa.start();
		bernado.start();
		
		System.out.println("Main terminado!");
	}

}
