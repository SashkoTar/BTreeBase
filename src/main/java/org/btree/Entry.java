package org.btree;

public class Entry<K extends Comparable<K>, V> {	

	private K key;
	private V value;
	private Node<K, V> leftChild;
	private Node<K, V> rightChild;
	

	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V obj) {
		this.value = obj;
	}


	public Node<K, V> getLeftChild() {
		return leftChild;
	}

	public Node<K, V> getRightChild() {
		return rightChild;
	}

	public void setLeftChild(Node<K, V> leftChild) {
		this.leftChild = leftChild;
	}

	public void setRightChild(Node<K, V> rightChild) {
		this.rightChild = rightChild;
	}

	public K getKey() {
		return key;
	}

}
