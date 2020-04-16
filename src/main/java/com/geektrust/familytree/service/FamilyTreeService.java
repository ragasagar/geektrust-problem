package com.geektrust.familytree.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geektrust.familytree.constant.FamilyConstant;
import com.geektrust.familytree.exception.FamilyException;
import com.geektrust.familytree.model.FamilyTree;
import com.geektrust.familytree.model.Gender;
import com.geektrust.familytree.model.Person;

/**
 * The Class FamilyTreeService is service class that execute all the commands.
 * 
 * @author Sagar Poudel
 *
 */
public class FamilyTreeService {

	/** The Constant LOGGER. */
	public static final Logger LOGGER = LoggerFactory.getLogger(FamilyTreeService.class);

	/** The instance. */
	public static FamilyTreeService instance;

	/**
	 * Gets the instanse.
	 *
	 * @return the instanse
	 */
	public static FamilyTreeService getInstanse() {
		if (instance == null) {
			instance = new FamilyTreeService();
		}
		return instance;
	}

	/**
	 * Instantiates a new family tree service.
	 */
	private FamilyTreeService() {
	}

	/**
	 * Execute.
	 *
	 * @param familyTree the family tree
	 * @param command    the command
	 * @return the string
	 */
	public String execute(final FamilyTree familyTree, final String command) {
		String responseMsg = "";
		String[] commandArgs = command.trim().split(" ");
		final String commandType = commandArgs[0].trim().toUpperCase();
		final String firstArg = commandArgs[1].trim();
		final String secondArg = commandArgs[2].trim();
		final String thirdArg = commandArgs[commandArgs.length - 1].trim();
		try {
			Person person = familyTree.getPersonByName(firstArg);
			final Gender gender = thirdArg.equalsIgnoreCase("male") ? Gender.MALE : Gender.FEMALE;
			switch (commandType) {
			case ("ADD_CHILD"):
				responseMsg = addChild(person, secondArg, gender);
				break;
			case ("ADD_SPOUSE"):
				responseMsg = addSpouse(person, secondArg, gender);
				break;
			case ("GET_RELATIONSHIP"):
				Set<String> names = getRelatives(person, secondArg);
				responseMsg = String.join(" ", names);
				break;
			}
		} catch (FamilyException exception) {
			LOGGER.debug(exception.getMessage());
			responseMsg = exception.getMessage();
		}
		return responseMsg;
	}

	/**
	 * Adds the child.
	 *
	 * @param person     the person
	 * @param personName the person name
	 * @param gender     the gender
	 * @return the string
	 * @throws FamilyException the family exception
	 */
	private String addChild(final Person person, final String personName, final Gender gender) throws FamilyException {
		Person p = person.addChild(personName, gender);
		return null != p ? FamilyConstant.CHILD_ADDITION_SUCCESS_MSG
				: FamilyConstant.CHILD_ADDITION_FAILED_EXCEPTION_MSG;
	}

	/**
	 * Adds the spouse.
	 *
	 * @param person       the person
	 * @param spouseName   the spouse name
	 * @param spouseGender the spouse gender
	 * @return the string
	 * @throws FamilyException the family exception
	 */
	private String addSpouse(final Person person, final String spouseName, final Gender spouseGender)
			throws FamilyException {
		Person p = person.addSpouse(spouseName, spouseGender);
		return null != p ? FamilyConstant.SPOUSE_ADDITION_SUCCESS_MSG : FamilyConstant.SPOUSE_ADDITION_SUCCESS_MSG;
	}

	/**
	 * Gets the relatives.
	 *
	 * @param person       the person
	 * @param relationShip the relation ship
	 * @return the relatives
	 * @throws FamilyException the family exception
	 */
	private Set<String> getRelatives(final Person person, final String relationShip) throws FamilyException {
		Set<Person> relatives = null;
		switch (relationShip.toUpperCase()) {
		case ("SON"):
			relatives = person.getSons();
			break;
		case ("DAUGHTER"):
			relatives = person.getDaughters();
			break;
		case ("SIBLINGS"):
			relatives = person.getSibilings();
			break;
		case ("PATERNAL-UNCLE"):
			relatives = person.getPaternalUncle();
			break;
		case ("PATERNAL-AUNT"):
			relatives = person.getPaternalAunt();
			break;
		case ("MATERNAL-UNCLE"):
			relatives = person.getMaternalUncle();
			break;
		case ("MATERNAL-AUNT"):
			relatives = person.getMaternalAunt();
			break;
		case ("SISTER-IN-LAW"):
			relatives = person.getSisterInLaw();
			break;
		case ("BROTHER-IN-LAW"):
			relatives = person.getBrotherInLaw();
			break;
		default:
			LOGGER.error("UNDEFINED RELATIONSHIP");
			throw new FamilyException(FamilyConstant.RELATION_SHIP_EXCEPTION_MSG);
		}
		Set<String> names = null;
		if (null != relatives && relatives.size() > 0) {
			names = relatives.stream().map(x -> x.getName()).collect(Collectors.toSet());
		} else {
			throw new FamilyException(FamilyConstant.RELATIVE_NOT_FOUND_MSG);
		}
		return names;
	}

}
