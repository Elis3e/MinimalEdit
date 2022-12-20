package fr.istic.aco.editor.command;

import java.util.Optional;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.Selection;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.memento.SelectionChangeMemento;
import fr.istic.aco.editor.recorder.*;
import fr.istic.aco.editor.userinterface.UserInterface;
import fr.istic.aco.editor.util.MyPairImpl;

/**
 * Concrete command/originator SelectionChange.
 */
public class SelectionChange implements CommandOriginator {

	private int beginIndex;

	private int endIndex;

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
	public SelectionChange(Engine engine, UserInterface userInterface, Recorder recorder) {
		this.engine = engine;
		this.userInterface = userInterface;
		this.recorder = recorder;
	}

	/**
	 * Calls the operations setBeginIndex(int i) and setEndIndex(int i) from the
	 * selection type receiver.
	 */
	@Override
	public void execute() {
		Selection selection = engine.getSelection();
		if (!((RecorderImpl) recorder).isReplaying()) {
			beginIndex = userInterface.getBeginIndex();
			endIndex = userInterface.getBeginIndex();
		}
		selection.setEndIndex(endIndex);
		selection.setBeginIndex(beginIndex);
		recorder.save(this);
	}

	@Override
	public Optional<Memento> getMemento() {
		MyPairImpl<Integer, Integer> selection = new MyPairImpl<>(beginIndex, endIndex);
		return Optional.ofNullable(new SelectionChangeMemento(selection));
	}

	@Override
	public void setMemento(Memento m) {
		beginIndex = ((SelectionChangeMemento) m).getBeginIndex();
		endIndex = ((SelectionChangeMemento) m).getEndIndex();
	}

}
