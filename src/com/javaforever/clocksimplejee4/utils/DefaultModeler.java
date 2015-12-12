package com.javaforever.clocksimplejee4.utils;

/**
 * DefaultModeler description
 * 
 * @author Jerry Shen
 * @version v 1.0 Dec. 2nd, 2004
 *------------------------------------------------
 * @History
 */

// The dumb defaultModeler for normal grids
// a Grid must had a Modeler
public class DefaultModeler implements IModeler {

	public IModel doModel(Row r) {
		return r;
	}

	public boolean validate(Row r) {
		return true;
	}

	public Row deModel(IModel m) {
		return Row.EMPTY_ROW;
	}
}