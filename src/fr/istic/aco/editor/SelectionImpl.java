package fr.istic.aco.editor;

public class SelectionImpl implements Selection {

	static final Integer BUFFER_BEGIN_INDEX = 0;

	private StringBuilder buffer;
	private Integer beginIndex;
	private Integer endIndex;

	public SelectionImpl(StringBuilder buffer, Integer beginIndex, Integer endIndex) {
		this.buffer = buffer;
		this.beginIndex = BUFFER_BEGIN_INDEX;
		this.endIndex = endIndex;
	}

	public SelectionImpl() {
		this.buffer = new StringBuilder();
		this.beginIndex = BUFFER_BEGIN_INDEX;
		this.beginIndex = BUFFER_BEGIN_INDEX;
		this.endIndex = BUFFER_BEGIN_INDEX;
	}

	public StringBuilder getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuilder buffer) {
		this.buffer = buffer;
	}

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
		return buffer.capacity();
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
