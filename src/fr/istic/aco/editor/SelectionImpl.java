package fr.istic.aco.editor;

public class SelectionImpl implements Selection {

	static final Integer BUFFER_BEGIN_INDEX = 0;

	private StringBuilder buffer;
	private Integer beginIndex;
	private Integer endIndex;

	public SelectionImpl(StringBuilder buffer) {
		this.buffer = buffer;
		this.beginIndex = BUFFER_BEGIN_INDEX;
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
		if (beginIndex > this.getBufferEndIndex())
			throw new IllegalArgumentException("L'index de debut doit etre inferieur à l'index de fin");
		if (beginIndex < this.getBufferBeginIndex())
			throw new IllegalArgumentException("L'index de debut doit etre superieur à 0");
		this.beginIndex = beginIndex;
	}

	@Override
	public void setEndIndex(int endIndex) {
		if (this.getBeginIndex() > endIndex)
			throw new IllegalArgumentException("L'index de fin doit etre superieur à l'index de debut");
		if (this.getBufferEndIndex() < endIndex)
			throw new IllegalArgumentException("L'index de fin doit etre inferieur à la taille du buffer");
		this.endIndex = endIndex;
	}

}
