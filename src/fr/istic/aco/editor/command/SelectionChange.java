package fr.istic.aco.editor.command;

import java.io.IOException;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.Selection;
import fr.istic.aco.editor.gui.UserInterface;

public class SelectionChange implements Command {

	private Engine engine;
	private UserInterface invoker;

	public SelectionChange(Engine engine, UserInterface invoker) {
		this.engine = engine;
		this.invoker = invoker;
	}

	@Override
	public void execute() {
		Selection selection = engine.getSelection();
		try {
			selection.setBeginIndex(invoker.getIndex());
			selection.setEndIndex(invoker.getIndex());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
