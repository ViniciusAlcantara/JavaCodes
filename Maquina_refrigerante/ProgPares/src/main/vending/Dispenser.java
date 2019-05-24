package vending;

/**
 * Esta classe representa uma possível implementação do componente responsável
 * pela entrega do item comprado. Para que a entrega do item se concretize,
 * algumas restrições precisam ser satisfeitas. Tais condições são descritas
 * abaixo.
 * 
 * @author Auri Marcelo Rizzo Vincenzi
 */
public class Dispenser {
	final private int MINSEL = 1; // Índice do primeiro item
	final private int MAXSEL = 20; // Índice do último item
	final private int VAL = 50; // Preço máximo dos itens

	// Lista dos itens disponíveis para compra. Números comentados representam
	// produtos esgotados.
	private int[] availSelectionVals = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,12, 13, 14, 15, 16, 17, 18,19,20};
	private int[] estoque = 			{20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20};

	/**
	 * Simula o comportamento do componente que faz a entrega de determinado
	 * item em uma máquina de venda. Um item é entregue quando uma quantidade de
	 * créditos suficiente para a sua compra estiver disponível, o código do
	 * item ser um código válido e o item estiver disponível para compra (não
	 * estiver esgotado).
	 * 
	 * @param credit
	 *            - o valor atual em centavos do crédito disponíve no
	 *            compartimento de moedas. Um valor de 50 centavos é necessáiro
	 *            para a compra de qualquer item.
	 * 
	 * @param sel
	 *            - o índice do item selecionado para compra. Um item válido tem
	 *            um índice representado por um número inteiro variando de 1 a
	 *            20. Observa-se que embora o índice seja válido, pode ser que o
	 *            item em questão esteja esgotado e não possa ser vendido.
	 * 
	 * @return O valor pago pelo item desejado. Atualmente esse valor é sempre
	 *         50.Ou um número negativo em caso de erro, conforme tabela de
	 *         código de erro abaixo:
	 *         <ul>
	 *         <li>-1: nenhuma moeda depositada.
	 *         <li>-2: crédito insuficiente para a compra do item.
	 *         <li>-3: índice de item inválido (fora do intervalo especificado).
	 *         <li>-4: item desejado indisponível para compra. Esgotado.
	 *         </ul>
	 */
	public int dispense(int credit, int sel) {
		int val = 0;

		if (credit == 0) // nenhuma moeda inserida
			val = -1;
		else if ((sel < MINSEL) || (sel > MAXSEL)) // item selecionado fora do
			// intervalo
			val = -3;
		else if (!available(sel)) // item esgotado
			val = -4;
		else {
			val = VAL;
			if (credit < val) { // sem crédito suficiente para a compra do item
				int value = val - credit;

				val = -2;
			} 
		}
		return val;
	}

	/**
	 * Este método verifica se determinado item encontra-se disponível, ou seja,
	 * não esgotado.
	 * 
	 * @param sel
	 *            índice do item desejado.
	 * @return true se o item não estiver esgotado ou false caso o item esteja
	 *         esgotado.
	 */
	private boolean available(int sel) {
		for (int i = 0; i < availSelectionVals.length; i++)
			if (availSelectionVals[i] == sel)
				return true;
		return false;
	}
	
	protected boolean checkAvailable(int sel){
		for (int i = 0; i < availSelectionVals.length; i++)
			if (availSelectionVals[i] == sel)
				return (estoque[i] > 0);
		return false;
	}
	
	protected void removeItem(int sel){
		for (int i = 0; i < availSelectionVals.length; i++)
			if (availSelectionVals[i] == sel){
				estoque[i] -= 1;
				return;
			}
	}
	
	protected void printItem(){
		int total = 0;
		for (int i = 0; i < availSelectionVals.length; i++){
			System.out.println("Item: " + availSelectionVals[i] + "\t Estoque: " + estoque[i]);
			total += estoque[i];
		}
		System.out.println("Total de itens disponíveis na máquina: " + total);
	}
	
} // classe Dispenser
