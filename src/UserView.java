import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.JSpinner;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 * This class contains the GUI that the user uses to interact with the database.
 * It was created using the WindowBuilder plugin to make the creation process
 * easier.
 */
public class UserView {

	private JFrame frame;

	/**
	 * The following elements exist in the "Search Users" section on the left side
	 * of the GUI.
	 */
	private JRadioButton rdbtnUserID = new JRadioButton("User ID");
	private JRadioButton rdbtnLastName = new JRadioButton("Last Name");
	private JRadioButton rdbtnUserType = new JRadioButton("User Type");
	private JTextField txtFieldSearchParameter;
	private JButton btnSearch = new JButton("Search");
	private JButton btnClearSearch = new JButton("Clear Search");
	private DefaultListModel<UserModel> userModel = new DefaultListModel();
	private JList<User> userJList = new JList(userModel);

	/**
	 * The following elements exist in the "User Information" section on the right
	 * side of the GUI.
	 */
	private JTextField txtFieldUserID;
	private JTextField txtFieldFirstName;
	private JTextField txtFieldLastName;
	private JTextField txtFieldAddress;
	private JTextField txtFieldPostalCode;
	private JTextField txtFieldPhoneNumber;
	private String[] spinnerList = { "C", "R" };
	private JSpinner spinnerUserType = new JSpinner(new SpinnerListModel(spinnerList));
	private JButton btnSave = new JButton("Save");
	private JButton btnAddNewUser = new JButton("Add New User");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnClear = new JButton("Clear");

	/**
	 * Getter method to retrieve the ID number from the 'User ID' text field.
	 * 
	 * @return - returns the ID number as a string.
	 */
	public String getID() {
		return txtFieldUserID.getText();
	}

	/**
	 * Setter method to set the ID number of the 'User ID' text field.
	 * 
	 * @param id - value that the user ID will be set to.
	 */
	public void setID(int id) {
		this.txtFieldUserID.setText(Integer.toString(id));
	}

	/**
	 * Method to clear the ID number from the 'User ID' text field.
	 */
	public void clearID() {
		this.txtFieldUserID.setText("");
	}

	/**
	 * Getter method to retrieve the first name from the 'First Name' text field.
	 * 
	 * @return - returns the first name as a string.
	 */
	public String getFirstName() {
		return txtFieldFirstName.getText();
	}

	/**
	 * Setter method to set the first name of the 'First Name' text field.
	 * 
	 * @param firstName - value that the first name will be set to.
	 */
	public void setFirstName(String firstName) {
		this.txtFieldFirstName.setText(firstName);
	}

	/**
	 * Getter method to retrieve the last name from the 'Last Name' text field.
	 * 
	 * @return - returns the last name as a string.
	 */
	public String getLastName() {
		return txtFieldLastName.getText();
	}

	/**
	 * Setter method to set the last name of the 'Last Name' text field.
	 * 
	 * @param lastName - value that the last name will be set to.
	 */
	public void setLastName(String lastName) {
		this.txtFieldLastName.setText(lastName);
	}

	/**
	 * Getter method to retrieve the address from the 'Address' text field.
	 * 
	 * @return - returns the address as a string.
	 */
	public String getAddress() {
		return txtFieldAddress.getText();
	}

	/**
	 * Setter method to set the address of the 'Address' text field.
	 * 
	 * @param address - value that the address will be set to.
	 */
	public void setAddress(String address) {
		this.txtFieldAddress.setText(address);
	}

	/**
	 * Getter method to retrieve the postal code from the 'Postal Code' text field.
	 * 
	 * @return - returns the postal code as a string.
	 */
	public String getPostalCode() {
		return txtFieldPostalCode.getText();
	}

	/**
	 * Setter method to set the postal code of the 'Postal Code' text field.
	 * 
	 * @param postalCode - value that the postal code will be set to.
	 */
	public void setPostalCode(String postalCode) {
		this.txtFieldPostalCode.setText(postalCode);
	}

	/**
	 * Getter method to retrieve the phone number from the 'Phone Number' text
	 * field.
	 * 
	 * @return - returns the phone number as a string.
	 */
	public String getPhoneNumber() {
		return txtFieldPhoneNumber.getText();
	}

	/**
	 * Setter method to set the phone number of the 'Phone Number' text field.
	 * 
	 * @param phoneNumber - value that the phone number will be set to.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.txtFieldPhoneNumber.setText(phoneNumber);
	}

	/**
	 * Getter method to retrieve the user type from the 'User Type' spinner
	 * field.
	 * 
	 * @return - returns the user type as a string.
	 */
	public String getUserType() {
		return (String) spinnerUserType.getValue();
	}

