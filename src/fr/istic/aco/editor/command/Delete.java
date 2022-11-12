package fr.istic.aco.editor.command;

import fr.istic.aco.editor.Engine;

public class Delete implements Command {

	private Engine engine;

	public Delete(Engine engine) {
		super();
		this.engine = engine;
	}

	@Override
	public void execute() {
		engine.delete();
	}

}
