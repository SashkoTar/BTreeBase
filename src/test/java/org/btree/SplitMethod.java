package org.btree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SplitMethod {
	private Node getInitializedNode(){
		Node<Integer, Integer> node = new Node<Integer, Integer>();	
		Entry [] keys = {new IntegerBasedKey<Integer>(2, 2),new IntegerBasedKey<Integer>(4, 4),new IntegerBasedKey<Integer>(6, 6),new IntegerBasedKey<Integer>(7, 7)};
		node.keys = keys;
		return node;
		
	} 
	
	@Test
	public void testIfNodeCanBeSplited(){
		Node node = getInitializedNode();
		node.add(8,8);
		Entry key = node.split();
		assertTrue(key.getLeftChild() instanceof Node);
		assertTrue(key.getRightChild() instanceof Node);
		assertEquals(6, key.getValue());
		assertEquals(2, key.getLeftChild().keys.length);
		assertEquals(2, key.getRightChild().keys.length);
	}
	
	@Test
	public void testIfLeftChildrenIsInitialNode() {
		Node node = getInitializedNode();
		node.add(8, 8);
		Entry key = node.split();
		assertTrue(key.getLeftChild() == node);
	}
}
