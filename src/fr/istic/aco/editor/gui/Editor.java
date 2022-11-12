package fr.istic.aco.editor.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import fr.istic.aco.editor.command.Command;

public class Editor implements UserInterface {

	private Map<String, Command> commands = new HashMap<>();
	private boolean stopLoop = false;
	private InputStream inputStream;
	private BufferedReader bufferedReader;

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
			Command cmdToExecute = commands.get(userInput);
			if (cmdToExecute != null) {
				cmdToExecute.execute();
			}
		}
	}

	@Override
	public void stopLoop() {
		stopLoop = true;
	}

	private String readUserInput() throws IOException {
		return bufferedReader.readLine();
	}

	/**
	 * Registers a new keyword/command pair
	 *
	 * @param keyword a non-null String
	 * @param cmd     a non-null Command reference
	 * @throws java.lang.IllegalArgumentException for null parameters
	 */
	@Override
	public void addCommand(String keyword, Command cmd) {
		if ((keyword == null) || (cmd == null)) {
			throw new IllegalArgumentException("null parameter");
		}
		commands.put(keyword.toLowerCase().trim(), cmd);
	}

	@Override
	public void setReadStream(InputStream is) {
		if (is == null) {
			throw new IllegalArgumentException("null inputStream");
		}
		inputStream = is;
		this.bufferedReader = new BufferedReader(new InputStreamReader(is));
	}

	/**
	 * @throws IOException
	 */
	@Override
	public String getText() throws IOException {
		return readUserInput();
	}

	
	@Override
	public Integer getIndex() throws IOException {
		String userInput = readUserInput();
		return Integer.parseInt(userInput);
	}


}
