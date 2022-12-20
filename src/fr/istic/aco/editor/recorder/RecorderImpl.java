package fr.istic.aco.editor.recorder;

import java.util.ArrayList;
import java.util.List;

import fr.istic.aco.editor.command.CommandOriginator;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.util.MyPairImpl;

/**
 * 
 * Implementation of the commands recorder.
 * 
 * @version 2.0
 */
public class RecorderImpl implements Recorder {

	private boolean recording;

	private boolean replaying;

	private List<MyPairImpl<CommandOriginator, Memento>> savedStates;

	/**
	 * Creates an empty recorder.
	 */
	public RecorderImpl() {
		savedStates = new ArrayList<>();
		recording = false;
		replaying = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(CommandOriginator cmdTosave) {
		if (recording)
			savedStates.add(new MyPairImpl<>(cmdTosave, cmdTosave.getMemento()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start() {
		recording = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop() {
		recording = false;
	}

	/**
	 * This method is useful in the case the command thats must be replayed requires
	 * user input, such as Insert or SelectionChange
	 * 
	 * @return true this recorder is replaying registered commands.
	 */
	public boolean isReplaying() {
		return replaying;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void replay() {
		if (!recording) {
			replaying = true;
			for (MyPairImpl<CommandOriginator, Memento> pair : savedStates) {
				CommandOriginator cmd = pair.getKey();
				Memento stateToRestore = pair.getValue();
				cmd.setMemento(stateToRestore);
				cmd.execute();
				// System.out.println(pair);
			}
			replaying = false;
		}
	}

}
