package com.geektrust.familytree.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.geektrust.familytree.exception.FamilyException;
import com.geektrust.familytree.exception.PersonNotFoundException;

/**
 * The Class PersonTest.
 */
@TestInstance(Lifecycle.PER_CLASS)
class PersonTest {

	/** The family tree. */
	private FamilyTree familyTree;

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
	 * Adds the child test.
	 *
	 * @throws FamilyException the family exception
	 */
	@Test
	@BeforeEach
	public void addChildTest() throws FamilyException{
		Person testPerson = familyTree.getPersonByName("Rita");
		Person result = testPerson.addChild("Sunita", Gender.FEMALE);
		assertNotNull(result);
		assertEquals("Rita", result.getMother().getName());
	}
	
	/**
	 * Gets the sons test.
	 *
	 * @return the sons test
	 */
	@Test
	public void getSonsTest() {
		Person ram = familyTree.getKing();
		Set<Person> testdata = ram.getSons();
		assertEquals(3, testdata.size());
		String personNames = testdata.stream().map(x -> x.getName()).collect(Collectors.joining(" "));
		assertTrue(personNames.contains("Shyam"));
		assertTrue(personNames.contains("Manoj"));
		assertTrue(personNames.contains("Ghane"));
	}

	/**
	 * Gets the daughter test.
	 *
	 * @return the daughter test
	 * @throws PersonNotFoundException the person not found exception
	 */
	@Test
	public void getDaughterTest() throws PersonNotFoundException {
		Person person = familyTree.getPersonByName("Rita");
		Set<Person> testdata = person.getDaughters();
		assertEquals(2, testdata.size());
		String personNames = testdata.stream().map(x -> x.getName()).collect(Collectors.joining(" "));
		assertTrue(personNames.contains("Ritu"));
		assertTrue(personNames.contains("Sunita"));
		
		
	}

	/**
	 * Gets the sibiling test.
	 *
	 * @return the sibiling test
	 * @throws PersonNotFoundException the person not found exception
	 */
	@Test
	public void getSibilingTest() throws PersonNotFoundException {
		Person person = familyTree.getPersonByName("Ritu");
		Set<Person> testdata = person.getSibilings();
		assertEquals(4, testdata.size());
		String personNames = testdata.stream().map(x -> x.getName()).collect(Collectors.joining(" "));
		assertTrue(personNames.contains("Shyam"));
		assertTrue(personNames.contains("Manoj"));
		assertTrue(personNames.contains("Ghane"));
		assertTrue(personNames.contains("Sunita"));
	}

	/**
	 * Gets the paternal uncle test.
	 *
	 * @return the paternal uncle test
	 * @throws PersonNotFoundException the person not found exception
	 */
	@Test
	public void getPaternalUncleTest() throws PersonNotFoundException {
		Person person = familyTree.getPersonByName("Sagar");
		Set<Person> testdata = person.getPaternalUncle();
		assertEquals(2, testdata.size());
		String personNames = testdata.stream().map(x -> x.getName()).collect(Collectors.joining(" "));
		assertTrue(personNames.contains("Ghane"));
		assertTrue(personNames.contains("Manoj"));
	}

	/**
	 * Gets the paternal aunt test.
	 *
	 * @return the paternal aunt test
	 * @throws PersonNotFoundException the person not found exception
	 */
	@Test
	public void getPaternalAuntTest() throws PersonNotFoundException {
		Person person = familyTree.getPersonByName("Sagar");
		Set<Person> testdata = person.getPaternalAunt();
		assertEquals(2, testdata.size());
		String personNames = testdata.stream().map(x -> x.getName()).collect(Collectors.joining(" "));
		assertTrue(personNames.contains("Ritu"));
		assertTrue(personNames.contains("Sunita"));
		
	}

	/**
	 * Gets the maternal uncle test.
	 *
	 * @return the maternal uncle test
	 * @throws PersonNotFoundException the person not found exception
	 */
	@Test
	public void getMaternalUncleTest() throws PersonNotFoundException {
		Person person = familyTree.getPersonByName("Sujal");
		Set<Person> testdata = person.getMaternalUncle();
		assertEquals(3, testdata.size());
		String personNames = testdata.stream().map(x -> x.getName()).collect(Collectors.joining(" "));
		assertTrue(personNames.contains("Shyam"));
		assertTrue(personNames.contains("Manoj"));
		assertTrue(personNames.contains("Ghane"));
		
	}

	/**
	 * Gets the maternal aunt test.
	 *
	 * @return the maternal aunt test
	 * @throws PersonNotFoundException the person not found exception
	 */
	@Test
	public void getMaternalAuntTest() throws PersonNotFoundException {
		Person person = familyTree.getPersonByName("Sujal");
		Set<Person> testdata = person.getMaternalAunt();
		assertEquals(1, testdata.size());
		String personNames = testdata.stream().map(x -> x.getName()).collect(Collectors.joining(" "));
		assertEquals("Sunita", personNames);
	}

	/**
	 * Gets the sister in law.
	 *
	 * @return the sister in law
	 * @throws PersonNotFoundException the person not found exception
	 */
	@Test
	public void getSisterInLaw() throws PersonNotFoundException {
		Person person = familyTree.getPersonByName("Aparna");
		Set<Person> testdata = person.getSisterInLaw();
		assertEquals(1, testdata.size());
		String personNames = testdata.stream().map(x -> x.getName()).collect(Collectors.joining(" "));
		assertEquals("Ritika", personNames);
	}

	/**
	 * Gets the brother in law.
	 *
	 * @return the brother in law
	 * @throws PersonNotFoundException the person not found exception
	 */
	@Test
	public void getBrotherInLaw() throws PersonNotFoundException {
		Person person = familyTree.getPersonByName("Aparna");
		Set<Person> testdata = person.getBrotherInLaw();
		assertEquals(1, testdata.size());
		String personNames = testdata.stream().map(x -> x.getName()).collect(Collectors.joining(" "));
		assertEquals("Rama", personNames);
	}
	
	/**
	 * Gets the no person test.
	 *
	 * @return the no person test
	 * @throws PersonNotFoundException the person not found exception
	 */
	@Test
	public void getNoPersonTest() throws PersonNotFoundException {
		Person person = familyTree.getPersonByName("Aparna");
		Set<Person> testdata = person.getSibilings();
		assertEquals(0, testdata.size());
	}
	
	/**
	 * Hash code test.
	 */
	@Test
	public void hashCodeTest() {
		Person first = new Person("test", null);
		Person second = new Person("test", null);
		assertEquals(first.hashCode(), second.hashCode());
	}

	/**
	 * Equals test.
	 */
	@Test
	public void equalsTest() {
		Person first = new Person("test", null);
		Person second = new Person("test", null);
		assertTrue(first.equals(second));
	}

}
