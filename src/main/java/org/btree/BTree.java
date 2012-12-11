package org.btree;

public class BTree {
	
	private Node<Integer, Integer> root;
	
	public BTree() {
		root = new Node<Integer, Integer>();
	}

	public BTree(int order) {
		root = new Node<Integer, Integer>(order);
	}
	
		
	public void add(IntegerBasedKey<Integer> intKey) {
		getRoot().findNodeToInsert(intKey.getKey()).add(3,3);
	}

	public Node<Integer, Integer> getRoot() {
		if(!root.isRoot()) {
			while(!root.isRoot()) {
				root = root.getParent();
			}
		}
		return root;
	}

}
