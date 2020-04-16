package com.geektrust.familytree.model;

import com.geektrust.familytree.exception.FamilyException;

/**
 * The Class TestData.
 */
public class TestData {
	
	/** The familytree. */
	private static FamilyTree familytree;

	/**
	 * Gets the test tree data.
	 *
	 * @return the test tree data
	 * @throws FamilyException the family exception
	 */
	public static FamilyTree getTestTreeData() throws FamilyException {
		if (familytree == null) {
			familytree = new FamilyTree("Ram");
			Person Ram = familytree.getPersonByName("Ram");
			Ram.addSpouse("Rita", Gender.FEMALE);
			Ram.getSpouse().addChild("Shyam", Gender.MALE);
			Ram.getSpouse().addChild("Ghane", Gender.MALE);
			Ram.getSpouse().addChild("Manoj", Gender.MALE);
			Ram.getSpouse().addChild("Ritu", Gender.FEMALE);
			Person ghane = familytree.getPersonByName("Ghane");
			ghane.addSpouse("Monika", Gender.FEMALE);
			Person shyam = familytree.getPersonByName("Shyam");
			shyam.addSpouse("Dimpy", Gender.FEMALE);
			Person manoj = familytree.getPersonByName("Manoj");
			manoj.addSpouse("Mamta", Gender.FEMALE);
			Person ritu = familytree.getPersonByName("Ritu");
			ritu.addSpouse("Raj", Gender.MALE);
			shyam.getSpouse().addChild("Sagar", Gender.MALE);
			shyam.getSpouse().addChild("Rama", Gender.MALE);
			shyam.getSpouse().addChild("Ritika", Gender.FEMALE);
			ghane.getSpouse().addChild("Lita", Gender.FEMALE);
			ghane.getSpouse().addChild("Daya", Gender.MALE);
			manoj.getSpouse().addChild("babita", Gender.FEMALE);
			ritu.addChild("Sujal", Gender.MALE);
			Person sagar = familytree.getPersonByName("Sagar");
			sagar.addSpouse("Aparna", Gender.FEMALE);
			Person rama = familytree.getPersonByName("Rama");
			rama.addSpouse("Sima", Gender.FEMALE);
			Person lita = familytree.getPersonByName("Lita");
			lita.addSpouse("Satya", Gender.MALE);
			sagar.getSpouse().addChild("Deepak", Gender.MALE);
			sagar.getSpouse().addChild("Aria", Gender.FEMALE);
			lita.addChild("Ghapte", Gender.MALE);
			lita.addChild("Lulu", Gender.FEMALE);
			lita.addChild("Lempa", Gender.MALE);
		}
		return familytree;
	}
}
