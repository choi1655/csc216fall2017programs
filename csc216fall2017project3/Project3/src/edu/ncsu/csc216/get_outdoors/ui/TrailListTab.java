package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import edu.ncsu.csc216.get_outdoors.model.ActivityList;
import edu.ncsu.csc216.get_outdoors.model.Trail;
import edu.ncsu.csc216.get_outdoors.model.TrailList;

/**
 * Represents the TrailList Tab in the the Get Outdoors! application.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 * @version 11242017
 *
 */
public class TrailListTab extends Tab {

	/** Serial id */
	private static final long serialVersionUID = 1L;
	/** The list of trails */
	private TrailList trails;

	/**
	 * Constructs the TrailTab with the given TrailList and ActivityList. Sets
	 * the Edit and List Pane's for this Tab.
	 * 
	 * @param trails
	 *            to display
	 * @param activities
	 *            to populate data with
	 */
	public TrailListTab(TrailList trails, ActivityList activities) {
		super();
		this.trails = trails;

		this.setListPane(new TrailListPane(this.trails));

		this.getListPane().getTable().getSelectionModel().addListSelectionListener(this);

		this.setEditPane(new TrailEditPane(activities));
		this.getEditPane().addFieldListener(this);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(this.getListPane());
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(this.getEditPane());
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(this.getButtons());

	}

	/**
	 * Called when the user selects the option to delete a selected item. If
	 * there is no item selected, a pop-up is displayed. If an item is selected,
	 * the item is removed from the List, and the remaining fields are reset to
	 * default (non-editing) states.
	 */
	@Override
	public void delete() {
		int idx = getListPane().getTable().getSelectedRow();
		if (idx == -1) {
			JOptionPane.showMessageDialog(this, "No Item is Selected", "Trail Error", JOptionPane.ERROR_MESSAGE);
		} else {
			trails.removeTrail(idx);
			getListPane().clearSelection();
			enableSave(false);
			getEditPane().disableEdit();
		}

	}

	/**
	 * Method inherited from ActionListener. If there is an add, save, or cancel
	 * action (which correspond to the three buttons), this method is called.
	 * The model is updated (or not) depending on the action. Referenced from
	 * TrailTab.actionPerformed() and ActivityTab.actionPerformed().
	 * 
	 * @param e
	 *            ActionEvent that represents the user's interaction with the
	 *            GUI.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (addMode && e.getActionCommand().equals("add")) {
			try {
				TrailData d = (TrailData) getEditPane().getFields();
				if (d.getTrailName() == null || d.getTrailName().trim().equals("")) {

					JOptionPane.showMessageDialog(this, "Trail name must be non-whitespace.", "Trail Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (d.getDistance() < 0) {

					JOptionPane.showMessageDialog(this, "Trail distance must be a postive number.", "Trail Error",
							JOptionPane.ERROR_MESSAGE);
				}
				getEditPane().setData(d);
				trails.addTrail(d.getTrailName(), d.getActivities(), d.isClosedForMaintenance(), d.getSnow(),
						d.getDistance(), d.getDifficulty());
				enableAdd(false);
				getEditPane().disableAdd();
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "Trail distance and snow must be doubles.", "Trail Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, "Trail name must be unique.", "Trail Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (editMode && e.getActionCommand().equals("save")) {
			try {
				TrailData d = (TrailData) getEditPane().getFields();
				if (d.getDistance() < 0) {

					JOptionPane.showMessageDialog(this, "Trail distance must be a postive number.", "Trail Error",
							JOptionPane.ERROR_MESSAGE);
				}
				getEditPane().setData(d);
				Trail tl = trails.getTrailAt(trails.indexOfID(d.getTrailID()));
				tl.setActivities(d.getActivities());
				tl.setDistance(d.getDistance());
				tl.setTrailMaintenance(d.isClosedForMaintenance());
				tl.setSnow(d.getSnow());
				getListPane().clearSelection();
				enableSave(false);
				getEditPane().disableEdit();

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "Trail distance and snow must be doubles.", "Trail Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getActionCommand().equals("cancel")) {
			getEditPane().clearFields();
			if (addMode) {
				enableAdd(false);
				getEditPane().disableAdd();
			}
			if (editMode) {
				getListPane().clearSelection();
				enableSave(false);
				getEditPane().disableEdit();
			}
		}

	}
}
