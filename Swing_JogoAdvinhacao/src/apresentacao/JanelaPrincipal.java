package apresentacao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import negocio.JogoAdivinhacao;

public class JanelaPrincipal extends JFrame {

	//--------------------------------------------------------
	// C O N S T A N T E S
	//--------------------------------------------------------
	
	// Janela
	public static final String JANELA_TITULO = "Jogo de Adivinhação de Número";
	public static final int JANELA_LARGURA = 400;
	public static final int JANELA_ALTURA = 250;
	public static final Color JANELA_COR_FUNDO = Color.WHITE;
	public static final Color JANELA_COR_TEXTO = new Color(0,125,0);
	
	// Cabecalho
	public static final String CABECALHO_TITULO = "Adivinha Número";
	public static final ImageIcon CABECALHO_IMAGEM = new ImageIcon("titulo.png");
	
	// Caixa de texto
	public static final Color CAIXA_TEXTO_COR_FUNDO = new Color(185,255,185);
	
	// Botoes
	public static final ImageIcon BOTAO_CHUTAR_IMG = new ImageIcon("botaoChutar.png");
	public static final ImageIcon BOTAO_SOBRE_IMG = new ImageIcon("botaoSobre.png");
	public static final ImageIcon BOTAO_SAIR_IMG = new ImageIcon("botaoSair.png");
	public static final Color COR_FUNDO_BOTOES = Color.WHITE;
	
	//--------------------------------------------------------
	// A T R I B U T O S   P R I V A D O S
	//--------------------------------------------------------

	/**
	 * Referencia para um objeto usado para manipular o jogo na memoria.
	 */
	private JogoAdivinhacao jogo;
	
	/**
	 * Label contendo a caixa de mensagens do jogo.
	 */
	private JLabel lbResposta;
	
	/**
	 * Caixa de texto onde o jogador digita os palpites.
	 */
	private JTextField tfPalpite;

	//--------------------------------------------------------
	// C O N S T R U T O R E S
	//--------------------------------------------------------
	
	/**
	 * Construtor da classe. Cria uma nova janela que manipulara o jogo passado como
	 * parametro.
	 * 
	 * @param jogo Objeto que sera manipulado pela janela e que armazena o estado do
	 *             jogo.
	 */
	public JanelaPrincipal(JogoAdivinhacao jogo) {
		
		// Chamada ao construtor da superclasse (JFrame) que recebe um String contendo
		// o titulo da janela.
		super(JANELA_TITULO);
		
		// Inicializa o atributo "jogo" com o objeto passado no parametro. Caso o 
		// valor passado para o parametro for "null", inicia o atributo com um novo
		// objeto padrao (construido a partir do construtor que nao recebe parametros
		// da classe JogoAdivinhacao). Este atributo deve apontar para um objeto que
		// armazene o estado atual do jogo, ou seja, que armazene os dados do jogo
		// (numero sorteado, numero de tentativas, etc). Todas as operacoes realizadas
		// o jogo serao feitas em cima deste objeto.
		if(jogo != null)
			this.jogo = jogo;
		else
			this.jogo = new JogoAdivinhacao();
		
		// Define o tamanho da janela.
		setSize(JANELA_LARGURA, JANELA_ALTURA);
		
		// Define que ao fechar a janela, o programa deve ser encerrado.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Define que a posicao inicial da janela sera no centro da tela.
		setLocationRelativeTo(null);
		
		// Define que o tamanho da janela nao pode ser alterado pelo usuario. O botao
		// de maximizar estara desabilitado e o usuario nao podera alterar o tamanho
		// da janela clicando e arrastando o mouse em suas bordas.
		setResizable(false);
		
		// Define o JPanel que sera colocado no miolo da janela. Este painel sera 
		// criado e retornado pelo metodo criaPainelPrincipal, define mais abaixo.
		setContentPane(criaPainelPrincipal());
	}

	//--------------------------------------------------------
	// M E T O D O S   P R I V A D O S
	//--------------------------------------------------------
	
	/**
	 * Cria e preenche o painel do conteudo da janela.
	 * 
	 * @return Objeto da classe JPanel contendo o painel.
	 */
	private JPanel criaPainelPrincipal() {
		
		// Cria objeto para o painel principal, utilizando o gerenciador de layout
		// GridLayout. Este objeto organizara os componentes do painel em 4 linhas.
		// A ideia eh usar a primeira para o titulo do programa, a segunda para a
		// digitacao do numero, a terceira para mostrar o numero de tentativas jah
		// efetuadas e a quarta para botoes contendo outras opcoes.
		JPanel pnPrincipal = new JPanel(new GridLayout(4,1));

		// Altera a cor de fundo do painel para a cor de fundo da janela.
		pnPrincipal.setBackground(JANELA_COR_FUNDO);

		// Cria e adiciona cada linha do painel de conteudo. 
		pnPrincipal.add(criaCabecalho());
		pnPrincipal.add(criaPainelEntradaNumero());
		pnPrincipal.add(lbResposta = criaPainelMensagem());
		pnPrincipal.add(criaPainelBotoes());
		
		// Retorna o objeto contendo o painel criado
		return pnPrincipal;
	}

