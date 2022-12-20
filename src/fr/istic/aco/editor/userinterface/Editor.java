package fr.istic.aco.editor.userinterface;

import java.util.logging.Logger;

import fr.istic.aco.editor.*;
import fr.istic.aco.editor.command.*;
import fr.istic.aco.editor.recorder.Recorder;
import fr.istic.aco.editor.recorder.RecorderImpl;
import fr.istic.aco.editor.undomanager.UndoManager;
import fr.istic.aco.editor.undomanager.UndoManagerImpl;
import fr.istic.aco.editor.util.Command;

/**
 * An simple application for the text userInterface.
 */
public class Editor {

	private UserInterface userInterface;
	private EngineOriginator engine;
	private Recorder recorder;
	private UndoManager undoManager;

	/**
	 * Initializes the text userInterface components and starts the application.
	 */
	public void run() {
		Logger.getGlobal().info("Starting...");
		this.userInterface = new UserInterfaceImpl();
		this.engine = new EngineImpl();
		this.recorder = new RecorderImpl();
		this.undoManager = new UndoManagerImpl(engine);
		userInterface.setReadStream(System.in);
		configureCommands();
		userInterface.runInvokerLoop();
	}

	/**
	 * Configures the text userInterface commands.
	 */
	private void configureCommands() {
		// Engine commands(originators)
		userInterface.addCommand(Command.SELECT, new SelectionChange(engine, userInterface, recorder));
		userInterface.addCommand(Command.INSERT, new Insert(engine, userInterface, recorder, undoManager));
		userInterface.addCommand(Command.CUT, new CutSelectedText(engine, recorder, undoManager));
		userInterface.addCommand(Command.COPY, new CopySelectedText(engine, recorder));
		userInterface.addCommand(Command.PASTE, new PasteClipboard(engine, recorder, undoManager));
		userInterface.addCommand(Command.DELETE, new Delete(engine, recorder, undoManager));

		// Recorder commands
		userInterface.addCommand(Command.START, () -> recorder.start());
		userInterface.addCommand(Command.STOP, () -> recorder.stop());
		userInterface.addCommand(Command.REPLAY, () -> recorder.replay());

		// UndoManager commands
		userInterface.addCommand(Command.UNDO, () -> undoManager.undo());
		userInterface.addCommand(Command.REDO, () -> undoManager.redo());

		// User interface & others commands
		userInterface.addCommand("quit", () -> userInterface.stopLoop());
		userInterface.addCommand("show", () -> System.out.println(">> " + engine.getBufferContents()));
		userInterface.addCommand("index", () -> System.out
				.println(engine.getSelection().getBeginIndex() + "," + engine.getSelection().getEndIndex()));
	}

	public static void main(String lineArgs[]) {
		Editor client = new Editor();
		client.run();
	}
}
