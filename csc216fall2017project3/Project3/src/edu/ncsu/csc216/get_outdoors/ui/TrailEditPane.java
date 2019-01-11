package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.model.ActivityList;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * Panel for editing a TrailList.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 *
 */
public class TrailEditPane extends EditPane implements Observer {
	/** Default Serialization ID */
	private static final long serialVersionUID = 1L;
	/** The list of activities for this trail */
	private ActivityList activityList;
	/** The textField for a trail's ID */
	private JTextField txtTrailID;
	/** The textField for a trail's name */
	private JTextField txtTrailName;
	/** The textField for the a trail's snow amount */
	private JTextField txtSnow;
	/** The textField for the a trail's distance amount */
	private JTextField txtDistance;
	/** The checkBox for whether a trail is closed for maintenance */
	private JCheckBox tcClosedForMaintenance;
	/** The comboBox for the a trail's difficulty levels */
	private JComboBox<Difficulty> tcDiffculty;
	/**
	 * An array of checkBoxes that contain a checkBox for each activity in the
	 * trail
	 */
	private JCheckBox[] tcActivities;
	/** The trail data */
	private TrailData data;
	/** The Panel for the array of checkBoxes */
	private JPanel boxes;

	/**
	 * Creates a new edit pane with a given ActivityList.
	 * 
	 * @param list
	 *            data information to populate the pane with
	 */
	public TrailEditPane(ActivityList list) {
		this(null, list);
	}

	/**
	 * Creates a new edit pane with given TrailData and an ActivityList.
	 * 
	 * @param data
	 *            information to populate the pane with
	 * @param list
	 *            to populate the pane
	 */
	public TrailEditPane(TrailData data, ActivityList list) {
		super();
		this.data = data;
		activityList = list;
		activityList.addObserver(this);
		tcActivities = new JCheckBox[activityList.size()];
		for (int i = 0; i < activityList.size(); i++) {
			tcActivities[i] = new JCheckBox(activityList.getActivityAt(i).getName());
			tcActivities[i].setName(activityList.getActivityAt(i).getName());
			tcActivities[i].setVisible(true);
		}
		init();
		disableEdit();
	}

	/**
	 * Returns activitylist field.
	 * 
	 * @return activityList to return
	 */
	ActivityList getActivityList() {
		return activityList;
	}

