package vending;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Esta classe representa a máquina de vender produtos. Ela implementa uma
 * possível interface de acesso do usuário com a máquina de vender itens.
 * 
 * Nesse caso em particular, a máquina possui uma interface via linha de
 * comando, definida por um conjunto de instruções básicas, definidas a seguir.
 * 
 * @author Auri Marcelo Rizzo Vincenzi
 */

public class VendingMachine {
	final private int COIN = 25; /*
								 * Valor das moedas aceitas. No caso, só são
								 * aceitas moedas de 25 centavos.
								 */
	private int totValue; // Valor total depositado
	private int currValue; // Valor atual depositado
	private Dispenser d;

	/**
	 * Construtor da classe, inicializa os contadores e o dispenser da máquina.
	 */
	public VendingMachine() {
		totValue = 0;
		currValue = 0;
		d = new Dispenser();
	}

	/**
	 * Este método simula que uma nova moeda de 25 centavos foi depositada na
	 * máquina. Ele retorna o valor atual já depositado na máquina.
	 * 
	 * @param - nenhum parâmetro requerido.
	 * @return - o valor total depositado em centavos.
	 */
	public int insertCoin(int coin) {
		if(validateCoin(coin)){
		currValue += coin;
		}else{
			System.out.println("Valor não é igual a 25, 50, 75 ou 100.");
		}
		return currValue;
		
	}
	
	public boolean validateCoin(int coin){
		return coin % 25 == 0 && coin > 0 && coin <= 100; 
	}

	/**
	 * Este método simula a devolução das moedas ao usuário ou a devolução do
	 * troco.
	 * 
	 * @param - nenhum parâmetro requerido..
	 * @return - o valor atual em centavos remanescente no porta moeda ou zero
	 *         caso não haja saldo a ser devolvido.
	 */
	public int returnCoin() {
		int value = currValue;
		System.out.println("Saldo resgatado: " + currValue);
		if (currValue != 0)
			currValue = 0;

		return value;
	}

	/**
	 * Este método simula a requisição de determinado item a ser comprado.
	 * 
	 * @param selection
	 *            o índice do item que se deseja comprar que corresponde a um
	 *            número entre 1 e 20.
	 * 
	 * @return O crédito remanescente após a compra do item no caso de sucesso.
	 *         Em caso de falha, um código de erro é retornado, conforme tabela
	 *         de código descrita abaixo:
	 *         <ul>
	 *         <li>-1: nenhuma moeda depositada.
	 *         <li>-2: crédito insuficiente para a compra do item.
	 *         <li>-3: índice de item inválido (fora do intervalo especificado).
	 *         <li>-4: item desejado indisponível para compra. Esgotado.
	 *         </ul>
	 * 
	 */
	public int vendItem(int selection) {
		int expense;
		if(d.checkAvailable(selection)){
			d.removeItem(selection);
		}
		else{
			System.out.println("Item indisponível");
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
	 * Este método simula o comportamento da máquina de vendas. As ações
	 * permitidas para realizar suas tarefas são as de inserir moedas, solicitar
	 * a devolução das moedas ou solicitar a compra de determinado item. Para
	 * realizar tais ações, o usuário interage com o sistema enviando os
	 * comandos definidos a seguir: <BR>
	 * <UL>
	 * <LI>insertCoin;
	 * <LI>returnCoin; and
	 * <LI>vendItem <item_number>. <\UL>
	 * <P>
	 * Essas operações podem ser fornecidas via teclado ou por meio de um
	 * arquivo texto contendo, em cada linha, a invocação de uma das operações
	 * que se deseja realizar. Para encerrar a execução via teclado, basta
	 * pressionar CTRL+D.
	 * 
	 * Após executar cada uma das operações, uma mensagem é exebida no display
	 * da máquina para o usuário tomar conhecimento do ocorrido e se ele obteve
	 * sucesso ou não em sua solicitação.
	 */
	static public void main(String args[]) throws Exception {
		BufferedReader drvInput;
		String tcLine = new String();

		String methodName = new String();

		VendingMachine machine = new VendingMachine();

		if (args.length < 1) // leitura das operações via teclado
			drvInput = new BufferedReader(new InputStreamReader(System.in));
		else
			// leitura das operações via arquivo texto
			drvInput = new BufferedReader(new FileReader(args[0]));

		System.out.println("VendingMachine LIGADA");
		menu();
		// para entradas via teclado, CTRL+D pára a execução da máquina
		while ((tcLine = drvInput.readLine()) != null) {
			StringTokenizer tcTokens = new StringTokenizer(tcLine);

			if (tcTokens.hasMoreTokens())
				methodName = tcTokens.nextToken();

			int value = 0;
				
			// Uma moeda é inserida
			if (methodName.equals("insertCoin")) {
				value = machine.insertCoin(Integer.parseInt(tcTokens.nextToken()));
				System.out.println("Crédito atual = " + value);
			} // Uma ou mais moedas são devolvidas (caso exista alguma)
			else if (methodName.equals("returnCoin")) {
				value = machine.returnCoin();
				if (value == 0)
					System.err.println("Sem crédito para devolução");
				else
					System.out.println("Retire suas moedas");
			} // Solicitação para a entrega de determinado item
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
							+ ") é inválido!!!");
				} else if (value == -4) {
					System.out.println("O item selecionado (" + selection
							+ ") está esgotado!!!");
				} else if (value == -2) {
					System.out
							.println("O crédito atual é insuficiente para a compra do item "
									+ selection + ".");
				}
			} else if (methodName.equals("showItems")) {
				machine.showItem();
			}
			else {
				System.out.println("Operação inválida!!!");
			}
		}
		System.out.println("VendingMachine DESLIGADA");
	}
} // classe VendingMachine
