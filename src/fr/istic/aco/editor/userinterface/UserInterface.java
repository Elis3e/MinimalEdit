package fr.istic.aco.editor.userinterface;

import java.io.InputStream;

import fr.istic.aco.editor.command.Command;

/**
 * Command-line user interface.
 * 
 * The operations getText() and getIndex() are useful for concretes commands
 * that call operaions with a parameter : Insert, SelectionChange.
 * 
 * @version 1.0
 */
public interface UserInterface {

	/**
	 * Starts the reading of the read stream set by setReadStream operation
	 */
	void runInvokerLoop();

	/**
	 * Stops the read stream loop now.
	 */
	void stopLoop();

	/**
	 * Sets the read stream that be be used by runInvokerLoop
	 *
	 * @param inputStream the read stream
	 * @throws IllegalArgumentException if inputStream is null
	 */
	void setReadStream(InputStream inputStream);

	/**
	 * Registers a new keyword/command pair
	 *
	 * @param keyword a non-null String
	 * @param cmd     a non-null Command reference
	 * @throws java.lang.IllegalArgumentException for null parameters
	 */
	void addCommand(String keyword, Command cmd);

	/**
	 * Reads a line of text entered by the user. The text can be anything except
	 * line-termination characters
	 * 
	 * @return a String containing the contents of the line
	 */
	String getText();

	/**
	 * Executes a command if registered
	 * 
	 * @param cmd cmd a non-null command reference
	 */
	void executeCommand(String cmd);

	/**
	 * Reads a line of text entered by the user. The text must be a number that is
	 * the begin index selection.
	 * 
	 * @return a int value that is the selection begin index
	 */
	int getBeginIndex();

	/**
	 * Reads a line of text entered by the user. The text must be a number thats is
	 * the end index selection.
	 * 
	 * @return a int value that is selection end index
	 */
	int getEndIndex();
}
