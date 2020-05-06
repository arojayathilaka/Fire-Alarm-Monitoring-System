/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS_Project;

import DS_Project.RMI_Interface;
//import DS_Project.Sensors;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kesara
 */
public class viewSensors extends javax.swing.JFrame {
    

    /**
     * Creates new form viewSensors
     */
    public viewSensors() {
        initComponents();
        
        try{
           getSensorsView(); 
        }
        catch(RemoteException e){
            System.out.println(e.getMessage());
        }
        
        start();
        
        jTableViewSensors.setDefaultRenderer(Object.class, new Table_Colour());
        
    }
    
    //adding sensor details to the table
    public void getSensorsView() throws RemoteException{
    
        try {
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            RMI_Interface server = (RMI_Interface) reg.lookup("rmi://localhost/server");
            
            List<Sensors> list = server.getAllSensors();
           
            DefaultTableModel tableModel = (DefaultTableModel) jTableViewSensors.getModel();
            
            Object[] col = new Object[5];
            
           
                for(Sensors s : list){
                    col[0] = s.getId();
                    col[1] = s.isActive();
                    col[2] = s.getLocation();
                    col[3] = s.getSmokeLvl();
                    col[4] = s.getCO2Lvl();
                
                    tableModel.addRow(col);
                }
            
               
            
        }  catch(RemoteException | NotBoundException e) {
            System.out.println(e.getMessage());
        }
    
    }
    
    //RMI client get the sensor status every 15 second
    Timer timer = new Timer();

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            try {
                jTableViewSensors.setModel(new DefaultTableModel(null, new Object[]{"ID", "Active/Not Active", "Location", "Smoke Level", "CO2 Level"}));
                getSensorsView();

 

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

 

        }
    };
    
    
    public void start() {
        timer.scheduleAtFixedRate(task, 30000, 30000);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableViewSensors = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableViewSensors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Active/Not Active", "Location", "Smoke Level", "CO2 Level"
            }
        ));
        jScrollPane1.setViewportView(jTableViewSensors);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(viewSensors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewSensors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewSensors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewSensors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewSensors().setVisible(true);
            }
        });
        
        
        
        
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableViewSensors;
    // End of variables declaration//GEN-END:variables
}