	/**
	 * Setter method to set the user type of the 'User Type' spinner field.
	 * 
	 * @param userType - value that the user type will be set to.
	 */
	public void setUserType(String userType) {
		this.spinnerUserType.setValue(userType);
	}

	/**
	 * Method to check if the 'User ID' radio button is selected.
	 * 
	 * @return - returns true if the radio button is selected, otherwise returns
	 *         false.
	 */
	public boolean checkRadioButtonUserID() {
		return rdbtnUserID.isSelected();
	}

	/**
	 * Method to check if the 'Last Name' radio button is selected.
	 * 
	 * @return - returns true if the radio button is selected, otherwise returns
	 *         false.
	 */
	public boolean checkRadioButtonLastName() {
		return rdbtnLastName.isSelected();
	}

	/**
	 * Method to check if the 'User Type' radio button is selected.
	 * 
	 * @return - returns true if the radio button is selected, otherwise returns
	 *         false.
	 */
	public boolean checkRadioButtonUserType() {
		return rdbtnUserType.isSelected();
	}

	/**
	 * Method to enable the 'Save' button on the GUI.
	 */
	public void enableSaveButton() {
		btnSave.setEnabled(true);
	}

	/**
	 * Method to disable the 'Save' button on the GUI.
	 */
	public void disableSaveButton() {
		btnSave.setEnabled(false);
	}

	/**
	 * Method to enable the 'Delete' button on the GUI.
	 */
	public void enableDeleteButton() {
		btnDelete.setEnabled(true);
	}

	/**
	 * Method to disable the 'Delete' button on the GUI.
	 */
	public void disableDeleteButton() {
		btnDelete.setEnabled(false);
	}

	/**
	 * Method to enable the 'Search' button on the GUI.
	 */
	public void enableSearchButton() {
		btnSearch.setEnabled(true);
	}

	/**
	 * Method to disable the 'Search' button on the GUI.
	 */
	public void disableSearchButton() {
		btnSearch.setEnabled(false);
	}

	/**
	 * Method to enable the 'Clear Search' button on the GUI.
	 */
	public void enableClearSearchButton() {
		btnClearSearch.setEnabled(true);
	}

	/**
	 * Method to disable the 'Clear Search' button on the GUI.
	 */
	public void disableClearSearchButton() {
		btnClearSearch.setEnabled(false);
	}

	/**
	 * Method to retrieve the string that the user has entered in the 'Search
	 * Parameter' text field.
	 * 
	 * @return - returns the string that the user wishes to search.
	 */
	public String getSearchParameter() {
		return txtFieldSearchParameter.getText();
	}

	/**
	 * Method to add users to the scroll pane that have been found in the
	 * database.
	 * 
	 * @param userArrayList - list of User objects that have been found using
	 *                        one of the search methods in the Model class.
	 */
	public void appendScrollPaneTextArea(ArrayList<UserModel> userArrayList) {
		for (int i = 0; i < userArrayList.size(); i++)
			userModel.addElement(userArrayList.get(i));
	}

	/**
	 * Method to clear the scroll pane. This method is called when the "Clear
	 * Search' button is clicked by the user.
	 */
	public void clearScrollPaneTextArea() {
		userModel.removeAllElements();
	}

	/**
	 * Method to register an action listener to the 'Search' button.
	 * 
	 * @param ac - action listener object to add to to the 'Search' button.
	 */
	public void registerSearchListener(ActionListener ac) {
		btnSearch.addActionListener(ac);
	}

	/**
	 * Method to register an action listener to the 'Clear Search' button.
	 * 
	 * @param ac - action listener object to add to to the 'Clear Search' button.
	 */
	public void registerClearSearchListener(ActionListener ac) {
		btnClearSearch.addActionListener(ac);
	}

	/**
	 * Method to register a list selection listener to the scroll pane area.
	 * 
	 * @param lsl - list selection listener object to add to to the scroll pane
	 *            area.
	 */
	public void registerSelectUserFromListListener(ListSelectionListener lsl) {
		userJList.addListSelectionListener(lsl);
	}

	/**
	 * Method to register an action listener to the 'Clear' button.
	 * 
	 * @param ac - action listener object to add to to the 'Clear' button.
	 */
	public void registerClearListener(ActionListener ac) {
		btnClear.addActionListener(ac);
	}

