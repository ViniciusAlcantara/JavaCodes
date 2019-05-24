import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vending.Dispenser;
import vending.VendingMachine;

public class DispenserTeste {

	Dispenser d;
	
	@Before
	public void setUp()
	{
		d = new Dispenser();
	}
	
	@Test
	public void testDispenseCredit2()
	{
		int expense = d.dispense(50, 1);
		assertEquals(50,expense);
		
		expense = d.dispense(100, 19);
		assertTrue(expense==50);
	}

}
