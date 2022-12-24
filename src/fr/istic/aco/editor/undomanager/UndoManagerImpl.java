package fr.istic.aco.editor.undomanager;

import java.util.ArrayList;
import java.util.List;

import fr.istic.aco.editor.EngineOriginator;
import fr.istic.aco.editor.memento.EditorMemento;

/**
 * Implementation of the text editor state recorder.
 */
public class UndoManagerImpl implements UndoManager {

	private List<EditorMemento> pastStates;
	private List<EditorMemento> futurStates;

	private EngineOriginator engine;

	/**
	 * Creates an recorder with the text editor initial state.
	 * 
	 * @param engine the mini text editor engine.
	 */
	public UndoManagerImpl(EngineOriginator engine) {
		this.engine = engine;
		pastStates = new ArrayList<>();
		futurStates = new ArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void store() {
		EditorMemento stateToSave = (EditorMemento) engine.getMemento();
		pastStates.add(stateToSave);
		futurStates.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		if (!pastStates.isEmpty()) {
			EditorMemento currentState = (EditorMemento) engine.getMemento();
			EditorMemento lastState = pastStates.get(pastStates.size() - 1);
			futurStates.add(currentState);
			engine.setMemento(lastState);
			pastStates.remove(lastState);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		if (!futurStates.isEmpty()) {
			EditorMemento currentState = (EditorMemento) engine.getMemento();
			EditorMemento nextState = futurStates.get(futurStates.size() - 1);
			pastStates.add(currentState);
			engine.setMemento(nextState);
			futurStates.remove(nextState);
		}
	}

}
