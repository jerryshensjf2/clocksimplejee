package com.javaforever.clocksimplejee4.utils;

/**
 * IModeler description
 * 
 * @author Jerry Shen
 * @version v 1.0 Dec. 1st, 2004
 *------------------------------------------------
 * @History
 */

public interface IModeler {
	// Model a model from a row
	public IModel doModel(Row r);

	// validate to see if the row can model a model
	public boolean validate(Row r);

	// deModel a model to a row
	public Row deModel(IModel m);
}