package fr.istic.aco.editor.userinterface;

import java.util.logging.Logger;

import fr.istic.aco.editor.*;
import fr.istic.aco.editor.command.*;
import fr.istic.aco.editor.recorder.Recorder;
import fr.istic.aco.editor.recorder.RecorderImpl;
import fr.istic.aco.editor.util.Command;

/**
 * An simple application for the text userInterface.
 */
public class Editor {

	private UserInterface userInterface;
	private Engine engine;
	private Recorder recorder;

	/**
	 * Initializes the text userInterface components and starts the application.
	 */
	public void run() {
		Logger.getGlobal().info("Starting...");
		this.userInterface = new UserInterfaceImpl();
		this.engine = new EngineImpl();
		this.recorder = new RecorderImpl();
		userInterface.setReadStream(System.in);
		configureCommands();
		userInterface.runInvokerLoop();
	}

	/**
	 * Configures the text userInterface commands.
	 */
	private void configureCommands() {
		// Engine commands(originators)
		userInterface.addCommand(Command.DELETE, new Delete(engine, recorder));
		userInterface.addCommand(Command.COPY, new CopySelectedText(engine, recorder));
		userInterface.addCommand(Command.PASTE, new PasteClipboard(engine, recorder));
		userInterface.addCommand(Command.SELECT, new SelectionChange(engine, userInterface, recorder));
		userInterface.addCommand(Command.INSERT, new Insert(engine, userInterface, recorder));
		userInterface.addCommand(Command.CUT, new CutSelectedText(engine, recorder));

		// Recorder commands
		userInterface.addCommand(Command.START, () -> recorder.start());
		userInterface.addCommand(Command.STOP, () -> recorder.stop());
		userInterface.addCommand(Command.REPLAY, () -> recorder.replay());

		// User interface & others commands
		userInterface.addCommand("quit", () -> userInterface.stopLoop());
		userInterface.addCommand("show", () -> System.out.println(">> " + engine.getBufferContents()));
		userInterface.addCommand("indexes", () -> System.out
				.println(engine.getSelection().getBeginIndex() + "," + engine.getSelection().getEndIndex()));
	}

	// To launch the application.
	public static void main(String lineArgs[]) {
		Editor client = new Editor();
		client.run();
	}
}
