package vending;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Esta classe representa a m�quina de vender produtos. Ela implementa uma
 * poss�vel interface de acesso do usu�rio com a m�quina de vender itens.
 * 
 * Nesse caso em particular, a m�quina possui uma interface via linha de
 * comando, definida por um conjunto de instru��es b�sicas, definidas a seguir.
 * 
 * @author Auri Marcelo Rizzo Vincenzi
 */

public class VendingMachine {
	final private int COIN = 25; /*
								 * Valor das moedas aceitas. No caso, s� s�o
								 * aceitas moedas de 25 centavos.
								 */
	private int totValue; // Valor total depositado
	private int currValue; // Valor atual depositado
	private Dispenser d;

	/**
	 * Construtor da classe, inicializa os contadores e o dispenser da m�quina.
	 */
	public VendingMachine() {
		totValue = 0;
		currValue = 0;
		d = new Dispenser();
	}

	/**
	 * Este m�todo simula que uma nova moeda de 25 centavos foi depositada na
	 * m�quina. Ele retorna o valor atual j� depositado na m�quina.
	 * 
	 * @param - nenhum par�metro requerido.
	 * @return - o valor total depositado em centavos.
	 */
	public int insertCoin(int coin) {
		if(validateCoin(coin)){
		currValue += coin;
		}else{
			System.out.println("Valor n�o � igual a 25, 50, 75 ou 100.");
		}
		return currValue;
		
	}
	
	public boolean validateCoin(int coin){
		return coin % 25 == 0 && coin > 0 && coin <= 100; 
	}

	/**
	 * Este m�todo simula a devolu��o das moedas ao usu�rio ou a devolu��o do
	 * troco.
	 * 
	 * @param - nenhum par�metro requerido..
	 * @return - o valor atual em centavos remanescente no porta moeda ou zero
	 *         caso n�o haja saldo a ser devolvido.
	 */
	public int returnCoin() {
		int value = currValue;
		System.out.println("Saldo resgatado: " + currValue);
		if (currValue != 0)
			currValue = 0;

		return value;
	}

	/**
	 * Este m�todo simula a requisi��o de determinado item a ser comprado.
	 * 
	 * @param selection
	 *            o �ndice do item que se deseja comprar que corresponde a um
	 *            n�mero entre 1 e 20.
	 * 
	 * @return O cr�dito remanescente ap�s a compra do item no caso de sucesso.
	 *         Em caso de falha, um c�digo de erro � retornado, conforme tabela
	 *         de c�digo descrita abaixo:
	 *         <ul>
	 *         <li>-1: nenhuma moeda depositada.
	 *         <li>-2: cr�dito insuficiente para a compra do item.
	 *         <li>-3: �ndice de item inv�lido (fora do intervalo especificado).
	 *         <li>-4: item desejado indispon�vel para compra. Esgotado.
	 *         </ul>
	 * 
	 */
	public int vendItem(int selection) {
		int expense;
		if(d.checkAvailable(selection)){
			d.removeItem(selection);
		}
		else{
			System.out.println("Item indispon�vel");
			return -4;
		}
		expense = d.dispense(currValue, selection);
		if (expense > 0) {
			totValue += expense;
			currValue -= expense;
			expense = currValue;
		}

		return expense;
	}
	
	public void showItem(){
		d.printItem();
	}
	
	public static void menu(){
		System.out.println("/////////////////////////////////////////////");
		System.out.println("MENU");
		System.out.println("Inserir Moeda: insertCoin <valor da moeda>");
		System.out.println("Remover Moedas: returnCoin");
		System.out.println("Mostrar itens: showItems");
		System.out.println("Comprar Item: vendItem <valor entre 1 e 20>");
		System.out.println("/////////////////////////////////////////////");
	}

	/**
	 * Este m�todo simula o comportamento da m�quina de vendas. As a��es
	 * permitidas para realizar suas tarefas s�o as de inserir moedas, solicitar
	 * a devolu��o das moedas ou solicitar a compra de determinado item. Para
	 * realizar tais a��es, o usu�rio interage com o sistema enviando os
	 * comandos definidos a seguir: <BR>
	 * <UL>
	 * <LI>insertCoin;
	 * <LI>returnCoin; and
	 * <LI>vendItem <item_number>. <\UL>
	 * <P>
	 * Essas opera��es podem ser fornecidas via teclado ou por meio de um
	 * arquivo texto contendo, em cada linha, a invoca��o de uma das opera��es
	 * que se deseja realizar. Para encerrar a execu��o via teclado, basta
	 * pressionar CTRL+D.
	 * 
	 * Ap�s executar cada uma das opera��es, uma mensagem � exebida no display
	 * da m�quina para o usu�rio tomar conhecimento do ocorrido e se ele obteve
	 * sucesso ou n�o em sua solicita��o.
	 */
	static public void main(String args[]) throws Exception {
		BufferedReader drvInput;
		String tcLine = new String();

		String methodName = new String();

		VendingMachine machine = new VendingMachine();

		if (args.length < 1) // leitura das opera��es via teclado
			drvInput = new BufferedReader(new InputStreamReader(System.in));
		else
			// leitura das opera��es via arquivo texto
			drvInput = new BufferedReader(new FileReader(args[0]));

		System.out.println("VendingMachine LIGADA");
		menu();
		// para entradas via teclado, CTRL+D p�ra a execu��o da m�quina
		while ((tcLine = drvInput.readLine()) != null) {
			StringTokenizer tcTokens = new StringTokenizer(tcLine);

			if (tcTokens.hasMoreTokens())
				methodName = tcTokens.nextToken();

			int value = 0;
				
			// Uma moeda � inserida
			if (methodName.equals("insertCoin")) {
				value = machine.insertCoin(Integer.parseInt(tcTokens.nextToken()));
				System.out.println("Cr�dito atual = " + value);
			} // Uma ou mais moedas s�o devolvidas (caso exista alguma)
			else if (methodName.equals("returnCoin")) {
				value = machine.returnCoin();
				if (value == 0)
					System.err.println("Sem cr�dito para devolu��o");
				else
					System.out.println("Retire suas moedas");
			} // Solicita��o para a entrega de determinado item
			else if (methodName.equals("vendItem")) {
				Integer selection = new Integer(tcTokens.nextToken());

				value = machine.vendItem(selection.intValue());
				if (value >= 0) {
					System.out.println("Retire o item desejado!!!");
					System.out.println("Saldo atual = " + value);
				} else if (value == -1) {
					System.out.println("Nenhuma moeda inserida!!!");
				} else if (value == -3) {
					System.out.println("O item selecionado (" + selection
							+ ") � inv�lido!!!");
				} else if (value == -4) {
					System.out.println("O item selecionado (" + selection
							+ ") est� esgotado!!!");
				} else if (value == -2) {
					System.out
							.println("O cr�dito atual � insuficiente para a compra do item "
									+ selection + ".");
				}
			} else if (methodName.equals("showItems")) {
				machine.showItem();
			}
			else {
				System.out.println("Opera��o inv�lida!!!");
			}
		}
		System.out.println("VendingMachine DESLIGADA");
	}
} // classe VendingMachine
