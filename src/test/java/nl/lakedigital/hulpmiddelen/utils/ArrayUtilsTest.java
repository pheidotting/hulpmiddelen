package nl.lakedigital.hulpmiddelen.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ArrayUtilsTest {

	@Test
	public void testCheckNotEmpty() {
		List<String> lijstje = null;

		assertFalse(ArrayUtils.checkNotEmpty(lijstje));

		lijstje = new ArrayList<String>();

		assertFalse(ArrayUtils.checkNotEmpty(lijstje));

		lijstje.add(new String());

		assertTrue(ArrayUtils.checkNotEmpty(lijstje));
	}

}
