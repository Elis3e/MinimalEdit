package fr.istic.aco.editor;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

	private Engine initEngine;

	private Engine engine;

	private String bufferContent;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		initEngine = new EngineImpl();

		bufferContent = "abcdef";
		engine = new EngineImpl(bufferContent);
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(3);
	}

	@Test
	@DisplayName("Buffer must be empty after initialisation")
	void BufferMustbeEmptyAfterInitialization() {
		//Selection selection = initEngine.getSelection();
		//assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
		assertEquals("", initEngine.getBufferContents());

//		initEngine.insert(bufferContent);
//		assertEquals(6, initEngine.getSelection().getBeginIndex());
//		assertEquals(6, initEngine.getSelection().getEndIndex());
	}

	@Test
	void getBufferContents() {
		assertEquals("abcdef", engine.getBufferContents());
	}

	@Test
	void getClipboardContents() {
		assertEquals("", engine.getClipboardContents());
	}

	@Test
	void cutSelectedText() {
		assertEquals("ghi", engine.getClipboardContents());
		engine.cutSelectedText();
		assertEquals("def", engine.getBufferContents());
		assertEquals("abc", engine.getClipboardContents());
		assertEquals(0, engine.getSelection().getBeginIndex());
		assertEquals(0, engine.getSelection().getEndIndex());
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
		assertEquals(9, engine.getSelection().getBeginIndex());
		assertEquals(9, engine.getSelection().getEndIndex());
	}
}
