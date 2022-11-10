package fr.istic.aco.editor;

public class SelectionImpl implements Selection {

	private static final Integer BUFFER_BEGIN_INDEX = 0;

	private StringBuilder buffer;
	private Integer beginIndex;
	private Integer endIndex;

	public SelectionImpl(StringBuilder buffer) {
		this.buffer = buffer;
		this.beginIndex = BUFFER_BEGIN_INDEX;
		this.endIndex = BUFFER_BEGIN_INDEX;
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
		return buffer.length();
	}

	@Override
	public void setBeginIndex(int beginIndex) {
		if (beginIndex < BUFFER_BEGIN_INDEX || beginIndex > getBufferEndIndex())
			throw new IndexOutOfBoundsException();
		if (beginIndex > this.endIndex) {
			this.beginIndex = this.endIndex;
			this.endIndex = beginIndex;
		} else {
			this.beginIndex = beginIndex;
		}
	}

	@Override
	public void setEndIndex(int endIndex) {
		if (endIndex > getBufferEndIndex() || endIndex < BUFFER_BEGIN_INDEX)
			throw new IndexOutOfBoundsException();
		if (endIndex < this.beginIndex) {
			this.endIndex = this.beginIndex;
			this.beginIndex = endIndex;
		} else {
			this.endIndex = endIndex;
		}
	}

}
