package org.btree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BTreeTest {

	@Test
	public void testIfTreeHasRoot(){
		BTree tree = new BTree();
		assertNotNull(tree.getRoot());
	}
	
	@Test
	public void testIfRootIsChanged() {
		BTree tree = new BTree(3);
		Node oldRoot = tree.getRoot();
		tree.add(new IntegerBasedKey<Integer>(2));
		tree.add(new IntegerBasedKey<Integer>(4));
		tree.add(new IntegerBasedKey<Integer>(1));
		//tree.add(new IntegerBasedKey<Integer>(6));
		//tree.add(new IntegerBasedKey<Integer>(11));
		//tree.add(new IntegerBasedKey<Integer>(7));
		//tree.add(new IntegerBasedKey<Integer>(3));
		assertTrue(oldRoot != tree.getRoot());	
	}
	
}
