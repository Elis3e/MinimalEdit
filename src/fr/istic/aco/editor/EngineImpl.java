package fr.istic.aco.editor;

public class EngineImpl implements Engine {

	private StringBuilder buffer;

	private Selection selection;

	private String clipboard;

	public EngineImpl(String bufferContent) {
		this.buffer = new StringBuilder(bufferContent);
		this.clipboard = "";
		this.selection = new SelectionImpl(buffer);
	}

	public EngineImpl() {
		this.clipboard = new String();
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
		this.getSelection().setEndIndex(this.getSelection().getBeginIndex());
	}

	/**
	 * Copies the text within the interval specified by the selection control object
	 * into the clipboard.
	 */
	@Override
	public void copySelectedText() {
		Integer beginIndex = getSelection().getBeginIndex();
		Integer endIndex = getSelection().getEndIndex();
		this.clipboard = this.buffer.substring(beginIndex, endIndex);
	}

	/**
	 * Replaces the text within the interval specified by the selection object with
	 * the contents of the clipboard.
	 */
	@Override
	public void pasteClipboard() {
		this.insert(clipboard);
	}

	/**
	 * Inserts a String in the buffer, which replaces the contents of the
	 * selection
	 *
	 * @param s the text to insert
	 */
	@Override
	public void insert(String s) {
		Selection selection = this.getSelection();
		Integer beginIndex = selection.getBeginIndex();
		Integer endIndex = selection.getEndIndex();
		this.buffer.replace(beginIndex, endIndex, s);
		selection.setEndIndex(beginIndex + s.length());
		selection.setBeginIndex(selection.getEndIndex());
	}

	/**
	 * Removes the contents of the selection in the buffer
	 */
	@Override
	public void delete() {
		Integer beginIndex = getSelection().getBeginIndex();
		Integer endIndex = getSelection().getEndIndex();
		this.buffer.delete(beginIndex, endIndex);
	}
}
