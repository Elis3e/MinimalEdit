package fr.istic.aco.editor.undomanager;

import java.util.Optional;

import fr.istic.aco.editor.command.CommandOriginator;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.util.MyPairImpl;

/**
 * 
 * @version 3.0
 */
public interface UndoManager {

	void store(Memento m);

	void store(MyPairImpl<CommandOriginator, Optional<Memento>> m);

	void undo();

	void redo();
}
