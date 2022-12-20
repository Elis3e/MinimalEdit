package fr.istic.aco.editor.recorder;

import fr.istic.aco.editor.command.CommandOriginator;

/**
 * Save and restore commands references.
 * 
 * @version 2.0
 */
public interface Recorder {

	/**
	 * Registers a new command.
	 * 
	 * @param cmd the command to save
	 */
	void save(CommandOriginator cmd);

	/**
	 * Causes this recorder to begin registers commands.
	 */
	void start();

	/**
	 * Causes this recorder to stop registers commands.
	 */
	void stop();

	/**
	 * Executes all registered commands in the order in which they were saved.
	 */
	void replay();

}
