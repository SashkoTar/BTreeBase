package org.btree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UtilTest {
	
	private Node<Integer, Integer> node, node7;
	
	@Before
	public void init() {
		node = getInitializedNode();
		node7 = get7OrederedNode();
	}
	//@Test
	public void testInsert() {
		OrderManager manager = new OrderManager();
		Integer [] list = {1,3,5};
		Integer [] expected = {1,2,3,5};
		assertArrayEquals(expected, manager.addElement(list, 2));
		
	}
	
	@Test
	public void testInsertAtFirstPosition() {
		node.add(1, 1);
		assertEquals(1, node.keys[0].getValue().intValue());
	}
	
	@Test
	public void testInsertAtSecondPosition() {
		node.add(3,3);
		assertEquals(3, node.keys[1].getValue().intValue());
	}
	
	@Test
	public void testInsertAtLastPosition() {
		node.add(8,8);
		assertEquals(8, node.keys[4].getValue().intValue());
	}	
	
	private Node<Integer, Integer> getInitializedNode(){
        node = new Node<Integer, Integer>();	
		Entry [] keys = {new IntegerBasedKey<Integer>(2, 2),new IntegerBasedKey<Integer>(4, 4),new IntegerBasedKey<Integer>(6, 6),new IntegerBasedKey<Integer>(7, 7)};
		node.keys = keys;
		return node;
		
	} 
	
	@Test
	public void testIfCreatedKeyDoesNotHaveChildren(){
		Entry key = new IntegerBasedKey<Integer>(2, 2);
		assertNull(key.getLeftChild());
		assertNull(key.getRightChild());
	}
	
	/*
	@Test
	public void testInsertedInLastHasCorrectChild() {
		Node rootNode = new Node();
		rootNode.add(getChildrenMockedIntegerKey(1, 1));
		rootNode.add(getChildrenMockedIntegerKey(2, 2));
		assertTrue(rootNode.keys[0].getRightChild() == rootNode.keys[1].getLeftChild());		
	}

	@Test
	public void testInsertedInFirstHasCorrectChild() {
		Node rootNode = new Node();
		rootNode.add(getChildrenMockedIntegerKey(3, 3));
		rootNode.add(getChildrenMockedIntegerKey(2, 2));
		assertTrue(rootNode.keys[0].getRightChild() == rootNode.keys[1].getLeftChild());		
	}
	
	private void mockChildren(Entry key) {
		key.setLeftChild(new Node());
		key.setRightChild(new Node());
	}
	
	private Entry getChildrenMockedIntegerKey(int key, int data) {
		IntegerBasedKey<Integer> intKey = new IntegerBasedKey<Integer>(key,data);
		mockChildren(intKey);
		return intKey;
	}
	*/
	private Entry getIntegerKey(int k) {
		return new IntegerBasedKey<Integer>(k, k);
	}
	
	private Node<Integer, Integer> get7OrederedNode() {
		Node<Integer, Integer> node = new Node<Integer, Integer>();
		assertNull(node.getParent());
		node.add(1, 1);
		node.add(12, 12);
		node.add(8, 8);
		node.add(2, 2);
		node.add(25, 25);
		node.add(4, 4);
		node.add(7, 7);
		return node;
	}
	
	@Test
	public void testIfParentIsCreated() {
		assertNotNull(node7.getParent());
		assertEquals(1, node7.getParent().keys.length);
		assertEquals(7, node7.getParent().keys[0].getValue().intValue());
	}
	
	@Test
	public void testIfCurrentNodeBecomesLeftOne(){
		assertEquals(3, node7.keys.length);
		assertEquals(1, node7.keys[0].getValue().intValue());
	}
	
	@Test
	public void testIfRightSiblingHasCorrectData(){
		Node rightSibling = node7.getParent().keys[0].getRightChild();
		assertEquals(node7.getParent(), rightSibling.getParent());
	}
	
	@Test
	public void testContainsKey() {
		IntegerBasedKey<Integer> intKey = new IntegerBasedKey<Integer>(3,null);
		assertFalse(node7.containsKey(intKey));
		node7.add(3, 3);
		assertTrue(node7.containsKey(intKey));
	}
	
	@Test
	public void testIsLeaf() {
		assertTrue(node7.isLeaf());
	} 
	
	@Test
	public void testIsRoot() {
		assertTrue(node7.getParent().isRoot());
	}
	
	@Test
	public void testFindNodeToInsertFirst(){
		assertEquals(node7, node7.getParent().findNodeToInsert(3));
	}
	
	@Test
	public void testFindNodeToInsertLast(){
		Node rightSibling = node7.getParent().keys[0].getRightChild();
		assertEquals(rightSibling, node7.getParent().findNodeToInsert(37));
	}
}

