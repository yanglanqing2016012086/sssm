package JunitTest;

import static org.junit.Assert.*;
import org.junit.Test;
import core.core_operation;

public class TTest {

	@Test
	public void testCore_add_one() {
		int a = new core_operation().core_add_one(1, 1);
		assertEquals(1,a);
	}

	@Test
	public void testCore_select_all() {
		fail("Not yet implemented");
	}

	@Test
	public void testCore_select_part() {
		fail("Not yet implemented");
	}

	@Test
	public void testCore_delete() {
		fail("Not yet implemented");
	}

}