	/**
	 * Cria o cabecalho da janela do jogo.
	 * 
	 * @return Objeto da classe JLabel contendo o cabecalho.
	 */
	private JLabel criaCabecalho() {
		// Cria o label que contera o cabecalho, definindo o texto, a imagem e
		// a posicao do texto no label (neste caso, centralizado).
		JLabel lbTitulo = new JLabel(CABECALHO_TITULO, CABECALHO_IMAGEM, SwingConstants.CENTER);
		
		// Altera o tamanho da fonte usada no label para 30. O F apos o numero 
		// foi colocado para que o valor seja considerado do tipo float.
		lbTitulo.setFont(lbTitulo.getFont().deriveFont(30F));
		
		// Altera a cor do texto do label.
		lbTitulo.setForeground(JANELA_COR_TEXTO);
		
		return lbTitulo;
	}
	
	/**
	 * Cria um painel para conter a parte da janela onde o jogador digitara seu
	 * palpite. Essa parte da tela contera um label com um texto mostrando onde
	 * o jogador deve digitar o palpite, uma caixa de texto onde o jogador digitara
	 * o palpite e um botao para que o palpite digitado seja processado pelo 
	 * programa.
	 * 
	 * @return Um objeto da classe JPanel contendo o painel.
	 */
	private JPanel criaPainelEntradaNumero() {
		
		// Cria o painel com um layout de fluxo com os componentes centralizados,
		// 2 pixels de distancia entre cada um na horizontal e 10 pixels de distancia
		// na vertical. Este tamanho grande na vertical foi usado para que os
		// componentes ficassem mais distanciados do cabecalho. 
		JPanel pnMeio = new JPanel(new FlowLayout(FlowLayout.CENTER,2,10));
		
		// Define que o painel serah transparente (tera a mesma cor de fundo do que
		// estiver por baixo).
		pnMeio.setOpaque(false);
		
		// Cria o label que indicara ao usuario onde digitar o palpite, definindo
		// tambem sua fonte (tamanho 15 e negrito) e sua cor de texto.
		JLabel lbPalpite = new JLabel("Digite o seu palpite: ");
		lbPalpite.setFont(lbPalpite.getFont().deriveFont(Font.BOLD, 15));
		lbPalpite.setForeground(JANELA_COR_TEXTO);
		pnMeio.add(lbPalpite);
		
		// Cria a caixa de texto onde o jogador devera escrever seus palpites. Esta
		// caixa de texto eh associada com o atributo tfPalpite, para acesso posterior
		// pelo programa. Na criacao do objeto definimos aqui um numero de colunas (4)
		// que eh usado pelo Swing no calculo do comprimento do componente. Logo apos,
		// sao definidos a fonte usada (negrito com tamanho 30), cor de fundo e cor de
		// texto do componente.
		tfPalpite = new JTextField(4);
		tfPalpite.setFont(tfPalpite.getFont().deriveFont(Font.BOLD, 30));
		tfPalpite.setBackground(CAIXA_TEXTO_COR_FUNDO);
		tfPalpite.setForeground(JANELA_COR_TEXTO);
		
		// Define um listener (ouvinte) para este componente. Toda vez que se pressionar
		// a tecla ENTER no componente, sera chamado o metodo actionPerformed do objeto
		// listener.
		tfPalpite.addActionListener(new PalpiteListener(this));
		
		// Adiciona a caixa de texto ao painel.
		pnMeio.add(tfPalpite);
	
		// Adiciona um botao para processar o palpite do usuario. Ao criar o botao
		// aqui abaixo, especificamos o texto do botao e uma imagem a ser mostrada
		// ao lado do texto. Logo apos sao definidas a fonte (negrito e tamanho 15)
		// e a cor de fundo do botao.
		JButton btPalpite = new JButton("Chutar!", BOTAO_CHUTAR_IMG);
		btPalpite.setFont(btPalpite.getFont().deriveFont(Font.BOLD, 15));
		btPalpite.setBackground(COR_FUNDO_BOTOES);
		
		// Adicionamos um listener ao botao (o mesmo da caixa de texto). Toda vez
		// que se clicar sobre o botao, o metodo actionPerformed do objeto listener
		// sera chamado.
		btPalpite.addActionListener(tfPalpite.getActionListeners()[0]);
		
		// Adiciona o botao ao painel.
		pnMeio.add(btPalpite);
		
		return pnMeio;
	}

