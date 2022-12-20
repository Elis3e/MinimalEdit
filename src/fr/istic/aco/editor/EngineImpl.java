package fr.istic.aco.editor;

import fr.istic.aco.editor.memento.EditorMemento;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.util.MyPairImpl;

/**
 * Implementation of the text editing engine.
 * 
 * @version 1.0
 */
public class EngineImpl implements EngineOriginator {

	private StringBuilder buffer;

	private Selection selection;

	private String clipboard;

	/**
	 * Initializes a newly created engine object.
	 */
	public EngineImpl() {
		buffer = new StringBuilder();
		clipboard = "";
		selection = new SelectionImpl(buffer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Selection getSelection() {
		return selection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getBufferContents() {
		return buffer.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClipboardContents() {
		return clipboard;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cutSelectedText() {
		copySelectedText();
		delete();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copySelectedText() {
		clipboard = buffer.substring(selection.getBeginIndex(), selection.getEndIndex());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void pasteClipboard() {
		insert(clipboard);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(String s) {
		buffer.replace(selection.getBeginIndex(), selection.getEndIndex(), s);
		selection.setEndIndex(selection.getBeginIndex() + s.length());
		selection.setBeginIndex(selection.getEndIndex());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete() {
		buffer.delete(selection.getBeginIndex(), selection.getEndIndex());
		selection.setEndIndex(selection.getBeginIndex());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Memento getMemento() {
		return new EditorMemento(new MyPairImpl<>(selection.getBeginIndex(), selection.getEndIndex()),
				getBufferContents());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMemento(Memento m) {

		EditorMemento stateToRestore = (EditorMemento) m;

		buffer.replace(0, selection.getBufferEndIndex(), stateToRestore.getBufferContent());

		selection.setBeginIndex(stateToRestore.getBeginIndex());
		selection.setEndIndex(stateToRestore.getEndIndex());
	}
}
