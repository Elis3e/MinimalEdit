package fr.istic.aco.editor.command;

import java.util.Optional;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.memento.InsertMemento;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.recorder.*;
import fr.istic.aco.editor.userinterface.UserInterface;

/**
 * Concrete command/originator Insert.
 */
public class Insert implements CommandOriginator {

	private String text;
	private Engine engine;
	private UserInterface userInterface;
	private Recorder recorder;

	/**
	 * Creates a concrete command CutSelectedText command with specified receiver,
	 * invoker and recorder.
	 * 
	 * @param userInterface the invoker of this concrete command
	 * @param engine        the receiver of this concrete command
	 * @param recorder      the recorder of this concrete command
	 */
	public Insert(Engine engine, UserInterface userInterface, Recorder recorder) {
		this.engine = engine;
		this.userInterface = userInterface;
		this.recorder = recorder;
	}

	/**
	 * Calls the operation insert(String s) from the Engine type receiver.
	 */
	@Override
	public void execute() {
		// invoker = user interface
		if (!((RecorderImpl) recorder).isReplaying())
			text = userInterface.getText();
		engine.insert(text);
		recorder.save(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Memento> getMemento() {
		return Optional.ofNullable(new InsertMemento(text));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMemento(Memento m) {
		this.text = ((InsertMemento) m).getText();
	}

}
