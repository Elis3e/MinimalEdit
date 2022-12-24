package fr.istic.aco.editor.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.*;
import fr.istic.aco.editor.command.*;
import fr.istic.aco.editor.recorder.Recorder;
import fr.istic.aco.editor.recorder.RecorderImpl;
import fr.istic.aco.editor.undomanager.UndoManager;
import fr.istic.aco.editor.undomanager.UndoManagerImpl;
import fr.istic.aco.editor.userinterface.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

import fr.istic.aco.editor.util.Command;

public class CommandTest {

	private UserInterface editor;

	private EngineOriginator engine;

	private Recorder recorder;

	private UndoManager undoManager;

	private String contentExample;

	private void setReadStream(String s) {
		editor.setReadStream(new ByteArrayInputStream(s.getBytes()));
	}

	@org.junit.jupiter.api.BeforeEach
	void setUp() {

		engine = new EngineImpl();
		editor = new UserInterfaceImpl();
		recorder = new RecorderImpl();
		undoManager = new UndoManagerImpl(engine);

		editor.addCommand(Command.INSERT, new Insert(engine, editor, recorder, undoManager));
		editor.addCommand(Command.SELECT, new SelectionChange(engine, editor, recorder));
		editor.addCommand(Command.CUT, new CutSelectedText(engine, recorder, undoManager));
		editor.addCommand(Command.COPY, new CopySelectedText(engine, recorder));
		editor.addCommand(Command.PASTE, new PasteClipboard(engine, recorder, undoManager));
		editor.addCommand(Command.DELETE, new Delete(engine, recorder, undoManager));

		contentExample = "Toto likes football";
	}

	@Test
	@DisplayName("Begin index and end index must be just after the inserted text")
	void insert() {

		setReadStream(contentExample);

		editor.executeCommand(Command.INSERT);

		assertEquals(19, engine.getSelection().getBeginIndex());
		assertEquals(19, engine.getSelection().getEndIndex());
	}

	@Test
	@DisplayName("The buffer contains the inserted text")
	void getBufferContents() {

		setReadStream(contentExample);

		editor.executeCommand(Command.INSERT);

		assertEquals(contentExample, engine.getBufferContents());
	}

	@Test
	@DisplayName("Buffer & clipboard must be empty after initialisation")
	void getSelection() {

		setReadStream(contentExample);

		editor.executeCommand(Command.INSERT);

		setReadStream("11\n15");

		editor.executeCommand(Command.SELECT);

		assertEquals(11, engine.getSelection().getBeginIndex());
		assertEquals(15, engine.getSelection().getEndIndex());
	}

	@Test
	@DisplayName("The cut text is erased from the buffer")
	void SelectedText() {
		getSelection();

		// cut it
		editor.executeCommand(Command.CUT);

		// "foot" must be erased from the buffer
		assertEquals("Toto likes ball", engine.getBufferContents());
		assertEquals("foot", engine.getClipboardContents());
	}

	@Test
	@DisplayName("The buffer remains unchanged after a copy")
	void copySelectedText() {

		getSelection();

		// copy it
		editor.executeCommand(Command.COPY);

		// the buffer remains unchanged
		assertEquals(contentExample, engine.getBufferContents());
		assertEquals("foot", engine.getClipboardContents());
	}

	@Test
	@DisplayName("The text in the clipboard replaces the current selected text")
	void pasteClipboard() {

		setReadStream(contentExample + "basket");
		editor.executeCommand(Command.INSERT);

		assertEquals("Toto likes footballbasket", engine.getBufferContents());

		// select "basket"
		setReadStream("19\n25");
		editor.executeCommand(Command.SELECT);

		// cut it
		editor.executeCommand(Command.CUT);

		// select "basket"
		setReadStream("11\n15");
		editor.executeCommand(Command.SELECT);

		// paste it
		editor.executeCommand(Command.PASTE);

		// "basket" must replace "foot"
		assertEquals("Toto likes basketball", engine.getBufferContents());
		assertEquals("basket", engine.getClipboardContents());
	}

	@Test
	@DisplayName("The current selected text appear in the clipboard after a copy")
	void getClipboardContents() {

		setReadStream(contentExample);
		editor.executeCommand(Command.INSERT); // "Toto likes football"

		// select the word "likes"
		setReadStream("5\n10");
		editor.executeCommand(Command.SELECT);

		// copy (or cut) it
		editor.executeCommand(Command.COPY);

		// verify if the copied word appear in the clipboard
		assertEquals("likes", engine.getClipboardContents());
	}

	@Test
	@DisplayName("Delete has no effect when beginIndex equals to endIndex")
	void delete() {

		// delete has no effect on empty buffer (after initialisation)
		editor.executeCommand(Command.DELETE);

		assertEquals("", engine.getBufferContents());

		// delete has no effect when beginIndex == endIndex

		setReadStream(contentExample); // "Toto likes football"
		editor.executeCommand(Command.INSERT);
		setReadStream("5\n5");
		editor.executeCommand(Command.SELECT);
		engine.delete();

		assertEquals(contentExample, engine.getBufferContents());
	}

	// TODO Exceptions thrown tests
}
