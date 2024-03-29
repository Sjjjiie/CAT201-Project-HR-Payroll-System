/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package payroll.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class Audit extends javax.swing.JFrame {
    Connection conn = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String username;
    
    Calendar cal = new GregorianCalendar();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String date = sdf.format(cal.getTime());

    int second = cal.get(Calendar.SECOND);
    int minute = cal.get(Calendar.MINUTE);
    int hour = cal.get(Calendar.HOUR);
    DecimalFormat df = new DecimalFormat("00");
    String time = df.format(hour) + ":" + df.format(minute) + ":" + df.format(second);

    /**
     * Creates new form Audit
     */
    public Audit(){
        initComponents();conn = db.java_db();
        showAudit();
    }
    public Audit(String name) {
        this.username = name;
        
        initComponents();conn = db.java_db();
        showAudit();
    }
    
    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void showAudit() {
        try {
            String sql;
            String sort = Objects.toString(sorting_combobox.getSelectedItem());
            
            if (null == sort){
                sql = "select audit_date, audit_time, audit_type, audit_action from Audit order by audit_date, audit_time";
            }
            else sql = switch (sort) {
                case "Login & Logout" -> "select audit_date, audit_time, audit_type, audit_action from Audit WHERE audit_type = 'Login & Logout' ";
                case "Employee Management" -> "select audit_date, audit_time, audit_type, audit_action from Audit WHERE audit_type = 'Employee Management' ";
                case "Salary Calculation" -> "select audit_date, audit_time, audit_type, audit_action from Audit WHERE audit_type = 'Salary Calculation' ";
                case "Salary Slip" -> "select audit_date, audit_time, audit_type, audit_action from Audit WHERE audit_type = 'Salary Slip' ";
                default -> "select audit_date, audit_time, audit_type, audit_action from Audit order by audit_date AND audit_time";
            };
            
            st = conn.createStatement();
            rs = st.executeQuery(sql);  
            
            DefaultTableModel tblModel = (DefaultTableModel) audit_table.getModel();
            tblModel.setRowCount(0);
            
            while(rs.next()){
                String date = rs.getString("audit_date");
                String time = rs.getString("audit_time");
                String type = rs.getString("audit_type");
                String action = rs.getString("audit_action");
                
                String tbData[] = {date, time, type, action};  
                tblModel.addRow(tbData);
            }
   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            closeResources();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        greeting_label2 = new javax.swing.JLabel();
        logout_button = new javax.swing.JButton();
        back = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        audit_table = new javax.swing.JTable();
        clear_button = new javax.swing.JButton();
        sorting_combobox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        side_menu = new javax.swing.JPanel();
        calculation_button = new javax.swing.JButton();
        slip_button = new javax.swing.JButton();
        dashboard_button = new javax.swing.JButton();
        employee_button = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        hr_label = new javax.swing.JLabel();
        payroll_label1 = new javax.swing.JLabel();
        system_label = new javax.swing.JLabel();
        audit_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main_panel.setBackground(new java.awt.Color(255, 255, 255));
        main_panel.setFocusable(false);
        main_panel.setFont(new java.awt.Font("Californian FB", 0, 14)); // NOI18N

        greeting_label2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        greeting_label2.setText("Audit Trail");

        logout_button.setBackground(new java.awt.Color(250, 255, 255));
        logout_button.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        logout_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll/system/images/logout_icon.png"))); // NOI18N
        logout_button.setText("Logout");
        logout_button.setIconTextGap(12);
        logout_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_buttonActionPerformed(evt);
            }
        });

        back.setFont(new java.awt.Font("Californian FB", 1, 36)); // NOI18N
        back.setText("<");
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        audit_table.setBackground(new java.awt.Color(250, 255, 255));
        audit_table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        audit_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Time", "Type", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        audit_table.setToolTipText("");
        audit_table.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(audit_table);
        if (audit_table.getColumnModel().getColumnCount() > 0) {
            audit_table.getColumnModel().getColumn(0).setMinWidth(100);
            audit_table.getColumnModel().getColumn(0).setMaxWidth(100);
            audit_table.getColumnModel().getColumn(1).setMinWidth(100);
            audit_table.getColumnModel().getColumn(1).setMaxWidth(100);
            audit_table.getColumnModel().getColumn(3).setMinWidth(200);
        }

        clear_button.setBackground(new java.awt.Color(250, 255, 255));
        clear_button.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        clear_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll/system/images/delete_icon.png"))); // NOI18N
        clear_button.setText("Delete");
        clear_button.setIconTextGap(18);
        clear_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_buttonActionPerformed(evt);
            }
        });

        sorting_combobox.setBackground(new java.awt.Color(250, 255, 255));
        sorting_combobox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        sorting_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- All --", "Login & Logout", "Employee Management", "Salary Calculation", "Salary Slip" }));
        sorting_combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sorting_comboboxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Sort by:");

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, main_panelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sorting_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addComponent(greeting_label2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                                .addComponent(logout_button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addContainerGap(49, Short.MAX_VALUE)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(clear_button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(greeting_label2)
                        .addComponent(back))
                    .addComponent(logout_button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(clear_button, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(sorting_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side_menu.setBackground(new java.awt.Color(0, 33, 120));
        side_menu.setPreferredSize(new java.awt.Dimension(226, 550));

        calculation_button.setBackground(new java.awt.Color(0, 30, 120));
        calculation_button.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        calculation_button.setForeground(new java.awt.Color(255, 255, 255));
        calculation_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll/system/images/calculation_icon.png"))); // NOI18N
        calculation_button.setText("Salary Calculation");
        calculation_button.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        calculation_button.setIconTextGap(23);
        calculation_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculation_buttonActionPerformed(evt);
            }
        });

        slip_button.setBackground(new java.awt.Color(0, 30, 120));
        slip_button.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        slip_button.setForeground(new java.awt.Color(255, 255, 255));
        slip_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll/system/images/slip_icon.png"))); // NOI18N
        slip_button.setText("Salary Slip");
        slip_button.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        slip_button.setIconTextGap(38);
        slip_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slip_buttonActionPerformed(evt);
            }
        });

        dashboard_button.setBackground(new java.awt.Color(0, 30, 120));
        dashboard_button.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dashboard_button.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll/system/images/dashboard_icon.png"))); // NOI18N
        dashboard_button.setText("Dashboard");
        dashboard_button.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboard_button.setIconTextGap(40);
        dashboard_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard_buttonActionPerformed(evt);
            }
        });

        employee_button.setBackground(new java.awt.Color(0, 30, 120));
        employee_button.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        employee_button.setForeground(new java.awt.Color(255, 255, 255));
        employee_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll/system/images/empmanagement_icon.png"))); // NOI18N
        employee_button.setText(" Employee Management");
        employee_button.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employee_button.setIconTextGap(5);
        employee_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_buttonActionPerformed(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll/system/images/logo1.png"))); // NOI18N
        logo.setPreferredSize(new java.awt.Dimension(100, 130));

        hr_label.setFont(new java.awt.Font("Californian FB", 0, 32)); // NOI18N
        hr_label.setForeground(new java.awt.Color(255, 255, 255));
        hr_label.setText("HR &");

        payroll_label1.setFont(new java.awt.Font("Californian FB", 0, 32)); // NOI18N
        payroll_label1.setForeground(new java.awt.Color(255, 255, 255));
        payroll_label1.setText("Payroll");

        system_label.setFont(new java.awt.Font("Californian FB", 0, 32)); // NOI18N
        system_label.setForeground(new java.awt.Color(255, 255, 255));
        system_label.setText("System");

        audit_button.setBackground(new java.awt.Color(250, 255, 255));
        audit_button.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        audit_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll/system/images/history_icon.png"))); // NOI18N
        audit_button.setText("Audit Trail");
        audit_button.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        audit_button.setIconTextGap(50);
        audit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                audit_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout side_menuLayout = new javax.swing.GroupLayout(side_menu);
        side_menu.setLayout(side_menuLayout);
        side_menuLayout.setHorizontalGroup(
            side_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(side_menuLayout.createSequentialGroup()
                .addGroup(side_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(side_menuLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(side_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(slip_button, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(calculation_button, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dashboard_button, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(employee_button, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(audit_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(side_menuLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(side_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(hr_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payroll_label1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(system_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        side_menuLayout.setVerticalGroup(
            side_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(side_menuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(side_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, side_menuLayout.createSequentialGroup()
                        .addComponent(hr_label)
                        .addGap(1, 1, 1)
                        .addComponent(payroll_label1)
                        .addGap(1, 1, 1)
                        .addComponent(system_label))
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(dashboard_button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(employee_button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(calculation_button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(slip_button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(audit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(side_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(side_menu, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1200, 680));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logout_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_buttonActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showOptionDialog(
            this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[]{"Logout", "Cancel"},
            "Logout"
        );

        if (option == JOptionPane.YES_OPTION) {
            Login x = new Login();
            x.setVisible(true);
            this.dispose();
            
            String action = "Logout as " + username;
            String auditSQL = "INSERT INTO Audit (audit_date, audit_time, audit_type, audit_action) VALUES (?,?,?,?)";
            try {
                pst = conn.prepareStatement(auditSQL);
                pst.setString(1, date);
                pst.setString(2, time);
                pst.setString(3, "Login & Logout");
                pst.setString(4, action);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_logout_buttonActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
        Homepage h = new Homepage();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void calculation_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculation_buttonActionPerformed
        // TODO add your handling code here:
        SalaryCalculation c = new SalaryCalculation(username);
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_calculation_buttonActionPerformed

    private void slip_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slip_buttonActionPerformed
        // TODO add your handling code here:
        SalarySlip s = new SalarySlip(username);
        s.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_slip_buttonActionPerformed

    private void dashboard_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard_buttonActionPerformed
        // TODO add your handling code here:
        Homepage x = new Homepage();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashboard_buttonActionPerformed

    private void employee_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_buttonActionPerformed
        // TODO add your handling code here:
        EmployeeManagement e = new EmployeeManagement(username);
        e.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_employee_buttonActionPerformed

    private void audit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_audit_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_audit_buttonActionPerformed

    private void clear_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_buttonActionPerformed
        // TODO add your handling code here:
       int response = JOptionPane.showConfirmDialog(
           this,
           "Are you sure you want to delete all Audit records?",
           "Confirmation",
           JOptionPane.YES_NO_OPTION
       );

       // Check the user's response
       if (response == JOptionPane.YES_OPTION) {
           try {
               String sql = "DELETE FROM Audit";
               st = conn.createStatement();
               st.executeUpdate(sql);

               showAudit();

               // Show a success message
               JOptionPane.showMessageDialog(null, "Audit records deleted successfully.");
           } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, "Error deleting audit records: " + e.getMessage());
           } finally {
               closeResources();
           }
       }
    }//GEN-LAST:event_clear_buttonActionPerformed

    private void sorting_comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sorting_comboboxActionPerformed
        // TODO add your handling code here:
        showAudit();
    }//GEN-LAST:event_sorting_comboboxActionPerformed

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
            java.util.logging.Logger.getLogger(Audit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Audit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Audit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Audit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Audit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton audit_button;
    private javax.swing.JTable audit_table;
    private javax.swing.JLabel back;
    private javax.swing.JButton calculation_button;
    private javax.swing.JButton clear_button;
    private javax.swing.JButton dashboard_button;
    private javax.swing.JButton employee_button;
    private javax.swing.JLabel greeting_label2;
    private javax.swing.JLabel hr_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JButton logout_button;
    private javax.swing.JPanel main_panel;
    private javax.swing.JLabel payroll_label1;
    private javax.swing.JPanel side_menu;
    private javax.swing.JButton slip_button;
    private javax.swing.JComboBox<String> sorting_combobox;
    private javax.swing.JLabel system_label;
    // End of variables declaration//GEN-END:variables
}
