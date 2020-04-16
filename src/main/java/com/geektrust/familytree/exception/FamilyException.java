package com.geektrust.familytree.exception;

/**
 * The Class FamilyException is exception container which all handle all the
 * exception related to Family Tree problem.
 * 
 * @author Sagar Poudel
 *
 */
public class FamilyException extends Exception {

	/**
	 * Instantiates a new family exception.
	 */
	public FamilyException() {
		super();
	}

	/**
	 * Instantiates a new family exception.
	 *
	 * @param message the message
	 */
	public FamilyException(final String message) {
		super(message);
	}
}