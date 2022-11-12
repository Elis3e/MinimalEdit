package fr.istic.aco.editor.command;

import fr.istic.aco.editor.Engine;

public class PasteClipboard implements Command {

	private Engine engine;

	public PasteClipboard(Engine engine) {
		this.engine = engine;
	}

	@Override
	public void execute() {
		engine.pasteClipboard();
	}

}
