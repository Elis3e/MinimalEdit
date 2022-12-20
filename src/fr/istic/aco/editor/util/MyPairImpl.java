package fr.istic.aco.editor.util;

/**
 * Container that store a tuple of two objects.
 * 
 * @version 2.0
 */
public class MyPairImpl<T1, T2> {

	private T1 key;
	private T2 value;

	/**
	 * Creates a new pair.
	 * 
	 * @param key   The key for this pair
	 * @param value The value to use for this pair
	 */
	public MyPairImpl(T1 key, T2 value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Gets the key for this pair.
	 * 
	 * @return the key for this pair
	 */
	public T1 getKey() {
		return key;
	}

	/**
	 * Gets the value for this pair.
	 * 
	 * @return the value for this pair
	 */
	public T2 getValue() {
		return value;
	}

//	@Override
//	public String toString() {
//		return "MyPairImpl(" + key + ", " + value + ")";
//	}

}
