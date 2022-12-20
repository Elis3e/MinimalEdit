package fr.istic.aco.editor.memento;

import fr.istic.aco.editor.util.MyPairImpl;

public class EditorMemento implements Memento {

	private MyPairImpl<Integer, Integer> selection;

	private String bufferContent;

	public EditorMemento(MyPairImpl<Integer, Integer> selection, String bufferContent) {
		this.selection = selection;
		this.bufferContent = bufferContent;
	}

	public String getBufferContent() {
		return this.bufferContent;
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
