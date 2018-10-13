package com.repl.common;

import java.util.Comparator;

public class ReplListComparator implements Comparator {

	private int fieldIndex;

	private int orderby;

	public ReplListComparator(int fieldIndex, int orderby) {
		super();
		this.fieldIndex = fieldIndex;
		this.orderby = orderby;
	}

	public int compare(Object o1, Object o2) {
		Object[] obj1 = (Object[]) o1;
		Object[] obj2 = (Object[]) o2;
		if (null == obj1[fieldIndex])
			return 1 * orderby;
		else if (null == obj2[fieldIndex])
			return -1 * orderby;
		Object data1 = obj1[fieldIndex];
		Object data2 = obj2[fieldIndex];
		if (data1 instanceof String) {
			if (null != data1) {
				data1 = data1.toString().toUpperCase().trim();
			}
			if (null != data2) {
				data2 = data2.toString().toUpperCase().trim();
			}

		}
		return data1.toString().compareTo(data2.toString()) * orderby;
	}
}
