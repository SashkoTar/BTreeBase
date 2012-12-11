package org.btree;

import java.util.Arrays;


public class Node<K extends Comparable<K>, V> {
		
	//TODO Investigate more elegant generic using
	public Entry<K, V>[] keys;
	private Node<K, V> parent;	
	private final int order;
		
	public Node() {
		this(7);
	}
	
	public Node(int order) {
		this.order = order;
	}

	public void add(K key, V value) {
		add(new Entry<K, V>(key, value));
	} 
	
	//TODO Improve sorting strategy
	private void add(Entry<K, V> entry) {
		if (keys == null) {
			keys = new Entry[1];
			keys[0] = entry;
		} else {
			Entry<K, V>[] newKeys = new Entry[keys.length + 1];
			int keyIndex = 0;
			boolean isNewKeySet = false;
			for(int i = 0; i < newKeys.length; i++) {
				if(isIndexWithinBounds(keyIndex, keys) && (compareKey(entry, keys[keyIndex]) > 0 || isNewKeySet)) {
					newKeys[i] = keys[keyIndex];
					keyIndex++;
				} else {
					newKeys[i] = entry;	
					isNewKeySet = true;
					if(isIndexWithinBounds(keyIndex, keys)) {
						keys[keyIndex].setLeftChild(entry.getRightChild());
					}
					if(i-1 >= 0) {
						newKeys[i-1].setRightChild(entry.getLeftChild());	
					}
				}
			}
			keys = newKeys;
			if(hasMaximumKeys()) {
				splitAndAddMedianaToParent();
			}
		}		
	}

	private void splitAndAddMedianaToParent() {
		if(this.parent == null) {
			this.parent = new Node<K, V>(order);
		}
		Entry<K, V> mediana = split();
		mediana.getRightChild().parent = this.parent;
		this.parent.add(mediana);
	}	
	
	private boolean hasMaximumKeys() {
		return keys.length >= order;
	}
	
	private boolean isIndexWithinBounds(int index, Entry [] array) {
		return  index < array.length;
	}

	public Entry<K, V> split() {
		int splitIndex = keys.length/2;
		Entry<K, V> key = keys[splitIndex];
		initChildren(key);
		key.getRightChild().keys = Arrays.copyOfRange(keys, splitIndex + 1, keys.length);
		keys = Arrays.copyOfRange(keys, 0, splitIndex);
		return key;
	}
	
	private void initChildren(Entry<K, V> entry) {
		entry.setLeftChild(this);
		entry.setRightChild(new Node<K, V>());
	}

	public Node<K, V> getParent() {
		return parent;
	}

	public boolean containsKey(Entry<K, V> entry) {
		boolean result = false;
		for(int i = 0; i < keys.length; i++) {
			if(compareKey(keys[i], entry) == 0) {
				result = true;
			}
		}
		return result;
	}

	public boolean isLeaf() {
		return keys[0].getLeftChild() == null;
	}

	public boolean isRoot() {
		return this.parent == null;
	}

	public Node<K, V> findNodeToInsert(K key) {
		return findNodeToInsert(new Entry<K, V>(key, null));
	}
	
	private Node<K, V> findNodeToInsert(Entry<K, V> entry) {
		Node<K, V> result = null;
		if(keys == null || isLeaf()) {
			return this;
		}
		for(int i = 0; i < keys.length; i++) {
			if(compareKey(keys[i], entry) > 0) {
				result = keys[i].getLeftChild();
				break;
			}
		}
		if(result == null) {
			result = keys[keys.length - 1].getRightChild();
		}
		if(!result.isLeaf()) {
			result.findNodeToInsert(entry);
		}
		return result;
	}

	private int compareKey(Entry<K, V> entry, Entry<K, V> entry2){
		return entry.getKey().compareTo(entry2.getKey());
	}

}