	/**
	 * Returns txtTrailID field.
	 * 
	 * @return txtTrailID to return
	 */
	JTextField getTxtTrailID() {
		if (txtTrailID == null) {
			txtTrailID = new JTextField();
			txtTrailID.setColumns(5);
			txtTrailID.setEditable(false);
			txtTrailID.setVisible(true);
			txtTrailID.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return txtTrailID;
	}

	/**
	 * Returns the TxtTrailName.
	 * 
	 * @return the txtTrailName
	 */
	JTextField getTxtTrailName() {
		if (null == txtTrailName) {
			txtTrailName = new JTextField();
			txtTrailName.setColumns(20);
			txtTrailName.setEditable(false);
			txtTrailName.setVisible(true);
			txtTrailName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return txtTrailName;
	}

	/**
	 * Returns the txtSnow field.
	 * 
	 * @return the txtSnow to return
	 */
	JTextField getTxtSnow() {
		if (null == txtSnow) {
			txtSnow = new JTextField();
			txtSnow.setColumns(5);
			txtSnow.setEditable(false);
			txtSnow.setVisible(true);
			txtSnow.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return txtSnow;
	}

	/**
	 * Returns the txtDistance field.
	 * 
	 * @return the txtDistance of trail
	 */
	JTextField getTxtDistance() {
		if (null == txtDistance) {
			txtDistance = new JTextField();
			txtDistance.setColumns(5);
			txtDistance.setEditable(false);
			txtDistance.setVisible(true);
			txtDistance.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return txtDistance;
	}

	/**
	 * Returns tcClosedForMaintenance field.
	 * 
	 * @return the tcClosedForMaintenance of Trail
	 */
	JCheckBox gettcClosedForMaintenance() {
		if (null == tcClosedForMaintenance) {
			tcClosedForMaintenance = new JCheckBox();
			tcClosedForMaintenance.setVisible(true);
		}
		return tcClosedForMaintenance;
	}

	/**
	 * Returns tcDifficulty field.
	 * 
	 * @return the tcDiffculty of Trail
	 */
	JComboBox<Difficulty> getTcDiffculty() {
		if (null == tcDiffculty) {
			tcDiffculty = new JComboBox<Difficulty>(Difficulty.values());
			tcClosedForMaintenance.setVisible(true);
		}
		return tcDiffculty;
	}

	/**
	 * Returns tcActivities field.
	 * 
	 * @return the tcActivities of Trail
	 */
	JCheckBox[] getTcActivities() {

		return tcActivities;
	}

	/**
	 * Initializes view.
	 */
	@Override
	protected void initView() {

		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Trail ID: "));
		p.add(this.getTxtTrailID());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Trail Name: "));
		p.add(this.getTxtTrailName());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Closed for Maintenance? "));
		p.add(this.gettcClosedForMaintenance());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Snow: ", SwingConstants.LEFT));
		p.add(this.getTxtSnow());
		p.add(new JLabel("Distance: "));
		p.add(this.getTxtDistance());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Difficulty "));
		p.add(this.getTcDiffculty());
		this.add(p);
		boxes = new JPanel(new FlowLayout(FlowLayout.LEADING));
		boxes.add(new JLabel("Activities: "));
		for (JCheckBox cb : tcActivities) {
			boxes.add(cb);
		}
		this.add(boxes);

	}

	/**
	 * Sets the data with the passed in data.
	 * 
	 * @param d
	 *            data to populate with
	 */
	@Override
	void setData(Data d) {
		data = (TrailData) d;
		fillFields();

	}

	/**
	 * Enables the edit view.
	 * 
	 * @param d
	 *            data to populate with
	 */
	@Override
	void enableEdit(Data d) {
		if (!edit) {
			edit = true;
			data = (TrailData) d;
			fillFields();
		}

	}

	/**
	 * Clears the field.
	 */
	@Override
	void clearFields() {
		data = null;
		fillFields();

	}

	/**
	 * Adds the field listener and takes in document listener object.
	 * 
	 * @param l
	 *            DocumentListener variable
	 */
	@Override
	void addFieldListener(DocumentListener l) {
		getTxtTrailName().getDocument().addDocumentListener(l);
		getTxtSnow().getDocument().addDocumentListener(l);
		getTxtDistance().getDocument().addDocumentListener(l);

	}

	/**
	 * Fills in the fields.
	 */
	@Override
	void fillFields() {
		if (data == null) {
			txtTrailID.setText("");
			txtTrailName.setText("");
			txtSnow.setText("");
			txtDistance.setText("");
			tcClosedForMaintenance.setSelected(false);
			tcDiffculty.setSelectedIndex(-1);
			for (JCheckBox cb : tcActivities) {

				cb.setSelected(false);
			}

			txtTrailID.setEditable(false);
			txtTrailName.setEditable(false);
			txtSnow.setEditable(false);
			txtDistance.setEditable(false);
			tcClosedForMaintenance.setEnabled(false);
			tcDiffculty.setEnabled(false);
			for (JCheckBox cb : tcActivities) {
				cb.setEnabled(false);
			}

		} else {
			txtTrailID.setText(data.getTrailID());
			txtTrailName.setText(data.getTrailName());
			txtSnow.setText("" + data.getSnow());
			txtDistance.setText("" + data.getDistance());
			tcClosedForMaintenance.setSelected(data.isClosedForMaintenance());
			tcDiffculty.setSelectedItem(data.getDifficulty());
			for (int i = 0; i < tcActivities.length; i++) {
				for (int j = 0; j < data.getActivities().size(); j++) {
					if (data.getActivities().get(j).getName().equals(tcActivities[i].getName())) {
						tcActivities[i].setSelected(true);
						break;
					}
					tcActivities[i].setSelected(false);

				}

			}
		}
		if (add) {
			txtTrailName.setEditable(true);
			tcDiffculty.setEnabled(true);
		}
		if (edit || add) {
			txtSnow.setEditable(true);
			txtDistance.setEditable(true);
			tcClosedForMaintenance.setEnabled(true);
			for (JCheckBox cb : tcActivities) {
				cb.setEnabled(true);
			}

		}

	}

	/**
	 * Returns true if the field is not empty.
	 * 
	 * @return true if field is not empty
	 */
	@Override
	boolean fieldsNotEmpty() {
		return getTxtTrailName().getDocument().getLength() != 0 && getTxtSnow().getDocument().getLength() != 0
				&& getTxtDistance().getDocument().getLength() != 0;
	}

	/**
	 * Returns the field.
	 * 
	 * @return the TrailData based on what is written or selected on the GUI
	 */
	@Override
	Data getFields() {
		return new TrailData(this.getTxtTrailID().getText(), this.getTxtTrailName().getText(),
				this.tcClosedForMaintenance.isSelected(), Double.parseDouble(this.getTxtSnow().getText()),
				Double.parseDouble(this.getTxtDistance().getText()),
				(Difficulty) this.getTcDiffculty().getSelectedItem(), this.getSelectedActivities());
	}

	/**
	 * Returns the selected Activities.
	 * 
	 * @return list the list of selected activities
	 */
	private SortedArrayList<Activity> getSelectedActivities() {
		SortedArrayList<Activity> list = new SortedArrayList<Activity>();

		for (int i = 0; i < tcActivities.length; i++) {
			if (tcActivities[i].isSelected()) {
				list.add(activityList.getActivityAt(i));
			}

		}

		return list;

	}

	/**
	 * Updates the array of checkBoxes for when a new Activity is added to the
	 * program
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (activityList.equals(o)) {
			boxes.removeAll();
			tcActivities = new JCheckBox[activityList.size()];
			for (int i = 0; i < activityList.size(); i++) {
				tcActivities[i] = new JCheckBox(activityList.getActivityAt(i).getName());
				tcActivities[i].setName(activityList.getActivityAt(i).getName());
				tcActivities[i].setVisible(true);
			}
			for (JCheckBox cb : tcActivities) {
				boxes.add(cb);
			}

		}

	}

}
