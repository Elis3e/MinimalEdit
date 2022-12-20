package fr.istic.aco.editor.memento;

import fr.istic.aco.editor.util.MyPairImpl;

/**
 * Concrete memento for SelectionChange command.
 * 
 * @version 2.0
 */
public class SelectionChangeMemento implements Memento {

	private MyPairImpl<Integer, Integer> selection;

	/**
	 * Creates a memento object that stores the begin index and the end index.
	 * 
	 * @param selection a pair of int values that represents the selection
	 */
	public SelectionChangeMemento(MyPairImpl<Integer, Integer> selection) {
		this.selection = selection;
	}

	/**
	 * Gets the selection begin index stored.
	 * 
	 * @return a int value which is the selection begin index
	 */
	public int getBeginIndex() {
		return selection.getKey();
	}

	/**
	 * Gets the selection end index stored.
	 * 
	 * @return a int value which is the selection end index
	 */
	public int getEndIndex() {
		return selection.getValue();
	}

}
