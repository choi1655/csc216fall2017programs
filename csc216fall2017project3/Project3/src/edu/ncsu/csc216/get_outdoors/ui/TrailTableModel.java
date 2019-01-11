package edu.ncsu.csc216.get_outdoors.ui;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * TrailTableModel is a wrapper for the information in List that can be used by
 * a JTable.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 *
 */
public class TrailTableModel extends TableModel {

	/** Serial version UID */
	private static final long serialVersionUID = 1L;
	/** Names for each of the columns in the TableModel */
	private String[] colNames = { "ID", "Trail Name", "Closed for Maintenance?", "Snow", "Distance", "Difficulty" };

	/**
	 * Creates the model from the given data.
	 * 
	 * @param data
	 *            the data to populate the TableModel
	 */
	public TrailTableModel(Object[][] data) {
		super(data);

	}

	/**
	 * Returns the number of columns in the data.
	 * 
	 * @return the number of columns in the data
	 */
	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	/**
	 * Returns the column name at the given index.
	 * 
	 * @param col
	 *            the index for finding the column name
	 * @return the column name at the given index
	 */
	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}

	/**
	 * Returns the Data object associated with the given row in the TableModel.
	 * 
	 * @param row
	 *            the Data to return
	 * @return the Data for the given row
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Data getRowData(int row) {

		return new TrailData((String) data[row][0], (String) data[row][1], (Boolean) data[row][2],
				(Double) data[row][3], (Double) data[row][4], (Difficulty) data[row][5], (SortedArrayList<Activity>) data[row][6]);
	}

	/**
	 * Sets the given row with the provided Data.
	 * 
	 * @param d
	 *            Data to set in the row
	 * @param row
	 *            the row to set
	 */
	@Override
	public void setData(Data d, int row) {
		TrailData tr = (TrailData) d;
		setValueAt(tr.getTrailID(), row, 0);
		setValueAt(tr.getTrailName(), row, 1);
		setValueAt(tr.isClosedForMaintenance(), row, 2);
		setValueAt(tr.getSnow(), row, 3);
		setValueAt(tr.getDistance(), row, 4);
		setValueAt(tr.getDifficulty(), row, 5);
		setValueAt(tr.getActivities(), row, 6);

	}

}
