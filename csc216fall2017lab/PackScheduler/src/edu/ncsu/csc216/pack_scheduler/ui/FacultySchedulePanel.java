
package edu.ncsu.csc216.pack_scheduler.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;
import javax.swing.table.AbstractTableModel;

/**
 * Creates a user interface for working with the FacultySchedulePanel.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 * @author kavitpatel
 */
public class FacultySchedulePanel extends JPanel {
	/** Default serial ID */
	private static final long serialVersionUID = 1L;
	/** Table for the faculty's schedule */
	private JTable tableSchedule;
	/** Table for the course roll */
	private JTable tableRoll;
	/** The table model for the schedule */
	private CourseTableModel scheduleTableModel;
	/** The table model for the course roll */
	private StudentTableModel rollTableModel;
	/** The border */
	private Border lowerEtched;
	/** The scroll pane for the schedule table */
	private JScrollPane scrollSchedule;
	/** The scroll pane for the roll table */
	private JScrollPane scrollRoll;
	/** The panel for course details */
	private JPanel pnlCourseDetails;
	/** The label for the name title */
	private JLabel lblNameTitle = new JLabel("Name: ");
	/** The name label for the title */
	private JLabel lblSectionTitle = new JLabel("Section: ");
	/** The title label for the section */
	private JLabel lblTitleTitle = new JLabel("Title: ");
	/** The instructor label for the title */
	private JLabel lblInstructorTitle = new JLabel("Instructor: ");
	/** The credits label for the title */
	private JLabel lblCreditsTitle = new JLabel("Credits: ");
	/** The meeting label for the title */
	private JLabel lblMeetingTitle = new JLabel("Meeting: ");
	/** The label title for the enrollment cap */
	private JLabel lblEnrollmentCapTitle = new JLabel("Enrollment Cap: ");
	/** The label title for the open seats */
	private JLabel lblOpenSeatsTitle = new JLabel("Open Seats: ");
	/** The label title for the waitlist */
	private JLabel lblWaitlistTitle = new JLabel("Number on Waitlist: ");
	/** The label for the name field */
	private JLabel lblName = new JLabel("");
	/** The label for the section field */
	private JLabel lblSection = new JLabel("");
	/** The label for the title field */
	private JLabel lblTitle = new JLabel("");
	/** The label for the instructor field */
	private JLabel lblInstructor = new JLabel("");
	/** The label for the credits field */
	private JLabel lblCredits = new JLabel("");
	/** The label for the meeting field */
	private JLabel lblMeeting = new JLabel("");
	/** The label for the enrollment cap field */
	private JLabel lblEnrollmentCap = new JLabel("");
	/** The label for the open seats field */
	private JLabel lblOpenSeats = new JLabel("");
	/** The label for the waitlist field */
	private JLabel lblWaitlist = new JLabel("");
	/** The logged in faculty's schedule */
	private FacultySchedule schedule;
	/** The current user logged in */
	private Faculty currentUser;
	/** The current course */
	private Course currentCourse;

