package fr.istic.aco.editor.undomanager;

/**
 * Provides acess to undo/redo operations.
 * 
 * @version 3.0
 */
public interface UndoManager {

	/**
	 * Save the mini text editor current state.
	 */
	void store();

	/**
	 * Restore the mini text editor to a previous state.
	 */
	void undo();

	/**
	 * Restore the mini text editor to a later state.
	 */
	void redo();
}
