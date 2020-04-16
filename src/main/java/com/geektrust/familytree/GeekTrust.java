package com.geektrust.familytree;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geektrust.familytree.constant.FamilyConstant;
import com.geektrust.familytree.model.FamilyTree;
import com.geektrust.familytree.service.FamilyTreeService;
import com.geektrust.familytree.utility.FamilyTreeInitializer;
import com.geektrust.familytree.utility.FileInputReader;

/**
 * This class contains the starter of the tree and input file runner.
 *
 * @author Sagar Poudel
 */
public class GeekTrust {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GeekTrust.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			final String filePath = args[0];
			final FamilyTree rootFamilyTree = FamilyTreeInitializer.getFamilyTreeInstance(FamilyConstant.KING_NAME);
			LOGGER.debug("Running command from input file with path: {}", filePath);
			final List<String> commands = FileInputReader.readFile(filePath);
			FamilyTreeService service = FamilyTreeService.getInstanse();
			for (final String command : commands) {
				LOGGER.debug(command);
				String response = service.execute(rootFamilyTree, command);
				System.out.println(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
