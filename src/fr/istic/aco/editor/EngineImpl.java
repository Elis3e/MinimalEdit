package fr.istic.aco.editor;

import java.util.Optional;

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
		this.buffer = new StringBuilder();
		this.clipboard = "";
		this.selection = new SelectionImpl(buffer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Selection getSelection() {
		return this.selection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getBufferContents() {
		return this.buffer.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClipboardContents() {
		return this.clipboard;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cutSelectedText() {
		this.copySelectedText();
		this.delete();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copySelectedText() {
		Selection currentSelection = getSelection();
		this.clipboard = buffer.substring(currentSelection.getBeginIndex(), currentSelection.getEndIndex());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void pasteClipboard() {
		this.insert(this.clipboard);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(String s) {

		Selection currentSelection = getSelection();

		this.buffer.replace(currentSelection.getBeginIndex(), currentSelection.getEndIndex(), s);

		currentSelection.setEndIndex(currentSelection.getBeginIndex() + s.length());
		currentSelection.setBeginIndex(currentSelection.getEndIndex());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete() {

		Selection currentSelection = getSelection();

		this.buffer.delete(currentSelection.getBeginIndex(), currentSelection.getEndIndex());
		currentSelection.setEndIndex(currentSelection.getBeginIndex());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Memento> getMemento() {
		return Optional.ofNullable(new EditorMemento(
				new MyPairImpl<>(selection.getBeginIndex(), selection.getEndIndex()), getBufferContents()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMemento(Memento m) {
		EditorMemento e = (EditorMemento) m;
		selection.setBeginIndex(e.getBeginIndex());
		selection.setEndIndex(e.getEndIndex());
		this.buffer = new StringBuilder(e.getBufferContent());
	}
}
