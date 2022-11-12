package fr.istic.aco.editor.command;

import fr.istic.aco.editor.Engine;

public class CutSelectedText implements Command {

	private Engine engine;

	public CutSelectedText(Engine engine) {
		this.engine = engine;
	}

	@Override
	public void execute() {
		engine.cutSelectedText();
	}

}
