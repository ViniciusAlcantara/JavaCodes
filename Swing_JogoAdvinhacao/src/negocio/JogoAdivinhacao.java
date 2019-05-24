package negocio;

public class JogoAdivinhacao {
	
	public static final int LIMITE_INFERIOR_PADRAO = 1;
	public static final int LIMITE_SUPERIOR_PADRAO = 1000;
	
	private int numero;
	private int limiteInferior;
	private int limiteSuperior;
	private int tentativas;
	private boolean terminou;
	
	public JogoAdivinhacao() {
		this(LIMITE_INFERIOR_PADRAO, LIMITE_SUPERIOR_PADRAO);
	}

	public JogoAdivinhacao(int limiteSuperior) {
		this(LIMITE_INFERIOR_PADRAO, limiteSuperior);
	}
	
	public JogoAdivinhacao(int limiteInferior, int limiteSuperior) {
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
		reiniciaJogo();
	}
	
	public void reiniciaJogo() {
		numero = (int)(limiteInferior + Math.random()*(limiteSuperior-limiteInferior+1));
		tentativas = 0;
		terminou = false;
	}
	
	public String jogada(int palpite) {
		tentativas++;
		if(palpite > numero)
			return "Palpite muito alto! Tente novamente.";
		else if(palpite < numero)
			return "Palpite muito baixo! Tente novamente.";
		else {
			terminou = true;
			return "Parabens! Voce acertou!";
		}
	}

	public int getNumero() {
		return numero;
	}

	public int getLimiteInferior() {
		return limiteInferior;
	}

	public int getLimiteSuperior() {
		return limiteSuperior;
	}

	public int getTentativas() {
		return tentativas;
	}

	public boolean terminou() {
		return terminou;
	}
}
