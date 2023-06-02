/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Dashboard;

import MedInfo.*;
import ManageAppointment.*;
import BookAppointment.*;
import Register.*;
import Ratings.*;
import java.awt.*;
import javax.swing.*;
import java.util.Objects;
/**
 *
 * @author Chi Phan
 */
public class AdminHome extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public AdminHome() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        btnLogin1 = new javax.swing.JButton();
        btnLogin2 = new javax.swing.JButton();
        btnLogin3 = new javax.swing.JButton();
        btnLogin4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(174, 226, 255));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogin.setBackground(new java.awt.Color(39, 123, 192));
        btnLogin.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myicons/list_1.png"))); // NOI18N
        btnLogin.setText("<html><center>Medical Record <br> Management </center></html>");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManageAppointmentAction(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 290, 100));

        btnLogin1.setBackground(new java.awt.Color(39, 123, 192));
        btnLogin1.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnLogin1.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myicons/about.png"))); // NOI18N
        btnLogin1.setText("<html><center> View <br> Feedback </center></html>");
        btnLogin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RatingAction(evt);
            }
        });
        jPanel1.add(btnLogin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 290, 100));

        btnLogin2.setBackground(new java.awt.Color(39, 123, 192));
        btnLogin2.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnLogin2.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myicons/new-user.png"))); // NOI18N
        btnLogin2.setText("<html><center> Add<br> Timeslot </center></html>");
        btnLogin2.setToolTipText("");
        btnLogin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookingAppear(evt);
            }
        });
        jPanel1.add(btnLogin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 290, 100));

        btnLogin3.setBackground(new java.awt.Color(39, 123, 192));
        btnLogin3.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnLogin3.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin3.setText("Log out");
        btnLogin3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MedicalReportAction(evt);
            }
        });
        jPanel1.add(btnLogin3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 90, 40));

        btnLogin4.setBackground(new java.awt.Color(39, 123, 192));
        btnLogin4.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnLogin4.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myicons/view all record.png"))); // NOI18N
        btnLogin4.setText("<html><center> Appointment <br> Management </center></html>");
        btnLogin4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogin4MedicalReportAction(evt);
            }
        });
        jPanel1.add(btnLogin4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 290, 100));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 800, 400));

        jPanel2.setBackground(new java.awt.Color(39, 123, 192));
        jPanel2.setEnabled(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Bodoni MT", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("MedCare - Hospital Booking System");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 800, 140));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 200));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RatingAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RatingAction
        // TODO add your handling code here:
        Ratings2 rate = new Ratings2();
        rate.show();
        this.dispose();
    }//GEN-LAST:event_RatingAction

    private void ManageAppointmentAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageAppointmentAction
        ManageAppointment.Manage manage = new ManageAppointment.Manage();
        manage.show();
        this.dispose();
        
    }//GEN-LAST:event_ManageAppointmentAction

    private void MedicalReportAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MedicalReportAction
        Register.Login login = new Register.Login();
        login.show();
        this.dispose();
    }//GEN-LAST:event_MedicalReportAction

    private void BookingAppear(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookingAppear
        Booking book = new Booking();
        book.show();
        this.dispose();
    }//GEN-LAST:event_BookingAppear

    private void btnLogin4MedicalReportAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin4MedicalReportAction
        // TODO add your handling code here:
        MedReports med = new MedReports();
        med.show();
        this.dispose();
        
    }//GEN-LAST:event_btnLogin4MedicalReportAction

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
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogin1;
    private javax.swing.JButton btnLogin2;
    private javax.swing.JButton btnLogin3;
    private javax.swing.JButton btnLogin4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
