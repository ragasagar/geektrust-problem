package com.geektrust.familytree.utility;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geektrust.familytree.constant.IntializerCommands;
import com.geektrust.familytree.exception.FamilyException;
import com.geektrust.familytree.model.FamilyTree;
import com.geektrust.familytree.service.FamilyTreeService;

/**
 * The Class FamilyTreeInitializer initialize the family tree.
 * 
 * @author Sagar Poudel
 *
 */
public class FamilyTreeInitializer {

	/** The family tree. */
	private static FamilyTree familyTree;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FamilyTreeInitializer.class);

	/**
	 * Gets the family tree instance.
	 *
	 * @param kingName the king name
	 * @return the family tree instance
	 * @throws FamilyException the family exception
	 */
	public static FamilyTree getFamilyTreeInstance(final String kingName) throws FamilyException {
		try {
			familyTree = new FamilyTree(kingName);
			initializeTree();
			LOGGER.debug("FAMILY of {} IS INITIALIZED", familyTree.getKing().getName());
		} catch (FamilyException exception) {
			throw new FamilyException("Incomplete command to oprerate");
		}
		return familyTree;
	}

	/**
	 * Initialize tree.
	 *
	 * @throws FamilyException the family exception
	 */
	private static void initializeTree() throws FamilyException {
		final List<String> commands = IntializerCommands.COMMANDS;
		FamilyTreeService familyTreeService = FamilyTreeService.getInstanse();
		for (final String command : commands) {
			String message = familyTreeService.execute(familyTree, command);
			LOGGER.debug(message);
		}
	}
}
