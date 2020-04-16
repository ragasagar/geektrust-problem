package com.geektrust.familytree.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.jupiter.api.Assertions.*;

import com.geektrust.familytree.exception.FamilyException;
import com.geektrust.familytree.model.FamilyTree;
import com.geektrust.familytree.model.TestData;
import com.geektrust.familytree.utility.FamilyTreeInitializer;

@TestInstance(Lifecycle.PER_CLASS)
class FamilyTreeServiceTest {

	private FamilyTreeService treeService;

	private FamilyTree familyTree;

	@BeforeAll
	public void init() throws FamilyException {
		treeService = FamilyTreeService.getInstanse();
		familyTree = TestData.getTestTreeData();
	}

	@Test
	public void getInstanseTest() {
		FamilyTreeService instance = FamilyTreeService.getInstanse();
		assertEquals(instance, treeService);
	}

	@Test
	void executeAddChildTest() {
		String response = treeService.execute(familyTree, "ADD_CHILD Aparna Sarika Female");
		assertEquals(response, "CHILD_ADDITION_SUCCEEDED");

		String response2 = treeService.execute(familyTree, "ADD_CHILD Latha Dipti Female");
		assertEquals(response2, "PERSON_NOT_FOUND");

		String response3 = treeService.execute(familyTree, "ADD_CHILD Ram Dipti Female");
		assertEquals(response3, "CHILD_ADDITION_FAILED");
		
		String response4 = treeService.execute(familyTree, "ADD_CHILD Aparna Sarika Female");
		assertEquals(response4, "CHILD_ADDITION_SUCCEEDED");
	}

	@Test
	void executeGetRelationShipTest() {
		String response = treeService.execute(familyTree, "GET_RELATIONSHIP Sagar DAUGHTER");
		assertEquals(response, "Sarika Aria");

		String response2 = treeService.execute(familyTree, "GET_RELATIONSHIP Latha SON");
		assertEquals(response2, "PERSON_NOT_FOUND");

		String response3 = treeService.execute(familyTree, "GET_RELATIONSHIP Shyam Siblings");
		assertEquals(response3, "Sunita Ritu Ghane Manoj");

		String response4 = treeService.execute(familyTree, "GET_RELATIONSHIP Sagar GRANDSON");
		assertEquals(response4, "UNDEFINED RELATIONSHIP");
	}
	
	@Test
	void executeAddSpouseTest() {
		String response = treeService.execute(familyTree, "ADD_SPOUSE Sagar Dolma Female");
		assertEquals(response, "SPOUSE_ALREADY_EXIST");

		String response2 = treeService.execute(familyTree, "ADD_SPOUSE Latha Dipti Female");
		assertEquals(response2, "PERSON_NOT_FOUND");

		String response3 = treeService.execute(familyTree, "ADD_SPOUSE Lulu Dipti Female");
		assertEquals(response3, "SPOUSE_ADDITION_FAILED_GENDER_SAME");
		
		String response4 = treeService.execute(familyTree, "ADD_SPOUSE Ghapte Dipti Female");
		assertEquals(response4, "SPOUSE_ADDITION_SUCCEEDED");
	}

}
