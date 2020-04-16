package com.geektrust.familytree.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.geektrust.familytree.exception.PersonNotFoundException;

/**
 * The Class FamilyTree is base family tree for entire application.
 * 
 * @author Sagar Poudel
 *
 */
public class FamilyTree {

	/** The king. */
	private Person king;

	/**
	 * Instantiates a new family tree.
	 *
	 * @param kingName the king name
	 */
	public FamilyTree(String kingName) {
		super();
		this.king = new Person(kingName, Gender.MALE);
	}

	/**
	 * Gets the king.
	 *
	 * @return the king
	 */
	public Person getKing() {
		return king;
	}

	/**
	 * Gets the person by name.
	 *
	 * @param personName the person name
	 * @return the person by name
	 * @throws PersonNotFoundException the person not found exception
	 */
	public Person getPersonByName(final String personName) throws PersonNotFoundException {
		Queue<Person> familyQueue = new LinkedList<>();
		familyQueue.add(this.king);
		while (familyQueue.size() > 0) {
			final Person person = familyQueue.remove();
			if (person.getName().equalsIgnoreCase(personName)) {
				return person;
			}
			final Person spousePerson = person.getSpouse();
			if (null != spousePerson && spousePerson.getName().equalsIgnoreCase(personName)) {
				return spousePerson;
			}
			final Set<Person> children = person.getChildren();
			if (null != children && children.size() > 0) {
				familyQueue.addAll(children);
			}
		}
		throw new PersonNotFoundException();
	}
}
