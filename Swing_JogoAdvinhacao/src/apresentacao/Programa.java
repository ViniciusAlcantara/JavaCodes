package apresentacao;

import negocio.JogoAdivinhacao;

public class Programa {

	public static void main(String[] args) {

		// Cria a janela do jogo.
		JanelaPrincipal tela = new JanelaPrincipal(new JogoAdivinhacao());
		
		// Mostra a janela na tela.
		tela.setVisible(true);
		
	}

}
