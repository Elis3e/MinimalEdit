package fr.istic.aco.editor.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.EngineImpl;
import fr.istic.aco.editor.Selection;

import static org.junit.jupiter.api.Assertions.*;

//
class EngineTest {

	private Engine engine;

	private String bufferContent;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		engine = new EngineImpl();
		bufferContent = "Toto likes football";
	}

	@Test
	@DisplayName("Buffer & clipboard must be empty after initialisation")
	void getSelection() {
		Selection selection = engine.getSelection();

		assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
		assertEquals("", engine.getBufferContents());
		assertEquals("", engine.getClipboardContents());

	}

	@Test
	@DisplayName("Begin index and end index must be just after the inserted text")
	void insert() {
		Selection selection = engine.getSelection();

		engine.insert(bufferContent);

		assertEquals(19, selection.getBeginIndex());
		assertEquals(19, selection.getEndIndex());
	}

	@Test
	@DisplayName("The buffer contains the inserted text")
	void getBufferContents() {
		engine.insert(bufferContent);
		assertEquals("Toto likes football", engine.getBufferContents());
	}

	@Test
	@DisplayName("The cut text is erased from the buffer")
	void cutSelectedText() {
		Selection selection = engine.getSelection();
		engine.insert(bufferContent);

		// select "foot" in "football"
		selection.setBeginIndex(11);
		selection.setEndIndex(15);

		// cut it
		engine.cutSelectedText();

		// "foot" must be erased from the buffer
		assertEquals("Toto likes ball", engine.getBufferContents());
		assertEquals("foot", engine.getClipboardContents());
	}

	@Test
	@DisplayName("The buffer remains unchanged after a copy")
	void copySelectedText() {
		Selection selection = engine.getSelection();
		engine.insert(bufferContent);

		// select "foot" in "football"
		selection.setBeginIndex(11);
		selection.setEndIndex(15);

		// copy it
		engine.copySelectedText();

		// the buffer remains unchanged
		assertEquals("Toto likes football", engine.getBufferContents());
		assertEquals("foot", engine.getClipboardContents());
	}

	@Test
	@DisplayName("The text in the clipboard replaces the current selected text")
	void pasteClipboard() {
		Selection selection = engine.getSelection();
		engine.insert(bufferContent + "basket");
		assertEquals("Toto likes footballbasket", engine.getBufferContents());

		// select "basket"
		selection.setBeginIndex(19);
		selection.setEndIndex(25);

		// cut it
		engine.cutSelectedText();

		// select "foot" in "football"
		selection.setBeginIndex(11);
		selection.setEndIndex(15);

		// paste it
		engine.pasteClipboard();

		// "basket" must replace "foot"
		assertEquals("Toto likes basketball", engine.getBufferContents());
		assertEquals("basket", engine.getClipboardContents());
	}

	@Test
	@DisplayName("The current selected text appear in the clipboard after a copy")
	void getClipboardContents() {
		Selection selection = engine.getSelection();
		engine.insert(bufferContent); // "Toto likes football"

		// select the word "likes"
		selection.setBeginIndex(5);
		selection.setEndIndex(10);

		// copy (or cut) it
		engine.copySelectedText();

		// verify if the copied word appear in the clipboard
		assertEquals("likes", engine.getClipboardContents());
	}

	@Test
	@DisplayName("Selection out of the buffer bounds")
	void indexOutOfBounds() throws IndexOutOfBoundsException {
		Selection selection = engine.getSelection();

		engine.insert(bufferContent);
		assertThrows(IndexOutOfBoundsException.class, () -> selection.setEndIndex(100));
		assertThrows(IndexOutOfBoundsException.class, () -> selection.setEndIndex(-5));

		assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(100));
		assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(-17));
	}
	
	@Test
	@DisplayName("Selection with reversed indexes")
	void reversedIndexes() {
		Selection selection = engine.getSelection();
		engine.insert(bufferContent);
		
		// Both indexes are in the end of buffers
		// Move beginIndex before endIndex
		selection.setBeginIndex(5);
		selection.setEndIndex(5);

		// EndIndex < current beginIndex = 5
		selection.setEndIndex(3);
		assertEquals(3, selection.getBeginIndex());
		
		// BeginIndex > current endIndex  = 5
		selection.setBeginIndex(8);
		assertEquals(8, selection.getEndIndex());
	}
}
