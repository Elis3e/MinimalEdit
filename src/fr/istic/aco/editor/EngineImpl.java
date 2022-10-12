package fr.istic.aco.editor;

public class EngineImpl implements Engine {

	private SelectionImpl selection;

	private String clipboard;

	public EngineImpl(SelectionImpl selection, String clipboard) {
		this.selection = selection;
		this.clipboard = clipboard;
	}

	public EngineImpl() {
		this.selection = new SelectionImpl();
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
		return this.selection.getBuffer().toString();
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
		this.delete();
	}

	/**
	 * Copies the text within the interval specified by the selection control object
	 * into the clipboard.
	 */
	@Override
	public void copySelectedText() {
		Integer beginIndex = getSelection().getBeginIndex();
		Integer endIndex = getSelection().getEndIndex();
		StringBuilder buffer = this.selection.getBuffer();
		this.clipboard = buffer.substring(beginIndex, endIndex);
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
	 * Inserts a string in the buffer, which replaces the contents of the selection
	 *
	 * @param s the text to insert
	 */
	@Override
	public void insert(String s) {
		Integer beginIndex = getSelection().getBeginIndex();
		Integer endIndex = getSelection().getEndIndex();
		StringBuilder buffer = this.selection.getBuffer();
		buffer.replace(beginIndex, endIndex, s);
	}

	/**
	 * Removes the contents of the selection in the buffer
	 */
	@Override
	public void delete() {
		Integer beginIndex = getSelection().getBeginIndex();
		Integer endIndex = getSelection().getEndIndex();
		StringBuilder buffer = this.selection.getBuffer();
		buffer.delete(beginIndex, endIndex);
	}
}
