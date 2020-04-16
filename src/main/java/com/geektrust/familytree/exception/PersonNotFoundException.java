package com.geektrust.familytree.exception;

import com.geektrust.familytree.constant.FamilyConstant;

/**
 * The Class PersonNotFoundException is used handle the exception related to
 * person not available in family tree.
 * 
 * @author Sagar Poudel
 *
 */
public class PersonNotFoundException extends FamilyException {

	/**
	 * Instantiates a new person not found exception.
	 */
	public PersonNotFoundException() {
		super(FamilyConstant.PERSON_NOT_FOUND_EXCEPTION_MESSAGE);
	}
}
