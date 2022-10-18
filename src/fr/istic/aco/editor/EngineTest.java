package fr.istic.aco.editor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

	private Engine initEngine;

	private Engine engine;

	private StringBuilder buffer;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		initEngine = new EngineImpl();

		buffer = new StringBuilder("abcdef");
		engine = new EngineImpl(buffer, "ghi");
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(3);
	}

	@Test
	@DisplayName("Buffer must be empty after initialisation")
	void getSelection() {
		Selection selection = engine.getSelection();
		assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
		assertEquals("", initEngine.getBufferContents());
	}

	@Test
	void getBufferContents() {
		assertEquals("abcdef", engine.getBufferContents());
	}

	@Test
	void getClipboardContents() {
		assertEquals("ghi", engine.getClipboardContents());
	}

	@Test
	void cutSelectedText() {
		assertEquals("ghi", engine.getClipboardContents());
		engine.cutSelectedText();
		assertEquals("def", engine.getBufferContents());
		assertEquals("abc", engine.getClipboardContents());

	}

	@Test
	void copySelectedText() {
		assertEquals("ghi", engine.getClipboardContents());
		engine.copySelectedText();
		assertEquals("abcdef", engine.getBufferContents());
		assertEquals("abc", engine.getClipboardContents());
	}

	@Test
	void pasteClipboard() {
		engine.getSelection().setBeginIndex(6);
		engine.getSelection().setEndIndex(6);
		engine.pasteClipboard();
		assertEquals("abcdefghi", engine.getBufferContents());
	}
}
