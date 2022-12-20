package fr.istic.aco.editor.undomanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.istic.aco.editor.EngineImpl;
import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.command.CommandOriginator;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.memento.EditorMemento;
import fr.istic.aco.editor.util.MyPairImpl;

public class UndoManagerImpl implements UndoManager {

	private List<EditorMemento> pastStates;
	private List<EditorMemento> futurStates;
	private List<MyPairImpl<Command, Optional<Memento>>> pastCommands;
	private List<MyPairImpl<Command, Optional<Memento>>> futurCommands;

	private EngineImpl engine;

	private final int K = 5;

	public UndoManagerImpl(EngineImpl engine) {
		this.engine = engine;
		pastStates = new ArrayList<>();
		futurStates = new ArrayList<>();
		pastCommands = new ArrayList<>();
		futurCommands = new ArrayList<>();
	}

	@Override
	public void store(Memento m) {

	}

	@Override
	public void store(MyPairImpl<CommandOriginator, Optional<Memento>> m) {
		// TODO

	}

	@Override
	public void undo() {
		// TODO

	}

	@Override
	public void redo() {
		// TODO

	}

}
