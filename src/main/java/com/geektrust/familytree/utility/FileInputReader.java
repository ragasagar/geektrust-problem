package com.geektrust.familytree.utility;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.geektrust.familytree.constant.FamilyConstant;

/**
 * The Class FileInputReader is used to read the content of the input file.
 * 
 * @author Sagar Poudel
 *
 */
public class FileInputReader {

	/**
	 * Read file.
	 *
	 * @param filePath the file path
	 * @return the list
	 */
	public static List<String> readFile(final String filePath) {
		List<String> commandList = Collections.emptyList();
		try {
			commandList = Files.readAllLines(Path.of(filePath), StandardCharsets.UTF_8);
		} catch (IOException exception) {
			throw new RuntimeException(FamilyConstant.FILE_READER_EXCEPTION_MSG);
		}
		commandList = commandList.stream().filter(x -> x.trim().length() > 0).collect(Collectors.toList());
		return commandList;
	}
}
