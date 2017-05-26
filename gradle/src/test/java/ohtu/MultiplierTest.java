package ohtu;

import static org.junit.Assert.*;
import org.junit.Test;

public class MultiplierTest {

	@Test
	public void multiplicationWorks() {
		Multiplier five = new Multiplier(5);
		assertEquals(5, five.mutipliedBy(1));
		assertEquals(35, five.mutipliedBy(7));
	}
}