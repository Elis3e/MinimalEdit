package fr.istic.aco.editor.memento;

/**
 * 
 * Concrete memento for Insert command.
 * 
 * @version 2.0
 */
public class InsertMemento implements Memento {

	private String text;

	/**
	 * Creates a new InsertMemento that stores the text to insert.
	 * 
	 * @param text the text to insert.
	 */
	public InsertMemento(String text) {
		this.text = text;
	}

	/**
	 * Gets the text to insert stored.
	 * 
	 * @return a string containing the text to insert.
	 */
	public String getText() {
		return text;
	}

}