	/**
	 * Method to register an action listener to the 'Delete' button.
	 * 
	 * @param ac - action listener object to add to to the 'Delete' button.
	 */
	public void registerDeleteUserListener(ActionListener ac) {
		btnDelete.addActionListener(ac);
	}

	/**
	 * Method to register an action listener to the 'Add New User' button.
	 * 
	 * @param ac - action listener object to add to to the 'Add New User' button.
	 */
	public void registerAddNewUserListener(ActionListener ac) {
		btnAddNewUser.addActionListener(ac);
	}

	/**
	 * Method to register an action listener to the 'Save' button.
	 * 
	 * @param ac - action listener object to add to to the 'Save' button.
	 */
	public void registerSaveUserListener(ActionListener ac) {
		btnSave.addActionListener(ac);
	}

	/**
	 * Create the application.
	 */
	public UserView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame, including all panels, panes, buttons,
	 * text fields, lists, and layouts.
	 */
	private void initialize() {
		frame = new JFrame("User Management Screen");
		frame.setBounds(100, 100, 650, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JSplitPane splitPaneHorizontal = new JSplitPane();
		frame.getContentPane().add(splitPaneHorizontal, BorderLayout.CENTER);

		JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneHorizontal.setLeftComponent(splitPaneVertical);

		JPanel searchUsersPanel = new JPanel();
		splitPaneVertical.setTopComponent(searchUsersPanel);
		searchUsersPanel.setLayout(new BorderLayout(0, 0));

		JPanel searchUsersLabelPanel = new JPanel();
		searchUsersPanel.add(searchUsersLabelPanel, BorderLayout.NORTH);

		JLabel lblSearchUsers = new JLabel("Search Users");
		searchUsersLabelPanel.add(lblSearchUsers);

		JPanel searchUserTypePanel = new JPanel();
		searchUsersPanel.add(searchUserTypePanel, BorderLayout.CENTER);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[] { 229, 0, 0 };
		gbl_panel_8.rowHeights = new int[] { 5, 0, 0, 0 };
		searchUserTypePanel.setLayout(gbl_panel_8);

		JLabel lblSelectTypeOf = new JLabel("Select type of search to be performed:");
		GridBagConstraints gbc_lblSelectTypeOf = new GridBagConstraints();
		gbc_lblSelectTypeOf.anchor = GridBagConstraints.WEST;
		gbc_lblSelectTypeOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectTypeOf.gridx = 0;
		gbc_lblSelectTypeOf.gridy = 0;
		searchUserTypePanel.add(lblSelectTypeOf, gbc_lblSelectTypeOf);

		GridBagConstraints gbc_rdbtnUserId = new GridBagConstraints();
		gbc_rdbtnUserId.anchor = GridBagConstraints.WEST;
		gbc_rdbtnUserId.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnUserId.gridx = 0;
		gbc_rdbtnUserId.gridy = 1;
		searchUserTypePanel.add(rdbtnUserID, gbc_rdbtnUserId);

		GridBagConstraints gbc_rdbtnFirstName = new GridBagConstraints();
		gbc_rdbtnFirstName.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFirstName.gridx = 0;
		gbc_rdbtnFirstName.gridy = 2;
		searchUserTypePanel.add(rdbtnLastName, gbc_rdbtnFirstName);

		GridBagConstraints gbc_rdbtnClietnType = new GridBagConstraints();
		gbc_rdbtnClietnType.anchor = GridBagConstraints.WEST;
		gbc_rdbtnClietnType.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnClietnType.gridx = 0;
		gbc_rdbtnClietnType.gridy = 3;
		searchUserTypePanel.add(rdbtnUserType, gbc_rdbtnClietnType);

		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(rdbtnUserID);
		radioButtonGroup.add(rdbtnLastName);
		radioButtonGroup.add(rdbtnUserType);

		JPanel searchUserTypePane = new JPanel();
		searchUsersPanel.add(searchUserTypePane, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWeights = new double[] { 1.0, 0.0, 0.0 };
		gbl_panel_10.columnWidths = new int[] { 5, 0, 0 };
		gbl_panel_10.rowHeights = new int[] { 5, 0 };
		searchUserTypePane.setLayout(gbl_panel_10);

		JLabel lblEnterTheSearch = new JLabel("Enter the search parameter below:");
		GridBagConstraints gbc_lblEnterTheSearch = new GridBagConstraints();
		gbc_lblEnterTheSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterTheSearch.gridx = 0;
		gbc_lblEnterTheSearch.gridy = 0;
		searchUserTypePane.add(lblEnterTheSearch, gbc_lblEnterTheSearch);

		txtFieldSearchParameter = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 0, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 0;
		gbc_textField_6.gridy = 1;
		searchUserTypePane.add(txtFieldSearchParameter, gbc_textField_6);
		txtFieldSearchParameter.setColumns(10);

		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 1;
		searchUserTypePane.add(btnSearch, gbc_btnSearch);

		GridBagConstraints gbc_btnClearSearch = new GridBagConstraints();
		gbc_btnClearSearch.gridx = 2;
		gbc_btnClearSearch.gridy = 1;
		searchUserTypePane.add(btnClearSearch, gbc_btnClearSearch);

		JPanel searchResultsPanel = new JPanel();
		splitPaneVertical.setBottomComponent(searchResultsPanel);
		searchResultsPanel.setLayout(new BorderLayout(0, 0));

		JPanel searchResultsLabelPanel = new JPanel();
		searchResultsPanel.add(searchResultsLabelPanel, BorderLayout.NORTH);

		JLabel lblSearchResults = new JLabel("Search Results:");
		searchResultsLabelPanel.add(lblSearchResults);

		JScrollPane searchResultsScrollPane = new JScrollPane(userJList);
		searchResultsPanel.add(searchResultsScrollPane, BorderLayout.CENTER);

		JPanel userInformationPanel = new JPanel();

		splitPaneHorizontal.setRightComponent(userInformationPanel);
		userInformationPanel.setLayout(new BorderLayout(0, 0));

		JPanel userInformationLabelPanel = new JPanel();
		userInformationPanel.add(userInformationLabelPanel, BorderLayout.NORTH);

		JLabel lblUserInformation = new JLabel("User Information");
		userInformationLabelPanel.add(lblUserInformation);

		JPanel enterUserInformationPanel = new JPanel();
		userInformationPanel.add(enterUserInformationPanel, BorderLayout.CENTER);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 45, 6, 35, 49, 6, 35, 8, 6, 0, 32, 0 };
		gbl_panel_4.rowHeights = new int[] { 20, 20, 20, 20, 0, 0, 0, 0, 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		enterUserInformationPanel.setLayout(gbl_panel_4);

		JLabel lblUserId = new JLabel("User ID:");
		GridBagConstraints gbc_lblUserId = new GridBagConstraints();
		gbc_lblUserId.gridwidth = 2;
		gbc_lblUserId.anchor = GridBagConstraints.WEST;
		gbc_lblUserId.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserId.gridx = 1;
		gbc_lblUserId.gridy = 0;
		enterUserInformationPanel.add(lblUserId, gbc_lblUserId);

		txtFieldUserID = new JTextField();
		txtFieldUserID.setEditable(false);
		txtFieldUserID.setColumns(10);
		GridBagConstraints gbc_txtFieldUserID = new GridBagConstraints();
		gbc_txtFieldUserID.gridwidth = 3;
		gbc_txtFieldUserID.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldUserID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldUserID.gridx = 3;
		gbc_txtFieldUserID.gridy = 0;
		enterUserInformationPanel.add(txtFieldUserID, gbc_txtFieldUserID);

		JLabel lblFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridwidth = 2;
		gbc_lblFirstName.gridx = 1;
		gbc_lblFirstName.gridy = 1;
		enterUserInformationPanel.add(lblFirstName, gbc_lblFirstName);

		txtFieldFirstName = new JTextField(20);
		GridBagConstraints gbc_txtFieldFirstName = new GridBagConstraints();
		gbc_txtFieldFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldFirstName.anchor = GridBagConstraints.NORTH;
		gbc_txtFieldFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldFirstName.gridwidth = 3;
		gbc_txtFieldFirstName.gridx = 3;
		gbc_txtFieldFirstName.gridy = 1;
		enterUserInformationPanel.add(txtFieldFirstName, gbc_txtFieldFirstName);
		txtFieldFirstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name:");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridwidth = 2;
		gbc_lblLastName.gridx = 1;
		gbc_lblLastName.gridy = 2;
		enterUserInformationPanel.add(lblLastName, gbc_lblLastName);

		txtFieldLastName = new JTextField(20);
		GridBagConstraints gbc_txtFieldLastName = new GridBagConstraints();
		gbc_txtFieldLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldLastName.anchor = GridBagConstraints.NORTH;
		gbc_txtFieldLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldLastName.gridwidth = 3;
		gbc_txtFieldLastName.gridx = 3;
		gbc_txtFieldLastName.gridy = 2;
		enterUserInformationPanel.add(txtFieldLastName, gbc_txtFieldLastName);
		txtFieldLastName.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.WEST;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridwidth = 2;
		gbc_lblAddress.gridx = 1;
		gbc_lblAddress.gridy = 3;
		enterUserInformationPanel.add(lblAddress, gbc_lblAddress);

		txtFieldAddress = new JTextField(50);
		GridBagConstraints gbc_txtFieldAddress = new GridBagConstraints();
		gbc_txtFieldAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldAddress.anchor = GridBagConstraints.NORTH;
		gbc_txtFieldAddress.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldAddress.gridwidth = 3;
		gbc_txtFieldAddress.gridx = 3;
		gbc_txtFieldAddress.gridy = 3;
		enterUserInformationPanel.add(txtFieldAddress, gbc_txtFieldAddress);
		txtFieldAddress.setColumns(10);

		JLabel lblPostalCode = new JLabel("Postal Code:");
		GridBagConstraints gbc_lblPostalCode = new GridBagConstraints();
		gbc_lblPostalCode.anchor = GridBagConstraints.WEST;
		gbc_lblPostalCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostalCode.gridwidth = 2;
		gbc_lblPostalCode.gridx = 1;
		gbc_lblPostalCode.gridy = 4;
		enterUserInformationPanel.add(lblPostalCode, gbc_lblPostalCode);

		txtFieldPostalCode = new JTextField(7);
		GridBagConstraints gbc_txtFieldPostalCode = new GridBagConstraints();
		gbc_txtFieldPostalCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldPostalCode.anchor = GridBagConstraints.NORTH;
		gbc_txtFieldPostalCode.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldPostalCode.gridwidth = 3;
		gbc_txtFieldPostalCode.gridx = 3;
		gbc_txtFieldPostalCode.gridy = 4;
		enterUserInformationPanel.add(txtFieldPostalCode, gbc_txtFieldPostalCode);
		txtFieldPostalCode.setColumns(10);

		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
		gbc_lblPhoneNumber.anchor = GridBagConstraints.WEST;
		gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNumber.gridwidth = 2;
		gbc_lblPhoneNumber.gridx = 1;
		gbc_lblPhoneNumber.gridy = 5;
		enterUserInformationPanel.add(lblPhoneNumber, gbc_lblPhoneNumber);

		txtFieldPhoneNumber = new JTextField(12);
		GridBagConstraints gbc_txtFieldPhoneNumber = new GridBagConstraints();
		gbc_txtFieldPhoneNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldPhoneNumber.gridwidth = 3;
		gbc_txtFieldPhoneNumber.anchor = GridBagConstraints.NORTH;
		gbc_txtFieldPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldPhoneNumber.gridx = 3;
		gbc_txtFieldPhoneNumber.gridy = 5;
		enterUserInformationPanel.add(txtFieldPhoneNumber, gbc_txtFieldPhoneNumber);
		txtFieldPhoneNumber.setColumns(10);

		JLabel lblUserType = new JLabel("User Type:");
		GridBagConstraints gbc_lblUserType = new GridBagConstraints();
		gbc_lblUserType.anchor = GridBagConstraints.WEST;
		gbc_lblUserType.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserType.gridwidth = 2;
		gbc_lblUserType.gridx = 1;
		gbc_lblUserType.gridy = 6;
		enterUserInformationPanel.add(lblUserType, gbc_lblUserType);

		GridBagConstraints gbc_spinnerUserType = new GridBagConstraints();
		gbc_spinnerUserType.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerUserType.gridx = 3;
		gbc_spinnerUserType.gridy = 6;
		enterUserInformationPanel.add(spinnerUserType, gbc_spinnerUserType);

		JPanel userInformationButtonPanel = new JPanel();
		userInformationPanel.add(userInformationButtonPanel, BorderLayout.SOUTH);

		userInformationButtonPanel.add(btnAddNewUser);
		userInformationButtonPanel.add(btnSave);
		userInformationButtonPanel.add(btnDelete);
		userInformationButtonPanel.add(btnClear);
		btnSave.setEnabled(false);
		btnDelete.setEnabled(false);

		frame.pack();

		frame.setVisible(true);

	}

}
