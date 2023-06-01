/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package BookAppointment;
import Dashboard.*;
import ManageAppointment.*;
import MedInfo.*;
import javax.swing.JFrame;
import Register.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;


/**
 *
 * @author truonghuy
 */
public class Booking extends JFrame {

    /**
     * Creates new form Booking
     */
    public Booking() {
        initComponents();
        getPatientInfo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    String name, dateOfBirth, gender, address, phoneNum, status = "Incoming";
    
    String selectedReason, selectedDepartment, selectedDate, selectedTime, symptomDeclaration, doctorName;
    
    String availableDepartment, availableTime, availableDate;
    
    public int appointmentID, doctorID, timeslotID;
    public static int userID;
    
    private boolean shouldPerformAction = true;
    
    public void getPatientInfo(){
        userID = Login.userid;
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\test.sqlite");
            PreparedStatement stmt = conn.prepareStatement("SELECT Name, DOB, Gender, PhoneNumber, Address FROM SignUp WHERE UserID = ?");
            stmt.setInt(1, userID);
            ResultSet resultSet = stmt.executeQuery();
       
            while (resultSet.next()) {
                name = resultSet.getString("Name");
                dateOfBirth = resultSet.getString("DOB");
                gender = resultSet.getString("Gender");
                phoneNum = resultSet.getString("PhoneNumber");
                address = resultSet.getString("Address");
                
                nameTextField.setText(name);
                dobTextField.setText(dateOfBirth);
                genderTextField.setText(gender);
                phoneNumTextField.setText(phoneNum);
                addressTextField.setText(address);
            }
            resultSet.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void getAvailableDepartment(){
        ResultSet resultSet = null;
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\test.sqlite");
            String sql = "SELECT DISTINCT Department FROM Timeslot WHERE Available = 1";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            
            departmentComboBox.removeAllItems();
            while(resultSet.next()){
                availableDepartment = resultSet.getString("Department");
                departmentComboBox.addItem(availableDepartment);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void getAvailableDate(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\test.sqlite");
            PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT Date FROM Timeslot WHERE Department = ? AND Available = 1");
            stmt.setString(1, selectedDepartment);
            ResultSet resultSet = stmt.executeQuery();
            
            shouldPerformAction = false;
            dateComboBox.removeAllItems();
            // Add dates with available timeslots as new items in date combo box
            while (resultSet.next()) {
                availableDate = resultSet.getString("Date");
                dateComboBox.addItem(availableDate);
            }
            shouldPerformAction = true;
            
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getAvailableTime(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\test.sqlite");
            PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT TimeslotID, DoctorID, Time FROM Timeslot WHERE Date = ? AND Department = ? AND Available = 1");
            stmt.setString(1, selectedDate);
            stmt.setString(2, selectedDepartment);
            ResultSet resultSet = stmt.executeQuery();
            
            shouldPerformAction = false;
            timeComboBox.removeAllItems();
            while (resultSet.next()) {
                availableTime = resultSet.getString("Time");
                timeComboBox.addItem(availableTime);
            }
            shouldPerformAction = true;
            
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getDoctorID(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\test.sqlite");
            PreparedStatement stmt = conn.prepareStatement("SELECT DoctorID FROM Timeslot WHERE Date = ? AND Time = ? AND Department = ?");
            stmt.setString(1, selectedDate);
            stmt.setString(2, selectedTime);
            stmt.setString(3, selectedDepartment);
            ResultSet resultSet = stmt.executeQuery();
          
            while (resultSet.next()) {
                doctorID = resultSet.getInt("DoctorID");
            }
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getDoctorName(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\test.sqlite");
            PreparedStatement stmt = conn.prepareStatement("SELECT DoctorName FROM Doctor WHERE DoctorID = ?");
            stmt.setInt(1, doctorID);
            ResultSet resultSet = stmt.executeQuery();
          
            while (resultSet.next()) {
                doctorName = resultSet.getString("DoctorName");
            }
            
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void getAppointmentId(){
        ResultSet resultSet = null;
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\test.sqlite");
            String sql = "SELECT max(AppointmentID) FROM Appointment";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            
            while(resultSet.next()){
                appointmentID = resultSet.getInt(1);
                appointmentID++;
            }
            resultSet.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    boolean validation(){
        
        if(selectedReason.equals("")){
            JOptionPane.showMessageDialog(this, "please fill all field");
            return false;
        }
        
        if(selectedDepartment.equals("")){
            JOptionPane.showMessageDialog(this, "please fill all field");
            return false;
        }
        
        if(selectedDate.equals("")){
            JOptionPane.showMessageDialog(this, "please fill all field");
            return false;
        }
        
        if(selectedTime.equals("")){
            JOptionPane.showMessageDialog(this, "please fill all field");
            return false;
        }
        
        if(symptomDeclaration.equals("")){
            JOptionPane.showMessageDialog(this, "please fill all field");
            return false;
        }
        return true;
    }
    
    public void insertAppointmentDetails(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\test.sqlite");
            String sql = "INSERT INTO Appointment (AppointmentID, UserID, DoctorID, DoctorName, Date, Time, Department, Reason, Symptom, Status) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, appointmentID);
            stmt.setInt(2, userID);
            stmt.setInt(3, doctorID);
            stmt.setString(4, doctorName);
            stmt.setString(5, selectedDate);
            stmt.setString(6, selectedTime);
            stmt.setString(7, selectedDepartment);
            stmt.setString(8, selectedReason);
            stmt.setString(9, symptomDeclaration);
            stmt.setString(10, status);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void insertTimseslotStatus(){
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\test.sqlite");
        String sql = "UPDATE Timeslot SET Available = 0 WHERE DoctorID = ? AND Date = ? AND Time = ? ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, doctorID);
        stmt.setString(2, selectedDate);
        stmt.setString(3, selectedTime);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    } catch(Exception e){
        e.printStackTrace();
    }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sideBar = new javax.swing.JPanel();
        homeButton = new javax.swing.JButton();
        viewStatusButton = new javax.swing.JButton();
        medicalRecordsButton = new javax.swing.JButton();
        panelParent = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        dateOfBirthLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        phoneNumLabel = new javax.swing.JLabel();
        patienInformationLabel = new javax.swing.JLabel();
        appointmentInformationLabel = new javax.swing.JLabel();
        departmentLabel = new javax.swing.JLabel();
        reasonLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        phoneNumTextField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        departmentComboBox = new javax.swing.JComboBox<>();
        reasonComboBox = new javax.swing.JComboBox<>();
        dateComboBox = new javax.swing.JComboBox<>();
        symptomsDecLabel = new javax.swing.JLabel();
        timeComboBox = new javax.swing.JComboBox<>();
        symptomsDecTextArea = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        dobTextField = new javax.swing.JTextField();
        genderTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sideBar.setBackground(new java.awt.Color(39, 123, 192));
        sideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        homeButton.setBackground(new java.awt.Color(0, 129, 201));
        homeButton.setFont(new java.awt.Font("Cambria", 1, 17)); // NOI18N
        homeButton.setForeground(new java.awt.Color(255, 255, 255));
        homeButton.setText("Home");
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });
        sideBar.add(homeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 160, 70));

        viewStatusButton.setBackground(new java.awt.Color(0, 129, 201));
        viewStatusButton.setFont(new java.awt.Font("Cambria", 1, 17)); // NOI18N
        viewStatusButton.setForeground(new java.awt.Color(255, 255, 255));
        viewStatusButton.setText("Manage ");
        viewStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewStatusButtonActionPerformed(evt);
            }
        });
        sideBar.add(viewStatusButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 160, 70));

        medicalRecordsButton.setBackground(new java.awt.Color(0, 129, 201));
        medicalRecordsButton.setFont(new java.awt.Font("Cambria", 1, 17)); // NOI18N
        medicalRecordsButton.setForeground(new java.awt.Color(255, 255, 255));
        medicalRecordsButton.setText("Medical Record");
        medicalRecordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicalRecordsButtonActionPerformed(evt);
            }
        });
        sideBar.add(medicalRecordsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 160, 70));

        getContentPane().add(sideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 600));

        panelParent.setBackground(new java.awt.Color(174, 226, 255));
        panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        timeLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        timeLabel.setText("Time:");
        panelParent.add(timeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        dateOfBirthLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        dateOfBirthLabel.setText("Date of birth:");
        panelParent.add(dateOfBirthLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        nameLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        nameLabel.setText("Name:");
        panelParent.add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        addressLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        addressLabel.setText("Address:");
        panelParent.add(addressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        genderLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        genderLabel.setText("Gender:");
        panelParent.add(genderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        phoneNumLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        phoneNumLabel.setText("Phone number:");
        panelParent.add(phoneNumLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        patienInformationLabel.setFont(new java.awt.Font("Cambria", 1, 17)); // NOI18N
        patienInformationLabel.setText("Patient Information");
        panelParent.add(patienInformationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        appointmentInformationLabel.setFont(new java.awt.Font("Cambria", 1, 17)); // NOI18N
        appointmentInformationLabel.setText("Appointment Information");
        panelParent.add(appointmentInformationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        departmentLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        departmentLabel.setText("Department:");
        panelParent.add(departmentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        reasonLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        reasonLabel.setText("Reason:");
        panelParent.add(reasonLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        dateLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        dateLabel.setText("Date:");
        panelParent.add(dateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        nameTextField.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        panelParent.add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 190, -1));

        phoneNumTextField.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        panelParent.add(phoneNumTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 160, -1));

        addressTextField.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        panelParent.add(addressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 340, -1));

        departmentComboBox.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        getAvailableDepartment();
        departmentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentComboBoxActionPerformed(evt);
            }
        });
        panelParent.add(departmentComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 300, -1));

        reasonComboBox.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        reasonComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Treatment (medication management, therapies, or procedures)", "Preventive care (routine check-ups, vaccinations, or screenings)", "Diagnostic tests (blood tests, imaging studies, etc.)", "Health maintenance (physical exams, lifestyle counseling, or chronic disease management)", "Mental health counseling (therapy, support, or medication management for mental health concerns)" }));
        reasonComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reasonComboBoxActionPerformed(evt);
            }
        });
        panelParent.add(reasonComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 420, -1));

        dateComboBox.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        dateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxActionPerformed(evt);
            }
        });
        panelParent.add(dateComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 150, -1));

        symptomsDecLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        symptomsDecLabel.setText("Symptoms Declaration:");
        panelParent.add(symptomsDecLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        timeComboBox.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        timeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeComboBoxActionPerformed(evt);
            }
        });
        panelParent.add(timeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 150, -1));

        symptomsDecTextArea.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        symptomsDecTextArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                symptomsDecTextAreaActionPerformed(evt);
            }
        });
        panelParent.add(symptomsDecTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 300, 150));

        submitButton.setBackground(new java.awt.Color(39, 123, 192));
        submitButton.setFont(new java.awt.Font("Cambria", 0, 17)); // NOI18N
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        panelParent.add(submitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 550, 140, 40));

        dobTextField.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        panelParent.add(dobTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 140, -1));

        genderTextField.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        panelParent.add(genderTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 90, -1));

        getContentPane().add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 600, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        Home home = new Home();
        home.show();
        this.dispose();
        
    }//GEN-LAST:event_homeButtonActionPerformed

    private void reasonComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reasonComboBoxActionPerformed
        // TODO add your handling code here:
        JComboBox<String> comboBox = (JComboBox<String>) evt.getSource();
        selectedReason = comboBox.getSelectedItem().toString();
    }//GEN-LAST:event_reasonComboBoxActionPerformed

    private void departmentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentComboBoxActionPerformed
        // TODO add your handling code here:
        JComboBox<String> comboBox = (JComboBox<String>) evt.getSource();
        selectedDepartment = comboBox.getSelectedItem().toString();
        getAvailableDate();
    }//GEN-LAST:event_departmentComboBoxActionPerformed

    private void dateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxActionPerformed
        // TODO add your handling code here:
        if (shouldPerformAction) {
            JComboBox<String> comboBox = (JComboBox<String>) evt.getSource();
            selectedDate = comboBox.getSelectedItem().toString();
            getAvailableTime();
        }
    }//GEN-LAST:event_dateComboBoxActionPerformed

    private void timeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeComboBoxActionPerformed
        // TODO add your handling code here:
        if (shouldPerformAction){
            JComboBox<String> comboBox = (JComboBox<String>) evt.getSource();
            selectedTime = comboBox.getSelectedItem().toString();
        }
    }//GEN-LAST:event_timeComboBoxActionPerformed

    private void symptomsDecTextAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_symptomsDecTextAreaActionPerformed
        // TODO add your handling code here:
        symptomDeclaration = symptomsDecTextArea.getText();
    }//GEN-LAST:event_symptomsDecTextAreaActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        if(validation()){
            getAppointmentId();
            getDoctorID();
            getDoctorName();
            insertAppointmentDetails();
            insertTimseslotStatus();
            super.dispose();
            BookingSuccessful successful = new BookingSuccessful();
            successful.setVisible(true);
            
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void viewStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewStatusButtonActionPerformed
        // TODO add your handling code here:
        Manage mana = new Manage();
        mana.show();
        this.dispose();
    }//GEN-LAST:event_viewStatusButtonActionPerformed

    private void medicalRecordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicalRecordsButtonActionPerformed
        // TODO add your handling code here:
        MedReports med = new MedReports();
        med.show();
        this.dispose();
        
    }//GEN-LAST:event_medicalRecordsButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Booking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JLabel appointmentInformationLabel;
    private javax.swing.JComboBox<String> dateComboBox;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dateOfBirthLabel;
    private javax.swing.JComboBox<String> departmentComboBox;
    private javax.swing.JLabel departmentLabel;
    private javax.swing.JTextField dobTextField;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JTextField genderTextField;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton medicalRecordsButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JPanel panelParent;
    private javax.swing.JLabel patienInformationLabel;
    private javax.swing.JLabel phoneNumLabel;
    private javax.swing.JTextField phoneNumTextField;
    private javax.swing.JComboBox<String> reasonComboBox;
    private javax.swing.JLabel reasonLabel;
    private javax.swing.JPanel sideBar;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel symptomsDecLabel;
    private javax.swing.JTextField symptomsDecTextArea;
    private javax.swing.JComboBox<String> timeComboBox;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JButton viewStatusButton;
    // End of variables declaration//GEN-END:variables
}
