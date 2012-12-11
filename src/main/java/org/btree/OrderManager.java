package org.btree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderManager {

	public Integer[] addElement(Integer[] list, int i) {
		// TODO Auto-generated method stub
		List<Integer> localList = Arrays.asList(list);
		localList.add(i);
		Collections.sort(localList);
		Integer [] result = new Integer[localList.size()];
		
		return localList.toArray(result);
	}

}
