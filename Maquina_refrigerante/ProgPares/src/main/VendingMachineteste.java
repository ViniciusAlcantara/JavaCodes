import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import vending.VendingMachine;

public class VendingMachineteste {
	VendingMachine vm;	
	
	@Before
	public void setUp()
	{
		vm = new VendingMachine();
	}
	
	@Test
	public void inserirMoedasCorretamenteTeste()
	{
		int valor = vm.insertCoin(40);
		
		assertFalse(valor==40);
	}
	
	@After
	public void retornarMoedaTeste()
	{
		//vm.insertCoin(100);
		int retorno = vm.returnCoin();
		assertTrue(retorno == 0);
	}
}
