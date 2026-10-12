import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PeopleMenu implements ActionListener {
  public JPanel mainPanel;
  public ArrayList<Person> people;
  public JTable table;
  public String[] cols = { "ID", "Name", "Birth Year", "Email" };
  public DefaultTableModel tableModel;
  public JButton addButton;
  public JButton delButton;
  public JButton updateButton;
  public JTextField idField;
  public JTextField nameField;
  public JTextField ageField;
  public JTextField emailField;

  public void main(String[] args) {
    JFrame frame = new JFrame();
    PeopleMenu list = new PeopleMenu();
    frame.setSize(400, 400);
    frame.add(list.mainPanel);

    frame.setVisible(true);
  }

  public PeopleMenu() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

    // ID input field
    idField = new JTextField("", 10);
    JLabel idLabel = new JLabel("ID:");
    JPanel idRow = new JPanel(new FlowLayout(0));
    idRow.setAlignmentX(Component.LEFT_ALIGNMENT);
    idRow.add(idLabel);
    idRow.add(idField);
    mainPanel.add(idRow);

    // name input field
    nameField = new JTextField("", 10);
    JLabel nameLabel = new JLabel("Name:");
    JPanel nameRow = new JPanel(new FlowLayout(0));
    nameRow.setAlignmentX(Component.LEFT_ALIGNMENT);
    nameRow.add(nameLabel);
    nameRow.add(nameField);
    mainPanel.add(nameRow);

    // age input field
    ageField = new JTextField("", 10);
    JLabel ageLabel = new JLabel("Age:");
    JPanel ageRow = new JPanel(new FlowLayout(0));
    ageRow.setAlignmentX(Component.LEFT_ALIGNMENT);
    ageRow.add(ageLabel);
    ageRow.add(ageField);
    mainPanel.add(ageRow);

    // email input field
    emailField = new JTextField("", 10);
    JLabel emailLabel = new JLabel("Email:");
    JPanel emailRow = new JPanel(new FlowLayout(0));
    emailRow.setAlignmentX(Component.LEFT_ALIGNMENT);
    emailRow.add(emailLabel);
    emailRow.add(emailField);
    mainPanel.add(emailRow);

    // ADD button
    addButton = new JButton("ADD");
    addButton.addActionListener(this);
    mainPanel.add(addButton);

    // DEL button
    delButton = new JButton("DELETE");
    delButton.addActionListener(this);
    mainPanel.add(delButton);

    // UPDATE button
    updateButton = new JButton("UPDATE");
    updateButton.addActionListener(this);
    mainPanel.add(updateButton);

    // person table
    people = new ArrayList<Person>();
    people.add(new Person(0, "Osman", 22, "osmanmedoff@student.upi.edu"));
    Object[][] data = { people.get(0).asObj() };
    tableModel = new DefaultTableModel(data, cols);
    table = new JTable(tableModel);
    JScrollPane pane = new JScrollPane(table);
    pane.setMaximumSize(new Dimension(300, 40));
    pane.setAlignmentX(Component.LEFT_ALIGNMENT);
    mainPanel.add(pane);
  }

  // https://stackoverflow.com/a/576713
  static public void setTableHeight(JTable table, int rows) {
    int width = table.getPreferredSize().width;
    int height = rows * table.getRowHeight();
    table.setPreferredScrollableViewportSize(new Dimension(width, height));
  }

  // updates table based on people list
  public void updateTable() {
    Object[][] data = new Object[people.size()][];
    for (int p = 0; p < people.size(); p++) {
      data[p] = people.get(p).asObj();
    }
    tableModel = new DefaultTableModel(data, cols);
    table.setModel(tableModel);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == addButton) {
      // add new person
      people.add(
          new Person(Integer.parseInt(idField.getText()), nameField.getText(), Integer.parseInt(ageField.getText()),
              emailField.getText()));
      updateTable();
      JOptionPane.showMessageDialog(null, "A new soul has been claimed by the mortal realm...");
    } else if (e.getSource() == delButton) {
      JOptionPane.showMessageDialog(null, "Banish thee unto thy land of despair?");

      // find and delete the person
      boolean found = false;
      for (int p = 0; p < people.size(); p++) {
        if (people.get(p).id == Integer.parseInt(idField.getText())) {
          people.remove(p);
          found = true;
          break;
        }
      }

      if (found) {
        // update table if person was found and deleted
        updateTable();
        JOptionPane.showMessageDialog(null, "Begone, fool!");
      } else {
        // person was not found
        JOptionPane.showMessageDialog(null, "Thee does not exist, sire!");
      }
    } else if (e.getSource() == updateButton) {
      // find person to update
      boolean found = false;
      int idx = 0;
      for (int p = 0; p < people.size(); p++) {
        if (people.get(p).id == Integer.parseInt(idField.getText())) {
          found = true;
          idx = p;
          break;
        }
      }

      if (found) {
        // update person
        people.set(idx, new Person(Integer.parseInt(idField.getText()), nameField.getText(),
            Integer.parseInt(ageField.getText()), emailField.getText()));
        updateTable();
        JOptionPane.showMessageDialog(null, "An old face has been turned anew!");
      } else {
        // person was not found
        JOptionPane.showMessageDialog(null, "Nonsense! Who is this you speak of?");
      }
    }
  }
}
