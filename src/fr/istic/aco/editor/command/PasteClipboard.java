package fr.istic.aco.editor.command;

import java.util.Optional;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.recorder.Recorder;

/**
 * Concrete command/originator PasteClipboard.
 */
public class PasteClipboard implements CommandOriginator {

	private Engine engine;

	private Recorder recorder;

	/**
	 * Creates a concrete command PasteClipboard command with specified receiver and
	 * recorder.
	 * 
	 * @param engine   the receiver of this concrete command
	 * @param recorder the recorder of this concrete command
	 */
	public PasteClipboard(Engine engine, Recorder recorder) {
		this.engine = engine;
		this.recorder = recorder;
	}

	/**
	 * Calls the operation pasteClipboard() from the Engine type receiver.
	 */
	@Override
	public void execute() {
		engine.pasteClipboard();
		recorder.save(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Memento> getMemento() {
		return Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMemento(Memento m) {
		// Nothing to do!
	}

}