	/**
	 * Constructs the FacultySchedulePanel. Initializes the sub panels, gets the
	 * current user and manager.
	 */
	public FacultySchedulePanel() {
		super(new GridBagLayout());
		RegistrationManager manager = RegistrationManager.getInstance();
		currentUser = (Faculty) manager.getCurrentUser();
		if (currentUser != null) {
			schedule = currentUser.getSchedule();
		}

		initFacultySchedule();
		initCourseDetails();
		initCourseRoll();
		updateTables();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollSchedule, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlCourseDetails, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollRoll, c);

	}

	/**
	 * Initializes the faculty schedule panel
	 */
	private void initFacultySchedule() {

		// set up Faculty Schedule table
		scheduleTableModel = new CourseTableModel();
		tableSchedule = new JTable(scheduleTableModel) {
			private static final long serialVersionUID = 1L;

			/**
			 * Set custom tool tips for cells
			 * 
			 * @param e
			 *            MouseEvent that causes the tool tip
			 * @return tool tip text
			 */
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);

				return (String) scheduleTableModel.getValueAt(rowIndex, realColumnIndex);
			}
		};

		tableSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableSchedule.setFillsViewportHeight(true);
		tableSchedule.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			/**
			 * Checks if the value changed
			 */
			@Override
			public void valueChanged(ListSelectionEvent e) {
				currentCourse = null;
				String name = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 0).toString();
				String section = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 1).toString();
				Course c = schedule.getCourseFromSchedule(name, section);
				currentCourse = c;
				updateCourseDetails(c);
				updateTables();
			}

		});

		scrollSchedule = new JScrollPane(tableSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		TitledBorder borderSchedule = BorderFactory.createTitledBorder(lowerEtched, "Faculty Schedule");
		scrollSchedule.setBorder(borderSchedule);
		scrollSchedule.setToolTipText("Faculty Schedule");

	}

	/**
	 * Initializes the course details panel
	 */
	private void initCourseDetails() {
		// Set up Course Details panel
		pnlCourseDetails = new JPanel();
		pnlCourseDetails.setLayout(new GridLayout(6, 1));
		JPanel pnlName = new JPanel(new GridLayout(1, 4));
		pnlName.add(lblNameTitle);
		pnlName.add(lblName);
		pnlName.add(lblSectionTitle);
		pnlName.add(lblSection);

		JPanel pnlTitle = new JPanel(new GridLayout(1, 1));
		pnlTitle.add(lblTitleTitle);
		pnlTitle.add(lblTitle);

		JPanel pnlInstructor = new JPanel(new GridLayout(1, 4));
		pnlInstructor.add(lblInstructorTitle);
		pnlInstructor.add(lblInstructor);
		pnlInstructor.add(lblCreditsTitle);
		pnlInstructor.add(lblCredits);

		JPanel pnlMeeting = new JPanel(new GridLayout(1, 1));
		pnlMeeting.add(lblMeetingTitle);
		pnlMeeting.add(lblMeeting);

		JPanel pnlEnrollment = new JPanel(new GridLayout(1, 4));
		pnlEnrollment.add(lblEnrollmentCapTitle);
		pnlEnrollment.add(lblEnrollmentCap);
		pnlEnrollment.add(lblOpenSeatsTitle);
		pnlEnrollment.add(lblOpenSeats);

		JPanel pnlWaitlist = new JPanel(new GridLayout(1, 2));
		pnlWaitlist.add(lblWaitlistTitle);
		pnlWaitlist.add(lblWaitlist);

		pnlCourseDetails.add(pnlName);
		pnlCourseDetails.add(pnlTitle);
		pnlCourseDetails.add(pnlInstructor);
		pnlCourseDetails.add(pnlMeeting);
		pnlCourseDetails.add(pnlEnrollment);
		pnlCourseDetails.add(pnlWaitlist);

		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Course Details");
		pnlCourseDetails.setBorder(borderCourseDetails);
		pnlCourseDetails.setToolTipText("Course Details");

	}

	/**
	 * Initializes the course roll panel
	 */
	private void initCourseRoll() {
		// Set up Course Roll table

		rollTableModel = new StudentTableModel();
		tableRoll = new JTable(rollTableModel);

		tableRoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRoll.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableRoll.setFillsViewportHeight(true);

		scrollRoll = new JScrollPane(tableRoll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		TitledBorder borderRoll = BorderFactory.createTitledBorder(lowerEtched, "Course Roll");
		scrollRoll.setBorder(borderRoll);
		scrollRoll.setToolTipText("Course Roll");
	}

	/**
	 * Updating tables.
	 */
	public void updateTables() {
		scheduleTableModel.updateData();
		rollTableModel.updateData();
	}

	/**
	 * Updates the course details
	 * 
	 * @param c
	 *            the course to be displayed in the table
	 */
	private void updateCourseDetails(Course c) {
		if (c != null) {
			lblName.setText(c.getName());
			lblSection.setText(c.getSection());
			lblTitle.setText(c.getTitle());
			lblInstructor.setText(c.getInstructorId());
			lblCredits.setText("" + c.getCredits());
			lblMeeting.setText(c.getMeetingString());
			lblEnrollmentCap.setText("" + c.getCourseRoll().getEnrollmentCap());
			lblOpenSeats.setText("" + c.getCourseRoll().getOpenSeats());
			lblWaitlist.setText("" + c.getCourseRoll().getNumberOnWaitlist());
		}

	}

	/**
	 * CourseTableModel extending AbstractTableModle.
	 * 
	 * @author kavitpatel
	 */
	private class CourseTableModel extends AbstractTableModel {

		/**
		 * ID number used for object serialization.
		 */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String[] columnNames = { "Name", "Section", "Title", "Meeting Days", "Open Seats" };
		/** Data stored in the table */
		private Object[][] data;

		/**
		 * Constructs the {@link CourseTableModel} by requesting the latest
		 * information from the {@link RequirementTrackerModel}.
		 */
		public CourseTableModel() {
			updateData();
		}

		/**
		 * Updates the data for the course table model
		 */
		private void updateData() {

			currentUser = (Faculty) RegistrationManager.getInstance().getCurrentUser();
			if (currentUser != null) {
				schedule = currentUser.getSchedule();
				data = schedule.getScheduledCourses();

				FacultySchedulePanel.this.repaint();
				FacultySchedulePanel.this.validate();

			}

		}

		/**
		 * Gets the row count
		 * 
		 * @return the number of rows.
		 */
		@Override
		public int getRowCount() {
			if (data == null) {
				return 0;
			}
			return data.length;
		}

		/**
		 * Gets the column count
		 * 
		 * @return the number of columns.
		 */
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the column name at the given index.
		 * 
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Gets the value at the passed in row and column index.
		 * 
		 * @param rowIndex
		 *            row index of the table
		 * @param columnIndex
		 *            column index of the table
		 * @return the object at that row and index
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (data == null) {
				return null;
			}
			return data[rowIndex][columnIndex];
		}

		/**
		 * Sets the given value to the given {row, col} location.
		 * 
		 * @param value
		 *            Object to modify in the data.
		 * @param row
		 *            location to modify the data.
		 * @param column
		 *            location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

	}

	/**
	 * StudentTableModel extending AbstractTableModel.
	 * 
	 * @author kavitpatel
	 * @author jballie
	 * @author mchoi
	 */
	private class StudentTableModel extends AbstractTableModel {

		/**
		 * ID number used for object serialization.
		 */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String[] columnNames = { "First Name", "Last Name", "Student ID" };
		/** Data stored in the table */
		private Object[][] data;

		/**
		 * Constructs a studentTaleModel and updates its data.
		 */
		public StudentTableModel() {
			updateData();
		}

		/**
		 * Gets the row count of the table.
		 * 
		 * @return the number of rows.
		 */
		@Override
		public int getRowCount() {
			if (data == null) {
				return 0;
			}
			return data.length;
		}

		/**
		 * Get the column count of the table.
		 * 
		 * @return the number of columns.
		 */
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the column name at the given index.
		 * 
		 * @param col
		 *            the col index
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Gets the value at the passed in row and column index.
		 * 
		 * @param rowIndex
		 *            row index of the table
		 * @param columnIndex
		 *            column index of the table
		 * @return the object at that row and index
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (data == null) {
				return null;
			}
			return data[rowIndex][columnIndex];
		}

		/**
		 * Sets the given value to the given {row, col} location.
		 * 
		 * @param value
		 *            Object to modify in the data.
		 * @param row
		 *            location to modify the data.
		 * @param column
		 *            location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		/**
		 * Updates the data in the table model.
		 */
		private void updateData() {

			currentUser = (Faculty) RegistrationManager.getInstance().getCurrentUser();
			if (currentUser != null) {

				if (currentCourse == null) {
					data = null;
				} else {

					data = currentCourse.getCourseRoll().getEnrolledStudents();
				}

				FacultySchedulePanel.this.repaint();
				FacultySchedulePanel.this.validate();

			}
		}

	}
}
