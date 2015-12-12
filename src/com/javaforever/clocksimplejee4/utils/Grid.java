package com.javaforever.clocksimplejee4.utils;

import java.util.ArrayList;
import java.util.TreeMap;
/** 
 * Grid description 
 *  
 * @author Jerry Shen 
 * @version v 1.0 Dec. 1st, 2004  
 * --------------------------------------------------------------------------- 
 * @History 
 */

public class Grid {

	private Row[] rows;

	private IModeler rowModeler;

	private int columnCount = 0;

	private TreeMap metaData;

	private Row metaRow;

	public Grid() {
		super();
		rowModeler = new DefaultModeler();
		metaData = new TreeMap();
		metaRow = null;
	}

	public Grid(IModeler m) {
		this();
		setRowModeler(m);
	}

	/**
	 * @return Returns the rowModeler.
	 */
	public IModeler getRowModeler() {
		return rowModeler;
	}

	/**
	 * @param rowModeler
	 *            The rowModeler to set.
	 */
	public void setRowModeler(IModeler rowModeler) {
		this.rowModeler = rowModeler;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int width) {
		this.columnCount = width;
	}

	// Get the rows count of the grid
	public int size() {
		if (rows == null)
			return 0;
		return rows.length;
	}

	// set up the grid table head row
	public void setMetaRow(Row r) {
		if (size() == 0 && r != null && r.getFieldsNumber() > 0) {
			metaRow = r;
			setColumnCount(r.getFieldsNumber());
		} else if (getColumnCount() > 0 && r.getFieldsNumber() == getColumnCount()) {
			metaRow = r;
		}
	}

	// get the grid table head row
	public Row getMetaRow() {
		if (metaRow != null)
			return metaRow.getRow();
		else
			return Row.EMPTY_ROW;
	}

	// get the grid table head by col
	public String getRowHead(int col) {
		Row r = getMetaRow();
		if (r != null
				&& !(r.getField(col) == null || "".equals(r.getField(col))))
			return r.getField(col);
		else
			return "";
	}

	// add a row to the grid, the row will be validated
	public void addRow(Row r) {
		if (rowModeler == null || Row.EMPTY_ROW.equals(r))
			return;
		if (!validate(r))
			return;
		if (rows == null) {
			if (getColumnCount() == 0) {
				rows = new Row[1];
				rows[0] = r;
				columnCount = r.getFieldsNumber();
				metaRow = new Row(getColumnCount());
			} else if (getColumnCount() == r.getFieldsNumber()) {
				rows = new Row[1];
				rows[0] = r;
			} else {
				return;
			}
		} else if (getColumnCount() == r.getFieldsNumber()) {
			Row[] rs = new Row[size() + 1];
			for (int i = 0; i < size(); i++)
				rs[i] = rows[i];
			rs[size()] = r;
			rows = rs;
		} else {
			return;
		}
	}

	// remove one row of the grid
	public void removeRow(int pos) {
		if (pos < 0 || size() <= pos || rows == null) {
			return;
		} else if (size() == 1 && pos == 0) {
			rows = null;
			return;
		} else {
			Row[] rs = new Row[size() - 1];
			for (int i = 0, j = 0; i < size() - 1; i++, j++) {
				if (j != pos) {
					rs[i] = rows[j];
				} else {
					i--;
				}
			}
			rows = rs;
		}
	}

	// model one row to a Model object
	public IModel doModel(int row) {
		if (rowModeler == null)
			return null;
		if (row < size() && row >= 0 && (!(rowModeler == null))) {
			return rowModeler.doModel(rows[row]);
		} else
			return null;
	}

	// set one field of the grid
	public void setField(int row, int col, String value) {
		if (row >= 0 && row < size() && col >= 0 && col < getColumnCount())
			rows[row].setField(col, value);
	}

	// get one field of the grid
	public String getField(int row, int col) {
		if (row >= 0 && row < size() && col >= 0 && col < getColumnCount()
				&& rows != null)
			return rows[row].getField(col);
		else
			return null;
	}

	// get the row of the grid
	public Row getRow(int row) {
		if (row >= 0 && row < size())
			return rows[row].getRow();
		else
			return null;
	}

	// set one row of the grid, the row will be validated
	public void setRow(int row, Row r) {
		if (r.getFieldsNumber() == getColumnCount() && row >= 0 && row < size()
				&& validate(r))
			rows[row] = r;
	}

	// get the row(use as an colum) of one colum
	public Row getColum(int col) {
		if (col >= 0 && col < getColumnCount()) {
			Row r = new Row(size());
			for (int i = 0; i < r.getFieldsNumber(); i++)
				r.setField(i, getField(i, col));
			return r;
		} else
			return null;

	}

	// set one colum of this grid
	public void setColum(int col, Row colum) {
		if (size() == colum.getFieldsNumber() && col >= 0
				&& col < getColumnCount()) {
			for (int i = 0; i < size(); i++)
				setField(i, col, colum.getField(i));
		}
	}

	// set the meta data carried by the grid
	// if the key is not existed, then create a new key
	public void setMetaData(String key, String value) {
		try {
			metaData.put(key, value);
		} catch (Exception e) {
		}
	}

	// get the meta data carried with the grid
	// return "" if the meta data is not existed
	public String getMetaData(String key) {
		try {
			String str = (String) metaData.get(key);
			if (str == null || "".equals(str))
				return "";
			else
				return str;
		} catch (Exception e) {
			return "";
		}
	}

	// generate the model list.
	public ArrayList generateList() {
		ArrayList arr = new ArrayList();
		for (int i = 0; i < size(); i++) {
			IModel m = doModel(i);
			if (m == null)
				return null;
			else
				arr.add(m);
		}
		return arr;
	}

	// print out the grid on the console
	public void consoleShow() {
		for (int i = 0; i < size(); i++) {
			Row r = getRow(i);
			r.consoleShow();
		}
	}

	// see wether one row in the grid is validated
	public boolean validate(Row r) {
		if (rowModeler == null)
			return false;
		else
			return rowModeler.validate(r);
	}

	// see all the rows in the grid is validated
	public boolean validate() {
		if (rowModeler == null)
			return false;
		else if (size() == 0)
			return false;
		else {
			for (int i = 0; i < size(); i++) {
				if (!validate(rows[i]))
					return false;
			}
			return true;
		}
	}

	// add an model to the grid using the rowmodeler's deModel
	public void addRowByModel(IModel m) {
		try {
			Row r = rowModeler.deModel(m);
			addRow(r);
		} catch (Exception e) {
		}
	}

}