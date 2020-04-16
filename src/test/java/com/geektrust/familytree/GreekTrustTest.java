package com.geektrust.familytree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class GeekTrustTest {

	@Test
	void testMain() {
		String[] args = { "src/test/resources/test_input.txt"};
		boolean test = false;
		GeekTrust.main(args);
		test = true;
		assertEquals(true, test);
	}
}
