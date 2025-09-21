package gui;

import entity.Reservation;
import entitylist.ReservationList;
import fileio.FileIO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class RailwayReservationGUI extends JFrame {

    private JTextField nameField, nidField, mobileField, dateField, fromField, toField;
    private JComboBox<String> trainCombo, classCombo;
    private JTable table;
    private ReservationList reservationList;

    public RailwayReservationGUI() {
        setTitle("Railway Reservation System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

       
        setContentPane(new JLabel(new ImageIcon("background.jpg")));
        setLayout(null);

        
        reservationList = new ReservationList();
        FileIO.loadReservations(reservationList);

        JLabel titleLabel = new JLabel("Railway Reservation System");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(300, 10, 400, 30);
        add(titleLabel);

        int labelX = 20, fieldX = 200, y = 60, height = 25, gap = 35;

        addLabel("Passenger Name:", labelX, y);
        nameField = addTextField(fieldX, y); y += gap;

        addLabel("NID/Passport:", labelX, y);
        nidField = addTextField(fieldX, y); y += gap;

        addLabel("Mobile Number:", labelX, y);
        mobileField = addTextField(fieldX, y); y += gap;

        addLabel("Travel Date (DD-MM-YYYY):", labelX, y);
        dateField = addTextField(fieldX, y); y += gap;

        addLabel("Train:", labelX, y);
		
        trainCombo = new JComboBox<>(new String[]{
            "Maitree Express", "Cox’s Bazar Express", "Sonar Bangla Express"
        });
        trainCombo.setBounds(fieldX, y, 300, 25);
        add(trainCombo);
        y += gap;

        addLabel("Class:", labelX, y);
        classCombo = new JComboBox<>(new String[]{
            "Shovon", "Shovon Chair", "Snigdha", "AC Berth"
        });
        classCombo.setBounds(fieldX, y, 300, 25);
        add(classCombo);
        y += gap;

        addLabel("From:", labelX, y);
        fromField = addTextField(fieldX, y); y += gap;

        addLabel("To:", labelX, y);
        toField = addTextField(fieldX, y); y += gap;

        
        table = new JTable(reservationList.getTableModel());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, y, 900, 150);
        add(scrollPane);
        y += 160;

       
        JButton addBtn = createButton("Add Reservation", 0, y);
        JButton removeBtn = createButton("Remove Reservation", 200, y);
        JButton showBtn = createButton("Show All", 400, y);
        JButton clearBtn = createButton("Clear Fields", 600, y);

        add(addBtn);
        add(removeBtn);
        add(showBtn);
        add(clearBtn);

        
        addBtn.addActionListener(e -> addReservation());
        removeBtn.addActionListener(e -> removeReservation());
        showBtn.addActionListener(e -> showAllReservations());
        clearBtn.addActionListener(e -> clearFields());


        addWindowListener(new WindowAdapter() {
           
            public void windowClosing(WindowEvent e) {
                FileIO.saveReservations(reservationList);
                System.exit(0);
            }
        });
		setVisible(true);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 180, 25);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(label);
    }

    private JTextField addTextField(int x, int y) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 300, 25);
        add(field);
        return field;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 180, 30);
        return button;
    }

    private void addReservation() {
        Reservation reservation = new Reservation(
            nameField.getText(),
            nidField.getText(),
            mobileField.getText(),
            dateField.getText(),
            trainCombo.getSelectedItem().toString(),
            classCombo.getSelectedItem().toString(),
            fromField.getText(),
            toField.getText()
        );
        reservationList.addReservation(reservation);
        clearFields();
    }

    private void removeReservation() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            reservationList.removeReservation(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to remove.");
        }
    }

    private void showAllReservations() {
        JOptionPane.showMessageDialog(this, "Total Reservations: " + reservationList.getReservationCount());
    }

    private void clearFields() {
        nameField.setText("");
        nidField.setText("");
        mobileField.setText("");
        dateField.setText("");
        fromField.setText("");
        toField.setText("");
        trainCombo.setSelectedIndex(0);
        classCombo.setSelectedIndex(0);
    }
}