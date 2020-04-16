package com.geektrust.familytree.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.geektrust.familytree.exception.FamilyException;
import com.geektrust.familytree.exception.PersonNotFoundException;

/**
 * The Class FamilyTreeTest.
 */
@TestInstance(Lifecycle.PER_CLASS)
class FamilyTreeTest {

	/** The family tree. */
	FamilyTree familyTree;

	/**
	 * Inits the.
	 *
	 * @throws FamilyException the family exception
	 */
	@BeforeAll
	public void init() throws FamilyException {
		familyTree = TestData.getTestTreeData();
	}

	/**
	 * Gets the king test.
	 *
	 * @return the king test
	 */
	@Test
	void getKingTest() {
		Person person = familyTree.getKing();
		assertNotNull(person);
		assertEquals(person.getName(), "Ram");
		assertEquals(person.getGender(), Gender.MALE);
		assertNotNull(person.getChildren());
	}

	/**
	 * Gets the person by name test.
	 *
	 * @return the person by name test
	 * @throws PersonNotFoundException the person not found exception
	 */
	@Test
	void getPersonByNameTest() throws PersonNotFoundException {
		Person person = familyTree.getPersonByName("Sagar");
		assertNotNull(person);
		assertEquals(person.getName(), "Sagar");
		assertEquals(person.getGender(), Gender.MALE);
		assertEquals(person.getMother().getName(), "Dimpy");
	}

	/**
	 * Gets the personby name test exception.
	 *
	 * @return the personby name test exception
	 */
	@Test
	void getPersonbyNameTestException() {
		assertThrows(PersonNotFoundException.class, () -> familyTree.getPersonByName("didtya"));
	}

}
