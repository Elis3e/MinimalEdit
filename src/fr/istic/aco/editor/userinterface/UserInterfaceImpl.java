package fr.istic.aco.editor.userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import fr.istic.aco.editor.command.Command;

/**
 * Implementation of the command-line user interface.
 * 
 * @version 1.0
 */
public class UserInterfaceImpl implements UserInterface {

	private Map<String, Command> commands = new HashMap<>();
	private boolean stopLoop = false;
	private InputStream inputStream;
	private BufferedReader bufferedReader;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runInvokerLoop() {
		while (!stopLoop) {
			String userInput = null;
			try {
				userInput = readUserInput();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (userInput == null) {
				stopLoop = true;
				break;
			}
			executeCommand(userInput.toLowerCase().trim());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeCommand(String command) {
		Command cmdToExecute = commands.get(Objects.requireNonNull(command));
		if (cmdToExecute != null)
			cmdToExecute.execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopLoop() {
		stopLoop = true;
	}

	/**
	 * Reads a line of text. A line is considered to be terminated by any one of a
	 * line feed ('\n'), a carriage return ('\r'), or a carriage return followed
	 * immediately by a linefeed.
	 * 
	 * 
	 * @return a String containing the contents of the line, not including any
	 *         line-termination characters, or null if the end of the stream has
	 *         been reached
	 * 
	 * @throws IOException if If an I/O error occurs
	 */
	private String readUserInput() throws IOException {
		return bufferedReader.readLine();
	}

	private InputStream getInputStream() {
		return inputStream;
	}

	private void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addCommand(String keyword, Command cmd) {
		commands.put(Objects.requireNonNull(keyword), Objects.requireNonNull(cmd));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setReadStream(InputStream is) {
		if (is == null) {
			throw new IllegalArgumentException("null inputStream");
		}
		setInputStream(getInputStream());
		this.bufferedReader = new BufferedReader(new InputStreamReader(is));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getText() {
		String userInput = "";
		try {
			userInput += readUserInput();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInput;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getBeginIndex() {
		String userInput = getText();
		return Integer.parseInt(userInput);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getEndIndex() {
		String userInput = getText();
		return Integer.parseInt(userInput);
	}
}
