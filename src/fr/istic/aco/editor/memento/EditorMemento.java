package fr.istic.aco.editor.memento;

import fr.istic.aco.editor.util.MyPairImpl;

/**
 * Concrete Memento for the text editor engine.
 */
public class EditorMemento implements Memento {

	private MyPairImpl<Integer, Integer> selection;

	private String bufferContent;

	/**
	 * Creates a memento object that stores the selection, and the buffer content.
	 * 
	 * @param selection     a pair of int values that represents the selection
	 * @param bufferContent the buffer content
	 */
	public EditorMemento(MyPairImpl<Integer, Integer> selection, String bufferContent) {
		this.selection = selection;
		this.bufferContent = bufferContent;
	}

	/**
	 * Gets the buffer content stored.
	 * 
	 * @return a string that is the buffer content
	 */
	public String getBufferContent() {
		return bufferContent;
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
