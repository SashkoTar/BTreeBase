package org.btree;

public class IntegerBasedKey<V> extends Entry<Integer, V> {
	
	public IntegerBasedKey(Integer key) {
		this(key, null);
	}

	public IntegerBasedKey(Integer key, V value) {
		super(key, value);
	}

}
