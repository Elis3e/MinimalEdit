package fr.istic.aco.editor;

public class SelectionImpl implements Selection {

	static final Integer BUFFER_BEGIN_INDEX = 0;

	private StringBuilder buffer;
	private Integer beginIndex;
	private Integer endIndex;

	public SelectionImpl(StringBuilder buffer, Integer beginIndex, Integer endIndex) {
		this.buffer = buffer;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}

	public SelectionImpl(StringBuilder buffer) {
		this.buffer = buffer;
		this.beginIndex = BUFFER_BEGIN_INDEX;
		this.beginIndex = BUFFER_BEGIN_INDEX;
		this.endIndex = BUFFER_BEGIN_INDEX;
	}

	// TODO pre 0 <= beginIndex < endIndex <= buffer.length()
	@Override
	public int getBeginIndex() {
		return this.beginIndex;
	}

	@Override
	public int getEndIndex() {
		return this.endIndex;
	}

	@Override
	public int getBufferBeginIndex() {
		return BUFFER_BEGIN_INDEX;
	}

	@Override
	public int getBufferEndIndex() {
		return buffer.length();
	}

	@Override
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	@Override
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
