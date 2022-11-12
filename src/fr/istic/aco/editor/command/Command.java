package fr.istic.aco.editor.command;

/**
 * Defines a common interface for concrete commands.
 */
@FunctionalInterface
public interface Command {
    /**
     * Calls an appropriate operation on an appropriate receiver.
     * 'Appropriates' are defined in concrete implementation of Command.
     */
	void execute();	
}
