package fr.istic.aco.editor;

public class EngineImpl implements Engine {

	private StringBuilder buffer;

	private Selection selection;

	private String clipboard;

	public EngineImpl(StringBuilder buffer, String clipboard) {
		this.buffer = buffer;
		this.clipboard = clipboard;
		this.selection = new SelectionImpl(buffer);
	}

	public EngineImpl() {
		this.buffer = new StringBuilder();
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
		this.getSelection().setEndIndex(this.getSelection().getBufferEndIndex());
		this.getSelection().setBeginIndex(this.getSelection().getBufferEndIndex());
	}

	/**
	 * Inserts a string in the buffer, which replaces the contents of the selection
	 *
	 * @param s the text to insert
	 */
	@Override
	public void insert(String s) {
		Integer beginIndex = getSelection().getBeginIndex();
		Integer endIndex = getSelection().getEndIndex();
		this.buffer.replace(beginIndex, endIndex, s);
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
