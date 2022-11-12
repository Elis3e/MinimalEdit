package fr.istic.aco.editor;

public class EngineImpl implements Engine {

	private StringBuilder buffer;

	private Selection selection;

	private String clipboard;

	public EngineImpl() {
		this.buffer = new StringBuilder();
		this.clipboard = "";
		this.selection = new SelectionImpl(buffer);
	}

	/**
	 * Provides access to the selection control object
	 *
	 * @return the selection object
	 */
	@Override
	public Selection getSelection() {
		return this.selection;
	}

	/**
	 * Provides the whole contents of the buffer, as a string
	 *
	 * @return a copy of the buffer's contents
	 */
	@Override
	public String getBufferContents() {
		return this.buffer.toString();
	}

	/**
	 * Provides the clipboard contents
	 *
	 * @return a copy of the clipboard's contents
	 */
	@Override
	public String getClipboardContents() {
		return this.clipboard;
	}

	/**
	 * Removes the text within the interval specified by the selection control
	 * object, from the buffer.
	 */
	@Override
	public void cutSelectedText() {
		this.copySelectedText();
		this.delete();
		Selection currentSelection = this.getSelection();
		currentSelection.setEndIndex(currentSelection.getBeginIndex());
	}

	/**
	 * Copies the text within the interval specified by the selection control object
	 * into the clipboard.
	 */
	@Override
	public void copySelectedText() {
		Selection currentSelection = getSelection();
		clipboard = buffer.substring(currentSelection.getBeginIndex(), currentSelection.getEndIndex());
	}

	/**
	 * Replaces the text within the interval specified by the selection object with
	 * the contents of the clipboard.
	 */
	@Override
	public void pasteClipboard() {
		this.insert(this.clipboard);
	}

	/**
	 * Inserts a string in the buffer, which replaces the contents of the selection
	 *
	 * @param s the text to insert
	 */
	@Override
	public void insert(String s) {
		if (s != null) {
			Selection currentSelection = getSelection();
			this.buffer.replace(currentSelection.getBeginIndex(), currentSelection.getEndIndex(), s);
			currentSelection.setEndIndex(currentSelection.getBeginIndex() + s.length());
			currentSelection.setBeginIndex(currentSelection.getEndIndex());
		}
	}

	/**
	 * Removes the contents of the selection in the buffer
	 */
	@Override
	public void delete() {
		Selection currentSelection = getSelection();
		this.buffer.delete(currentSelection.getBeginIndex(), currentSelection.getEndIndex());
	}
}
