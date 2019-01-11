package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.ncsu.csc216.get_outdoors.model.TrailList;

/**
 * Represents the TrailListPane that maintains the list of trails.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 * @version 11242017
 *
 */
public class TrailListPane extends ListPane implements Observer {
	/** The serialization ID */
	private static final long serialVersionUID = 1L;
	/** The list of trails */
	private TrailList trailList;
	/** TrailTableModel which displays the list of trails */
	private TrailTableModel ttm;
	/** the column widths */
	private int[] colWidths = { 50, 125, 200, 75, 75, 100 };

	/**
	 * Constructs the TrailListPane that contains the list of trails to display
	 * in a table. Is an observer of the past in trailList. Constructs and new
	 * TrailTableModel and initializes the view of the Pane.
	 * 
	 * @param list
	 *            list of trails
	 */
	public TrailListPane(TrailList list) {
		super();
		trailList = list;
		trailList.addObserver(this);
		ttm = new TrailTableModel(trailList.get2DArray());
		initView();
	}

	/**
	 * Initialize view.
	 */
	private void initView() {
		table = new JTable(ttm);
		for (int i = 0; i < colWidths.length; i++) {
			TableColumn col = table.getColumnModel().getColumn(i);
			col.setPreferredWidth(colWidths[i]);
		}
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		setViewportView(table);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	/**
	 * This method is called by the observed object, whenever the observed
	 * object is changed. Referenced from TrailListPane.update().
	 * 
	 * @param o
	 *            the observable object
	 * @param arg
	 *            any additional information needed about the change.
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (trailList.equals(o)) {
			// If there is a different number of rows, create a show new
			// TableModel.
			if (trailList.size() != ttm.getRowCount()) {
				ttm = new TrailTableModel(trailList.get2DArray());
				table.setModel(ttm);
			} else {
				// Otherwise, just update the values directly.
				Object[][] arr = trailList.get2DArray();
				for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < ttm.getColumnCount() + 1; j++) {
						ttm.setValueAt(arr[i][j], i, j);
					}
				}
			}
		}
	}

	/**
	 * Returns the TableModel.
	 * 
	 * @return the TableModel
	 */
	@Override
	public TableModel getTableModel() {
		return ttm;
	}

}
