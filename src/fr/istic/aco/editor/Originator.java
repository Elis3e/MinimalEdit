package fr.istic.aco.editor;

import java.util.Optional;

import fr.istic.aco.editor.memento.Memento;

/**
 * Common interface for Concrete originators
 * 
 * @version 2.0
 */
public interface Originator {

	/**
	 * Save its internal state (if exists) to a memento object.
	 * 
	 * @return a Memento object that stores the originator current internal state if
	 *         exists.
	 */
	Optional<Memento> getMemento();

	/**
	 * Restore to a previous state (if exists) from a memento object.
	 * 
	 * @param m a Memento object.
	 */
	void setMemento(Memento m);
}
