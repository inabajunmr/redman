package xyz.dgz48.redman.domain.user;

import org.junit.Test;

/**
 * Test for jacoco.
 */
public class IdpTypeTest {

	/**
	 * Test for jacoco.
	 */
	@Test
	public void testValueOf() {
		IdpType.valueOf("GOOGLE");
	}

	/**
	 * Test for jacoco.
	 */
	@Test
	public void testValues() {
		IdpType.values();
	}

}
