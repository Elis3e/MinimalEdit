package fr.istic.aco.editor.gui;

/**
 * Created by plouzeau on 2014-06-01.
 */

import java.util.logging.Logger;

import fr.istic.aco.editor.*;
import fr.istic.aco.editor.command.*;

/**
 * A simple demo application for the Greetings Command design pattern example
 */
public class EditorConfigurator {

	private UserInterface invoker;
	private Engine receiver;

	private void run() {
		Logger.getGlobal().info("Starting...");
		this.invoker = new Editor();
		this.receiver = new EngineImpl();
		invoker.setReadStream(System.in);
		configureCommands();
		invoker.runInvokerLoop();
	}

	private void configureCommands() {
		invoker.addCommand("delete", new Delete(receiver));
		invoker.addCommand("copy", new CopySelectedText(receiver));
		invoker.addCommand("paste", new PasteClipboard(receiver));
		invoker.addCommand("select", new SelectionChange(receiver, invoker));
		invoker.addCommand("insert", new Insert(receiver, invoker));
		invoker.addCommand("cut", new CutSelectedText(receiver));
		// An example of Java 8 lambdas
		invoker.addCommand("show", () -> System.err.println("Buffer content >> " + receiver.getBufferContents()));
	}

	public static void main(String lineArgs[]) {
		EditorConfigurator client = new EditorConfigurator();
		client.run();
	}
}
