package fr.istic.aco.editor;

/**
 * Implements all selection control operations.
 * 
 * @version 1.0
 */
public class SelectionImpl implements Selection {

	private final Integer BUFFER_BEGIN_INDEX = 0;

	private Integer beginIndex;
	private Integer endIndex;

	private StringBuilder buffer;

	/**
	 * Constructs a selection object with the specified buffer.
	 * 
	 * @param buffer the buffer on which selection operations will be performed
	 */
	public SelectionImpl(StringBuilder buffer) {
		this.buffer = buffer;
		this.beginIndex = BUFFER_BEGIN_INDEX;
		this.endIndex = BUFFER_BEGIN_INDEX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getBeginIndex() {
		return this.beginIndex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getEndIndex() {
		return this.endIndex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getBufferBeginIndex() {
		return BUFFER_BEGIN_INDEX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getBufferEndIndex() {
		return buffer.length();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setBeginIndex(int newBeginIndex) {
		if (newBeginIndex < BUFFER_BEGIN_INDEX || newBeginIndex > getBufferEndIndex())
			throw new IndexOutOfBoundsException();
		this.beginIndex = newBeginIndex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEndIndex(int newEndIndex) {
		if (newEndIndex > getBufferEndIndex() || newEndIndex < BUFFER_BEGIN_INDEX)
			throw new IndexOutOfBoundsException();
		this.endIndex = newEndIndex;
	}

}
