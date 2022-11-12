package fr.istic.aco.editor.command;

import fr.istic.aco.editor.Engine;

public class CopySelectedText implements Command {

	private Engine engine;

	public CopySelectedText(Engine engine) {
		this.engine = engine;
	}

	@Override
	public void execute() {
		engine.copySelectedText();
	}

}
