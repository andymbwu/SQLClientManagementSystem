import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This is the class that connects the logic of the program (UserModel) to the
 * GUI (UserView). This class is also responsible for registering the action
 * listeners of the buttons and text fields in the GUI (UserView).
 */
public class UserController implements Constants {

    private UserView theView;
    private UserModel theModel;

    private UserWrapper theWrapper;
    private ObjectOutputStream objOut = null;


    public UserController(UserView theView, UserModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        /**
         * This method registers an action listener to the "Search" button in the GUI.
         * It checks to see which radio button is selected, and sends the search
         * parameter to the proper database search method in the Model class.
         */
        theView.registerSearchListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theView.disableSearchButton();
                theWrapper.setQuery(theView.getSearchParameter());
                theWrapper.setUserList(null);
                if (theView.checkRadioButtonUserID() == true) {
//					theView.appendScrollPaneTextArea("");
                    theWrapper.setAction(SEARCHUSERID);
                } else if (theView.checkRadioButtonLastName() == true) {
//                    theView.appendScrollPaneTextArea(theModel.searchUserLastName(theView.getSearchParameter()));
                    theWrapper.setAction(Constants.SEARCHLASTNAME);
                } else if (theView.checkRadioButtonUserType() == true) {
//                    theView.appendScrollPaneTextArea(theModel.searchUserType(theView.getSearchParameter()));
                    theWrapper.setAction(Constants.SEARCHUSERTYPE);
                }

                try {
                    objOut.writeObject(theWrapper);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
//                theView.appendScrollPaneTextArea()
            }
        });

        /**
         * This method registers an action listener to the "Clear Search" button in the
         * GUI. It calls a method from the View class to clear the scroll pane and then
         * disables the "Delete" and "Save" buttons. The delete and update functionality
         * is only meant to be accessible when a user is selected from the scroll
         * pane, therefore they are disabled when the scroll pane is cleared.
         */
        theView.registerClearSearchListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theView.clearScrollPaneTextArea();
                theView.enableSearchButton();
                theView.disableDeleteButton();
                theView.disableSaveButton();
            }
        });

        /**
         * This method registers a list selection listener to the scroll pane that
         * displays coient information to the user. When a search is performed and a
         * user is selected by a user click, the text fields in the User Information
         * panel on the right side of the GUI are populated with that users
         * information.
         */
        theView.registerSelectUserFromListListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    JList source = (JList) event.getSource();
                    User temp = (User) source.getSelectedValue();
                    theView.enableDeleteButton();
                    theView.enableSaveButton();
                    if (temp != null) {
                        theView.setID(temp.getID());
                        theView.setFirstName(temp.getFirstName());
                        theView.setLastName(temp.getLastName());
                        theView.setAddress(temp.getAddress());
                        theView.setPostalCode(temp.getPostalCode());
                        theView.setPhoneNumber(temp.getPhoneNumber());
                        theView.setUserType(temp.getUserType());
                    }
                }
            }

        });

        /**
         * This method registers an action listener for the "Clear" button on the right
         * side of the GUI. When the "Clear" button is clicked it sets the value of all
         * User Information text fields to empty.
         */
        theView.registerClearListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theView.clearID();
                theView.setFirstName("");
                theView.setLastName("");
                theView.setAddress("");
                theView.setPostalCode("");
                theView.setPhoneNumber("");
                theView.disableDeleteButton();
                theView.disableSaveButton();
            }
        });

        /**
         * This method registers an action listener for the "Delete" button. When
         * clicked, the user that is currently selected in the scroll pane will be
         * deleted by calling a method in the Model class to delete the user from the
         * database. If the user was successfully deleted without errors then a message
         * is displayed to the user notifying them.
         */
        theView.registerDeleteUserListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                theModel.deleteUser(Integer.parseInt(theView.getID()));
                theWrapper.setAction(DELETEUSER);
                theWrapper.setUserList(null);
                theWrapper.setQuery(theView.getID());
                try {
                    objOut.writeObject(theWrapper);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "User successfully deleted!", "Delete User",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        /**
         * This method registers an action listener for the "Add New User" button. When
         * clicked, the information that the user has entered in the User Information
         * text fields is validated (see Model class for validation methods for each
         * text field). If any information is not valid then the user is shown an
         * appropriate message and allowed correct the information and try again.
         * If the information is valid then a method from the Model class is called
         * to add a new user to the database with this validated information.
         */
        theView.registerAddNewUserListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String firstName = theView.getFirstName();
                boolean firstNameBool = theModel.checkFirstName(firstName);
                String lastName = theView.getLastName();
                boolean lastNameBool = theModel.checkLastName(lastName);
                String address = theView.getAddress();
                boolean addressBool = theModel.checkAddress(address);
                String postalCode = theView.getPostalCode();
                boolean postalCodeBool = theModel.checkPostalCode(postalCode);
                String phoneNumber = theView.getPhoneNumber();
                boolean phoneNumberBool = theModel.checkPhoneNumber(phoneNumber);
                String userType = theView.getUserType();

                if (!firstNameBool) {
                    JOptionPane.showMessageDialog(null,
                            "First name must not be blank and must be be less than 20 " + "charactersin length.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (!lastNameBool) {
                    JOptionPane.showMessageDialog(null,
                            "Last name must not be blank and must be less than 20 " + "charactersin length.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!addressBool) {
                    JOptionPane.showMessageDialog(null,
                            "Address must not be blank and must be less than 50 " + "charactersin length.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!postalCodeBool) {
                    JOptionPane.showMessageDialog(null,
                            "Postal code must not be blank and must be in format " + "A#A #A#.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!phoneNumberBool) {
                    JOptionPane.showMessageDialog(null,
                            "Phone number must not be blank and must be in format " + "### - ### - ####.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    User temp = new User(firstName, lastName, address, postalCode, phoneNumber, userType);
                    theModel.addUser(temp);
                    JOptionPane.showMessageDialog(null, "User successfully added!", "Add New User",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        /**
         * This method registers an action listener for the "Save" button. When
         * clicked, the information that the user has updated in the User Information
         * text fields for an existing user is validated (see Model class for validation
         * methods for each
         * text field). If any information is not valid then the user is shown an
         * appropriate message and allowed correct the information and try again.
         * If the information is valid then a method from the Model class is called
         * to update the users' information in the database.
         */
        theView.registerSaveUserListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = theView.getID();
                String firstName = theView.getFirstName();
                boolean firstNameBool = theModel.checkFirstName(firstName);
                String lastName = theView.getLastName();
                boolean lastNameBool = theModel.checkLastName(lastName);
                String address = theView.getAddress();
                boolean addressBool = theModel.checkAddress(address);
                String postalCode = theView.getPostalCode();
                boolean postalCodeBool = theModel.checkPostalCode(postalCode);
                String phoneNumber = theView.getPhoneNumber();
                boolean phoneNumberBool = theModel.checkPhoneNumber(phoneNumber);
                String userType = theView.getUserType();

                if (!firstNameBool) {
                    JOptionPane.showMessageDialog(null,
                            "First name must not be blank and must be be less than 20 " + "charactersin length.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (!lastNameBool) {
                    JOptionPane.showMessageDialog(null,
                            "Last name must not be blank and must be less than 20 " + "charactersin length.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!addressBool) {
                    JOptionPane.showMessageDialog(null,
                            "Address must not be blank and must be less than 50 " + "charactersin length.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!postalCodeBool) {
                    JOptionPane.showMessageDialog(null,
                            "Postal code must not be blank and must be in format " + "A#A #A#.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!phoneNumberBool) {
                    JOptionPane.showMessageDialog(null,
                            "Phone number must not be blank and must be in format " + "### - ### - ####.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    User temp = new User(Integer.parseInt(id), firstName, lastName, address, postalCode,
                            phoneNumber, userType);
                    theModel.updateExistingUser(temp);
                    JOptionPane.showMessageDialog(null, "User information updated successfully!", "Update User",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }

}
