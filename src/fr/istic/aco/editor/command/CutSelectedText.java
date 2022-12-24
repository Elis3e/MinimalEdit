package fr.istic.aco.editor.command;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.memento.EmptyMemento;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.recorder.Recorder;
import fr.istic.aco.editor.undomanager.UndoManager;

/**
 * Concrete command/originator CutSelectedText.
 */
public class CutSelectedText implements CommandOriginator {

	private Engine engine;

	private Recorder recorder;

	private UndoManager undoManager;

	/**
	 * Creates a concrete command CutSelectedText command with specified receiver
	 * and recorder.
	 * 
	 * @param engine      the receiver of this concrete command
	 * @param recorder    the recorder of this concrete command
	 * @param undoManager the engine recorder
	 */
	public CutSelectedText(Engine engine, Recorder recorder, UndoManager undoManager) {
		this.engine = engine;
		this.recorder = recorder;
		this.undoManager = undoManager;
	}

	/**
	 * Calls the operation cutSelectedText() from the Engine type receiver.
	 */
	@Override
	public void execute() {
		undoManager.store();
		engine.cutSelectedText();
		recorder.save(this);
	}

	/**
	 * Theres's no state to save.
	 * 
	 * @return an empty memento
	 */
	@Override
	public Memento getMemento() {
		return new EmptyMemento();
	}

	/**
	 * Do nothing.
	 */
	@Override
	public void setMemento(Memento m) {
		// Nothing to do!
	}

}