	/**
	 * Cria o painel para mostrar mensagens ao usuario sobre a situacao do jogo.
	 * 
	 * @return Um objeto da classe JLabel contendo a caixa de mensagens.
	 */
	private JLabel criaPainelMensagem() {
		// Cria um label para conter as mensagens, mostrando inicialmente o texto
		// "Bem-vindo!". O texto estara centralizado e tera a cor e fonte (tamanho 20)
		// definidas abaixo.
		JLabel lbResposta = new JLabel("Bem-vindo!!");
		lbResposta.setHorizontalAlignment(SwingConstants.CENTER);
		lbResposta.setForeground(JANELA_COR_TEXTO);
		lbResposta.setFont(lbResposta.getFont().deriveFont(20F));
		return lbResposta;
	}
	
	/**
	 * Cria um painel contendo alguns botoes com opcoes extras.
	 * 
	 * @return Um objeto da classe JPanel contendo o painel.
	 */
	private JPanel criaPainelBotoes() {
		
		// Cria o painel com layout de fluxo mostrando os componentes centralizados.
		JPanel pnBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		// O painel sera transparente.
		pnBotoes.setOpaque(false);
		
		// Cria um botao para mostrar um texto explicativo sobre o jogo na tela.
		JButton btConfig = new JButton("Sobre", BOTAO_SOBRE_IMG);
		btConfig.setBackground(COR_FUNDO_BOTOES);
		btConfig.addActionListener(new SobreListener(this));
		pnBotoes.add(btConfig);
		
		// Cria um botao para sair do programa.
		JButton btSair = new JButton("Sair", BOTAO_SAIR_IMG);
		btSair.setBackground(COR_FUNDO_BOTOES);
		btSair.addActionListener(new SairListener());
		pnBotoes.add(btSair);
		
		return pnBotoes;
	}
	
	//--------------------------------------------------------
	// C L A S S E S   I N T E R N A S   P R I V A D A S
	//--------------------------------------------------------
	
	/**
	 * Classe listener usada na caixa de texto e no botao "Chutar!" para processar
	 * o palpite do jogador.
	 */
	private class PalpiteListener implements ActionListener {

		/**
		 * Referencia para a janela do jogo.
		 */
		private JFrame janela;
		
		/**
		 * Construtor da classe.
		 *  
		 * @param janela Referencia para a janela do jogo.
		 */
		public PalpiteListener(JFrame janela) {
			this.janela = janela;
		}
		
		/**
		 * Executado quando uma determinada acao no compomente for feita (clicar no
		 * botao, teclar ENTER na caixa de texto).
		 * 
		 * @param e Dados do evento ocorrido.
		 */
		public void actionPerformed(ActionEvent e) {
			
			// Se o jogo terminou, nao faz nada. Apenas mostra uma mensagem.
			if(jogo.terminou())
				JOptionPane.showMessageDialog(janela, "O jogo já terminou!", "ERRO", JOptionPane.ERROR_MESSAGE);
			else {
				
				// Caso o jogo nao tenha terminado, tenta processar o palpite.
				try {
					
					// Pega o texto digitado na caixa de texto e tenta converte-lo para um inteiro.
					int palpite = Integer.parseInt(tfPalpite.getText());
					
					// Faz a jogada e mostra o resultado para o jogador.
					JOptionPane.showMessageDialog(
							janela,
							jogo.jogada(palpite),
							"Resposta",
							JOptionPane.INFORMATION_MESSAGE);

					// Mostra uma mensagem na caixa de mensagens da janela, mostrando
					// o numero de tentativas efetuadas.
					if(jogo.terminou()) 
						lbResposta.setText("Você acertou em " + jogo.getTentativas() + " tentativa(s)");
					else if(jogo.getTentativas() == 1)
						lbResposta.setText("Você já realizou " + jogo.getTentativas() + " tentativa");
					else	
						lbResposta.setText("Você já realizou " + jogo.getTentativas() + " tentativas");

					// Força que o label contendo a caixa de mensagens seja redesenhado.
					lbResposta.invalidate();
			
				} catch (NumberFormatException exc) { // Caso o jogador tenha digitado qualquer coisa menos um numero.
					JOptionPane.showMessageDialog(janela, "O palpite que você digitou não é um número inteiro!", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Classe para contrucao de listeners usada no botao "Sair".
	 */
	private class SairListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}
	
	/**
	 * Classe para contrucao de listeners usada no botao "Sobre".
	 */
	private class SobreListener implements ActionListener {

		private JFrame janela;
		
		public SobreListener(JFrame janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(
					janela,
					"Jogo de Adivinhação de Números. Para escrever seus palpites,\n" +
					"utilize a caixa de texto verde no centro da janela e clique\n" +
					"em 'Chutar!'.",
					"Sobre...",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
} // FIM DA CLASSE JanelaPrincipal
