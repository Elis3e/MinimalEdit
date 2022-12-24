package fr.istic.aco.editor.command;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.memento.InsertMemento;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.recorder.*;
import fr.istic.aco.editor.undomanager.UndoManager;
import fr.istic.aco.editor.userinterface.UserInterface;

/**
 * Concrete command/originator Insert.
 */
public class Insert implements CommandOriginator {

	private String text;
	private Engine engine;
	private UserInterface userInterface;
	private Recorder recorder;
	private UndoManager undoManager;

	/**
	 * Creates a concrete command CutSelectedText command with specified receiver,
	 * invoker and recorder.
	 * 
	 * @param userInterface the invoker of this concrete command
	 * @param engine        the receiver of this concrete command
	 * @param recorder      the recorder of this concrete command
	 * @param undoManager   the engine recorder
	 */
	public Insert(Engine engine, UserInterface userInterface, Recorder recorder, UndoManager undoManager) {
		this.engine = engine;
		this.userInterface = userInterface;
		this.recorder = recorder;
		this.undoManager = undoManager;
	}

	/**
	 * Calls the operation insert(String s) from the Engine type receiver.
	 */
	@Override
	public void execute() {
		// user interface is the invoker
		undoManager.store();
		if (!((RecorderImpl) recorder).isReplaying())
			text = userInterface.getText();
		engine.insert(text);
		recorder.save(this);
	}

	/**
	 * Save the text to insert to a memento object.
	 * 
	 * @return a memento object that stores the text to insert
	 */
	@Override
	public Memento getMemento() {
		return new InsertMemento(text);
	}

	/**
	 * Restore the text from a memento object.
	 * 
	 * @param m a Memento object.
	 */
	@Override
	public void setMemento(Memento m) {
		this.text = ((InsertMemento) m).getText();
	}

}
