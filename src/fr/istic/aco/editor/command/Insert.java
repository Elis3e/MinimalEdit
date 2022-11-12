package fr.istic.aco.editor.command;

import java.io.IOException;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.gui.UserInterface;

public class Insert implements Command {

	private Engine engine;
	private UserInterface invoker;

	public Insert(Engine engine, UserInterface invoker) {
		this.engine = engine;
		this.invoker = invoker;
	}

	@Override
	public void execute() {
		try {
			engine.insert(invoker.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
